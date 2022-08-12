package com.zerox.from1201to1400

/**
 * 1367. 二叉树中的列表 | 难度：中等 | 标签：树、深度优先搜索、广度优先搜索、链表、二叉树
 * 给你一棵以 root 为根的二叉树和一个 head 为第一个节点的链表。
 *
 * 如果在二叉树中，存在一条一直向下的路径，且每个点的数值恰好一一对应以 head 为首的链表中每个节点的值，那么请你返回 True ，否则返回 False 。
 *
 * 一直向下的路径的意思是：从树中某个节点开始，一直连续向下的路径。
 *
 * 示例 1：
 * 输入：head = [4,2,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
 * 输出：true
 * 解释：树中蓝色的节点构成了与链表对应的子路径。
 *
 * 示例 2：
 * 输入：head = [1,4,2,6], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
 * 输出：true
 *
 * 示例 3：
 * 输入：head = [1,4,2,6,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
 * 输出：false
 * 解释：二叉树中不存在一一对应链表的路径。
 *
 * 提示：
 * 二叉树和链表中的每个节点的值都满足 1 <= node.val <= 100 。
 * 链表包含的节点数目在 1 到 100 之间。
 * 二叉树包含的节点数目在 1 到 2500 之间。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/linked-list-in-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/12 11:01
 */
object Solution1367 {
    /**
     * Example:
     * var li = ListNode(5)
     * var v = li.`val`
     * Definition for singly-linked list.
     * class ListNode(var `val`: Int) {
     *     var next: ListNode? = null
     * }
     */
    /**
     * Example:
     * var ti = TreeNode(5)
     * var v = ti.`val`
     * Definition for a binary tree node.
     * class TreeNode(var `val`: Int) {
     *     var left: TreeNode? = null
     *     var right: TreeNode? = null
     * }
     */
    /**
     * 执行用时：232 ms, 在所有 Kotlin 提交中击败了 50.00% 的用户
     * 内存消耗：37.2 MB, 在所有 Kotlin 提交中击败了 50.00% 的用户
     * 通过测试用例：67 / 67
     *
     * 不能合并子方法，不然计算的就是子序列（可断开）里面有匹配的就可以了
     */
    fun isSubPath(head: ListNode?, root: TreeNode?): Boolean {
        if (head == null) return true
        if (root == null) return false
        if (isSub(head, root)) return true
        return isSubPath(head, root.left) || isSubPath(head, root.right)
    }

    private fun isSub(head: ListNode?, root: TreeNode?): Boolean {
        if (head == null) return true
        if (root == null) return false
        return head.`val` == root.`val` && (isSub(head.next, root.left) || isSub(head.next, root.right))
    }

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}