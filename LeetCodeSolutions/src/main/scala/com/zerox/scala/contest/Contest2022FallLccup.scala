package com.zerox.scala.contest

/**
 * @author ZeromaXHe
 * @since 2022/9/24 14:01
 * @note
 * LCCUP 22 力扣杯秋季编程大赛
 */
object Contest2022FallLccup {
  /**
   * 3. 弹珠游戏 | 难度：中等
   *
   * @param num
   * @param plate
   * @return
   */
  def ballGame(num: Int, plate: Array[String]): Array[Array[Int]] = {
    val queue = new scala.collection.mutable.Queue[(Int, Int, Int)]
    for (i <- plate.indices; j <- plate(i).indices if plate(i)(j) == 'O') {
      // 上
      queue.enqueue((i, j, 0))
      // 右
      queue.enqueue((i, j, 1))
      // 下
      queue.enqueue((i, j, 2))
      // 左
      queue.enqueue((i, j, 3))
    }
    var remain = num
    val res = new scala.collection.mutable.ArrayBuffer[Array[Int]]
    val set = Array.ofDim[Boolean](plate.length, plate(0).length, 4)
    while (remain >= 0 && queue.nonEmpty) {
      var size = queue.size
      while (size > 0) {
        val (x, y, dir) = queue.dequeue()
        set(x)(y)(dir) = true
        if ((dir == 0 && x == 0) ||
          (dir == 1 && y == plate(0).length - 1) ||
          (dir == 2 && x == plate.length - 1) ||
          (dir == 3 && y == 0)) {
          if (plate(x)(y) == '.' && ((x != 0 && x != plate.length - 1) || (y != 0 && y != plate(0).length - 1))) {
            res.addOne(Array(x, y))
          }
        } else {
          var next: (Int, Int, Int) = null
          val nx = if (dir == 0) x - 1 else if (dir == 2) x + 1 else x
          val ny = if (dir == 1) y + 1 else if (dir == 3) y - 1 else y
          if (plate(nx)(ny) == '.') {
            next = (nx, ny, dir)
          } else if (plate(nx)(ny) == 'W') {
            next = (nx, ny, (dir + 1) % 4)
          } else if (plate(nx)(ny) == 'E') {
            next = (nx, ny, (dir + 3) % 4)
          }
          if (next != null && !set(next._1)(next._2)(next._3)) queue.enqueue(next)
        }
        size -= 1
      }
      remain -= 1
    }
    res.toArray
  }

  /**
   * 4. 二叉树灯饰 | 难度：中等
   * 未通过
   *
   * @param root
   * @return
   */
  def closeLampInTree(root: TreeNode): Int = {
    closeTree(root)._1
  }

  def closeTree(root: TreeNode): (Int, Int) = {
    if (root == null) return (0, 0)
    val (lc, lso) = closeTree(root.left)
    val (rc, rso) = closeTree(root.right)
    val rootToBeClose = if (root.value == 1) 1 else 0
    val rootToBeOpen = if (root.value == 1) 0 else 1
    val base = (lc + rc) min (lc + rso + 1) min (lso + rc + 1)
    val close = (rootToBeClose + base) min (lso + rso + 1 + rootToBeOpen)
    val singleOpen = (rootToBeOpen + base) min (lso + rso + 1 + rootToBeClose)
    (close, singleOpen)
  }

  class TreeNode(var _value: Int) {
    var value: Int = _value
    var left: TreeNode = null
    var right: TreeNode = null
  }

  /**
   * 5. 舒适的湿度 | 难度：困难
   * 未通过
   *
   * @param operate
   * @return
   */
  def unSuitability(operate: Array[Int]): Int = {
    val sum = operate.sum
    val m = sum / 2
    val dp = new Array[Int](m + 1)
    for (i <- operate.indices; j <- m to 1 by -1 if j >= operate(i)) dp(j) = dp(j - operate(i)) + operate(i) max dp(j)
    dp(m) max (sum - dp(m))
  }

  def main(args: Array[String]): Unit = {
    test03()
    println(unSuitability(Array(5, 3, 7, 2))) // 8
  }

  private def test03(): Unit = {
    val res1 = ballGame(2, Array("..E.", ".EOW", "..W."))
    print("[")
    res1.foreach(arr => print(s"[${arr(0)}, ${arr(1)}], "))
    println("]")
    val res2 = ballGame(3, Array(".....", "..E..", ".WO..", "....."))
    print("[")
    res2.foreach(arr => print(s"[${arr(0)}, ${arr(1)}], "))
    println("]")
    val res3 = ballGame(3, Array(".....", "....O", "....O", "....."))
    print("[")
    res3.foreach(arr => print(s"[${arr(0)}, ${arr(1)}], "))
    println("]")
  }
}
