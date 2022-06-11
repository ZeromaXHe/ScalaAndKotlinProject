package com.zerox.chapter15

import com.zerox.chapter10.Element
import com.zerox.chapter10.Element.elem

/**
 * @Author: zhuxi
 * @Time: 2022/6/11 18:24
 * @Description: 15.8 一个复杂的例子
 * @ModifiedBy: zhuxi
 */
class ExprFormatter {
  // 包含优先级递增的操作符分组
  private val opGroups = Array(
    Set("|", "||"),
    Set("&", "&&"),
    Set("^"),
    Set("==", "!="),
    Set("<", "<=", ">", ">="),
    Set("+", "-"),
    Set("*", "%")
  )

  // 从操作符到对应优先级的映射关系
  private val precedence = {
    val assocs =
      for {
        i <- 0 until opGroups.length
        op <- opGroups(i)
      } yield op -> i

    assocs.toMap
  }

  private val unaryPrecedence = opGroups.length
  private val fractionalPrecedence = -1

  private def format(e: Expr, enclPrec: Int): Element =
    e match {
      case Var(name) => elem(name)
      case Number(num) =>
        def stripDot(s: String) =
          if (s endsWith ".0") s.substring(0, s.length - 2)
          else s

        elem(stripDot(num.toString))

      case UnOp(op, arg) => elem(op) beside format(arg, unaryPrecedence)
      case BinOp("/", left, right) =>
        val top = format(left, fractionalPrecedence)
        val bot = format(right, fractionalPrecedence)
        val line = elem('-', top.width max bot.width, 1)
        val frac = top above line above bot
        if (enclPrec != fractionalPrecedence) frac
        else elem(" ") beside frac beside elem(" ")
      case BinOp(op, left, right) =>
        val opPrec = precedence(op)
        val l = format(left, opPrec)
        val r = format(right, opPrec + 1)
        val oper = l beside elem(" " + op + " ") beside r
        if (enclPrec <= opPrec) oper
        else elem("(") beside oper beside elem(")")
    }

  def format(e: Expr): Element = format(e, 0)
}
