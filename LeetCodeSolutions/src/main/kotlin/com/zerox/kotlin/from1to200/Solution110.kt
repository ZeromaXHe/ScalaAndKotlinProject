package com.zerox.kotlin.from1to200

/**
 * 110. 平衡二叉树 | 难度：简单 | 标签：树、深度优先搜索、二叉树
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 *
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：true
 *
 * 示例 2：
 * 输入：root = [1,2,2,3,3,null,null,4,4]
 * 输出：false
 *
 * 示例 3：
 * 输入：root = []
 * 输出：true
 *
 * 提示：
 * 树中的节点数在范围 [0, 5000] 内
 * -104 <= Node.val <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/balanced-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/10 14:53
 */
object Solution110 {
    /**
     * Example:
     * var ti = TreeNode(5)
     * var v = ti.`val`
     * Definition for a binary tree node.
     * | class TreeNode(var `val`: Int) {
     * |     var left: TreeNode? = null
     * |     var right: TreeNode? = null
     * | }
     *
     * 执行用时：208 ms, 在所有 Kotlin 提交中击败了 18.42% 的用户
     * 内存消耗：35.1 MB, 在所有 Kotlin 提交中击败了 97.37% 的用户
     * 通过测试用例：228 / 228
     */
    fun isBalanced(root: TreeNode?): Boolean {
        return balanceAndDepth(root).first
    }

    private fun balanceAndDepth(root: TreeNode?): Pair<Boolean, Int> {
        if (root == null) return Pair(true, 0)
        val left = balanceAndDepth(root.left)
        if (!left.first) return left
        val right = balanceAndDepth(root.right)
        if (!right.first) return right
        return if (Math.abs(left.second - right.second) > 1) Pair(false, 1)
        else Pair(true, Math.max(left.second, right.second) + 1)
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}