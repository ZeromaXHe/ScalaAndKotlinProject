package com.zerox.scala.from601to800

/**
 * @author zhuxi
 * @since 2022/8/22 15:20
 * @note
 * 707. 设计链表 | 难度：中等 | 标签：设计、链表
 * 设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next 是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-idx 的。
 *
 * 在链表类中实现这些功能：
 *
 * get(idx)：获取链表中第 idx 个节点的值。如果索引无效，则返回-1。
 * addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
 * addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
 * addAtIndex(idx,val)：在链表中的第 idx 个节点之前添加值为 val  的节点。如果 idx 等于链表的长度，则该节点将附加到链表的末尾。如果 idx 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
 * deleteAtIndex(idx)：如果索引 idx 有效，则删除链表中的第 idx 个节点。
 *
 * 示例：
 * MyLinkedList linkedList = new MyLinkedList();
 * linkedList.addAtHead(1);
 * linkedList.addAtTail(3);
 * linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
 * linkedList.get(1);            //返回2
 * linkedList.deleteAtIndex(1);  //现在链表是1-> 3
 * linkedList.get(1);            //返回3
 *
 * 提示：
 * 所有val值都在 [1, 1000] 之内。
 * 操作次数将在  [1, 1000] 之内。
 * 请不要使用内置的 LinkedList 库。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/design-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution707 {
  /**
   * 执行用时：644 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：57.9 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：64 / 64
   */
  class MyLinkedList() {
    private case class Node(x: Int, var prev: Node = null, var next: Node = null)

    private val head: Node = Node(-1)
    private val tail: Node = Node(-1, head)
    head.next = tail
    private var count = 0

    private def getNode(index: Int): Node = {
      var idx = index
      if (idx >= count) return null
      if (idx <= count / 2) {
        var ptr = head.next
        while (idx > 0) {
          ptr = ptr.next
          idx -= 1
        }
        ptr
      } else {
        idx = count - 1 - idx
        var ptr = tail.prev
        while ( {
          idx > 0
        }) {
          ptr = ptr.prev
          idx -= 1
        }
        ptr
      }
    }

    def get(index: Int): Int = {
      if (index >= count) -1
      else getNode(index).x
    }

    def addAtNodeNext(n: Node, `val`: Int) {
      val node = Node(`val`, n, n.next)
      n.next.prev = node
      n.next = node
      count += 1
    }

    def addAtNodePrev(n: Node, `val`: Int) {
      val node = Node(`val`, n.prev, n)
      n.prev.next = node
      n.prev = node
      count += 1
    }

    def addAtHead(`val`: Int) {
      addAtNodeNext(head, `val`)
    }

    def addAtTail(`val`: Int) {
      addAtNodePrev(tail, `val`)
    }

    def addAtIndex(index: Int, `val`: Int) {
      if (index < count) {
        val next = getNode(index)
        addAtNodePrev(next, `val`)
      }
      else if (index == count) addAtTail(`val`)
    }

    def deleteAtIndex(index: Int) {
      if (index < count) {
        val del = getNode(index)
        del.prev.next = del.next
        del.next.prev = del.prev
        count -= 1
      }
    }

  }

  /**
   * Your MyLinkedList object will be instantiated and called as such:
   * var obj = new MyLinkedList()
   * var param_1 = obj.get(idx)
   * obj.addAtHead(`val`)
   * obj.addAtTail(`val`)
   * obj.addAtIndex(idx,`val`)
   * obj.deleteAtIndex(idx)
   */
}
