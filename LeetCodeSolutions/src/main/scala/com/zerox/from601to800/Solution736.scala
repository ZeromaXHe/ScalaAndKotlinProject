package com.zerox.from601to800

/**
 * @author zhuxi
 * @since 2022/7/6 10:04
 * @note
 * 736. Lisp 语法解析 | 难度：困难 | 标签：栈、递归、哈希表、字符串
 * 给你一个类似 Lisp 语句的字符串表达式 expression，求出其计算结果。
 *
 * 表达式语法如下所示:
 *
 * 表达式可以为整数，let 表达式，add 表达式，mult 表达式，或赋值的变量。表达式的结果总是一个整数。
 * (整数可以是正整数、负整数、0)
 * let 表达式采用 "(let v1 e1 v2 e2 ... vn en expr)" 的形式，其中 let 总是以字符串 "let"来表示，接下来会跟随一对或多对交替的变量和表达式，也就是说，第一个变量 v1被分配为表达式 e1 的值，第二个变量 v2 被分配为表达式 e2 的值，依次类推；最终 let 表达式的值为 expr表达式的值。
 * add 表达式表示为 "(add e1 e2)" ，其中 add 总是以字符串 "add" 来表示，该表达式总是包含两个表达式 e1、e2 ，最终结果是 e1 表达式的值与 e2 表达式的值之 和 。
 * mult 表达式表示为 "(mult e1 e2)" ，其中 mult 总是以字符串 "mult" 表示，该表达式总是包含两个表达式 e1、e2，最终结果是 e1 表达式的值与 e2 表达式的值之 积 。
 * 在该题目中，变量名以小写字符开始，之后跟随 0 个或多个小写字符或数字。为了方便，"add" ，"let" ，"mult" 会被定义为 "关键字" ，不会用作变量名。
 * 最后，要说一下作用域的概念。计算变量名所对应的表达式时，在计算上下文中，首先检查最内层作用域（按括号计），然后按顺序依次检查外部作用域。测试用例中每一个表达式都是合法的。有关作用域的更多详细信息，请参阅示例。
 *
 * 示例 1：
 * 输入：expression = "(let x 2 (mult x (let x 3 y 4 (add x y))))"
 * 输出：14
 * 解释：
 * 计算表达式 (add x y), 在检查变量 x 值时，
 * 在变量的上下文中由最内层作用域依次向外检查。
 * 首先找到 x = 3, 所以此处的 x 值是 3 。
 *
 * 示例 2：
 * 输入：expression = "(let x 3 x 2 x)"
 * 输出：2
 * 解释：let 语句中的赋值运算按顺序处理即可。
 *
 * 示例 3：
 * 输入：expression = "(let x 1 y 2 x (add x y) (add x y))"
 * 输出：5
 * 解释：
 * 第一个 (add x y) 计算结果是 3，并且将此值赋给了 x 。
 * 第二个 (add x y) 计算结果是 3 + 2 = 5 。
 *
 * 提示：
 * 1 <= expression.length <= 2000
 * exprssion 中不含前导和尾随空格
 * expressoin 中的不同部分（token）之间用单个空格进行分隔
 * 答案和所有中间计算结果都符合 32-bit 整数范围
 * 测试用例中的表达式均为合法的且最终结果为整数
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/parse-lisp-expression
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution736 {
  def main(args: Array[String]): Unit = {
    println(evaluate("(let x 3 x 2 x)") == 2)
    println(evaluate("(let x 2 (mult x (let x 3 y 4 (add x y))))") == 14)
    println(evaluate("(let x 1 y 2 x (add x y) (add x y))") == 5)
    println(evaluate("(let x 7 -12)") == -12)
  }

  val varMap = new scala.collection.mutable.HashMap[String, scala.collection.mutable.ListBuffer[Int]]

  /**
   * 执行用时：512 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：52.5 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：66 / 66
   *
   * 负数真的恶心……
   *
   * @param expression
   * @return
   */
  def evaluate(expression: String): Int = {
    varMap.clear()
    parseExpression(expression, 0)._1
  }

  def parseExpression(expression: String, from: Int): (Int, Int) = {
    expression(from) match {
      case '(' => parseParenInner(expression, from + 1)
      case ch if Character.isDigit(ch) || ch == '-' => parseInt(expression, from)
      case _ => parseVar(expression, from)
    }
  }

  def parseParenInner(expression: String, from: Int): (Int, Int) = {
    if (from + 4 < expression.length) {
      if (expression(from) == 'l' && expression(from + 1) == 'e'
        && expression(from + 2) == 't' && expression(from + 3) == ' ') {
        return parseLet(expression, from)
      } else if (expression(from) == 'a' && expression(from + 1) == 'd'
        && expression(from + 2) == 'd' && expression(from + 3) == ' ') {
        return parseAdd(expression, from)
      } else if (from + 5 < expression.length && expression(from) == 'm' && expression(from + 1) == 'u'
        && expression(from + 2) == 'l' && expression(from + 3) == 't' && expression(from + 4) == ' ') {
        return parseMult(expression, from)
      }
    }
    throw new RuntimeException("should not come here")
  }

  def parseInt(expression: String, from: Int): (Int, Int) = {
    var to = from
    while (to < expression.length && (Character.isDigit(expression(to)) || expression(to) == '-')) {
      to += 1
    }
    (expression.substring(from, to).toInt, to)
  }

  def parseVar(expression: String, from: Int): (Int, Int) = {
    var to = from
    while (to < expression.length && expression(to) != ' ' && expression(to) != ')') {
      to += 1
    }
    (varMap(expression.substring(from, to)).head, to)
  }

  def parseLet(expression: String, from: Int): (Int, Int) = {
    var index = from + 4
    val set = new scala.collection.mutable.HashSet[String]
    while (index < expression.length && expression(index) != ')') {
      if (Character.isDigit(expression(index)) || expression(index) == '-') {
        // 最后一个数字表达式
        set.foreach(varMap(_).remove(0))
        val tuple = parseInt(expression, index)
        return (tuple._1, tuple._2 + 1)
      }
      if (expression(index) == '(') {
        // 最后一个括号内的表达式
        val tuple = parseParenInner(expression, index + 1)
        set.foreach(varMap(_).remove(0))
        return (tuple._1, tuple._2 + 1)
      }
      var to = index
      while (expression(to) != ' ' && expression(to) != ')') {
        to += 1
      }
      val key = expression.substring(index, to)
      if (expression(to) == ')') {
        // 最后一个变量表达式
        val value = varMap(key).head
        set.foreach(varMap(_).remove(0))
        return (value, to + 1)
      }
      index = to + 1
      val tuple = parseExpression(expression, index)
      if (set(key)) {
        // let 里面可能重复定义变量
        varMap(key).remove(0)
      } else {
        set += key
        if (!varMap.contains(key)) varMap.put(key, new scala.collection.mutable.ListBuffer[Int])
      }
      varMap(key) prepend tuple._1
      index = tuple._2 + 1
    }
    throw new RuntimeException("should not come here")
  }

  def parseMult(expression: String, from: Int): (Int, Int) = {
    val tuple1 = parseExpression(expression, from + 5)
    val tuple2 = parseExpression(expression, tuple1._2 + 1)
    (tuple1._1 * tuple2._1, tuple2._2 + 1)
  }

  def parseAdd(expression: String, from: Int): (Int, Int) = {
    val tuple1 = parseExpression(expression, from + 4)
    val tuple2 = parseExpression(expression, tuple1._2 + 1)
    (tuple1._1 + tuple2._1, tuple2._2 + 1)
  }
}
