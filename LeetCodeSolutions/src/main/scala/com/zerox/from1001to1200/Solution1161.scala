package com.zerox.from1001to1200

/**
 * @author ZeromaXHe
 * @since 2022/7/31 11:39
 * @note
 * 1161. 最大层内元素和 | 难度：中等 | 标签：树、深度优先遍历、广度优先遍历、二叉树
 * 给你一个二叉树的根节点 root。设根节点位于二叉树的第 1 层，而根节点的子节点位于第 2 层，依此类推。
 *
 * 请返回层内元素之和 最大 的那几层（可能只有一层）的层号，并返回其中 最小 的那个。
 *
 * 示例 1：
 * 输入：root = [1,7,0,7,-8,null,null]
 * 输出：2
 * 解释：
 * 第 1 层各元素之和为 1，
 * 第 2 层各元素之和为 7 + 0 = 7，
 * 第 3 层各元素之和为 7 + -8 = -1，
 * 所以我们返回第 2 层的层号，它的层内元素之和最大。
 *
 * 示例 2：
 * 输入：root = [989,null,10250,98693,-89388,null,null,null,-32127]
 * 输出：2
 *
 * 提示：
 * 树中的节点数在 [1, 104]范围内
 * -105 <= Node.val <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-level-sum-of-a-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1161 {
  /**
   * Definition for a binary tree node.
   * | class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
   * |   var value: Int = _value
   * |   var left: TreeNode = _left
   * |   var right: TreeNode = _right
   * | }
   *
   * 执行用时：732 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：62.9 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：40 / 40
   *
   * @param root
   * @return
   */
  def maxLevelSum(root: TreeNode): Int = {
    val queue = new scala.collection.mutable.Queue[TreeNode]
    queue.enqueue(root)
    var layer = 1
    var max = Long.MinValue
    var result = 0
    while (queue.nonEmpty) {
      var sum = 0L
      var count = queue.size
      while (count > 0) {
        val node = queue.dequeue()
        if (node.left != null) queue.enqueue(node.left)
        if (node.right != null) queue.enqueue(node.right)
        sum += node.value
        count -= 1
      }
      if (sum > max) {
        max = sum
        result = layer
      }
      layer += 1
    }
    result
  }

  class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
    var value: Int = _value
    var left: TreeNode = _left
    var right: TreeNode = _right
  }
}
