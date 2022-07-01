package com.zerox

/**
 * @author zhuxi
 * @since 2022/7/1 11:14
 * @note
 * 241. 为运算表达式设计优先级 | 难度：中等 | 标签：递归、记忆化搜索、数学、字符串、动态规划
 * 给你一个由数字和运算符组成的字符串 expression ，按不同优先级组合数字和运算符，计算并返回所有可能组合的结果。你可以 按任意顺序 返回答案。
 *
 * 生成的测试用例满足其对应输出值符合 32 位整数范围，不同结果的数量不超过 104 。
 *
 * 示例 1：
 * 输入：expression = "2-1-1"
 * 输出：[0,2]
 * 解释：
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 *
 * 示例 2：
 * 输入：expression = "2*3-4*5"
 * 输出：[-34,-14,-10,-10,10]
 * 解释：
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 *
 * 提示：
 * 1 <= expression.length <= 20
 * expression 由数字和算符 '+'、'-' 和 '*' 组成。
 * 输入表达式中的所有整数值在范围 [0, 99] 
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/different-ways-to-add-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution241 {
  /**
   * 去掉 val func: (Int, Int) => Int 之后，compute 还是快一些
   *
   * 执行用时：496 ms, 在所有 Scala 提交中击败了 83.33% 的用户
   * 内存消耗：53.5 MB, 在所有 Scala 提交中击败了 83.33% 的用户
   * 通过测试用例：25 / 25
   *
   * @param expression
   * @return
   */
  def diffWaysToCompute(expression: String): List[Int] = {
    val map = new scala.collection.mutable.HashMap[(Int, Int), List[Int]]
    compute(expression, 0, expression.length, map)
  }

  private def compute(expression: String, from: Int, to: Int,
                      map: scala.collection.mutable.HashMap[(Int, Int), List[Int]]): List[Int] = {
    if (map.contains((from, to))) return map((from, to))
    val list = (for (i <- from until to if !Character.isDigit(expression(i))) yield {
      val rightList = compute(expression, i + 1, to, map)
      // 这里如果还是使用 val func: (Int, Int) => Int 进行模式匹配的写法的话，耗时如下：
      // 执行用时：528 ms, 在所有 Scala 提交中击败了 16.67% 的用户
      // 内存消耗：53.1 MB, 在所有 Scala 提交中击败了 100.00% 的用户
      // 通过测试用例：25 / 25
      compute(expression, from, i, map).flatMap(l => rightList.map(r => expression(i) match {
        case '+' => l + r
        case '-' => l - r
        case '*' => l * r
        case '/' => l / r
      }))
    }).flatten.toList
    val result = if (list.isEmpty) List(expression.substring(from, to).toInt) else list
    map((from, to)) = result
    result
  }

  /**
   * 执行用时：556 ms, 在所有 Scala 提交中击败了 16.67% 的用户
   * 内存消耗：57.5 MB, 在所有 Scala 提交中击败了 33.33% 的用户
   * 通过测试用例：25 / 25
   *
   * @param expression
   * @return
   */
  def diffWaysToCompute_withoutMap(expression: String): List[Int] = {
    compute(expression, 0, expression.length)
  }

  private def compute(expression: String, from: Int, to: Int): List[Int] = {
    val list = (for (i <- from until to if !Character.isDigit(expression(i))) yield {
      val func: (Int, Int) => Int = expression(i) match {
        case '+' => (x, y) => x + y
        case '-' => (x, y) => x - y
        case '*' => (x, y) => x * y
        case '/' => (x, y) => x / y
      }
      val rightList = compute(expression, i + 1, to)
      compute(expression, from, i).flatMap(l => rightList.map(r => func(l, r)))
    }).flatten.toList
    val result = if (list.isEmpty) List(expression.substring(from, to).toInt) else list
    result
  }
}
