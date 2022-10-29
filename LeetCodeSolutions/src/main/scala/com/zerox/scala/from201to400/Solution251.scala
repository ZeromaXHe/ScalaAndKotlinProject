package com.zerox.scala.from201to400

/**
 * @author zhuxi
 * @since 2022/10/29 18:34
 * @note
 * 251. 展开二维向量 | 难度：中等 | 标签：设计、数组、双指针、迭代器
 * 请设计并实现一个能够展开二维向量的迭代器。该迭代器需要支持 next 和 hasNext 两种操作。
 *
 * 示例：
 * Vector2D iterator = new Vector2D([[1,2],[3],[4]]);
 *
 * iterator.next(); // 返回 1
 * iterator.next(); // 返回 2
 * iterator.next(); // 返回 3
 * iterator.hasNext(); // 返回 true
 * iterator.hasNext(); // 返回 true
 * iterator.next(); // 返回 4
 * iterator.hasNext(); // 返回 false
 *
 * 注意：
 * 请记得 重置 在 Vector2D 中声明的类变量（静态变量），因为类变量会 在多个测试用例中保持不变，影响判题准确。请 查阅 这里。
 * 你可以假定 next() 的调用总是合法的，即当 next() 被调用时，二维向量总是存在至少一个后续元素。
 *
 * 进阶：尝试在代码中仅使用 C++ 提供的迭代器 或 Java 提供的迭代器。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/flatten-2d-vector
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution251 {
  def main(args: Array[String]): Unit = {
    val iterator = new Vector2D(Array(Array(1, 2), Array(3), Array(4)))
    println(iterator.next()) // 返回 1
    println(iterator.next()) // 返回 2
    println(iterator.next()) // 返回 3
    println(iterator.hasNext()) // 返回 true
    println(iterator.hasNext()) // 返回 true
    println(iterator.next()) // 返回 4
    println(iterator.hasNext()) // 返回 false
  }

  /**
   * 执行用时：644 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：61.7 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：18 / 18
   *
   * @param _vec
   */
  class Vector2D(_vec: Array[Array[Int]]) {
    private val iter: Iterator[Array[Int]] = _vec.iterator
    private var iter2: Iterator[Int] = null

    def next(): Int = {
      hasNext()
      iter2.next()
    }

    def hasNext(): Boolean = {
      if (iter2 == null) {
        if (iter.hasNext) {
          iter2 = iter.next().iterator
        } else return false
      }
      if (iter2.hasNext) return true
      iter2 = null
      hasNext()
    }

  }

  /**
   * Your Vector2D object will be instantiated and called as such:
   * var obj = new Vector2D(vec)
   * var param_1 = obj.next()
   * var param_2 = obj.hasNext()
   */
}
