package com.zerox.scala.from2201to2400

/**
 * @author zhuxi
 * @since 2022/9/1 10:16
 * @note
 * 2251. 花期内花的数目 | 难度：困难 | 标签：数组、哈希表、二分查找、有序集合、前缀和、排序
 * 给你一个下标从 0 开始的二维整数数组 flowers ，其中 flowers[i] = [starti, endi] 表示第 i 朵花的 花期 从 starti 到 endi （都 包含）。
 * 同时给你一个下标从 0 开始大小为 n 的整数数组 persons ，persons[i] 是第 i 个人来看花的时间。
 *
 * 请你返回一个大小为 n 的整数数组 answer ，其中 answer[i]是第 i 个人到达时在花期内花的 数目 。
 *
 * 示例 1：
 * 输入：flowers = [[1,6],[3,7],[9,12],[4,13]], persons = [2,3,7,11]
 * 输出：[1,2,2,2]
 * 解释：上图展示了每朵花的花期时间，和每个人的到达时间。
 * 对每个人，我们返回他们到达时在花期内花的数目。
 *
 * 示例 2：
 * 输入：flowers = [[1,10],[3,3]], persons = [3,3,2]
 * 输出：[2,2,1]
 * 解释：上图展示了每朵花的花期时间，和每个人的到达时间。
 * 对每个人，我们返回他们到达时在花期内花的数目。
 *
 * 提示：
 * 1 <= flowers.length <= 5 * 104
 * flowers[i].length == 2
 * 1 <= starti <= endi <= 109
 * 1 <= persons.length <= 5 * 104
 * 1 <= persons[i] <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-flowers-in-full-bloom
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution2251 {
  /**
   * 执行用时：1148 ms, 在所有 Scala 提交中击败了 50.00% 的用户
   * 内存消耗：92.2 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：52 / 52
   *
   * 参考了题解，还有一种时间序列差分数组的解法
   *
   * @param flowers
   * @param persons
   * @return
   */
  def fullBloomFlowers(flowers: Array[Array[Int]], persons: Array[Int]): Array[Int] = {
    val starts = flowers.map(_ (0)).sorted
    val ends = flowers.map(_ (1)).sorted
    // scala 的数组二分查找 API，并不能找到最左边的一个
    // （而且很好奇怎么分辨是否找到？只能通过判断类型是 Found 还是 InsertionPoint 吗？）
    // persons.map(p => starts.search(p + 1).insertionPoint - ends.search(p).insertionPoint)
    persons.map(p => binarySearch(starts, p + 1) - binarySearch(ends, p))
  }

  private def binarySearch(arr: Array[Int], i: Int): Int = {
    var l = 0
    var r = arr.length
    while (l < r) {
      val mid = l + (r - l) / 2
      if (arr(mid) >= i) r = mid else l = mid + 1
    }
    l
  }
}
