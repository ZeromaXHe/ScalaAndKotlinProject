package chapter7

import java.io.{BufferedReader, File, FileNotFoundException, FileReader, IOException, InputStreamReader}
import scala.io.Source
import scala.util.control.Breaks.{break, breakable}

/**
 * @Author: zhuxi
 * @Time: 2022/6/9 16:18
 * @Description: 第7章 内建的控制结构
 * @ModifiedBy: zhuxi
 */
object Chapter7Test {

  /**
   * 7.3 for 表达式
   */
  def test7_3(): Unit = {
    val filesHere = (new File("./ProgrammingInScala4thEdition/src/chapter6")).listFiles
    for (file <- filesHere)
      println(file)
    for (i <- 1 to 4)
      println("Iteration[to] " + i)
    for (i <- 1 until 4)
      println("Iteration[until] " + i)
    // 在 Scala 中并不常见
    for (i <- 0 to filesHere.length - 1)
      println(filesHere(i))

    val filesHere2 = (new File(".")).listFiles
    for (
      file <- filesHere2
      if file.isFile
      if file.getName.endsWith(".iml")
    ) println(file)

    def fileLines(file: File) = Source.fromFile(file).getLines.toArray

    /**
     * 如果你添加多个<-子句，将得到嵌套的“循环”。如果你愿意，也可以使用花括号而不是圆括号来包括生成器和过滤器。
     * 这样做的一个好处是可以在需要时省去某些分号，因为 Scala 编译器在圆括号中并不会自动推断分号
     *
     * @param pattern
     */
    def grep(pattern: String) = for (
      file <- filesHere
      if file.getName.endsWith(".scala");
      line <- fileLines(file);
      trimmed = line.trim
      if trimmed.matches(pattern)
    ) println(s"$file:$trimmed")

    grep(".*gcd.*")

    /**
     * 虽然到目前为止所有示例都是对遍历到的值进行操作然后忘掉它们，也完全可以在每次迭代中生成一个可以被记住的值。
     * 具体做法是在 for 表达式的代码体之前加上关键字 yield。
     *
     * 要小心yield关键字的位置。for-yield表达式的语法如下：
     * for 子句 yield 代码体
     * yield 关键字必须出现在整个代码体之前。哪怕代码体是由花括号包起来的，也要将 yield 放在花括号之前，而不是在代码块最后一个表达式前面。
     *
     * @return
     */
    def scalaFiles = for {
      file <- filesHere
      if file.getName.endsWith(".scala")
    } yield file

    println(scalaFiles.mkString("Array(", ", ", ")"))

    val forLineLengths =
      for {
        file <- (new File("./ProgrammingInScala4thEdition/src/chapter7")).listFiles
        if file.getName.endsWith(".scala")
        line <- fileLines(file)
        trimmed = line.trim
        if trimmed.matches(".*for.*")
      } yield trimmed.length

    println(forLineLengths.mkString("Array(", ", ", ")"))
  }

  /**
   * 7.4 用 try 表达式实现异常处理
   */
  def test7_4(): Unit = {
    // 你会注意到一个 Scala 跟 Java 的区别，Scala 并不要求你捕获受检异常（checked exception）或在 throws 子句里声明。
    // 可以选择用 @throws 注解来声明一个 throws 子句，但这并不是必需的。
    var fi: FileReader = null
    try {
      val file = new FileReader("input.txt")
      fi = file
      // 使用文件
    } catch {
      // 处理找不到文件的情况
      case ex: FileNotFoundException => println(ex.getMessage)
      // 处理其他 I/O 错误
      case ex: IOException => println(ex.getMessage)
    } finally {
      // 确保关闭文件
      if (fi != null) fi.close()
    }

    // 如果有 finally 子句，该子句计算出来的值会被丢弃。finally 子句一般都用来执行清理工作，比如关闭文件。
    // 通常来说，它们不应该改变主代码体或 catch 子句中计算出来的值。
    // 如果你熟悉 Java，需要注意的是 Scala 的行为跟 Java 不同，仅仅是因为 Java 的 try-finally 并不返回某个值。
    // 跟 Java 一样，当 finally 子句包含一个显式的返回语句，或者抛出某个异常，
    // 那么这个返回值或异常将会“改写”（overrule）任何在之前的 try 代码块或某个 catch 子句中产生的值。
    def f(): Int = try return 1 finally return 2

    def g(): Int = try 1 finally 2

    // 调用 f() 将得到 2
    println(f())
    // 调用 g() 将得到 1
    println(g())
  }

