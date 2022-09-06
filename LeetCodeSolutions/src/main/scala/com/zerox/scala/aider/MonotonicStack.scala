package com.zerox.scala.aider

/**
 * @author zhuxi
 * @since 2022/9/6 15:54
 * @note
 * 单调栈
 * 尝试了一下用 implicit 表示泛型中已过时的视界（[ T <% Ordered[T] ]）
 * 然后进一步根据题目要求优化成了自定义排序的 implicit ord: Ordering[T]
 * @param min     true: 保持栈顶最小; false: 保持栈顶最大
 * @param popSame true: 当 push 元素和栈顶相等时 pop 栈顶; false: 同情况下不 pop
 * @param ord     自定义 T 的排序依据
 * @tparam T
 */
class MonotonicStack[T](min: Boolean = true, popSame: Boolean = true)
                       (implicit ord: Ordering[T]) {
  private val stack = new scala.collection.mutable.Stack[T]

  /**
   * 压入单调栈的操作
   *
   * @param elem
   * @param exec 弹出不单调元素时执行的方法
   * @return 弹出所有不单调的元素后的栈顶（即最后的次栈顶）
   */
  def push(elem: T, exec: T => Unit = _ => {}): Option[T] = {
    while (stack.nonEmpty &&
      ((!popSame && ord.equiv(elem, stack.head)) ||
        (min && ord.lt(elem, stack.head)) ||
        (!min && ord.gt(elem, stack.head)))
    ) {
      exec(stack.pop())
    }
    val res = stack.headOption
    stack.push(elem)
    res
  }

  def pop(): T = stack.pop()

  def head: T = stack.head

  def isEmpty: Boolean = stack.isEmpty

  def nonEmpty: Boolean = stack.nonEmpty

  def size: Int = stack.size
}
