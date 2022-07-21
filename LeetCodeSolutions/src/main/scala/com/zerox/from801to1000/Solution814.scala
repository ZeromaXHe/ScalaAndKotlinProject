package com.zerox.from801to1000

/**
 * @author zhuxi
 * @since 2022/7/21 9:58
 * @note
 * 814. 二叉树剪枝 | 难度：中等 | 标签：树、深度优先搜索、二叉树
 * 给你二叉树的根结点 root ，此外树的每个结点的值要么是 0 ，要么是 1 。
 *
 * 返回移除了所有不包含 1 的子树的原二叉树。
 *
 * 节点 node 的子树为 node 本身加上所有 node 的后代。
 *
 * 示例 1：
 * 输入：root = [1,null,0,0,1]
 * 输出：[1,null,0,null,1]
 * 解释：
 * 只有红色节点满足条件“所有不包含 1 的子树”。 右图为返回的答案。
 *
 * 示例 2：
 * 输入：root = [1,0,1,0,0,0,1]
 * 输出：[1,null,1,null,1]
 *
 * 示例 3：
 * 输入：root = [1,1,0,1,1,0,1,0]
 * 输出：[1,1,0,1,1,null,1]
 *
 * 提示：
 * 树中节点的数目在范围 [1, 200] 内
 * Node.val 为 0 或 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/binary-tree-pruning
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution814 {

  /**
   * Definition for a binary tree node.
   * | class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
   * |   var value: Int = _value
   * |   var left: TreeNode = _left
   * |   var right: TreeNode = _right
   * | }
   *
   * 执行用时：500 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：55 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：30 / 30
   *
   * @param root
   * @return
   */
  def pruneTree_sub(root: TreeNode): TreeNode = {
    val dummy: TreeNode = new TreeNode(1, root, null)
    pruneSubTree(dummy, left = true)
    dummy.left
  }

  def pruneSubTree(root: TreeNode, left: Boolean): Unit = {
    val sub = if (left) root.left else root.right
    if (sub.left != null) pruneSubTree(sub, left = true)
    if (sub.right != null) pruneSubTree(sub, left = false)
    if (sub.value == 0 && sub.left == null && sub.right == null) {
      if (left) root.left = null
      else root.right = null
    }
  }

  /**
   * 执行用时：524 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：55 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：30 / 30
   *
   * @param root
   * @return
   */
  def pruneTree(root: TreeNode): TreeNode = {
    if (root == null) return null
    root.left = pruneTree(root.left)
    root.right = pruneTree(root.right)
    if (root.value == 0 && root.left == null && root.right == null) null else root
  }

  class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
    var value: Int = _value
    var left: TreeNode = _left
    var right: TreeNode = _right
  }
}
