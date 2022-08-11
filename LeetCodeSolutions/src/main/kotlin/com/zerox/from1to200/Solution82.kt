package com.zerox.from1to200

/**
 * 82. 删除排序链表中的重复元素 II | 难度：中等 | 标签：
 * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
 *
 * 示例 1：
 * 输入：head = [1,2,3,3,4,4,5]
 * 输出：[1,2,5]
 *
 * 示例 2：
 * 输入：head = [1,1,1,2,3]
 * 输出：[2,3]
 *
 * 提示：
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序 排列
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/11 15:18
 */
object Solution82 {
    /**
     * Example:
     * var li = ListNode(5)
     * var v = li.`val`
     * Definition for singly-linked list.
     * class ListNode(var `val`: Int) {
     *     var next: ListNode? = null
     * }
     *
     * 执行用时：200 ms, 在所有 Kotlin 提交中击败了 13.89% 的用户
     * 内存消耗：34.8 MB, 在所有 Kotlin 提交中击败了 58.33% 的用户
     * 通过测试用例：166 / 166
     */
    fun deleteDuplicates(head: ListNode?): ListNode? {
        if (head == null) return head
        val dummy = ListNode(-1)
        var end = dummy
        var ptr = head
        while (ptr != null) {
            if (ptr.next?.`val` == ptr.`val`) {
                val pass = ptr.`val`
                while (ptr?.`val` == pass) {
                    ptr = ptr.next
                }
            } else {
                end.next = ptr
                end = ptr
                ptr = ptr.next
                end.next = null
            }
        }
        return dummy.next
    }

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }
}