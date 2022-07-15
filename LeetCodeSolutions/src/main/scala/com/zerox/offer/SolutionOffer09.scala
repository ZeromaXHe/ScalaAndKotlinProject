package com.zerox.offer

/**
 * @author zhuxi
 * @since 2022/7/15 16:40
 * @note
 * 剑指 Offer 09. 用两个栈实现队列 | 难度：简单 | 标签：栈、设计、队列
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。
 * (若队列中没有元素，deleteHead 操作返回 -1 )
 *
 * 示例 1：
 * 输入：
 * ["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 *
 * 示例 2：
 * 输入：
 * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * [[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 *
 * 提示：
 * 1 <= values <= 10000
 * 最多会对 appendTail、deleteHead 进行 10000 次调用
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionOffer09 {
  /**
   * 执行用时：1636 ms, 在所有 Scala 提交中击败了 53.85% 的用户
   * 内存消耗：63.5 MB, 在所有 Scala 提交中击败了 15.38% 的用户
   * 通过测试用例：55 / 55
   */
  class CQueue() {
    val inStack = new scala.collection.mutable.Stack[Int]
    val outStack = new scala.collection.mutable.Stack[Int]

    def appendTail(value: Int) {
      inStack.push(value)
    }

    def deleteHead(): Int = {
      if (outStack.isEmpty) while (inStack.nonEmpty) outStack.push(inStack.pop())
      if (outStack.nonEmpty) outStack.pop() else -1
    }

  }

  /**
   * Your CQueue object will be instantiated and called as such:
   * var obj = new CQueue()
   * obj.appendTail(value)
   * var param_2 = obj.deleteHead()
   */
}
