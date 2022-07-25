package com.zerox.from2201to2400

/**
 * @author zhuxi
 * @since 2022/7/25 11:47
 * @note
 * 2301. 替换字符后匹配 | 难度：困难 | 标签：数组、哈希表、字符串、字符串匹配
 * 给你两个字符串 s 和 sub 。同时给你一个二维字符数组 mappings ，其中 mappings[i] = [oldi, newi] 表示你可以将 sub 中任意数目的 oldi 字符替换为 newi 。sub 中每个字符 不能 被替换超过一次。
 *
 * 如果使用 mappings 替换 0 个或者若干个字符，可以将 sub 变成 s 的一个子字符串，请你返回 true，否则返回 false 。
 *
 * 一个 子字符串 是字符串中连续非空的字符序列。
 *
 * 示例 1：
 * 输入：s = "fool3e7bar", sub = "leet", mappings = [["e","3"],["t","7"],["t","8"]]
 * 输出：true
 * 解释：将 sub 中第一个 'e' 用 '3' 替换，将 't' 用 '7' 替换。
 * 现在 sub = "l3e7" ，它是 s 的子字符串，所以我们返回 true 。
 *
 * 示例 2：
 * 输入：s = "fooleetbar", sub = "f00l", mappings = [["o","0"]]
 * 输出：false
 * 解释：字符串 "f00l" 不是 s 的子串且没有可以进行的修改。
 * 注意我们不能用 'o' 替换 '0' 。
 *
 * 示例 3：
 * 输入：s = "Fool33tbaR", sub = "leetd", mappings = [["e","3"],["t","7"],["t","8"],["d","b"],["p","b"]]
 * 输出：true
 * 解释：将 sub 里第一个和第二个 'e' 用 '3' 替换，用 'b' 替换 sub 里的 'd' 。
 * 得到 sub = "l33tb" ，它是 s 的子字符串，所以我们返回 true 。
 *
 * 提示：
 * 1 <= sub.length <= s.length <= 5000
 * 0 <= mappings.length <= 1000
 * mappings[i].length == 2
 * oldi != newi
 * s 和 sub 只包含大写和小写英文字母和数字。
 * oldi 和 newi 是大写、小写字母或者是个数字。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/match-substring-after-replacement
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution2301 {
  /**
   * 执行用时：1456 ms, 在所有 Scala 提交中击败了 33.33% 的用户
   * 内存消耗：62 MB, 在所有 Scala 提交中击败了 33.33% 的用户
   * 通过测试用例：113 / 113
   *
   * @param s
   * @param sub
   * @param mappings
   * @return
   */
  def matchReplacement_regex(s: String, sub: String, mappings: Array[Array[Char]]): Boolean = {
    val sb = new StringBuilder
    val map = mappings.groupBy(_ (0)).mapValues(_.map(_ (1)).toSet)
    sub.foreach(c => {
      if (map.contains(c)) {
        sb append '[' append c
        map(c).foreach(sb append _)
        sb append ']'
      }
      else sb append c
    })
    sb.toString().r.findFirstIn(s).nonEmpty
  }

  /**
   * 执行用时：920 ms, 在所有 Scala 提交中击败了 33.33% 的用户
   * 内存消耗：54.8 MB, 在所有 Scala 提交中击败了 33.33% 的用户
   * 通过测试用例：113 / 113
   *
   * @param s
   * @param sub
   * @param mappings
   * @return
   */
  def matchReplacement(s: String, sub: String, mappings: Array[Array[Char]]): Boolean = {
    val arr = Array.tabulate(256)(i => Array.tabulate(256)(j => i == j))
    mappings.withFilter { case Array(_, _) => true; case _ => false }.foreach { case Array(from, to) => arr(from)(to) = true }
    (0 to s.length - sub.length).exists(s0 => (0 until sub.length).forall(i => arr(sub(i))(s(s0 + i))))
  }
}
