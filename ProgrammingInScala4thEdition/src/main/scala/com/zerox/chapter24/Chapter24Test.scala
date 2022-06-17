package com.zerox.chapter24

/**
 * @author zhuxi
 * @since 2022/6/17 11:20
 * @note
 * 第 24 章 深入集合类
 */
object Chapter24Test {

  /**
   * 24.3 Iterable 特质
   */
  def test24_3(): Unit = {
    val xs = List(1, 2, 3, 4, 5)
    val git = xs grouped 3
    println(git.next())
    println(git.next())
    val sit = xs sliding 3
    println(sit.next())
    println(sit.next())
    println(sit.next())
  }

  /**
   * 24.6 映射
   */
  def test24_6(): Unit = {
    def f(x: String) = {
      println("taking my time.")
      Thread.sleep(100)
      x.reverse
    }

    val cache = collection.mutable.Map[String, String]()

    /**
     * getOrElseUpdate适用于对用作缓存的映射的访问。
     *
     * @param s
     * @return
     */
    def cachedF(s: String) = cache.getOrElseUpdate(s, f(s))

    println(cachedF("abc"))
    println(cachedF("abc"))
  }

  /**
   * 24.7 具体的不可变集合类
   */
  def test24_7(): Unit = {
    // LazyList 也是 2.13 引入的
    // val str = 1 #:: 2 #:: 3 #:: LazyList.empty

    println("【向量】")
    val vec = scala.collection.immutable.Vector.empty
    println(vec)
    val vec2 = vec :+ 1 :+ 2
    println(vec2)
    val vec3 = 100 +: vec2
    println(vec3)
    println(vec3(0))

    val vector = Vector(1, 2, 3)
    println(vector updated(2, 4))
    println(vector)
    // 由于向量在快速的任意位置的选择和快速的任意位置的函数式更新之间达到了较好的平衡，它们目前是不可变的带下标索引的序列的默认实现
    println(collection.immutable.IndexedSeq(1, 2, 3))

    println("\n【不可变的队列】")
    val empty = scala.collection.immutable.Queue[Int]()
    val has1 = empty.enqueue(1)
    println(has1)
    // enqueueAll 是 2.13 版本的方法
    // val has123 = has1.enqueueAll(List(2,3))
    val has123 = has1.enqueue(2).enqueue(3)
    println(has123)
    val (element, has23) = has123.dequeue
    println(element)
    println(has23)

    println("\n区间")
    println(1 to 3)
    println(5 to 14 by 3)
    println(1 until 3)

    println("\n【不可变的位组（bit set）】")
    val bits = scala.collection.immutable.BitSet.empty
    val moreBits = bits + 3 + 4 + 4
    println(moreBits)
    println(moreBits(3))
    println(moreBits(0))

    // 向量映射（VectorMap）也是 2.13 版本的
    // val vm = scala.collection.immutable.VectorMap.empty[Int, String]

    println("\n【列表映射】")
    // 列表映射将映射表示为一个由键值对组成的链表。一般而言，对列表映射的操作需要遍历整个列表，因此，对列表映射的操作耗时跟映射的规模成正比。
    // 事实上，Scala 对于列表映射用得很少，因为标准的不可变映射几乎总是比列表映射更快。唯一可能有区别
    // （译者注： 意思是列表映射比标准的不可变映射更快）的场景是当映射因为某种原因需要经常访问列表中的首个元素，频率远高于访问其他元素。
    val map = collection.immutable.ListMap(1 -> "one", 2 -> "two")
    println(map)
    println(map(2))
  }

  /**
   * 24.8 具体的可变集合类
   */
  def test24_8(): Unit = {
    println("【数组缓冲】")
    // 数组双向队列（ArrayDeque）是一种可变序列，支持在头部和尾部高效地添加元素。其内部使用了可变大小的数组。
    // 如果你需要对缓冲进行头部和尾部追加，用 ArrayDeque，而不是 ArrayBuffer。
    val arrbuf = collection.mutable.ArrayBuffer.empty[Int]
    println(arrbuf)
    arrbuf += 1
    println(arrbuf)
    arrbuf += 10
    println(arrbuf)
    println(arrbuf.toArray.mkString("Array(", ", ", ")"))

    println("\n【列表缓冲】")
    val listBuf = collection.mutable.ListBuffer.empty[Int]
    println(listBuf)
    listBuf += 1
    println(listBuf)
    listBuf += 10
    println(listBuf)
    println(listBuf.toList)

    println("\n【字符串构建器】")
    val buf = new StringBuilder
    buf += 'a'
    println(buf)
    buf ++= "bcdef"
    println(buf)
    println(buf.toString)

    println("\n【队列】")
    val queue = new scala.collection.mutable.Queue[String]
    println(queue)
    queue += "a"
    println(queue)
    queue ++= List("b", "c")
    println(queue)
    println(queue.dequeue)
    println(queue)

    println("\n【栈】")
    val stack = new scala.collection.mutable.Stack[Int]
    stack.push(1)
    println(stack)
    stack.push(2)
    println(stack)
    println(stack.top)
    println(stack)
    println(stack.pop)
    println(stack)

    println("\n【哈希表】")
    val map = collection.mutable.HashMap.empty[Int, String]
    println(map)
    map += (1 -> "make a web site")
    println(map)
    map += (3 -> "profit!")
    println(map)
    println(map(1))
    println(map contains 2)

    println("\n【可变的位组（bit set）】")
    val bits = scala.collection.mutable.BitSet.empty
    println(bits)
    bits += 1
    println(bits)
    bits += 3
    println(bits)
  }

