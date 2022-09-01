package com.zerox.scala.from1to200

/**
 * @author ZeromaXHe
 * @since 2022/7/6 21:29
 * @note
 * 51. N 皇后 | 难度：困难 | 标签：数组、回溯
 * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
 *
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 *
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * 示例 1：
 * 输入：n = 4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：[["Q"]]
 *
 * 提示：
 * 1 <= n <= 9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/n-queens
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution51 {
  def main(args: Array[String]): Unit = {
    val l = System.currentTimeMillis()
    // solveNQueens_StringPerm(9)
    println(System.currentTimeMillis() - l)
    println("----------------------------------------")
    val l1 = System.currentTimeMillis()
    val strList = (0 until 9).map(("." * 9).updated(_, 'Q')).toList
    val l2 = System.currentTimeMillis()
    println(l2 - l1)
    val permutations = (0 until 9).permutations
    val l3 = System.currentTimeMillis()
    println(l3 - l2)
    val iterator = permutations.filter(_.zipWithIndex.combinations(2) forall {
      case Seq((a, b), (c, d)) => a + b != c + d && a + d != b + c
    })
    val l4 = System.currentTimeMillis()
    println(l4 - l3)
    val iterator1 = iterator.map(_.map {
      strList
    }.toList)
    val l5 = System.currentTimeMillis()
    println(l5 - l4)
    iterator1.toList
    println(System.currentTimeMillis() - l5)
  }

  /**
   * 执行用时：1468 ms, 在所有 Scala 提交中击败了 12.50% 的用户
   * 内存消耗：55.7 MB, 在所有 Scala 提交中击败了 37.50% 的用户
   * 通过测试用例：9 / 9
   *
   * @param n
   * @return
   */
  def solveNQueens(n: Int): List[List[String]] = {
    (0 until n).permutations.filter(_.zipWithIndex.combinations(2) forall {
      case Seq((a, b), (c, d)) => a + b != c + d && a + d != b + c
    }).map(_.map {
      ("." * n).updated(_, 'Q')
    }.toList).toList
  }

  /**
   * 执行用时：1480 ms, 在所有 Scala 提交中击败了 12.50% 的用户
   * 内存消耗：55.6 MB, 在所有 Scala 提交中击败了 50.00% 的用户
   * 通过测试用例：9 / 9
   *
   * @param n
   * @return
   */
  def solveNQueens_prepareStr(n: Int): List[List[String]] = {
    val strList = (0 until n).map(("." * n).updated(_, 'Q')).toList
    (0 until n).permutations.filter(_.zipWithIndex.combinations(2) forall {
      case Seq((a, b), (c, d)) => a + b != c + d && a + d != b + c
    }).map(_.map(strList).toList).toList
  }

  // 今天看知乎 https://www.zhihu.com/question/19748408/answer/62527490：
  // 用 zipWithIndex() 的时候，一定要明白这是两次循环，最好改用 Vector(...).indices.foreach() 的方法，或者用 .view 来推迟执行。
  // 但是实际上在力扣 .view 会报错？貌似是 2.13 的一个早期 bug？而实际执行也没有快多少，主要耗时集中在最后一个 map 过程。
  // 所以下面两个方法没有实际提交
  def solveNQueens_prepareStr2(n: Int): List[List[String]] = {
    val strList = (0 until n).map(("." * n).updated(_, 'Q')).toList
    (0 until n).permutations.filter(p => p.indices.map(i => (p(i), i)).combinations(2) forall {
      case Seq((a, b), (c, d)) => a + b != c + d && a + d != b + c
    }).map(_.map(strList).toList).toList
  }

  // 2.11 代码，2.13 中会报错(.combinations 不行)
  // def solveNQueens_StringPerm(n: Int): List[List[String]] = {
  //   val strIndexTupleList = (0 until n).map(i => (("." * n).updated(i, 'Q'), i))
  //   strIndexTupleList.view.permutations.filter(_.view.zipWithIndex.combinations(2) forall {
  //     case Seq((a, b), (c, d)) => a._2 + b != c._2 + d && a._2 + d != b + c._2
  //   }).map(_.map(_._1).toList).toList
  // }

  /**
   * 执行用时：540 ms, 在所有 Scala 提交中击败了 62.50% 的用户
   * 内存消耗：52.6 MB, 在所有 Scala 提交中击败了 87.50% 的用户
   * 通过测试用例：9 / 9
   *
   * 不使用 strList 的话
   * 执行用时：580 ms, 在所有 Scala 提交中击败了 37.50% 的用户
   * 内存消耗：52.3 MB, 在所有 Scala 提交中击败了 87.50% 的用户
   * 通过测试用例：9 / 9
   *
   * 52 题提交时看到的一个不错的写法
   *
   * @param n
   * @return
   */
  def solveNQueens_another(n: Int): List[List[String]] = {
    val strList = (0 until n).map(("." * n).updated(_, 'Q')).toList

    def nQueens(k: Int): List[List[Int]] =
      if (k == 0) List(Nil)
      else nQueens(k - 1).flatMap(q => (0 until n).withFilter(c => isValid(c, q)).map(c => c :: q))

    def isValid(col: Int, queens: List[Int]) =
      (1 to queens.length).zip(queens).forall({ case (r, c) => c != col && r != (col - c).abs })

    nQueens(n).map(_.map(strList))
  }

  /**
   * 执行用时：664 ms, 在所有 Scala 提交中击败了 14.29% 的用户
   * 内存消耗：53.2 MB, 在所有 Scala 提交中击败了 85.71% 的用户
   * 通过测试用例：9 / 9
   *
   * @param n
   * @return
   */
  def solveNQueens_recursive(n: Int): List[List[String]] = {
    val strList = (0 until n).map(("." * n).updated(_, 'Q')).toList

    def nQueens: Int => List[List[Int]] = {
      case 0 => List(Nil)
      case k => for (q <- nQueens(k - 1); c <- 0 until n if noSameDiag(c, q)) yield c :: q
    }

    def noSameDiag(col: Int, queens: List[Int]) =
      (1 to queens.length).zip(queens).forall({ case (r, c) => c != col && r != (col - c).abs })

    nQueens(n).map(_.map(strList))
  }

  /**
   * 执行用时：1644 ms, 在所有 Scala 提交中击败了 14.29% 的用户
   * 内存消耗：166 MB, 在所有 Scala 提交中击败了 14.29% 的用户
   * 通过测试用例：9 / 9
   *
   * @param n
   * @return
   */
  def solveNQueens_haskell(n: Int): List[List[String]] = {
    val strList = (0 until n).map(("." * n).updated(_, 'Q')).toList

    def insert: (Int, List[Int]) => List[List[Int]] = {
      case (n, List()) => List(List(n))
      case (n, nI :: ns) => (n :: nI :: ns) :: (for (nsI <- insert(n, ns)) yield nI +: nsI)
    }

    def perm: List[Int] => List[List[Int]] = {
      case List() => List(Nil);
      case x :: xs => (for (permuxs <- perm(xs)) yield insert(x, permuxs)).flatten
    }

    def noSameDiag: List[Int] => Boolean = {
      case List() => true
      case xs@_ :: xsI =>
        val (i1, p1) :: ip = xs zip Stream.from(0)
        (for ((i, p) <- ip) yield (i1 - i).abs != (p1 - p).abs).forall(identity) && noSameDiag(xsI)
    }

    def nQueens: Int => List[List[Int]] = n => for (xs <- perm((0 until n).toList) if noSameDiag(xs)) yield xs

    nQueens(n).map(_.map(strList))
  }
}