  /**
   * 7.5 match 表达式
   */
  def test7_5(args: Array[String]): Unit = {
    val firstArg = if (args.length > 0) args(0) else ""
    firstArg match {
      case "salt" => println("pepper")
      case "chips" => println("salsa")
      case "eggs" => println("bacon")
      case _ => println("huh?")
    }
    // Scala 的 match 表达式跟 Java 的 switch 相比，有一些重要的区别。
    // 其中一个区别是任何常量、字符串等都可以用作样例，而不仅限于 Java 的 case 语句支持的整型、枚举和字符串常量。
    // 另一个区别是在每个可选项的最后并没有 break。在 Scala 中 break 是隐含的，并不会出现某个可选项执行完继续执行下一个可选项的情况。
    // 不过 Scala 的 match 表达式跟 Java 的 switch 相比最显著的不同，在于 match 表达式会返回值。
    // 在前一例中，match 表达式的每个可选项都打印出一个值。如果将打印语句换成交出某个值，相应的代码依然能工作。
    val friend = firstArg match {
      case "salt" => "pepper"
      case "chips" => "salsa"
      case "eggs" => "bacon"
      case _ => "huh?"
    }
    println(friend)
  }

  /**
   * 7.6 没有 break 和 continue 的日子
   */
  def test7_6(args: Array[String]): Unit = {
    // 示例 7.16 不使用 break 或 continue 的循环
    var i = 0
    var foundIt = false
    while (i < args.length && !foundIt) {
      if (!args(i).startsWith("-")) {
        if (args(i).endsWith(".scala"))
          foundIt = true

      }
      // 这里之前原书没有 if (!foundIt)，感觉有问题
      if (!foundIt) i = i + 1
    }

    println(i)

    // 如果你想去掉示例 7.16 中的 var，一种做法是将循环重写为递归的函数。
    // 比方说，可以定义一个 searchFrom 函数，接收一个整数作为输入，从那里开始向前检索，然后返回找到的入参下标。
    //
    // Scala 编译器实际上并不会对示例 7.17 中的代码生成递归的函数。
    // 由于所有的递归调用都发生在函数尾部（tail-call position），编译器会生成与 while 循环类似的代码。
    // 每一次递归都会被实现成跳回到函数开始的位置。
    def searchFrom(i: Int): Int = {
      if (i >= args.length) -1
      else if (args(i).startsWith("-")) searchFrom(i + 1)
      else if (args(i).endsWith(".scala")) i
      else searchFrom(i + 1)
    }

    println(searchFrom(0))

    // 如果经过这些讨论你仍觉得需要使用 break，Scala 标准类库也提供了帮助。
    // scala.util.control 包的 Break 类给出了一个 break 方法，可以被用来退出包含它的用 breakable 标记的代码块。
    // Break 类实现 break 的方式是抛出一个异常，然后由外围的对 breakable 方法的应用所捕获。
    // 因此，对break的调用并不需要跟对 breakable 的调用放在同一个方法内。
    val in = new BufferedReader(new InputStreamReader(System.in))
    breakable {
      while (true) {
        println("输入回车（空行）以继续")
        if (in.readLine() == "") break
      }
    }
  }

  /**
   * 7.7 变量作用域
   */
  def test7_7(): Unit = {
    // 如果你是 Java 程序员，会发现 Scala 的作用域规则几乎跟 Java 完全一样。
    // Java 和 Scala 的一个区别是 Scala 允许你在嵌套的作用域内定义同名的变量。
    val a = 1;
    {
      val a = 2;
      println(a)
    }
    println(a)
    // 这段脚本执行时，会先打印 2 然后打印 1，这是因为在花括号中定义的 a 是不同的变量，这个变量只在右花括号结束之前处于作用域内。
    // 需要注意 Scala 跟 Java 的一个区别是，Scala 不允许你在内嵌的作用域使用一个跟外部作用域内相同名称的变量。
    // 在Scala程序中，内嵌作用域中的变量会遮挡（shadow）外部作用域中相同名称的变量，因为外部作用域的同名变量在内嵌作用域内将不可见。
    // 在解释器中，可以随心地使用变量名。其他的先不谈，单这一点，让你能够在不小心定义错了某个变量之后改变主意。
    // 你之所以能这样做，是因为从概念上讲，解释器会对你录入的每一条语句创建一个新的作用域。
  }

  /**
   * 7.8 对指令式代码进行重构
   */
  def test7_8(): Unit = {
    // 示例7.19 用函数式编程的方式创建乘法表
    // 以序列形式返回一行
    def makeRowSeq(row: Int) =
      for (col <- 1 to 10) yield {
        val prod = (row * col).toString
        val padding = " " * (4 - prod.length)
        padding + prod
      }

    // 以字符串形式返回一行
    def makeRow(row: Int) = makeRowSeq(row).mkString

    // 以每行占一个文本行的字符串的形式返回表格
    def multiTable() = {
      // 行字符串的序列
      val tableSeq =
        for (row <- 1 to 10)
          yield makeRow(row)
      tableSeq.mkString("\n")
    }

    println(multiTable())
  }

  def main(args: Array[String]): Unit = {
    test7_3()
    println("----------------")
    test7_4()
    println("----------------")
    test7_5(Array("test"))
    println("----------------")
    test7_6(Array("-test", "test.scala"))
    println("----------------")
    test7_7()
    println("----------------")
    test7_8()
  }
}
