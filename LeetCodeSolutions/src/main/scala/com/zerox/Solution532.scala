package com.zerox

/**
 * @author zhuxi
 * @since 2022/6/16 9:51
 * @note
 * 532. 数组中的 k-diff 数对 | 难度：中等 | 标签：数组、哈希表、双指针、二分查找、排序
 * 给定一个整数数组和一个整数 k，你需要在数组里找到 不同的 k-diff 数对，并返回不同的 k-diff 数对 的数目。
 *
 * 这里将 k-diff 数对定义为一个整数对 (nums[i], nums[j])，并满足下述全部条件：
 *
 * 0 <= i < j < nums.length
 * |nums[i] - nums[j]| == k
 * 注意，|val| 表示 val 的绝对值。
 *
 * 示例 1：
 * 输入：nums = [3, 1, 4, 1, 5], k = 2
 * 输出：2
 * 解释：数组中有两个 2-diff 数对, (1, 3) 和 (3, 5)。
 * 尽管数组中有两个1，但我们只应返回不同的数对的数量。
 *
 * 示例 2：
 * 输入：nums = [1, 2, 3, 4, 5], k = 1
 * 输出：4
 * 解释：数组中有四个 1-diff 数对, (1, 2), (2, 3), (3, 4) 和 (4, 5)。
 *
 * 示例 3：
 * 输入：nums = [1, 3, 1, 5, 4], k = 0
 * 输出：1
 * 解释：数组中只有一个 0-diff 数对，(1, 1)。
 *
 * 提示：
 * 1 <= nums.length <= 104
 * -107 <= nums[i] <= 107
 * 0 <= k <= 107
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/k-diff-pairs-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution532 {
  def main(args: Array[String]): Unit = {
    println(findPairs(Array(3, 1, 4, 1, 5), 2) == 2)
    println(findPairs(Array(1, 1, 1, 1, 1), 0) == 1)
    println(findPairs(Array(1, 3, 1, 5, 4), 0) == 1)
  }

  /**
   * 执行用时：588 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗： 53.8 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：60 / 60
   *
   * @param nums
   * @param k
   * @return
   */
  def findPairs(nums: Array[Int], k: Int): Int = {
    if (k == 0) {
      // Scala 元素计数
      nums.groupBy(identity).mapValues(_.length).count(x => x._2 > 1)
    } else {
      val set = nums.toSet
      // 必须先 set.toArray，直接使用 set 的话，后面 map 里面的 1 和 0 貌似会去重, 导致结果只能是 1 或 0
      set.toArray.map(num => if (set(num + k)) 1 else 0).sum
    }
  }
}
