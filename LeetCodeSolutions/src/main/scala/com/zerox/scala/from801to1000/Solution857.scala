package com.zerox.scala.from801to1000

/**
 * @author ZeromaXHe
 * @since 2022/9/11 9:30
 * @note
 * 857. 雇佣 K 名工人的最低成本 | 难度：困难 | 标签：贪心、数组、排序、堆（优先队列）
 * 有 n 名工人。 给定两个数组 quality 和 wage ，其中，quality[i] 表示第 i 名工人的工作质量，其最低期望工资为 wage[i] 。
 *
 * 现在我们想雇佣 k 名工人组成一个工资组。在雇佣 一组 k 名工人时，我们必须按照下述规则向他们支付工资：
 *
 * 对工资组中的每名工人，应当按其工作质量与同组其他工人的工作质量的比例来支付工资。
 * 工资组中的每名工人至少应当得到他们的最低期望工资。
 * 给定整数 k ，返回 组成满足上述条件的付费群体所需的最小金额 。在实际答案的 10-5 以内的答案将被接受。。
 *
 * 示例 1：
 * 输入： quality = [10,20,5], wage = [70,50,30], k = 2
 * 输出： 105.00000
 * 解释： 我们向 0 号工人支付 70，向 2 号工人支付 35。
 *
 * 示例 2：
 * 输入： quality = [3,1,10,10,1], wage = [4,8,2,2,7], k = 3
 * 输出： 30.66667
 * 解释： 我们向 0 号工人支付 4，向 2 号和 3 号分别支付 13.33333。
 *
 * 提示：
 * n == quality.length == wage.length
 * 1 <= k <= n <= 104
 * 1 <= quality[i], wage[i] <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-cost-to-hire-k-workers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution857 {
  /**
   * 执行用时：656 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：57.5 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：46 / 46
   *
   * @param quality
   * @param wage
   * @param k
   * @return
   */
  def mincostToHireWorkers(quality: Array[Int], wage: Array[Int], k: Int): Double = {
    import scala.collection.mutable
    val pq = new mutable.PriorityQueue[Int]()
    val sort = quality.indices.sortBy(i => wage(i).toDouble / quality(i))
    var res = 1e10
    var sumQ = 0.0
    for (i <- 0 until k - 1) {
      sumQ += quality(sort(i))
      pq.enqueue(quality(sort(i)))
    }
    for (i <- k - 1 until quality.length) {
      val idx = sort(i)
      sumQ += quality(idx)
      pq.enqueue(quality(idx))
      val pay = wage(idx).toDouble / quality(idx) * sumQ
      if (pay < res) res = pay
      sumQ -= pq.dequeue()
    }
    res
  }
}
