package com.zerox.chapter20

/**
 * @author zhuxi
 * @since 2022/6/16 14:58
 * @note
 * 第 20 章 抽象成员
 */
object Chapter20Test {
  /**
   * 20.5 初始化抽象的 val
   */
  def test20_5(): Unit = {
    try {
      // 本例抛出异常是因为 denomArg 在 RationalTrait 初始化的时候还是默认值 0，这让 require 的调用失败了。
      // 这个例子展示了类参数和抽象字段的初始化顺序并不相同。
      // 类参数在传入类构造方法之前被求值（传名参数除外），而在子类中实现的 val 定义则是在超类初始化之后被求值。
      val rationalTrait1 = new RationalTrait {
        val numerArg = 1
        val denomArg = 2
      }
      println(rationalTrait1)
    } catch {
      case ex: Exception => println(ex.getClass + ": " + ex.getMessage + "\n" +
        ex.getStackTrace.mkString("StackTrace(", "\n", ")"))
    }

    // 既然已经理解了为何抽象的 val 跟参数行为不同，最好也知道一下如何应对这个问题。
    // 有没有可能定义一个能被健壮地初始化的 RationalTrait，而不用担心未初始化字段带来的错误呢？
    // 事实上，Scala 提供了两种可选方案来应对这个问题：预初始化字段（pre-initialized field）和惰性的（lazy）val。

    // 第一种方案，预初始化字段，让我们在超类被调用之前初始化子类的字段。只需要在将字段定义放在超类的构造方法之前的花括号中即可。
    val x = 2
    val rationalTrait2 = new {
      val numerArg = 1 * x
      val denomArg = 2 * x
    } with RationalTrait
    println(rationalTrait2)

    /**
     * 预初始化字段并不仅仅局限于匿名类，它们也可以被用在对象或具名子类中。参考示例 20.6 和 20.7。
     * 从这些例子中可以看到，预初始化的代码段分别都出现在对象或类的 extends关键字之后。
     */
    object twoThirds extends {
      val numerArg = 2
      val denomArg = 3
    } with RationalTrait

    /**
     * 示例 20.7 的 RationalClass 类展示了如何让类参数被超特质的初始化用到的通用写法
     */
    class RationalClass(n: Int, d: Int) extends {
      val numerArg = n
      val denomArg = d
    } with RationalTrait {
      def +(that: RationalClass) = new RationalClass(numer * that.denom + that.numer * denom, denom * that.denom)
    }
    // 由于预初始化字段在超类的构造方法被调用之前初始化，它们的初始化代码不能引用那个正在被构造的对象。
    // 因此，如果这样的初始化代码引用了 this，这个引用将指向包含当前被构造的类或对象的对象，而不是被构造的对象本身。
    // new {
    //   val numerArg = 1
    //   val denomArg = this.numerArg * 2
    // } with RationalTrait
    // 这个例子不能正常编译，因为 this.numerArg 引用是在包含 new 的对象（在本例中是解释器放置用户输入的代码行的名为 $iw 的合成对象）中查找 numerArg 字段。
    // 再一次，预初始化字段在这方面跟类构造方法的入参行为类似。

    object Demo {
      val x = {
        println("initializing x")
        "done"
      }
    }
    println(Demo)
    println(Demo.x)
    object Demo2 {
      /**
       * 现在，Demo2 的初始化并不涉及对 x 的初始化。对 x 的初始化被延迟到第一次访问 x 的时候。这跟 x 用 def 定义成无参方法的情况类似。
       * 不过，不同于 def，惰性的 val 永远不会被求值多次。
       * 事实上，在对惰性的 val 首次求值之后，其结果会被保存起来，在后续的使用当中，都会复用这个相同的 val。
       */
      lazy val x = {
        println("initializing x")
        "done"
      }
    }
    println(Demo2)
    println(Demo2.x)

    val lazyRationalTrait = new LazyRationalTrait {
      val numerArg = 1 * x
      val denomArg = 2 * x
    }
    println(lazyRationalTrait)
    // 并不需要预初始化任何内容，有必要跟踪一下上述代码最终输出 1/2 这个字符串的初始化过程：
    // 1. LazyRationalTrait 的一个全新示例被创建， LazyRationalTrait 的初始化代码被执行。
    // 这段初始化代码是空的，这时 LazyRationalTrait 还没有任何字段被初始化。
    // 2. 由 new 表达式定义的匿名子类的主构造方法被执行。这包括用 2 初始化 numerArg，以及用 4 初始化 denomArg。
    // 3. 解释器调用了被构造对象的 toString 方法，以便打印出结果值。
    // 4. 在 LazyRationalTrait 特质的 toString 方法中，numer 字段被首次访问，因此，其初始化代码被执行。
    // 5. numer 的初始化代码访问了私有字段 g，因而 g 随之被求值。求值过程访问到 numerArg 和 denomArg，这两个变量已经在第 2 步被定义。
    // 6. toString 方法访问 denom 的值，这将引发 denom 的求值。对 denom 的求值会访问 denomArg 和 g 的值。
    // g 字段的初始化代码并不会被重新求值，因为它已经在第 5 步完成了求值。
    // 7. 最后，结果字符串 "1/2" 被构造并打印出来。
  }

  /**
   * 20.9 枚举
   */
  def test20_9(): Unit = {
    // 可以通过枚举的 values 方法返回的集来遍历枚举的值
    for (d <- Direction.values) print(d + " ")
    // 枚举的值从 0 开始编号，可以通过枚举值的 id 方法获取这个编号
    println("\n" + Direction.East.id)
    // 还可以反过来，从一个非负的整数编号获取以该数字为编号的枚举值：
    println(Direction(1))
    // 有了这些知识，应该就能开始使用枚举了。可以从 scala.Enumeration 类的 Scaladoc 注释获取到更多信息。
  }

  /**
   * 20.10 案例分析：货币
   */
  def test20_10(): Unit = {
    val res16 = Japan.Yen from US.Dollar * 100
    println(res16)
    val res17 = Europe.Euro from res16
    println(res17)
    val res18 = US.Dollar from res17
    println(res18)
    println(US.Dollar * 100 + res18)
  }

  def main(args: Array[String]): Unit = {
    test20_5()
    println("----------------")
    test20_9()
    println("----------------")
    test20_10()
  }
}
