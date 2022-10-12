package com.zerox.scala.from801to1000

/**
 * @author ZeromaXHe
 * @since 2022/10/12 10:08
 * @note
 * 817. 链表组件 | 难度：中等
 */
object Solution817 {
  /**
   * 时间 592 ms 击败 100%
   * 内存 54.1 MB 击败 100%
   *
   * @param head
   * @param nums
   * @return
   */
  def numComponents(head: ListNode, nums: Array[Int]): Int = {
    val set = nums.toSet
    var pre = false
    var node = head
    var count = 0
    while (node != null) {
      val exist = set(node.x)
      if (exist && !pre) count += 1
      pre = exist
      node = node.next
    }
    count
  }

  class ListNode(_x: Int = 0, _next: ListNode = null) {
    var next: ListNode = _next
    var x: Int = _x
  }
}
