package com.zerox.scala.from1to200

/**
 * @author zhuxi
 * @since 2022/6/14 18:12
 * @note
 * 2. 两数相加 | 难度：中等 | 标签：
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 *
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例 1：
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 *
 * 示例 2：
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 *
 * 示例 3：
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 *
 * 提示：
 * 每个链表中的节点数在范围 [1, 100] 内
 * 0 <= Node.val <= 9
 * 题目数据保证列表表示的数字不含前导零
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution2 {
  /**
   * 以下均为去掉 @scala.annotation.tailrec 后的结果
   *
   * 方法体为 addTwoNumbers(l1, l2, carry, headPtr, tailPtr) 时：
   * 执行用时： 576 ms , 在所有 Scala 提交中击败了 82.09% 的用户
   * 内存消耗： 56.5 MB , 在所有 Scala 提交中击败了 5.97% 的用户
   * 通过测试用例：1568 / 1568
   *
   * 发现方法体里面的头尾指针可以优化掉，就修改后又提交了一下。耗时多了一些，不知道是波动还是确实有影响？
   * 方法体为 addTwoNumbers(l1, l2, carry) 时：592 ms（48%） 和 56 MB
   *
   * 不去掉注解的话：624 ms（20%） 和 56.5 MB
   *
   * @param l1
   * @param l2
   * @return
   */
  def addTwoNumbers(l1: ListNode, l2: ListNode): ListNode = {
    val headPtr: ListNode = new ListNode()
    val tailPtr: ListNode = new ListNode()

    @scala.annotation.tailrec
    def addTwoNumbers(l1: ListNode, l2: ListNode, carry: Int): ListNode = {
      if (l1 == null) {
        if (carry == 0) l2
        else addTwoNumbers(new ListNode(1), l2, 0)
      } else if (l2 == null) {
        if (carry == 0) l1
        else addTwoNumbers(l1, new ListNode(1), 0)
      } else {
        val sum = l1.x + l2.x + carry
        val result = new ListNode(sum % 10)
        if (headPtr.next == null) {
          headPtr.next = result
          tailPtr.next = result
        } else {
          tailPtr.next.next = result
          tailPtr.next = result
        }
        addTwoNumbers(l1.next, l2.next, if (sum >= 10) 1 else 0)
      }
    }

    val node = addTwoNumbers(l1, l2, 0)
    if (tailPtr.next != null) {
      tailPtr.next.next = node
      headPtr.next
    } else node
  }

  /**
   * Definition for singly-linked list.
   * | class ListNode(_x: Int = 0, _next: ListNode = null) {
   * |   var next: ListNode = _next
   * |   var x: Int = _x
   * | }
   *
   * 执行用时：616 ms, 在所有 Scala 提交中击败了 26.87% 的用户
   * 内存消耗：56.3 MB, 在所有 Scala 提交中击败了 16.42% 的用户
   * 通过测试用例：1568 / 1568
   *
   * @param l1
   * @param l2
   * @return
   */
  def addTwoNumbers_recursive(l1: ListNode, l2: ListNode): ListNode = {
    def addTwoNumbers(l1: ListNode, l2: ListNode, carry: Int): ListNode = {
      if (l1 == null) {
        if (carry == 0) l2
        else addTwoNumbers(new ListNode(1), l2, 0)
      } else if (l2 == null) {
        if (carry == 0) l1
        else addTwoNumbers(l1, new ListNode(1), 0)
      } else {
        val sum = l1.x + l2.x + carry
        val result = new ListNode(sum % 10)
        result.next = addTwoNumbers(l1.next, l2.next, if (sum >= 10) 1 else 0)
        result
      }
    }

    addTwoNumbers(l1, l2, 0)
  }

  class ListNode(_x: Int = 0, _next: ListNode = null) {
    var next: ListNode = _next
    var x: Int = _x

    /// 自己实现的 equals
    // override def equals(obj: Any): Boolean = {
    //   obj match {
    //     case that: ListNode => (this.x == that.x) && (this.next == that.next)
    //     case _ => false
    //   }
    // }

    // IDEA 自动实现的
    override def toString = s"ListNode($next, $x)"

    def canEqual(other: Any): Boolean = other.isInstanceOf[ListNode]

    override def equals(other: Any): Boolean = other match {
      case that: ListNode =>
        (that canEqual this) &&
          next == that.next &&
          x == that.x
      case _ => false
    }

    override def hashCode(): Int = {
      val state = Seq(next, x)
      state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
    }
  }
}
