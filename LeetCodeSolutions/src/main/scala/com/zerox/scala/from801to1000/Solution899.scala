package com.zerox.scala.from801to1000

/**
 * @author zhuxi
 * @since 2022/8/3 9:53
 * @note
 * 899. 有序队列 | 难度：困难 | 标签：数学、字符串、排序
 * 给定一个字符串 s 和一个整数 k 。你可以从 s 的前 k 个字母中选择一个，并把它加到字符串的末尾。
 *
 * 返回 在应用上述步骤的任意数量的移动后，字典上最小的字符串 。
 *
 * 示例 1：
 * 输入：s = "cba", k = 1
 * 输出："acb"
 * 解释：
 * 在第一步中，我们将第一个字符（“c”）移动到最后，获得字符串 “bac”。
 * 在第二步中，我们将第一个字符（“b”）移动到最后，获得最终结果 “acb”。
 *
 * 示例 2：
 * 输入：s = "baaca", k = 3
 * 输出："aaabc"
 * 解释：
 * 在第一步中，我们将第一个字符（“b”）移动到最后，获得字符串 “aacab”。
 * 在第二步中，我们将第三个字符（“c”）移动到最后，获得最终结果 “aaabc”。
 *
 * 提示：
 * 1 <= k <= S.length <= 1000
 * s 只由小写字母组成。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/orderly-queue
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution899 {
  def main(args: Array[String]): Unit = {
    // "aagtkuqxitavoyjqiupzadbdyymyvuteolyeerecnuptghlzsynozeuuvteryojyokpufanyrqqmtgxhyycltlnusyeyyqygwupc"
    println(orderlyQueue("xitavoyjqiupzadbdyymyvuteolyeerecnuptghlzsynozeuuvteryojyokpufanyrqqmtgxhyycltlnusyeyyqygwupcaagtkuq", 1))
  }

  /**
   * 执行用时：496 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：52.7 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：110 / 110
   *
   * @param s
   * @param k
   * @return
   */
  def orderlyQueue(s: String, k: Int): String = {
    if (k == 1) {
      val n = s.length
      var minChar = s(0)
      val indexes = new scala.collection.mutable.ListBuffer[Int]
      indexes.append(0)
      for (i <- 1 until n) {
        if (s(i) <= minChar) {
          if (s(i) < minChar) {
            minChar = s(i)
            indexes.clear()
          }
          indexes.append(i)
        }
      }
      val sort = indexes.sortWith((i1, i2) => {
        var count = 0
        var result = false
        while (count < n) {
          if (s((i1 + count) % n) != s((i2 + count) % n)) {
            if (s((i1 + count) % n) < s((i2 + count) % n)) {
              result = true
            }
            count = n
          }
          count += 1
        }
        result
      })
      s.substring(sort.head) + s.substring(0, sort.head)
    } else s.sorted
  }
}
