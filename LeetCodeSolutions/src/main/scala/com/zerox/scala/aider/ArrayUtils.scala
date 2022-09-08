package com.zerox.scala.aider

/**
 * @author zhuxi
 * @since 2022/9/8 10:17
 * @note
 */
object ArrayUtils {
  def reverse(arr: Array[Int], from: Int, to: Int): Unit = {
    for (i <- from until from + (to - from) / 2) {
      val temp = arr(i)
      arr(i) = arr(to - 1 - i + from)
      arr(to - 1 - i + from) = temp
    }
  }
}
