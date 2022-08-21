package com.zerox.scala.from401to600

/**
 * @author ZeromaXHe
 * @since 2022/8/21 19:51
 * @note
 * 445. 两数相加 II | 难度：中等 | 标签：
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 *
 * 示例1：
 * 输入：l1 = [7,2,4,3], l2 = [5,6,4]
 * 输出：[7,8,0,7]
 *
 * 示例2：
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[8,0,7]
 *
 * 示例3：
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 *
 * 提示：
 * 链表的长度范围为 [1, 100]
 * 0 <= node.val <= 9
 * 输入数据保证链表代表的数字无前导 0
 *
 * 进阶：如果输入链表不能翻转该如何解决？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/add-two-numbers-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution445 {
  /**
   * Definition for singly-linked list.
   * | class ListNode(_x: Int = 0, _next: ListNode = null) {
   * |   var next: ListNode = _next
   * |   var x: Int = _x
   * | }
   * 执行用时：620 ms, 在所有 Scala 提交中击败了 33.33% 的用户
   * 内存消耗：56.7 MB, 在所有 Scala 提交中击败了 33.33% 的用户
   * 通过测试用例：1563 / 1563
   */
  def addTwoNumbers(l1: ListNode, l2: ListNode): ListNode = {
    val stack1 = new scala.collection.mutable.Stack[Int]
    val stack2 = new scala.collection.mutable.Stack[Int]
    var p1 = l1
    var p2 = l2
    while (p1 != null) {
      stack1.push(p1.x)
      p1 = p1.next
    }
    while (p2 != null) {
      stack2.push(p2.x)
      p2 = p2.next
    }
    var carry = 0
    var result: ListNode = null
    while (stack1.nonEmpty || stack2.nonEmpty || carry > 0) {
      val sum = (if (stack1.isEmpty) 0 else stack1.pop()) + (if (stack2.isEmpty) 0 else stack2.pop()) + carry
      result = new ListNode(sum % 10, result)
      carry = sum / 10
    }
    result
  }

  class ListNode(_x: Int = 0, _next: ListNode = null) {
    var next: ListNode = _next
    var x: Int = _x
  }
}
