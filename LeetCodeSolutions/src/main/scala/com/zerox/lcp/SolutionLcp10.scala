package com.zerox.lcp

/**
 * @author zhuxi
 * @since 2022/7/21 17:03
 * @note
 * LCP 10. 二叉树任务调度 | 难度：困难 | 标签：
 * 任务调度优化是计算机性能优化的关键任务之一。在任务众多时，不同的调度策略可能会得到不同的总体执行时间，因此寻求一个最优的调度方案是非常有必要的。
 *
 * 通常任务之间是存在依赖关系的，即对于某个任务，你需要先完成他的前导任务（如果非空），才能开始执行该任务。
 * 我们保证任务的依赖关系是一棵二叉树，其中 root 为根任务，root.left 和 root.right 为他的两个前导任务（可能为空），root.val 为其自身的执行时间。
 *
 * 在一个 CPU 核执行某个任务时，我们可以在任何时刻暂停当前任务的执行，并保留当前执行进度。在下次继续执行该任务时，会从之前停留的进度开始继续执行。暂停的时间可以不是整数。
 *
 * 现在，系统有两个 CPU 核，即我们可以同时执行两个任务，但是同一个任务不能同时在两个核上执行。给定这颗任务树，请求出所有任务执行完毕的最小时间。
 *
 * 示例 1：
 * 输入：root = [47, 74, 31]
 * 输出：121
 * 解释：根节点的左右节点可以并行执行31分钟，剩下的43+47分钟只能串行执行，因此总体执行时间是121分钟。
 *
 * 示例 2：
 * 输入：root = [15, 21, null, 24, null, 27, 26]
 * 输出：87
 *
 * 示例 3：
 * 输入：root = [1,3,2,null,null,4,4]
 * 输出：7.5
 *
 * 限制：
 * 1 <= 节点数量 <= 1000
 * 1 <= 单节点执行时间 <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/er-cha-shu-ren-wu-diao-du
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionLcp10 {

  /**
   * Definition for a binary tree node.
   * | class TreeNode(var _value: Int) {
   * |   var value: Int = _value
   * |   var left: TreeNode = null
   * |   var right: TreeNode = null
   * | }
   *
   * 执行用时：600 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：55.7 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：123 / 123
   *
   * @param root
   * @return
   */
  def minimalExecTime(root: TreeNode): Double = {
    val t = maxmalParallelTime(root)
    t._1 + t._2
  }

  def maxmalParallelTime(root: TreeNode): (Double, Double) = {
    if (root == null) return (0, 0)
    val (p1, s1) = maxmalParallelTime(root.left)
    val (p2, s2) = maxmalParallelTime(root.right)
    val ps = math.min(s1, s2)
    // s1s 表示 s1 在与 s2 并行后剩下的串行部分，s2s 类似。两者中只有一个非零
    val s1s = s1 - ps
    val s2s = s2 - ps
    // ps1s 表示 s1s 再与 p2 并行执行的并行时间，ps2s 类似。
    val ps1s = math.min(p2 * 2, s1s)
    val ps2s = math.min(p1 * 2, s2s)
    // 以上尝试并行之后依然串行的时间
    val ss = s1s - ps1s + s2s - ps2s
    (p1 + p2 + ps + (ps1s + ps2s) / 2.0, ss + root.value)
  }

  class TreeNode(var _value: Int) {
    var value: Int = _value
    var left: TreeNode = null
    var right: TreeNode = null
  }
}
