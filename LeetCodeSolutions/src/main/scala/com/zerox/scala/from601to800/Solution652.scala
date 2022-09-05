package com.zerox.scala.from601to800

/**
 * @author zhuxi
 * @since 2022/9/5 9:46
 * @note
 * 652. 寻找重复的子树 | 难度：中等 | 标签：树、深度优先搜索、哈希表、二叉树
 * 给定一棵二叉树 root，返回所有重复的子树。
 *
 * 对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
 *
 * 如果两棵树具有相同的结构和相同的结点值，则它们是重复的。
 *
 * 示例 1：
 * 输入：root = [1,2,3,4,null,2,4,null,null,4]
 * 输出：[[2,4],[4]]
 *
 * 示例 2：
 * 输入：root = [2,1,1]
 * 输出：[[1]]
 *
 * 示例 3：
 * 输入：root = [2,2,2,3,null,3,null]
 * 输出：[[2,3],[3]]
 *
 * 提示：
 * 树中的结点数在[1,10^4]范围内。
 * -200 <= Node.val <= 200
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-duplicate-subtrees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution652 {
  /**
   * 执行用时：580 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：56.7 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：176 / 176
   *
   * 参考题解做的
   *
   * @param root
   * @return
   */
  def findDuplicateSubtrees(root: TreeNode): List[TreeNode] = {
    import scala.collection.mutable
    val map = new mutable.HashMap[String, (TreeNode, Int)]
    val set = new mutable.HashSet[TreeNode]
    var idx = 0

    def dfs(node: TreeNode): Int = {
      if (node == null) return 0
      val tri = s"${node.value},${dfs(node.left)},${dfs(node.right)}"
      if (map.contains(tri)) {
        val t = map(tri)
        set.add(t._1)
        t._2
      } else {
        idx += 1
        map(tri) = (node, idx)
        idx
      }
    }

    dfs(root)
    set.toList
  }

  class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
    var value: Int = _value
    var left: TreeNode = _left
    var right: TreeNode = _right
  }
}
