package com.zerox.scala.aider

/**
 * @author zhuxi
 * @since 2022/9/5 10:55
 * @note
 * 图论相关的工具类
 */
object GraphUtils {
  /**
   * 深度优先搜索
   *
   * @param matrix        矩阵表示的图
   * @param i             行数
   * @param j             列数
   * @param valid         判断是否合法的下一步的方法
   * @param exec          算出本步结果的方法
   * @param exitCondition 判断是否提前退出的方法
   * @param exitRes       计算提前退出的返回值的方法
   * @param reduceRes     如何把结果合并的方法
   * @param postExec      计算出结果的后处理
   * @tparam T 矩阵的类型
   * @return
   */
  def dfs[T](matrix: Array[Array[T]], i: Int, j: Int,
             valid: (Int, Int, Int, Int) => Boolean,
             exec: (Int, Int) => Int,
             reduceRes: (Int, Int) => Int,
             exitCondition: (Int, Int) => Boolean = (_, _) => false,
             exitRes: (Int, Int) => Int = (_, _) => 0,
             postExec: (Int, Int, Int) => Int = (_, _, res) => res): Int = {
    if (exitCondition(i, j)) return exitRes(i, j)
    var res = exec(i, j)
    if (i - 1 >= 0 && valid(i - 1, j, i, j)) {
      res = reduceRes(res, dfs(matrix, i - 1, j, valid, exec, reduceRes))
    }
    if (i + 1 < matrix.length && valid(i + 1, j, i, j)) {
      res = reduceRes(res, dfs(matrix, i + 1, j, valid, exec, reduceRes))
    }
    if (j - 1 >= 0 && valid(i, j - 1, i, j)) {
      res = reduceRes(res, dfs(matrix, i, j - 1, valid, exec, reduceRes))
    }
    if (j + 1 < matrix(0).length && valid(i, j + 1, i, j)) {
      res = reduceRes(res, dfs(matrix, i, j + 1, valid, exec, reduceRes))
    }
    res = postExec(i, j, res)
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
        if (map.contains(deq)) {
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
