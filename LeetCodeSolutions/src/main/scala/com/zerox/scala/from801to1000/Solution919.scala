package com.zerox.scala.from801to1000

/**
 * @author zhuxi
 * @since 2022/7/25 10:20
 * @note
 * 919. 完全二叉树插入器 | 难度：中等 | 标签：树、广度优先搜索、设计、二叉树
 * 完全二叉树 是每一层（除最后一层外）都是完全填充（即，节点数达到最大）的，并且所有的节点都尽可能地集中在左侧。
 *
 * 设计一种算法，将一个新节点插入到一个完整的二叉树中，并在插入后保持其完整。
 *
 * 实现 CBTInserter 类:
 *
 * CBTInserter(TreeNode root) 使用头节点为 root 的给定树初始化该数据结构；
 * CBTInserter.insert(int v)  向树中插入一个值为 Node.val == val的新节点 TreeNode。使树保持完全二叉树的状态，并返回插入节点 TreeNode 的父节点的值；
 * CBTInserter.get_root() 将返回树的头节点。
 *
 * 示例 1：
 * 输入
 * ["CBTInserter", "insert", "insert", "get_root"]
 * [[[1, 2]], [3], [4], []]
 * 输出
 * [null, 1, 2, [1, 2, 3, 4]]
 *
 * 解释
 * CBTInserter cBTInserter = new CBTInserter([1, 2]);
 * cBTInserter.insert(3);  // 返回 1
 * cBTInserter.insert(4);  // 返回 2
 * cBTInserter.get_root(); // 返回 [1, 2, 3, 4]
 *
 * 提示：
 * 树中节点数量范围为 [1, 1000] 
 * 0 <= Node.val <= 5000
 * root 是完全二叉树
 * 0 <= val <= 5000 
 * 每个测试用例最多调用 insert 和 get_root 操作 104 次
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/complete-binary-tree-inserter
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution919 {
  def main(args: Array[String]): Unit = {
    val root = new TreeNode(1)
    root.left = new TreeNode(2)
    val inserter = new CBTInserter(root)
    println(inserter.insert(3))
    println(inserter.insert(4))
    println(inserter.get_root())

    val root2 = new TreeNode(1)
    root2.left = new TreeNode(2)
    root2.right = new TreeNode(3)
    root2.left.left = new TreeNode(4)
    root2.left.right = new TreeNode(5)
    root2.right.left = new TreeNode(6)
    val inserter2 = new CBTInserter(root)
    println(inserter2.insert(7))
    println(inserter2.insert(8))
    println(inserter2.get_root())
  }

  /**
   * Definition for a binary tree node.
   * | class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
   * |   var value: Int = _value
   * |   var left: TreeNode = _left
   * |   var right: TreeNode = _right
   * | }
   *
   * 执行用时：744 ms, 在所有 Scala 提交中击败了 40.00% 的用户
   * 内存消耗：59.1 MB, 在所有 Scala 提交中击败了 20.00% 的用户
   * 通过测试用例：84 / 84
   *
   * @param _root
   */
  class CBTInserter_caseClass(_root: TreeNode) {
    case class TreeNodeWithParent(var node: TreeNode, parent: TreeNode) {
      var left: TreeNodeWithParent = null
      var right: TreeNodeWithParent = null
      private val leftNode = node.left
      if (leftNode != null) {
        left = TreeNodeWithParent(leftNode, node)
      }
      private val rightNode = node.right
      if (rightNode != null) {
        right = TreeNodeWithParent(rightNode, node)
      }
    }

    val root = TreeNodeWithParent(_root, null)
    var preSize = 1
    var nextSize = 2
    val queue = new scala.collection.mutable.Queue[TreeNodeWithParent]
    queue += root
    while (queue.forall(node => node.left != null && node.right != null)) {
      for (_ <- queue.indices) {
        val deq = queue.dequeue()
        queue.enqueue(deq.left)
        queue.enqueue(deq.right)
      }
      preSize *= 2
      nextSize *= 2
    }
    for (i <- queue.indices) {
      val node = queue(i)
      if (node.left != null) queue.enqueue(node.left)
      if (node.right != null) queue.enqueue(node.right)
    }

    def insert(`val`: Int): Int = {
      if (queue.size == preSize + nextSize) {
        for (_ <- 0 until preSize) queue.dequeue()
        preSize *= 2
        nextSize *= 2
      }
      val parent = queue((queue.size - preSize) / 2)
      val newNode = new TreeNode(`val`)
      val newNodeWithParent = TreeNodeWithParent(newNode, parent.node)
      if ((queue.size - preSize) % 2 == 0) {
        parent.left = newNodeWithParent
        parent.node.left = newNode
      } else {
        parent.right = newNodeWithParent
        parent.node.right = newNode
      }
      queue enqueue newNodeWithParent
      parent.node.value
    }

    def get_root(): TreeNode = {
      root.node
    }

  }

  /**
   * 执行用时：780 ms, 在所有 Scala 提交中击败了 20.00% 的用户
   * 内存消耗：58.8 MB, 在所有 Scala 提交中击败了 20.00% 的用户
   * 通过测试用例：84 / 84
   *
   * @param _root
   */
  class CBTInserter(_root: TreeNode) {
    var preSize = 1
    var nextSize = 2
    val queue = new scala.collection.mutable.Queue[TreeNode]
    queue += _root
    while (queue.forall(node => node.left != null && node.right != null)) {
      for (_ <- queue.indices) {
        val deq = queue.dequeue()
        queue.enqueue(deq.left)
        queue.enqueue(deq.right)
      }
      preSize *= 2
      nextSize *= 2
    }
    for (i <- queue.indices) {
      val node = queue(i)
      if (node.left != null) queue.enqueue(node.left)
      if (node.right != null) queue.enqueue(node.right)
    }

    def insert(`val`: Int): Int = {
      if (queue.size == preSize + nextSize) {
        for (_ <- 0 until preSize) queue.dequeue()
        preSize *= 2
        nextSize *= 2
      }
      val parent = queue((queue.size - preSize) / 2)
      val newNode = new TreeNode(`val`)
      if ((queue.size - preSize) % 2 == 0) {
        parent.left = newNode
      } else {
        parent.right = newNode
      }
      queue enqueue newNode
      parent.value
    }

    def get_root(): TreeNode = {
      _root
    }
  }

  /**
   * Your CBTInserter object will be instantiated and called as such:
   * var obj = new CBTInserter(root)
   * var param_1 = obj.insert(`val`)
   * var param_2 = obj.get_root()
   */

  class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
    var value: Int = _value
    var left: TreeNode = _left
    var right: TreeNode = _right
  }
}
