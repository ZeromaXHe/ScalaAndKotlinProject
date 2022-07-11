package com.zerox.interview

/**
 * @author zhuxi
 * @since 2022/7/11 14:19
 * @note
 * 面试题 17.12. BiNode | 难度：简单 | 标签：栈、树、深度优先搜索、二叉搜索树、链表、二叉树
 * 二叉树数据结构TreeNode可用来表示单向链表（其中left置空，right为下一个链表节点）。
 * 实现一个方法，把二叉搜索树转换为单向链表，要求依然符合二叉搜索树的性质，转换操作应是原址的，也就是在原始的二叉搜索树上直接修改。
 *
 * 返回转换后的单向链表的头节点。
 *
 * 注意：本题相对原题稍作改动
 *
 * 示例：
 * 输入： [4,2,5,1,3,null,6,0]
 * 输出： [0,null,1,null,2,null,3,null,4,null,5,null,6]
 * 提示：
 * 节点数量不会超过 100000。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/binode-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionInterview17_12 {
  /**
   * Definition for a binary tree node.
   * | class TreeNode(var _value: Int) {
   * |   var value: Int = _value
   * |   var left: TreeNode = null
   * |   var right: TreeNode = null
   * | }
   *
   * 递归方法都不可以，会超过内存限制
   *
   * @param root
   * @return
   */
  def convertBiNode_recursive(root: TreeNode): TreeNode = {
    if (root == null) return null
    var pre: TreeNode = null

    def inOrder(root: TreeNode): TreeNode = {
      var first: TreeNode = root
      if (root.left != null) {
        first = inOrder(root.left)
        root.left = null
      }
      if (pre != null) pre.right = root
      pre = root
      if (root.right != null) inOrder(root.right)
      first
    }

    // inOrder(root)

    def rightInOrder(root: TreeNode): (TreeNode, TreeNode) = {
      var from = root
      var to = root
      if (root.right != null) {
        val tuple = rightInOrder(root.right)
        root.right = tuple._1
        to = tuple._2
      }
      if (root.left != null) {
        val tuple = rightInOrder(root.left)
        tuple._2.right = root
        root.left = null
        from = tuple._1
      }
      (from, to)
    }

    rightInOrder(root)._1
  }

  /**
   * 迭代一样超出内存限制，吐了……
   *
   * @param root
   * @return
   */
  def convertBiNode(root: TreeNode): TreeNode = {
    if (root == null) return null
    val dummy = new TreeNode(0)
    var pre = dummy
    var node = root
    // val stack = new scala.collection.mutable.ListBuffer[TreeNode]
    val stack = new scala.collection.mutable.Stack[TreeNode]
    while (node != null || stack.nonEmpty) {
      if (node != null) {
        // stack prepend node
        stack push node
        node = node.left
      } else {
        // node = stack remove 0
        node = stack.pop()
        node.left = null
        pre.right = node
        pre = node
        node = node.right
      }
    }
    dummy.right
  }

  class TreeNode(var _value: Int) {
    var value: Int = _value
    var left: TreeNode = null
    var right: TreeNode = null
  }
}
