package com.zerox.scala.from801to1000

/**
 * @author zhuxi
 * @since 2022/8/31 9:56
 * @note
 * 946. 验证栈序列 | 难度：中等 | 标签：栈、数组、模拟
 * 给定 pushed 和 popped 两个序列，每个序列中的 值都不重复，只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时，返回 true；否则，返回 false 。
 *
 * 示例 1：
 * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * 输出：true
 * 解释：我们可以按以下顺序执行：
 * push(1), push(2), push(3), push(4), pop() -> 4,
 * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 *
 * 示例 2：
 * 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * 输出：false
 * 解释：1 不能在 2 之前弹出。
 *
 * 提示：
 * 1 <= pushed.length <= 1000
 * 0 <= pushed[i] <= 1000
 * pushed 的所有元素 互不相同
 * popped.length == pushed.length
 * popped 是 pushed 的一个排列
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/validate-stack-sequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution946 {
  /**
   * 执行用时：548 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：55.3 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：151 / 151
   *
   * @param pushed
   * @param popped
   * @return
   */
  def validateStackSequences(pushed: Array[Int], popped: Array[Int]): Boolean = {
    val stack = new scala.collection.mutable.Stack[Int]
    var ptr = 0
    for (push <- pushed) {
      stack.push(push)
      while (stack.nonEmpty && stack.head == popped(ptr)) {
        ptr += 1
        stack.pop()
      }
    }
    stack.isEmpty
  }
}
