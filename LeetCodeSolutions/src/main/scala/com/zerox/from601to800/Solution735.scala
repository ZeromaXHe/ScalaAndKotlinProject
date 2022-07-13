package com.zerox.from601to800

/**
 * @author zhuxi
 * @since 2022/7/13 9:50
 * @note
 * 735. 行星碰撞 | 难度：中等 | 标签：栈、数组
 * 给定一个整数数组 asteroids，表示在同一行的行星。
 *
 * 对于数组中的每一个元素，其绝对值表示行星的大小，正负表示行星的移动方向（正表示向右移动，负表示向左移动）。每一颗行星以相同的速度移动。
 *
 * 找出碰撞后剩下的所有行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。
 *
 * 示例 1：
 * 输入：asteroids = [5,10,-5]
 * 输出：[5,10]
 * 解释：10 和 -5 碰撞后只剩下 10 。 5 和 10 永远不会发生碰撞。
 *
 * 示例 2：
 * 输入：asteroids = [8,-8]
 * 输出：[]
 * 解释：8 和 -8 碰撞后，两者都发生爆炸。
 *
 * 示例 3：
 * 输入：asteroids = [10,2,-5]
 * 输出：[10]
 * 解释：2 和 -5 发生碰撞后剩下 -5 。10 和 -5 发生碰撞后剩下 10 。
 *
 * 提示：
 * 2 <= asteroids.length <= 104
 * -1000 <= asteroids[i] <= 1000
 * asteroids[i] != 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/asteroid-collision
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution735 {
  /**
   * 执行用时：588 ms, 在所有 Scala 提交中击败了 50.00% 的用户
   * 内存消耗：57.7 MB, 在所有 Scala 提交中击败了 50.00% 的用户
   * 通过测试用例：275 / 275
   *
   * @param asteroids
   * @return
   */
  def asteroidCollision(asteroids: Array[Int]): Array[Int] = {
    val stack = new scala.collection.mutable.Stack[Int]
    for (a <- asteroids) {
      if (a > 0) stack.push(a)
      else {
        while (stack.nonEmpty && stack.head > 0 && stack.head < -a) stack.pop()
        if (stack.isEmpty || stack.head < 0) stack.push(a)
        else if (stack.nonEmpty && stack.head == -a) stack.pop()
      }
    }
    stack.reverse.toArray
  }
}
