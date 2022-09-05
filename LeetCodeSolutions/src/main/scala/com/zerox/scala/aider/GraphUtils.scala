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
}
