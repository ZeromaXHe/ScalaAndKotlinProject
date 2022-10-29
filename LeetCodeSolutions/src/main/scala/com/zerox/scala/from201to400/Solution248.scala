package com.zerox.scala.from201to400

/**
 * @author zhuxi
 * @since 2022/10/29 17:19
 * @note
 * 248. 中心对称数 III | 难度：困难 | 标签：递归、数组、字符串
 * 给定两个字符串 low 和 high 表示两个整数 low 和 high ，其中 low <= high ，返回 范围 [low, high] 内的 「中心对称数」总数  。
 *
 * 中心对称数 是一个数字在旋转了 180 度之后看起来依旧相同的数字（或者上下颠倒地看）。
 *
 * 示例 1:
 * 输入: low = "50", high = "100"
 * 输出: 3
 *
 * 示例 2:
 * 输入: low = "0", high = "0"
 * 输出: 1
 *
 * 提示:
 * 1 <= low.length, high.length <= 15
 * low 和 high 只包含数字
 * low <= high
 * low and high 不包含任何前导零，除了零本身。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/strobogrammatic-number-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution248 {
  def main(args: Array[String]): Unit = {
    println(strobogrammaticInRange("50", "100"))
  }

  /**
   * 执行用时：440 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：52.2 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：14 / 14
   *
   * @param low
   * @param high
   * @return
   */
  def strobogrammaticInRange(low: String, high: String): Int = {
    val l = low.toLong
    val h = high.toLong
    var count = if (0 >= l && 0 <= h) 1 else 0
    count += dfs(0, l, h, 100)
    count += dfs(0, l, h, 1000)
    count += dfs(1, l, h, 100)
    count += dfs(8, l, h, 100)
    count += dfs(11, l, h, 1000)
    count += dfs(69, l, h, 1000)
    count += dfs(88, l, h, 1000)
    count += dfs(96, l, h, 1000)
    count
  }

  private def dfs(core: Long, low: Long, high: Long, multi: Long): Int = {
    var count = if (core >= low && core <= high && core * 100 >= multi) 1 else 0
    if (multi > high) return count
    count += dfs(core * 10 + multi + 1, low, high, multi * 100)
    count += dfs(core * 10 + multi * 6 + 9, low, high, multi * 100)
    count += dfs(core * 10 + multi * 9 + 6, low, high, multi * 100)
    count += dfs(core * 10 + multi * 8 + 8, low, high, multi * 100)
    count += dfs(core * 10, low, high, multi * 100)
    count
  }
}
