package com.zerox.scala.from1001to1200

/**
 * @author zhuxi
 * @since 2022/8/25 15:54
 * @note
 * 1146. 快照数组 | 难度：中等 | 标签：
 * 实现支持下列接口的「快照数组」- SnapshotArray：
 *
 * SnapshotArray(int length) - 初始化一个与指定长度相等的 类数组 的数据结构。初始时，每个元素都等于 0。
 * void set(index, val) - 会将指定索引 index 处的元素设置为 val。
 * int snap() - 获取该数组的快照，并返回快照的编号 snap_id（快照号是调用 snap() 的总次数减去 1）。
 * int get(index, snap_id) - 根据指定的 snap_id 选择快照，并返回该快照指定索引 index 的值。
 *
 * 示例：
 * 输入：["SnapshotArray","set","snap","set","get"]
 * [[3],[0,5],[],[0,6],[0,0]]
 * 输出：[null,null,0,null,5]
 * 解释：
 * SnapshotArray snapshotArr = new SnapshotArray(3); // 初始化一个长度为 3 的快照数组
 * snapshotArr.set(0,5);  // 令 array[0] = 5
 * snapshotArr.snap();  // 获取快照，返回 snap_id = 0
 * snapshotArr.set(0,6);
 * snapshotArr.get(0,0);  // 获取 snap_id = 0 的快照中 array[0] 的值，返回 5
 *
 * 提示：
 * 1 <= length <= 50000
 * 题目最多进行50000 次set，snap，和 get的调用 。
 * 0 <= index < length
 * 0 <= snap_id < 我们调用 snap() 的总次数
 * 0 <= val <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/snapshot-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1146 {
  /**
   * 执行用时：1176 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：96.6 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：72 / 72
   *
   * @param _length
   */
  class SnapshotArray(_length: Int) {
    val snaps = Array.fill(_length) {
      val map = new java.util.TreeMap[Int, Int]
      map.put(0, 0)
      map
    }
    var snapId = 0

    def set(index: Int, `val`: Int) {
      snaps(index).put(snapId, `val`)
    }

    def snap(): Int = {
      snapId += 1
      snapId - 1
    }

    def get(index: Int, snap_id: Int): Int = {
      val map = snaps(index)
      map.get(map.floorKey(snap_id))
    }

  }

  /**
   * Your SnapshotArray object will be instantiated and called as such:
   * var obj = new SnapshotArray(length)
   * obj.set(index,`val`)
   * var param_2 = obj.snap()
   * var param_3 = obj.get(index,snap_id)
   */
}
