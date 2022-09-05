package com.zerox.scala.aider

/**
 * @author zhuxi
 * @since 2022/9/5 10:12
 * @note
 * 并查集
 */
class UnionSet(n: Int) {
  private val parent = new Array[Int](n)
  private val size = Array.fill(n)(1)
  private var count = n

  for (i <- 0 until n) {
    parent(i) = i
  }

  def find(x: Int): Int = {
    if (x != parent(x)) parent(x) = find(parent(x))
    parent(x)
  }

  def union(x: Int, y: Int): Unit = {
    val rootX = find(x)
    val rootY = find(y)
    if (rootX == rootY) return
    val big = if (size(rootX) < size(rootY)) rootY else rootX
    val small = if (size(rootX) < size(rootY)) rootX else rootY
    parent(small) = big
    size(big) += size(small)
    count -= 1
  }

  def getSize(x: Int): Int = size(find(x))

  def getCount: Int = count
}
