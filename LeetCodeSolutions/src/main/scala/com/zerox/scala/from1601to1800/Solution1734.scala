package com.zerox.scala.from1601to1800

/**
 * @author zhuxi
 * @since 2022/9/1 14:41
 * @note
 * 1734. 解码异或后的排列 | 难度：中等 | 标签：位运算、数组
 * 给你一个整数数组 perm ，它是前 n 个正整数的排列，且 n 是个 奇数 。
 *
 * 它被加密成另一个长度为 n - 1 的整数数组 encoded ，满足 encoded[i] = perm[i] XOR perm[i + 1] 。比方说，如果 perm = [1,3,2] ，那么 encoded = [2,1] 。
 *
 * 给你 encoded 数组，请你返回原始数组 perm 。题目保证答案存在且唯一。
 *
 * 示例 1：
 * 输入：encoded = [3,1]
 * 输出：[1,2,3]
 * 解释：如果 perm = [1,2,3] ，那么 encoded = [1 XOR 2,2 XOR 3] = [3,1]
 *
 * 示例 2：
 * 输入：encoded = [6,5,4,6]
 * 输出：[2,4,1,5,3]
 *
 * 提示：
 * 3 <= n < 105
 * n 是奇数。
 * encoded.length == n - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/decode-xored-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1734 {
  /**
   * 执行用时：1048 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：75.8 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：63 / 63
   *
   * @param encoded
   * @return
   */
  def decode(encoded: Array[Int]): Array[Int] = {
    val len = encoded.length
    // 快速计算 1 到 n 的异或
    var xor = (len + 1) % 4 match {
      case 0 => len + 1
      case 1 => 1
      case 2 => len + 2
      case 3 => 0
    }
    for (i <- 1 until len by 2) {
      xor ^= encoded(i)
    }
    encoded.scanLeft(xor)((xor, i) => xor ^ i)
  }
}
