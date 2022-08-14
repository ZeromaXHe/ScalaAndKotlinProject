package com.zerox.scala.contest

/**
 * @author ZeromaXHe
 * @since 2022/7/10 10:19
 * @note
 * 第 301 场周赛
 */
object WeeklyContest301 {
  /**
   * 6112. 装满杯子需要的最短总时长 | 难度：简单
   * 279 / 279 个通过测试用例
   * 状态：通过
   * 执行用时: 472 ms
   * 内存消耗: 52.8 MB
   *
   * @param amount
   * @return
   */
  def fillCups(amount: Array[Int]): Int = {
    val sorted = amount.sorted
    if (sorted(0) + sorted(1) <= sorted(2)) {
      return sorted(2)
    }
    1 + fillCups(Array(sorted(0), sorted(1) - 1, sorted(2) - 1))
  }

  /**
   * 6113. 无限集中的最小数字 | 难度：中等
   * 135 / 135 个通过测试用例
   * 状态：通过
   * 执行用时: 652 ms
   * 内存消耗: 56.3 MB
   */
  class SmallestInfiniteSet() {
    private val treeSet = new scala.collection.mutable.TreeSet[Int]
    private var min = 1

    def popSmallest(): Int = {
      if (treeSet.nonEmpty) {
        val head = treeSet.head
        treeSet remove head
        head
      } else {
        min += 1
        min - 1
      }
    }

    def addBack(num: Int) {
      if (num < min) {
        treeSet += num
      }
    }

  }

  /**
   * 6114. 移动片段得到字符串 | 难度：中等
   * 103 / 103 个通过测试用例
   * 状态：通过
   * 执行用时: 1432 ms
   * 内存消耗: 69.2 MB
   *
   * @param start
   * @param target
   * @return
   */
  def canChange(start: String, target: String): Boolean = {
    val startL = start.zipWithIndex.filter(_._1 == 'L').map(_._2).toArray
    val startR = start.zipWithIndex.filter(_._1 == 'R').map(_._2).toArray
    val targetL = target.zipWithIndex.filter(_._1 == 'L').map(_._2).toArray
    val targetR = target.zipWithIndex.filter(_._1 == 'R').map(_._2).toArray
    if (startL.length != targetL.length || startR.length != targetR.length) return false
    if (startL.length == 0 && startR.length == 0) return true
    val zipL = targetL zip startL
    val zipR = startR zip targetR
    if (zipL.exists(t => t._1 > t._2) || zipR.exists(t => t._1 > t._2)) return false
    if (zipR.length == 0 || zipL.length == 0) return true
    var i = 0
    var j = 0
    while (i < zipL.length && j < zipR.length) {
      val lo = math.max(zipL(i)._1, zipR(j)._1)
      val hi = math.min(zipL(i)._2, zipR(j)._2)
      if (lo <= hi) return false
      if (zipL(i)._2 < zipR(j)._2) i += 1
      else j += 1
    }
    true
  }

  /**
   * 6115. 统计理想数组的数目 | 难度：困难
   * 未完成：超时、超过内存限制
   *
   * @param n
   * @param maxValue
   * @return
   */
  def idealArrays(n: Int, maxValue: Int): Int = {
    val MOD = (1e9 + 7).toInt
    val dp = Array.fill(n) {
      new Array[Int](maxValue + 1)
    }
    for (i <- 1 to maxValue) {
      dp(0)(i) = 1
    }
    val map = (for (j <- 1 to maxValue) yield (j, (for (k <- 1 to j if j % k == 0) yield k).toList)).toMap

    for (i <- 1 until n;
         j <- 1 to maxValue;
         k <- map(j)) {
      dp(i)(j) = (dp(i - 1)(k) + dp(i)(j)) % MOD
    }
    var sum = 0
    dp(n - 1).foreach(i => sum = (sum + i) % MOD)
    sum
  }

  def main(args: Array[String]): Unit = {
    println(idealArrays(2, 5))
    println(idealArrays(5, 3))
    println(idealArrays(5878, 2900))
    println(idealArrays(9767, 9557))
  }

  private def test1 = {
    println(fillCups(Array(2, 4, 2)) == 4)
  }
}
