package com.zerox.scala.contest

/**
 * @author ZeromaXHe
 * @since 2022/9/4 10:18
 * @note
 * 第 309 场周赛
 */
object WeeklyContest309 {
  /**
   * 6167. 检查相同字母间的距离 | 难度：简单
   * 335 / 335 个通过测试用例
   * 状态：通过
   * 执行用时: 496 ms
   * 内存消耗: 53.2 MB
   *
   * @param s
   * @param distance
   * @return
   */
  def checkDistances(s: String, distance: Array[Int]): Boolean = {
    val pres = Array.fill(26)(-1)
    for (i <- s.indices) {
      val idx = s(i) - 'a'
      if (pres(idx) > -1 && i - pres(idx) - 1 != distance(idx)) return false
      pres(idx) = i
    }
    true
  }

  /**
   * 6168. 恰好移动 k 步到达某一位置的方法数目 | 难度：中等
   * 求组合数没有模板啊…… 烦死了…… 自己想排列组合也不愿意静下心去想，跳过以后就没时间回来写了
   * 其实很简单：往正方向走了 a 步，往负方向走了 (k − a) 步后到达 endPos，根据组合数的定义可知答案为 C(a, k)（k 步里选 a 步走正方向）
   * 脑子没想明白，净想着写代码了。
   * 比赛时没有做出来，代码是后来写的
   *
   * 35 / 35 个通过测试用例
   * 状态：通过
   * 执行用时: 476 ms
   * 内存消耗: 65.9 MB
   *
   * @param startPos
   * @param endPos
   * @param k
   * @return
   */
  def numberOfWays(startPos: Int, endPos: Int, k: Int): Int = {
    val MOD = (1e9 + 7).toInt
    val diff = (endPos - startPos).abs
    if ((diff + k) % 2 == 1 || diff > k) return 0
    val dp = Array.ofDim[Long](k + 1, k + 1)
    for (i <- 0 to k) {
      dp(i)(0) = 1
      for (j <- 1 to i) dp(i)(j) = (dp(i - 1)(j) + dp(i - 1)(j - 1)) % MOD
    }
    dp(k)((diff + k) / 2).toInt
  }

  /**
   * 6169. 最长优雅子数组 | 难度：中等
   * 比赛其间没做出来。没看到连续，吐了
   *
   * 61 / 61 个通过测试用例
   * 状态：通过
   * 执行用时: 712 ms
   * 内存消耗: 66.7 MB
   *
   * @param nums
   * @return
   */
  def longestNiceSubarray(nums: Array[Int]): Int = {
    var l = 0
    var r = 1
    var andSum = nums(l)
    var max = 1
    while (r < nums.length) {
      if ((nums(r) & andSum) == 0) {
        andSum |= nums(r)
        r += 1
      } else {
        andSum ^= nums(l)
        l += 1
      }
      if (r - l > max) max = r - l
    }
    max
  }

  /**
   * 6170. 会议室 III | 难度：困难
   * 81 / 81 个通过测试用例
   * 状态：通过
   * 执行用时: 1680 ms
   * 内存消耗: 103.3 MB
   *
   * delay 的逻辑没想明白，疯狂抓虫，从头做到尾。吐了
   * 不过幸好最后还是做出来了，不然这次就做一道简单就不知道要掉多少分了
   *
   * @param n
   * @param meetings
   * @return
   */
  def mostBooked(n: Int, meetings: Array[Array[Int]]): Int = {

    import scala.collection.mutable

    val res = new Array[Int](n)
    // (end, room_id) 最小堆
    val pq = new mutable.PriorityQueue[(Long, Int)]()(Ordering.Tuple2[Long, Int].reverse)
    // 最小堆
    val roomPq = new mutable.PriorityQueue[Int]()(Ordering.Int.reverse)
    for (i <- 0 until n) roomPq.enqueue(i)
    val sortMeet = meetings.sortBy(_ (0))
    for (i <- sortMeet.indices) {
      while (pq.nonEmpty && pq.head._1 <= sortMeet(i)(0)) {
        val deq = pq.dequeue()
        roomPq.enqueue(deq._2)
        res(deq._2) += 1
      }
      var delay = 0L
      if (pq.size == n) {
        val deq = pq.dequeue()
        delay += deq._1 - sortMeet(i)(0)
        roomPq.enqueue(deq._2)
        res(deq._2) += 1
      }
      pq.enqueue((sortMeet(i)(1) + delay, roomPq.dequeue()))
    }
    while (pq.nonEmpty) res(pq.dequeue()._2) += 1
    var max = -1
    var idx = -1
    for (i <- res.indices) {
      if (res(i) > max) {
        max = res(i)
        idx = i
      }
    }
    idx
  }

  def main(args: Array[String]): Unit = {
    // [893803453,60543633,85309607,348377798,696815256,916582942,991112444,736712017,916399634,744677522,274332240,119047370,542000876,256170455,16,8192,2,8,96,128,135168,67108864,171021977,498052009,509664323,119776370,938908623,387770982,863736718,622916572,773798777,252211255,972969388,247290894,341468520,185997261,621380930,426480917]
    // 预期： 8

    // [904163577,321202512,470948612,490925389,550193477,87742556,151890632,655280661,4,263168,32,573703555,886743681,937599702,120293650,725712231,257119393]
    // 预期： 3

    // -----------------------------------------

    // 4
    //[[38,44],[17,38],[6,29],[34,40],[7,14],[4,27]]
    // 预期：0
    // println(mostBooked(4, Array(Array(38, 44), Array(17, 38), Array(6, 29), Array(34, 40), Array(7, 14), Array(4, 27))))
    // 3
    //[[39,49],[28,39],[9,29],[10,36],[22,47],[2,3],[4,49],[46,50],[45,50],[17,33]]
    // 预期：0

    // 2
    //[[4,11],[1,13],[8,15],[9,18],[0,17]]
    // 预期：1
    // println(mostBooked(2, Array(Array(4, 11), Array(1, 13), Array(8, 15), Array(9, 18), Array(0, 17))))

    // 2
    //[[0,10],[1,5],[2,7],[3,4],[8,11],[9,12]]
    // 预期：0
    println(mostBooked(2, Array(Array(0, 10), Array(1, 5), Array(2, 7), Array(3, 4), Array(8, 11), Array(9, 12))))

    // 5
    //[[3,29],[7,43],[48,50],[20,32],[38,49],[22,48],[41,47]]
    // 预期：0
  }
}
