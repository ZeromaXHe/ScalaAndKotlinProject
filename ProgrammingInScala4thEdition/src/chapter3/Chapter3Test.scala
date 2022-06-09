package chapter3

import java.io.File
import scala.collection.immutable.HashSet
import scala.collection.mutable
import scala.io.Source

/**
 * @Author: zhuxi
 * @Time: 2022/6/9 11:33
 * @Description: 第3章 Scala 入门（续）
 * @ModifiedBy: zhuxi
 */
object Chapter3Test {

  /**
   * 第7步 用类型参数化数组
   */
  def test3_1(): Unit = {
    // 注意：虽然这里示例 3.1 展示了重要的概念，这并不是 Scala 创建并初始化数组的推荐做法。
    // 更好的方式是类似 val numNames = Array("zero", "one", "two")
    // 实际上是调用了一个名为 apply 的工厂方法，这个方法创建并返回了新的数组。
    // 这个apply方法接收一个变长的参数列表，该方法定义在 Array 的伴生对象（companion object）中。
    val greetStrings = new Array[String](3)
    // Scala 的数组的访问方式是将下标放在圆括号里，而不是像 Java 那样用方括号。
    // 当我们尝试对通过圆括号应用了一个或多个参数的变量进行赋值时，编译器会将代码转换成对 update 方法的调用，
    // 这个 update 方法接收两个参数：用圆括号括起来的值，以及等号右边的对象。
    // 即 greetStrings(0) = "Hello" 会被转换为： greetStrings.update(0, "Hello")
    greetStrings(0) = "Hello"
    greetStrings(1) = ", "
    greetStrings(2) = "world!\n"
    for (i <- 0 to 2) {
      // 本例展示的一个重要理念是为什么 Scala 用圆括号（而不用方括号）来访问数组。跟Java相比Scala的特例更少。
      // 数组不过是类的实例，这一点跟其他Scala实例没有本质区别。
      // 当你用一组圆括号将一个或多个值包起来，并将其应用（apply）到某个对象时，Scala 会将这段代码转换成
      // 对这个对象的一个名为 apply 的方法的调用。 所以 greetStrings(i) 会被转换成 greetStrings.apply(i)。
      print(greetStrings(i))
    }
  }

  /**
   * 第8步 使用列表
   */
  def test3_2(): Unit = {
    val oneTwo = List(1, 2)
    val threeFour = List(3, 4)
    val oneTwoThreeFour = oneTwo ::: threeFour
    println(oneTwo + " and " + threeFour + " were not mutated.")
    println("Thus, " + oneTwoThreeFour + " is a new list.")

    // 在表达式 “1 :: twoThree” 中，:: 是它右操作元（right operand，即twoThree这个列表）的方法。
    // 你可能会觉得 :: 方法的结合性（associativity）有些奇怪，实际上其背后的规则很简单：
    // 如果一个方法被用在操作符表示法（operator notation）当中时，
    // 比如a * b，方法调用默认都发生在左操作元（left operand），除非方法名以冒号（:）结尾。
    // 如果方法名的最后一个字符是冒号，该方法的调用会发生在它的右操作元上。
    // 因此，在1 :: twoThree中，:: 方法调用发生在 twoThree 上，传入的参数是 1，就像这样：twoThree.::(1)。
    // 关于操作符结合性的更多细节将在5.9节详细介绍。
    val twoThree = List(2, 3)
    val oneTwoThree = 1 :: twoThree
    println(oneTwoThree)
    // 表示空列表的快捷方式是Nil，初始化一个新的列表的另一种方式是用 :: 将元素串接起来，并将Nil作为最后一个元素。
    val oneTwoThree2 = 1 :: 2 :: 3 :: Nil
    println(oneTwoThree2)

    val thrill = "Will" :: "fill" :: "until" :: Nil
    println("thrill(2): " + thrill(2))
    println("thrill.count(s => s.length == 4): " + thrill.count(s => s.length == 4))
    println("thrill.drop(2): " + thrill.drop(2))
    println("thrill.dropRight(2): " + thrill.dropRight(2))
    println("""thrill.exists(s => s == "until"): """ + thrill.exists(s => s == "until"))
    println("thrill.filter(s => s.length == 4): " + thrill.filter(s => s.length == 4))
    println("thrill.forall(s => s.endsWith(\"l\")): " + thrill.forall(s => s.endsWith("l")))
    println("thrill.foreach(s => println(s)): " + thrill.foreach(s => println(s)))
    println("thrill.foreach(println): " + thrill.foreach(println))
    println("thrill.head: " + thrill.head)
    println("thrill.init: " + thrill.init)
    println("thrill.isEmpty: " + thrill.isEmpty)
    println("thrill.last: " + thrill.last)
    println("thrill.tail: " + thrill.tail)
    println("thrill.length: " + thrill.length)
    println("thrill.map(s => s + \"y\"): " + thrill.map(s => s + "y"))
    println("thrill.mkString(\", \"): " + thrill.mkString(", "))
    println("thrill.filterNot(s => s.length == 4): " + thrill.filterNot(s => s.length == 4))
    println("thrill.reverse: " + thrill.reverse)
    println("thrill.sort((s, t) => s.charAt(0).toLower < t.charAt(0).toLower): " +
      thrill.sortWith((s, t) => s.charAt(0).toLower < t.charAt(0).toLower))
  }

