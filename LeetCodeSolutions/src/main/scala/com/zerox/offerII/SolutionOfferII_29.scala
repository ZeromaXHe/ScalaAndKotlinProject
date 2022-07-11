package com.zerox.offerII

/**
 * @author ZeromaXHe
 * @since 2022/6/18 14:24
 * @note
 * 剑指 Offer II 029. 排序的循环链表 | 难度：中等 | 标签：链表
 * 给定循环单调非递减列表中的一个点，写一个函数向这个列表中插入一个新元素 insertVal ，使这个列表仍然是循环升序的。
 *
 * 给定的可以是这个列表中任意一个顶点的指针，并不一定是这个列表中最小元素的指针。
 *
 * 如果有多个满足条件的插入位置，可以选择任意一个位置插入新的值，插入后整个列表仍然保持有序。
 *
 * 如果列表为空（给定的节点是 null），需要创建一个循环有序列表并返回这个节点。否则。请返回原先给定的节点。
 *
 * 示例 1：
 * 输入：head = [3,4,1], insertVal = 2
 * 输出：[3,4,1,2]
 * 解释：在上图中，有一个包含三个元素的循环有序列表，你获得值为 3 的节点的指针，我们需要向表中插入元素 2 。新插入的节点应该在 1 和 3 之间，插入之后，整个列表如上图所示，最后返回节点 3 。
 *
 * 示例 2：
 * 输入：head = [], insertVal = 1
 * 输出：[1]
 * 解释：列表为空（给定的节点是 null），创建一个循环有序列表并返回这个节点。
 *
 * 示例 3：
 * 输入：head = [1], insertVal = 0
 * 输出：[1,0]
 *
 * 提示：
 * 0 <= Number of Nodes <= 5 * 10^4
 * -10^6 <= Node.val <= 10^6
 * -10^6 <= insertVal <= 10^6
 *
 * 注意：本题与主站 708 题相同： https://leetcode-cn.com/problems/insert-into-a-sorted-circular-linked-list/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/4ueAj6
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionOfferII_29 {
  /**
   * Definition for a Node.
   * | class Node(var _value: Int) {
   * |   var value: Int = _value
   * |   var next: Node = null
   * | }
   *
   * 执行用时：476 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：51.4 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：106 / 106
   *
   * @param head
   * @param insertVal
   * @return
   */
  def insert(head: Node, insertVal: Int): Node = {
    if (head == null) {
      val node = new Node(insertVal)
      node.next = node
      return node
    } else if (head.next == head) {
      val node = new Node(insertVal)
      node.next = head
      head.next = node
      return head
    }
    var less = head
    var more = head.next;
    while (more != head && !((less.value <= insertVal && more.value >= insertVal)
      || (less.value > more.value && (insertVal <= more.value || insertVal >= less.value)))) {
      less = less.next;
      more = more.next;
    }
    val node = new Node(insertVal)
    node.next = more
    less.next = node
    head
  }

  class Node(var _value: Int) {
    var value: Int = _value
    var next: Node = null
  }
}
