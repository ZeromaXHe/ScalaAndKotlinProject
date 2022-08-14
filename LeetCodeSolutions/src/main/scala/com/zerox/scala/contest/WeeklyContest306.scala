package com.zerox.scala.contest

/**
 * @author ZeromaXHe
 * @since 2022/8/14 9:54
 * @note
 * 第 306 场周赛
 */
object WeeklyContest306 {
  /**
   * 6148. 矩阵中的局部最大值 | 难度：简单
   * 50 / 50 个通过测试用例
   * 状态：通过
   * 执行用时: 624 ms
   * 内存消耗: 55.2 MB
   *
   * @param grid
   * @return
   */
  def largestLocal(grid: Array[Array[Int]]): Array[Array[Int]] = {
    val result = Array.ofDim[Int](grid.length - 2, grid.length - 2)
    for (i <- grid.indices; j <- grid.indices; ri <- i - 2 to i; rj <- j - 2 to j if valid(ri, rj, result.length)) {
      result(ri)(rj) = math.max(result(ri)(rj), grid(i)(j))
    }
    result
  }

  private def valid(i: Int, j: Int, len: Int): Boolean = {
    i >= 0 && j >= 0 && i < len && j < len
  }

  def edgeScore_wrong(edges: Array[Int]): Int = {
    val map = edges.zipWithIndex.groupBy(_._1).mapValues(_.map(_._2).sum)
    val max = map.values.max
    var min = Int.MaxValue
    for (k <- map.keys if map(k) == max) {
      min = math.min(min, k)
    }
    min
  }

  /**
   * 6149. 边积分最高的节点 | 难度：中等
   * 116 / 116 个通过测试用例
   * 状态：通过
   * 执行用时: 1000 ms
   * 内存消耗: 80.5 MB
   *
   * @param edges
   * @return
   */
  def edgeScore(edges: Array[Int]): Int = {
    val map = new scala.collection.mutable.HashMap[Int, Int]
    var max = Int.MinValue
    var result = Int.MaxValue
    for (i <- edges.indices) {
      map(edges(i)) = map.getOrElse(edges(i), 0) + i
      if (map(edges(i)) > max) {
        max = map(edges(i))
        result = edges(i)
      } else if (max == map(edges(i))) {
        result = math.min(result, edges(i))
      }
    }
    result
  }

  /**
   * 6150. 根据模式串构造最小数字 | 难度：中等
   * 104 / 104 个通过测试用例
   * 状态：通过
   * 执行用时: 888 ms
   * 内存消耗: 55.7 MB
   *
   * @param pattern
   * @return
   */
  def smallestNumber(pattern: String): String = {
    val map = Map('I' -> true, 'D' -> false)
    val maybeInts = (1 to pattern.length + 1).permutations.find(p => {
      (1 until p.length).forall(i => (p(i) > p(i - 1)) == map(pattern(i - 1)))
    })
    val sb = new StringBuilder
    maybeInts.get.foreach(sb.append)
    sb.toString()
  }

  /**
   * 6151. 统计特殊整数 | 难度：困难
   *
   * @param n
   * @return
   */
  def countSpecialNumbers(n: Int): Int = {
    // 0/9
    // 11 22 (1 * (9 - 0) /90)
    // 112 121 (2 * (90 - 9) + 1 * 9) / 900
    // 3 * (9000 - dp(2)) + 2 * (dp(2) - dp(1)) + dp(1)

    //    val len = n.toString.length
    //    val dp = new Array[Int](len)
    //    var total = 9
    //    for (i <- 1 until dp.length) {
    //      dp(i) += i * (total - dp(i - 1))
    //      for (j <- 1 until i) dp(i) += j * (dp(j) - dp(j - 1))
    //      total *= 10
    //    }
    //    var result = total / 9 - dp.sum + dp(len - 1) - 1
    //    for (i <- (total / 9) to n if i.toString.groupBy(identity).values.forall(_.length == 1)) {
    //      result += 1
    //    }
    //    result
    var result = 0
    var i = 1
    while (i <= n) {
      i.toString.zipWithIndex.groupBy(identity).toArray.sortBy(_._1._2).find(_._2.length != 1)
      result += 1
      i += 1
    }
    result
  }

  def main(args: Array[String]): Unit = {
    println(countSpecialNumbers(20)) // 19
    println(countSpecialNumbers(5)) // 5
    println(countSpecialNumbers(135)) // 110
    println(countSpecialNumbers(1581)) // 1005

    // 第二题补极端的案例的话估计就过不了了，好气
    // i: 65537, max: 2147450880
    // i: 92683, max: 2147413805
    // i: 100001, max: 704927097
    var max = 0
    var sum = 0
    var i = 0
    while (i <= 1e5) {
      max = sum
      sum += i
      i += 1
      if(sum < 0) {
        println(s"i: $i, max: $max")
        max = 0
        sum = 0
      }
    }
    println(s"i: $i, max: $max")
  }
}
