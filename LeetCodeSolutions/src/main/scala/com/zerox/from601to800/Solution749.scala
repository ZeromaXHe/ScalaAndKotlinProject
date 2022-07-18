package com.zerox.from601to800

/**
 * @author zhuxi
 * @since 2022/7/18 10:48
 * @note
 * 749. 隔离病毒 | 难度：困难 | 标签：深度优先搜索、广度优先搜索、数组、矩阵、模拟
 * 病毒扩散得很快，现在你的任务是尽可能地通过安装防火墙来隔离病毒。
 *
 * 假设世界由 m x n 的二维矩阵 isInfected 组成， isInfected[i][j] == 0 表示该区域未感染病毒，而  isInfected[i][j] == 1 表示该区域已感染病毒。可以在任意 2 个相邻单元之间的共享边界上安装一个防火墙（并且只有一个防火墙）。
 *
 * 每天晚上，病毒会从被感染区域向相邻未感染区域扩散，除非被防火墙隔离。现由于资源有限，每天你只能安装一系列防火墙来隔离其中一个被病毒感染的区域（一个区域或连续的一片区域），且该感染区域对未感染区域的威胁最大且 保证唯一 。
 *
 * 你需要努力使得最后有部分区域不被病毒感染，如果可以成功，那么返回需要使用的防火墙个数; 如果无法实现，则返回在世界被病毒全部感染时已安装的防火墙个数。
 *
 * 示例 1：
 * 输入: isInfected = [[0,1,0,0,0,0,0,1],[0,1,0,0,0,0,0,1],[0,0,0,0,0,0,0,1],[0,0,0,0,0,0,0,0]]
 * 输出: 10
 * 解释:一共有两块被病毒感染的区域。
 * 在第一天，添加 5 墙隔离病毒区域的左侧。病毒传播后的状态是:
 *
 * 第二天，在右侧添加 5 个墙来隔离病毒区域。此时病毒已经被完全控制住了。
 *
 * 示例 2：
 * 输入: isInfected = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出: 4
 * 解释: 虽然只保存了一个小区域，但却有四面墙。
 * 注意，防火墙只建立在两个不同区域的共享边界上。
 *
 * 示例 3:
 * 输入: isInfected = [[1,1,1,0,0,0,0,0,0],[1,0,1,0,1,1,1,1,1],[1,1,1,0,0,0,0,0,0]]
 * 输出: 13
 * 解释: 在隔离右边感染区域后，隔离左边病毒区域只需要 2 个防火墙。
 *
 * 提示:
 * m == isInfected.length
 * n == isInfected[i].length
 * 1 <= m, n <= 50
 * isInfected[i][j] is either 0 or 1
 * 在整个描述的过程中，总有一个相邻的病毒区域，它将在下一轮 严格地感染更多未受污染的方块 
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/contain-virus
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution749 {
  val dirs: Array[Array[Int]] = Array(Array(-1, 0), Array(1, 0), Array(0, -1), Array(0, 1))

  /**
   * 执行用时：632 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：56.6 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：32 / 32
   *
   * @param isInfected
   * @return
   */
  def containVirus(isInfected: Array[Array[Int]]): Int = {
    val m = isInfected.length
    val n = isInfected(0).length
    var ans = 0
    var continue = true
    while (continue) {
      // 保存索引为 idx 的病毒区域的相邻无病毒邻居的坐标（高 16 位为 x，低 16 位为 y）
      val neighbors = new scala.collection.mutable.ArrayBuffer[scala.collection.mutable.HashSet[Int]]
      // 计数拦截对应索引为 idx 的病毒区域需要的防火墙数目（不等于邻居数量，因为可能和两个以上的病毒相邻）
      val firewalls = new scala.collection.mutable.ArrayBuffer[Int]
      bfsInfected(isInfected, m, n, neighbors, firewalls)
      // 没有剩余的非病毒区域了，则跳出 while 循环
      if (neighbors.isEmpty) continue = false
      else {
        // 寻找未感染邻居最多的病毒区域
        val idx = neighbors.zipWithIndex.maxBy(_._1.size)._2
        ans += firewalls(idx)
        for (i <- 0 until m; j <- 0 until n if isInfected(i)(j) < 0) {
          // 其他区域还原为 1，未感染邻居最多的病毒区域设置为 2 表示已被隔离
          if (isInfected(i)(j) != -idx - 1) isInfected(i)(j) = 1
          else isInfected(i)(j) = 2
        }
        // 下一轮传播
        for (i <- neighbors.indices if i != idx; value <- neighbors(i)) {
          isInfected(value >> 16)(value & ((1 << 16) - 1)) = 1
        }
        // 如果本轮已控制最后一个病毒区域，跳出 while 循环
        if (neighbors.size == 1) continue = false
      }
    }
    ans
  }

  private def bfsInfected(isInfected: Array[Array[Int]], m: Int, n: Int,
                          neighbors: scala.collection.mutable.ArrayBuffer[scala.collection.mutable.HashSet[Int]],
                          firewalls: scala.collection.mutable.ArrayBuffer[Int]) = {
    // BFS 把各个病毒分区的值设置为 -idx
    for (i <- 0 until m; j <- 0 until n if isInfected(i)(j) == 1) {
      val queue = new scala.collection.mutable.Queue[(Int, Int)]
      queue.enqueue((i, j))
      val neighbor = new scala.collection.mutable.HashSet[Int]
      var firewall = 0
      val idx = neighbors.size + 1
      isInfected(i)(j) = -idx

      // BFS
      while (queue.nonEmpty) {
        val (x, y) = queue.dequeue()
        for (d <- dirs) {
          val nx = x + d(0)
          val ny = y + d(1)
          if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
            if (isInfected(nx)(ny) == 1) {
              queue.enqueue((nx, ny))
              isInfected(nx)(ny) = -idx
            } else if (isInfected(nx)(ny) == 0) {
              firewall += 1
              neighbor.add((nx << 16) ^ ny)
            }
          }
        }
      }
      firewalls.append(firewall)
      neighbors.append(neighbor)
    }
  }

  /**
   * 执行用时：660 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：56.7 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：32 / 32
   *
   * 按 Java 题解直接翻译的
   *
   * @param isInfected
   * @return
   */
  def containVirus_java(isInfected: Array[Array[Int]]): Int = {
    val m = isInfected.length
    val n = isInfected(0).length
    var ans = 0
    var continue = true
    while (continue) {
      val neighbors = new scala.collection.mutable.ArrayBuffer[scala.collection.mutable.HashSet[Int]]
      val firewalls = new scala.collection.mutable.ArrayBuffer[Int]
      for (i <- 0 until m; j <- 0 until n if isInfected(i)(j) == 1) {
        val queue = new scala.collection.mutable.Queue[(Int, Int)]
        queue.enqueue((i, j))
        val neighbor = new scala.collection.mutable.HashSet[Int]
        var firewall = 0
        val idx = neighbors.size + 1
        isInfected(i)(j) = -idx

        while (queue.nonEmpty) {
          val (x, y) = queue.dequeue()
          for (d <- dirs) {
            val nx = x + d(0)
            val ny = y + d(1)
            if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
              if (isInfected(nx)(ny) == 1) {
                queue.enqueue((nx, ny))
                isInfected(nx)(ny) = -idx
              } else if (isInfected(nx)(ny) == 0) {
                firewall += 1
                neighbor.add((nx << 16) ^ ny)
              }
            }
          }
        }
        neighbors.append(neighbor)
        firewalls.append(firewall)
      }
      if (neighbors.isEmpty) continue = false
      else {
        var idx = 0
        for (i <- 1 until neighbors.size if neighbors(i).size > neighbors(idx).size) {
          idx = i
        }
        ans += firewalls(idx)
        for (i <- 0 until m; j <- 0 until n if isInfected(i)(j) < 0) {
          if (isInfected(i)(j) != -idx - 1) isInfected(i)(j) = 1
          else isInfected(i)(j) = 2
        }
        for (i <- neighbors.indices if i != idx; value <- neighbors(i)) {
          isInfected(value >> 16)(value & ((1 << 16) - 1)) = 1
        }
        if (neighbors.size == 1) continue = false
      }
    }
    ans
  }
}
