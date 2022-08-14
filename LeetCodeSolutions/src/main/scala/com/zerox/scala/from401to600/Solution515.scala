package com.zerox.scala.from401to600

/**
 * @author ZeromaXHe
 * @since 2022/6/24 0:13
 * @note
 * 515. 在每个树行中找最大值 | 难度：中等 | 标签：树、深度优先搜索、广度优先搜索、二叉树
 * 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
 *
 * 示例1：
 * 输入: root = [1,3,2,5,3,null,9]
 * 输出: [1,3,9]
 *
 * 示例2：
 * 输入: root = [1,2,3]
 * 输出: [1,3]
 *
 * 提示：
 * 二叉树的节点个数的范围是 [0,104]
 * -231 <= Node.val <= 231 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-largest-value-in-each-tree-row
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution515 {
  /**
   * Definition for a binary tree node.
   * | class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
   * |   var value: Int = _value
   * |   var left: TreeNode = _left
   * |   var right: TreeNode = _right
   * | }
   *
   * 执行用时：544 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：55.9 MB, 在所有 Scala 提交中击败了 50.00% 的用户
   * 通过测试用例：78 / 78
   *
   * @param root
   * @return
   */
  def largestValues(root: TreeNode): List[Int] = {
    if (root == null) {
      return List.empty
    }
    val queue = new scala.collection.mutable.Queue[TreeNode]
    queue enqueue root
    val result = new scala.collection.mutable.ListBuffer[Int]
    while (queue.nonEmpty) {
      val size: Int = queue.size
      var max: Int = Integer.MIN_VALUE
      for (_ <- 0 until size) {
        val poll: TreeNode = queue.dequeue()
        if (poll.value > max) {
          max = poll.value
        }
        if (poll.left != null) {
          queue.enqueue(poll.left)
        }
        if (poll.right != null) {
          queue.enqueue(poll.right)
        }
      }
      result += max
    }
    result.toList
  }

  class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
    var value: Int = _value
    var left: TreeNode = _left
    var right: TreeNode = _right
  }
}
