package com.zerox.kotlin.from1to200

/**
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
 * @author ZeromaXHe
 * @since 2022/8/18 22:34
 */
object Solution2 {
    /**
     * Example:
     * var li = ListNode(5)
     * var v = li.`val`
     * Definition for singly-linked list.
     * class ListNode(var `val`: Int) {
     *     var next: ListNode? = null
     * }
     * 执行用时：224 ms, 在所有 Kotlin 提交中击败了 40.36% 的用户
     * 内存消耗：41.5 MB, 在所有 Kotlin 提交中击败了 60.09% 的用户
     * 通过测试用例：1568 / 1568
     */
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        val dummy = ListNode(-1)
        var p1 = l1
        var p2 = l2
        var p = dummy
        var carry = 0
        while (p1 != null && p2 != null) {
            val sum = p1.`val` + p2.`val` + carry
            p.next = ListNode(sum % 10)
            carry = sum / 10
            p = p.next!!
            p1 = p1.next
            p2 = p2.next
        }

        while (p1 != null) {
            if (carry == 0) {
                p.next = p1
                return dummy.next
            }
            val sum = p1.`val` + carry
            p.next = ListNode(sum % 10)
            carry = sum / 10
            p = p.next!!
            p1 = p1.next
        }

        while (p2 != null) {
            if (carry == 0) {
                p.next = p2
                return dummy.next
            }
            val sum = p2.`val` + carry
            p.next = ListNode(sum % 10)
            carry = sum / 10
            p = p.next!!
            p2 = p2.next
        }

        if (carry != 0) {
            p.next = ListNode(carry)
        }

        return dummy.next
    }

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }
}