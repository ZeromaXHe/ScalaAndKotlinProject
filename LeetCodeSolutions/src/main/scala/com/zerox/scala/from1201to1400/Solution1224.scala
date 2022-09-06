package com.zerox.scala.from1201to1400

import com.zerox.scala.aider.BidirectionalHashMap

/**
 * @author zhuxi
 * @since 2022/9/6 14:48
 * @note
 * 1224. 最大相等频率 | 难度：困难 | 标签：数组、哈希表
 * 给你一个正整数数组 nums，请你帮忙从该数组中找出能满足下面要求的 最长 前缀，并返回该前缀的长度：
 *
 * 从前缀中 恰好删除一个 元素后，剩下每个数字的出现次数都相同。
 * 如果删除这个元素后没有剩余元素存在，仍可认为每个数字都具有相同的出现次数（也就是 0 次）。
 *
 * 示例 1：
 * 输入：nums = [2,2,1,1,5,3,3,5]
 * 输出：7
 * 解释：对于长度为 7 的子数组 [2,2,1,1,5,3,3]，如果我们从中删去 nums[4] = 5，就可以得到 [2,2,1,1,3,3]，里面每个数字都出现了两次。
 *
 * 示例 2：
 * 输入：nums = [1,1,1,2,2,2,3,3,3,4,4,4,5]
 * 输出：13
 *
 * 提示：
 * 2 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-equal-frequency
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1224 {
  def main(args: Array[String]): Unit = {
    println(maxEqualFreq(Array(1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5))) // 13
    println(maxEqualFreq(Array(1, 2))) // 2
  }

  /**
   * 执行用时：1180 ms, 在所有 Scala 提交中击败了 50.00% 的用户
   * 内存消耗：68.1 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：45 / 45
   *
   * @param nums
   * @return
   */
  def maxEqualFreq(nums: Array[Int]): Int = {
    var max = 1
    val map = new BidirectionalHashMap[Int, Int]
    map.put(nums(0), 1)
    for (i <- 1 until nums.length) {
      map.put(nums(i), map.getValueOrElse(nums(i), 0) + 1)
      var satisfied = map.keySet.size == 1 || (map.valueSet.size == 1 && map.valueSet(1))
      if (!satisfied && map.valueSet.size == 2) {
        if (map.valueSet.contains(1) && map.getKeys(1).size == 1) satisfied = true
        else {
          val big = map.valueSet.max
          if (map.containsValue(big - 1) && map.getKeys(big).size == 1) satisfied = true
        }
      }
      if (satisfied) max = i + 1
    }
    max
  }
}
