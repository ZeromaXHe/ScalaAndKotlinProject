package com.zerox.scala.from1601to1800

/**
 * @author zhuxi
 * @since 2022/10/19 9:44
 * @note
 * 1700. 无法吃午餐的学生数量 | 难度：简单
 */
object Solution1700 {
  /**
   * 时间 508 ms 击败 100%
   * 内存 55.2 MB 击败 100%
   *
   * @param students
   * @param sandwiches
   * @return
   */
  def countStudents(students: Array[Int], sandwiches: Array[Int]): Int = {
    val count = Array(students.count(_ == 0), 0)
    count(1) = students.length - count(0)
    val queue = new scala.collection.mutable.Queue[Int]
    for (s <- students) queue.enqueue(s)
    var ptr = 0
    while (ptr < sandwiches.length) {
      val top = sandwiches(ptr)
      val anti = (top + 1) % 2
      if (count(top) == 0) return count(anti)
      while (queue.dequeue() != top) {
        queue.enqueue(anti)
      }
      count(top) -= 1
      ptr += 1
    }
    0
  }
}
