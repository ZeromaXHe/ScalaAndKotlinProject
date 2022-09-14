package com.zerox.scala.from201to400

import com.zerox.scala.aider.{BinaryIndexedTree, SegmentTree}

/**
 * @author zhuxi
 * @since 2022/9/14 16:25
 * @note
 * 307. 区域和检索 - 数组可修改 | 难度：中等 | 标签：设计、树状数组、线段树、数组
 * 给你一个数组 nums ，请你完成两类查询。
 *
 * 其中一类查询要求 更新 数组 nums 下标对应的值
 * 另一类查询要求返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 ，其中 left <= right
 * 实现 NumArray 类：
 *
 * NumArray(int[] nums) 用整数数组 nums 初始化对象
 * void update(int index, int val) 将 nums[index] 的值 更新 为 val
 * int sumRange(int left, int right) 返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 （即，nums[left] + nums[left + 1], ..., nums[right]）
 *
 * 示例 1：
 *
 * 输入：
 * ["NumArray", "sumRange", "update", "sumRange"]
 * [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
 * 输出：
 * [null, 9, null, 8]
 *
 * 解释：
 * NumArray numArray = new NumArray([1, 3, 5]);
 * numArray.sumRange(0, 2); // 返回 1 + 3 + 5 = 9
 * numArray.update(1, 2);   // nums = [1,2,5]
 * numArray.sumRange(0, 2); // 返回 1 + 2 + 5 = 8
 *
 * 提示：
 * 1 <= nums.length <= 3 * 104
 * -100 <= nums[i] <= 100
 * 0 <= index < nums.length
 * -100 <= val <= 100
 * 0 <= left <= right < nums.length
 * 调用 update 和 sumRange 方法次数不大于 3 * 104 
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/range-sum-query-mutable
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution307 {
  /**
   * 执行用时：1896 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：94.4 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：16 / 16
   *
   * @param _nums
   */
  class NumArray(_nums: Array[Int]) {
    private val tree = new SegmentTree(
      (_, v, c) => v * c,
      (_, s) => s,
      (lv, rv) => lv + rv,
      end = _nums.length - 1
    )
    for (i <- _nums.indices) {
      tree.update(i, i, _nums(i))
    }

    def update(index: Int, `val`: Int) {
      tree.update(index, index, `val`)
    }

    def sumRange(left: Int, right: Int): Int = {
      tree.query(left, right)
    }
  }

  /**
   * 执行用时：1520 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：90.5 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：16 / 16
   *
   * @param _nums
   */
  class NumArray_BIT(_nums: Array[Int]) {
    private val tree = new BinaryIndexedTree(_nums)

    def update(index: Int, `val`: Int) {
      tree.add(index + 1, `val` - _nums(index))
      _nums(index) = `val`
    }

    def sumRange(left: Int, right: Int): Int = {
      tree.query(right + 1) - tree.query(left)
    }
  }

  /**
   * Your NumArray object will be instantiated and called as such:
   * var obj = new NumArray(nums)
   * obj.update(index,`val`)
   * var param_2 = obj.sumRange(left,right)
   */
}
