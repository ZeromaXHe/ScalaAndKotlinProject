package com.zerox.scala.from201to400

/**
 * @author zhuxi
 * @since 2022/9/21 11:40
 * @note
 * 232. 用栈实现队列 | 难度：简单 | 标签：栈、设计、队列
 * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：
 *
 * 实现 MyQueue 类：
 * void push(int x) 将元素 x 推到队列的末尾
 * int pop() 从队列的开头移除并返回元素
 * int peek() 返回队列开头的元素
 * boolean empty() 如果队列为空，返回 true ；否则，返回 false
 *
 * 说明：
 * 你 只能 使用标准的栈操作 —— 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
 * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 *
 * 示例 1：
 * 输入：
 * ["MyQueue", "push", "push", "peek", "pop", "empty"]
 * [[], [1], [2], [], [], []]
 * 输出：
 * [null, null, null, 1, 1, false]
 *
 * 解释：
 * MyQueue myQueue = new MyQueue();
 * myQueue.push(1); // queue is: [1]
 * myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
 * myQueue.peek(); // return 1
 * myQueue.pop(); // return 1, queue is [2]
 * myQueue.empty(); // return false
 *
 * 提示：
 * 1 <= x <= 9
 * 最多调用 100 次 push、pop、peek 和 empty
 * 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）
 *
 * 进阶：
 * 你能否实现每个操作均摊时间复杂度为 O(1) 的队列？换句话说，执行 n 个操作的总时间复杂度为 O(n) ，即使其中一个操作可能花费较长时间。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/implement-queue-using-stacks
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution232 {
  /**
   * 执行用时：468 ms, 在所有 Scala 提交中击败了 54.55% 的用户
   * 内存消耗：54.6 MB, 在所有 Scala 提交中击败了 36.36% 的用户
   * 通过测试用例：22 / 22
   */
  class MyQueue() {

    import scala.collection.mutable

    val in = new mutable.Stack[Int]
    val out = new mutable.Stack[Int]

    def push(x: Int) {
      in.push(x)
    }

    def pop(): Int = {
      if (out.isEmpty) {
        while (in.nonEmpty) {
          out.push(in.pop())
        }
      }
      out.pop()
    }

    def peek(): Int = {
      if (out.isEmpty) {
        while (in.nonEmpty) {
          out.push(in.pop())
        }
      }
      out.head
    }

    def empty(): Boolean = {
      in.isEmpty && out.isEmpty
    }

  }

  /**
   * Your MyQueue object will be instantiated and called as such:
   * var obj = new MyQueue()
   * obj.push(x)
   * var param_2 = obj.pop()
   * var param_3 = obj.peek()
   * var param_4 = obj.empty()
   */
}
