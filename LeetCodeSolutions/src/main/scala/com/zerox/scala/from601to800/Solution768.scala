package com.zerox.scala.from601to800

/**
 * @author ZeromaXHe
 * @since 2022/8/13 9:04
 * @note
 * 768. 最多能完成排序的块 II | 难度：困难 | 标签：栈、贪心、数组、排序、单调栈
 * 这个问题和“最多能完成排序的块”相似，但给定数组中的元素可以重复，输入数组最大长度为2000，其中的元素最大为10**8。
 *
 * arr是一个可能包含重复元素的整数数组，我们将这个数组分割成几个“块”，并将这些块分别进行排序。之后再连接起来，使得连接的结果和按升序排序后的原数组相同。
 *
 * 我们最多能将数组分成多少块？
 *
 * 示例 1:
 * 输入: arr = [5,4,3,2,1]
 * 输出: 1
 * 解释:
 * 将数组分成2块或者更多块，都无法得到所需的结果。
 * 例如，分成 [5, 4], [3, 2, 1] 的结果是 [4, 5, 1, 2, 3]，这不是有序的数组。
 *
 * 示例 2:
 * 输入: arr = [2,1,3,4,4]
 * 输出: 4
 * 解释:
 * 我们可以把它分成两块，例如 [2, 1], [3, 4, 4]。
 * 然而，分成 [2, 1], [3], [4], [4] 可以得到最多的块数。
 *
 * 注意:
 * arr的长度在[1, 2000]之间。
 * arr[i]的大小在[0, 10**8]之间。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/max-chunks-to-make-sorted-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution768 {
  def main(args: Array[String]): Unit = {
    println(maxChunksToSorted(Array(5, 4, 3, 2, 1))) // 1
    println(maxChunksToSorted(Array(2, 1, 3, 4, 4))) // 4
    println(maxChunksToSorted(Array(2, 3, 5, 5, 4, 1, 3, 2, 1, 6, 7, 8, 9, 6, 5, 19))) // 3
  }

  /**
   * 执行用时：548 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：55.6 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：139 / 139
   *
   * @param arr
   * @return
   */
  def maxChunksToSorted(arr: Array[Int]): Int = {
    val stack = new scala.collection.mutable.Stack[Int]
    var max = Int.MinValue
    for (i <- arr) {
      while (stack.nonEmpty && i < stack.head) {
        stack.pop()
      }
      max = math.max(max, i)
      stack push max
    }
    stack.size
  }
}
