package com.zerox.scala.aider

/**
 * @author zhuxi
 * @since 2022/9/5 13:39
 * @note
 * 线段树
 */
class SegmentTree {
  private var left: SegmentTree = null
  private var right: SegmentTree = null
  private var value = 0
  private var lazySign = 0

  def update(start: Int, end: Int, l: Int, r: Int, value: Int): Unit = {
    if (l <= start && end <= r) {
      this.value += value
      this.lazySign += value
      return
    }
    val mid = (start + end) >> 1
    pushDown()
    if (l <= mid) {
      this.left.update(start, mid, l, r, value)
    }
    if (r > mid) {
      this.right.update(mid + 1, end, l, r, value)
    }
    pushUp()
  }

  def query(start: Int, end: Int, l: Int, r: Int): Int = {
    if (l <= start && end <= r) {
      return this.value
    }
    val mid = (start + end) >> 1;
    var ans = 0;
    pushDown();
    if (l <= mid) ans = this.left.query(start, mid, l, r);
    if (r > mid) ans = Math.max(ans, this.right.query(mid + 1, end, l, r));
    ans
  }

  private def pushUp(): Unit = {
    // 每个节点存的是当前区间的最大值
    this.value = Math.max(this.left.value, this.right.value)
  }

  private def pushDown(): Unit = {
    // 没有子节点的话，建立新的子节点
    if (this.left == null) {
      this.left = new SegmentTree()
    }
    if (this.right == null) {
      this.right = new SegmentTree()
    }
    if (this.lazySign == 0) {
      return
    }
    // 将更新值 lazy 向下推
    this.left.value += this.lazySign
    this.right.value += this.lazySign
    this.left.lazySign += this.lazySign
    this.right.lazySign += this.lazySign
    this.lazySign = 0
  }
}
