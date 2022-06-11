package com.zerox.chapter11

/**
 * @Author: zhuxi
 * @Time: 2022/6/10 14:19
 * @Description: 第 11 章 Scala 的继承关系
 * @ModifiedBy: zhuxi
 */
object Chapter11Test {

  /**
   * 11.1 Scala 的类继承关系
   */
  def test11_1(): Unit = {
    // 在继承关系的顶部是 Any 类。
    // 由于每个类都继承自 Any，Scala 程序中的每个对象都可以用 ==、!= 或 equals 来进行比较，用 ## 或 hashCode 做哈希，以及用 toString 做格式化。
    // 相等和不等方法（== 和 !=）在 Any 类中声明为 final，所以它们不能被子类重写。
    // == 方法从本质上讲等同于 equals，而 != 一定是 equals 的反义。这样一来，子类可以通过重写 equals 方法来定制 == 或 != 的含义。

    // 根类 Any有两个子类：AnyVal 和 AnyRef。AnyVal 是 Scala 中所有值类（value class）的父类。
    // 虽然你可以定义自己的值类（参见 11.4 节），但 Scala 提供了九个内建的值类：Byte、Short、Char、Int、Long、Float、Double、Boolean 和 Unit。
    // 前八个对应 Java 的基本类型，它们的值在运行时是用 Java 的基本类型的值来表示的。这些类的实例在 Scala 中统统写作字面量。
    // 另外的那个值类 Unit 粗略地对应到 Java 的 void 类型；它用来作为那些不返回有趣的结果的方法的结果类型。Unit 有且只有一个实例值，写作 ()

    // 值类同样继承了 Any 类的所有方法
    println(42.toString)
    println(42.hashCode)
    println(42 equals 42)

    // 正如我们在 5.10 节提到的，隐式转换还被用于给值类型添加更多功能。例如，Int 类型支持所有下列操作：
    // 工作原理是这样的：方法 min、max、until、to 和 abs 都定义在 scala.runtime.RichInt 类中，并且存在从 Int 类到 RichInt 类的隐式转换。
    // 只要对 Int 调用的方法没有在 Int 类中定义，而 RichInt 类中定义了这样的方法，隐式转换就会被自动应用。
    println(42 max 43)
    println(42 min 43)
    println(1 until 5)
    println(1 to 5)
    println(3.abs)
    // 发现书里面好像没提前缀操作符的优先级，不过这里运行实际上应该 -3 不需要打括号也可以，说明负号前缀的优先级还是比 abs 高。
    println(-3.abs)

    // 根类 Any 的另一个子类是 AnyRef 类。这是 Scala 所有引用类的基类。
    // 前面我们提到过，在 Java 平台上 AnyRef 事实上只是 java.lang.Object 的一个别名。
    // 因此，Java 编写的类和 Scala 编写的类都继承自 AnyRef。因此，我们可以这样来看待java.lang.Object：它是AnyRef在Java平台的实现。
    // 虽然可以在面向 Java 平台的 Scala 程序中任意换用 Object 和 AnyRef，推荐的风格是尽量都使用 AnyRef。
  }

  /**
   * 11.2 基本类型的实现机制
   */
  def test11_2(): Unit = {
    // Scala 中的装箱跟 Java 相比要透明得多。
    // Scala 的相等性操作 == 被设计为对于类型的实际呈现是透明的。对于值类型而言，它表示的是自然（数值或布尔值）相等性。
    // 而对除 Java 装箱数值类型之外的引用类型，== 被处理成从 Object 继承的 equals 方法的别名。
    // 这个方法原本定义用于引用相等性，但很多子类都重写了这个方法来实现它们对于相等性更自然的理解和表示。
    // 这也意味着在 Scala 中不会陷入 Java 那个跟字符串对比相关的陷阱。
    def isEqual(x: Int, y: Int) = x == y

    println(isEqual(421, 421))

    def isEqual2(x: Any, y: Any) = x == y

    println(isEqual2(421, 421))

    // 在 Java 中，对 x 和 y 的对比结果会返回 false。程序员在这里应该用 equals，但是很容易忘记。
    val x = "abcd".substring(2)
    val y = "abcd".substring(2)
    println(x == y)

    // 不过，在有些场景下你需要引用相等性而不是用户定义的相等性。
    // 例如，有些场景对于效率的要求超高，你可能会对某些类使用 hash cons 并用引用相等性来比对其实例。
    // 对这些情况，AnyRef 类定义了一个额外的 eq 方法，该方法不能被重写，实现为引用相等性（即它的行为跟 Java 中 == 对于引用类型的行为是一致的）。
    // 还有一个 eq 的反义方法 ne。
    val a = new String("abc")
    val b = new String("abc")
    println(a == b)
    println(a eq b)
    println(a ne b)
  }

