package com.zerox.kotlin.from601to800

/**
 * 641. 设计循环双端队列 | 难度：中等 | 标签：设计、队列、数组、链表
 * 设计实现双端队列。
 *
 * 实现 MyCircularDeque 类:
 *
 * MyCircularDeque(int k) ：构造函数,双端队列最大为 k 。
 * boolean insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true ，否则返回 false 。
 * boolean insertLast() ：将一个元素添加到双端队列尾部。如果操作成功返回 true ，否则返回 false 。
 * boolean deleteFront() ：从双端队列头部删除一个元素。 如果操作成功返回 true ，否则返回 false 。
 * boolean deleteLast() ：从双端队列尾部删除一个元素。如果操作成功返回 true ，否则返回 false 。
 * int getFront() )：从双端队列头部获得一个元素。如果双端队列为空，返回 -1 。
 * int getRear() ：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1 。
 * boolean isEmpty() ：若双端队列为空，则返回 true ，否则返回 false  。
 * boolean isFull() ：若双端队列满了，则返回 true ，否则返回 false 。
 *
 * 示例 1：
 *
 * 输入
 * ["MyCircularDeque", "insertLast", "insertLast", "insertFront", "insertFront", "getRear", "isFull", "deleteLast", "insertFront", "getFront"]
 * [[3], [1], [2], [3], [4], [], [], [], [4], []]
 * 输出
 * [null, true, true, true, false, 2, true, true, true, 4]
 *
 * 解释
 * MyCircularDeque circularDeque = new MycircularDeque(3); // 设置容量大小为3
 * circularDeque.insertLast(1);			        // 返回 true
 * circularDeque.insertLast(2);			        // 返回 true
 * circularDeque.insertFront(3);			        // 返回 true
 * circularDeque.insertFront(4);			        // 已经满了，返回 false
 * circularDeque.getRear();  				// 返回 2
 * circularDeque.isFull();				        // 返回 true
 * circularDeque.deleteLast();			        // 返回 true
 * circularDeque.insertFront(4);			        // 返回 true
 * circularDeque.getFront();				// 返回 4
 *
 * 提示：
 * 1 <= k <= 1000
 * 0 <= value <= 1000
 * insertFront, insertLast, deleteFront, deleteLast, getFront, getRear, isEmpty, isFull  调用次数不大于 2000 次
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/design-circular-deque
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhuxi
 * @since 2022/8/15 9:56
 */
object Solution641 {
    @JvmStatic
    fun main(args: Array<String>) {
        val deque = MyCircularDeque(3)
        println(deque.insertLast(1))
        println(deque.insertLast(2))
        println(deque.insertFront(3))
        println(deque.insertFront(4))
        println(deque.getRear())
        println(deque.isFull())
        println(deque.deleteLast())
        println(deque.insertFront(4))
        println(deque.getFront())
    }

    /**
     * 执行用时：236 ms, 在所有 Kotlin 提交中击败了 100.00% 的用户
     * 内存消耗：37.9 MB, 在所有 Kotlin 提交中击败了 100.00% 的用户
     * 通过测试用例：51 / 51
     */
    class MyCircularDeque(k: Int) {
        private val arr = IntArray(k)
        private var head = 0
        private var tail = 0
        private var count = 0
        private val capacity = k

        fun insertFront(value: Int): Boolean {
            if (isFull()) return false
            head = (head - 1 + capacity) % capacity
            arr[head] = value
            count++
            return true
        }

        fun insertLast(value: Int): Boolean {
            if (isFull()) return false
            arr[tail] = value
            tail = (tail + 1) % capacity
            count++
            return true
        }

        fun deleteFront(): Boolean {
            if (isEmpty()) return false
            head = (head + 1) % capacity
            count--
            return true
        }

        fun deleteLast(): Boolean {
            if (isEmpty()) return false
            tail = (tail - 1 + capacity) % capacity
            count--
            return true
        }

        fun getFront(): Int {
            return if (isEmpty()) -1 else arr[head]
        }

        fun getRear(): Int {
            return if (isEmpty()) -1 else arr[(tail - 1 + capacity) % capacity]
        }

        fun isEmpty(): Boolean {
            return head == tail && count == 0
        }

        fun isFull(): Boolean {
            return head == tail && count == capacity
        }

    }

    /**
     * Your MyCircularDeque object will be instantiated and called as such:
     * var obj = MyCircularDeque(k)
     * var param_1 = obj.insertFront(value)
     * var param_2 = obj.insertLast(value)
     * var param_3 = obj.deleteFront()
     * var param_4 = obj.deleteLast()
     * var param_5 = obj.getFront()
     * var param_6 = obj.getRear()
     * var param_7 = obj.isEmpty()
     * var param_8 = obj.isFull()
     */
}