package com.zerox.scala.from1to200

/**
 * @author zhuxi
 * @since 2022/10/28 17:13
 * @note
 * 156. 上下翻转二叉树 | 难度：中等 | 标签：树、深度优先搜索、二叉树
 * 给你一个二叉树的根节点 root ，请你将此二叉树上下翻转，并返回新的根节点。
 *
 * 你可以按下面的步骤翻转一棵二叉树：
 *
 * 原来的左子节点变成新的根节点
 * 原来的根节点变成新的右子节点
 * 原来的右子节点变成新的左子节点
 *
 * 上面的步骤逐层进行。题目数据保证每个右节点都有一个同级节点（即共享同一父节点的左节点）且不存在子节点。
 *
 * 示例 1：
 * 输入：root = [1,2,3,4,5]
 * 输出：[4,5,2,null,null,3,1]
 *
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：root = [1]
 * 输出：[1]
 *
 * 提示：
 * 树中节点数目在范围 [0, 10] 内
 * 1 <= Node.val <= 10
 * 树中的每个右节点都有一个同级节点（即共享同一父节点的左节点）
 * 树中的每个右节点都没有子节点
 */
object Solution156 {
  def main(args: Array[String]): Unit = {
    val node = new TreeNode(1)
    node.left = new TreeNode(2)
    node.right = new TreeNode(3)
    node.left.left = new TreeNode(4)
    node.left.right = new TreeNode(5)
    upsideDownBinaryTree(node)
  }

  /**
   * 时间 568 ms 击败 100%
   * 内存 54.6 MB 击败 100%
   *
   * @param root
   * @return
   */
  def upsideDownBinaryTree(root: TreeNode): TreeNode = {
    if (root == null) return null
    var res = root
    if (root.left != null) {
      val left = root.left
      res = upsideDownBinaryTree(left)
      left.left = root.right
      left.right = root
      root.left = null
      root.right = null
    }
    res
  }

  class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
    var value: Int = _value
    var left: TreeNode = _left
    var right: TreeNode = _right
  }
}
