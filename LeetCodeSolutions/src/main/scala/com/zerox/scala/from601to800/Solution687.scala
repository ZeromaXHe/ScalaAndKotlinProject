package com.zerox.scala.from601to800

/**
 * @author zhuxi
 * @since 2022/9/2 9:47
 * @note
 * 687. 最长同值路径 | 难度：中等 | 标签：树、深度优先搜索、二叉树
 * 给定一个二叉树的 root ，返回 最长的路径的长度 ，这个路径中的 每个节点具有相同值 。 这条路径可以经过也可以不经过根节点。
 *
 * 两个节点之间的路径长度 由它们之间的边数表示。
 *
 * 示例 1:
 * 输入：root = [5,4,5,1,1,5]
 * 输出：2
 *
 * 示例 2:
 * 输入：root = [1,4,5,4,4,5]
 * 输出：2
 *
 * 提示:
 * 树的节点数的范围是 [0, 104] 
 * -1000 <= Node.val <= 1000
 * 树的深度将不超过 1000 
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-univalue-path
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution687 {
  /**
   * 执行用时：644 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：57.8 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：71 / 71
   *
   * @param root
   * @return
   */
  def longestUnivaluePath(root: TreeNode): Int = {
    longestUnivaluePathAndRootedPath(root)._1
  }

  private def longestUnivaluePathAndRootedPath(root: TreeNode): (Int, Int) = {
    if (root == null) return (0, 0)
    var path1 = 0
    var path2 = 0
    val leftPaths = longestUnivaluePathAndRootedPath(root.left)
    if (root.left != null) {
      path1 = leftPaths._1
      if (root.left.value == root.value) {
        path2 = leftPaths._2 + 1
        path1 = path1 max (leftPaths._2 + 1)
      }
    }
    val rightPaths = longestUnivaluePathAndRootedPath(root.right)
    if (root.right != null) {
      path1 = path1 max rightPaths._1
      if (root.right.value == root.value) {
        path2 = path2 max (rightPaths._2 + 1)
        path1 = path1 max (rightPaths._2 + 1)
        if (root.left != null && root.left.value == root.right.value) {
          path1 = path1 max (rightPaths._2 + leftPaths._2 + 2)
        }
      }
    }
    (path1, path2)
  }

  class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
    var value: Int = _value
    var left: TreeNode = _left
    var right: TreeNode = _right
  }
}
