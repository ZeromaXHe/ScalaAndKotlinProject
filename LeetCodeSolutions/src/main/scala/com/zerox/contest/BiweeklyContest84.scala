package com.zerox.contest

/**
 * @author ZeromaXHe
 * @since 2022/8/6 22:13
 * @note
 * 第 84 场双周赛
 */
object BiweeklyContest84 {
  /**
   * 6141. 合并相似的物品 | 难度：简单
   * 49 / 49 个通过测试用例
   * 状态：通过
   * 执行用时: 700 ms
   * 内存消耗: 54.5 MB
   *
   * @param items1
   * @param items2
   * @return
   */
  def mergeSimilarItems(items1: Array[Array[Int]], items2: Array[Array[Int]]): List[List[Int]] = {
    val map = items1.map(arr => (arr(0), arr(1))).toMap
    val map2 = items2.map(arr => (arr(0), arr(1) + (if (map.contains(arr(0))) map(arr(0)) else 0))).toMap
    val list = new scala.collection.mutable.ListBuffer[List[Int]]
    for (elem <- map if !map2.contains(elem._1)) {
      list += List(elem._1, elem._2)
    }
    for (elem <- map2) {
      list += List(elem._1, elem._2)
    }
    list.toList.sortBy(_.head)
  }

  /**
   * 6142. 统计坏数对的数目 | 难度：中等
   * 65 / 65 个通过测试用例
   * 状态：通过
   * 执行用时: 1556 ms
   * 内存消耗: 94.9 MB
   *
   * @param nums
   * @return
   */
  def countBadPairs(nums: Array[Int]): Long = {
    nums.length * (nums.length + 1L) / 2 - nums.zipWithIndex.map(t => t._1 - t._2).groupBy(identity).mapValues(l => (1L + l.length) * l.length / 2).values.sum
  }

  def countBadPairs_timeout(nums: Array[Int]): Long = {
    nums.zipWithIndex.combinations(2).count(arr => arr(0)._2 < arr(1)._2 && arr(1)._2 - arr(0)._2 != arr(1)._1 - arr(0)._1)
  }

  /**
   * 6174. 任务调度器 II | 难度：中等
   * 61 / 61 个通过测试用例
   * 状态：通过
   * 执行用时: 916 ms
   * 内存消耗: 78.3 MB
   *
   * @param tasks
   * @param space
   * @return
   */
  def taskSchedulerII(tasks: Array[Int], space: Int): Long = {
    val map = new scala.collection.mutable.HashMap[Int, Long]
    var result = 0L
    for (i <- tasks.indices) {
      if (map.contains(tasks(i))) {
        result += (if (result - map(tasks(i)) >= space) 1 else 1 + space - result + map(tasks(i)))
        map(tasks(i)) = result
      } else {
        result += 1
        map(tasks(i)) = result
      }
    }
    result
  }

  /**
   * 6144. 将数组排序的最少替换次数 | 难度：困难
   * 47 / 47 个通过测试用例
   * 状态：通过
   * 执行用时: 724 ms
   * 内存消耗: 79.7 MB
   *
   * @param nums
   * @return
   */
  def minimumReplacement(nums: Array[Int]): Long = {
    var i = nums.length - 1 - 1
    var result = 0L
    var max = nums(nums.length - 1)
    while (i >= 0) {
      if (nums(i) > max) {
        val remain = nums(i) % max
        val div = nums(i) / max
        if (remain == 0) {
          result += div - 1
        } else {
          var bigger = max
          var smaller = remain
          while (smaller < bigger) {
            smaller += div
            bigger -= 1
          }
          max = bigger
          result += div
        }
      } else {
        max = nums(i)
      }
      i -= 1
    }
    result
  }

  def main(args: Array[String]): Unit = {
    println(minimumReplacement(Array(2, 10, 20, 19, 1))) // 47
    println(minimumReplacement(Array(19, 7, 2, 24, 11, 16, 1, 11, 23))) // 73
  }
}
