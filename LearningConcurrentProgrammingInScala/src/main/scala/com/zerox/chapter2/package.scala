package com.zerox

/**
 * @author zhuxi
 * @since 2022/7/19 15:31
 * @note
 */
package object chapter2 {
  def log(msg: String): Unit = println(s"${Thread.currentThread.getName}: $msg")
}
