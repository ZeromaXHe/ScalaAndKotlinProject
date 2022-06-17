package com.zerox.chapter23

/**
 * @author zhuxi
 * @since 2022/6/17 10:22
 * @note
 * 第 23 章 重访 for 表达式
 */
object Chapter23Test {

  /**
   * 23.2 N 皇后问题
   */
  def test23_2(): Unit = {
    def queens(n: Int): List[List[(Int, Int)]] = {
      def placeQueens(k: Int): List[List[(Int, Int)]] =
        if (k == 0)
          List(List())
        else
          for {
            queens <- placeQueens(k - 1)
            column <- 1 to n
            queen = (k, column)
            if isSafe(queen, queens)
          } yield queen :: queens

      def isSafe(queen: (Int, Int), queens: List[(Int, Int)]) = queens forall (q => !inCheck(queen, q))

      def inCheck(q1: (Int, Int), q2: (Int, Int)) =
        q1._1 == q2._1 || // 同一行
          q1._2 == q2._2 || // 同一列
          (q1._1 - q2._1).abs == (q1._2 - q2._2).abs // 斜线

      placeQueens(n)
    }

    println(queens(4))
  }

  /**
   * 23.3 用 for 推导式进行查询
   */
  def test23_3(): Unit = {
    case class Book(title: String, authors: String*)

    val books: List[Book] =
      List(
        Book(
          "Structure and Interpretation of Computer Programs",
          "Abelson, Harold", "Sussman, Gerald J."
        ),
        Book(
          "Principles of Compiler Design",
          "Aho, Alfred", "Ullman, Jeffrey"
        ),
        Book(
          "Programming in Modula-2",
          "Wirth, Niklaus"
        ),
        Book(
          "Elements of ML Programming",
          "Ullman, Jeffrey"
        ),
        Book(
          "The Java Language Specification",
          "Gosling, James", "Joy, Bill", "Steele, Guy", "Bracha, Gilad"
        )
      )
    // 要找出所有作者的姓为 “Gosling” 的书的书名
    println(
      for (b <- books; a <- b.authors
           if a startsWith "Gosling")
      yield b.title
    )
    // 或者是找出所有在书名中包含了 “Program” 字样的书名
    println(
      for (b <- books if (b.title indexOf "Program") >= 0)
        yield b.title
    )
    // 又或者是找出所有至少编写了数据库中两本书以上的作者的名字
    val res6 =
      for (b1 <- books; b2 <- books if b1 != b2;
           a1 <- b1.authors; a2 <- b2.authors if a1 == a2)
      yield a1
    println(res6)

    def removeDuplicates[A](xs: List[A]): List[A] = {
      if (xs.isEmpty) xs
      else xs.head :: removeDuplicates(xs.tail filter (x => x != xs.head))
      // 值得注意的是，removeDuplicates 方法中的最后一个表达式也可以用 for 表达式来表示：
      // else xs.head :: removeDuplicates(for (x <- xs.tail if x != xs.head) yield x)
    }

    println(removeDuplicates(res6))
  }

  def main(args: Array[String]): Unit = {
    test23_2()
    println("-------------")
    test23_3()
  }
}
