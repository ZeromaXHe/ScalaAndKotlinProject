package com.zerox.scala.from601to800

/**
 * @author ZeromaXHe
 * @since 2022/6/26 17:35
 * @note
 * 710. 黑名单中的随机数 | 难度：困难 | 标签：哈希表、数学、二分查找、排序、随机化
 * 给定一个整数 n 和一个 无重复 黑名单整数数组 blacklist 。设计一种算法，从 [0, n - 1] 范围内的任意整数中选取一个 未加入 黑名单 blacklist 的整数。任何在上述范围内且不在黑名单 blacklist 中的整数都应该有 同等的可能性 被返回。
 *
 * 优化你的算法，使它最小化调用语言 内置 随机函数的次数。
 *
 * 实现 Solution 类:
 *
 * Solution(int n, int[] blacklist) 初始化整数 n 和被加入黑名单 blacklist 的整数
 * int pick() 返回一个范围为 [0, n - 1] 且不在黑名单 blacklist 中的随机整数
 *
 * 示例 1：
 * 输入
 * ["Solution", "pick", "pick", "pick", "pick", "pick", "pick", "pick"]
 * [[7, [2, 3, 5]], [], [], [], [], [], [], []]
 * 输出
 * [null, 0, 4, 1, 6, 1, 0, 4]
 *
 * 解释
 * Solution solution = new Solution(7, [2, 3, 5]);
 * solution.pick(); // 返回0，任何[0,1,4,6]的整数都可以。注意，对于每一个pick的调用，
 * // 0、1、4和6的返回概率必须相等(即概率为1/4)。
 * solution.pick(); // 返回 4
 * solution.pick(); // 返回 1
 * solution.pick(); // 返回 6
 * solution.pick(); // 返回 1
 * solution.pick(); // 返回 0
 * solution.pick(); // 返回 4
 *
 * 提示:
 * 1 <= n <= 109
 * 0 <= blacklist.length <= min(105, n - 1)
 * 0 <= blacklist[i] < n
 * blacklist 中所有值都 不同
 *  pick 最多被调用 2 * 104 次
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/random-pick-with-blacklist
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution710 {
  /**
   * 执行用时：1024 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：69.6 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：68 / 68
   *
   * 使用 zip 直接构造哈希表
   *
   * @param _n
   * @param _blacklist
   */
  class Solution(_n: Int, _blacklist: Array[Int]) {
    private val bound: Int = _n - _blacklist.length
    private val set = _blacklist.filter(black => black >= bound).toSet
    private val map: Map[Int, Int] =
      (_blacklist.filter(black => black < bound))
        .zip((bound until _n).filter(big => !set.contains(big)))
        .toMap

    def pick(): Int = {
      val test = scala.util.Random.nextInt(bound)
      map.getOrElse(test, test)
    }
  }

  /**
   * Your Solution object will be instantiated and called as such:
   * var obj = new Solution(n, blacklist)
   * var param_1 = obj.pick()
   */

  /**
   * 执行用时：988 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：69 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：68 / 68
   *
   * Java 题解改的
   *
   * @param _n
   * @param _blacklist
   */
  class Solution_java(_n: Int, _blacklist: Array[Int]) {
    private val map = new scala.collection.mutable.HashMap[Int, Int]
    private val bound = _n - _blacklist.length
    private val black = _blacklist.filter(b => b >= bound).toSet
    private var w = bound
    for (b <- _blacklist
         if b < bound) {
      while (black.contains(w)) {
        w += 1
      }
      map.put(b, w);
      w += 1
    }

    def pick(): Int = {
      val test = scala.util.Random.nextInt(bound)
      map.getOrElse(test, test)
    }
  }
}