  /**
   * 第9步 使用元组
   */
  def test3_3(): Unit = {
    val pair = (99, "Luftballons")
    println(pair._1)
    println(pair._2)
  }

  /**
   * 第10步 使用集和映射
   */
  def test3_4(): Unit = {
    var jetSet = Set("Boeing", "Airbus")
    jetSet += "Lear"
    println(jetSet.contains("Cessna"))

    // 如果你想要的是一个可变集，需要做一次引入（import）
    val movieSet = mutable.Set("Hitch", "Poltergeist")
    movieSet += "Shrek"
    println(movieSet)

    // 尽管由可变和不可变 Set 的工厂方法生产出来的默认集的实现对于大多数情况来说都够用了，偶尔可能也需要一类特定的集。
    // 幸运的是，语法上面并没有大的不同。只需要简单地引入你需要的类，然后使用其伴生对象上的工厂方法即可。
    // 例如，如果需要一个不可变的 HashaSet，可以：
    val hashSet = HashSet("Tomatoes", "Chilies")
    println(hashSet + "Coriander")

    val treasureMap = mutable.Map[Int, String]()
    treasureMap += (1 -> "Go to island.")
    treasureMap += (2 -> "Find big X on ground.")
    treasureMap += (3 -> "Dig.")
    println(treasureMap(2))

    // 如果你更倾向于使用不可变的映射，则不需要任何引入，因为默认的映射就是不可变的
    val romanNumeral = Map(1 -> "I", 2 -> "II", 3 -> "III", 4 -> "IV", 5 -> "V")
    println(romanNumeral(4))
  }

  /**
   * 第12步 从文件读取文本行
   */
  def test3_6(args: Array[String]): Unit = {
    def widthOfLength(s: String) = s.length.toString.length

    if (args.length > 0) {
      val file = new File(args(0));
      println(file.getAbsolutePath)

      val lines = Source.fromFile(args(0)).getLines().toList
      val longestLine = lines.reduceLeft((a, b) => if (a.length > b.length) a else b)
      val maxWidth = widthOfLength(longestLine)
      for (line <- lines) {
        val numSpaces = maxWidth - widthOfLength(line)
        val padding = " " * numSpaces
        println(padding + line.length + " | " + line)
      }
    }
    else
      Console.err.println("Please enter filename")
  }

  def main(args: Array[String]): Unit = {
    test3_1()
    println("--------------")
    test3_2()
    println("--------------")
    test3_3()
    println("--------------")
    test3_4()
    println("--------------")
    test3_6(Array("ProgrammingInScala4thEdition/src/chapter3/Chapter3Test.scala"))
  }
}
