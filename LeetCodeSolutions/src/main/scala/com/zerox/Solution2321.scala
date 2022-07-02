package com.zerox

/**
 * @author ZeromaXHe
 * @since 2022/7/2 22:10
 * @note
 * 2321. 拼接数组的最大分数 | 难度：困难 | 标签：数组、动态规划
 * 给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，长度都是 n 。
 *
 * 你可以选择两个整数 left 和 right ，其中 0 <= left <= right < n ，接着 交换 两个子数组 nums1[left...right] 和 nums2[left...right] 。
 *
 * 例如，设 nums1 = [1,2,3,4,5] 和 nums2 = [11,12,13,14,15] ，整数选择 left = 1 和 right = 2，那么 nums1 会变为 [1,12,13,4,5] 而 nums2 会变为 [11,2,3,14,15] 。
 * 你可以选择执行上述操作 一次 或不执行任何操作。
 *
 * 数组的 分数 取 sum(nums1) 和 sum(nums2) 中的最大值，其中 sum(arr) 是数组 arr 中所有元素之和。
 *
 * 返回 可能的最大分数 。
 *
 * 子数组 是数组中连续的一个元素序列。arr[left...right] 表示子数组包含 nums 中下标 left 和 right 之间的元素（含 下标 left 和 right 对应元素）。
 *
 * 示例 1：
 * 输入：nums1 = [60,60,60], nums2 = [10,90,10]
 * 输出：210
 * 解释：选择 left = 1 和 right = 1 ，得到 nums1 = [60,90,60] 和 nums2 = [10,60,10] 。
 * 分数为 max(sum(nums1), sum(nums2)) = max(210, 80) = 210 。
 *
 * 示例 2：
 * 输入：nums1 = [20,40,20,70,30], nums2 = [50,20,50,40,20]
 * 输出：220
 * 解释：选择 left = 3 和 right = 4 ，得到 nums1 = [20,40,20,40,20] 和 nums2 = [50,20,50,70,30] 。
 * 分数为 max(sum(nums1), sum(nums2)) = max(140, 220) = 220 。
 *
 * 示例 3：
 * 输入：nums1 = [7,11,13], nums2 = [1,1,1]
 * 输出：31
 * 解释：选择不交换任何子数组。
 * 分数为 max(sum(nums1), sum(nums2)) = max(31, 3) = 31 。
 *
 * 提示：
 * n == nums1.length == nums2.length
 * 1 <= n <= 105
 * 1 <= nums1[i], nums2[i] <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-score-of-spliced-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution2321 {
  /**
   * 执行用时：840 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：75.9 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：34 / 34
   *
   * 其实用差分数组的最大子数组和会比较简洁
   *
   * @param nums1
   * @param nums2
   * @return
   */
  def maximumsSplicedArray(nums1: Array[Int], nums2: Array[Int]): Int = {
    val n = nums1.length
    val noChange1 = new Array[Int](n)
    val noChange2 = new Array[Int](n)
    val changing1 = new Array[Int](n)
    val changing2 = new Array[Int](n)
    val changed1 = new Array[Int](n)
    val changed2 = new Array[Int](n)
    noChange1(0) = nums1(0)
    noChange2(0) = nums2(0)
    changing1(0) = nums2(0)
    changing2(0) = nums1(0)
    changed1(0) = nums1(0)
    changed2(0) = nums2(0)
    for (i <- 1 until n) {
      noChange1(i) = noChange1(i - 1) + nums1(i)
      noChange2(i) = noChange2(i - 1) + nums2(i)
      changing1(i) = math.max(changing1(i - 1), noChange1(i - 1)) + nums2(i)
      changing2(i) = math.max(changing2(i - 1), noChange2(i - 1)) + nums1(i)
      changed1(i) = math.max(changing1(i - 1), changed1(i - 1)) + nums1(i)
      changed2(i) = math.max(changing2(i - 1), changed2(i - 1)) + nums2(i)
    }
    math.max(
      math.max(
        math.max(changed1(n - 1), changed2(n - 1)),
        math.max(noChange1(n - 1), noChange2(n - 1))),
      math.max(changing1(n - 1), changing2(n - 1)))
  }
}
