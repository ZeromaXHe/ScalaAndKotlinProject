package com.zerox

/**
 * @author zhuxi
 * @since 2022/7/4 9:43
 * @note
 * 1200. 最小绝对差 | 难度：简单 | 标签：数组、排序
 * 给你个整数数组 arr，其中每个元素都 不相同。
 *
 * 请你找到所有具有最小绝对差的元素对，并且按升序的顺序返回。
 *
 * 示例 1：
 * 输入：arr = [4,2,1,3]
 * 输出：[[1,2],[2,3],[3,4]]
 *
 * 示例 2：
 * 输入：arr = [1,3,6,10,15]
 * 输出：[[1,3]]
 *
 * 示例 3：
 * 输入：arr = [3,8,-10,23,19,-4,-14,27]
 * 输出：[[-14,-10],[19,23],[23,27]]
 *
 * 提示：
 * 2 <= arr.length <= 10^5
 * -10^6 <= arr[i] <= 10^6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-absolute-difference
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1200 {
  /**
   * 执行用时：912 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：71.3 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：37 / 37
   *
   * @param arr
   * @return
   */
  def minimumAbsDifference(arr: Array[Int]): List[List[Int]] = {
    val result = new scala.collection.mutable.ListBuffer[List[Int]]
    val sorted = arr.sorted
    var min = Int.MaxValue
    for (i <- 1 until sorted.length) {
      if (sorted(i) - sorted(i - 1) <= min) {
        if (sorted(i) - sorted(i - 1) < min) {
          result.clear()
          min = sorted(i) - sorted(i - 1)
        }
        result += List(sorted(i - 1), sorted(i))
      }
    }
    result.toList
  }
}
