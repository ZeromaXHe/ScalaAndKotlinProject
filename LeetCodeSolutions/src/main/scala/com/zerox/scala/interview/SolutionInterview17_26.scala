package com.zerox.scala.interview

/**
 * @author zhuxi
 * @since 2022/8/3 11:17
 * @note
 * 面试题 17.26. 稀疏相似度 | 难度：困难 | 标签：数组、哈希表、排序
 * 两个(具有不同单词的)文档的交集(intersection)中元素的个数除以并集(union)中元素的个数，就是这两个文档的相似度。例如，{1, 5, 3} 和 {1, 7, 2, 3} 的相似度是 0.4，其中，交集的元素有 2 个，并集的元素有 5 个。给定一系列的长篇文档，每个文档元素各不相同，并与一个 ID 相关联。它们的相似度非常“稀疏”，也就是说任选 2 个文档，相似度都很接近 0。请设计一个算法返回每对文档的 ID 及其相似度。只需输出相似度大于 0 的组合。请忽略空文档。为简单起见，可以假定每个文档由一个含有不同整数的数组表示。
 *
 * 输入为一个二维数组 docs，docs[i] 表示 id 为 i 的文档。返回一个数组，其中每个元素是一个字符串，代表每对相似度大于 0 的文档，其格式为 {id1},{id2}: {similarity}，其中 id1 为两个文档中较小的 id，similarity 为相似度，精确到小数点后 4 位。以任意顺序返回数组均可。
 *
 * 示例:
 *
 * 输入:
 * [
 *   [14, 15, 100, 9, 3],
 *   [32, 1, 9, 3, 5],
 *   [15, 29, 2, 6, 8, 7],
 *   [7, 10]
 * ]
 * 输出:
 * [
 *   "0,1: 0.2500",
 *   "0,2: 0.1000",
 *   "2,3: 0.1429"
 * ]
 * 提示：
 * docs.length <= 500
 * docs[i].length <= 500
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sparse-similarity-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionInterview17_26 {
  /**
   * 执行用时：2096 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：90.5 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：33 / 33
   *
   * @param docs
   * @return
   */
  def computeSimilarities(docs: Array[Array[Int]]): List[String] = {
    import scala.collection.mutable
    val map = new mutable.HashMap[Int, mutable.ListBuffer[Int]]
    val intersect = Array.ofDim[Int](docs.length, docs.length)
    (for (i <- docs.indices) yield {
      for (j <- docs(i).indices) {
        if (!map.contains(docs(i)(j))) map(docs(i)(j)) = new mutable.ListBuffer[Int]
        else map(docs(i)(j)).foreach(intersect(i)(_) += 1)
        map(docs(i)(j)).append(i)
      }
      for (j <- docs.indices if intersect(i)(j) > 0) yield {
        f"$j,$i: ${(intersect(i)(j).toDouble / (docs(i).length + docs(j).length - intersect(i)(j)))}%.4f"
      }
    }).flatten.toList
  }

  /**
   * 执行用时：2572 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：94.7 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：33 / 33
   *
   * @param docs
   * @return
   */
  def computeSimilarities_hashMap(docs: Array[Array[Int]]): List[String] = {
    import scala.collection.mutable
    val map = new mutable.HashMap[Int, mutable.ListBuffer[Int]]
    val intersect = new mutable.HashMap[Int, Int]
    (for (i <- docs.indices) yield {
      intersect.clear()
      for (j <- docs(i).indices) {
        if (!map.contains(docs(i)(j))) map(docs(i)(j)) = new mutable.ListBuffer[Int]
        else map(docs(i)(j)).foreach(k => if (intersect.contains(k)) intersect(k) += 1 else intersect(k) = 1)
        map(docs(i)(j)).append(i)
      }
      // f 字符串插值器
      intersect.map(e => f"${e._1},$i: ${e._2.toDouble / (docs(i).length + docs(e._1).length - e._2)}%.4f")
    }).flatten.toList
  }
}
