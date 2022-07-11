package com.zerox.from401to600

/**
 * @author ZeromaXHe
 * @since 2022/6/19 15:13
 * @note
 * 508. 出现次数最多的子树元素和 | 难度：中等 | 标签：树、深度优先搜索、哈希表、二叉树
 * 给你一个二叉树的根结点 root ，请返回出现次数最多的子树元素和。如果有多个元素出现的次数相同，返回所有出现次数最多的子树元素和（不限顺序）。
 *
 * 一个结点的 「子树元素和」 定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。
 *
 * 示例 1：
 * 输入: root = [5,2,-3]
 * 输出: [2,-3,4]
 *
 * 示例 2：
 * 输入: root = [5,2,-5]
 * 输出: [2]
 *
 * 提示:
 * 节点数在 [1, 104] 范围内
 * -105 <= Node.val <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/most-frequent-subtree-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution508 {
  /**
   * 执行用时：588 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：56.1 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例： 58 / 58
   *
   * @param root
   * @return
   */
  def findFrequentTreeSum(root: TreeNode): Array[Int] = {
    val countMap = new scala.collection.mutable.HashMap[Int, Int]
    traverseAndCount(root, countMap)
    val max = countMap.values.max
    countMap.filter(x => x._2 == max).keys.toArray
  }

  def traverseAndCount(root: TreeNode, map: scala.collection.mutable.HashMap[Int, Int]): Int = {
    var sum = root.value
    if (root.left != null) {
      sum += traverseAndCount(root.left, map)
    }
    if (root.right != null) {
      sum += traverseAndCount(root.right, map)
    }
    map(sum) = map.getOrElse(sum, 0) + 1
    sum
  }

  /**
   * Definition for a binary tree node.
   * | class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
   * |   var value: Int = _value
   * |   var left: TreeNode = _left
   * |   var right: TreeNode = _right
   * | }
   *
   * 执行用时：744 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：58.6 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例： 58 / 58
   */
  def findFrequentTreeSum2(root: TreeNode): Array[Int] = {
    val map = new scala.collection.mutable.HashMap[TreeNode, Int]
    traverse(root, map)
    val countMap = map.values.groupBy(identity).mapValues(_.size)
    val max = countMap.values.max
    countMap.filter(x => x._2 == max).keys.toArray
  }

  def traverse(root: TreeNode, map: scala.collection.mutable.HashMap[TreeNode, Int]): Int = {
    var sum = root.value
    if (root.left != null) {
      sum += map.getOrElseUpdate(root.left, traverse(root.left, map))
    }
    if (root.right != null) {
      sum += map.getOrElseUpdate(root.right, traverse(root.right, map))
    }
    map.put(root, sum)
    sum
  }

  class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
    var value: Int = _value
    var left: TreeNode = _left
    var right: TreeNode = _right
  }
}
