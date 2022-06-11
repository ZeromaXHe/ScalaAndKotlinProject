package com.zerox.chapter5

import scala.language.postfixOps

/**
 * @Author: zhuxi
 * @Time: 2022/6/9 14:21
 * @Description: 第5章 基础类型和操作
 * @ModifiedBy: zhuxi
 */
object Chapter5Test {

  /**
   * 5.2 字面量
   */
  def test5_2(): Unit = {
    // Scala 并不支持八进制字面量和以0开头的整数字面量，比如031，将无法编译通过。
    // (例如 val test = 031 会提示 Octal literals syntax has been disabled since Scala 2.11)
    //
    // 注意，Scala 的 shell 总是以十进制打印整数值，不论用哪种形式来初始化。
    // 因此解释器把用字面量 0x00FF 初始化的变量 hex2 显示为十进制的 255
    // （当然，不必盲目相信我们说的，感受Scala的好方法是一边读一边在解释器中尝试这些语句）。
    val hex = 0x5
    println(hex)
    val hex2 = 0x00FF
    println(hex2)
    val magic = 0xcafebabe
    println(magic)

    val dec1 = 31
    println(dec1)
    val dec2 = 255
    println(dec2)
    val dec3 = 20
    println(dec3)

    // 如果整数字面量以 L 或 l 结尾，那么它就是 Long 类型的，否则就是 Int 类型的。
    val prog = 0XCAFEBABEL
    println(prog)
    val tower = 35L
    println(tower)
    val of = 31l
    println(of)

    // 如果一个 Int 类型的字面量被赋值给一个类型为 Short 或 Byte 的变量，该字面量会被当作 Short 或 Byte 类型，
    // 只要这个字面量的值在对应类型的合法取值区间即可。
    val little: Short = 367
    println(little)
    val littler: Byte = 38
    println(littler)

    // 浮点数字面量由十进制的数字、可选的小数点（decimal point），以及后续一个可选的 E 或 e 打头的指数（exponent）组成。
    val big = 1.2345
    println(big)
    val bigger = 1.2345e1
    println(bigger)
    val biggerStill = 123E45
    println(biggerStill)

    // 如果浮点数字面量以 F 或 f 结尾，那它就是 Float 类型的；否则它就是 Double 类型的。
    // Double 类型的浮点数字面量也可以以 D 或 d 结尾，但这是可选的。
    val littleF = 1.2345F
    println(littleF)
    val littleBigger = 3e5f
    println(littleBigger)

    // 字符字面量（character literal）由一对单引号和中间的任意 Unicode 字符组成
    val a = 'A'
    println(a)
    val d = '\u0041'
    println(d)
    val f = '\u0044'
    println(f)

    // 事实上，这样的 Unicode 字符可以出现在 Scala 程序的任何位置。(貌似只能在解释器这样写？)
    //    val B\u0041\u0044 = 1

    // 最后，还有一些字符字面量是由特殊的转义序列来表示的
    val backslash = '\\'
    println(backslash)

    val hello = "hello"
    println(hello)
    val escapes = "\\\"\'"
    println(escapes)
    // Scala支持一种特殊的语法来表示原生字符串（raw string）。
    // 可以用三个双引号（"""）开始并以三个双引号（"""）结束来表示原生字符串。
    // 原生字符串内部可以包含任何字符，包括换行、单双引号和其他特殊字符。当然，连续三个双引号的情况除外。
    // 为了防止前面的空格被包含在了字符串里,在每一行开始加一个管道符（|），然后对整个字符串调用 stripMargin
    println(
      """|Welcome to Ultamix 3000.
         |Type "Help" for help.""".stripMargin)

    // 符号字面量（symbol literal）的写法是 'ident，其中 ident 可以是任何由字母和数字组成的标识符。
    // 这样的字面量会被映射成 scala.Symbol 这个预定义类的实例。
    // 确切地说，字面量 'cymbal 会被编译器展开成一个工厂方法的调用：Symbol("cymbal")。
    // 符号字面量通常用在那些在动态类型语言中用来当作标识符的场合。
    //
    // 符号会被内部化。如果同样的符号字面量出现两次，这两次引用都会指向同一个Symbol对象。
    def updateRecordByName(r: Symbol, value: Any) = {
      // 具体的代码
    }

    updateRecordByName('favoriteAlbum, "OK Computer")
    val s = 'aSymbol
    println(s)
    val nm = s.name
    println(nm)
  }

  /**
   * 5.3 字符串插值
   */
  def test5_3(): Unit = {
    val name = "reader"
    println(s"Hello, $name")
    println(s"The answer is ${6 * 7}.")
    // raw 字符串插值器的行为跟 s 类似，不过它并不识别字符转义序列
    println(raw"No\\\\escape!")
    // f 字符串插值器允许你给内嵌的表达式加上 printf 风格的指令。
    // 需要将指令放在表达式之后，以百分号（%）开始，使用 java.util.Formatter 中给出的语法。
    println(f"${math.Pi}%.5f")
    // 如果不对内嵌表达式给出任何格式化指令，f 字符串插值器将默认使用 %s，其含义是用 toString 的值来替换，就像 s 字符串插值器那样。
    val pi = "Pi"
    println(f"$pi is approximately ${math.Pi}%.8f.")
  }

  /**
   * 5.4 操作符即方法
   */
  def test5_4(): Unit = {
    // Scala 将调用 1.+(2)
    val sum = 1 + 2
    println(sum)
    val sumMore = 1.+(2)
    println(sumMore)

    // Scala 将调用 1.+(2L)
    val longSum = 1 + 2L
    println(longSum)

    val s = "Hello, world!"
    // Scala 将调用 s.indexOf('o')
    println(s indexOf 'o')
    // Scala 将调用 s.indexOf('o', 5)
    println(s indexOf('o', 5))

    // 跟中缀操作符表示法（操作符接收两个操作元，一个在左一个在右）不同，前缀和后缀操作符是一元的（unary）：它们只接收一个操作元。
    // 在前缀表示法中，操作元位于操作符的右侧。 前缀操作符的例子有-2.0、!found 和 ~0xFF 等。
    // 跟中缀操作符类似，这些前缀操作符也是调用方法的一种简写。不同的是，方法名称是 “unary_” 加上操作符。
    // 唯一能被用作前缀操作符的是 +、-、! 和 ~。
    // 可以像正常的方法调用那样调用 p.unary_*，但如果你尝试用 *p 这样的方式来调用，Scala会当作 *.p 来解析
    //
    // Scala 将调用 (2.0).unary_-
    println(-2.0)
    println(2.0.unary_-)

    // 后缀操作符是那些不接收参数并且在调用时没有用英文句点圆括号的方法。在 Scala 中，可以在方法调用时省去空的圆括号。
    // 从约定俗成的角度讲，如果方法有副作用的时候保留空的圆括号，比如 println()；而在方法没有副作用时则可以省掉这组圆括号
    val s2 = "Hello, world!"
    // 需要 import scala.language.postfixOps，否则这里会告警
    println(s2 toLowerCase)
  }

  /**
   * 5.5 算术操作
   */
  def test5_5(): Unit = {
    // 你从浮点数的 % 得到的余数跟 IEEE 754 标准定义的不同。
    // IEEE 754 的余数在计算时用的是四舍五入，而不是截断（truncating），因此跟整数的余数操作很不一样。
    // 如果你确实需要 IEEE 754 的余数，可以调用 scala.math 的 IEEEremainder
    println(math.IEEEremainder(11.0, 4.0))
  }

  /**
   * 5.8 对象相等性
   */
  def test5_8(): Unit = {
    println(1 == 2)
    println(1 != 2)
    println(2 == 2)

    println(List(1, 2, 3) == List(1, 2, 3))
    println(List(1, 2, 3) == List(4, 5, 6))

    println(1 == 1.0)
    println(List(1, 2, 3) == "hello")

    println(List(1, 2, 3) == null)
    println(null == List(1, 2, 3))
    // 如你所见，== 的实现很用心，大部分场合都能返回给你需要的相等性比较的结果。
    // 这背后的规则很简单：首先检查左侧是否为 null， 如果不为 null，调用 equals 方法。
    // 由于 equals 是个方法，你得到的确切比较逻辑取决于左侧参数的类型。由于有自动的 null 检查，你不必亲自做这个检查。
    println(("he" + "llo") == "hello")
    // 在 Java 中，可以用 == 来比较基本类型和引用类型。对基本类型而言，Java 的 == 比较的是值的相等性，就跟 Scala 一样。
    // 但是对于引用类型，Java 的 == 比较的是引用相等性（reference equality），意思是两个变量指向 JVM 的堆上的同一个对象。
    // Scala 也提供了用于比较引用相等性的机制，即名为 eq 的方法。不过，eq 和跟它对应的 ne 只对那些直接映射到 Java 对象的对象有效。
  }

  /**
   * 5.11 结语
   */
  def test5_11(): Unit = {
    println(0 max 5)
    println(0 min 5)
    println(-2.7 abs)
    println(-2.7 round)
    println(1.5 isInfinity)
    println((1.0 / 0) isInfinity)
    println(4 to 6)
    println("bob" capitalize)
    println("robert" drop 2)
  }

  def main(args: Array[String]): Unit = {
    test5_2()
    println("--------------")
    test5_3()
    println("--------------")
    test5_4()
    println("--------------")
    test5_5()
    println("--------------")
    test5_8()
    println("--------------")
    test5_11()
  }
}
