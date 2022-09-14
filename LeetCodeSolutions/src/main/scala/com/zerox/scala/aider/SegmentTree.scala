package com.zerox.scala.aider

/**
 * @author zhuxi
 * @since 2022/9/5 13:39
 * @note
 * 线段树
 * @param valueFunc  更新值的函数，第一参数为原值，第二参数为惰性延迟值（lazySign），第三参数为个数
 * @param lazyFunc   更新惰性延迟值（lazySign）的函数，第一参数为原惰性延迟值，第二参数为新惰性延迟值
 * @param reduceFunc 合并结果的函数
 * @param start      线段树的最小值（包含）
 * @param end        线段树的最大值（包含）
 */
class SegmentTree(valueFunc: (Int, Int, Int) => Int,
                  lazyFunc: (Int, Int) => Int,
                  reduceFunc: (Int, Int) => Int,
                  start: Int = 0, end: Int = 1e9.toInt) {

  private val root = new SegmentTreeNode()

  /**
   * 更新 l 到 r（均包含）的值为 value
   *
   * @param l
   * @param r
   * @param value
   */
  def update(l: Int, r: Int, value: Int): Unit = {
    root.update(start, end, l, r, value)
  }

  /**
   * 查询 l 到 r（均包含）的结果
   *
   * @param l
   * @param r
   * @return
   */
  def query(l: Int, r: Int): Int = {
    root.query(start, end, l, r)
  }

  class SegmentTreeNode(var left: SegmentTreeNode = null,
                        var right: SegmentTreeNode = null,
                        var value: Int = 0, var lazySign: Int = 0) {
    def update(start: Int, end: Int, l: Int, r: Int, v: Int): Unit = {
      if (l <= start && end <= r) {
        value = valueFunc(0, v, end - start + 1)
        lazySign = lazyFunc(lazySign, v)
        return
      }
      val mid = (start + end) >> 1
      pushDown(mid - start + 1, end - mid)
      if (l <= mid) left.update(start, mid, l, r, v)
      if (r > mid) right.update(mid + 1, end, l, r, v)
      pushUp()
    }

    def query(start: Int, end: Int, l: Int, r: Int): Int = {
      if (l <= start && end <= r) return this.value
      val mid = (start + end) >> 1
      var ans = 0
      pushDown(mid - start + 1, end - mid)
      if (l <= mid) ans = reduceFunc(ans, this.left.query(start, mid, l, r))
      if (r > mid) ans = reduceFunc(ans, this.right.query(mid + 1, end, l, r))
      ans
    }

    private def pushUp(): Unit = {
      this.value = reduceFunc(this.left.value, this.right.value)
    }

    private def pushDown(leftNum: Int, rightNum: Int): Unit = {
      // 没有子节点的话，建立新的子节点
      if (left == null) left = new SegmentTreeNode()
      if (right == null) right = new SegmentTreeNode()
      if (lazySign == 0) return
      // 将更新值 lazy 向下推
      left.value = valueFunc(left.value, lazySign, leftNum)
      right.value = valueFunc(right.value, lazySign, rightNum)
      left.lazySign = lazyFunc(left.lazySign, lazySign)
      right.lazySign = lazyFunc(right.lazySign, lazySign)
      lazySign = 0
    }
  }
}
