package com.zerox.scala.aider

/**
 * @author zhuxi
 * @since 2022/9/5 16:00
 * @note
 * 二叉树
 */
class BinaryTree[T] (left: T => T, right: T => T) {
  case class Node(x: T, var left: Node = null, var right: Node = null, var parent: Node = null)

  object Order extends Enumeration {
    val PRE, IN, POST = Value
  }

  def traverse[R](order: Order.Value, root: T, exec: T => R): LazyList[R] = {
    val res = LazyList.empty
    if (root == null) return res
    if (order == Order.PRE) res.appended(exec(root))
    res.appended(traverse(order, left(root), exec))
    if (order == Order.IN) res.appended(exec(root))
    res.appended(traverse(order, right(root), exec))
    if (order == Order.POST) res.appended(exec(root))
    res
  }
}
