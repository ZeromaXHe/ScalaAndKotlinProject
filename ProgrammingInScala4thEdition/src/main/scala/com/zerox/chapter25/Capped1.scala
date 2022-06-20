package com.zerox.chapter25

import scala.collection._

/**
 * @author zhuxi
 * @since 2022/6/20 10:02
 * @note
 * 25.2 集成新的集合
 */
class Capped1[A] private(val capacity: Int, val length: Int, offset: Int, elems: Array[Any]) extends immutable.Iterable[A] {
  self =>
  def this(capacity: Int) = this(capacity, length = 0, offset = 0, elems = Array.ofDim(capacity))

  def appended[B >: A](elem: B): Capped1[B] = {
    val newElems = Array.ofDim[Any](capacity)
    Array.copy(elems, 0, newElems, 0, capacity)
    val (newOffset, newLength) =
      if (length == capacity) {
        newElems(offset) = elem
        ((offset + 1) % capacity, length)
      } else {
        newElems(length) = elem
        (offset, length + 1)
      }
    new Capped1[B](capacity, newLength, newOffset, newElems)
  }

  @inline def :+[B >: A](elem: B): Capped1[B] = appended(elem)

  def apply(i: Int): A = elems((i + offset) % capacity).asInstanceOf[A]

  def iterator: Iterator[A] = new AbstractIterator[A] {
    private var current = 0

    override def hasNext: Boolean = current < self.length

    override def next(): A = {
      val elem = self(current)
      current += 1
      elem
    }
  }
  /// className 方法是 2.13 版本引入的
  // override def className = "Capped1"
}
