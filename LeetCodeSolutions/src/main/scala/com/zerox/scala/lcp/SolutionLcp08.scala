package com.zerox.scala.lcp

/**
 * @author zhuxi
 * @since 2022/7/21 11:29
 * @note
 * LCP 08. 剧情触发时间 | 难度：中等 | 标签：数组、二分查找、排序
 * 在战略游戏中，玩家往往需要发展自己的势力来触发各种新的剧情。
 * 一个势力的主要属性有三种，分别是文明等级（C），资源储备（R）以及人口数量（H）。在游戏开始时（第 0 天），三种属性的值均为 0。
 *
 * 随着游戏进程的进行，每一天玩家的三种属性都会对应增加，我们用一个二维数组 increase 来表示每天的增加情况。
 * 这个二维数组的每个元素是一个长度为 3 的一维数组，例如 [[1,2,1],[3,4,2]] 表示第一天三种属性分别增加 1,2,1 而第二天分别增加 3,4,2。
 *
 * 所有剧情的触发条件也用一个二维数组 requirements 表示。
 * 这个二维数组的每个元素是一个长度为 3 的一维数组，对于某个剧情的触发条件 c[i], r[i], h[i]，如果当前 C >= c[i] 且 R >= r[i] 且 H >= h[i] ，则剧情会被触发。
 *
 * 根据所给信息，请计算每个剧情的触发时间，并以一个数组返回。如果某个剧情不会被触发，则该剧情对应的触发时间为 -1 。
 *
 * 示例 1：
 * 输入： increase = [[2,8,4],[2,5,0],[10,9,8]] requirements = [[2,11,3],[15,10,7],[9,17,12],[8,1,14]]
 * 输出: [2,-1,3,-1]
 * 解释：
 * 初始时，C = 0，R = 0，H = 0
 * 第 1 天，C = 2，R = 8，H = 4
 * 第 2 天，C = 4，R = 13，H = 4，此时触发剧情 0
 * 第 3 天，C = 14，R = 22，H = 12，此时触发剧情 2
 * 剧情 1 和 3 无法触发。
 *
 * 示例 2：
 * 输入： increase = [[0,4,5],[4,8,8],[8,6,1],[10,10,0]] requirements = [[12,11,16],[20,2,6],[9,2,6],[10,18,3],[8,14,9]]
 * 输出: [-1,4,3,3,3]
 *
 * 示例 3：
 * 输入： increase = [[1,1,1]] requirements = [[0,0,0]]
 * 输出: [0]
 *
 * 限制：
 * 1 <= increase.length <= 10000
 * 1 <= requirements.length <= 100000
 * 0 <= increase[i] <= 10
 * 0 <= requirements[i] <= 100000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/ju-qing-hong-fa-shi-jian
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionLcp08 {
  def main(args: Array[String]): Unit = {
    println(getTriggerTime(Array(Array(2, 8, 4), Array(2, 5, 0), Array(10, 9, 8)),
      Array(Array(2, 11, 3), Array(15, 10, 7), Array(9, 17, 12), Array(8, 1, 14))).mkString("Array(", ", ", ")"))
    println(getTriggerTime(Array(Array(1, 1, 1)),
      Array(Array(0, 0, 0))).mkString("Array(", ", ", ")"))
  }

  /**
   * 执行用时：2228 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：139.5 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：105 / 105
   *
   * @param increase
   * @param requirements
   * @return
   */
  def getTriggerTime_transpose(increase: Array[Array[Int]], requirements: Array[Array[Int]]): Array[Int] = {
    (for (i <- 0 until 3) yield {
      val n = requirements.length
      val result = Array.fill(n)(Int.MaxValue)
      val sorted = requirements.map(_ (i)).zipWithIndex.sorted
      var sum = 0
      var index = 0
      while (index < n && sorted(index)._1 == 0) {
        result(sorted(index)._2) = 0
        index += 1
      }
      for (j <- increase.indices if index < n) {
        sum += increase(j)(i)
        while (index < n && sum >= sorted(index)._1) {
          result(sorted(index)._2) = j + 1
          index += 1
        }
      }
      result
    }).transpose.map(arr => {
      val finish = arr.max
      if (finish == Int.MaxValue) -1
      else finish
    }).toArray
  }

  /**
   * 执行用时：2100 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：146.7 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：105 / 105
   *
   * @param increase
   * @param requirements
   * @return
   */
  def getTriggerTime(increase: Array[Array[Int]], requirements: Array[Array[Int]]): Array[Int] = {
    val n = requirements.length
    val result = Array.fill(3)(Array.fill(n)(Int.MaxValue))
    for (i <- 0 until 3) {
      val sorted = requirements.map(_ (i)).zipWithIndex.sorted
      var sum = 0
      var index = 0
      while (index < n && sorted(index)._1 == 0) {
        result(i)(sorted(index)._2) = 0
        index += 1
      }
      for (j <- increase.indices if index < n) {
        sum += increase(j)(i)
        while (index < n && sum >= sorted(index)._1) {
          result(i)(sorted(index)._2) = j + 1
          index += 1
        }
      }
    }
    (for (i <- 0 until n) yield {
      val max = math.max(math.max(result(0)(i), result(1)(i)), result(2)(i))
      if (max == Int.MaxValue) -1 else max
    }).toArray
  }
}
