package com.zerox.scala.from801to1000

import com.zerox.scala.aider.UnionSet

/**
 * @author ZeromaXHe
 * @since 2022/10/16 19:58
 * @note
 * 886. 可能的二分法 | 难度：中等
 */
object Solution886 {
  /**
   * 时间 1044 ms 击败 100%
   * 内存 65.6 MB 击败 100%
   *
   * @param n
   * @param dislikes
   * @return
   */
  def possibleBipartition(n: Int, dislikes: Array[Array[Int]]): Boolean = {
    import scala.collection.mutable
    val unionSet = new UnionSet(n + 1)
    val g = Array.fill(n + 1)(new mutable.ListBuffer[Int])
    for (Array(p0, p1) <- dislikes) {
      g(p0).addOne(p1)
      g(p1).addOne(p0)
    }
    for (i <- 1 to n; j <- g(i).indices) {
      unionSet.union(g(i).head, g(i)(j))
      if (unionSet.find(g(i)(j)) == unionSet.find(i)) return false
    }
    true
  }
}
