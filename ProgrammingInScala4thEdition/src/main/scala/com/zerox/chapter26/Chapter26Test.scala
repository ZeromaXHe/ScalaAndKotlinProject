package com.zerox.chapter26

/**
 * @author zhuxi
 * @since 2022/6/20 10:36
 * @note
 * 第 26 章 提取器
 */
object Chapter26Test {

  /**
   * 26.2 提取器
   */
  def test26_2(): Unit = {
    println(Email.unapply("John@epfl.ch") equals Some("John", "epfl.ch"))
    println(Email.unapply("John Doe").isEmpty)
  }

  /**
   * 26.3 提取 0 或 1 个变量的模式
   */
  def test26_3(): Unit = {
    def userTwiceUpper(s: String) = s match {
      case Email(Twice(x@UpperCase()), domain) => "match: " + x + " in domain " + domain
      case _ => "no match"
    }

    println(userTwiceUpper("DIDI@hotmail.com"))
    println(userTwiceUpper("DIDO@hotmail.com"))
    println(userTwiceUpper("didi@hotmail.com"))
  }

  /**
   * 26.4 提取可变长度参数的模式
   */
  def test26_4(): Unit = {
    def isTomInDotCom(s: String): Boolean = s match {
      case Email("tom", Domain("com", _*)) => true
      case _ => false
    }

    println(isTomInDotCom("tom@sun.com"))
    println(isTomInDotCom("peter@sun.com"))
    println(isTomInDotCom("tom@acm.org"))

    val s = "tom@support.epfl.cn"
    val ExpandedEmail(name, topdom, subdoms@_*) = s
    println(name)
    println(topdom)
    println(subdoms)
  }

  /**
   * 26.7 正则表达式
   */
  def test26_7(): Unit = {
    import scala.util.matching.Regex
    val Decimal1 = new Regex("(-)?(\\d+)(\\.\\d*)?")
    println(Decimal1)
    val Decimal2 = new Regex("""(-)?(\d+)(\.\d*)?""")
    println(Decimal2)
    // 换句话说，只要在字符串后面追加一个 .r 就能得到一个正则表达式。
    // 这之所以可行，是因为 StringOps 类里有一个名为 r 的方法，将字符串转换成正则表达式。
    val Decimal3 = """(-)?(\d+)(\.\d*)?""".r
    println(Decimal3)

    val input = "for -1.0 to 99 by 3"
    for (s <- Decimal3 findAllIn input)
      println(s)
    println(Decimal3 findFirstIn input)
    println(Decimal3 findPrefixOf input)

    // 不仅如此，在 Scala 中每个正则表达式都定义了一个提取器。该提取器用来识别正则表达式中的组匹配的子字符串。
    val Decimal3(sign, integerpart, decimalpart) = "-1.23"
    println(sign)
    println(integerpart)
    println(decimalpart)
    // 如果某个组缺失了，对应的元素值就被设为 null
    val Decimal3(sign2, integerpart2, decimalpart2) = "1.0"
    println(sign2)
    println(integerpart2)
    println(decimalpart2)
    // 我们也可以在 for 表达式中混用提取器和正则表达式的查找。
    for (Decimal3(s, i, d) <- Decimal3 findAllIn input)
      println("sign: " + s + ", integer: " + i + ", decimal: " + d)
  }

  def main(args: Array[String]): Unit = {
    test26_2()
    println("---------------")
    test26_3()
    println("---------------")
    test26_4()
    println("---------------")
    test26_7()
  }
}
