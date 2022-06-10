package chapter9

import java.io.{File, PrintWriter}

/**
 * @Author: zhuxi
 * @Time: 2022/6/9 19:36
 * @Description:
 * @ModifiedBy: zhuxi
 */
object Chapter9Test {

  /**
   * 9.1 减少代码重复
   */
  def test9_1(): Unit = {
    def filesHere = (new File(".")).listFiles

    def filesMatching(matcher: String => Boolean) = {
      for (file <- filesHere; if matcher(file.getName))
        yield file
    }

    def filesEnding(query: String) = filesMatching(_.endsWith(query))

    def filesContaining(query: String) = filesMatching(_.contains(query))

    def filesRegex(query: String) = filesMatching(_.matches(query))
  }

  /**
   * 9.2 简化调用方代码
   */
  def test9_2(): Unit = {
    def containsNeg(nums: List[Int]) = nums.exists(_ < 0)

    println(containsNeg(Nil))
    println(containsNeg(List(0, -1, -2)))

    def containsOdd(nums: List[Int]) = nums.exists(_ % 2 == 1)
  }

  /**
   * 9.3 柯里化
   */
  def test9_3(): Unit = {
    // 示例 9.2 展示了一个常规的，没有经过柯里化的函数，对两个 Int 参数 x 和 y 做加法
    def plainOldSum(x: Int, y: Int) = x + y

    println(plainOldSum(1, 2))

    // 与此相反，示例 9.3 展示了一个相似功能的函数，不过这次是经过柯里化的。
    // 跟使用一个包含两个Int参数列表不同，应用这个函数到两个参数列表，每个列表包含一个Int参数。
    def curriedSum(x: Int)(y: Int) = x + y

    println(curriedSum(1)(2))

    // 这里发生的事情是，当你调用 curriedSum，实际上是连着做了两次传统的函数调用。
    // 第一次调用接收了一个名为 x 的 Int 参数，返回一个用于第二次调用的函数值，这个函数接收一个 Int 参数 y。
    def first(x: Int) = (y: Int) => x + y

    val second = first(1)
    println(second(2))

    // 这里的 first 和 second 函数只是对柯里化过程的示意，它们跟 curriedSum 并不直接相关。
    // 尽管如此，我们还是有办法获取到指向 curriedSum 的“第二个”函数的引用。
    // 这个办法就是通过占位符表示法，在一个部分应用函数表达式中使用 curriedSum
    val onePlus = curriedSum(1) _
    println(onePlus(2))

    // _ 前面的空格居然可以省略
    // 注释里面写了原因：在前一章，当我们对传统方法使用占位符表示法时，比如 println _，需要在方法名和下画线之间放一个空格。
    // 在本例中不需要这样做，因为 println_ 是一个合法的 Scala 标识符，但 curriedSum(1)_ 并不是。
    val twoPlus = curriedSum(2) _
    println(twoPlus(2))
  }

  /**
   * 9.4 编写新的控制结构
   */
  def test9_4(): Unit = {
    def twice(op: Double => Double, x: Double) = op(op(x))

    println(twice(_ + 1, 5))

    def withPrintWriter(file: File, op: PrintWriter => Unit) = {
      val writer = new PrintWriter(file)
      try {
        op(writer)
      } finally {
        writer.close()
      }
    }

    val file = new File("date.txt")
    // 在项目文件夹根目录下
    println(file.getAbsolutePath)
    withPrintWriter(file, writer => writer.println("plain: " + new java.util.Date))

    // 可以用花括号而不是圆括号来表示参数列表，这样调用方的代码看上去更像是在使用内建的控制结构。
    // 在 Scala 中，只要有那种只传入一个参数的方法调用，都可以选择使用花括号来将入参包起来，而不是圆括号。
    println("Hello, world")
    // 在第二个例子中，用了花括号而不是圆括号来将 println 的入参包起来。不过，这个花括号技巧仅对传入单个入参的场景适用。
    println {
      "Hello, world"
    }

    // Scala 允许用花括号替代圆括号来传入单个入参的目的是，为了让调用方程序员在花括号当中编写函数字面量。这能让方法用起来更像是控制抽象。
    // 拿前面的 withPrintWriter 举例，在最新的版本中，withPrintWriter 接收两个入参，因此你不能用花括号。
    // 尽管如此，由于传入 withPrintWriter 的函数是参数列表中的最后一个，
    // 可以用柯里化将第一个 File 参数单独拉到一个参数列表中，这样剩下的函数就独占了第二个参数列表。
    // 示例 9.4 展示了如何重新定义 withPrintWriter。
    def withPrintWriterCurried(file: File)(op: PrintWriter => Unit) = {
      // 方法体里面的结构一样，只是参数柯里化了
      withPrintWriter(file, op)
    }

    withPrintWriterCurried(file) {
      writer => writer.println("curried: " + new java.util.Date)
    }
  }

  /**
   * 9.5 传名参数
   */
  def test9_5(): Unit = {
    var assertionsEnabled = true

    // 你大概更希望能不在函数字面量里写空的圆括号和 => 符号。传名参数就是为此而生的。
    // 要让参数成为传名参数，需要给参数一个以 => 开头的类型声明，而不是 () =>。
    def byNameAssert(predicate: => Boolean) =
      if (assertionsEnabled && !predicate)
        throw new AssertionError

    byNameAssert(5 > 3)

    // 你可能会好奇为什么不能简单地用老旧的 Boolean 来作为其参数的类型声明。
    // 这两种方式有一个显著的区别需要注意。
    // 由于boolAssert 的参数类型为Boolean，在boolAssert(5 > 3)圆括号中的表达式将“先于”对 boolAssert 的调用被求值。
    // 而由于 byNameAssert 的 predicate 参数类型是 => Boolean，
    // 在 byNameAssert(5 > 3) 的圆括号中的表达式在调用 byNameAssert 之前并不会被求值，而是会有一个函数值被创建出来，
    // 这个函数值的 apply 方法将会对 5 > 3 求值，传入 byNameAssert 的是这个函数值。
    def boolAssert(predicate: Boolean) =
      if (assertionsEnabled && !predicate)
        throw new AssertionError

    boolAssert(5 > 3)

    // 因此，两种方式的区别在于如果断言被禁用，你将能够观察到 boolAssert 的圆括号当中的表达式的副作用，而用 byNameAssert则不会。
    assertionsEnabled = false
    val x = 5
    try {
      // 如果断言被禁用，那么我们断言 “x / 0 == 0” 的话，boolAssert 会抛异常
      boolAssert(x / 0 == 0)
    } catch {
      case ex: Throwable => ex.printStackTrace()
    }
    // 而对同样的代码用byNameAssert来做断言的话，不会有异常抛出
    byNameAssert(x / 0 == 0)
  }

  def main(args: Array[String]): Unit = {
    test9_1()
    println("---------------")
    test9_2()
    println("---------------")
    test9_3()
    println("---------------")
    test9_4()
    println("---------------")
    test9_5()
  }
}
