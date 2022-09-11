package com.zerox.scala.contest

import com.zerox.scala.aider.BinarySearcher

/**
 * @author ZeromaXHe
 * @since 2022/9/11 9:59
 * @note
 * 第 310 场周赛
 */
object WeeklyContest310 {
  /**
   * 6176. 出现最频繁的偶数元素 | 难度：简单
   * 217 / 217 个通过测试用例
   * 状态：通过
   * 执行用时: 992 ms
   * 内存消耗: 54.9 MB
   *
   * @param nums
   * @return
   */
  def mostFrequentEven(nums: Array[Int]): Int = {
    val map = nums.groupBy(identity).view.mapValues(_.length).filter(_._1 % 2 == 0)
    if (map.isEmpty) -1 else map.maxBy(t => (t._2, -t._1))._1
  }

  /**
   * 6177. 子字符串的最优划分 | 难度：中等
   * 59 / 59 个通过测试用例
   * 状态：通过
   * 执行用时: 492 ms
   * 内存消耗: 53.9 MB
   *
   * @param s
   * @return
   */
  def partitionString(s: String): Int = {
    val set = new scala.collection.mutable.HashSet[Char]
    var res = 1
    for (c <- s) {
      if (set.contains(c)) {
        set.clear()
        res += 1
      }
      set.add(c)
    }
    res
  }

  /**
   * 6178. 将区间分为最少组数 | 难度：中等
   * 35 / 35 个通过测试用例
   * 状态：通过
   * 执行用时: 2080 ms
   * 内存消耗: 112.2 MB
   *
   * @param intervals
   * @return
   */
  def minGroups(intervals: Array[Array[Int]]): Int = {
    val sort = intervals.sortBy(a => a(0))
    val map = new scala.collection.mutable.TreeMap[Int, Int]
    for (i <- sort.indices) {
      if (map.isEmpty) map(sort(i)(1)) = 1
      else {
        // maxBefore 又不包含参数了，草…… minAfter 就包含，scala 这个 API 和 java TreeMap 相比真傻逼
        val pre = map.maxBefore(sort(i)(0))
        if (pre.nonEmpty) {
          val preKey = pre.get._1
          if (map(preKey) == 1) map.remove(preKey)
          else map(preKey) -= 1
        }
        if (map.contains(sort(i)(1))) map(sort(i)(1)) += 1
        else map(sort(i)(1)) = 1
      }
    }
    map.values.sum
  }

  //变量说明，下同
  var maxd: Int = 50000 + 7
  var a = new Array[Int](maxd) //原数组
  var mina = Array.ofDim[Int](maxd, 110) //存区间最小值
  var maxa = Array.ofDim[Int](maxd, 110) //存区间最大值

  def getST(n: Int): Unit = { //n为元素的总个数
    for (i <- 1 to n) {
      mina(i)(0) = a(i)
      maxa(i)(0) = a(i)
    }
    var j = 1
    while ((1 << j) <= n) { //2的j次方。也可以写为 （1<<j）<=n
      var i = 1
      while (i + (1 << j) - 1 <= n) { //防止越界
        mina(i)(j) = Math.min(mina(i)(j - 1), mina(i + (1 << (j - 1)))(j - 1)) //最小值
        maxa(i)(j) = Math.max(maxa(i)(j - 1), maxa(i + (1 << (j - 1)))(j - 1)) //最大值
        i += 1
      }
      j += 1
    }
  }

  /**
   * 6206. 最长递增子序列 II | 难度：困难
   * 做不出，超时
   *
   * @param nums
   * @param k
   * @return
   */
  def lengthOfLIS_timeout(nums: Array[Int], k: Int): Int = {
    val dp = Array.fill(nums.length)(1)
    import scala.collection.mutable
    val map = new mutable.TreeMap[Int, mutable.HashSet[Int]]
    for (i <- dp.indices) {
      if (!map.contains(nums(i))) map(nums(i)) = new mutable.HashSet[Int]
      map(nums(i)).addOne(i)
      var pre = map.maxBefore(nums(i))
      var continue = true
      while (continue && pre.nonEmpty) {
        if (pre.get._1 < nums(i) - k) continue = false
        else for (j <- pre.get._2) dp(i) = Math.max(dp(i), dp(j) + 1)
        pre = map.maxBefore(pre.get._1)
      }
    }
    dp.max
  }

  def lengthOfLIS_timeout2(nums: Array[Int], k: Int): Int = {
    val dp = Array.fill(nums.length)(1)
    val sort = nums.zipWithIndex.sortBy(_._1)
    for (i <- dp.indices) {
      var l = 0
      var r = sort.length
      var idx = -1
      while (l < r) {
        val mid = (l + r) / 2
        if (sort(mid)._1 <= nums(i)) {
          l = mid + 1
          idx = mid
        } else r = mid
      }
      while (idx > 0 && sort(idx)._1 >= nums(i) - k) {
        val j = sort(idx)._2
        if (j < i) dp(i) = dp(i) max dp(j)
      }
    }
    dp.max
  }

  def main(args: Array[String]): Unit = {
    // println(minGroups(Array(Array(5, 10), Array(6, 8), Array(1, 5), Array(2, 3), Array(1, 10)))) // 3
    println(lengthOfLIS_timeout(Array(4, 2, 1, 4, 3, 4, 5, 8, 15), 3)) // 5
  }
}
