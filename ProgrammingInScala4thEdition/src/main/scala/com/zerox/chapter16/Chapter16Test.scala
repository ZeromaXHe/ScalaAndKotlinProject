package com.zerox.chapter16

/**
 * @Author: zhuxi
 * @Time: 2022/6/13 11:12
 * @Description: 第 16 章 使用列表
 *               列表跟数组非常像，不过有两个重要的区别。首先，列表是不可变的。也就是说，列表的元素不能通过赋值改变。
 *               其次，列表的结构是递归的（即链表，linked list），[1]而数组是平的。
 * @ModifiedBy: zhuxi
 */
object Chapter16Test {
  /**
   * 16.2 List 类型
   */
  def test16_2(): Unit = {
    // 跟数组一样，列表也是同构（homogeneous）的：同一个列表的所有元素都必须是相同的类型。元素类型为 T 的列表的类型写作 List[T]。
    val fruit: List[String] = List("apples", "oranges", "pears")
    val nums: List[Int] = List(1, 2, 3, 4)
    val diag3: List[List[Int]] =
      List(
        List(1, 0, 0),
        List(0, 1, 0),
        List(0, 0, 1)
      )

    val empty: List[Nothing] = List()
    println(fruit)
    println(nums)
    println(diag3)
    println(empty)

    // Scala的列表类型是协变（covariant）的。意思是对每一组类型 S 和 T，如果 S 是 T 的子类型，那么 List[S] 就是 List[T] 的子类型。
    // 例如，List[String] 是 List[Object] 的子类型。因为每个字符串列表也都可以被当作对象列表，这很自然。

    // 注意，空列表的类型为 List[Nothing]。在 11.3 节我们讲过，在 Scala 的类继承关系中，Nothing 是底类型。
    // 由于列表是协变的，对于任何 T 而言，List[Nothing] 都是 List[T] 的子类型。
    // 因此，既然空列表对象的类型为 List[Nothing]，可以被当作其他形如 List[T] 类型的对象。这也是为什么编译器允许我们编写如下的代码：
    val xs: List[String] = List()
    println(xs)
  }

  /**
   * 16.3 构建列表
   */
  def test16_3(): Unit = {
    // 所有的列表都构建自两个基础的构建单元：Nil 和 ::（读作 “cons”）。Nil 表示空列表。中缀操作符 :: 表示在列表前追加元素。
    // 也就是说，x :: xs 表示这样一个列表：第一个元素为 x，接下来是列表 xs 的全部元素。
    val fruit = "apples" :: ("oranges" :: ("pears" :: Nil))
    val nums = 1 :: (2 :: (3 :: (4 :: Nil)))
    val diag3 = (1 :: (0 :: (0 :: Nil))) ::
      (0 :: (1 :: (0 :: Nil))) ::
      (0 :: (0 :: (1 :: Nil))) :: Nil
    val empty = Nil

    println(fruit)
    println(nums)
    println(diag3)
    println(empty)
    // 事实上，之前我们用 List(...) 对 fruit、nums、diag3 和 empty 的定义，不过是最终展开成上面这些定义的包装方法而已。
    // 由于 :: 以冒号结尾，:: 这个操作符是右结合的，A :: B :: C 会被翻译成 A :: (B :: C)。因此，我们可以在前面的定义中去掉圆括号。
  }

  /**
   * 16.4 列表的基本操作
   */
  def test16_4(): Unit = {
    // head 和 tail 方法只对非空列表有定义。当我们从一个空列表调用时，它们将抛出异常
    try {
      println(Nil.head)
    } catch {
      case ex: Exception => println(ex.getClass + ": " + ex.getMessage + "\n" +
        ex.getStackTrace.mkString("StackTrace(", "\n", ")"))
    }

    // 作为如何处理列表的例子，考虑按升序排列一个数字列表的元素。一个简单的做法是插入排序（insertion sort），这个算法的工作原理如下：
    // 对于非空列表x :: xs，先对xs排序，然后将第一个元素 x 插入到这个排序结果中正确的位置
    def isort(xs: List[Int]): List[Int] =
      if (xs.isEmpty) Nil
      else insert(xs.head, isort(xs.tail))

    def insert(x: Int, xs: List[Int]): List[Int] =
      if (xs.isEmpty || x <= xs.head) x :: xs
      else xs.head :: insert(x, xs.tail)

    println(isort(List(3, 2, 5, 1, 4)))
  }

