package com.zerox.from1to200

import com.zerox.offer.SolutionOffer07
import com.zerox.offer.SolutionOffer07.TreeNode

/**
 * @author zhuxi
 * @since 2022/7/15 16:28
 * @note
 * 105. 从前序与中序遍历序列构造二叉树 | 难度：中等 | 标签：树、数组、哈希表、分治、二叉树
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 *
 * 示例 1:
 * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * 输出: [3,9,20,null,null,15,7]
 *
 * 示例 2:
 * 输入: preorder = [-1], inorder = [-1]
 * 输出: [-1]
 *
 * 提示:
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder 和 inorder 均 无重复 元素
 * inorder 均出现在 preorder
 * preorder 保证 为二叉树的前序遍历序列
 * inorder 保证 为二叉树的中序遍历序列
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution105 {
  /**
   * Definition for a binary tree node.
   * | class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
   * |   var value: Int = _value
   * |   var left: TreeNode = _left
   * |   var right: TreeNode = _right
   * | }
   *
   * 执行用时：624 ms, 在所有 Scala 提交中击败了 84.62% 的用户
   * 内存消耗：55.2 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：203 / 203
   *
   * @param preorder
   * @param inorder
   * @return
   */
  def buildTree_zipWithIndex(preorder: Array[Int], inorder: Array[Int]): TreeNode = {
    SolutionOffer07.buildTree(preorder, inorder)
  }

  /**
   * 执行用时：644 ms, 在所有 Scala 提交中击败了 69.23% 的用户
   * 内存消耗：56.2 MB, 在所有 Scala 提交中击败了 84.62% 的用户
   * 通过测试用例：203 / 203
   *
   * @param preorder
   * @param inorder
   * @return
   */
  def buildTree(preorder: Array[Int], inorder: Array[Int]): TreeNode = {
    val map = inorder.indices.map(i => inorder(i) -> i).toMap
    SolutionOffer07.buildTree(preorder, 0, preorder.length, inorder, 0, inorder.length, map)
  }
}
