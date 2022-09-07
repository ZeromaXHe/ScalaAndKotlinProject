package com.zerox.scala.from201to400

import com.zerox.scala.aider.DynamicProgrammingUtils.{cachedArray2D, cachedHash}

/**
 * @author zhuxi
 * @since 2022/9/7 14:05
 * @note
 * 312. 戳气球 | 难度：困难 | 标签：数组、动态规划
 * 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 *
 * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
 *
 * 求所能获得硬币的最大数量。
 *
 * 示例 1：
 * 输入：nums = [3,1,5,8]
 * 输出：167
 * 解释：
 * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
 * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
 *
 * 示例 2：
 * 输入：nums = [1,5]
 * 输出：10
 *
 * 提示：
 * n == nums.length
 * 1 <= n <= 300
 * 0 <= nums[i] <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/burst-balloons
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution312 {
  def main(args: Array[String]): Unit = {
    println(maxCoins(Array(3, 1, 5, 8))) // 167
  }

  /**
   * 执行用时：3120 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：61.3 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：73 / 73
   *
   * @param nums
   * @return
   */
  def maxCoins(nums: Array[Int]): Int = {
    val n = nums.length
    val score = Array.tabulate(n + 2)(i => if (i == 0 || i == n + 1) 1 else nums(i - 1))
    import scala.collection.mutable
    val cache = new mutable.HashMap[(Int, Int), Int]
    lazy val solve: ((Int, Int)) => Int = cachedHash[(Int, Int), Int](cache, _,
      t => {
        val (left, right) = t
        var max = 0
        for (i <- left + 1 until right) {
          var sum = score(left) * score(i) * score(right)
          sum += solve((left, i)) + solve((i, right))
          max = math.max(max, sum)
        }
        max
      },
      noCacheCondition = t => t._1 >= t._2 - 1,
      noCacheGenerate = _ => 0)
    solve((0, n + 1))
  }

  /**
   * 执行用时：1024 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：55 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：73 / 73
   *
   * @param nums
   * @return
   */
  def maxCoins_arrTemplate(nums: Array[Int]): Int = {
    val n = nums.length
    val score = Array.tabulate(n + 2)(i => if (i == 0 || i == n + 1) 1 else nums(i - 1))
    val cache = Array.fill(n + 2)(Array.fill(n + 2)(-1))
    lazy val solve: (Int, Int) => Int = cachedArray2D[Int](cache, _, _,
      (left, right) => {
        var max = 0
        for (i <- left + 1 until right) {
          var sum = score(left) * score(i) * score(right)
          sum += solve(left, i) + solve(i, right)
          max = math.max(max, sum)
        }
        max
      },
      -1,
      noCacheCondition = (left, right) => left >= right - 1,
      noCacheGenerate = (_, _) => 0)
    solve(0, n + 1)
  }

  /**
   * 执行用时：2636 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：61.5 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：73 / 73
   *
   * @param nums
   * @return
   */
  def maxCoins_hashCache(nums: Array[Int]): Int = {
    val n = nums.length
    val score = Array.tabulate(n + 2)(i => if (i == 0 || i == n + 1) 1 else nums(i - 1))
    import scala.collection.mutable
    val cache = new mutable.HashMap[(Int, Int), Int]

    def solve(t: (Int, Int)): Int = {
      val (left, right) = t
      if (left >= right - 1) return 0
      cache.getOrElseUpdate(t, {
        var max = 0
        for (i <- left + 1 until right) {
          var sum = score(left) * score(i) * score(right)
          sum += solve(left, i) + solve(i, right)
          max = math.max(max, sum)
        }
        max
      })
    }

    solve((0, n + 1))
  }

  /**
   * 执行用时：620 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：53.3 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：73 / 73
   *
   * @param nums
   * @return
   */
  def maxCoins_arrayCache(nums: Array[Int]): Int = {
    val n = nums.length
    val value = Array.tabulate(n + 2)(i => if (i == 0 || i == n + 1) 1 else nums(i - 1))
    val rec = Array.fill(n + 2)(Array.fill(n + 2)(-1))

    def solve(left: Int, right: Int): Int = {
      if (left >= right - 1) 0
      else {
        if (rec(left)(right) == -1) {
          for (i <- left + 1 until right) {
            var sum = value(left) * value(i) * value(right)
            sum += solve(left, i) + solve(i, right)
            rec(left)(right) = math.max(rec(left)(right), sum)
          }
        }
        rec(left)(right)
      }
    }

    solve(0, n + 1)
  }
}
