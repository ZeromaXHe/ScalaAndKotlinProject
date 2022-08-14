package com.zerox.scala.from1401to1600

/**
 * @author zhuxi
 * @since 2022/8/4 17:07
 * @note
 * 1405. 最长快乐字符串 | 难度：中等 | 标签：贪心、字符串、堆（优先队列）
 * 如果字符串中不含有任何 'aaa'，'bbb' 或 'ccc' 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。
 *
 * 给你三个整数 a，b ，c，请你返回 任意一个 满足下列全部条件的字符串 s：
 *
 * s 是一个尽可能长的快乐字符串。
 * s 中 最多 有a 个字母 'a'、b 个字母 'b'、c 个字母 'c' 。
 * s 中只含有 'a'、'b' 、'c' 三种字母。
 * 如果不存在这样的字符串 s ，请返回一个空字符串 ""。
 *
 * 示例 1：
 * 输入：a = 1, b = 1, c = 7
 * 输出："ccaccbcc"
 * 解释："ccbccacc" 也是一种正确答案。
 *
 * 示例 2：
 * 输入：a = 2, b = 2, c = 1
 * 输出："aabbc"
 *
 * 示例 3：
 * 输入：a = 7, b = 1, c = 0
 * 输出："aabaa"
 * 解释：这是该测试用例的唯一正确答案。
 *
 * 提示：
 * 0 <= a, b, c <= 100
 * a + b + c > 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-happy-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1405 {
  /**
   * 执行用时：432 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：50.8 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：34 / 34
   *
   * @param a
   * @param b
   * @param c
   * @return
   */
  def longestDiverseString(a: Int, b: Int, c: Int): String = {
    val sb = StringBuilder.newBuilder
    var va = a
    var vb = b
    var vc = c
    while ((if (va == 0) 1 else 0) + (if (vb == 0) 1 else 0) + (if (vc == 0) 1 else 0) < 2) {
      if (va > vb && vb >= vc) {
        sb.append("aab")
        va -= 2
        vb -= 1
      } else if (va > vc && vc > vb) {
        sb.append("aac")
        va -= 2
        vc -= 1
      } else if (vb > vc && vc >= va) {
        sb.append("bbc")
        vb -= 2
        vc -= 1
      } else if (vb > va && va > vc) {
        sb.append("bba")
        vb -= 2
        va -= 1
      } else if (vc > va && va >= vb) {
        sb.append("cca")
        vc -= 2
        va -= 1
      } else if (vc > vb && vb > va) {
        sb.append("ccb")
        vc -= 2
        vb -= 1
      } else if (vc == vb && vb > va) {
        sb.append("cb")
        vc -= 1
        vb -= 1
      } else if (vc == va && va > vb) {
        sb.append("ca")
        vc -= 1
        va -= 1
      } else if (vb == va && va > vc) {
        sb.append("ba")
        vb -= 1
        va -= 1
      } else {
        sb.append("abc")
        va -= 1
        vb -= 1
        vc -= 1
      }
    }
    if (va > 0) sb.append(if (va > 1) "aa" else "a")
    if (vb > 0) sb.append(if (vb > 1) "bb" else "b")
    if (vc > 0) sb.append(if (vc > 1) "cc" else "c")
    sb.toString()
  }
}
