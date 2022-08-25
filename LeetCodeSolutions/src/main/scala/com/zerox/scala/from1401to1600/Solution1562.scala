package com.zerox.scala.from1401to1600

/**
 * @author zhuxi
 * @since 2022/8/25 18:01
 * @note
 * 1562. 查找大小为 M 的最新分组 | 难度：中等 | 标签：数组、二分查找、模拟
 * 给你一个数组 arr ，该数组表示一个从 1 到 n 的数字排列。有一个长度为 n 的二进制字符串，该字符串上的所有位最初都设置为 0 。
 *
 * 在从 1 到 n 的每个步骤 i 中（假设二进制字符串和 arr 都是从 1 开始索引的情况下），二进制字符串上位于位置 arr[i] 的位将会设为 1 。
 *
 * 给你一个整数 m ，请你找出二进制字符串上存在长度为 m 的一组 1 的最后步骤。一组 1 是一个连续的、由 1 组成的子串，且左右两边不再有可以延伸的 1 。
 *
 * 返回存在长度 恰好 为 m 的 一组 1  的最后步骤。如果不存在这样的步骤，请返回 -1 。
 *
 * 示例 1：
 * 输入：arr = [3,5,1,2,4], m = 1
 * 输出：4
 * 解释：
 * 步骤 1："00100"，由 1 构成的组：["1"]
 * 步骤 2："00101"，由 1 构成的组：["1", "1"]
 * 步骤 3："10101"，由 1 构成的组：["1", "1", "1"]
 * 步骤 4："11101"，由 1 构成的组：["111", "1"]
 * 步骤 5："11111"，由 1 构成的组：["11111"]
 * 存在长度为 1 的一组 1 的最后步骤是步骤 4 。
 *
 * 示例 2：
 * 输入：arr = [3,1,5,4,2], m = 2
 * 输出：-1
 * 解释：
 * 步骤 1："00100"，由 1 构成的组：["1"]
 * 步骤 2："10100"，由 1 构成的组：["1", "1"]
 * 步骤 3："10101"，由 1 构成的组：["1", "1", "1"]
 * 步骤 4："10111"，由 1 构成的组：["1", "111"]
 * 步骤 5："11111"，由 1 构成的组：["11111"]
 * 不管是哪一步骤都无法形成长度为 2 的一组 1 。
 *
 * 示例 3：
 * 输入：arr = [1], m = 1
 * 输出：1
 *
 * 示例 4：
 * 输入：arr = [2,1], m = 2
 * 输出：2
 *
 * 提示：
 * n == arr.length
 * 1 <= n <= 10^5
 * 1 <= arr[i] <= n
 * arr 中的所有整数 互不相同
 * 1 <= m <= arr.length
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-latest-group-of-size-m
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1562 {
  def main(args: Array[String]): Unit = {
    println(findLatestStep_stack(Array(10, 6, 9, 4, 7, 8, 5, 2, 1, 3), 1)) // 8
  }

  /**
   * 执行用时：856 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：75.5 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：114 / 114
   *
   * 参考题解做的，这里没用到二分查找，还是挺妙的
   *
   * @param arr
   * @param m
   * @return
   */
  def findLatestStep(arr: Array[Int], m: Int): Int = {
    val n = arr.length
    val range = Array.fill(n + 1)(Array.fill(2)(-1))
    var count = 0
    var res = -1
    for (i <- arr.indices) {
      var l = arr(i)
      var r = arr(i)
      if (arr(i) > 1) {
        val lRange = range(l - 1)
        if (lRange(0) != -1) {
          l = lRange(0)
          if (lRange(1) - lRange(0) + 1 == m) count -= 1
        }
      }
      if (arr(i) < n) {
        val rRange = range(r + 1)
        if (rRange(0) != -1) {
          r = rRange(1)
          if (rRange(1) - rRange(0) + 1 == m) count -= 1
        }
      }
      if (r - l + 1 == m) count += 1
      if (count > 0) res = i + 1
      range(l)(0) = l
      range(r)(0) = l
      range(l)(1) = r
      range(r)(1) = r
    }
    res
  }

  /**
   * 执行用时：864 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：82.3 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：114 / 114
   *
   * @param arr
   * @param m
   * @return
   */
  def findLatestStep_stack(arr: Array[Int], m: Int): Int = {
    // t 数组存储对应索引的 1 的插入时间
    val t = new Array[Int](arr.length + 2)
    t(0) = Int.MaxValue
    for (i <- arr.indices) {
      t(arr(i)) = i
    }
    t(arr.length + 1) = Int.MaxValue
    val stack = new scala.collection.mutable.Stack[Int]
    var ans = -1
    for (i <- t.indices) {
      if (stack.nonEmpty) {
        // 代表前面栈顶对应的 1 的插入时间比现在第 i 个位置上的 1 插入时间早
        while (t(stack.top) < t(i)) {
          stack.pop()
          // 直接弹出，使用下一个栈顶的位置（对应之前连续 1 的左端）来计算连续的 1 长度
          if (i - stack.top - 1 == m) {
            if (stack.top == 0 && i == arr.length + 1) return arr.length
            val min = math.min(t(stack.top), t(i))
            if (min != Int.MaxValue && min > ans) ans = min
          }
        }
      }
      stack.push(i)
    }
    ans
  }
}
