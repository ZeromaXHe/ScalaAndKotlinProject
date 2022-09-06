package com.zerox.scala.aider

/**
 * @author zhuxi
 * @since 2022/9/5 13:48
 * @note
 * 二分查找类
 */
class BinarySearcher[T](value: Long => T)(implicit ord: Ordering[T]) {
  def this(arr: Array[T])(implicit ord: Ordering[T]) = {
    // 使用 this(arr.apply) 无法通过编译；this(arr.apply(_))) 可以编译但是会有警告让你把 _ 省略
    this(l => arr(l.toInt))(ord)
    defaultTo = arr.length
  }

  var defaultTo: Long = 1e9.toLong

  /**
   * 表示找到的索引和结果
   *
   * @param index  当结果为 Some 时，对应结果的索引；当结果为 None 时，对应插入点
   * @param result 为 None 时说明没找到
   */
  case class BinarySearchResult(index: Long, result: Option[T])

  /**
   * 二分查找
   *
   * @param target       想找到的值
   * @param from         范围从该索引开始（包含）
   * @param to           范围在该索引结束（不包含）
   * @param findLeast    为 true 且存在多个 target 时，需要找到索引最小的
   * @param findGreatest 为 true 且存在多个 target 时，需要找到索引最大的
   * @param less         小于 target 的元素可以作为结果返回
   * @param greater      大于 target 的元素可以作为结果返回
   * @return
   */
  def binarySearch(target: T, from: Long = 0L, to: Long = defaultTo,
                   findLeast: Boolean = false, findGreatest: Boolean = false,
                   less: Boolean = false, greater: Boolean = false): BinarySearchResult = {
    var l = from
    var r = to
    var res: BinarySearchResult = null
    while (l < r) {
      // 当 l 和 r 均保证大于零时（其实更准确来说是 r - l 不溢出），可以写成 val mid = l + (r - l) / 2
      // 按照力扣一般情况，l + r 并不会取到让 Long 都溢出的值，所以此处不做判断
      val mid = (l + r) / 2
      if (ord.lt(value(mid), target)) {
        l = mid + 1
        if (less) res = BinarySearchResult(mid, Some(value(mid)))
      } else if (ord.gt(value(mid), target)) {
        r = mid
        if (greater) res = BinarySearchResult(mid, Some(value(mid)))
      } else {
        res = BinarySearchResult(mid, Some(value(mid)))
        if (findLeast) r = mid
        else if (findGreatest) l = mid + 1
        else return res
      }
    }
    if (res == null) BinarySearchResult(l, None) else res
  }
}
