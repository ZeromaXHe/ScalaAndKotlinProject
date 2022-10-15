package com.zerox.scala.from1401to1600

/**
 * @author ZeromaXHe
 * @since 2022/10/15 11:05
 * @note
 * 1441. 用栈操作构建数组 | 难度：中等
 */
object Solution1441 {
  /**
   * 时间 464 ms 击败 100%
   * 内存 53.1 MB 击败 100%
   *
   * @param target
   * @param n
   * @return
   */
  def buildArray(target: Array[Int], n: Int): List[String] = {
    val list = new scala.collection.mutable.ListBuffer[String]
    var test = 1
    for (i <- target) {
      while (test < i) {
        list.addOne("Push")
        list.addOne("Pop")
        test += 1
      }
      list.addOne("Push")
      test += 1
    }
    list.toList
  }
}
