package com.zerox.from601to800

import java.util.*

/**
 * 658. 找到 K 个最接近的元素 | 难度：中等 | 标签：数组、双指针、二分查找、排序、堆（优先队列）
 * 给定一个 排序好 的数组 arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。
 *
 * 整数 a 比整数 b 更接近 x 需要满足：
 *
 * |a - x| < |b - x| 或者
 * |a - x| == |b - x| 且 a < b
 *
 * 示例 1：
 * 输入：arr = [1,2,3,4,5], k = 4, x = 3
 * 输出：[1,2,3,4]
 *
 * 示例 2：
 * 输入：arr = [1,2,3,4,5], k = 4, x = -1
 * 输出：[1,2,3,4]
 *
 * 提示：
 * 1 <= k <= arr.length
 * 1 <= arr.length <= 104
 * arr 按 升序 排列
 * -104 <= arr[i], x <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-k-closest-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/10 15:20
 */
object Solution658 {
    /**
     * 执行用时：604 ms, 在所有 Kotlin 提交中击败了 50.00% 的用户
     * 内存消耗：45.4 MB, 在所有 Kotlin 提交中击败了 50.00% 的用户
     * 通过测试用例：65 / 65
     */
    fun findClosestElements(arr: IntArray, k: Int, x: Int): List<Int> {
        val zip = arr zip arr.indices
        val heap = PriorityQueue(k,
            compareBy<Pair<Int, Int>> { kotlin.math.abs(it.first - x) }.thenComparingInt { it.second }.reversed()
        )
//        val heap = PriorityQueue(k) { p1: Pair<Int, Int>, p2: Pair<Int, Int> ->
//            if (kotlin.math.abs(p1.first - x) != kotlin.math.abs(p2.first - x)) {
//                kotlin.math.abs(p1.first - x) - kotlin.math.abs(p2.first - x)
//            } else p1.second - p2.second
//        }
        // 力扣没法调用 Java 静态方法
//        val heap = PriorityQueue(k,
//            Comparator.comparingInt { p: Pair<Int, Int> -> kotlin.math.abs(p.first - x) }
//                .thenComparingInt(Pair<Int, Int>::second))
        zip.forEach {
            heap.offer(it);
            if (heap.size == k + 1) heap.poll()
        }
        return heap.toList().map { it.first }.sorted()
    }

    /**
     * 执行用时：392 ms, 在所有 Kotlin 提交中击败了 100.00% 的用户
     * 内存消耗：38.3 MB, 在所有 Kotlin 提交中击败了 100.00% 的用户
     * 通过测试用例：65 / 65
     */
    fun findClosestElements_doublePtr(arr: IntArray, k: Int, x: Int): List<Int> {
        var l = 0
        var r = arr.size
        while (r - l > k) {
            if (kotlin.math.abs(arr[l] - x) <= kotlin.math.abs(arr[r - 1] - x)) r--
            else l++
        }
        return arr.copyOfRange(l, r).toList()
    }
}