  /**
   * 16.5 列表模式
   */
  def test16_5(): Unit = {
    // 列表也可以用模式匹配解开。列表模式可以逐一对应到列表表达式。
    // 我们既可以用 List(...) 这样的模式来匹配列表的所有元素，也可以用 :: 操作符和 Nil 常量一点点地将列表解开。
    val fruit = List("apples", "oranges", "pears")
    val List(a, b, c) = fruit
    println(a)
    println(b)
    println(c)
    // List(a, b, c) 这个模式匹配长度为3的列表，并将三个元素分别绑定到模式变量 a、b 和 c。
    // 如果我们事先并不知道列表中元素的个数，更好的做法是用 :: 来匹配。
    val a2 :: b2 :: rest = fruit
    println(a2)
    println(b2)
    println(rest)

    // 使用模式是用基本方法 head、tail 和 isEmpty 来解开列表的变通方式。例如，我们再次实现插入排序，不过这一次，用模式匹配：
    def isort(xs: List[Int]): List[Int] = xs match {
      case List() => List()
      case x :: xs1 => insert(x, isort(xs1))
    }

    def insert(x: Int, xs: List[Int]): List[Int] = xs match {
      case List() => List(x)
      case y :: ys =>
        if (x <= y) x :: xs
        else y :: insert(x, ys)
    }

    println(isort(List(0, 3, 4, 2, 1)))
  }

