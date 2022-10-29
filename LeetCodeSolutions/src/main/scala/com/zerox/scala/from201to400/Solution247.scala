package com.zerox.scala.from201to400

/**
 * @author zhuxi
 * @since 2022/10/29 17:00
 * @note
 * 247. 中心对称数 II | 难度：中等 | 标签：递归、数组、字符串
 * 给定一个整数 n ，返回所有长度为 n 的 中心对称数 。你可以以 任何顺序 返回答案。
 *
 * 中心对称数 是一个数字在旋转了 180 度之后看起来依旧相同的数字（或者上下颠倒地看）。
 *
 * 示例 1:
 * 输入：n = 2
 * 输出：["11","69","88","96"]
 *
 * 示例 2:
 * 输入：n = 1
 * 输出：["0","1","8"]
 *
 * 提示：
 * 1 <= n <= 14
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/strobogrammatic-number-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution247 {
  /**
   * 执行用时：600 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：64.7 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：14 / 14
   *
   * @param n
   * @return
   */
  def findStrobogrammatic(n: Int): List[String] = {
    if (n == 1) List("0", "1", "8")
    else if (n == 2) List("11", "69", "88", "96")
    else {
      val list1 = findStrobogrammatic(2)
      val list2 = findStrobogrammatic(n - 2)
      val res = new scala.collection.mutable.ListBuffer[String]
      for (s1 <- list1; s2 <- list2) {
        res.addOne(s1(0) + s2 + s1(1))
      }
      var test = n - 4
      while (test > 0) {
        val list = findStrobogrammatic(test)
        val zeroStr = "0" * ((n - 2 - test) / 2)
        for (s1 <- list1; s2 <- list) {
          res.addOne(s1(0) + zeroStr + s2 + zeroStr + s1(1))
        }
        test -= 2
      }
      if (test == 0) {
        val zeroStr = "0" * (n - 2)
        for (s1 <- list1) {
          res.addOne(s1(0) + zeroStr + s1(1))
        }
      }
      res.toList
    }
  }
}
