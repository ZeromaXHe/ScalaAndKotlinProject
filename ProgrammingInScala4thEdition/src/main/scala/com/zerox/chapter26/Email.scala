package com.zerox.chapter26

/**
 * @author zhuxi
 * @since 2022/6/20 10:38
 * @note
 * 26.2 提取器
 */
object Email extends ((String, String) => String) {
  // 注入方法（可选）
  def apply(user: String, domain: String) = user + "@" + domain

  // 提取方法（必选）
  def unapply(str: String): Option[(String, String)] = {
    val parts = str split "@"
    if (parts.length == 2) Some(parts(0), parts(1)) else None
  }
}
