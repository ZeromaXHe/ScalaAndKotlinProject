package chapter13

/**
 * @Author: zhuxi
 * @Time: 2022/6/10 17:18
 * @Description: 第 13 章 包和引入
 *               到 Fruit 的便捷访问
 *               import chapter13.Fruit
 *               到 chapter13 所有成员的便捷访问
 *               import chapter13._
 *               到 Fruits 所有成员的便捷访问
 *               import chapter13.Fruits._
 *               第一个对应 Java 的单类型引入，而第二个对应 Java 的按需（on-demand）引入。
 *               唯一的区别是 Scala 的按需引入跟在后面的是下画线（_）而不是星号（*）（毕竟 * 是个合法的标识符！）。
 *               上述第三个引入子句对应Java对类静态字段的引入。
 *
 *               跟Java相比，Scala的import子句要灵活得多。主要的区别有三点，
 *               在Scala中，引入可以：
 *               • 出现在任意位置
 *               • 引用对象（不论是单例还是常规对象），而不只是包
 *               • 让你重命名并隐藏某些被引入的成员
 * @ModifiedBy: zhuxi
 */
object Chapter13Test {
  /**
   * Scala 的引入实际上更加通用。
   * 首先，Scala 的引入可以出现在任何地方，不仅仅是在某个编译单元的最开始，它们还可以引用任意的值。
   * showFruit 方法引入了其参数 fruit（类型为 Fruit）的所有成员。这样接下来的 println 语句就可以直接引用 name 和 color。
   * 这两个引用等同于 fruit.name 和 fruit.color。这种语法在你需要用对象来表示模块时尤其有用，我们将在第 29 章详细介绍。
   *
   * @param fruit
   */
  def showFruit(fruit: Fruit) = {
    import fruit._
    println(name + "s are " + color)
  }

  /**
   * 还有一点可以说明Scala的引入更灵活：它们可以引入包本身，而不仅仅是这些包中的非包成员。
   */
  class AStarB {

    import java.util.regex

    // 访问 java.util.regex.Pattern
    val pat = regex.Pattern.compile("a*b")
  }

  /**
   * Scala 中的引入还可以重命名或隐藏指定的成员。
   * 做法是包在花括号内的引入选择器子句（import selector clause）中，这个子句跟在那个我们要引入成员的对象后面。
   *
   * // 这只会从 Fruits 对象引入 Apple 和 Orange 两个成员。
   * import Fruits.{Apple, Orange}
   *
   * // 这会从 Fruits 对象引入 Apple 和 Orange 两个成员。
   * // 不过 Apple 对象被重命名为 McIntosh，因此，代码中要么用 Fruits.Apple 要么用 McIntosh 来访问这个对象。
   * // 重命名子句的形式永远都是“<原名> => <新名>”。
   * import Fruits.{Apple => McIntosh, Orange}
   *
   * // 这会以 SDate 为名引入 SQL 日期类，这样就可以同时以 Date 这个名字引入 Java 的普通日期对象。
   * import java.sql.{Date => SDate}
   *
   * // 这会以 S 为名引入 java.sql 包，这样就可以编写类似 S.Date 这样的代码。
   * import java.{sql => S}
   *
   * // 这将从 Fruits 对象引入所有的成员，跟 import Fruits._ 的含义是一样的。
   * import Fruits.{_}
   *
   * // 这将从 Fruits 对象引入所有的成员，但会把 Apple 重命名为 McIntosh。
   * import Fruits.{Apple => McIntosh, _}
   *
   * // 这会引入除 Pear 之外 Fruits 的所有成员。形如“<原名> => _”的子句将在引入的名称中排除<原名>。
   * // 从某种意义上讲，将某个名称重命名为“_”意味着将它完全隐藏掉。这有助于避免歧义。
   * import Fruits.{Pear => _, _}
   *
   * 这些例子展示了Scala在选择性地引入成员，以及用别名来引入成员方面提供的巨大灵活度。总之，引入选择器可以包含：
   * • 一个简单的名称 x。这将把 x 包含在引入的名称集里。
   * • 一个重命名子句 x => y。这会让名为x的成员以y的名称可见。
   * • 一个隐藏子句 x => _。这会从引入的名称集里排除掉x。
   * • 一个捕获所有（catch-all）的“_”。这会引入除之前子句中 提到的成员之外的所有成员。
   * 如果要给出捕获所有子句，它必须出现在引入选择器列表的末尾。
   *
   * 在本节最开始，给出的简单引入子句可以被视为带有选择器子句的引入子句的特殊简写。
   * 例如，“import p._”等价于“import p.{_}”，而“import p.n”等价于“import p.{n}”。
   */