  /**
   * 24.9 数组
   */
  def test24_9(): Unit = {
    val a1 = Array(1, 2, 3)
    println(a1.mkString("Array(", ", ", ")"))
    val a2 = a1 map (_ * 3)
    println(a2.mkString("Array(", ", ", ")"))
    val a3 = a2 filter (_ % 2 != 0)
    println(a3.mkString("Array(", ", ", ")"))
    println(a3.reverse.mkString("Array(", ", ", ")"))
    val seq: collection.Seq[Int] = a1
    println(seq)
    val a4: Array[Int] = seq.toArray
    println(a1 eq a4)

    println(seq.reverse)
    // 2.13 中所处的包不同
    // val ops: collection.ArrayOps[Int] = a1
    val ops: collection.mutable.ArrayOps[Int] = a1
    println(ops)
    println(ops.reverse)
    println(ops.reverse.mkString("Array(", ", ", ")"))

    import scala.reflect.ClassTag
    /**
     * 在新的定义当中，当 Array[T] 被创建时，编译器会查找类型参数 T 的类标签，也就是说，它会查找一个类型为 ClassTag[T] 的隐式值。
     * 如果找到这样的值，类标签就被用于构造正确类型的数组。不然，你就会看到前面那样的错误提示。
     *
     * @param xs
     * @tparam T
     * @return
     */
    def evenElems[T: ClassTag](xs: Vector[T]): Array[T] = {
      val arr = new Array[T]((xs.length + 1) / 2)
      for (i <- xs.indices by 2)
        arr(i / 2) = xs(i)
      arr
    }

    println(evenElems(Vector(1, 2, 3, 4, 5)).mkString("Array(", ", ", ")"))
    println(evenElems(Vector("this", "is", "a", "test", "run")).mkString("Array(", ", ", ")"))
  }

  /**
   * 24.10 字符串
   */
  def test24_10(): Unit = {
    val str = "hello"
    println(str.reverse)
    println(str.map(_.toUpper))
    println(str drop 3)
    println(str.slice(1, 4))
    val s: Seq[Char] = str
    println(s)
  }

  /**
   * 24.12 相等性
   */
  def test24_12(): Unit = {
    // 至于集合是不可变的还是可变的并不会影响相等性检查。对可变集合而言，相等性的判断仅取决于执行相等性判断当时的元素。
    // 这意味着，随着元素的添加和移除，可变集合可能会在不同的时间点跟不同的集合相等。当我们用可变集合作为哈希映射的键时，这是个潜在的坑。
    import collection.mutable.{ArrayBuffer, HashMap}
    val buf = ArrayBuffer(1, 2, 3)
    val map = HashMap(buf -> 3)
    println(map)
    println(map(buf))
    buf(0) += 1
    println(map.get(buf))
  }

  /**
   * 24.13 视图
   */
  def test24_13(): Unit = {
    val v = Vector(1 to 10: _*)
    println(v map (_ + 1) map (_ * 2))
    println((v.view map (_ + 1) map (_ * 2)).toVector)
    val vv = v.view
    println(vv)
    val res13 = vv map (_ + 1)
    println(res13)
    println(res13 map (_ * 2))

    def isPalidrome(x: String) = x == x.reverse

    def findPalidrome(s: Iterable[String]) = s find isPalidrome

    val words = List("a", "test")
    println(findPalidrome(words.view take 1000000))
  }

  /**
   * 24.14 迭代器
   */
  def test24_14(): Unit = {
    val it = Iterator("a", "number", "of", "words")
    val (it1, it2) = it.duplicate
    // 这里和书上不一样，it 没被移到末端，处于首位，依然可用
    println(it.hasNext)

    val res1 = it1.map(_.length)
    println(it1.hasNext)
    res1 foreach println
    println(it1.hasNext)

    // 这个和书上不一样…… dropWhile 之后，还是从 a 开始的
    // val it2 = Iterator("a", "number", "of", "words")
    it2 dropWhile (_.length < 2)
    println(it2.next())

    // 每个迭代器都可以被转成带缓冲的迭代器，方法是调用其 buffered 方法。
    val iterator = Iterator(1, 2, 3, 4)
    val bit = iterator.buffered
    println(bit.head)
    println(bit.next())
    println(bit.next())
  }

  /**
   * 24.16 Java 和 Scala 集合互转
   */
  def test24_16(): Unit = {
    import collection.JavaConverters._
    import collection.mutable._
    val jul: java.util.List[Int] = ArrayBuffer(1, 2, 3).asJava
    println(jul)
    val buf: Seq[Int] = jul.asScala
    println(buf)
    val m: java.util.Map[String, Int] = HashMap("abc" -> 1, "hello" -> 2).asJava
    println(m)
  }

  def main(args: Array[String]): Unit = {
    test24_3()
    println("---------")
    test24_6()
    println("---------")
    test24_7()
    println("---------")
    test24_8()
    println("---------")
    test24_9()
    println("---------")
    test24_10()
    println("---------")
    test24_12()
    println("---------")
    test24_13()
    println("---------")
    test24_14()
    println("---------")
    test24_16()
  }
}
