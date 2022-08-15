package com.zerox.kotlin.from801to1000

/**
 * 841. 钥匙和房间 | 难度：中等 | 标签：深度优先搜素、广度优先搜索、图
 * 有 n 个房间，房间按从 0 到 n - 1 编号。最初，除 0 号房间外的其余所有房间都被锁住。你的目标是进入所有的房间。然而，你不能在没有获得钥匙的时候进入锁住的房间。
 *
 * 当你进入一个房间，你可能会在里面找到一套不同的钥匙，每把钥匙上都有对应的房间号，即表示钥匙可以打开的房间。你可以拿上所有钥匙去解锁其他房间。
 *
 * 给你一个数组 rooms 其中 rooms[i] 是你进入 i 号房间可以获得的钥匙集合。如果能进入 所有 房间返回 true，否则返回 false。
 *
 * 示例 1：
 * 输入：rooms = [[1],[2],[3],[]]
 * 输出：true
 * 解释：
 * 我们从 0 号房间开始，拿到钥匙 1。
 * 之后我们去 1 号房间，拿到钥匙 2。
 * 然后我们去 2 号房间，拿到钥匙 3。
 * 最后我们去了 3 号房间。
 * 由于我们能够进入每个房间，我们返回 true。
 *
 * 示例 2：
 * 输入：rooms = [[1,3],[3,0,1],[2],[0]]
 * 输出：false
 * 解释：我们不能进入 2 号房间。
 *
 * 提示：
 * n == rooms.length
 * 2 <= n <= 1000
 * 0 <= rooms[i].length <= 1000
 * 1 <= sum(rooms[i].length) <= 3000
 * 0 <= rooms[i][j] < n
 * 所有 rooms[i] 的值 互不相同
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/keys-and-rooms
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/15 10:31
 */
object Solution841 {
    /**
     * 执行用时：180 ms, 在所有 Kotlin 提交中击败了 100.00% 的用户
     * 内存消耗：38.2 MB, 在所有 Kotlin 提交中击败了 100.00% 的用户
     * 通过测试用例：68 / 68
     */
    fun canVisitAllRooms(rooms: List<List<Int>>): Boolean {
        val visit = BooleanArray(rooms.size)
        visit[0] = true
        var count = 1
        val queue = java.util.LinkedList<Int>()
        queue.offer(0)
        while (queue.isNotEmpty()) {
            var size = queue.size
            while (size > 0) {
                val poll = queue.poll()
                for (key in rooms[poll]) {
                    if (!visit[key]) {
                        queue.offer(key)
                        visit[key] = true
                        count++
                    }
                }
                size--
            }
        }
        return count == rooms.size
    }
}