  /**
   * 13.4 隐式引入
   * Scala 对每个程序都隐式地添加了一些引入。本质上，这就好比每个扩展名为“.scala”的源码文件的顶部都添加了如下三行引入子句：
   * import java.lang._
   * import scala._
   * import Predef._
   *
   * java.lang 包包含了标准的 Java 类，它总是被隐式地引入到 Scala 源码文件中。
   * 由于 java.lang 是隐式引入的，举例来说，可以直接写 Thread，而不是 java.lang.Thread。
   * 你无疑已经意识到，scala 包包含了 Scala 的标准类库，这里面有许多公用的类和对象。
   * 由于 scala 是隐式引入的，举例来说，可以直接写 List，而不是 scala.List。
   * Predef 对象包含了许多类型、方法和隐式转换的定义，这些定义在 Scala 程序中经常被用到。
   * 举例来说，由于 Predef 是隐式引入的，可以直接写 assert，而不是 Predef.assert。
   *
   * Scala 对这三个引入子句做了一些特殊处理，后引入的会遮挡前面的。
   * 举例来说，scala 包和 Java 1.5 版本后的 java.lang 包都定义了 StringBuilder类。由于 scala 的引入遮挡了 java.lang 的引入，
   * 因此 StringBuilder 这个简单名称会引用到 scala.StringBuilder，而不是 java.lang.StringBuilder。
   */

  /**
   * 在 Scala 中，像 (new Inner).f() 这样的访问方式是非法的，
   * 因为 f 在 Inner 中声明为 private，并且对 f 的调用并不是发生在 Inner 类内部。
   * 而第一次在 InnerMost 类中访问 f 是 OK 的，因为这个调用包含在 Inner 类内部。
   * Java 则对两种访问都允许，因为在 Java 中可以从外部类访问其内部类的私有成员。
   */
  class Outer {
    class Inner {
      private def f() = println("f")

      class InnerMost {
        f() // OK
      }
    }
    /// 错误：无法访问 f
    // (new Inner).f()
  }

  /**
   * 在示例 13.11 中，Sub 类对 f 的访问是 OK 的，因为在 Super 中 f 声明为 protected，而 Sub 是 Super 的子类。
   * 相反，Other 类对 f 的访问是被禁止的，因为 Other 并不继承自 Super。在 Java 中，后者依然被允许，因为 Other 跟 Sub 在同一个包中。
   */
  class Super {
    protected def f() = println("f")
  }

  class Sub extends Super {
    f()
  }

  class Other {
    /// 错误：无法访问 f
    // (new Super).f()
  }

  /**
   * Scala 并没有专门的修饰符用来标记公共成员：任何没有被标为 private 或 protected 的成员都是公共的。公共成员可以从任何位置访问到。
   *
   * 我们可以用限定词对 Scala 中的访问修饰符机制进行增强。
   * 形如 private[X] 或 protected[X] 的修饰符的含义是对此成员的访问限制“上至” X 都是私有或受保护的，
   * 其中 X 表示某个包含该定义的包、类或单例对象。
   * 所有的限定词也可以应用在 protected 上，跟private上的限定词作用一样。
   * 也就是说，如果我们在 C 类中使用 protected[X] 这个修饰符，那么 C 的所有子类，以及 X 表示的包、类或对象中，都能访问这个被标记的定义。
   *
   * 最后，Scala 还提供了比 private 限制范围更小的访问修饰符。被标记为 private[this] 的定义，只能在包含该定义的同一个对象中访问。
   * 这样的定义被称作是对象私有（object-private）的。
   *
   * Scala 的访问规则在 private 和 protected 的处理上给伴生对象和类保留了特权。
   * 一个类会将它的所有访问权跟它的伴生对象共享，反过来也一样。
   * 具体来说，一个对象可以访问它的伴生类的所有私有成员，一个类也可以访问它的伴生对象中的所有私有成员。
   *
   * Scala 和 Java 在修饰符的方面的确很相似，不过有一个重要的例外：protected static。
   * Java 中类 C 的 protected static 成员可以被 C 的所有子类访问。
   * 而对于 Scala 的伴生对象而言，protected 的成员没有意义，因为单例对象没有子类。
   */

  def main(args: Array[String]): Unit = {
    for(fruit <- Fruits.menu) {
      showFruit(fruit)
    }
  }
}
