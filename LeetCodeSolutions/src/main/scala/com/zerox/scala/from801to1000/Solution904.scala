package com.zerox.scala.from801to1000

/**
 * @author zhuxi
 * @since 2022/10/17 9:18
 * @note
 * 904. 水果成篮 | 难度：中等
 */
object Solution904 {
  def main(args: Array[String]): Unit = {
    println(totalFruit(Array(1, 2, 1))) // 3
    println(totalFruit(Array(0, 1, 2, 2))) // 3
    println(totalFruit(Array(1, 2, 3, 2, 2))) // 4
    println(totalFruit(Array(3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4))) // 5
  }

  /**
   * 时间 832 ms 击败 100%
   * 内存 63.1 MB 击败 100%
   *
   * @param fruits
   * @return
   */
  def totalFruit(fruits: Array[Int]): Int = {
    var count = 0
    var max = 0
    var temp1 = -1
    var last1 = 0
    var temp2 = -1
    var last2 = 0
    for (f <- fruits) {
      if (temp1 == -1) {
        temp1 = f
        last1 = 1
      } else if (temp1 == f) {
        count += last2
        last2 = 0
        last1 += 1
      } else if (temp2 == -1) {
        temp2 = f
        last2 = 1
        count += last1
        last1 = 0
      } else if (temp2 == f) {
        count += last1
        last1 = 0
        last2 += 1
      } else if (last1 > 0) {
        max = max max (count + last1 + last2)
        count = last1
        last1 = 0
        temp2 = f
        last2 = 1
      } else {
        max = max max (count + last1 + last2)
        count = last2
        last2 = 0
        temp1 = f
        last1 = 1
      }
    }
    max max (count + last1 + last2)
  }
}
