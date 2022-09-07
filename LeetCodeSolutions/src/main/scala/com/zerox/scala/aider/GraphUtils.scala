package com.zerox.scala.aider

/**
 * @author zhuxi
 * @since 2022/9/5 10:55
 * @note
 * 图论相关的工具类
 */
object GraphUtils {
  def dfs[T](matrix: Array[Array[T]], i: Int, j: Int,
             valid: (Array[Array[T]], Int, Int) => Boolean,
             exec: (Array[Array[T]], Int, Int) => Int): Int = {
    var res = exec(matrix, i, j)
    if (i - 1 >= 0 && valid(matrix, i - 1, j)) res += dfs(matrix, i - 1, j, valid, exec)
    if (i + 1 < matrix.length && valid(matrix, i + 1, j)) res += dfs(matrix, i + 1, j, valid, exec)
    if (j - 1 >= 0 && valid(matrix, i, j - 1)) res += dfs(matrix, i, j - 1, valid, exec)
    if (j + 1 < matrix(0).length && valid(matrix, i, j + 1)) res += dfs(matrix, i, j + 1, valid, exec)
    res
  }

  def topologicalSort(n: Int, edges: Array[Array[Int]], dir: Boolean = true,
                      condition: Int => Boolean = _ == 0): List[List[Int]] = {
    import scala.collection.mutable
    val in = new Array[Int](n)
    val map = new mutable.HashMap[Int, mutable.HashSet[Int]]
    for (e <- edges) {
      // 默认为 e(0) 指向 e(1)
      if (!map.contains(e(0))) map(e(0)) = new mutable.HashSet[Int]
      map(e(0)) += e(1)
      in(e(1)) += 1
      if (!dir) {
        if (!map.contains(e(1))) map(e(1)) = new mutable.HashSet[Int]
        map(e(1)) += e(0)
        in(e(0)) += 1
      }
    }
    val res = new mutable.ListBuffer[List[Int]]
    val queue = new mutable.Queue[Int]
    for (i <- in.indices if condition(in(i))) queue.enqueue(i)
    if (queue.isEmpty) return List.empty
    var remain = n - queue.size
    res += queue.toList
    while (remain > 0 && queue.nonEmpty) {
      val size = queue.size
      for (_ <- 0 until size) {
        val deq = queue.dequeue()
        if (map.contains(deq)){
          for (v <- map(deq)) {
            in(v) -= 1
            if (condition(in(v))) queue.enqueue(v)
          }
        }
      }
      remain -= queue.size
      res += queue.toList
    }
    if (remain > 0) List.empty else res.toList
  }
}
