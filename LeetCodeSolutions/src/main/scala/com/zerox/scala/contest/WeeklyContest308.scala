package com.zerox.scala.contest


/**
 * @author ZeromaXHe
 * @since 2022/8/28 10:27
 * @note
 * 第 308 场周赛
 */
object WeeklyContest308 {
  /**
   * 6160. 和有限的最长子序列 | 难度：简单
   * 57 / 57 个通过测试用例
   * 状态：通过
   * 执行用时: 616 ms
   * 内存消耗: 53.5 MB
   *
   * @param nums
   * @param queries
   * @return
   */
  def answerQueries(nums: Array[Int], queries: Array[Int]): Array[Int] = {
    val sorted = nums.sorted
    val preSum = new Array[Int](sorted.length)
    var sum = 0
    for (i <- sorted.indices) {
      sum += sorted(i)
      preSum(i) = sum
    }
    (for (i <- queries.indices) yield {
      var idx = java.util.Arrays.binarySearch(preSum, queries(i))
      if (idx < 0) idx = -idx - 1
      else idx = idx + 1
      idx
    }).toArray
  }

  /**
   * 6161. 从字符串中移除星号 | 难度：中等
   * 65 / 65 个通过测试用例
   * 状态：通过
   * 执行用时: 668 ms
   * 内存消耗: 58.1 MB
   *
   * @param s
   * @return
   */
  def removeStars(s: String): String = {
    val sb = new StringBuilder
    for (c <- s) {
      if (c == '*') {
        if (sb.nonEmpty) sb.delete(sb.length - 1, sb.length)
      } else sb.append(c)
    }
    sb.toString()
  }

  /**
   * 6162. 收集垃圾的最少总时间 | 难度：中等
   * 75 / 75 个通过测试用例
   * 状态：通过
   * 执行用时: 884 ms
   * 内存消耗: 86.8 MB
   *
   * @param garbage
   * @param travel
   * @return
   */
  def garbageCollection(garbage: Array[String], travel: Array[Int]): Int = {
    val lastG = garbage.lastIndexWhere(_.contains('G'))
    val lastP = garbage.lastIndexWhere(_.contains('P'))
    val lastM = garbage.lastIndexWhere(_.contains('M'))
    var costG = 0
    var costP = 0
    var costM = 0
    if (lastG > -1) {
      for (i <- 0 to lastG) {
        costG += (if (i > 0) travel(i - 1) else 0) + garbage(i).count(_ == 'G')
      }
    }
    if (lastP > -1) {
      for (i <- 0 to lastP) {
        costP += (if (i > 0) travel(i - 1) else 0) + garbage(i).count(_ == 'P')
      }
    }
    if (lastM > -1) {
      for (i <- 0 to lastM) {
        costM += (if (i > 0) travel(i - 1) else 0) + garbage(i).count(_ == 'M')
      }
    }
    costG + costM + costP
  }

  /**
   * 6163. 给定条件下构造矩阵 | 难度：困难
   * 51 / 51 个通过测试用例
   * 状态：通过
   * 执行用时: 968 ms
   * 内存消耗: 61.8 MB
   *
   * 没自己的拓扑排序模板害死人啊……
   *
   * @param k
   * @param rowConditions
   * @param colConditions
   * @return
   */
  def buildMatrix(k: Int, rowConditions: Array[Array[Int]], colConditions: Array[Array[Int]]): Array[Array[Int]] = {
    import scala.collection.JavaConverters.collectionAsScalaIterableConverter
    var valid = true
    val edgesRow = new java.util.ArrayList[java.util.ArrayList[Int]]
    for (_ <- 0 until k) {
      edgesRow.add(new java.util.ArrayList[Int])
    }
    val visitedRow = new Array[Int](k)
    val resultRow = new Array[Int](k)
    var indexRow = k - 1
    for (info <- rowConditions) {
      edgesRow.get(info(0) - 1).add(info(1) - 1)
    }

    def dfsRow(i: Int): Unit = {
      visitedRow(i) = 1
      for (v <- edgesRow.get(i).asScala) {
        if (visitedRow(v) == 0) {
          dfsRow(v)
          if (!valid) return
        } else if (visitedRow(v) == 1) {
          valid = false
          return
        }
      }
      visitedRow(i) = 2
      resultRow(i) = indexRow
      indexRow -= 1
    }

    for (i <- 0 until k if visitedRow(i) == 0) {
      dfsRow(i)
    }
    if (!valid) {
      return new Array[Array[Int]](0)
    }

    val edgesCol = new java.util.ArrayList[java.util.ArrayList[Int]]
    for (_ <- 0 until k) {
      edgesCol.add(new java.util.ArrayList[Int])
    }
    val visitedCol = new Array[Int](k)
    val resultCol = new Array[Int](k)
    var indexCol = k - 1
    for (info <- colConditions) {
      edgesCol.get(info(0) - 1).add(info(1) - 1)
    }

    def dfsCol(i: Int): Unit = {
      visitedCol(i) = 1
      for (v <- edgesCol.get(i).asScala) {
        if (visitedCol(v) == 0) {
          dfsCol(v)
          if (!valid) return
        } else if (visitedCol(v) == 1) {
          valid = false
          return
        }
      }
      visitedCol(i) = 2
      resultCol(i) = indexCol
      indexCol -= 1
    }

    for (i <- 0 until k if visitedCol(i) == 0) {
      dfsCol(i)
    }
    if (!valid) {
      return new Array[Array[Int]](0)
    }

    val res = Array.ofDim[Int](k, k)
    var elem = 1
    for ((r, c) <- resultRow zip resultCol) {
      res(r)(c) = elem
      elem += 1
    }
    res
  }

  def main(args: Array[String]): Unit = {
    println(garbageCollection(Array("G", "P", "GP", "GG"), Array(2, 4, 3))) // 21
    println(garbageCollection(Array("MMM", "PGM", "GP"), Array(3, 10))) // 37
    // 8
    //[[1,2],[7,3],[4,3],[5,8],[7,8],[8,2],[5,8],[3,2],[1,3],[7,6],[4,3],[7,4],[4,8],[7,3],[7,5]]
    //[[5,7],[2,7],[4,3],[6,7],[4,3],[2,3],[6,2]]
    // 预期：[[0,0,0,0,0,0,7,0],[0,6,0,0,0,0,0,0],[0,0,5,0,0,0,0,0],[0,0,0,4,0,0,0,0],[8,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,1],[0,0,0,0,0,3,0,0],[0,0,0,0,2,0,0,0]]
  }
}
