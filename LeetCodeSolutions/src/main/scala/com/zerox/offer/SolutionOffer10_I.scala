package com.zerox.offer

/**
 * @author zhuxi
 * @since 2022/7/15 16:49
 * @note
 * 剑指 Offer 10- I. 斐波那契数列 | 难度：简单 | 标签：记忆化搜索、数学、动态规划
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
 *
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 * 输入：n = 2
 * 输出：1
 *
 * 示例 2：
 * 输入：n = 5
 * 输出：5
 *
 * 提示：
 * 0 <= n <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/fei-bo-na-qi-shu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionOffer10_I {
  val MOD: Int = (1e9 + 7).toInt

  /**
   * 执行用时：428 ms, 在所有 Scala 提交中击败了 26.67% 的用户
   * 内存消耗：51.5 MB, 在所有 Scala 提交中击败了 6.67% 的用户
   * 通过测试用例：51 / 51
   *
   * @param n
   * @return
   */
  def fib_iterate(n: Int): Int = {
    if (n == 0) return 0
    Stream.iterate((0, 1))(t => (t._2, (t._1 + t._2) % MOD))(n - 1)._2
  }

  /**
   * 执行用时：476 ms, 在所有 Scala 提交中击败了 6.67% 的用户
   * 内存消耗：51.8 MB, 在所有 Scala 提交中击败了 6.67% 的用户
   * 通过测试用例：51 / 51
   *
   * @param n
   * @return
   */
  def fib_scanLeft(n: Int): Int = {
    val MOD = (1e9 + 7).toInt
    lazy val fibStream: Stream[Int] = 0 #:: fibStream.scanLeft(1)((i1, i2) => (i1 + i2) % MOD)
    fibStream(n)
  }

  /**
   * 执行用时：432 ms, 在所有 Scala 提交中击败了 20.00% 的用户
   * 内存消耗：50.7 MB, 在所有 Scala 提交中击败了 60.00% 的用户
   * 通过测试用例：51 / 51
   *
   * @param n
   * @return
   */
  def fib_while(n: Int): Int = {
    n match {
      case 0 => 0
      case 1 => 1
      case _ =>
        var count = 1
        val MOD = (1e9 + 7).toInt
        var t = (0, 1)
        while (count < n) {
          t = (t._2, (t._1 + t._2) % MOD)
          count += 1
        }
        t._2
    }
  }

  /**
   * 执行用时：480 ms, 在所有 Scala 提交中击败了 6.67% 的用户
   * 内存消耗：51.1 MB, 在所有 Scala 提交中击败了 6.67% 的用户
   * 通过测试用例：51 / 51
   *
   * 子方法提到 object 中就快很多，估计用不到闭包的方法还是提出去比较好？
   * 执行用时：408 ms, 在所有 Scala 提交中击败了 60.00% 的用户
   * 内存消耗：50.9 MB, 在所有 Scala 提交中击败了 13.33% 的用户
   * 通过测试用例：51 / 51
   *
   * @param n
   * @return
   */
  def fib(n: Int): Int = {
    if (n == 0) return 0

    @scala.annotation.tailrec
    def fib(n: Int, a: Int, b: Int): Int = {
      if (n <= 1) return b
      fib(n - 1, b, (a + b) % MOD)
    }

    fib(n, 0, 1)
  }
}
