package com.zerox.kotlin.from1001to1200

/**
 * 1154. 一年中的第几天 | 难度：简单 | 标签：数学、字符串
 * 给你一个字符串 date ，按 YYYY-MM-DD 格式表示一个 现行公元纪年法 日期。返回该日期是当年的第几天。
 *
 * 示例 1：
 * 输入：date = "2019-01-09"
 * 输出：9
 * 解释：给定日期是2019年的第九天。
 *
 * 示例 2：
 * 输入：date = "2019-02-10"
 * 输出：41
 *
 * 提示：
 * date.length == 10
 * date[4] == date[7] == '-'，其他的 date[i] 都是数字
 * date 表示的范围从 1900 年 1 月 1 日至 2019 年 12 月 31 日
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/day-of-the-year
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/16 15:46
 */
object Solution1154 {
    val map = mapOf(
        "01" to 0, "02" to 31, "03" to 59, "04" to 90, "05" to 120, "06" to 151,
        "07" to 181, "08" to 212, "09" to 243, "10" to 273, "11" to 304, "12" to 334
    )
    val leapMap = mapOf(
        "01" to 0, "02" to 31, "03" to 60, "04" to 91, "05" to 121, "06" to 152,
        "07" to 182, "08" to 213, "09" to 244, "10" to 274, "11" to 305, "12" to 335
    )

    /**
     * 执行用时：384 ms, 在所有 Kotlin 提交中击败了 11.11% 的用户
     * 内存消耗：36.1 MB, 在所有 Kotlin 提交中击败了 88.89% 的用户
     * 通过测试用例：10958 / 10958
     */
    fun dayOfYear(date: String): Int {
        val split = date.split("-")
        return if (isLeapYear(split[0])) leapMap[split[1]]!! + split[2].toInt()
        else map[split[1]]!! + split[2].toInt()
    }

    private fun isLeapYear(s: String): Boolean {
        val year = s.toInt()
        return if (year % 100 == 0) year % 400 == 0
        else year % 4 == 0
    }
}