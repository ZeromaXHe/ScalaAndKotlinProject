package com.zerox.chapter26

/**
 * @author zhuxi
 * @since 2022/6/20 10:48
 * @note
 * 26.3 提取 0 或 1 个变量的模式
 * 也有可能某个提取器模式并不绑定任何变量，这时对应的 unapply 方法返回布尔值（true 表示成功，false 表示失败）。
 */
object UpperCase {
  def unapply(s: String): Boolean = s.toUpperCase == s
}
