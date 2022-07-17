package com.zerox.contest

/**
 * @author ZeromaXHe
 * @since 2022/7/17 10:24
 * @note
 * 第 302 场周赛
 */
object WeeklyContest302 {
  /**
   * 6120. 数组能形成多少数对 | 难度：简单
   *
   * @param nums
   * @return
   */
  def numberOfPairs(nums: Array[Int]): Array[Int] = {
    nums.groupBy(identity).values.map(arr => Array(arr.length / 2, arr.length % 2)).reduce((a1, a2) => Array(a1(0) + a2(0), a1(1) + a2(1)))
  }

  /**
   * 6164. 数位和相等数对的最大和 | 难度：中等
   *
   * @param nums
   * @return
   */
  def maximumSum(nums: Array[Int]): Int = {
    val map = nums.groupBy(sumDigit).filter(_._2.length >= 2)
    if (map.isEmpty) -1
    else map.values.map(_.sorted).map(arr => arr(arr.length - 1) + arr(arr.length - 2)).max
  }

  private def sumDigit(num: Int) = num.toString.map(_ - '0').sum

  private def sumDigit_while(num: Int) = {
    var n = num
    var sum = 0
    while (n > 0) {
      sum += n % 10
      n /= 10
    }
    sum
  }

  /**
   * 6121. 裁剪数字后查询第 K 小的数字 | 难度：中等
   *
   * @param nums
   * @param queries
   * @return
   */
  def smallestTrimmedNumbers(nums: Array[String], queries: Array[Array[Int]]): Array[Int] = {
    queries.map(arr => nums.map(str => str.substring(str.length - arr(1))).zipWithIndex.sortBy(_._1).map(_._2).apply(arr(0) - 1))
  }

  /**
   * 6122. 使数组可以被整除的最少删除次数 | 难度：困难
   *
   * @param nums
   * @param numsDivide
   * @return
   */
  def minOperations(nums: Array[Int], numsDivide: Array[Int]): Int = {
    val gcdRes = gcd(numsDivide)
    nums.filter(_ <= gcdRes).sorted.zipWithIndex.find(gcdRes % _._1 == 0).getOrElse((-1, -1))._2
  }

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

  private def gcd(arr: Array[Int]): Int = arr.reduce((res, next) => gcd(res, next))

  private def gcd_while(arr: Array[Int]): Int = {
    var res = arr(0)
    for (i <- 0 until (arr.length - 1)) {
      var tmp = 0
      while (arr(i + 1) != 0) {
        tmp = arr(i + 1)
        arr(i + 1) = res % arr(i + 1)
        res = tmp
      }
    }
    res
  }

  def main(args: Array[String]): Unit = {
    println(minOperations(Array(3, 2, 6, 2, 35, 5, 35, 2, 5, 8, 7, 3, 4), Array(105, 70, 70, 175, 105, 105, 105)) == 6)
  }
}
