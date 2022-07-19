package com.zerox.chapter3

import java.util.concurrent.atomic.{AtomicBoolean, AtomicLong, AtomicReference}
import java.util.concurrent.{ConcurrentHashMap, ForkJoinPool, TimeUnit}
import scala.collection.mutable
import scala.concurrent.ExecutionContext
import scala.jdk.CollectionConverters.ConcurrentMapHasAsScala

/**
 * @author zhuxi
 * @since 2022/7/19 15:26
 * @note
 * 3 构建并发程序的传统材料
 */
object Chapter3Test {

  def executorsCreate(): Unit = {
    val executor = new ForkJoinPool
    executor.execute(() => log("This task is run asynchronously"))
    // Thread.sleep(500)
    executor.shutdown()
    executor.awaitTermination(60, TimeUnit.SECONDS)
  }

  def executionContextGlobal() = {
    val ectx = ExecutionContext.global
    ectx.execute(() => log("Running on the execution context"))
    Thread.sleep(500)
  }

  def executionContextCreate(): Unit = {
    val pool = new ForkJoinPool(2)
    val ectx = ExecutionContext.fromExecutorService(pool)
    ectx.execute(() => log("Running on the execution context again."))
    Thread.sleep(500)
  }

  def execute(body: => Unit) = ExecutionContext.global.execute(() => body)

  def atomicUid(): Unit = {
    val uid = new AtomicLong(0L)

    def getUniqueId(): Long = uid.incrementAndGet()

    @scala.annotation.tailrec
    def getUniqueIdByCAS(): Long = {
      val oldUid = uid.get
      val newUid = oldUid + 1
      if (uid.compareAndSet(oldUid, newUid)) newUid
      else getUniqueIdByCAS()
    }

    execute(log(s"Uid asynchronously: ${getUniqueId()}"))
    log(s"Got a unique id: ${getUniqueId()}")
  }

  def atomicLock(): Unit = {
    val lock = new AtomicBoolean(false)

    def mySynchronized(body: => Unit): Unit = {
      while (!lock.compareAndSet(false, true)) {}
      try body finally lock.set(false)
    }

    var count = 0
    for (i <- 0 until 10) execute(mySynchronized(count += 1))
    Thread.sleep(100)
    log(s"Count is: $count")
  }

  def lazyValsCreate(): Unit = {
    lazy val obj = new AnyRef
    lazy val non = s"made by ${Thread.currentThread.getName}"
    execute {
      log(s"EC sees obj = $obj")
      log(s"EC sees non = $non")
    }
    log(s"Main sees obj = $obj")
    log(s"Main sees non = $non")
    Thread.sleep(500)
  }

  def lazyValsObject(): Unit = {
    object Lazy {
      log("Running Lazy constructor.")
    }
    log("Main thread is about to reference Lazy.")
    Lazy
    log("Main thread completed")
  }

  class AtomicBuffer[T] {
    private val buffer = new AtomicReference[List[T]](Nil)

    @scala.annotation.tailrec
    final def +=(x: T): Unit = {
      val xs = buffer.get
      val nxs = x :: xs
      if (!buffer.compareAndSet(xs, nxs)) this += x
    }
  }


  def collections(): Unit = {
    val buffer = mutable.ArrayBuffer[Int]()

    def asyncAdd(numbers: Seq[Int]) = execute {
      buffer.synchronized {
        buffer ++= numbers
        log(s"buffer = $buffer")
      }
    }

    asyncAdd(0 until 10)
    asyncAdd(10 until 20)
    Thread.sleep(500)
  }

  def collectionsConcurrentMapBulk(): Unit = {
    val names = new ConcurrentHashMap[String, Int]().asScala
    names("Johnny") = 0
    names("Jane") = 0
    names("Jack") = 0
    execute(for (n <- 0 until 10) names(s"John $n") = n)
    execute(for (n <- names) log(s"name: $n"))
    Thread.sleep(100)
  }

  def collectionsTrieMapBulk(): Unit = {
    val names = new collection.concurrent.TrieMap[String, Int]()
    names("Janice") = 0
    names("Jackie") = 0
    names("Jill") = 0
    execute(for (n <- 0 until 10) names(s"John $n") = n)
    execute {
      log("snapshot time!")
      for (n <- names.keys.toSeq.sorted) log(s"name: $n")
    }
    Thread.sleep(100)
  }

  def processRun(): Unit = {
    import scala.sys.process._
    val command = "ls"
    val exitcode = command.!
    log(s"command exited with status $exitcode")
  }

  def lineCount(filename: String): Int = {
    import scala.sys.process._
    val output = s"wc $filename".!!
    output.trim.split(" ").head.toInt
  }

  def processAsync(): Unit = {
    import scala.sys.process._
    val lsProcess = "ls -R /".run()
    Thread.sleep(1000)
    log("Timeout - killing ls!")
    lsProcess.destroy()
  }

  def main(args: Array[String]): Unit = {
    executorsCreate()
    println("-------------")
    executionContextGlobal()
    println("-------------")
    executionContextCreate()
    println("-------------")
    atomicUid()
    println("-------------")
    atomicLock()
    println("-------------")
    lazyValsCreate()
    println("-------------")
    lazyValsObject()
    println("-------------")
    collections()
    println("-------------")
    collectionsConcurrentMapBulk()
    collectionsTrieMapBulk()
    println("-------------")
  }
}