  /**
   * 16.6 List 类的初阶方法
   */
  def test16_6(): Unit = {
    // 跟 :: 操作相似的一个操作是拼接，写作 :::。不同于 ::，::: 接收两个列表参数作为操作元。
    // xs ::: ys 的结果是一个包含了 xs 所有元素，加上 ys 所有元素的新列表。
    println(List(1, 2) ::: List(3, 4, 5))
    println(List() ::: List(1, 2, 3))
    println(List(1, 2, 3) ::: List(4))
    // 跟 cons 类似，列表的拼接操作也是右结合的。

    // 拼接（:::）是作为 List 类的一个方法实现的。我们也可以通过对列表进行模式匹配来“手工”实现拼接。
    // 我们建议你自己做一下尝试，因为这个过程展示了用列表实现算法的常用方式。
    def append[T](xs: List[T], ys: List[T]): List[T] =
      xs match {
        case List() => ys
        case x :: xs1 => x :: append(xs1, ys)
      }

    // length 方法计算列表的长度
    println(List(1, 2, 3).length)

    println("\n【访问列表的末端：init 和 last】")
    // 我们已经知道基本的操作 head 和 tail，它们分别获取列表的首个元素和除了首个元素剩余的部分。
    // 它们也分别有一个对偶（dual）方法：last 返回（非空）列表的最后一个元素，而 init 返回除最后一个元素之外剩余的部分
    val abcde = List('a', 'b', 'c', 'd', 'e')
    println(abcde.last)
    println(abcde.init)
    // 跟 head 和 tail 一样，这两个方法在应用到空列表的时候也会抛出异常
    try {
      println(List().init)
    } catch {
      case ex: Exception => println(ex.getClass + ": " + ex.getMessage + "\n" +
        ex.getStackTrace.mkString("StackTrace(", "\n", ")"))
    }
    try {
      println(List().last)
    } catch {
      case ex: Exception => println(ex.getClass + ": " + ex.getMessage + "\n" +
        ex.getStackTrace.mkString("StackTrace(", "\n", ")"))
    }
    // 不像 head 和 tail 那样在运行的时候消耗常量时间，init 和 last 需要遍历整个列表来计算结果。因此它们的耗时跟列表的长度成正比。
    // 最好将数据组织成大多数访问都发生在列表头部而不是尾部。

    println("\n【反转列表：reverse】")
    // 如果在算法当中某个点需要频繁地访问列表的末尾，有时侯先将列表反转，再对反转后的列表做操作是更好的做法。
    println(abcde.reverse)
    // 跟所有其他列表操作一样，reverse 会创建一个新的列表，而不是对传入的列表做修改。由于列表是不可变的，这样的修改就算想做我们也做不到。
    println(abcde)

    println("\n【前缀和后缀：drop、take 和 splitAt】")
    // drop 和 take 是对 tail 和 init 的一般化。怎么说呢？它们返回的是 列表任意长度的前缀或后缀。
    // 表达式“xs take n”返回列表 xs 的前 n 个元素。如果 n 大于 xs.length，那么将返回整个 xs 列表。
    // 操作“xs drop n”返回列表 xs 除前 n 个元素之外的所有元素。如果 n 大于等于 xs.length，那么就返回空列表。
    // splitAt 操作将列表从指定的下标位置切开，返回这两个列表组成的对偶。它的定义来自如下这个等式：
    // xs splitAt n 等于 (xs take n, xs drop n)
    // 不过，splitAt 会避免遍历 xs 列表两次。
    println(abcde take 2)
    println(abcde drop 2)
    println(abcde splitAt 2)

    println("\n【元素选择：apply 和 indices】")
    // apply 方法支持从任意位置选取元素。不过相对于数组而言，对列表的这项操作并不是那么常用。
    println(abcde apply 2) // 在 Scala 中很少见
    // 跟其他类型一样，当对象出现在方法调用中函数出现的位置时，编译器会帮我们插入 apply。因此上面的代码可以简化为：
    println(abcde(2)) // 在 Scala 中很少见
    // 对列表而言，从任意位置选取元素的操作之所以不那么常用，是因为 xs(n) 的耗时跟下标 n 成正比。
    // 事实上，apply 是通过 drop 和 head 定义的：xs apply n 等于 (xs drop n).head
    // 从这个定义我们也可以清晰地看到，列表的下标从 0 开始直到列表长度减 1，跟数组一样。
    // Indices 方法返回包含了指定列表所有有效下标的列表：
    println(abcde.indices)

    println("\n【扁平化列表的列表：flatten】")
    // flatten 方法接收一个列表的列表并将它扁平化，返回单个列表
    println(List(List(1, 2), List(3), List(), List(4, 5)).flatten)
    val fruit = List("apples", "oranges", "pears")
    println(fruit.map(_.toCharArray).flatten)

    // 拉链（zip）操作接收两个列表，返回一个由对偶组成的列表
    println(abcde.indices zip abcde)
    // 如果两个列表的长度不同，那么任何没有配对上的元素将被丢弃
    val zipped = abcde zip List(1, 2, 3)
    println(zipped)
    // 一个有用的特例是将列表和它的下标 zip 起来。
    // 最高效的做法是用 zipWithIndex 方法，这个方法会将列表中的每个元素和它出现在列表中的位置组合成对偶
    println(abcde.zipWithIndex)
    // 任何元组的列表也可以通过unzip方法转换回由列表组成的元组
    println(zipped.unzip)

    println("\n【显示列表：toString 和 mkString】")
    // toString 操作返回列表的标准字符串表现形式
    println(abcde.toString)
    // 如果需要不同的表现形式，可以用 mkString 方法。xs mkString(pre, sep, post) 涉及四个操作元：
    // 要显示的列表 xs、出现在最前面的前缀字符串 pre、在元素间显示的分隔字符串sep，以及出现在最后面的后缀字符串 post。
    // mkString有两个重载的变种，让我们不必填写部分或全部入参。第一个变种只接收一个分隔字符串：
    // xs mkString sep 等于 xs mkString("", sep, "")
    // 第二个变种可以什么入参都不填：xs.mkString 等于 xs mkString ""
    println(abcde mkString("[", ",", "]"))
    println(abcde mkString "")
    println(abcde.mkString)
    println(abcde mkString("List(", ", ", ")"))

    // mkString 方法还有别的变种，比如 addString，这个方法将构建出来的字符串追加到一个 StringBuilder 对象，而不是作为结果返回
    val buf = new StringBuilder
    println(abcde addString(buf, "(", ";", ")"))
    // mkString 和 addString 这两个方法继承自 List 的超特质 Traversable，因此它们也可以用在所有其他集合类型上。

    println("\n【转换列表：iterator、toArray、copyToArray】")
    // 为了在扁平的数组世界和递归的列表世界之间做数据转换，可以使用 List 类的 toArray 和 Array 类的 toList 方法
    val arr = abcde.toArray
    println(arr.mkString("Array(", ", ", ")"))
    println(arr.toList)
    // 还有一个 copyToArray 方法可以将列表中的元素依次复制到目标数组的指定位置。
    // 将列表 xs 的所有元素复制至数组 arr，从下标 start 开始。我们必须确保目标数组足够大，能够容纳整个列表。
    val arr2 = new Array[Int](10)
    println(arr2.mkString("Array(", ", ", ")"))
    // IDEA scala 插件瞎涂颜色，这个明明有副作用，非要弄成灰色报 Unused expression without side effects
    List(1, 2, 3) copyToArray(arr2, 3)
    println(arr2.mkString("Array(", ", ", ")"))

    // 最后，如果要通过迭代器访问列表元素，可以用 iterator 方法
    val it = abcde.iterator
    println(it.next)
    println(it.next)

    println("\n【例子：归并排序】")

    def msort[T](less: (T, T) => Boolean)(xs: List[T]): List[T] = {
      def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
        case (Nil, _) => ys
        case (_, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (less(x, y)) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }

      val n = xs.length / 2
      if (n == 0) xs else {
        val (ys, zs) = xs splitAt n
        merge(msort(less)(ys), msort(less)(zs))
      }
    }

    println(msort((x: Int, y: Int) => x < y)(List(5, 7, 1, 3)))
    val intSort = msort((x: Int, y: Int) => x < y) _
    val reverseIntSort = msort((x: Int, y: Int) => x > y) _
    val mixedInts = List(4, 1, 9, 0, 5, 8, 3, 6, 2, 7)
    println(intSort(mixedInts))
    println(reverseIntSort(mixedInts))
  }

