package com.zerox.chapter26

/**
 * @author zhuxi
 * @since 2022/6/20 11:09
 * @note
 * 26.4 提取可变长度参数的模式
 * 提取器如何支持前面例子中的变长参数匹配（vararg matching），也就是说模式可以带上可变数量的子模式的情况，依然是个问题。
 * 我们目前介绍过的 unapply 方法是不够的，因为它们每一个在成功的 case 都返回固定数量的子元素。
 * 为了解决可变长度的参数匹配问题，Scala 允许我们定义另一个不同的提取方法专门处理变长参数匹配。这个方法叫作 unapplySeq。
 */
object Domain {
  // 注入方法（可选）
  def apply(parts: String*): String = parts.reverse.mkString(".")

  // 提取方法（必选）
  def unapplySeq(whole: String): Option[Seq[String]] = Some(whole.split("\\.").reverse.toSeq)
}
