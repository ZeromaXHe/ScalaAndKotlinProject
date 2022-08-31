package com.zerox.scala.from1201to1400

/**
 * @author zhuxi
 * @since 2022/8/31 10:28
 * @note
 * 1354. 多次求和构造目标数组 | 难度：困难 | 标签：数组、堆（优先队列）
 * 给你一个整数数组 target 。一开始，你有一个数组 A ，它的所有元素均为 1 ，你可以执行以下操作：
 *
 * 令 x 为你数组里所有元素的和
 * 选择满足 0 <= i < target.size 的任意下标 i ，并让 A 数组里下标为 i 处的值为 x 。
 * 你可以重复该过程任意次
 * 如果能从 A 开始构造出目标数组 target ，请你返回 True ，否则返回 False 。
 *
 * 示例 1：
 * 输入：target = [9,3,5]
 * 输出：true
 * 解释：从 [1, 1, 1] 开始
 * [1, 1, 1], 和为 3 ，选择下标 1
 * [1, 3, 1], 和为 5， 选择下标 2
 * [1, 3, 5], 和为 9， 选择下标 0
 * [9, 3, 5] 完成
 *
 * 示例 2：
 * 输入：target = [1,1,1,2]
 * 输出：false
 * 解释：不可能从 [1,1,1,1] 出发构造目标数组。
 *
 * 示例 3：
 * 输入：target = [8,5]
 * 输出：true
 *
 * 提示：
 * N == target.length
 * 1 <= target.length <= 5 * 10^4
 * 1 <= target[i] <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/construct-target-array-with-multiple-sums
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1354 {
  /**
   * 执行用时：712 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：64.5 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：71 / 71
   *
   * @param target
   * @return
   */
  def isPossible(target: Array[Int]): Boolean = {
    // 只有一个数字时只能是 1
    if (target.length == 1) return target(0) == 1
    var sum = 0L
    // 大顶堆
    val heap = new scala.collection.mutable.PriorityQueue[Int]
    for (t <- target) {
      sum += t
      heap.enqueue(t)
    }
    while (sum > target.length) {
      val max = heap.dequeue()
      val otherSum = sum - max
      // 剩下的数字都是 1 的情况
      if (otherSum == target.length - 1) return target.length == 2 || max % (target.length - 1) == 1
      if (max <= otherSum) return false
      // 对于较大的 max，直接通过取余操作直接求得多次计算后的下一个元素。不然会超时
      val next = max % otherSum
      if (next <= 0) return false
      sum += next - max
      heap.enqueue(next.toInt)
    }
    true
  }
}
