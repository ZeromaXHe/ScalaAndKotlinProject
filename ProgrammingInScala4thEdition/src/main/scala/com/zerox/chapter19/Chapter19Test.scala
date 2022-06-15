package com.zerox.chapter19

/**
 * @author zhuxi
 * @since 2022/6/15 15:12
 * @note
 * 第 19 章 类型参数化
 */
object Chapter19Test {

  /**
   * 19.1 函数式队列
   */
  def test19_1(): Unit = {
    val q = Queue(1, 2, 3)
    val q1 = q enqueue 4
    println(q)
    println(q1)
  }

  /**
   * 19.3 型变注解
   */
  def test19_3(): Unit = {
    // Scala 在这一点上比 Java 做得更纯粹，它并不把数组当作是协变的。
    val a1 = Array("abc")
    val a2: Array[Object] = a1.asInstanceOf[Array[Object]]
    println(a1.mkString("Array(", ", ", ")"))
    println(a2.mkString("Array(", ", ", ")"))
  }

  /**
   * 19.8 上界
   */
  def test19_8(): Unit = {
    class Person(val firstName: String, val lastName: String) extends Ordered[Person] {
      def compare(that: Person) = {
        val lastNameComparison = lastName.compareToIgnoreCase(that.lastName)
        if (lastNameComparison != 0) lastNameComparison
        else firstName.compareToIgnoreCase(that.firstName)
      }

      override def toString: String = firstName + " " + lastName
    }

    val robert = new Person("Robert", "Jones")
    val sally = new Person("Sally", "Smith")
    println(robert < sally)

    // 为了确保传入到这个新的排序函数的列表类型混入了 Ordered，需要使用上界（upper bound）。
    // 上界的指定方式跟下界类似，只不过不是用表示下界的 >: 符号，而是用 <: 符号
    // 通过 “T <: Ordered[T]” 这样的语法，我们告诉编译器类型参数 T 有一个上界 Order[T]。
    // 这意味着传入 orderedMergeSort 的列表的元素类型必须是 Ordered 的子类型。
    // 所以，我们就能将 List[Person] 传给 orderedMergeSort，因为 Person 混入了 Ordered。
    def orderedMergeSort[T <: Ordered[T]](xs: List[T]): List[T] = {
      def merge(xs: List[T], ys: List[T]): List[T] =
        (xs, ys) match {
          case (Nil, _) => ys
          case (_, Nil) => xs
          case (x :: xs1, y :: ys1) =>
            if (x < y) x :: merge(xs1, ys)
            else y :: merge(xs, ys1)
        }

      val n = xs.length / 2
      if (n == 0) xs
      else {
        val (ys, zs) = xs splitAt n
        merge(orderedMergeSort(ys), orderedMergeSort(zs))
      }
    }

    val people = List(
      new Person("Larry", "Wall"),
      new Person("Anders", "Hejlsberg"),
      new Person("Guido", "van Rossum"),
      new Person("Alan", "Kay"),
      new Person("Yukihiro", "Matsumoto")
    )
    println(people)
    val sortedPeople = orderedMergeSort(people)
    println(sortedPeople)
    // 虽然示例 19.12 中的排序函数对于说明上界这个概念很有帮助，它实际上并不是 Scala 中利用 Ordered 特质设计排序函数的最通用的方式。
    // 举例来说，我们并不能用 orderedMergeSort 来对整数列表进行排序，因为 Int 类并不是 Ordered[Int] 的子类型
  }

  def main(args: Array[String]): Unit = {
    test19_1()
    println("------------")
    test19_3()
    println("------------")
    test19_8()
  }
}
