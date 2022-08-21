package com.zerox.scala.from1to200

/**
 * @author ZeromaXHe
 * @since 2022/8/21 20:22
 * @note
 * 155. 最小栈 | 难度：中等 | 标签：栈、设计
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * 实现 MinStack 类:
 *
 * MinStack() 初始化堆栈对象。
 * void push(int val) 将元素val推入堆栈。
 * void pop() 删除堆栈顶部的元素。
 * int top() 获取堆栈顶部的元素。
 * int getMin() 获取堆栈中的最小元素。
 *
 * 示例 1:
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 *
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 *
 * 提示：
 * -231 <= val <= 231 - 1
 * pop、top 和 getMin 操作总是在 非空栈 上调用
 * push, pop, top, and getMin最多被调用 3 * 104 次
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/min-stack
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution155 {
  /**
   * 执行用时：640 ms, 在所有 Scala 提交中击败了 50.00% 的用户
   * 内存消耗：59.4 MB, 在所有 Scala 提交中击败了 50.00% 的用户
   * 通过测试用例：31 / 31
   */
  class MinStack() {
    val stack = new scala.collection.mutable.Stack[Int]
    val minStack = new scala.collection.mutable.Stack[Int]

    def push(`val`: Int) {
      stack.push(`val`)
      if (minStack.isEmpty || `val` <= minStack.top) {
        minStack.push(`val`)
      }
    }

    def pop() {
      val pop = stack.pop()
      if (minStack.top == pop) {
        minStack.pop()
      }
    }

    def top(): Int = {
      stack.top
    }

    def getMin(): Int = {
      minStack.top
    }

  }

  /**
   * Your MinStack object will be instantiated and called as such:
   * var obj = new MinStack()
   * obj.push(`val`)
   * obj.pop()
   * var param_3 = obj.top()
   * var param_4 = obj.getMin()
   */
}
