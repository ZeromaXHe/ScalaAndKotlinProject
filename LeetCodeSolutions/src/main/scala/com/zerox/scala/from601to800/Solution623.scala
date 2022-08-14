package com.zerox.scala.from601to800

/**
 * @author zhuxi
 * @since 2022/8/5 11:18
 * @note
 * 623. 在二叉树中增加一行 | 难度：中等 | 标签：树、深度优先搜索、广度优先搜索、二叉树
 * 给定一个二叉树的根 root 和两个整数 val 和 depth ，在给定的深度 depth 处添加一个值为 val 的节点行。
 *
 * 注意，根节点 root 位于深度 1 。
 *
 * 加法规则如下:
 *
 * 给定整数 depth，对于深度为 depth - 1 的每个非空树节点 cur ，创建两个值为 val 的树节点作为 cur 的左子树根和右子树根。
 * cur 原来的左子树应该是新的左子树根的左子树。
 * cur 原来的右子树应该是新的右子树根的右子树。
 * 如果 depth == 1 意味着 depth - 1 根本没有深度，那么创建一个树节点，值 val 作为整个原始树的新根，而原始树就是新根的左子树。
 *
 * 示例 1:
 * 输入: root = [4,2,6,3,1,5], val = 1, depth = 2
 * 输出: [4,1,1,2,null,null,6,3,1,5]
 *
 * 示例 2:
 * 输入: root = [4,2,null,3,1], val = 1, depth = 3
 * 输出:  [4,2,null,1,1,3,null,null,1]
 *
 * 提示:
 * 节点数在 [1, 104] 范围内
 * 树的深度在 [1, 104]范围内
 * -100 <= Node.val <= 100
 * -105 <= val <= 105
 * 1 <= depth <= the depth of tree + 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/add-one-row-to-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution623 {
  /**
   * Definition for a binary tree node.
   * | class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
   * |   var value: Int = _value
   * |   var left: TreeNode = _left
   * |   var right: TreeNode = _right
   * | }
   *
   * 执行用时：676 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：56 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：109 / 109
   *
   * @param root
   * @param `val`
   * @param depth
   * @return
   */
  def addOneRow(root: TreeNode, `val`: Int, depth: Int): TreeNode = {
    if (depth == 1) return new TreeNode(`val`, root, null)
    val queue = new scala.collection.mutable.Queue[TreeNode]
    queue += root
    var deep = 2
    while (deep < depth) {
      var size = queue.size
      while (size > 0) {
        val node = queue.dequeue()
        if (node.left != null) queue.enqueue(node.left)
        if (node.right != null) queue.enqueue(node.right)
        size -= 1
      }
      deep += 1
    }
    for (node <- queue) {
      node.left = new TreeNode(`val`, node.left, null)
      node.right = new TreeNode(`val`, null, node.right)
    }
    root
  }

  class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
    var value: Int = _value
    var left: TreeNode = _left
    var right: TreeNode = _right
  }
}
