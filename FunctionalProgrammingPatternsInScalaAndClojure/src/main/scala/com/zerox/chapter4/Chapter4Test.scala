package com.zerox.chapter4

import scala.collection.JavaConverters._
import scala.collection.mutable.ArrayBuffer
import scala.io.Source
import scala.util.Random
import scala.util.control.TailCalls.{TailRec, done, tailcall}

/**
 * @author zhuxi
 * @since 2022/7/14 14:02
 * @note
 * 第 4 章 函数式模式
 */
object Chapter4Test {
  /**
   * 模式 12 尾递归模式
   */
  def pattern12(): Unit = {
    // 范例代码：递归的“人”
    case class Person(firstNames: String, lastNames: String)

    def makePeople(firstNames: Seq[String], lastNames: Seq[String]) = {
      @scala.annotation.tailrec
      def helper(firstNames: Seq[String], lastNames: Seq[String], people: Vector[Person]): Seq[Person] = {
        if (firstNames.isEmpty) people
        else {
          val newPerson = Person(firstNames.head, lastNames.head)
          helper(firstNames.tail, lastNames.tail, people :+ newPerson)
        }
      }

      helper(firstNames, lastNames, Vector[Person]())
    }

    println(makePeople(Array("first1", "first2"), Array("last1", "last2")))
  }

  /**
   * 模式 13 相互递归模式
   */
  def pattern13(): Unit = {
    def isOddTrampoline(n: Long): TailRec[Boolean] = if (n == 0) done(false) else tailcall(isEvenTrampoline(n - 1))

    def isEvenTrampoline(n: Long): TailRec[Boolean] = if (n == 0) done(true) else tailcall(isOddTrampoline(n - 1))

    println(isEvenTrampoline(0).result)
    println(isEvenTrampoline(1).result)
    println(isEvenTrampoline(1000).result)
    println(isEvenTrampoline(1001).result)
    println(isEvenTrampoline(100001).result)

    // 范例代码：物质的状态
    class Transition
    case object Ionization extends Transition
    case object Deionization extends Transition
    case object Vaporization extends Transition
    case object Condensation extends Transition
    case object Freezing extends Transition
    case object Melting extends Transition
    case object Sublimation extends Transition
    case object Deposition extends Transition

    def plasma(transition: List[Transition]): TailRec[Boolean] = transition match {
      case Nil => done(true)
      case Deionization :: restTransitions => tailcall(vapor(restTransitions))
      case _ => done(false)
    }

    def vapor(transition: List[Transition]): TailRec[Boolean] = transition match {
      case Nil => done(true)
      case Condensation :: restTransitions => tailcall(liquid(restTransitions))
      case Deposition :: restTransitions => tailcall(solid(restTransitions))
      case Ionization :: restTransitions => tailcall(plasma(restTransitions))
      case _ => done(false)
    }

    def liquid(transition: List[Transition]): TailRec[Boolean] = transition match {
      case Nil => done(true)
      case Vaporization :: restTransitions => tailcall(vapor(restTransitions))
      case Freezing :: restTransitions => tailcall(solid(restTransitions))
      case _ => done(false)
    }

    def solid(transition: List[Transition]): TailRec[Boolean] = transition match {
      case Nil => done(true)
      case Melting :: restTransitions => tailcall(liquid(restTransitions))
      case Sublimation :: restTransitions => tailcall(vapor(restTransitions))
      case _ => done(false)
    }

    val validSequence = List(Melting, Vaporization, Ionization, Deionization)
    println("solid(validSequence): " + solid(validSequence).result)
    val invalidSequence = List(Vaporization, Freezing)
    println("liquid(invalidSequence): " + liquid(invalidSequence).result)
  }

  /**
   * 模式 14 Filter-Map-Reduce 模式
   */
  def pattern14(): Unit = {
    // 范例代码：折扣计算
    def calculateDiscount(prices: Seq[Double]): Double = {
      prices filter (price => price >= 20.0) map (price => price * 0.10) reduce ((total, price) => total + price)
    }

    println(calculateDiscount(Vector(20.0, 4.5, 50.0, 15.75, 30.0, 3.5))) // 10.0

    def calculateDiscountNamedFn(prices: Seq[Double]): Double = {
      def isGreaterThan20(price: Double) = price >= 20.0

      def tenPercent(price: Double) = price * 0.10

      def sumPrices(total: Double, price: Double) = total + price

      prices filter isGreaterThan20 map tenPercent reduce sumPrices
    }

    println(calculateDiscountNamedFn(Vector(20.0, 4.5, 50.0, 15.75, 30.0, 3.5))) // 10.0
  }

