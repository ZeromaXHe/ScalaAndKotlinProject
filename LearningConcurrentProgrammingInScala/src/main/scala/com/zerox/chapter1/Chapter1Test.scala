package com.zerox.chapter1

/**
 * @author zhuxi
 * @since 2022/7/19 11:40
 * @note
 * 1 简介
 */
object Chapter1Test {
  class Printer(val greeting: String) {
    def printMessage(): Unit = println(greeting + "!")

    def printNumber(x: Int): Unit = {
      println("Number: " + x)
    }
  }

  // 特征
  trait Logging {
    def log(s: String): Unit

    def warn(s: String) = log("WARN: " + s)

    def error(s: String) = log("ERROR: " + s)
  }

  class PrintLogging extends Logging {
    def log(s: String) = println(s)
  }

  // 类型参数
  class Pair[P, Q](val first: P, val second: Q)

  // 闭包
  val twice1: Int => Int = (x: Int) => x * 2
  val twice2 = (x: Int) => x * 2
  val twice3: Int => Int = x => x * 2
  val twice4: Int => Int = _ * 2

  // 叫名参数
  def runTwice(body: => Unit): Unit = {
    body
    body
  }

  // for 推导语句
  val pairs1 = for (x <- 0 until 4; y <- 0 until 4) yield (x, y)
  val pairs2 = (0 until 4).flatMap(x => (0 until 4).map(y => (x, y)))

  // 操作符重载
  class Position(val x: Int, val y: Int) {
    def +(that: Position) = new Position(x + that.x, y + that.y)
  }

  def main(args: Array[String]): Unit = {
    val printy = new Printer("Hi")
    printy.printMessage()
    printy.printNumber(5)
    println("-----------------")
    runTwice(println("Hello"))
    println("-----------------")
    for (i <- 0 until 10) println(i)
    (0 until 10).foreach(i => println(i))
    println("-----------------")
    pairs1.foreach(println)
    pairs2.foreach(println)
    println("-----------------")
    // 模式匹配
    val successors = Map(1 -> 2, 2 -> 3, 3 -> 4)
    successors.get(5) match {
      case Some(n) => println(s"Successor is: $n")
      case None => println("Could not find successor.")
    }
  }
}
