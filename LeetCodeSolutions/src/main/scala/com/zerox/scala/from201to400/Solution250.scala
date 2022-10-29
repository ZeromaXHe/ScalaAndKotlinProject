package com.zerox.scala.from201to400

/**
 * @author zhuxi
 * @since 2022/10/29 18:20
 * @note
 * 250. 统计同值子树 | 难度：中等 | 标签：树、深度优先搜索、二叉树
 * 给定一个二叉树，统计该二叉树数值相同的子树个数。
 *
 * 同值子树是指该子树的所有节点都拥有相同的数值。
 *
 * 示例：
 * 输入: root = [5,1,5,5,5,null,5]
 * |              5
 * |             / \
 * |            1   5
 * |           / \   \
 * |          5   5   5
 *
 * 输出: 4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/count-univalue-subtrees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution250 {
  /**
   * 执行用时：496 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：54.7 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：201 / 201
   *
   * @param root
   * @return
   */
  def countUnivalSubtrees(root: TreeNode): Int = {
    countUnivalSubtreesAndIs(root)._1
  }

  def countUnivalSubtreesAndIs(root: TreeNode): (Int, Boolean) = {
    if (root == null) (0, true)
    else if (root.left == null && root.right == null) (1, true)
    else if (root.left == null) {
      val (count, is) = countUnivalSubtreesAndIs(root.right)
      val newIs = is && root.value == root.right.value
      (count + (if (newIs) 1 else 0), newIs)
    } else if (root.right == null) {
      val (count, is) = countUnivalSubtreesAndIs(root.left)
      val newIs = is && root.value == root.left.value
      (count + (if (newIs) 1 else 0), newIs)
    } else {
      val (count1, is1) = countUnivalSubtreesAndIs(root.left)
      val (count2, is2) = countUnivalSubtreesAndIs(root.right)
      val newIs = is1 && is2 && root.value == root.left.value && root.value == root.right.value
      (count1 + count2 + (if (newIs) 1 else 0), newIs)
    }
  }

  class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
    var value: Int = _value
    var left: TreeNode = _left
    var right: TreeNode = _right
  }
}
