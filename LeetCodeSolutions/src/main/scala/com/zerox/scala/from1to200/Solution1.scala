package com.zerox.scala.from1to200

/**
 * @author zhuxi
 * @since 2022/6/14 11:54
 * @note
 * 1. 两数之和 | 难度：简单 | 标签：数组、哈希表
 * 给定一个整数数组 nums 和一个整数目标值 target，
 * 请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 *
 * 示例 1：
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 *
 * 示例 2：
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 *
 * 示例 3：
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 *
 * 提示：
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * 只会存在一个有效答案
 * 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1 {
  /**
   * 使用 map 的 += 方法：
   * 执行用时：520 ms, 在所有 Scala 提交中击败了 91.79% 的用户
   * 内存消耗：53.5 MB, 在所有 Scala 提交中击败了 39.75% 的用户
   * 通过测试用例：57 / 57
   *
   * 使用 map 的 + 方法很慢：
   * 执行用时：2336 ms, 在所有 Scala 提交中击败了 5.13% 的用户
   * 内存消耗：60.6 MB, 在所有 Scala 提交中击败了 5.13% 的用户
   * 通过测试用例：57 / 57
   *
   * 上面的都是在去除 @scala.annotation.tailrec 注解情况下提交的结果，加上后好像又慢一点。
   *
   * @param nums
   * @param target
   * @return
   */
  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
    @scala.annotation.tailrec
    def iterate(index: Int, map: scala.collection.mutable.Map[Int, Int]): Array[Int] = {
      map.get(nums(index)) match {
        case Some(x) => Array(x, index)
        // 注意 map 的 + 方法多了一步 clone，所以很慢，需要使用 += 方法。
        case None => iterate(index + 1, map += ((target - nums(index)) -> index))
      }
    }

    iterate(0, scala.collection.mutable.Map[Int, Int]())
  }
}