  /**
   * 16.7 List 类的高阶方法
   */
  def test16_7(): Unit = {
    println("【对列表作映射：map、flatMap 和 foreach】")
    println(List(1, 2, 3) map (_ + 1))
    val words = List("the", "quick", "brown", "fox")
    println(words map (_.length))
    println(words map (_.toList.reverse.mkString))
    // flatMap 操作符跟 map 类似，不过它要求右侧的操作元是一个返回元素列表的函数。
    // 它将这个函数应用到列表的每个元素，然后将所有结果拼接起来返回。
    println(words map (_.toList))
    println(words flatMap (_.toList))
    // 下面这个表达式也体现了 map 和 flatMap 的区别与联系，这个表达式构建的是一个满足 1 ≤ j < i < 5 的所有对偶 (i, j)
    println(List.range(1, 5) flatMap (i => List.range(1, i) map (j => (i, j))))
    // 第三个映射类的操作是 foreach。不同于 map 和 flatMap，foreach 要求右操作元是一个过程（结果类型为 Unit 的函数）。
    // 它只是简单地将过程应用到列表中的每个元素。整个操作本身的结果类型也是 Unit，并没有列表类型的结果被组装出来。
    var sum = 0
    List(1, 2, 3, 4, 5) foreach (sum += _)
    println(sum)

    println("\n【过滤列表：filter、partition、find、takeWhile、dropWhile 和 span】")
    // “xs filter p”这个操作的两个操作元分别是类型为 List[T] 的 xs 和类型为 T => Boolean 的前提条件函数 p。
    // 这个操作将交出 xs 中所有 p(x) 为 true 的元素 x。
    println(List(1, 2, 3, 4, 5) filter (_ % 2 == 0))
    println(words filter (_.length == 3))
    // partition 方法跟 filter 很像不过返回的是一对列表。
    // 其中一个包含所有前提条件为 true 的元素，另一个包含所有前提条件为 false 的元素。
    println(List(1, 2, 3, 4, 5) partition (_ % 2 == 0))
    // find 方法跟 filter 也很像，不过它返回满足给定前提条件的第一个元素，而不是所有元素。
    // xs find p 这个操作接收列表xs和前提条件函数 p 两个操作元，返回一个可选值。
    // 如果 xs 中存在一个元素 x 满足 p(x) 为 true，那么就返回 Some(x)。
    // 而如果对于所有元素而言 p 都为 false，那么则返回 None。
    println(List(1, 2, 3, 4, 5) find (_ % 2 == 0))
    println(List(1, 2, 3, 4, 5) find (_ <= 0))
    // takeWhile 和 dropWhile 操作符也将一个前提条件作为右操作元。xs takeWhile p 操作返回列表 xs 中连续满足 p 的最长前缀。
    // 同理，xs dropWhile p 操作将去除列表 xs 中连续满足 p 的最长前缀。
    println(List(1, 2, 3, -4, 5) takeWhile (_ > 0))
    println(words dropWhile (_ startsWith "t"))
    // span 方法将 takeWhile 和 dropWhile 两个操作合二为一，就像 splitAt 将 take 和 drop 合二为一一样。
    // 它返回一对列表，满足如下等式：xs span p 等于 (xs takeWhile p, xs dropWhile p)
    // 跟 splitAt 一样，span 同样不会重复遍历 xs：
    println(words span (_ startsWith "t"))

    println("\n【对列表的前提条件检查：forall 和 exists】")

    def hasZeroRow(m: List[List[Int]]) =
      m exists (row => row forall (_ == 0))

    val diag3: List[List[Int]] =
      List(
        List(1, 0, 0),
        List(0, 1, 0),
        List(0, 0, 1)
      )
    println(hasZeroRow(diag3))

    println("\n【折叠列表：foldLeft 和 foldRight】")

    // xs.foldLeft(z)(op) 操作涉及三个对象：起始值 z、列表 xs 和二元操作 op。折叠的结果是以 z 为前缀，对列表的元素依次连续应用 op。
    def sumFunc(xs: List[Int]): Int = xs.foldLeft(0)(_ + _)

    println(sumFunc(List(1, 2, 3, 4)))

    // 还有个例子可以说明 foldLeft 的用法。为了把列表中的字符串表示的单词拼接起来，在当中和最前面加上空格，可以：
    println(words.foldLeft("")(_ + " " + _))
    println(words.tail.foldLeft(words.head)(_ + " " + _))

    // foldLeft 操作产生一棵往左靠的操作树。与之相仿，还有另一个 foldRight 操作，产生一棵往右靠的操作树。
    // 对结合性的操作而言，左折叠和右折叠是等效的，不过可能存在执行效率上的差异。
    // 可以设想一下flatten方法对应的操作，这个操作是将一个列表的列表中的所有元素拼接起来。可以用左折叠也可以用右折叠来完成：
    def flattenLeft[T](xss: List[List[T]]) = (List[T]() /: xss) (_ ::: _)

    def flattenRight[T](xss: List[List[T]]) = (xss :\ List[T]()) (_ ::: _)
    // 由于列表拼接 xs ::: ys 的执行时间跟首个入参xs的长度成正比，用右折叠的 flattenRight 比用左折叠的 flattenLeft 更高效。
    // 左折叠在这里的问题是 flattenLeft(xss) 需要复制首个元素列表 xss.head n - 1 次，其中 n 为列表 xss 的长度。
    // 注意上述两个 flatten 版本都需要对表示折叠起始值的空列表做类型注解。
    // 这是由于 Scala 类型推断程序的一个局限，不能自动推断出正确的列表类型。

    // 操作(List(), x)等同于 List(x)，而 List(x) 也可以写作 x :: List()。
    // 这样我们就发现可以基于 :: 操作符把两个操作元反转一下来得到另一个操作（这个操作有时被称作“snoc”，即把 :: 的 “cons”反过来念）。
    // 同样地，为了让类型推断程序正常工作，这里的类型注解 List[T]() 是必需的。
    // 如果我们分析 reverseLeft 的时间复杂度，会发现它执行这个常量时间操作（即“snoc”）n 次。因此，reverseLeft 的时间复杂度是线性的。
    def reverseLeft[T](xs: List[T]) = (List[T]() /: xs) { (ys, y) => y :: ys }

    println("\n【列表排序：sortWith】")
    // xs sortWith before 这个操作对列表 xs 中的元素进行排序，其中 “xs” 是列表，而 “before” 是一个用来比较两个元素的函数。
    // 表达式 x before y 对于在预期的排序中 x 应出现在 y 之前的情况应返回 true。
    println(List(1, -3, 4, 2, 6) sortWith (_ < _))
    println(words sortWith (_.length > _.length))
    // 注意，sortWith 执行的是跟前一节的 msort 算法类似的归并排序。不过 sortWtih 是 List 类的方法，而 msort 定义在列表之外。
  }

