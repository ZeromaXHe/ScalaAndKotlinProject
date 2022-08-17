package com.zerox.kotlin.from1to200

/**
 * 23. 合并K个升序链表 | 难度：困难 | 标签：链表、分治、堆（优先队列）、归并排序
 * 给你一个链表数组，每个链表都已经按升序排列。
 *
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * 示例 1：
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 *
 * 示例 2：
 * 输入：lists = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：lists = [[]]
 * 输出：[]
 *
 * 提示：
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/merge-k-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/17 18:06
 */
object Solution23 {
    /**
     * Example:
     * var li = ListNode(5)
     * var v = li.`val`
     * Definition for singly-linked list.
     * class ListNode(var `val`: Int) {
     *     var next: ListNode? = null
     * }
     *
     * 执行用时：204 ms, 在所有 Kotlin 提交中击败了 87.34% 的用户
     * 内存消耗：35.5 MB, 在所有 Kotlin 提交中击败了 97.47% 的用户
     * 通过测试用例：133 / 133
     */
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        if (lists.isEmpty()) return null
        val sorted = java.util.PriorityQueue<ListNode>(compareBy { it.`val` })
        for (i in lists.indices) {
            if (lists[i] != null) sorted.offer(lists[i])
        }

        val dummy = ListNode(-1)
        var end = dummy

        while (sorted.isNotEmpty()) {
            val smallest = sorted.poll()
            end.next = smallest
            if (smallest.next != null) {
                sorted.offer(smallest.next)
            }
            end = smallest
            smallest.next = null
        }

        return dummy.next
    }

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }
}