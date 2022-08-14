package com.zerox.scala.from601to800

/**
 * @author zhuxi
 * @since 2022/7/29 14:40
 * @note
 * 715. Range 模块 | 难度：困难 | 标签：设计、线段树、有序集合
 * Range模块是跟踪数字范围的模块。设计一个数据结构来跟踪表示为 半开区间 的范围并查询它们。
 *
 * 半开区间 [left, right) 表示所有 left <= x < right 的实数 x 。
 *
 * 实现 RangeModule 类:
 *
 * RangeModule() 初始化数据结构的对象。
 * void addRange(int left, int right) 添加 半开区间 [left, right)，跟踪该区间中的每个实数。添加与当前跟踪的数字部分重叠的区间时，应当添加在区间 [left, right) 中尚未跟踪的任何数字到该区间中。
 * boolean queryRange(int left, int right) 只有在当前正在跟踪区间 [left, right) 中的每一个实数时，才返回 true ，否则返回 false 。
 * void removeRange(int left, int right) 停止跟踪 半开区间 [left, right) 中当前正在跟踪的每个实数。
 *
 * 示例 1：
 *
 * 输入
 * ["RangeModule", "addRange", "removeRange", "queryRange", "queryRange", "queryRange"]
 * [[], [10, 20], [14, 16], [10, 14], [13, 15], [16, 17]]
 * 输出
 * [null, null, null, true, false, true]
 *
 * 解释
 * RangeModule rangeModule = new RangeModule();
 * rangeModule.addRange(10, 20);
 * rangeModule.removeRange(14, 16);
 * rangeModule.queryRange(10, 14); 返回 true （区间 [10, 14) 中的每个数都正在被跟踪）
 * rangeModule.queryRange(13, 15); 返回 false（未跟踪区间 [13, 15) 中像 14, 14.03, 14.17 这样的数字）
 * rangeModule.queryRange(16, 17); 返回 true （尽管执行了删除操作，区间 [16, 17) 中的数字 16 仍然会被跟踪）
 *
 * 提示：
 * 1 <= left < right <= 109
 * 在单个测试用例中，对 addRange 、  queryRange 和 removeRange 的调用总数不超过 104 次
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/range-module
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution715 {
  def main(args: Array[String]): Unit = {
    val module = new RangeModule
    module.addRange(10, 20)
    module.removeRange(14, 16)
    println(module.queryRange(10, 14)) // true
    println(module.queryRange(13, 15)) // false
    println(module.queryRange(16, 17)) // true
    val module2 = new RangeModule
    module2.addRange(6, 8)
    module2.removeRange(7, 8)
    module2.removeRange(8, 9)
    module2.addRange(8, 9)
    module2.removeRange(1, 3)
    module2.addRange(1, 8)
    println(module2.queryRange(2, 4)) // true
    println(module2.queryRange(2, 9)) // true
    println(module2.queryRange(4, 6)) // true
  }

  /**
   * 执行用时：1116 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：64.1 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：54 / 54
   */
  class RangeModule() {
    val map = new java.util.TreeMap[Integer, Int]

    private def connectAndPutRange(l: Int, r: Int) = {
      var nl = l
      var nr = r
      val floor = map.floorKey(l)
      if (floor != null && map.get(floor) == l - 1) {
        nl = floor
        map.remove(floor)
      }
      val ceiling = map.ceilingKey(r)
      if (ceiling != null && ceiling == r + 1) {
        nr = map.get(ceiling)
        map.remove(ceiling)
      }
      map.put(nl, nr)
    }

    def addRange(left: Int, right: Int): Unit = {
      var floor = map.floorKey(right - 1)
      var l = left
      var r = right - 1
      // 弹出所有和要插入的区间有交集的区间
      while (floor != null && map.get(floor) >= l) {
        l = math.min(l, floor)
        r = math.max(r, map.get(floor))
        map.remove(floor)
        floor = map.floorKey(right - 1)
      }
      connectAndPutRange(l, r)
    }

    def queryRange(left: Int, right: Int): Boolean = {
      val floor = map.floorKey(right)
      floor != null && floor <= left && map.get(floor) >= right - 1
    }

    def removeRange(left: Int, right: Int): Unit = {
      var floor = map.floorKey(right - 1)
      var l = left
      var r = right - 1
      // 弹出所有和要删除的区间有交集的区间
      while (floor != null && map.get(floor) >= l) {
        l = math.min(l, floor)
        r = math.max(r, map.get(floor))
        map.remove(floor)
        floor = map.floorKey(right - 1)
      }
      if (left > l) connectAndPutRange(l, left - 1)
      if (r >= right) connectAndPutRange(right, r)

      // var ceiling = map.ceilingKey(left)
      // l = left
      // r = right - 1
      // // 弹出所有和要删除的区间有交集的区间
      // while (ceiling != null && ceiling <= r) {
      //   l = math.min(l, floor)
      //   r = math.max(r, ceiling)
      //   map.remove(ceiling)
      //   ceiling = map.ceilingKey(left)
      // }
      // if (left > l) connectAndPutRange(l, left - 1)
      // if (r >= right) connectAndPutRange(right, r)
    }

  }

  /**
   * Your RangeModule object will be instantiated and called as such:
   * var obj = new RangeModule()
   * obj.addRange(left,right)
   * var param_2 = obj.queryRange(left,right)
   * obj.removeRange(left,right)
   */
}