  /**
   * 16.8 List 对象的方法
   */
  def test16_8(): Unit = {
    println(List.apply(1, 2, 3))
    // 我们在介绍 map 和 flatMap 的时候曾经用到过 range 方法，它创建的是一个包含一个区间的数值的列表。
    // 这个方法最简单的形式是 List.range(from, until)，创建一个包含了从 from 开始递增到 until 减 1 的数的列表。
    // 所以终止值 until 并不是区间的一部分。range 方法还有两一个版本，接收 step 作为第三个参数。
    // 这个操作交出的列表元素是从 from 开始，间隔为 step 的值。step 可以是正值也可以是负值
    println(List.range(1, 5))
    println(List.range(1, 9, 2))
    println(List.range(9, 1, -3))
    // fill 方法创建包含零个或多个同一个元素拷贝的列表。它接收两个参数：要创建的列表长度和需要重复的元素。
    // 两个参数各自以不同的参数列表给出
    println(List.fill(5)('a'))
    println(List.fill(3)("hello"))
    // 如果我们给 fill 的参数多于 1 个，那么它就会创建多维的列表。也就是说，它将创建出列表的列表、列表的列表的列表，等等。
    // 多出来的这些参数要放在第一个参数列表中。
    println(List.fill(2, 3)('b'))
    // tabulate 方法创建的是一个根据给定的函数计算的元素的列表。其入参跟 List.fill 的一样：
    // 第一个参数列表给出要创建列表的维度，而第二个参数列表描述列表的元素。唯一的区别是，元素值不再是固定的，而是从函数计算得来
    println(List.tabulate(5)(n => n * n))
    println(List.tabulate(5, 5)(_ * _))
    // concat 方法将多个列表拼接在一起。要拼接的列表通过 concat 的直接入参给出
    println(List.concat(List('a', 'b'), List('c')))
    println(List.concat(List(), List('b'), List('c')))
    println(List.concat())
  }

  /**
   * 16.9 同时处理多个列表
   */
  def test16_9(): Unit = {
    println((List(10, 20) zip List(3, 4, 5)).map { case (x, y) => x * y })
    // 查文档，lazyZip 方法应该是 2.13 版本才有
    // println((List(10, 20) lazyZip List(3, 4, 5)).map (_ * _))
  }

  def main(args: Array[String]): Unit = {
    test16_2()
    println("-------------")
    test16_3()
    println("-------------")
    test16_4()
    println("-------------")
    test16_5()
    println("-------------")
    test16_6()
    println("-------------")
    test16_7()
    println("-------------")
    test16_8()
    println("-------------")
    test16_9()
  }
}