  /**
   * 模式 15 操作链模式
   */
  def pattern15(): Unit = {
    // 范例代码：函数调用链
    case class Video(title: String, video_type: String, length: Int)

    val v1 = Video("Pianocat Plays Carnegie Hall", "cat", 300)
    val v2 = Video("Paint Drying", "home-improvement", 300)
    val v3 = Video("Fuzzy McMittens Live At The Apollo", "cat", 200)

    val videos = Vector(v1, v2, v3)

    def catTime(videos: Vector[Video]) = {
      videos
        .filter(video => video.video_type == "cat")
        .map(video => video.length)
        .sum
    }

    println(catTime(videos))

    // 范例代码：采用序列推导来完成链式操作
    val vec1 = Vector(42)
    val vec2 = Vector(8)
    println(for (i1 <- vec1; i2 <- vec2) yield i1 + i2)

    val o1 = Some(42)
    val o2 = Some(8)
    println(for (v1 <- o1; v2 <- o2) yield v1 + v2)
    val o3: Option[Int] = None
    println(for (v1 <- o1; v3 <- o3) yield v1 + v3)

    case class User(name: String, id: String)
    case class Movie(name: String, id: String)

    def getUserById(id: String) = id match {
      case "1" => Some(User("Mike", "1"))
      case _ => None
    }

    def getFavoriteMovieForUser(user: User) = user match {
      case User(_, "1") => Some(Movie("Gigli", "101"))
      case _ => None
    }

    def getVideosForMovie(movie: Movie) = movie match {
      case Movie(_, "101") =>
        Some(Vector(Video("Interview With Cast", "interview", 480), Video("Gigli", "feature", 7260)))
      case _ => None
    }

    def getFavoriteVideos(userId: String) = for {
      user <- getUserById(userId)
      favoriteMovie <- getFavoriteMovieForUser(user)
      favoriteVideos <- getVideosForMovie(favoriteMovie)
    } yield favoriteVideos

    println(getFavoriteVideos("1"))
    println(getFavoriteVideos("42"))
  }

  /**
   * 模式 16 函数生成器模式
   */
  def pattern16(): Unit = {
    // 范例代码：通过静态数据生成函数
    // 折扣计算器生成器
    def discount(percent: Double) = {
      if (percent < 0.0 || percent > 100.0) throw new IllegalArgumentException("Discounts must be between 0.0 and 100.0")
      (originalPrice: Double) => originalPrice - (originalPrice * percent * 0.01)
    }

    println(discount(50)(200))
    println(discount(0)(200))
    println(discount(100)(200))

    val twentyFivePercentOff = discount(25)
    println(Vector(100.0, 25.0, 50.0, 25.0) map twentyFivePercentOff sum) // 150.0
    println(Vector(75.0, 25.0) map twentyFivePercentOff sum) // 75.0

    // map 的键选择器
    def selector(path: Symbol*): Map[Symbol, Any] => Option[Any] = {
      if (path.size <= 0) throw new IllegalArgumentException("path must not be empty")

      def selectorHelper(path: Seq[Symbol], ds: Map[Symbol, Any]): Option[Any] =
        if (path.size == 1) ds.get(path.head)
        else {
          val currentPiece = ds.get(path.head)
          currentPiece match {
            case Some(currentMap: Map[Symbol, Any]) => selectorHelper(path.tail, currentMap)
            case None => None
            case _ => None
          }
        }

      (ds: Map[Symbol, Any]) => selectorHelper(path.toSeq, ds)
    }

    val person = Map('name -> Map('first -> "Rob"))
    val firstName = selector('name, 'first)
    println(firstName(person))

