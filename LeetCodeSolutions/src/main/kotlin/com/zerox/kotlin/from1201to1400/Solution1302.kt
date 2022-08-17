package com.zerox.kotlin.from1201to1400

/**
 * 1302. 层数最深叶子节点的和 | 难度：中等 | 标签：树、深度优先搜索、广度优先搜索、二叉树
 * 给你一棵二叉树的根节点 root ，请你返回 层数最深的叶子节点的和 。
 *
 * 示例 1：
 * 输入：root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
 * 输出：15
 *
 * 示例 2：
 * 输入：root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
 * 输出：19
 *
 * 提示：
 * 树中节点数目在范围 [1, 104] 之间。
 * 1 <= Node.val <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/deepest-leaves-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/17 9:26
 */
object Solution1302 {
    /**
     * Example:
     * var ti = TreeNode(5)
     * var v = ti.`val`
     * Definition for a binary tree node.
     * class TreeNode(var `val`: Int) {
     *     var left: TreeNode? = null
     *     var right: TreeNode? = null
     * }
     *
     * 执行用时：292 ms, 在所有 Kotlin 提交中击败了 100.00% 的用户
     * 内存消耗：39 MB, 在所有 Kotlin 提交中击败了 100.00% 的用户
     * 通过测试用例：39 / 39
     */
    fun deepestLeavesSum(root: TreeNode?): Int {
        var result = 0
        val queue = java.util.LinkedList<TreeNode>()
        queue.offer(root)
        while (queue.isNotEmpty()) {
            result = 0
            var size = queue.size
            while (size > 0) {
                val poll = queue.poll()
                if (poll.left != null) queue.offer(poll.left)
                if (poll.right != null) queue.offer(poll.right)
                result += poll.`val`
                size--
            }
        }
        return result
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}