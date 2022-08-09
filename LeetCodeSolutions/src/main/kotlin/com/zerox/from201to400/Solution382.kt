package com.zerox.from201to400

import kotlin.random.Random

/**
 * 382. 链表随机节点 | 难度：中等 | 标签：水塘抽样、链表、数学、随机化
 * 给你一个单链表，随机选择链表的一个节点，并返回相应的节点值。每个节点 被选中的概率一样 。
 *
 * 实现 Solution 类：
 *
 * Solution(ListNode head) 使用整数数组初始化对象。
 * int getRandom() 从链表中随机选择一个节点并返回该节点的值。链表中所有节点被选中的概率相等。
 *
 * 示例：
 * 输入
 * ["Solution", "getRandom", "getRandom", "getRandom", "getRandom", "getRandom"]
 * [[[1, 2, 3]], [], [], [], [], []]
 * 输出
 * [null, 1, 3, 2, 2, 3]
 *
 * 解释
 * Solution solution = new Solution([1, 2, 3]);
 * solution.getRandom(); // 返回 1
 * solution.getRandom(); // 返回 3
 * solution.getRandom(); // 返回 2
 * solution.getRandom(); // 返回 2
 * solution.getRandom(); // 返回 3
 * // getRandom() 方法应随机返回 1、2、3中的一个，每个元素被返回的概率相等。
 *
 * 提示：
 * 链表中的节点数在范围 [1, 104] 内
 * -104 <= Node.val <= 104
 * 至多调用 getRandom 方法 104 次
 *  
 *
 * 进阶：
 * 如果链表非常大且长度未知，该怎么处理？
 * 你能否在不使用额外空间的情况下解决此问题？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/linked-list-random-node
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/9 17:20
 */
object Solution382 {
    @JvmStatic
    fun main(args: Array<String>) {
        for (i in 0..10) {
            println(Random.nextInt(10))
        }
    }

    /**
     * Example:
     * var li = ListNode(5)
     * var v = li.`val`
     * Definition for singly-linked list.
     * | class ListNode(var `val`: Int) {
     * |     var next: ListNode? = null
     * | }
     *
     * 不知道题解里面那个水塘抽样有鸡毛用，还不是要 O(n)?
     *
     * 执行用时：248 ms, 在所有 Kotlin 提交中击败了 83.33% 的用户
     * 内存消耗：38 MB, 在所有 Kotlin 提交中击败了 50.00% 的用户
     * 通过测试用例：8 / 8
     */
    class Solution(head: ListNode?) {
        var len = 0
        var ptr: ListNode = head!!
        val rand = java.util.Random()

        init {
            if (head != null) {
                len = 1
                while (ptr.next != null) {
                    ptr = ptr.next!!
                    len++
                }
                ptr.next = head
            }
        }

        fun getRandom(): Int {
            // 力扣上面不能直接用 Kotlin 1.5.31 的 Random.nextInt，为了兼容只好用 Java 的了
            for (i in 0 until rand.nextInt(len)) {
                ptr = ptr.next!!
            }
            return ptr.`val`
        }

    }

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    /**
     * Your Solution object will be instantiated and called as such:
     * var obj = Solution(head)
     * var param_1 = obj.getRandom()
     */
}