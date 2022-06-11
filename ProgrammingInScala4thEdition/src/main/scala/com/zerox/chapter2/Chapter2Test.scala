package com.zerox.chapter2

/**
 * @Author: zhuxi
 * @Time: 2022/6/9 11:22
 * @Description: 第2章 Scala 入门
 * @ModifiedBy: zhuxi
 */
object Chapter2Test {

  def test2_3(): Unit = {
    println(max(3, 5))
  }

  def max(x: Int, y: Int): Int = if (x > y) x else y

  def greet() = println("Hello, world!")

  def test2_5(args: Array[String]): Unit = {
    var i = 0
    while (i < args.length) {
      println(args(i))
      i += 1
    }

    i = 0
    while (i < args.length) {
      if (i != 0)
        print(" ")
      print(args(i))
      i += 1
    }
    println()
  }

  def test2_6(args: Array[String]): Unit = {
    args.foreach(println)

    for (arg <- args)
      println(arg)
  }

  def main(args: Array[String]): Unit = {
    test2_3()
    println("------------")
    test2_5(Array("Scala", "is", "even", "more", "fun"))
    println("------------")
    test2_6(Array("Scala", "is", "even", "more", "fun"))
    println("------------")
  }

}
