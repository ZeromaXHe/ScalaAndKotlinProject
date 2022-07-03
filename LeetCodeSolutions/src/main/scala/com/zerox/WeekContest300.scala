package com.zerox

/**
 * @author ZeromaXHe
 * @since 2022/7/3 10:24
 * @note
 * 力扣第 300 场周赛
 */
object WeekContest300 {
  /**
   * 6108. 解密消息 | 难度：简单
   * 49 / 49 个通过测试用例
   * 状态：通过
   * 执行用时: 1124 ms
   * 内存消耗: 78.9 MB
   *
   * @param key
   * @param message
   * @return
   */
  def decodeMessage(key: String, message: String): String = {
    val exist = new Array[Boolean](26)
    val map = (for (ch <- key if ch != ' ' && !exist(ch.toInt - 'a'.toInt)) yield {
      exist(ch.toInt - 'a'.toInt) = true
      ch
    }).zip("abcdefghijklmnopqrstuvwxyz").toMap
    for (ch <- message) yield {
      if (ch == ' ') ch else map(ch)
    }
  }

  /**
   * Definition for singly-linked list.
   * | class ListNode(_x: Int = 0, _next: ListNode = null) {
   * |   var next: ListNode = _next
   * |   var x: Int = _x
   * | }
   *
   * 6111. 螺旋矩阵 IV | 难度：中等
   * 68 / 68 个通过测试用例
   * 状态：通过
   * 执行用时: 508 ms
   * 内存消耗: 53.3 MB
   *
   * @param m
   * @param n
   * @param head
   * @return
   */
  def spiralMatrix(m: Int, n: Int, head: ListNode): Array[Array[Int]] = {
    val result = Array.fill(m) {
      Array.fill(n) {
        -1
      }
    }
    var ptr = head
    var minX = 0
    var minY = 0
    var maxX = m - 1
    var maxY = n - 1
    var x = 0
    var y = 0
    var dir = 0
    while (ptr != null) {
      result(x)(y) = ptr.x
      dir match {
        case 0 =>
          if (y == maxY) {
            dir = 1
            x += 1
            minX += 1
          } else y += 1
        case 1 =>
          if (x == maxX) {
            dir = 2
            y -= 1
            maxY -= 1
          } else x += 1
        case 2 =>
          if (y == minY) {
            dir = 3
            x -= 1
            maxX -= 1
          } else y -= 1
        case 3 =>
          if (x == minX) {
            dir = 0
            y += 1
            minY += 1
          } else x -= 1
      }
      ptr = ptr.next
    }
    result
  }

  class ListNode(_x: Int = 0, _next: ListNode = null) {
    var next: ListNode = _next
    var x: Int = _x
  }

  def main(args: Array[String]): Unit = {
    println(peopleAwareOfSecret_preSum(6, 2, 4) /* == 5*/)
    println(peopleAwareOfSecret_preSum(4, 1, 3) /* == 6*/)
    println(peopleAwareOfSecret_preSum(4, 1, 4) /* == 8*/)
    println(peopleAwareOfSecret_preSum(6, 1, 2) /* == 2*/)
    println(peopleAwareOfSecret_preSum(5, 2, 3) /* == 2*/)
    println(peopleAwareOfSecret_preSum(684, 18, 496) /* == 653668527*/)
    println(-1 % 1000000007)
  }

  /**
   * 6109. 知道秘密的人数 | 难度：困难
   * 周赛时间内没做出来。思路很简单，周赛时候就硬是写了一个多小时没做出来…… 吐了
   *
   * 执行用时：572 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：51.6 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：80 / 80
   *
   * @param n
   * @param delay
   * @param forget
   * @return
   */
  def peopleAwareOfSecret(n: Int, delay: Int, forget: Int): Int = {
    // 存的是第 i 天的新增消息人数
    val dp = new Array[Int](n + 1)
    dp(1) = 1
    for (i <- 1 to (n - delay);
         j <- (i + delay) until (i + forget)
         if j <= n) {
      dp(j) = (dp(i) + dp(j)) % 1000000007
    }
    var sum = 0
    for (i <- (n + 1 - forget) to n) {
      sum += dp(i)
      sum %= 1000000007
    }
    sum
  }

  /**
   * 6109. 知道秘密的人数
   * 前缀和解法
   *
   * 执行用时：432 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：51.2 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：80 / 80
   *
   * @param n
   * @param delay
   * @param forget
   * @return
   */
  def peopleAwareOfSecret_preSum(n: Int, delay: Int, forget: Int): Int = {
    // 存的是前缀和
    val dp = new Array[Int](n + 1)
    dp(1) = 1
    for (i <- 2 to n) {
      dp(i) = (dp(i - 1) + (dp(math.max(i - delay, 0)) - dp(math.max(i - forget, 0))) % 1000000007) % 1000000007
    }
    val res = (dp(n) - dp(n - forget)) % 1000000007
    if (res > 0) res else res + 1000000007
  }

  /**
   * 6110. 网格图中递增路径的数目 | 难度：困难
   * 周赛没做这道题。现在看也不难，第三题耗费太多时间了，这道题都没来得及看
   *
   * 执行用时：1020 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：79.6 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：36 / 36
   *
   * @param grid
   * @return
   */
  def countPaths(grid: Array[Array[Int]]): Int = {
    val m = grid.length
    val n = grid(0).length
    val cache = new Array[Array[Int]](m)
    for (i <- cache.indices) {
      cache(i) = new Array[Int](n)
    }

    def dfs(i: Int, j: Int): Int = {
      if (cache(i)(j) > 0) return cache(i)(j)
      var res = 1
      for ((x, y) <- Array((i + 1, j), (i - 1, j), (i, j + 1), (i, j - 1))
           if 0 <= x && x < m && 0 <= y && y < n && grid(x)(y) > grid(i)(j)) {
        res = (res + dfs(x, y)) % 1000000007
      }
      cache(i)(j) = res
      res
    }

    var sum = 0
    for (i <- grid.indices;
         j <- grid(0).indices) {
      sum = (sum + dfs(i, j)) % 1000000007
    }
    sum
  }
}
