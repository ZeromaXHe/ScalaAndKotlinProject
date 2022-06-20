package com.zerox.chapter26

/**
 * @author zhuxi
 * @since 2022/6/20 10:45
 * @note
 * 26.3 提取 0 或 1 个变量的模式
 * 不过，当模式只绑定一个变量时，处理逻辑是不同的。
 * Scala 并没有单个元素的元组。为了只返回单个模式元素，unapply 方法只是简单地将元素本身放在 Some 里。
 */
object Twice {
  def apply(s: String): String = s + s

  def unapply(s: String): Option[String] = {
    val length = s.length / 2
    val half = s.substring(0, length)
    if (half == s.substring(length)) Some(half) else None
  }
}
