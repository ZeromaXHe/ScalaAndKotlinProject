package com.zerox.from2201to2400

/**
 * @author zhuxi
 * @since 2022/7/29 13:47
 * @note
 * 2276. 统计区间中的整数数目 | 难度：困难 | 标签：设计、线段树、有序集合
 * 给你区间的 空 集，请你设计并实现满足要求的数据结构：
 *
 * 新增：添加一个区间到这个区间集合中。
 * 统计：计算出现在 至少一个 区间中的整数个数。
 * 实现 CountIntervals 类：
 *
 * CountIntervals() 使用区间的空集初始化对象
 * void add(int left, int right) 添加区间 [left, right] 到区间集合之中。
 * int count() 返回出现在 至少一个 区间中的整数个数。
 * 注意：区间 [left, right] 表示满足 left <= x <= right 的所有整数 x 。
 *
 * 示例 1：
 * 输入
 * ["CountIntervals", "add", "add", "count", "add", "count"]
 * [[], [2, 3], [7, 10], [], [5, 8], []]
 * 输出
 * [null, null, null, 6, null, 8]
 *
 * 解释
 * CountIntervals countIntervals = new CountIntervals(); // 用一个区间空集初始化对象
 * countIntervals.add(2, 3);  // 将 [2, 3] 添加到区间集合中
 * countIntervals.add(7, 10); // 将 [7, 10] 添加到区间集合中
 * countIntervals.count();    // 返回 6
 * // 整数 2 和 3 出现在区间 [2, 3] 中
 * // 整数 7、8、9、10 出现在区间 [7, 10] 中
 * countIntervals.add(5, 8);  // 将 [5, 8] 添加到区间集合中
 * countIntervals.count();    // 返回 8
 * // 整数 2 和 3 出现在区间 [2, 3] 中
 * // 整数 5 和 6 出现在区间 [5, 8] 中
 * // 整数 7 和 8 出现在区间 [5, 8] 和区间 [7, 10] 中
 * // 整数 9 和 10 出现在区间 [7, 10] 中
 *
 * 提示：
 * 1 <= left <= right <= 109
 * 最多调用  add 和 count 方法 总计 105 次
 * 调用 count 方法至少一次
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/count-integers-in-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution2276 {
  /**
   * 执行用时：1348 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：106.9 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：73 / 73
   */
  class CountIntervals() {
    val map = new java.util.TreeMap[Integer, Int]
    var total = 0

    def add(left: Int, right: Int) {
      var floor = map.floorKey(right)
      var l = left
      var r = right
      // 弹出所有和要插入的区间有交集的区间
      while (floor != null && map.get(floor) >= l) {
        l = math.min(l, floor)
        r = math.max(r, map.get(floor))
        total -= (map.get(floor) - floor + 1)
        map.remove(floor)
        floor = map.floorKey(right)
      }
      total += r - l + 1
      map.put(l, r)
    }

    def count(): Int = {
      total
    }

  }

  /**
   * Your CountIntervals object will be instantiated and called as such:
   * var obj = new CountIntervals()
   * obj.add(left,right)
   * var param_2 = obj.count()
   */
}
