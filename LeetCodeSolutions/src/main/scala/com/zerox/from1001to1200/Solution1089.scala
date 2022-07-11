package com.zerox.from1001to1200

/**
 * @author zhuxi
 * @since 2022/6/17 15:52
 * @note
 * 1089. 复写零 | 难度：简单 | 标签：数组、双指针
 * 给你一个长度固定的整数数组 arr，请你将该数组中出现的每个零都复写一遍，并将其余的元素向右平移。
 *
 * 注意：请不要在超过该数组长度的位置写入元素。
 *
 * 要求：请对输入的数组 就地 进行上述修改，不要从函数返回任何东西。
 *
 * 示例 1：
 * 输入：[1,0,2,3,0,4,5,0]
 * 输出：null
 * 解释：调用函数后，输入的数组将被修改为：[1,0,0,2,3,0,0,4]
 *
 * 示例 2：
 * 输入：[1,2,3]
 * 输出：null
 * 解释：调用函数后，输入的数组将被修改为：[1,2,3]
 *
 * 提示：
 * 1 <= arr.length <= 10000
 * 0 <= arr[i] <= 9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/duplicate-zeros
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1089 {
  def main(args: Array[String]): Unit = {
    val arr = Array(1, 0, 2, 3, 0, 4, 5, 0)
    duplicateZeros(arr)
    // 1,0,0,2,3,0,0,4
    println(arr.mkString("Array(", ", ", ")"))
  }

  /**
   * 执行用时：564 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：53.4 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：30 / 30
   *
   * @param arr
   */
  def duplicateZeros(arr: Array[Int]): Unit = {
    var index = 0
    var count = 0

    // 判断复写 0 需要获取的 arr 前几位的数据（存在 count 中，供之后 arr take count 取前 count 位元素）
    for (num <- arr if index < arr.length) {
      if (num == 0) index += 2
      else index += 1
      count += 1
    }

    index = 0
    // arr take count 操作后 foreach 不会受到原 arr 元素变化的影响
    // 如果把这里逻辑放在 for (num <- arr if index < arr.length) 中，不计算 count 直接处理 arr
    // 将会导致第一个 0 之后全部变成 0（每次循环受到 arr 元素影响）
    (arr take count).foreach(num => {
      if (num == 0) {
        arr(index) = 0
        if (index + 1 < arr.length) arr(index + 1) = 0
        index += 2
      } else {
        arr(index) = num
        index += 1
      }
    })
  }
}
