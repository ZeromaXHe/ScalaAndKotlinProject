package com.zerox.offerII

/**
 * @author ZeromaXHe
 * @since 2022/7/16 13:44
 * @note
 * 剑指 Offer II 041. 滑动窗口的平均值 | 难度：简单 | 标签：设计、队列、数组、数据流
 * 给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算滑动窗口里所有数字的平均值。
 *
 * 实现 MovingAverage 类：
 *
 * MovingAverage(int size) 用窗口大小 size 初始化对象。
 * double next(int val) 成员函数 next 每次调用的时候都会往滑动窗口增加一个整数，请计算并返回数据流中最后 size 个值的移动平均值，即滑动窗口里所有数字的平均值。
 *
 * 示例：
 *
 * 输入：
 * inputs = ["MovingAverage", "next", "next", "next", "next"]
 * inputs = [[3], [1], [10], [3], [5]]
 * 输出：
 * [null, 1.0, 5.5, 4.66667, 6.0]
 *
 * 解释：
 * MovingAverage movingAverage = new MovingAverage(3);
 * movingAverage.next(1); // 返回 1.0 = 1 / 1
 * movingAverage.next(10); // 返回 5.5 = (1 + 10) / 2
 * movingAverage.next(3); // 返回 4.66667 = (1 + 10 + 3) / 3
 * movingAverage.next(5); // 返回 6.0 = (10 + 3 + 5) / 3
 *
 * 提示：
 * 1 <= size <= 1000
 * -105 <= val <= 105
 * 最多调用 next 方法 104 次
 *
 * 注意：本题与主站 346 题相同： https://leetcode-cn.com/problems/moving-average-from-data-stream/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/qIsx9U
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionOfferII_41 {
  /**
   * 执行用时：820 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：61.2 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：11 / 11
   *
   * @param _size
   */
  class MovingAverage(_size: Int) {

    /** Initialize your data structure here. */
    private val queue = new scala.collection.mutable.Queue[Int]
    private var sum = 0

    def next(`val`: Int): Double = {
      if (queue.size == _size) {
        sum -= queue.dequeue()
      }
      queue.enqueue(`val`)
      sum += `val`
      sum.toDouble / queue.size
    }

  }

  /**
   * Your MovingAverage object will be instantiated and called as such:
   * var obj = new MovingAverage(size)
   * var param_1 = obj.next(`val`)
   */
}
