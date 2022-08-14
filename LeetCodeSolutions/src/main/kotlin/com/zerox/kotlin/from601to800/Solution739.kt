package com.zerox.kotlin.from601to800

/**
 * 739. 每日温度 | 难度：中等 | 标签：栈、数组、单调栈
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。
 * 如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 * 示例 1:
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 *
 * 示例 2:
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 *
 * 示例 3:
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 *
 * 提示：
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/daily-temperatures
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author ZeromaXHe
 * @since 2022/8/14 15:26
 */
object Solution739 {
    /**
     * 执行用时：612 ms, 在所有 Kotlin 提交中击败了 78.57% 的用户
     * 内存消耗：51 MB, 在所有 Kotlin 提交中击败了 46.43% 的用户
     * 通过测试用例：47 / 47
     */
    fun dailyTemperatures(temperatures: IntArray): IntArray {
        val stack = mutableListOf<Pair<Int, Int>>()
        val result = IntArray(temperatures.size)
        for (i in temperatures.indices) {
            while (stack.isNotEmpty() && temperatures[i] > stack.last().first) {
                // 力扣 Kotlin 1.3 没有 removeLast
//                val pre = stack.removeLast().second
                val pre = stack.removeAt(stack.size - 1).second
                result[pre] = i - pre
            }
            stack.add(Pair(temperatures[i], i))
        }
        return result
    }
}