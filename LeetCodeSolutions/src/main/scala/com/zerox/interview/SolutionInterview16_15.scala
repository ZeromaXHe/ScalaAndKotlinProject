package com.zerox.interview

/**
 * @author zhuxi
 * @since 2022/6/30 16:04
 * @note
 * 面试题 16.15. 珠玑妙算 | 难度：简单 | 标签：哈希表、字符串、计数
 * 珠玑妙算游戏（the game of master mind）的玩法如下。
 *
 * 计算机有4个槽，每个槽放一个球，颜色可能是红色（R）、黄色（Y）、绿色（G）或蓝色（B）。
 * 例如，计算机可能有RGGB 4种（槽1为红色，槽2、3为绿色，槽4为蓝色）。作为用户，你试图猜出颜色组合。打个比方，你可能会猜YRGB。
 * 要是猜对某个槽的颜色，则算一次“猜中”；要是只猜对颜色但槽位猜错了，则算一次“伪猜中”。注意，“猜中”不能算入“伪猜中”。
 *
 * 给定一种颜色组合solution和一个猜测guess，编写一个方法，返回猜中和伪猜中的次数answer，其中answer[0]为猜中的次数，answer[1]为伪猜中的次数。
 *
 * 示例：
 * 输入： solution="RGBY",guess="GGRR"
 * 输出： [1,1]
 * 解释： 猜中1次，伪猜中1次。
 *
 * 提示：
 * len(solution) = len(guess) = 4
 * solution和guess仅包含"R","G","B","Y"这4种字符
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/master-mind-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionInterview16_15 {
  /**
   * 执行用时：480 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：52 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：60 / 60
   *
   * @param solution
   * @param guess
   * @return
   */
  def masterMind(solution: String, guess: String): Array[Int] = {
    var countR = 0
    var countY = 0
    var countG = 0
    var countB = 0
    var exact_guess = 0
    var pseudo_guess = 0
    for (i <- 0 until 4) {
      if (solution(i) == guess(i)) exact_guess += 1
      else {
        solution(i) match {
          case 'R' =>
            if (countR < 0) pseudo_guess += 1
            countR += 1
          case 'Y' =>
            if (countY < 0) pseudo_guess += 1
            countY += 1
          case 'G' =>
            if (countG < 0) pseudo_guess += 1
            countG += 1
          case 'B' =>
            if (countB < 0) pseudo_guess += 1
            countB += 1
        }
        guess(i) match {
          case 'R' =>
            if (countR > 0) pseudo_guess += 1
            countR -= 1
          case 'Y' =>
            if (countY > 0) pseudo_guess += 1
            countY -= 1
          case 'G' =>
            if (countG > 0) pseudo_guess += 1
            countG -= 1
          case 'B' =>
            if (countB > 0) pseudo_guess += 1
            countB -= 1
        }
      }
    }
    Array(exact_guess, pseudo_guess)
  }
}