    val simplePerson = Map('name -> "Michael Bevilacqua-Linn")
    val name = selector('name)
    name(simplePerson)

    val moreComplexPerson = Map('name -> Map('first -> "Michael", 'last -> "Bevilacqua-Linn"))
    println(firstName(moreComplexPerson))
    val middleName = selector('name, 'middle)
    println(middleName(moreComplexPerson))

    // 通过其他函数来生成函数
    val appendA = (s: String) => s + "a"
    val appendB = (s: String) => s + "b"
    val appendC = (s: String) => s + "c"
    val appendCBA = appendA compose appendB compose appendC
    println(appendCBA("z"))

    case class HttpRequest(headers: Map[String, String], payload: String, principal: Option[String] = None)

    // def checkAuthorization: HttpRequest => HttpRequest = {
    //   request =>
    //     val authHeader = request.headers.get("Authorization")
    //     val mockPrincipal = authHeader match {
    //       case Some(_) => Some("AUser")
    //       case _ => None
    //     }
    //     request.copy(principal = mockPrincipal)
    // }
    //
    // def logFingerprint: HttpRequest => HttpRequest = {
    //   request =>
    //     val fingerprint = request.headers.getOrElse("X-RequestFingerprint", "")
    //     println("FINGERPRINT=" + fingerprint)
    //     request
    // }
    //
    // def composeFilters(filters: Seq[HttpRequest => HttpRequest]) =
    //   filters.reduce {
    //     (allFilters, currentFilter) => allFilters compose currentFilter
    //   }
    //
    // val filters = Vector(checkAuthorization, logFingerprint)

    def checkAuthorization(request: HttpRequest): HttpRequest = {
      val authHeader = request.headers.get("Authorization")
      val mockPrincipal = authHeader match {
        case Some(_) => Some("AUser")
        case _ => None
      }
      request.copy(principal = mockPrincipal)
    }

    def logFingerprint(request: HttpRequest): HttpRequest = {
      val fingerprint = request.headers.getOrElse("X-RequestFingerprint", "")
      println("FINGERPRINT=" + fingerprint)
      request
    }

    def composeFilters(filters: Seq[HttpRequest => HttpRequest]) =
      filters.reduce {
        (allFilters, currentFilter) => allFilters compose currentFilter
      }

    val filters = Vector(checkAuthorization _, logFingerprint _) // 如果不想加 _, 可以参考前面注释的代码
    val filterChain = composeFilters(filters)
    val requestHeaders = Map("Authorization" -> "Auth", "X-RequestFingerprint" -> "fingerprint")
    val request = HttpRequest(requestHeaders, "body")
    println(filterChain(request))

    // 部分应用函数
    def addTwoInts(intOne: Int, intTwo: Int) = intOne + intTwo

    val addFortyTwo = addTwoInts(42, _: Int)

    println(addFortyTwo(100))

    def taxForState(amount: Double, state: Symbol) = state match {
      case 'NY => amount * 0.0645
      case 'PA => amount * 0.045
    }

    val nyTax = taxForState(_: Double, 'NY)
    val paTax = taxForState(_: Double, 'PA)
    println(nyTax(100))
    println(paTax(100))
  }

  /**
   * 模式 17 记忆模式
   */
  def pattern17(): Unit = {
    def expensiveLookup(id: Int) = {
      Thread.sleep(100)
      println(s"Doing expensive lookup for $id")
      Map(42 -> "foo", 12 -> "bar", 1 -> "baz").get(id)
    }

    println(expensiveLookup(42))
    println(expensiveLookup(42))

    def memoizeExpensiveLookup() = {
      var cache = Map[Int, Option[String]]()
      (id: Int) =>
        cache.get(id) match {
          case Some(result: Option[String]) => result
          case None =>
            val result = expensiveLookup(id)
            cache += id -> result
            result
        }
    }

    val memoizedExpensiveLookup = memoizeExpensiveLookup()
    println(memoizedExpensiveLookup(42))
    println(memoizedExpensiveLookup(42))
  }

  /**
   * 模式 18 惰性序列模式
   */
  def pattern18(): Unit = {
    // 范例代码：内建的惰性序列
    val integers = Stream.from(0)
    val someints = integers take 5
    someints foreach println

    val generate = new Random()
    val randoms = Stream.continually(generate.nextInt)
    val aFewRandoms = randoms take 5
    aFewRandoms foreach println
    val aFewMoreRandoms = randoms take 6
    aFewMoreRandoms foreach println
    // 注意前五个数字是如何重复出现的。这是因为 Stream 库依赖于记忆模式，它会将你看到的每个数字进行缓存

    // 范例代码：分页的数据响应
    def pagedSequence(pageNum: Int): Stream[String] =
      getPage(pageNum) match {
        case Some(page: String) => page #:: pagedSequence(pageNum + 1)
        case None => Stream.Empty
      }

    def getPage(page: Int) = page match {
      case 1 => Some("Page1")
      case 2 => Some("Page2")
      case 3 => Some("Page3")
      case _ => None
    }

    val aStream = "foo" #:: "bar" #:: Stream[String]()
    println(aStream.head)
    println(aStream.tail)

    println(pagedSequence(1) take 2 force)
    println(pagedSequence(1) force)

    // 讨论
    val holdsHead = {
      def pagedSequence(pageNum: Int): Stream[String] = {
        getPage(pageNum) match {
          case Some(page: String) =>
            println("Realizing " + page)
            page #:: pagedSequence(pageNum + 1)
          case None => Stream.Empty
        }
      }

      pagedSequence(1)
    }

    println(holdsHead force)
    println(holdsHead force)

    def doesntHoldsHead = {
      def pagedSequence(pageNum: Int): Stream[String] = {
        getPage(pageNum) match {
          case Some(page: String) =>
            println("Realizing " + page)
            page #:: pagedSequence(pageNum + 1)
          case None => Stream.Empty
        }
      }

      pagedSequence(1)
    }

    println(doesntHoldsHead force)
    println(doesntHoldsHead force)
  }

  /**
   * 模式 19 集中的可变性
   */
  def pattern19(): Unit = {
    // 范例代码：添加元素到索引序列
    def testImmutable(count: Int): IndexedSeq[Int] = {
      var v = Vector[Int]()
      for (c <- Range(0, count)) v = v :+ c
      v
    }

    def testMutable(count: Int): IndexedSeq[Int] = {
      val s = ArrayBuffer[Int](count)
      for (c <- Range(0, count)) s.append(c)
      s.toIndexedSeq
    }

    def time[R](block: => R): R = {
      val start = System.nanoTime
      val result = block
      val end = System.nanoTime
      val elapsedTimeMs = (end - start) * 0.000001
      println("Elapsed time: %.3f msecs".format(elapsedTimeMs))
      result
    }

    def timeRuns[R](block: => R, count: Int) =
      for (_ <- Range(0, count)) time {
        block
      }

    val oneMillion = 1000000
    timeRuns(testImmutable(oneMillion), 5)
    println()
    timeRuns(testMutable(oneMillion), 5)
    println()

    // 范例代码：事件流处理
    case class Purchase(storeNumber: Int, customerNumber: Int, itemNumber: Int)
    val r = new Random

    def makeTestPurchase = Purchase(r.nextInt(100), r.nextInt(1000), r.nextInt(500))

    def infiniteTestPurchases: Stream[Purchase] = makeTestPurchase #:: infiniteTestPurchases

    val fiveTestPurchases = infiniteTestPurchases take 5
    for (purchase <- fiveTestPurchases) println(purchase)
    println()

    def immutableSequenceEventProcessing(count: Int) = {
      val testPurchases = infiniteTestPurchases.take(count)
      var mapOfPurchases = Map[Int, List[Purchase]]()
      for (purchase <- testPurchases)
        mapOfPurchases.get(purchase.storeNumber) match {
          case None => mapOfPurchases = mapOfPurchases + (purchase.storeNumber -> List(purchase))
          case Some(existing: List[Purchase]) => mapOfPurchases = mapOfPurchases + (purchase.storeNumber -> (purchase :: existing))
        }
      mapOfPurchases
    }

    def mutableSequenceEventProcessing(count: Int) = {
      val testPurchases = infiniteTestPurchases.take(count)
      val mapOfPurchases = scala.collection.mutable.Map[Int, List[Purchase]]()
      for (purchase <- testPurchases)
        mapOfPurchases.get(purchase.storeNumber) match {
          case None => mapOfPurchases.put(purchase.storeNumber, List(purchase))
          case Some(existing: List[Purchase]) => mapOfPurchases.put(purchase.storeNumber, purchase :: existing)
        }
      mapOfPurchases.toMap
    }

    val fiveHundredThousand = 500000
    timeRuns(immutableSequenceEventProcessing(fiveHundredThousand), 5)
    println()
    timeRuns(mutableSequenceEventProcessing(fiveHundredThousand), 5)
    println()
  }

  /**
   * 模式 20 自定义控制流
   */
  def pattern20(): Unit = {
    // 范例代码：三者选一
    def choose[E](num: Int, first: () => E, second: () => E, third: () => E) = {
      if (num == 1) first()
      else if (num == 2) second()
      else if (num == 3) third()
    }

    choose(2,
      () => println("hello, world"),
      () => println("goodbye, cruel world"),
      () => println("meh, indifferent world"))

    def test[E](expression: E) = expression

    test(println("hello, world"))

    def testTwice[E](expression: E) = {
      expression
      expression
    }

    testTwice(println("hello, world 2"))

    def testByName[E](expression: => E): Unit = {
      expression
      expression
    }

    testByName(println("hello, world byName"))

    def simplerChoose[E](num: Int, first: => E, second: => E, third: => E) = {
      if (num == 1) first
      else if (num == 2) second
      else if (num == 3) third
    }

    simplerChoose(2,
      println("hello, world"),
      println("goodbye, cruel world"),
      println("meh, indifferent world"))

    // 范例代码：平均时间
    def timeRun[E](toTime: => E) = {
      val start = System.currentTimeMillis
      toTime
      System.currentTimeMillis - start
    }

    def avgTime[E](times: Int, toTime: => E) = {
      val allTimes = for (_ <- Range(0, times)) yield timeRun(toTime)
      allTimes.sum / times
    }

    println(avgTime(5, Thread.sleep(100)))
  }

  /**
   * 模式 21 领域特定语言
   */
  def pattern21(): Unit = {
    // 范例代码：为 shell 而准备的 DSL
    case class CommandResult(status: Int, output: String, error: String)

    val lsProcessBuilder = new ProcessBuilder("ls", "-la")
    val lsProcess = lsProcessBuilder.start
    println(Source.fromInputStream(lsProcess.getInputStream).mkString(""))

    class Command(commandParts: List[String]) {
      def run() = {
        val processBuilder = new ProcessBuilder(commandParts.asJava)
        val process = processBuilder.start()
        val status = process.waitFor()
        val outputAsString = Source.fromInputStream(process.getInputStream).mkString("")
        val errorAsString = Source.fromInputStream(process.getErrorStream).mkString("")
        CommandResult(status, outputAsString, errorAsString)
      }
    }

    object Command {
      def apply(commandString: String) = new Command(commandString.split("\\s").toList)

      def apply(commandParts: String*) = new Command(commandParts.toList)
    }

    implicit class CommandString(firstCommandString: String) {
      def run(): CommandResult = Command(firstCommandString).run()

      def pipe(secondCommandString: String) = Vector(firstCommandString, secondCommandString)
    }

    "ls -la" run()

    implicit class CommandVector(existingCommands: Vector[String]) {
      def run = {
        val pipedCommands = existingCommands.mkString(" | ")
        Command("/bin/sh", "-c", pipedCommands).run()
      }

      def pipe(nextCommand: String): Vector[String] = {
        existingCommands :+ nextCommand
      }
    }

    "ls -la" pipe "grep build" run

    "ls -la" pipe "grep build" pipe "wc" run
  }

  def main(args: Array[String]): Unit = {
    pattern12()
    println("-------------")
    pattern13()
    println("-------------")
    pattern14()
    println("-------------")
    pattern15()
    println("-------------")
    pattern16()
    println("-------------")
    pattern17()
    println("-------------")
    pattern18()
    println("-------------")
    pattern19()
    println("-------------")
    pattern20()
    println("-------------")
    // pattern21()
  }
}
