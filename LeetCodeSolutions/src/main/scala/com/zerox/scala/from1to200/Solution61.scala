package com.zerox.scala.from1to200

/**
 * @author ZeromaXHe
 * @since 2022/8/21 20:01
 * @note
 * 61. 旋转链表 | 难度：中等 | 标签：链表、双指针
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 *
 * 示例 2：
 * 输入：head = [0,1,2], k = 4
 * 输出：[2,0,1]
 *
 * 提示：
 * 链表中节点的数目在范围 [0, 500] 内
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/rotate-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution61 {
  /**
   * Definition for singly-linked list.
   * | class ListNode(_x: Int = 0, _next: ListNode = null) {
   * |   var next: ListNode = _next
   * |   var x: Int = _x
   * | }
   *
   * 执行用时：532 ms, 在所有 Scala 提交中击败了 30.77% 的用户
   * 内存消耗：55.4 MB, 在所有 Scala 提交中击败了 53.85% 的用户
   * 通过测试用例：231 / 231
   */
  def rotateRight(head: ListNode, k: Int): ListNode = {
    if (head == null) return null
    var length = 1
    var end = head
    while (end.next != null) {
      end = end.next
      length += 1
    }
    var count = (length - k % length) % length
    var res = head
    if (count == 0) return res
    var pre: ListNode = null
    while (count > 0) {
      pre = res
      res = res.next
      count -= 1
    }
    pre.next = null
    end.next = head
    res
  }

  class ListNode(_x: Int = 0, _next: ListNode = null) {
    var next: ListNode = _next
    var x: Int = _x
  }
}
