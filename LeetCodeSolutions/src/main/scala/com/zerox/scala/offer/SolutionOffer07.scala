package com.zerox.scala.offer

/**
 * @author zhuxi
 * @since 2022/7/15 15:53
 * @note
 * 剑指 Offer 07. 重建二叉树 | 难度：中等 | 标签：树、数组、哈希表、分治、二叉树
 * 输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。
 *
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 *
 * 示例 1:
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 *
 * 示例 2:
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 *
 * 限制：
 * 0 <= 节点个数 <= 5000
 *
 * 注意：本题与主站 105 题重复：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/zhong-jian-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionOffer07 {
  /**
   * Definition for a binary tree node.
   * | class TreeNode(var _value: Int) {
   * |   var value: Int = _value
   * |   var left: TreeNode = null
   * |   var right: TreeNode = null
   * | }
   *
   * 执行用时：712 ms, 在所有 Scala 提交中击败了 50.00% 的用户
   * 内存消耗：56.1 MB, 在所有 Scala 提交中击败了 50.00% 的用户
   * 通过测试用例：203 / 203
   *
   * @param preorder
   * @param inorder
   * @return
   */
  def buildTree_withoutHashMap(preorder: Array[Int], inorder: Array[Int]): TreeNode = {
    buildTree_withoutHashMap(preorder, 0, preorder.length, inorder, 0, inorder.length)
  }

  def buildTree_withoutHashMap(preorder: Array[Int], fromPre: Int, toPre: Int,
                               inorder: Array[Int], fromIn: Int, toIn: Int): TreeNode = {
    if (toPre == fromPre) return null
    val root = new TreeNode(preorder(fromPre))
    val index = (fromIn until toIn).find(preorder(fromPre) == inorder(_)).get
    root.left = buildTree_withoutHashMap(preorder, fromPre + 1, fromPre + 1 + index - fromIn, inorder, fromIn, index)
    root.right = buildTree_withoutHashMap(preorder, fromPre + 1 + index - fromIn, toPre, inorder, index + 1, toIn)
    root
  }

  /**
   * 执行用时：668 ms, 在所有 Scala 提交中击败了 50.00% 的用户
   * 内存消耗：56.1 MB, 在所有 Scala 提交中击败了 50.00% 的用户
   * 通过测试用例：203 / 203
   *
   * @param preorder
   * @param inorder
   * @return
   */
  def buildTree(preorder: Array[Int], inorder: Array[Int]): TreeNode = {
    val map = inorder.zipWithIndex.toMap
    buildTree(preorder, 0, preorder.length, inorder, 0, inorder.length, map)
  }

  def buildTree(preorder: Array[Int], fromPre: Int, toPre: Int,
                inorder: Array[Int], fromIn: Int, toIn: Int, map: Map[Int, Int]): TreeNode = {
    if (toPre == fromPre) return null
    val root = new TreeNode(preorder(fromPre))
    val index = map(preorder(fromPre))
    root.left = buildTree(preorder, fromPre + 1, fromPre + 1 + index - fromIn, inorder, fromIn, index, map)
    root.right = buildTree(preorder, fromPre + 1 + index - fromIn, toPre, inorder, index + 1, toIn, map)
    root
  }

  class TreeNode(var _value: Int) {
    var value: Int = _value
    var left: TreeNode = null
    var right: TreeNode = null
  }
}
