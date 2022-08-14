package com.zerox.scala.from1201to1400

/**
 * @author zhuxi
 * @since 2022/7/26 9:53
 * @note
 * 1206. 设计跳表 | 难度：困难 | 标签：设计链表
 * 不使用任何库函数，设计一个 跳表 。
 *
 * 跳表 是在 O(log(n)) 时间内完成增加、删除、搜索操作的数据结构。跳表相比于树堆与红黑树，其功能与性能相当，并且跳表的代码长度相较下更短，其设计思想与链表相似。
 *
 * 例如，一个跳表包含 [30, 40, 50, 60, 70, 90] ，然后增加 80、45 到跳表中，以下图的方式操作：
 *
 * Artyom Kalinin [CC BY-SA 3.0], via Wikimedia Commons
 *
 * 跳表中有很多层，每一层是一个短的链表。在第一层的作用下，增加、删除和搜索操作的时间复杂度不超过 O(n)。跳表的每一个操作的平均时间复杂度是 O(log(n))，空间复杂度是 O(n)。
 *
 * 了解更多 : https://en.wikipedia.org/wiki/Skip_list
 *
 * 在本题中，你的设计应该要包含这些函数：
 *
 * bool search(int target) : 返回target是否存在于跳表中。
 * void add(int num): 插入一个元素到跳表。
 * bool erase(int num): 在跳表中删除一个值，如果 num 不存在，直接返回false. 如果存在多个 num ，删除其中任意一个即可。
 * 注意，跳表中可能存在多个相同的值，你的代码需要处理这种情况。
 *
 * 示例 1:
 *
 * 输入
 * ["Skiplist", "add", "add", "add", "search", "add", "search", "erase", "erase", "search"]
 * [[], [1], [2], [3], [0], [4], [1], [0], [1], [1]]
 * 输出
 * [null, null, null, null, false, null, true, false, true, false]
 *
 * 解释
 * Skiplist skiplist = new Skiplist();
 * skiplist.add(1);
 * skiplist.add(2);
 * skiplist.add(3);
 * skiplist.search(0);   // 返回 false
 * skiplist.add(4);
 * skiplist.search(1);   // 返回 true
 * skiplist.erase(0);    // 返回 false，0 不在跳表中
 * skiplist.erase(1);    // 返回 true
 * skiplist.search(1);   // 返回 false，1 已被擦除
 *
 * 提示:
 * 0 <= num, target <= 2 * 104
 * 调用search, add,  erase操作次数不大于 5 * 104 
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/design-skiplist
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1206 {
  def main(args: Array[String]): Unit = {
    val skiplist = new Skiplist()
    skiplist.add(1)
    skiplist.add(2)
    skiplist.add(3)
    println(skiplist.search(0)) // 返回 false
    skiplist.add(4)
    println(skiplist.search(1)) // 返回 true
    println(skiplist.erase(0)) // 返回 false，0 不在跳表中
    println(skiplist.erase(1)) // 返回 true
    println(skiplist.search(1)) // 返回 false，1 已被擦除

    // 想验证一下上升一层的逻辑来着，结果找到一个 NPE bug
    val skiplist2 = new Skiplist()
    for (i <- 1 to 200) skiplist2.add(i)
    println(skiplist2)

    case class Node(value: Int, var next: Node)

    println("-------------------------")
    try {
      // 2.13 推荐使用 LazyList 代替 Stream
      val stream = Stream.iterate(Node(-1, Node(1, Node(2, Node(3, Node(4, Node(5, null)))))).next)(_.next).takeWhile(_ != null)
      println(stream.force)
      val filtered = stream.filter(_ => math.random < 1.0 / 5)
      println(filtered.force)
      val stream2 = filtered
        .scanLeft(null: Node)((pre: Node, cur: Node) => {
          if (pre != null) pre.next = cur
          cur
        })
      println(stream2.force) // scanLeft 会把第一个括号内的元素拼进去
      println("first: " + stream2.head) // null
      println("second: " + stream2.tail.head) // 可能 java.util.NoSuchElementException: head of empty stream，即只有 null 一个元素
    } catch {
      case ex: Exception => ex.printStackTrace()
    }

    println("-------------------------")
    try {
      val iterator = Iterator.iterate(Node(-1, Node(1, Node(2, Node(3, Node(4, Node(5, null)))))).next)(_.next).takeWhile(_ != null)
      val (iter, iter2) = iterator.duplicate
      println(iter2.toList)
      val filter = iter.filter(_ => math.random < 1.0 / 5)
      val (filt, filt2) = filter.duplicate
      println(filt2.toList)
      val head = filt
        .scanLeft(null: Node)((pre: Node, cur: Node) => {
          // 2.13 没有问题，但 2.11 概率性会报错（前面 filter 可能过滤空，就不会报错了）
          println("throw exception")
          throw new NullPointerException
          /*if (pre != null) */ pre.next = cur
          cur
        }).next()
      println(head)
    } catch {
      case ex: Exception => ex.printStackTrace()
    }
  }

  /**
   * 执行用时：1192 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：66.3 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：20 / 20
   */
  class Skiplist() {
    val avgJump = 5
    var capacity: Int = avgJump
    var count = 0

    class Node(val value: Int, val down: Node, var right: Node)

    private var head = new Node(-1, null, null)

    def search(target: Int): Boolean = {
      if (count == 0) false
      else {
        val preRight = findPres(target).last.right
        preRight != null && preRight.value == target
      }
    }

    private def findPres(target: Int) = {
      val node = findPre(head, target)
      Stream.iterate(node)(up => findPre(up.down, target)) takeWhile (_ != null)
    }

    private def findPre(from: Node, target: Int): Node = {
      Iterator.iterate(from)(_.right).takeWhile(_ != null)
        .find(n => n.right == null || n.right.value >= target).orNull
    }

    def add(num: Int): Unit = {
      findPres(num).reverse
        .zip(0.0 #:: Stream.continually(math.random))
        .takeWhile(_._2 < 1.0 / avgJump)
        .map(_._1)
        .scanLeft(null: Node)((downNode: Node, pre: Node) => {
          pre.right = new Node(num, downNode, pre.right)
          pre.right
        }).force
      count += 1
      if (count > capacity) {
        head = new Node(-1, head, null)
        Stream.iterate(head.down.right)(_.right).takeWhile(_ != null)
          .filter(_ => math.random < 1.0 / avgJump)
          .scanLeft(head)((pre: Node, cur: Node) => {
            pre.right = new Node(cur.value, cur, null)
            pre.right
          }).force

        /**
         * 执行用时：1196 ms, 在所有 Scala 提交中击败了 100.00% 的用户
         * 内存消耗：69.5 MB, 在所有 Scala 提交中击败了 100.00% 的用户
         * 通过测试用例：20 / 20
         */
        // 这里如果使用下面代码，则 head.right 直接就等于 null 了，原因就是 scanLeft 会把 null 拼到开头。
        // head = new Node(-1, head,
        //   Iterator.iterate(head.right)(_.right).takeWhile(_ != null)
        //     .filter(_ => math.random < 1.0 / avgJump)
        //     .scanLeft(null: Node)((pre: Node, cur: Node) => {
        //       // 之所以发现，是因为这里其实会空指针，在 2.11 版本 Scala 会报错，但是 2.13 版本不会。
        //       // 2.11 如果改成 Stream.iterate 开头，.head 结尾则不会报错
        //       // 2.13 就不需要改也不会报错。估计是 Iterator.scanLeft 的实现也改成是惰性的了？第二个括号其实不会执行
        //       pre.right = cur
        //       cur
        //     }).next()
        capacity *= avgJump
      }
    }

    def erase(num: Int): Boolean = {
      if (search(num)) {
        count -= 1
        // 暂不添加高度缩小的逻辑，防止伸缩过程抖动
        findPres(num).reverse.foreach(pre => {
          if (pre.right != null && pre.right.value == num) pre.right = pre.right.right
          else return true
        })
        true
      } else false
    }

  }

  /**
   * Your Skiplist object will be instantiated and called as such:
   * var obj = new Skiplist()
   * var param_1 = obj.search(target)
   * obj.add(num)
   * var param_3 = obj.erase(num)
   */
}
