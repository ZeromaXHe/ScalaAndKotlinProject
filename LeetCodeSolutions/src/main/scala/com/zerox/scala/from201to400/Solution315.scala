package com.zerox.scala.from201to400

import com.zerox.scala.aider.BinaryIndexedTree

/**
 * @author zhuxi
 * @since 2022/9/8 14:57
 * @note
 * 315. 计算右侧小于当前元素的个数 | 难度：困难 | 标签：树状数组、线段树、数组、二分查找、分治、有序集合、归并排序
 * 给你一个整数数组 nums ，按要求返回一个新数组 counts 。
 * 数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 *
 * 示例 1：
 * 输入：nums = [5,2,6,1]
 * 输出：[2,1,1,0]
 * 解释：
 * 5 的右侧有 2 个更小的元素 (2 和 1)
 * 2 的右侧仅有 1 个更小的元素 (1)
 * 6 的右侧有 1 个更小的元素 (1)
 * 1 的右侧有 0 个更小的元素
 *
 * 示例 2：
 * 输入：nums = [-1]
 * 输出：[0]
 *
 * 示例 3：
 * 输入：nums = [-1,-1]
 * 输出：[0,0]
 *
 * 提示：
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/count-of-smaller-numbers-after-self
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution315 {
  def main(args: Array[String]): Unit = {
    // [2,1,1,0]
    //println(countSmaller(Array(5, 2, 6, 1)))
    // [10,27,10,35,12,22,28,8,19,2,12,2,9,6,12,5,17,9,19,12,14,6,12,5,12,3,0,10,0,7,8,4,0,0,4,3,2,0,1,0]
    println(countSmaller(Array(26, 78, 27, 100, 33, 67, 90, 23, 66, 5, 38, 7, 35, 23, 52, 22, 83, 51, 98, 69, 81,
      32, 78, 28, 94, 13, 2, 97, 3, 76, 99, 51, 9, 21, 84, 66, 65, 36, 100, 41)))
  }

  /**
   * 执行用时：1460 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：76.1 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：66 / 66
   *
   * @param nums
   * @return
   */
  def countSmaller(nums: Array[Int]): List[Int] = {
    val map = nums.distinct.sorted.zipWithIndex.toMap
    val bit = new BinaryIndexedTree(map.size)
    (for (i <- nums.indices.reverse) yield {
      val rank = map(nums(i))
      val res = bit.query(rank)
      bit.add(rank + 1, 1)
      res
    }).reverse.toList
  }

  def countSmaller_timeout(nums: Array[Int]): List[Int] = {
    val sort = nums.zipWithIndex.sorted
    val treeSet = new java.util.TreeSet[Int]
    val res = new Array[Int](nums.length)
    var i = 0
    while (i < sort.length) {
      val (value, idx) = sort(i)
      treeSet.add(idx)
      val count = treeSet.tailSet(idx).size() - 1
      res(idx) = count
      i += 1
    }
    res.toList
  }
}
