package com.zerox.chapter17

/**
 * @author zhuxi
 * @since 2022/6/14 14:12
 * @note
 * 第 17 章 使用其他集合类
 */
object Chapter17Test {

  def test17_1(): Unit = {
    val colors = List("red", "blue", "green")
    println(colors.head)
    println(colors.tail)
    val fiveInts = new Array[Int](5)
    println(fiveInts.mkString("Array(", ", ", ")"))
    val fiveToOne = Array(5, 4, 3, 2, 1)
    println(fiveToOne.mkString("Array(", ", ", ")"))
    fiveInts(0) = fiveToOne(4)
    println(fiveInts.mkString("Array(", ", ", ")"))
    // 另一种避免 reverse 操作的可选方案是使用 ListBuffer。
    // ListBuffer 是一个可变对象（包含在 scala.collection.mutable 包中），帮助我们在需要追加元素来构建列表时可以更高效。
    // ListBuffer 提供了常量时间的往后追加和往前追加的操作。我们可以用 += 操作符来往后追加元素，用 +=: 来往前追加元素。
    // 完成构建以后，我们可以调用 ListBuffer 的 toList 来获取最终的 List。
    import scala.collection.mutable.ListBuffer
    val listBuffer = new ListBuffer[Int]
    listBuffer += 1
    listBuffer += 2
    println(listBuffer)
    3 +=: listBuffer
    println(listBuffer.toList)
    // ArrayBuffer 跟数组很像，除了可以额外地从序列头部或尾部添加或移除元素。
    // 所有的 Array 操作在 ArrayBuffer 都可用，不过由于实现的包装，会稍慢一些。
    // 新的添加和移除操作平均而言是常量时间的，不过偶尔会需要线性的时间，这是因为其实现需要不时地分配新的数组来保存缓冲的内容。
    import scala.collection.mutable.ArrayBuffer
    val buf = new ArrayBuffer[Int]()
    buf += 12
    buf += 15
    println(buf)
    println(buf.length)
    println(buf(0))

    // 我们需要了解的另一个序列是 StringOps，它实现了很多序列方法。
    // 由于 Predef 有一个从 String 到 StringOps 的隐式转换，可以将任何字符串当作序列来处理。
    // 在本例中，hasUpperCase 方法体里，我们对名为 s 的字符串调用了 exists 方法。
    // 由于 String 类本身并没有声明任何名为 “exists” 的方法，Scala 编译器会隐式地将 s 转换成 StringOps，StringOps 有这样一个方法。
    // exists 方法将字符串当作字符的序列，当序列中存在大写字符时，这个方法将返回 true。
    def hasUpperCase(s: String) = s.exists(_.isUpper)

    hasUpperCase("Robert Frost")
    hasUpperCase("e e cummings")
  }

  def test17_2(): Unit = {
    import scala.collection.mutable
    val mutaSet = mutable.Set(1, 2, 3)
    println(mutaSet)

    println("\n【使用集】")
    val text = "See Spot run. Run, Spot. Run!"
    val wordsArray = text.split("[ !,.]+")
    println(wordsArray.mkString("Array(", ", ", ")"))
    val words = mutable.Set.empty[String]
    /// Set[]() 等同于 Set.apply(), 当 apply 参数为空时，其实等于调用上面的语句
    //    val words = mutable.Set[String]()
    for (word <- wordsArray)
      words += word.toLowerCase

    println(words)

    // 创建一个不可变集
    val numsSet = Set(1, 2, 3)
    // 添加一个
    println(numsSet + 5)
    // 移除一个
    println(numsSet - 3)
    // 添加多个
    println(numsSet ++ List(5, 6))
    // 移除多个
    println(numsSet -- List(1, 2))
    // 交集
    println(numsSet & Set(1, 3, 5, 7))
    println(numsSet.size)
    println(numsSet.contains(3))
    val wordSet = mutable.Set.empty[String]
    println(wordSet += "the")
    println(wordSet -= "the")
    println(wordSet ++= List("do", "re", "mi"))
    println(wordSet --= List("do", "re"))
    println(wordSet.clear)

    println("\n【使用映射】")
    val map = mutable.Map.empty[String, Int]
    map("hello") = 1
    map("there") = 2
    println(map)
    println(map("hello"))

    def countWords(text: String) = {
      val counts = mutable.Map.empty[String, Int]
      for (rawWord <- text.split("[ ,!.]+")) {
        val word = rawWord.toLowerCase
        val oldCount =
          if (counts.contains(word)) counts(word)
          else 0
        counts += (word -> (oldCount + 1))
      }
      counts
    }

    println(countWords("See Spot run! Run, Spot. Run!"))

    // 创建一个不可变映射
    val numsMap = Map("i" -> 1, "ii" -> 2)
    println(numsMap + ("vi" -> 6))
    println(numsMap - "ii")
    println(numsMap ++ List("iii" -> 3, "v" -> 5))
    println(numsMap -- List("i", "ii"))
    println(numsMap.size)
    println(numsMap.contains("ii"))
    println(numsMap("ii"))
    println(numsMap.keys)
    println(numsMap.keySet)
    println(numsMap.values)
    println(numsMap.isEmpty)
    // 创建一个空的可变映射
    val wordsMap = mutable.Map.empty[String, Int]
    println(wordsMap += ("one" -> 1))
    println(wordsMap -= ("one"))
    println(wordsMap ++= List("one" -> 1, "two" -> 2, "three" -> 3))
    println(wordsMap --= List("one", "two"))

    // 对于不可变集和映射而言，情况要稍微复杂一些。
    // 举例来说，scala.collection.immutable.Set() 工厂方法返回的类取决于我们传入了多少元素。
    // 对于少于五个元素的集，有专门的特定大小的类与之对应，以此来达到最好的性能。
    // 一旦我们要求一个大于等于五个元素的集，这个工厂方法将返回一个使用哈希字典树（hash trie）的实现。
    // 同理，scala.collection.immutable.Map() 这个工厂方法会根据我们传给它多少键值对来决定返回什么类的实现。
    // 跟集类似，对于少于五个元素的不可变映射，都会有一个特定的固定大小的映射与之对应，以此来达到最佳性能。
    // 而一旦映射中的键值对个数达到或超过五个，则会使用不可变的 HashMap。

    println("\n【排好序的集和映射】")
    // 有时我们可能需要一个迭代器按照特定顺序返回元素的集或映射。对此，Scala 集合类库提供了 SortedSet 和 SortedMap 特质。
    // 这些特质被 TreeSet 和 TreeMap 类实现，这些实现用红黑树来保持元素（对 TreeSet 而言）或键（对 TreeMap 而言）的顺序。
    // 具体顺序由 Ordering 特质决定，集的元素类型或映射的键的类型都能找到对应的 Ordering 隐式实例。这些类都有可变和不可变的版本。
    import scala.collection.immutable.TreeSet
    val ts = TreeSet(9, 3, 1, 8, 0, 2, 7, 4, 6, 5)
    println(ts)
    val cs = TreeSet('f', 'u', 'n')
    println(cs)
    import scala.collection.immutable.TreeMap
    var tm = TreeMap(3 -> 'x', 1 -> 'x', 4 -> 'x')
    println(tm)
    tm += (2 -> 'x')
    println(tm)
  }

