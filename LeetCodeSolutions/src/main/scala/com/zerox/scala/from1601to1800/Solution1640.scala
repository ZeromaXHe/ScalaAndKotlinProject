package com.zerox.scala.from1601to1800

/**
 * @author zhuxi
 * @since 2022/9/22 9:57
 * @note
 * 1640. 能否连接形成数组 | 难度：简单 | 标签：数组、哈希表
 * 给你一个整数数组 arr ，数组中的每个整数 互不相同 。另有一个由整数数组构成的数组 pieces，其中的整数也 互不相同 。请你以 任意顺序 连接 pieces 中的数组以形成 arr 。但是，不允许 对每个数组 pieces[i] 中的整数重新排序。
 *
 * 如果可以连接 pieces 中的数组形成 arr ，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 * 输入：arr = [15,88], pieces = [[88],[15]]
 * 输出：true
 * 解释：依次连接 [15] 和 [88]
 *
 * 示例 2：
 * 输入：arr = [49,18,16], pieces = [[16,18,49]]
 * 输出：false
 * 解释：即便数字相符，也不能重新排列 pieces[0]
 *
 * 示例 3：
 * 输入：arr = [91,4,64,78], pieces = [[78],[4,64],[91]]
 * 输出：true
 * 解释：依次连接 [91]、[4,64] 和 [78]
 *
 * 提示：
 * 1 <= pieces.length <= arr.length <= 100
 * sum(pieces[i].length) == arr.length
 * 1 <= pieces[i].length <= arr.length
 * 1 <= arr[i], pieces[i][j] <= 100
 * arr 中的整数 互不相同
 * pieces 中的整数 互不相同（也就是说，如果将 pieces 扁平化成一维数组，数组中的所有整数互不相同）
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/check-array-formation-through-concatenation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1640 {
  /**
   * 执行用时：572 ms, 在所有 Scala 提交中击败了 50.00% 的用户
   * 内存消耗：54.5 MB, 在所有 Scala 提交中击败了 50.00% 的用户
   * 通过测试用例：84 / 84
   *
   * @param arr
   * @param pieces
   * @return
   */
  def canFormArray(arr: Array[Int], pieces: Array[Array[Int]]): Boolean = {
    val map = pieces.groupBy(_ (0)).view.mapValues(_.flatten)
    var i = 0
    while (i < arr.length) {
      if (!map.contains(arr(i))) return false
      val piece = map(arr(i))
      for (j <- piece.indices if arr(i + j) != piece(j)) {
        return false
      }
      i += piece.length
    }
    true
  }
}
