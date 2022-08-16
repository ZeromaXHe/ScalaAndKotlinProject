package com.zerox.kotlin.from1to200

/**
 * 25. K 个一组翻转链表 | 难度：困难 | 标签：递归、链表
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 *
 * 示例 2：
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 *
 * 提示：
 * 链表中的节点数目为 n
 * 1 <= k <= n <= 5000
 * 0 <= Node.val <= 1000
 *
 * 进阶：你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/reverse-nodes-in-k-group
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/16 16:32
 */
object Solution25 {
    /**
     * Example:
     * var li = ListNode(5)
     * var v = li.`val`
     * Definition for singly-linked list.
     * class ListNode(var `val`: Int) {
     *     var next: ListNode? = null
     * }
     *
     * 执行用时：200 ms, 在所有 Kotlin 提交中击败了 46.15% 的用户
     * 内存消耗：36.2 MB, 在所有 Kotlin 提交中击败了 18.46% 的用户
     * 通过测试用例：62 / 62
     */
    fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
        if (k == 1) return head
        val dummy = ListNode(-1)
        var end = dummy
        var ptr = head
        while (ptr != null) {
            val from: ListNode = ptr
            var i = 0
            while (i < k && ptr != null) {
                ptr = ptr.next
                i++
            }
            if (i < k) {
                end.next = from
                return dummy.next
            }
            val next = ptr
            ptr = from
            var temp: ListNode?
            while (ptr != next) {
                temp = ptr!!.next
                ptr.next = end.next
                end.next = ptr
                ptr = temp
            }
            end = from
        }

        return dummy.next
    }

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }
}