package com.zerox.scala.contest

/**
 * @author ZeromaXHe
 * @since 2022/9/17 22:18
 * @note
 * 第 87 场双周赛
 */
object BiweeklyContest87 {
  /**
   * 6184. 统计共同度过的日子数 | 难度：简单
   * 48 / 48 个通过测试用例
   * 状态：通过
   * 执行用时: 444 ms
   * 内存消耗: 52.2 MB
   *
   * @param arriveAlice
   * @param leaveAlice
   * @param arriveBob
   * @param leaveBob
   * @return
   */
  def countDaysTogether(arriveAlice: String, leaveAlice: String, arriveBob: String, leaveBob: String): Int = {
    val set31 = Set(1, 3, 5, 7, 8, 10, 12)
    val arr = new Array[Int](13)
    for (i <- 1 until 12) {
      arr(i + 1) = arr(i) + (if (i == 2) 28 else if (set31.contains(i)) 31 else 30)
    }
    val arriveA = stringToDay(arriveAlice, arr)
    val arriveB = stringToDay(arriveBob, arr)
    val leaveA = stringToDay(leaveAlice, arr)
    val leaveB = stringToDay(leaveBob, arr)
    if (leaveA < arriveB || leaveB < arriveA) 0
    else (leaveA min leaveB) - (arriveA max arriveB) + 1
  }

  private def stringToDay(str: String, arr: Array[Int]): Int = {
    val ints = str.split("-").map(_.toInt)
    arr(ints(0)) + ints(1)
  }

  /**
   * 6185. 运动员和训练师的最大匹配数 | 难度：中等
   * 35 / 35 个通过测试用例
   * 状态：通过
   * 执行用时: 848 ms
   * 内存消耗: 83.5 MB
   *
   * @param players
   * @param trainers
   * @return
   */
  def matchPlayersAndTrainers(players: Array[Int], trainers: Array[Int]): Int = {
    val sortedP = players.sorted
    val sortedT = trainers.sorted
    var ptrP = sortedP.length - 1
    var ptrT = sortedT.length - 1
    var res = 0
    while (ptrP >= 0 && ptrT >= 0) {
      if (sortedT(ptrT) >= sortedP(ptrP)) {
        res += 1
        ptrT -= 1
      }
      ptrP -= 1
    }
    res
  }

  /**
   * 6186. 按位或最大的最小子数组长度 | 难度：中等
   * 59 / 59 个通过测试用例
   * 状态：通过
   * 执行用时: 8464 ms
   * 内存消耗: 71 MB
   *
   * @param nums
   * @return
   */
  def smallestSubarrays(nums: Array[Int]): Array[Int] = {
    val max = nums.clone()
    val count = Array.fill(nums.length)(1)
    for (i <- 1 until nums.length; j <- 0 until i) {
      if ((nums(i) | max(j)) > max(j)) {
        max(j) |= nums(i)
        count(j) = i + 1 - j
      }
    }
    count
  }

  /**
   * 6187. 完成所有交易的初始最少钱数 | 难度：困难
   *
   * @param transactions
   * @return
   */
  def minimumMoney(transactions: Array[Array[Int]]): Long = {
    val sort = transactions.sortBy(arr => arr(1) - arr(0))
    if (sort(0)(1) - sort(0)(0) >= 0) return transactions.map(_ (0)).max
    val pq = new scala.collection.mutable.PriorityQueue[(Int, Int)]()(Ordering.by[(Int, Int), Int](_._1))
    var sumNegative = 0L
    var money = sort(0)(0).toLong
    for (i <- sort.indices) {
      if (sort(i)(1) - sort(i)(0) >= 0) money = money max (-sumNegative + sort(i)(0))
      else {
        sumNegative += sort(i)(1) - sort(i)(0)
        pq.enqueue((sort(i)(1), sort(i)(0)))
        money = money max (-sumNegative + pq.head._2)
      }
    }
    money
  }

  def main(args: Array[String]): Unit = {
    // println(countDaysTogether("10-01", "10-31", "11-01", "12-31")) // 0
    println(minimumMoney(Array(Array(2, 1), Array(5, 0), Array(4, 2)))) // 10
    println(minimumMoney(Array(Array(3, 0), Array(0, 3)))) // 3
  }
}
