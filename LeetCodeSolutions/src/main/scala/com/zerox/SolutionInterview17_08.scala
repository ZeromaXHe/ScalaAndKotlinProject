package com.zerox

/**
 * @author zhuxi
 * @since 2022/7/9 14:50
 * @note
 * 面试题 17.08. 马戏团人塔 | 难度：中等 | 标签：数组、二分查找、动态规划、排序
 * 有个马戏团正在设计叠罗汉的表演节目，一个人要站在另一人的肩膀上。出于实际和美观的考虑，在上面的人要比下面的人矮一点且轻一点。已知马戏团每个人的身高和体重，请编写代码计算叠罗汉最多能叠几个人。
 *
 * 示例：
 * 输入：height = [65,70,56,75,60,68] weight = [100,150,90,190,95,110]
 * 输出：6
 * 解释：从上往下数，叠罗汉最多能叠 6 层：(56,90), (60,95), (65,100), (68,110), (70,150), (75,190)
 *
 * 提示：
 * height.length == weight.length <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/circus-tower-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionInterview17_08 {
  def main(args: Array[String]): Unit = {
    println(bestSeqAtIndex(Array(65, 70, 56, 75, 60, 68), Array(100, 150, 90, 190, 95, 110)) == 6)
    println(bestSeqAtIndex(Array(65, 70, 56, 75, 60, 68, 75), Array(100, 150, 90, 190, 95, 110, 200)) == 6)
    println(bestSeqAtIndex(Array(5401, 9628, 3367, 6600, 6983, 7853, 5715, 2654, 4453, 8619),
      Array(3614, 1553, 2731, 7894, 8689, 182, 7632, 4465, 8932, 4304)) == 5)
  }

  /**
   * 执行用时：996 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：61.3 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：43 / 43
   *
   * @param height
   * @param weight
   * @return
   */
  def bestSeqAtIndex(height: Array[Int], weight: Array[Int]): Int = {
    val zips = (height zip weight).sorted(Ordering.comparatorToOrdering(
      Ordering.by[(Int, Int), Int](t => t._1).thenComparing(Ordering.by[(Int, Int), Int](t => t._2).reverse)))
    Solution300.lengthOfLIS(zips.map(_._2))
  }
}
