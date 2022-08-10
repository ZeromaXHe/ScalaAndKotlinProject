package com.zerox.from601to800

/**
 * 706. 设计哈希映射 | 难度：简单 | 标签：设计、数组、哈希表、链表、哈希函数
 * 不使用任何内建的哈希表库设计一个哈希映射（HashMap）。
 *
 * 实现 MyHashMap 类：
 *
 * MyHashMap() 用空映射初始化对象
 * void put(int key, int value) 向 HashMap 插入一个键值对 (key, value) 。如果 key 已经存在于映射中，则更新其对应的值 value 。
 * int get(int key) 返回特定的 key 所映射的 value ；如果映射中不包含 key 的映射，返回 -1 。
 * void remove(key) 如果映射中存在 key 的映射，则移除 key 和它所对应的 value 。
 *
 * 示例：
 * 输入：
 * ["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
 * [[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
 * 输出：
 * [null, null, null, 1, -1, null, 1, null, -1]
 *
 * 解释：
 * MyHashMap myHashMap = new MyHashMap();
 * myHashMap.put(1, 1); // myHashMap 现在为 [[1,1]]
 * myHashMap.put(2, 2); // myHashMap 现在为 [[1,1], [2,2]]
 * myHashMap.get(1);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,2]]
 * myHashMap.get(3);    // 返回 -1（未找到），myHashMap 现在为 [[1,1], [2,2]]
 * myHashMap.put(2, 1); // myHashMap 现在为 [[1,1], [2,1]]（更新已有的值）
 * myHashMap.get(2);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,1]]
 * myHashMap.remove(2); // 删除键为 2 的数据，myHashMap 现在为 [[1,1]]
 * myHashMap.get(2);    // 返回 -1（未找到），myHashMap 现在为 [[1,1]]
 *
 * 提示：
 * 0 <= key, value <= 106
 * 最多调用 104 次 put、get 和 remove 方法
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/design-hashmap
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/10 17:00
 */
object Solution706 {
    /**
     * 执行用时：500 ms, 在所有 Kotlin 提交中击败了 62.50% 的用户
     * 内存消耗：57.4 MB, 在所有 Kotlin 提交中击败了 87.50% 的用户
     * 通过测试用例：36 / 36
     */
    class MyHashMap() {
        val bucket = IntArray(1000001) { -1 }

        fun put(key: Int, value: Int) {
            bucket[key] = value
        }

        fun get(key: Int): Int {
            return bucket[key]
        }

        fun remove(key: Int) {
            bucket[key] = -1
        }

    }

    /**
     * Your MyHashMap object will be instantiated and called as such:
     * var obj = MyHashMap()
     * obj.put(key,value)
     * var param_2 = obj.get(key)
     * obj.remove(key)
     */
}