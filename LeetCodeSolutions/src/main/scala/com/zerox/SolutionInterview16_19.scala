package com.zerox

/**
 * @author zhuxi
 * @since 2022/6/30 17:49
 * @note
 * 面试题 16.19. 水域大小 | 难度：中等 | 标签：深度优先搜索、广度优先搜索、并查集、数组、矩阵
 * 你有一个用于表示一片土地的整数矩阵land，该矩阵中每个点的值代表对应地点的海拔高度。若值为0则表示水域。由垂直、水平或对角连接的水域为池塘。池塘的大小是指相连接的水域的个数。编写一个方法来计算矩阵中所有池塘的大小，返回值需要从小到大排序。
 *
 * 示例：
 * 输入：
 * [
 * [0,2,1,0],
 * [0,1,0,1],
 * [1,1,0,1],
 * [0,1,0,1]
 * ]
 * 输出： [1,2,4]
 *
 * 提示：
 * 0 < len(land) <= 1000
 * 0 < len(land[i]) <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/pond-sizes-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionInterview16_19 {
  def main(args: Array[String]): Unit = {
    println(pondSizes(Array(Array(0, 2, 1, 0), Array(0, 1, 0, 1), Array(1, 1, 0, 1), Array(0, 1, 0, 1))).mkString("Array(", ", ", ")"))
  }

  /**
   * 执行用时：916 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：96.3 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：27 / 27
   *
   * @param land
   * @return
   */
  def pondSizes(land: Array[Array[Int]]): Array[Int] = {
    if (land.length == 0 || land(0).length == 0) return Array.empty
    val unionSet = new UnionSet(land.length * land(0).length)
    for (i <- land.indices;
         j <- land(0).indices
         if land(i)(j) == 0) {
      val index = i + j * land.length
      unionSet.addPool(index)
      if (i - 1 >= 0 && land(i - 1)(j) == 0) unionSet.union(index, i - 1 + j * land.length)
      if (i - 1 >= 0 && j - 1 >= 0 && land(i - 1)(j - 1) == 0) unionSet.union(index, i - 1 + (j - 1) * land.length)
      if (j - 1 >= 0 && land(i)(j - 1) == 0) unionSet.union(index, i + (j - 1) * land.length)
      if (i + 1 < land.length && j - 1 >= 0 && land(i + 1)(j - 1) == 0) unionSet.union(index, i + 1 + (j - 1) * land.length)
    }
    unionSet.getAllPoolSizeSortedArray()
  }

  class UnionSet(n: Int) {
    /**
     * 当前结点的父亲结点
     */
    private val parent = new Array[Int](n)
    /**
     * 以当前结点为根结点的子树的结点总数
     */
    private val size = new Array[Int](n)
    /**
     * 节点是否是池塘
     */
    private val pool = new Array[Boolean](n)

    for (i <- 0 until n) {
      parent(i) = i
      size(i) = 1
    }

    /**
     * 路径压缩，只要求每个不相交集合的「根结点」的子树包含的结点总数数值正确即可，因此在路径压缩的过程中不用维护数组 size
     *
     * @param x
     * @return
     */
    def find(x: Int): Int = {
      if (x != parent(x)) parent(x) = find(parent(x))
      parent(x)
    }

    def union(x: Int, y: Int): Unit = {
      val rootX = find(x)
      val rootY = find(y)
      if (rootX == rootY) return
      parent(rootX) = rootY
      // 在合并的时候维护数组 size
      size(rootY) += size(rootX)
    }

    /**
     * @param x
     * @return x 在并查集的根结点的子树包含的结点总数
     */
    def getSize(x: Int): Int = {
      val root = find(x)
      size(root)
    }

    def addPool(x: Int) = {
      pool(x) = true
    }

    def getAllPoolSizeSortedArray(): Array[Int] = {
      (for (i <- parent.indices
            if parent(i) == i && pool(i)) yield size(i)).sorted.toArray
    }
  }
}
