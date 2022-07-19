package com.zerox

/**
 * @author zhuxi
 * @since 2022/7/19 15:32
 * @note
 */
package object chapter3 {
  def log(msg: String): Unit = println(s"${Thread.currentThread.getName}: $msg")
}
