package com.zerox.from601to800

/**
 * @author zhuxi
 * @since 2022/7/19 9:58
 * @note
 * 731. 我的日程安排表 II | 难度：中等 | 标签：设计、线段树、二分查找、有序集合
 * 实现一个 MyCalendar 类来存放你的日程安排。如果要添加的时间内不会导致三重预订时，则可以存储这个新的日程安排。
 *
 * MyCalendar 有一个 book(int start, int end)方法。它意味着在 start 到 end 时间内增加一个日程安排，注意，这里的时间是半开区间，即 [start, end), 实数 x 的范围为，  start <= x < end。
 *
 * 当三个日程安排有一些时间上的交叉时（例如三个日程安排都在同一时间内），就会产生三重预订。
 *
 * 每次调用 MyCalendar.book方法时，如果可以将日程安排成功添加到日历中而不会导致三重预订，返回 true。否则，返回 false 并且不要将该日程安排添加到日历中。
 *
 * 请按照以下步骤调用MyCalendar 类: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 *
 * 示例：
 * MyCalendar();
 * MyCalendar.book(10, 20); // returns true
 * MyCalendar.book(50, 60); // returns true
 * MyCalendar.book(10, 40); // returns true
 * MyCalendar.book(5, 15); // returns false
 * MyCalendar.book(5, 10); // returns true
 * MyCalendar.book(25, 55); // returns true
 * 解释：
 * 前两个日程安排可以添加至日历中。 第三个日程安排会导致双重预订，但可以添加至日历中。
 * 第四个日程安排活动（5,15）不能添加至日历中，因为它会导致三重预订。
 * 第五个日程安排（5,10）可以添加至日历中，因为它未使用已经双重预订的时间10。
 * 第六个日程安排（25,55）可以添加至日历中，因为时间 [25,40] 将和第三个日程安排双重预订；
 * 时间 [40,50] 将单独预订，时间 [50,55）将和第二个日程安排双重预订。
 *
 * 提示：
 * 每个测试用例，调用 MyCalendar.book 函数最多不超过 1000次。
 * 调用函数 MyCalendar.book(start, end)时， start 和 end 的取值范围为 [0, 10^9]。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/my-calendar-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution731 {
  /**
   * 执行用时：876 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：63.2 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：97 / 97
   */
  class MyCalendarTwo() {
    private val root = new SegmentTree
    private val N = 1e9.toInt

    def book(start: Int, end: Int): Boolean = {
      if (root.query(0, N, start, end - 1) == 2) {
        false
      } else {
        root.update(0, N, start, end - 1, 1)
        true
      }
    }

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

      private def pushUp() = {
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

  }

  /**
   * Your MyCalendarTwo object will be instantiated and called as such:
   * var obj = new MyCalendarTwo()
   * var param_1 = obj.book(start,end)
   */
}