  /**
   * 11.3 底类型（bottom types）
   */
  def test11_3(): Unit = {
    // 类继承关系的底部，你会看到两个类：scala.Null 和 scala.Nothing。
    // 它们是 Scala 面向对象的类型系统用于统一处理某些“极端情况”（corner case）的特殊类型。
    // Null 类是 null 引用的类型，它是每个引用类（也就是每个继承自 AnyRef 的类）的子类。
    // Null 并不兼容于值类型，比如你并不能将 null 赋值给一个整数变量
    // Nothing 位于 Scala 类继承关系的底部，它是每个其他类型的子类型。不过，并不存在这个类型的任何值。

    // 这里 x / y 条件判断的“then”分支的类型为 Int，而 else 分支 （即调用 error 的部分）类型为 Nothing。
    // 由于 Nothing 是 Int 的子类型，整个条件判断表达式的类型就是 Int，正如方法声明要求的那样。
    def divide(x: Int, y: Int): Int = {
      if (y != 0) x / y
      // error 的返回类型是 Nothing，这告诉使用方该方法并不会正常返回（它会抛出异常）。
      // 由于 Nothing 是每个其他类型的子类型，可以以非常灵活的方式来使用 error 这样的方法。
      else sys.error("can't divide by zero")
    }

    try {
      println(divide(1, 0))
    } catch {
      case ex: Exception => println(ex.getStackTrace.mkString("stackTrace(", ",\n", ")"))
    }
  }

  /**
   * 11.4 定义自己的值类型
   */
  def test11_4(): Unit = {
    // 在本例中，money 指向该值类的一个实例。它在 Scala 源码中的类型为 Dollar，但在编译后的 Java 字节码中将直接使用 Int。
    // 这个例子定义了 toString 方法，编译器将识别出什么时候使用这个方法。
    // 这就是为什么打印 money 将给出 $1000000，带上了美元符号，而打印 money.amount 仅会给出 1000000。

    // Scala 2.13 才支持下划线分隔符？
    val money = new Dollars(1000000)
    println(money)
    println(money.amount)

    // 尽管 Dollars 和 SwissFrancs 最终都是以整数呈现的，在相同作用域内同时使用它们并没有什么问题
    println(new Dollars(1000))
    println(new SwissFrancs(1000))

    def title(text: Text, anchor: Anchor, style: Style): Html =
      new Html(
        s"<a id='${anchor.value}'>" +
          s"<h1 class='${style.value}'>" +
          text.value +
          "</h1></a>"
      )

    // 这时如果你再用错误的顺序调用这个版本的方法，编译器就可以探测到这个错误（并提示你）。
    println(title(new Text("Value Classes"), new Anchor("chap:vcls"), new Style("bold")).value)
  }

  // 值类不能放在 test11_4 方法下声明，会报错：value class may not be a local class
  class Dollars(val amount: Int) extends AnyVal {
    override def toString: String = "$" + amount
  }

  class SwissFrancs(val amount: Int) extends AnyVal {
    override def toString: String = s"$amount CHF"
  }

  // 要想尽可能发挥 Scala 类继承关系的好处，请试着对每个领域概念定义一个新的类，哪怕复用相同的类做不同的用途是可行的。
  // 即便这样的一个类是所谓的细微类型（tiny type），既没有方法也没有字段，定义这样的一个额外的类有助于编译器在更多的地方帮到你。
  // 如果你对每个领域概念都定义一个细微类型，编译器就能对你更有帮助。比如，可以分别对风格、锚定标识、显示文本和 HTML 等都定义一个小类。
  // 由于这些类只有一个参数，没有其他成员，它们可以被定义成值类
  class Anchor(val value: String) extends AnyVal

  class Style(val value: String) extends AnyVal

  class Text(val value: String) extends AnyVal

  class Html(val value: String) extends AnyVal

  def main(args: Array[String]): Unit = {
    test11_1()
    println("---------------")
    test11_2()
    println("---------------")
    test11_3()
    println("---------------")
    test11_4()
  }
}
