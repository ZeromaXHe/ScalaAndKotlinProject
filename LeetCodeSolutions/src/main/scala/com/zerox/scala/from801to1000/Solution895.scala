package com.zerox.scala.from801to1000

/**
 * @author zhuxi
 * @since 2022/9/1 18:00
 * @note
 * 895. 最大频率栈 | 难度：困难 | 标签：栈、设计、哈希表、有序集合
 * 设计一个类似堆栈的数据结构，将元素推入堆栈，并从堆栈中弹出出现频率最高的元素。
 *
 * 实现 FreqStack 类:
 *
 * FreqStack() 构造一个空的堆栈。
 * void push(int val) 将一个整数 val 压入栈顶。
 * int pop() 删除并返回堆栈中出现频率最高的元素。
 * 如果出现频率最高的元素不只一个，则移除并返回最接近栈顶的元素。
 *
 * 示例 1：
 * 输入：
 * ["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
 * [[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
 * 输出：[null,null,null,null,null,null,null,5,7,5,4]
 * 解释：
 * FreqStack = new FreqStack();
 * freqStack.push (5);//堆栈为 [5]
 * freqStack.push (7);//堆栈是 [5,7]
 * freqStack.push (5);//堆栈是 [5,7,5]
 * freqStack.push (7);//堆栈是 [5,7,5,7]
 * freqStack.push (4);//堆栈是 [5,7,5,7,4]
 * freqStack.push (5);//堆栈是 [5,7,5,7,4,5]
 * freqStack.pop ();//返回 5 ，因为 5 出现频率最高。堆栈变成 [5,7,5,7,4]。
 * freqStack.pop ();//返回 7 ，因为 5 和 7 出现频率最高，但7最接近顶部。堆栈变成 [5,7,5,4]。
 * freqStack.pop ();//返回 5 ，因为 5 出现频率最高。堆栈变成 [5,7,4]。
 * freqStack.pop ();//返回 4 ，因为 4, 5 和 7 出现频率最高，但 4 是最接近顶部的。堆栈变成 [5,7]。
 *
 * 提示：
 * 0 <= val <= 109
 * push 和 pop 的操作数不大于 2 * 104。
 * 输入保证在调用 pop 之前堆栈中至少有一个元素。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-frequency-stack
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution895 {
  /**
   * 执行用时：1228 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：65.3 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：38 / 38
   */
  class FreqStack() {

    import scala.collection.mutable

    case class FreqNode(ts: mutable.Stack[Int], num: Int)

    val map = new mutable.HashMap[Int, FreqNode]
    val heap = new java.util.PriorityQueue[FreqNode](
      Ordering.by[FreqNode, (Int, Int)](n => (n.ts.size, n.ts.head)).reverse)
    var t = 0

    def push(`val`: Int) {
      if (map.contains(`val`)) {
        val node = map(`val`)
        heap.remove(node)
        node.ts.push(t)
        heap.add(node)
      } else {
        val stack = new mutable.Stack[Int]()
        stack.push(t)
        val node = FreqNode(stack, `val`)
        map.put(`val`, node)
        heap.add(node)
      }
      t += 1
    }

    def pop(): Int = {
      val node = heap.poll()
      node.ts.pop()
      if (node.ts.nonEmpty) heap.add(node)
      else map.remove(node.num)
      node.num
    }
  }

  /**
   * 执行用时：1052 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：68.2 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：38 / 38
   *
   * 考虑到最大频率每次只会变化 1，而且一定是连续的，前面用优先队列有点杀鸡用牛刀了。
   * 官方题解这个思路还是简单啊……
   */
  class FreqStack_Simple() {

    import scala.collection.mutable

    // 数字映射到对应频率
    val freq = new mutable.HashMap[Int, Int]
    // 频率映射到对应栈
    val group = new mutable.HashMap[Int, mutable.Stack[Int]]
    var max = 0

    def push(`val`: Int) {
      val f = freq.getOrElse(`val`, 0) + 1
      freq.put(`val`, f)
      if (f > max) max = f
      if (!group.contains(f)) group(f) = new mutable.Stack[Int]
      group(f).push(`val`)
    }

    def pop(): Int = {
      val x = group(max).pop()
      freq(x) -= 1
      if (group(max).isEmpty) max -= 1
      x
    }

  }

  /**
   * Your FreqStack object will be instantiated and called as such:
   * var obj = new FreqStack()
   * obj.push(`val`)
   * var param_2 = obj.pop()
   */
}
