package com.zerox.from601to800

/**
 * @author zhuxi
 * @since 2022/8/5 11:51
 * @note
 * 629. K个逆序对数组 | 难度：困难 | 标签：动态规划
 * 给出两个整数 n 和 k，找出所有包含从 1 到 n 的数字，且恰好拥有 k 个逆序对的不同的数组的个数。
 *
 * 逆序对的定义如下：对于数组的第i个和第 j个元素，如果满i < j且 a[i] > a[j]，则其为一个逆序对；否则不是。
 *
 * 由于答案可能很大，只需要返回 答案 mod 109 + 7 的值。
 *
 * 示例 1:
 * 输入: n = 3, k = 0
 * 输出: 1
 * 解释:
 * 只有数组 [1,2,3] 包含了从1到3的整数并且正好拥有 0 个逆序对。
 *
 * 示例 2:
 * 输入: n = 3, k = 1
 * 输出: 2
 * 解释:
 * 数组 [1,3,2] 和 [2,1,3] 都有 1 个逆序对。
 *
 * 说明:
 *  n 的范围是 [1, 1000] 并且 k 的范围是 [0, 1000]。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/k-inverse-pairs-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution629 {
  /**
   * 执行用时：484 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：51.3 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：80 / 80
   *
   * 参考题解（https://leetcode.cn/problems/k-inverse-pairs-array/solution/629-kge-ni-xu-dui-shu-zu-dong-tai-gui-hu-qxyt/）做的
   * 这道题的转移方程推导是真的牛皮。然后题解里面这种滚动数组动态规划的写法也值得记一下。
   *
   * @param n
   * @param k
   * @return
   */
  def kInversePairs(n: Int, k: Int): Int = {
    val MOD = (1e9 + 7).toInt
    val dp = Array.ofDim[Int](2, k + 1)
    dp(0)(0) = 1
    for (i <- 1 to n; j <- 0 to k) {
      val cur = i & 1
      val prev = cur ^ 1
      dp(cur)(j) = (if (j >= 1) dp(cur)(j - 1) else 0) - (if (j >= i) dp(prev)(j - i) else 0) + dp(prev)(j)
      if (dp(cur)(j) >= MOD) dp(cur)(j) -= MOD
      else if (dp(cur)(j) < 0) dp(cur)(j) += MOD
    }
    dp(n & 1)(k)
  }
}
