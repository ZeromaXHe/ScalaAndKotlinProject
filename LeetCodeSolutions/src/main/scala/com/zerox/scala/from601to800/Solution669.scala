package com.zerox.scala.from601to800

/**
 * @author ZeromaXHe
 * @since 2022/9/10 12:54
 * @note
 * 669. 修剪二叉搜索树 | 难度：中等 | 标签：
 * 给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。通过修剪二叉搜索树，使得所有节点的值在[low, high]中。修剪树 不应该 改变保留在树中的元素的相对结构 (即，如果没有被移除，原有的父代子代关系都应当保留)。 可以证明，存在 唯一的答案 。
 *
 * 所以结果应当返回修剪好的二叉搜索树的新的根节点。注意，根节点可能会根据给定的边界发生改变。
 *
 * 示例 1：
 * 输入：root = [1,0,2], low = 1, high = 2
 * 输出：[1,null,2]
 *
 * 示例 2：
 * 输入：root = [3,0,4,null,2,null,null,1], low = 1, high = 3
 * 输出：[3,2,null,1]
 *
 * 提示：
 * 树中节点数在范围 [1, 104] 内
 * 0 <= Node.val <= 104
 * 树中每个节点的值都是 唯一 的
 * 题目数据保证输入是一棵有效的二叉搜索树
 * 0 <= low <= high <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/trim-a-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution669 {
  /**
   * 执行用时：580 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：55 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：78 / 78
   *
   * @param root
   * @param low
   * @param high
   * @return
   */
  def trimBST(root: TreeNode, low: Int, high: Int): TreeNode = {
    if (root == null) return null
    if (root.value < low) return trimBST(root.right, low, high)
    if (root.value > high) return trimBST(root.left, low, high)
    root.left = trimBST(root.left, low, high)
    root.right = trimBST(root.right, low, high)
    root
  }

  class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
    var value: Int = _value
    var left: TreeNode = _left
    var right: TreeNode = _right
  }
}
