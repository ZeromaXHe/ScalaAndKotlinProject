package com.zerox.scala.from601to800

/**
 * @author ZeromaXHe
 * @since 2022/8/27 15:45
 * @note
 * 662. 二叉树最大宽度 | 难度：中等 | 标签：树、深度优先搜索、广度优先搜索、二叉树
 * 给你一棵二叉树的根节点 root ，返回树的 最大宽度 。
 *
 * 树的 最大宽度 是所有层中最大的 宽度 。
 *
 * 每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的 null 节点，这些 null 节点也计入长度。
 *
 * 题目数据保证答案将会在  32 位 带符号整数范围内。
 *
 * 示例 1：
 * 输入：root = [1,3,2,5,3,null,9]
 * 输出：4
 * 解释：最大宽度出现在树的第 3 层，宽度为 4 (5,3,null,9) 。
 *
 * 示例 2：
 * 输入：root = [1,3,2,5,null,null,9,6,null,7]
 * 输出：7
 * 解释：最大宽度出现在树的第 4 层，宽度为 7 (6,null,null,null,null,null,7) 。
 *
 * 示例 3：
 * 输入：root = [1,3,2,5]
 * 输出：2
 * 解释：最大宽度出现在树的第 2 层，宽度为 2 (3,2) 。
 *
 * 提示：
 * 树中节点的数目范围是 [1, 3000]
 * -100 <= Node.val <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-width-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution662 {
  /**
   * 执行用时：520 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：55.6 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：114 / 114
   *
   * @param root
   * @return
   */
  def widthOfBinaryTree(root: TreeNode): Int = {
    val queue = new scala.collection.mutable.Queue[(TreeNode, Int)]
    queue.enqueue((root, 1))
    var width = 1
    while (queue.nonEmpty) {
      var size = queue.size
      var min = -1
      var max = -1
      while (size > 0) {
        val (node, i) = queue.dequeue()
        if (min == -1) min = i
        max = i
        if (node.left != null) queue.enqueue((node.left, 2 * i))
        if (node.right != null) queue.enqueue((node.right, 2 * i + 1))
        size -= 1
        if (max - min + 1 > width) width = max - min + 1
      }
    }
    width
  }

  class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
    var value: Int = _value
    var left: TreeNode = _left
    var right: TreeNode = _right
  }
}
