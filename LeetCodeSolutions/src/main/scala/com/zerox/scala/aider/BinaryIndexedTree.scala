package com.zerox.scala.aider

/**
 * @author zhuxi
 * @since 2022/9/14 18:05
 * @note
 * 树状数组，也叫做 Fenwick 树
 */
class BinaryIndexedTree(n: Int) {
  val tree = new Array[Int](n + 1)

  def this(arr: Array[Int]) = {
    this(arr.length)
    for (i <- arr.indices) {
      add(i + 1, arr(i))
    }
  }

  /**
   * 为了方便复制，直接写了和 BitOpUtils 中 lowBit 方法同样的代码
   *
   * @param x
   * @return
   */
  private def lowBit(x: Int) = x & -x

  /**
   * 计算小于 x 的元素前缀和
   *
   * @param x
   * @return
   */
  def query(x: Int): Int = {
    var res = 0
    var i = x
    while (i > 0) {
      res += tree(i)
      i -= lowBit(i)
    }
    res
  }

  /**
   * 在树状数组的第 x 索引处加 adder
   *
   * @param x
   * @param adder
   */
  def add(x: Int, adder: Int): Unit = {
    var i = x
    while (i <= n) {
      tree(i) += adder
      i += lowBit(i)
    }
  }
}
