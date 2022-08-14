package com.zerox.scala.from1401to1600

/**
 * @author zhuxi
 * @since 2022/8/6 17:10
 * @note
 * 1419. 数青蛙 | 难度：中等 | 标签：字符串、计数
 * 给你一个字符串 croakOfFrogs，它表示不同青蛙发出的蛙鸣声（字符串 "croak" ）的组合。由于同一时间可以有多只青蛙呱呱作响，所以 croakOfFrogs 中会混合多个 “croak” 。
 *
 * 请你返回模拟字符串中所有蛙鸣所需不同青蛙的最少数目。
 *
 * 要想发出蛙鸣 "croak"，青蛙必须 依序 输出 ‘c’, ’r’, ’o’, ’a’, ’k’ 这 5 个字母。如果没有输出全部五个字母，那么它就不会发出声音。如果字符串 croakOfFrogs 不是由若干有效的 "croak" 字符混合而成，请返回 -1 。
 *
 * 示例 1：
 * 输入：croakOfFrogs = "croakcroak"
 * 输出：1
 * 解释：一只青蛙 “呱呱” 两次
 *
 * 示例 2：
 * 输入：croakOfFrogs = "crcoakroak"
 * 输出：2
 * 解释：最少需要两只青蛙，“呱呱” 声用黑体标注
 * 第一只青蛙 "crcoakroak"
 * 第二只青蛙 "crcoakroak"
 *
 * 示例 3：
 * 输入：croakOfFrogs = "croakcrook"
 * 输出：-1
 * 解释：给出的字符串不是 "croak" 的有效组合。
 *
 * 提示：
 * 1 <= croakOfFrogs.length <= 105
 * 字符串中的字符只有 'c', 'r', 'o', 'a' 或者 'k'
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-number-of-frogs-croaking
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1419 {
  def main(args: Array[String]): Unit = {
    println(minNumberOfFrogs("croakcroak"))
  }

  val map: Map[Char, Int] = "croak".zipWithIndex.toMap

  /**
   * 执行用时：644 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：54.3 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：58 / 58
   *
   * @param croakOfFrogs
   * @return
   */
  def minNumberOfFrogs(croakOfFrogs: String): Int = {
    val charCount = croakOfFrogs.groupBy(identity).mapValues(_.length)
    if (charCount.size == 5 && "croak".forall(charCount.contains) && charCount.forall(_._2 == charCount('c'))) {
      val count = new Array[Int](4)
      var max = 0
      for (c <- croakOfFrogs) {
        val i = map(c)
        if (i < 4) count(i) += 1
        if (i == 0) max = math.max(max, count(0))
        else if (i == 4) {
          if (count(3) == 0) return -1
          count.indices.foreach(count(_) -= 1)
        } else if (count(i - 1) < count(i)) return -1
      }
      max
    } else -1
  }
}
