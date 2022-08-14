package com.zerox.scala.interview

/**
 * @author zhuxi
 * @since 2022/6/30 15:39
 * @note
 * 面试题 16.11. 跳水板 | 难度：简单 | 标签：数组、数学
 * 你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。
 *
 * 返回的长度需要从小到大排列。
 *
 * 示例 1
 * 输入：
 * shorter = 1
 * longer = 2
 * k = 3
 * 输出： [3,4,5,6]
 * 解释：
 * 可以使用 3 次 shorter，得到结果 3；使用 2 次 shorter 和 1 次 longer，得到结果 4 。以此类推，得到最终结果。
 *
 * 提示：
 * 0 < shorter <= longer
 * 0 <= k <= 100000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/diving-board-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionInterview16_11 {
  /**
   * 执行用时：556 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：56.9 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：60 / 60
   *
   * @param shorter
   * @param longer
   * @param k
   * @return
   */
  def divingBoard(shorter: Int, longer: Int, k: Int): Array[Int] = {
    if (k == 0) Array.empty
    else if (shorter == longer) Array(shorter * k)
    else (for (i <- 0 to k) yield shorter * (k - i) + longer * i).toArray
  }
}
