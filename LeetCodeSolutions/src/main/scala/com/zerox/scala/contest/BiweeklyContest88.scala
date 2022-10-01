package com.zerox.scala.contest

import com.zerox.scala.aider.SegmentTree

/**
 * @author ZeromaXHe
 * @since 2022/10/1 22:31
 * @note
 */
object BiweeklyContest88 {
  /**
   * 6212. 删除字符使频率相同 | 难度：简单
   * 37 / 37 个通过测试用例
   * 状态：通过
   * 执行用时: 448 ms
   * 内存消耗: 52.3 MB
   *
   * @param word
   * @return
   */
  def equalFrequency(word: String): Boolean = {
    val chCount = new Array[Int](26)
    for (c <- word) {
      chCount(c - 'a') += 1
    }
    val map = new scala.collection.mutable.HashMap[Int, Int]
    for (i <- chCount if i != 0) {
      map(i) = map.getOrElse(i, 0) + 1
      if (map.size > 2) return false
    }
    (map.size == 1 && map.contains(1)) ||
      (map.size == 2 && map.contains(1) && map(1) == 1) ||
      (map.size == 2 && map.keys.max == map.keys.min + 1 && map(map.keys.max) == 1) ||
      (map.size == 1 && map.values.last == 1)
  }

  /**
   * 差了这个条件： (map.size == 1 && map.values.last == 1)
   *
   * @param word
   * @return
   */
  def equalFrequency_fail(word: String): Boolean = {
    val map = word.groupBy(identity).values.map(_.length).groupBy(identity).view.mapValues(_.size).toMap
    (map.size == 1 && map.contains(1)) ||
      (map.size == 2 && map.contains(1) && map(1) == 1) ||
      (map.size == 2 && map.keys.max == map.keys.min + 1 && map(map.keys.max) == 1)
  }

  /**
   * 6197. 最长上传前缀 | 难度：中等
   * 24 / 24 个通过测试用例
   * 状态：通过
   * 执行用时: 1164 ms
   * 内存消耗: 141.2 MB
   *
   * @param _n
   */
  class LUPrefix(_n: Int) {
    val arr = new Array[Boolean](_n + 2)
    var ptr = 0

    def upload(video: Int) {
      arr(video) = true
      if (video == ptr + 1) {
        while (arr(ptr + 1)) {
          ptr += 1
        }
      }
    }

    def longest(): Int = {
      ptr
    }

  }

  /**
   * Your LUPrefix object will be instantiated and called as such:
   * var obj = new LUPrefix(n)
   * obj.upload(video)
   * var param_2 = obj.longest()
   */

  /**
   * 6213. 所有数对的异或和
   * 41 / 41 个通过测试用例
   * 状态：通过
   * 执行用时: 768 ms
   * 内存消耗: 73 MB
   *
   * @param nums1
   * @param nums2
   * @return
   */
  def xorAllNums(nums1: Array[Int], nums2: Array[Int]): Int = {
    val xor1 = nums1.reduce(_ ^ _)
    val xor2 = nums2.reduce(_ ^ _)
    if (nums1.length % 2 == 0 && nums2.length % 2 == 0) 0
    else if (nums1.length % 2 == 0) xor1
    else if (nums2.length % 2 == 0) xor2
    else xor1 ^ xor2
  }

  def numberOfPairs_timeout(nums1: Array[Int], nums2: Array[Int], diff: Int): Long = {
    val diffs = (nums1 zip nums2).map(t => t._1 - t._2)
    val treeMap = new scala.collection.mutable.TreeMap[Int, Int]
    var count = 0
    for (i <- diffs.indices) {
      val cur = diffs(i)
      var opt = treeMap.maxBefore(cur + diff + 1)
      while (opt.nonEmpty) {
        count += opt.get._2
        opt = treeMap.maxBefore(opt.get._1)
      }
      treeMap(cur) = treeMap.getOrElse(cur, 0) + 1
    }
    count
  }

  /**
   * 6198. 满足不等式的数对数目 | 难度：困难
   * 有思路但没做出来
   * 线段树学的就和一坨屎一样！
   * 看别人提交想起来还有树状数组这玩意，靠！
   *
   * @param nums1
   * @param nums2
   * @param diff
   * @return
   */
  def numberOfPairs(nums1: Array[Int], nums2: Array[Int], diff: Int): Long = {
    val diffs = (nums1 zip nums2).map(t => t._1 - t._2)
    var count = 0
    val tree = new SegmentTree(
      (v, l, _) => v + l,
      (l1, l2) => l1 + l2,
      math.max,
      -1e5.toInt, 1e5.toInt)
    for (i <- diffs.indices) {
      val cur = diffs(i)
      count += tree.query(-1e5.toInt, cur + diff)
      tree.update(cur, 1e5.toInt, 1)
    }
    count
  }

  def numberOfPairs_hiddenFail(nums1: Array[Int], nums2: Array[Int], diff: Int): Long = {
    val diffs = (nums1 zip nums2).map(t => t._1 - t._2)
    var count = 0
    val tree = new SegmentTree_h()
    for (i <- diffs.indices) {
      val cur = diffs(i)
      count += tree.query(cur + diff, cur + diff)
      tree.update(cur, 1e5.toInt, 1)
    }
    count
  }

  class SegmentTree_h(start: Int = -1e5.toInt, end: Int = 1e5.toInt) {

    private val root = new SegmentTreeNode()

    def update(l: Int, r: Int, value: Int): Unit = {
      root.update(start, end, l, r, value)
    }

    def query(l: Int, r: Int): Int = {
      root.query(start, end, l, r)
    }

    class SegmentTreeNode(var left: SegmentTreeNode = null,
                          var right: SegmentTreeNode = null,
                          var value: Int = 0, var lazySign: Int = 0) {
      def update(start: Int, end: Int, l: Int, r: Int, v: Int): Unit = {
        if (l <= start && end <= r) {
          value += v
          lazySign += v
          return
        }
        val mid = (start + end) >> 1
        pushDown()
        if (l <= mid) left.update(start, mid, l, r, v)
        if (r > mid) right.update(mid + 1, end, l, r, v)
        pushUp()
      }

      def query(start: Int, end: Int, l: Int, r: Int): Int = {
        if (l <= start && end <= r) return this.value
        val mid = (start + end) >> 1
        var ans = 0
        pushDown()
        if (l <= mid) ans = ans max this.left.query(start, mid, l, r)
        if (r > mid) ans = ans max this.right.query(mid + 1, end, l, r)
        ans
      }

      private def pushUp(): Unit = {
        this.value = this.left.value max this.right.value
      }

      private def pushDown(): Unit = {
        // 没有子节点的话，建立新的子节点
        if (left == null) left = new SegmentTreeNode()
        if (right == null) right = new SegmentTreeNode()
        if (lazySign == 0) return
        // 将更新值 lazy 向下推
        left.value = left.value + lazySign
        right.value = right.value + lazySign
        left.lazySign = left.lazySign + lazySign
        right.lazySign = right.lazySign + lazySign
        lazySign = 0
      }
    }
  }


  def main(args: Array[String]): Unit = {
    println(equalFrequency("ddaccb")) //false
    println(equalFrequency("abcc")) //true
    println(equalFrequency("aazz")) //false

    println(numberOfPairs(Array(3, 2, 5), Array(2, 2, 1), 1)) // 3
    println(numberOfPairs(Array(3, -1), Array(-2, 2), -1)) // 0
    println(numberOfPairs(Array(-3, 3, 2, 4, 5, -1, -4, 2, -2), Array(3, 3, -1, 4, 4, 4, 3, 3, 5), -1)) // 11
  }
}
