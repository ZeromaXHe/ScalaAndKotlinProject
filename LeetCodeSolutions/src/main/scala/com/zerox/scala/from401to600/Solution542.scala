package com.zerox.scala.from401to600

/**
 * @author ZeromaXHe
 * @since 2022/8/13 10:16
 * @note
 * 542. 01 矩阵 | 难度：中等 | 标签：广度优先搜索、数组、动态规划、矩阵
 * 给定一个由 0 和 1 组成的矩阵 mat ，请输出一个大小相同的矩阵，其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。
 *
 * 两个相邻元素间的距离为 1 。
 *
 * 示例 1：
 * 输入：mat = [[0,0,0],[0,1,0],[0,0,0]]
 * 输出：[[0,0,0],[0,1,0],[0,0,0]]
 *
 * 示例 2：
 * 输入：mat = [[0,0,0],[0,1,0],[1,1,1]]
 * 输出：[[0,0,0],[0,1,0],[1,2,1]]
 *
 * 提示：
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 104
 * 1 <= m * n <= 104
 * mat[i][j] is either 0 or 1.
 * mat 中至少有一个 0 
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/01-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution542 {
  /**
   * 执行用时：804 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：58.4 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：50 / 50
   *
   * @param mat
   * @return
   */
  def updateMatrix(mat: Array[Array[Int]]): Array[Array[Int]] = {
    val m = mat.length
    val n = mat(0).length
    val queue = new scala.collection.mutable.Queue[(Int, Int)]
    for (i <- mat.indices; j <- mat(0).indices if mat(i)(j) == 0) {
      enqueueSeaNearLand(mat, i, j, m, n, queue)
    }
    var dist = -1
    while (queue.nonEmpty) {
      var c = queue.size
      while (c > 0) {
        val (x, y) = queue.dequeue()
        enqueueLand(mat, x + 1, y, m, n, dist, queue)
        enqueueLand(mat, x, y + 1, m, n, dist, queue)
        enqueueLand(mat, x, y - 1, m, n, dist, queue)
        enqueueLand(mat, x - 1, y, m, n, dist, queue)
        c -= 1
      }
      dist -= 1
    }
    for (i <- mat.indices; j <- mat(0).indices) mat(i)(j) = -mat(i)(j)
    mat
  }

  private def enqueueLand(mat: Array[Array[Int]], x: Int, y: Int, m: Int, n: Int, dist: Int,
                          queue: scala.collection.mutable.Queue[(Int, Int)]) = {
    if (x >= 0 && y >= 0 && x < m && y < n && mat(x)(y) == 1) {
      queue.enqueue((x, y))
      mat(x)(y) = dist
    }
  }

  private def enqueueSeaNearLand(mat: Array[Array[Int]], x: Int, y: Int, m: Int, n: Int,
                                 queue: scala.collection.mutable.Queue[(Int, Int)]) = {
    if ((x > 0 && mat(x - 1)(y) == 1) || (y > 0 && mat(x)(y - 1) == 1) ||
      (x < m - 1 && mat(x + 1)(y) == 1) || (y < n - 1 && mat(x)(y + 1) == 1)) {
      queue.enqueue((x, y))
    }
  }
}