  /**
   * 17.3 在可变和不可变集合类之间选择
   */
  def test17_3(): Unit = {
    // 为了让从不可变集转到可变集（或者反过来）更容易，Scala 提供了一些语法糖。
    // 尽管不可变集和映射并不真正支持 += 操作，Scala 提供了一个变通的解读：
    // 只要看到 a += b 而 a 并不支持名为 += 的方法，Scala 会尝试将它解读为 a = a + b。
    var people = Set("Nancy", "Jane")
    println(people)
    people += "Bob"
    println(people)
    // 在这一系列语句之后，变量 people 指向了新的集，包含添加的字符串"Bob"。同样的理念适用于任何以 = 结尾的方法，并不仅仅是 += 方法。
    // 以下是相同的语法规则应用于 -= 操作符的例子，这个操作符将某个元素从集里移除；以及 ++= 操作符的例子，将一组元素添加到集里
    people -= "Jane"
    people ++= List("Tom", "Harry")
    println(people)
  }

  /**
   * 17.4 初始化集合
   */
  def test17_4(): Unit = {
    println(List(1, 2, 3))
    println(Set('a', 'b', 'c'))
    import scala.collection.mutable
    println(mutable.Map("hi" -> 2, "there" -> 5))
    println(Array(1.0, 2.0, 3.0).mkString("Array(", ", ", ")"))

    val stuff = mutable.Set[Any](42)
    stuff += "abracadabra"
    println(stuff)

    val colors = List("blue", "yellow", "red", "green")
    import scala.collection.immutable.TreeSet
    /// to 加 TreeSet 的工厂方法的方式也是 2.13 版本引入的
    // val treeSet = colors to TreeSet
    // 先暂时使用我自己尝试出来的办法转换
    val treeSet = TreeSet(colors: _*)
    println(treeSet)
    println(treeSet.toList)
    println(treeSet.toArray.mkString("Array(", ", ", ")"))

    println("\n【在可变和不可变集及映射间转换】")
    val mutaSet = mutable.Set.empty ++= treeSet
    println(mutaSet)
    val immutaSet = Set.empty ++ mutaSet
    println(immutaSet)
    val muta = mutable.Map("i" -> 1, "ii" -> 2)
    val immu = Map.empty ++ muta
    println(immu)
  }

  /**
   * 17.5 元组
   */
  def test17_5(): Unit = {
    def longestWord(words: Array[String]) = {
      var word = words(0)
      var idx = 0
      for (i <- 1 until words.length)
        if (words(i).length > word.length) {
          word = words(i)
          idx = i
        }
      (word, idx)
    }

    val longest = longestWord("The quick brown fox".split(" "))
    println(longest)
    println(longest._1)
    println(longest._2)
    val (word, idx) = longest
    println(word)
    println(idx)
    // 这样的语法对相同的表达式给出了多重定义（multiple definitions）。每个变量都通过对等号右侧的表达式求值来初始化。
    // 本例中右侧表达式求值得到元组这个细节并不重要，两个变量都被完整地赋予了元组的值。
    val word2, idx2 = longest
    println(word2)
    println(idx2)
  }

  def main(args: Array[String]): Unit = {
    test17_1()
    println("--------------")
    test17_2()
    println("--------------")
    test17_3()
    println("--------------")
    test17_4()
    println("--------------")
    test17_5()
  }
}
