package com.zerox.scala.from401to600

/**
 * @author zhuxi
 * @since 2022/6/22 9:44
 * @note
 * 513. 找树左下角的值 | 难度：中等 | 标签：树、深度优先搜索、广度优先搜索、二叉树
 * 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
 *
 * 假设二叉树中至少有一个节点。
 *
 * 示例 1:
 * 输入: root = [2,1,3]
 * 输出: 1
 *
 * 示例 2:
 * 输入: [1,2,3,4,null,5,6,null,null,7]
 * 输出: 7
 *
 * 提示:
 * 二叉树的节点个数的范围是 [1,104]
 * -231 <= Node.val <= 231 - 1 
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-bottom-left-tree-value
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution513 {
  /**
   * Definition for a binary tree node.
   * | class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
   * |   var value: Int = _value
   * |   var left: TreeNode = _left
   * |   var right: TreeNode = _right
   * | }
   *
   * 执行用时：492 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：55.9 MB, 在所有 Scala 提交中击败了 20.00% 的用户
   * 通过测试用例：76 / 76
   *
   * @param root
   * @return
   */
  def findBottomLeftValue(root: TreeNode): Int = {
    val queue = new scala.collection.mutable.Queue[TreeNode]
    queue enqueue root
    layerTraverse(queue, root).value
  }

  @scala.annotation.tailrec
  private def layerTraverse(queue: scala.collection.mutable.Queue[TreeNode], lastLeft: TreeNode): TreeNode = {
    val size = queue.size
    var first: TreeNode = null
    for (_ <- 0 until size) {
      val dequeue = queue.dequeue
      if (dequeue.left != null) {
        queue enqueue dequeue.left
        if (first == null) {
          first = dequeue.left
        }
      }
      if (dequeue.right != null) {
        queue enqueue dequeue.right
        if (first == null) {
          first = dequeue.right
        }
      }
    }
    if (queue.isEmpty) {
      return lastLeft
    }
    layerTraverse(queue, first)
  }

  class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
    var value: Int = _value
    var left: TreeNode = _left
    var right: TreeNode = _right
  }
}
