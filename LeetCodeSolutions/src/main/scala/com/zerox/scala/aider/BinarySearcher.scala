package com.zerox.scala.aider

/**
 * @author zhuxi
 * @since 2022/9/5 13:48
 * @note
 * 二分查找类
 */
class BinarySearcher[T](value: Int => T) {

  // 使用 this(arr.apply) 无法通过编译；this(arr.apply(_))) 可以编译但是会有警告让你把 _ 省略
  def this(arr: Array[T]) = this(i => arr(i))

  /**
   * 表示找到的索引和结果
   *
   * @param index  当结果为 Some 时，对应结果的索引；当结果为 None 时，对应插入点
   * @param result 为 None 时说明没找到
   */
  case class BinarySearchResult(index: Int, result: Option[T])

  def binarySearch(from: Int, to: Int, left: T => Boolean, right: T => Boolean,
                   findLeftest: Boolean, findRightest: Boolean): BinarySearchResult = {
    var l = from
    var r = to
    var res: BinarySearchResult = null
    while (l < r) {
      // 当 l 和 r 均保证大于零时（其实更准确来说是 r - l 不溢出），可以写成 val mid = l + (r - l) / 2
      val mid = ((l.toLong + r) / 2).toInt
      if (left(value(mid))) l = mid + 1
      else if (right(value(mid))) r = mid
      else {
        res = BinarySearchResult(mid, Some(value(mid)))
        if (findLeftest) r = mid
        else if (findRightest) l = mid + 1
        else return res
      }
    }
    if (res == null) BinarySearchResult(l, None) else res
  }
}
