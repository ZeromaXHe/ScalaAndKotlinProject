package com.zerox.scala.prime

import scala.collection.mutable

/**
 * @author zhuxi
 * @since 2022/9/27 17:35
 * @note
 * P1008 [NOIP1998 普及组] 三连击 | 难度：普及-
 */
object SolutionP1008 {
  /**
   * AC 179ms/25.00MB
   *
   * @param args
   */
  def main(args: Array[String]): Unit = {
    val set = new mutable.HashSet[Int]
    for (i <- 1 to 3;
         j <- 1 to 9 if j != i;
         k <- 1 to 9 if k != j && k != i;
         first = i * 100 + j * 10 + k if first * 3 < 1000) {
      set.clear()
      set.add(0)
      set.add(i)
      set.add(j)
      set.add(k)
      val second1 = first * 2 / 100 % 10
      val second2 = first * 2 / 10 % 10
      val second3 = first * 2 % 10
      if (!set.contains(second1) && !set.contains(second2) && !set.contains(second3) &&
        second1 != second2 && second2 != second3 && second1 != second3) {
        set.add(second1)
        set.add(second2)
        set.add(second3)
        val third1 = first * 3 / 100 % 10
        val third2 = first * 3 / 10 % 10
        val third3 = first * 3 % 10
        if (!set.contains(third1) && !set.contains(third2) && !set.contains(third3) &&
          third1 != third2 && third2 != third3 && third1 != third3) {
          println(s"$first ${first * 2} ${first * 3}")
        }
      }
    }
  }

  /**
   * MLE = Memory Limit Exceeded
   *
   * @param args
   */
  def main_MLE(args: Array[String]): Unit = {
    (1 to 9).permutations.foreach(seq => {
      val i1 = seq(0) * 100 + seq(1) * 10 + seq(2)
      val i2 = seq(3) * 100 + seq(4) * 10 + seq(5)
      if (i2 == 2 * i1) {
        val i3 = seq(6) * 100 + seq(7) * 10 + seq(8)
        if (i3 == 3 * i1) println(s"$i1 $i2 $i3")
      }
    })
  }
}
