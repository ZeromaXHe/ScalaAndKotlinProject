package com.zerox.scala.aider

/**
 * @author zhuxi
 * @since 2022/9/5 10:16
 * @note
 * 位运算工具类
 */
object BitOpUtils {
  /**
   * 计算 n 的二进制表示中 1 的个数
   *
   * @param n
   * @return
   */
  def bit1Count(n: Int): Int = Integer.bitCount(n)

  /**
   * 获取 n 的二进制表示中最低位的 1
   *
   * @param n
   * @return n == 0 时为 0，其他时候为 n 的二进制表示中最低位的 1 对应 Int
   */
  def lowBit(n: Int): Int = n & (-n)

  /**
   * 返回去掉 n 的二进制表示中最低位的 1 的数字
   * @param n
   * @return 当 n == 0 时为 0，其他时候为 n 去掉二进制最低位的 1 的 Int
   */
  def removeLowBit(n : Int): Int = n & (n - 1)

}
