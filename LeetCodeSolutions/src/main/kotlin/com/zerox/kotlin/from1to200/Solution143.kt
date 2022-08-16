package com.zerox.kotlin.from1to200

/**
 * 143. 重排链表 | 难度：中等 | 标签：
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 *
 * L0 → L1 → … → Ln - 1 → Ln
 * 请将其重新排列后变为：
 *
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4]
 * 输出：[1,4,2,3]
 *
 * 示例 2：
 * 输入：head = [1,2,3,4,5]
 * 输出：[1,5,2,4,3]
 *
 * 提示：
 * 链表的长度范围为 [1, 5 * 104]
 * 1 <= node.val <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/reorder-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/16 16:00
 */
object Solution143 {
    /**
     * Example:
     * var li = ListNode(5)
     * var v = li.`val`
     * Definition for singly-linked list.
     * class ListNode(var `val`: Int) {
     *     var next: ListNode? = null
     * }
     *
     * 执行用时：256 ms, 在所有 Kotlin 提交中击败了 47.83% 的用户
     * 内存消耗：39 MB, 在所有 Kotlin 提交中击败了 65.22% 的用户
     * 通过测试用例：12 / 12
     */
    fun reorderList(head: ListNode?): Unit {
        if (head!!.next == null) return
        var p1 = head
        var p2 = head
        while (p2?.next != null) {
            p1 = p1!!.next
            p2 = p2.next?.next
        }
        val dummy = ListNode(-1)
        dummy.next = p1!!.next
        p1.next = null
        p1 = dummy.next
        dummy.next = null
        while (p1 != null) {
            val next = p1.next
            p1.next = dummy.next
            dummy.next = p1
            p1 = next
        }

        p1 = head
        p2 = dummy.next
        while (p2 != null) {
            val next2 = p2.next
            p2.next = p1!!.next
            p1.next = p2
            p1 = p2.next
            p2 = next2
        }
    }

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }
}