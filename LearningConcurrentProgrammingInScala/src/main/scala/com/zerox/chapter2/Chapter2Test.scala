package com.zerox.chapter2

/**
 * @author zhuxi
 * @since 2022/7/19 13:46
 * @note
 * 2 JVM 和 Java 内存模型中的并发处理方式
 */
object Chapter2Test {

  def threadMain() = {
    val t: Thread = Thread.currentThread
    val name = t.getName
    println(s"I am the thread $name")
  }

  def threadsCreation(): Unit = {
    class MyThread extends Thread {
      override def run(): Unit = println("New thread running.")
    }
    val t = new MyThread
    t.start()
    t.join()
    println("New thread joined.")
  }

  def thread(body: => Unit): Thread = {
    val t = new Thread {
      override def run(): Unit = body
    }
    t.start()
    t
  }

  def threadsSleep(): Unit = {
    val t = thread {
      Thread.sleep(100)
      log("New thread running.")
      Thread.sleep(100)
      log("Still running.")
      Thread.sleep(100)
      log("Completed.")
    }
    t.join()
    log("New thread joined.")
  }

  def threadsNondeterminism(): Unit = {
    val t = thread {
      log("New thread running.")
    }
    log("...")
    log("...")
    t.join()
    log("New thread joined.")
  }

  def threadsCommunicate(): Unit = {
    var result: String = null
    val t = thread {
      result = "\nTitle\n" + "=" * 5
    }
    t.join()
    log(result)
  }

  def threadsUnprotectedUid(): Unit = {
    var uidCount = 0L

    def getUniqueId() = /*this.synchronized*/ {
      val freshUid = uidCount + 1
      uidCount = freshUid
      freshUid
    }

    def printUniqueIds(n: Int): Unit = {
      val uids = for (i <- 0 until n) yield getUniqueId()
      log(s"Generated uids: $uids")
    }

    val t = thread {
      printUniqueIds(5)
    }
    printUniqueIds(5)
    t.join()
  }

  def threadSharedStateAccessReordering(): Unit = {
    for (i <- 0 until 1000 /*00*/ ) {
      var a = false
      var b = false
      var x = -1
      var y = -1
      val t1 = thread {
        a = true
        y = if (b) 0 else 1
      }
      val t2 = thread {
        b = true
        x = if (a) 0 else 1
      }
      t1.join()
      t2.join()
      if (x == 1 && y == 1) println(s"assert failed! x = $x, y = $y")
    }
  }

  private val transfers = scala.collection.mutable.ArrayBuffer[String]()

  def logTransfer(name: String, n: Int) = transfers.synchronized {
    transfers += s"transfer to account '$name' = $n"
  }

  class Account(val name: String, var money: Int)

  def add(account: Account, n: Int) = account.synchronized {
    account.money += n
    if (n > 10) logTransfer(account.name, n)
  }

  def transferAccounts(): Unit = {
    val jane = new Account("Jane", 100)
    val john = new Account("John", 200)
    val t1 = thread {
      add(jane, 5)
    }
    val t2 = thread {
      add(john, 50)
    }
    val t3 = thread {
      add(jane, 70)
    }
    t1.join()
    t2.join()
    t3.join()
    log(s"--- transfer ---\n$transfers")
  }

  def synchronizedDeadLock(): Unit = {
    var uidCount = 0L

    def getUniqueId() = this.synchronized {
      val freshUid = uidCount + 1
      uidCount = freshUid
      freshUid
    }

    class Account(val name: String, var money: Int) {
      val uid: Long = getUniqueId()
    }

    def send(a: Account, b: Account, n: Int) = {
      // a.synchronized {
      //   b.synchronized {
      //     a.money -= n
      //     b.money += n
      //   }
      // }
      def adjust(): Unit = {
        a.money -= n
        b.money += n
      }

      if (a.uid < b.uid) a.synchronized(b.synchronized(adjust()))
      else b.synchronized(a.synchronized(adjust()))
    }

    val a = new Account("Jack", 1000)
    val b = new Account("Jill", 2000)
    val t1 = thread {
      for (i <- 0 until 100) send(a, b, 1)
    }
    val t2 = thread {
      for (i <- 0 until 100) send(b, a, 1)
    }
    t1.join()
    t2.join()
    log(s"a = ${a.money}, b = ${b.money}")
  }

  def synchronizedGuardedBlocks(): Unit = {
    val lock = new AnyRef
    var message: Option[String] = None
    val greeter = thread {
      lock.synchronized {
        while (message == None) lock.wait()
        log(message.get)
      }
    }

    lock.synchronized {
      message = Some("Hello!")
      lock.notify()
    }
    greeter.join()
  }

  def synchronizedPool(): Unit = {
    val tasks = scala.collection.mutable.Queue[() => Unit]()

    object Worker extends Thread {
      setDaemon(true)

      def poll() = tasks.synchronized {
        while (tasks.isEmpty) tasks.wait()
        tasks.dequeue()
      }

      override def run() = while (true) {
        val task = poll()
        task()
      }
    }
    Worker.start()

    // val worker: Thread = new Thread {
    //   def poll(): Option[() => Unit] = tasks.synchronized {
    //     if (tasks.nonEmpty) Some(tasks.dequeue()) else None
    //   }
    //
    //   override def run(): Unit = while (true) poll() match {
    //     case Some(task) => task()
    //     case None =>
    //   }
    // }
    //
    // worker.setName("Worker")
    // worker.setDaemon(true)
    // worker.start()

    def asynchronous(body: => Unit) = tasks.synchronized {
      tasks.enqueue(() => body)
      tasks.notify()
    }

    asynchronous(log("Hello"))
    asynchronous(log(" world!"))
    Thread.sleep(500)
  }

  val tasks = scala.collection.mutable.Queue[() => Unit]()

  object Worker extends Thread {
    var terminated = false

    def poll(): Option[() => Unit] = tasks.synchronized {
      while (tasks.isEmpty && !terminated) tasks.wait()
      if (!terminated) Some(tasks.dequeue()) else None
    }

    @scala.annotation.tailrec
    override def run(): Unit = poll() match {
      case Some(task) =>
        task()
        run()
      case None =>
    }

    def shutdown() = tasks.synchronized {
      terminated = true
      tasks.notify()
    }
  }

  class Page(val txt: String, var position: Int)

  def volatile(): Unit = {
    val pages = for (i <- 1 to 5) yield new Page("Na" * (100 - 20 * i) + " Batman!", -1)

    @volatile var found = false

    for (p <- pages) yield thread {
      var i = 0
      while (i < p.txt.length && !found) {
        if (p.txt(i) == '!') {
          p.position = i
          found = true
        } else i += 1
      }
    }
    while (!found) {}
    log(s"results: ${pages.map(_.position)}")
  }

  def main(args: Array[String]): Unit = {
    threadMain()
    println("-----------")
    threadsCreation()
    println("-----------")
    threadsSleep()
    println("-----------")
    threadsNondeterminism()
    println("-----------")
    threadsCommunicate()
    println("-----------")
    threadsUnprotectedUid()
    println("-----------")
    threadSharedStateAccessReordering()
    println("-----------")
    transferAccounts()
    println("-----------")
    synchronizedGuardedBlocks()
    println("-----------")
    synchronizedDeadLock()
    println("-----------")
    synchronizedPool()
    println("-----------")
    volatile()
  }
}
