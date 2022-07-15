package com.zerox.offer

/**
 * @author zhuxi
 * @since 2022/7/15 15:26
 * @note
 * 剑指 Offer 06. 从尾到头打印链表 | 难度：简单 | 标签：栈、递归、链表、双指针
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 *
 * 示例 1：
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 *
 * 限制：
 * 0 <= 链表长度 <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionOffer06 {
  /**
   * Definition for singly-linked list.
   * | class ListNode(var _x: Int = 0) {
   * |   var next: ListNode = null
   * |   var x: Int = _x
   * | }
   *
   * 执行用时：536 ms, 在所有 Scala 提交中击败了 81.82% 的用户
   * 内存消耗：53.6 MB, 在所有 Scala 提交中击败了 81.82% 的用户
   * 通过测试用例：24 / 24
   *
   * @param head
   * @return
   */
  def reversePrint(head: ListNode): Array[Int] = {
    (Stream.iterate(head)(_.next) takeWhile (_ != null)).map(_.x).reverse.toArray
  }

  class ListNode(var _x: Int = 0) {
    var next: ListNode = null
    var x: Int = _x
  }
}
