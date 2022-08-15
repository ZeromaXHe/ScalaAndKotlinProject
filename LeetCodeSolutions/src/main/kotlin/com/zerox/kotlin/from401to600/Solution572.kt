package com.zerox.kotlin.from401to600

/**
 * 572. 另一棵树的子树 | 难度：简单 | 标签：树、深度优先搜索、二叉树、字符串匹配、哈希函数
 * 给你两棵二叉树 root 和 subRoot 。检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。如果存在，返回 true ；否则，返回 false 。
 *
 * 二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。tree 也可以看做它自身的一棵子树。
 *
 * 示例 1：
 * 输入：root = [3,4,5,1,2], subRoot = [4,1,2]
 * 输出：true
 *
 * 示例 2：
 * 输入：root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
 * 输出：false
 *
 * 提示：
 * root 树上的节点数量范围是 [1, 2000]
 * subRoot 树上的节点数量范围是 [1, 1000]
 * -104 <= root.val <= 104
 * -104 <= subRoot.val <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/subtree-of-another-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/15 15:26
 */
object Solution572 {
    @JvmStatic
    fun main(args: Array<String>) {
        val root = TreeNode(3)
        root.left = TreeNode(4)
        root.right = TreeNode(5)
        root.left?.left = TreeNode(1)
        root.left?.right = TreeNode(2)
        val subRoot = root.left
        println(isSubtree(root, subRoot)) // true
    }

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
     * 执行用时：212 ms, 在所有 Kotlin 提交中击败了 30.77% 的用户
     * 内存消耗：37.3 MB, 在所有 Kotlin 提交中击败了 30.77% 的用户
     * 通过测试用例：182 / 182
     */
    fun isSubtree(root: TreeNode?, subRoot: TreeNode?): Boolean {
        return if (isSub(root, subRoot)) true
        else if (root == null) false
        else isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot)
    }

    fun isSub(root: TreeNode?, subRoot: TreeNode?): Boolean {
        return if (subRoot == null && root == null) true
        else if (subRoot == null || root == null || root.`val` != subRoot.`val`) false
        else isSub(root.left, subRoot.left) && isSub(root.right, subRoot.right)
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}