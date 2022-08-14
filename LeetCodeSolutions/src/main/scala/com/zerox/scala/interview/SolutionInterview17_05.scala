package com.zerox.scala.interview

/**
 * @author zhuxi
 * @since 2022/6/30 19:06
 * @note
 * 面试题 17.05.  字母与数字 | 难度：中等 | 标签：数组、哈希表、前缀和
 * 给定一个放有字母和数字的数组，找到最长的子数组，且包含的字母和数字的个数相同。
 *
 * 返回该子数组，若存在多个最长子数组，返回左端点下标值最小的子数组。若不存在这样的数组，返回一个空数组。
 *
 * 示例 1:
 * 输入: ["A","1","B","C","D","2","3","4","E","5","F","G","6","7","H","I","J","K","L","M"]
 * 输出: ["A","1","B","C","D","2","3","4","E","5","F","G","6","7"]
 *
 * 示例 2:
 * 输入: ["A","A"]
 * 输出: []
 *
 * 提示：
 * array.length <= 100000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-longest-subarray-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionInterview17_05 {
  def main(args: Array[String]): Unit = {
    // ["52","3","v","71","J","A","0","v","51","E","k","H","96","21","W","59","I","V","s","59","w","X","33","29","H",
    // "32","51","f","i","58","56","66","90","F","10","93","53","85","28","78","d","67","81","T","K","S","l","L","Z",
    // "j","5","R","b","44","R","h","B","30","63","z","75","60","m","61","a","5"]
    println(findLongestSubarray(
      Array("42", "10", "O", "t", "y", "p", "g", "B", "96", "H", "5", "v", "P", "52", "25", "96", "b", "L", "Y", "z", "d", "52", "3", "v",
        "71", "J", "A", "0", "v", "51", "E", "k", "H", "96", "21", "W", "59", "I", "V", "s", "59", "w", "X", "33", "29", "H", "32", "51", "f",
        "i", "58", "56", "66", "90", "F", "10", "93", "53", "85", "28", "78", "d", "67", "81", "T", "K", "S", "l", "L", "Z", "j", "5", "R",
        "b", "44", "R", "h", "B", "30", "63", "z", "75", "60", "m", "61", "a", "5", "S", "Z", "D", "2", "A", "W", "k", "84", "44", "96",
        "96", "y", "M")).mkString("Array(", ", ", ")"))
    // ["A","1","B","C","D","2","3","4","E","5","F","G","6","7"]
    println(findLongestSubarray(Array("A", "1", "B", "C", "D", "2", "3", "4", "E", "5", "F", "G", "6", "7", "H", "I",
      "J", "K", "L", "M")).mkString("Array(", ", ", ")"))
  }

  /**
   * 执行用时：904 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：68.6 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：45 / 45
   *
   * @param array
   * @return
   */
  def findLongestSubarray(array: Array[String]): Array[String] = {
    var from = 0
    var to = 0
    var sum = 0
    val map = new scala.collection.mutable.HashMap[Int, Int]
    for (i <- array.indices) {
      // 这里等于是计算差分数组，数字为 -1，字母为 1
      if (Character.isDigit(array(i)(0))) sum -= 1
      else sum += 1
      // 这里 yield sum 的话，相当于是一个前缀和数组
      if (sum == 0) {
        // 前缀和为 0，说明从开头到当前索引就是数字等于字母
        if (i > to - from) {
          from = -1
          to = i
        }
      } else if (map.contains(sum)) {
        // 否则，找到前一个相同前缀和的索引，从前一个索引后一位到当前索引也是数字等于字母
        if (i - map(sum) > to - from) {
          to = i
          from = map(sum)
        }
      } else {
        map(sum) = i
      }
    }
    array.slice(from + 1, to + 1)
  }
}
