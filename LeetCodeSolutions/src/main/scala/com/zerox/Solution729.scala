package com.zerox

/**
 * @author zhuxi
 * @since 2022/7/5 9:56
 * @note
 * 729. 我的日程安排表 I | 难度：中等 | 标签：设计、线段树、二分查找、有序集合
 * 实现一个 MyCalendar 类来存放你的日程安排。如果要添加的日程安排不会造成 重复预订 ，则可以存储这个新的日程安排。
 *
 * 当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生 重复预订 。
 *
 * 日程可以用一对整数 start 和 end 表示，这里的时间是半开区间，即 [start, end), 实数 x 的范围为，  start <= x < end 。
 *
 * 实现 MyCalendar 类：
 *
 * MyCalendar() 初始化日历对象。
 * boolean book(int start, int end) 如果可以将日程安排成功添加到日历中而不会导致重复预订，返回 true 。否则，返回 false 并且不要将该日程安排添加到日历中。
 *
 * 示例：
 * 输入：
 * ["MyCalendar", "book", "book", "book"]
 * [[], [10, 20], [15, 25], [20, 30]]
 * 输出：
 * [null, true, false, true]
 *
 * 解释：
 * MyCalendar myCalendar = new MyCalendar();
 * myCalendar.book(10, 20); // return True
 * myCalendar.book(15, 25); // return False ，这个日程安排不能添加到日历中，因为时间 15 已经被另一个日程安排预订了。
 * myCalendar.book(20, 30); // return True ，这个日程安排可以添加到日历中，因为第一个日程安排预订的每个时间都小于 20 ，且不包含时间 20 。
 *
 * 提示：
 * 0 <= start < end <= 109
 * 每个测试用例，调用 book 方法的次数最多不超过 1000 次。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/my-calendar-i
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution729 {
  /**
   * TreeSet 泛型使用元组 (Int, Int)
   * 执行用时：744 ms, 在所有 Scala 提交中击败了 50.00% 的用户
   * 内存消耗：57.1 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：107 / 107
   *
   * TreeSet 泛型使用数组 Array[Int]
   * 执行用时：768 ms, 在所有 Scala 提交中击败了 50.00% 的用户
   * 内存消耗：57.3 MB, 在所有 Scala 提交中击败了 50.00% 的用户
   * 通过测试用例：107 / 107
   */
  class MyCalendar() {
    // private val booked = new scala.collection.mutable.TreeSet[(Int, Int)]()(Ordering.by[(Int, Int), Int](t => t._1))
    // Scala 2.13 的 TreeSet 才有 minAfter / maxBefore 方法，只能先用 java.util.TreeSet 了
    // 而 Ordering 特质貌似混入了 Comparator 接口（trait Ordering[T] extends Comparator[T]），可以直接用
    private val booked = new java.util.TreeSet[(Int, Int)](Ordering.by[(Int, Int), Int](t => t._1))
    // 看提交记录里面，貌似已经有答案在用 2.12 的 mutable.TreeMap 了
    // 力扣《各语言对应版本和环境》：https://support.leetcode.cn/hc/kb/article/1194343/ 却没有更新…… 写的是 2.11.6
    // （实际测试了一下，scala.collection.mutable.TreeSet 也可以使用 minAfter 方法，所以应该实际编译版本最起码有 2.13）

    def book(start: Int, end: Int): Boolean = {
      if (booked.isEmpty) {
        booked.add((start, end))
        return true
      }
      val bigger = booked.ceiling((end, end))
      val test = if (bigger == null) booked.last() else booked.lower(bigger)
      if (test == null || test._2 <= start) {
        booked.add((start, end))
        true
      } else false
    }

  }

  /**
   * 执行用时：708 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：57.1 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：107 / 107
   */
  class MyCalendar_TreeMap() {
    // java.util.TreeMap 的 key 必须使用 Integer 而不是 Int
    // 否则后面 ceilingKey、lowerKey 等方法返回的就是 Int，无法处理返回 null 的情况
    private val booked = new java.util.TreeMap[Integer, Int]

    def book(start: Int, end: Int): Boolean = {
      if (booked.isEmpty) {
        booked.put(start, end)
        return true
      }
      val bigger = booked.ceilingKey(end)
      val test = if (bigger == null) booked.lastKey() else booked.lowerKey(bigger)
      if (test == null || booked.get(test) <= start) {
        booked.put(start, end)
        true
      } else false
    }

  }

  /**
   * 执行用时：740 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：57.2 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：107 / 107
   *
   * SegTree 使用 null 实现反而慢了：
   * 执行用时：816 ms, 在所有 Scala 提交中击败了 50.00% 的用户
   * 内存消耗：57.4 MB, 在所有 Scala 提交中击败了 50.00% 的用户
   * 通过测试用例：107 / 107
   */
  class MyCalendar_SegTree() {
    private val root = new SegTree(-2, -1)

    def book(start: Int, end: Int): Boolean = {
      root.book(start, end)
    }

  }

  class SegTree(min: Int, max: Int) {
    private var left: Option[SegTree] = None
    private var right: Option[SegTree] = None

    def book(start: Int, end: Int): Boolean = {
      if (end <= this.min) {
        if (left.isEmpty) {
          left = Some(new SegTree(start, end))
          true
        } else left.get.book(start, end)
      } else if (start >= this.max) {
        if (right.isEmpty) {
          right = Some(new SegTree(start, end))
          true
        } else right.get.book(start, end)
      } else false
    }
  }

  /**
   * Your MyCalendar object will be instantiated and called as such:
   * var obj = new MyCalendar()
   * var param_1 = obj.book(start,end)
   */
}
