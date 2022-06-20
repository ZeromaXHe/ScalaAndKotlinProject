package com.zerox.chapter26

/**
 * @author zhuxi
 * @since 2022/6/20 11:15
 * @note
 * 26.4 提取可变长度参数的模式
 */
object ExpandedEmail {
  def unapplySeq(email: String): Option[(String, Seq[String])] = {
    val parts = email split "@"
    if (parts.length == 2)
      Some(parts(0), parts(1).split("\\.").reverse.toSeq)
    else
      None
  }
}
