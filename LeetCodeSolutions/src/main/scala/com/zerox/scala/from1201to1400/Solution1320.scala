package com.zerox.scala.from1201to1400

/**
 * @author zhuxi
 * @since 2022/9/1 15:29
 * @note
 * 1320. 二指输入的的最小距离 | 难度：困难 | 标签：字符串、动态规划
 * 二指输入法定制键盘在 X-Y 平面上的布局如上图所示，其中每个大写英文字母都位于某个坐标处。
 *
 * 例如字母 A 位于坐标 (0,0)，字母 B 位于坐标 (0,1)，字母 P 位于坐标 (2,3) 且字母 Z 位于坐标 (4,1)。
 * 给你一个待输入字符串 word，请你计算并返回在仅使用两根手指的情况下，键入该字符串需要的最小移动总距离。
 *
 * 坐标 (x1,y1) 和 (x2,y2) 之间的 距离 是 |x1 - x2| + |y1 - y2|。 
 *
 * 注意，两根手指的起始位置是零代价的，不计入移动总距离。你的两根手指的起始位置也不必从首字母或者前两个字母开始。
 *
 * 示例 1：
 * 输入：word = "CAKE"
 * 输出：3
 * 解释：
 * 使用两根手指输入 "CAKE" 的最佳方案之一是：
 * 手指 1 在字母 'C' 上 -> 移动距离 = 0
 * 手指 1 在字母 'A' 上 -> 移动距离 = 从字母 'C' 到字母 'A' 的距离 = 2
 * 手指 2 在字母 'K' 上 -> 移动距离 = 0
 * 手指 2 在字母 'E' 上 -> 移动距离 = 从字母 'K' 到字母 'E' 的距离  = 1
 * 总距离 = 3
 *
 * 示例 2：
 * 输入：word = "HAPPY"
 * 输出：6
 * 解释：
 * 使用两根手指输入 "HAPPY" 的最佳方案之一是：
 * 手指 1 在字母 'H' 上 -> 移动距离 = 0
 * 手指 1 在字母 'A' 上 -> 移动距离 = 从字母 'H' 到字母 'A' 的距离 = 2
 * 手指 2 在字母 'P' 上 -> 移动距离 = 0
 * 手指 2 在字母 'P' 上 -> 移动距离 = 从字母 'P' 到字母 'P' 的距离 = 0
 * 手指 1 在字母 'Y' 上 -> 移动距离 = 从字母 'A' 到字母 'Y' 的距离 = 4
 * 总距离 = 6
 *
 * 提示：
 * 2 <= word.length <= 300
 * 每个 word[i] 都是一个大写英文字母。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-distance-to-type-a-word-using-two-fingers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1320 {
  /**
   * 执行用时：460 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：52.6 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：55 / 55
   *
   * 参考题解做的，不区分左右手的处理是真的妙。相比官方题解优化了切换移动手的逻辑处的代码
   *
   * @param word
   * @return
   */
  def minimumDistance(word: String): Int = {
    val n = word.length
    // dp(i)(j) 代表一只手处于 word(i) 另一只手处于第 j 个字符时的最小移动。因为两只手是对称的，所以这里不需要区分。
    val dp = Array.fill(n)(Array.fill(26)(Int.MaxValue >> 1))
    for (i <- 0 until 26) dp(0)(i) = 0
    for (i <- 1 until n) {
      val cur = word(i) - 'A'
      val pre = word(i - 1) - 'A'
      val d = dist(cur, pre)
      for (j <- 0 until 26) {
        // 对应上次移动的手和这次相同的情况
        // j 对应本次不移动的手之前处于的字符索引，本轮移动的手从 word(i-1) 上移动到 word(i) 上。
        dp(i)(j) = math.min(dp(i)(j), dp(i - 1)(j) + d)
        // 对应上次移动的手和这次不同的情况
        // 上次移动的手位于 word(i-1) （本次留在原位），而另一只手位于第 j 个字符（本次移动到 word(i)）。
        dp(i)(pre) = math.min(dp(i)(pre), dp(i - 1)(j) + dist(j, cur))
      }
    }
    dp(n - 1).min
  }

  /**
   * 执行用时：556 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：52.6 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：55 / 55
   *
   * 简化至一维 dp
   *
   * @param word
   * @return
   */
  def minimumDistance_dp_O26n(word: String): Int = {
    val n = word.length
    val dp = new Array[Int](26)
    for (i <- 1 until n) {
      val cur = word(i) - 'A'
      val pre = word(i - 1) - 'A'
      val d = dist(cur, pre)
      var min = Int.MaxValue
      for (j <- 0 until 26) {
        // 对应上次移动的手和这次不同的情况
        min = math.min(min, dp(j) + dist(j, cur))
        // 对应上次移动的手和这次相同的情况
        dp(j) += d
      }
      if (min < dp(pre)) dp(pre) = min
    }
    dp.min
  }

  /**
   * 执行用时：500 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：53.1 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：55 / 55
   *
   * 针对单词长度的一维 dp
   *
   * @param word
   * @return
   */
  def minimumDistance_dp_On2(word: String): Int = {
    val n = word.length
    // dp(i) 对应上次换手输入是在输入第 i 个字符时的双手总距离
    val dp = new Array[Int](n - 1)
    // sum 最后等于一只手打完前 n - 1 个字符，另一只手打最后一个字符的双手总距离
    var sum = 0
    for (i <- 1 until n - 1) {
      sum += dist(word, i, i - 1)
      // 默认的赋值 sum 代表另一只手之前没有打过字符，这次换手敲下第 i + 1 个字符的双手总距离
      dp(i) = sum
      val d = dist(word, i, i + 1)
      for (j <- 0 until i) {
        dp(i) = math.min(dp(i), dp(j) + dist(word, i + 1, j))
        dp(j) += d
      }
    }
    math.min(dp.min, sum)
  }

  private def dist(word: String, ia: Int, ib: Int): Int = {
    val a = word(ia) - 'A'
    val b = word(ib) - 'A'
    dist(a, b)
  }

  private def dist(a: Int, b: Int): Int = math.abs(a / 6 - b / 6) + math.abs(a % 6 - b % 6)
}
