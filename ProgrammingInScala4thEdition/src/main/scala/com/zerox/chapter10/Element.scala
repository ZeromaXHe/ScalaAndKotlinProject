package com.zerox.chapter10

import com.zerox.chapter10.Element.elem

/**
 * @Author: zhuxi
 * @Time: 2022/6/10 10:46
 * @Description: 10.2 抽象类、10.3 定义无参方法、10.12 实现 above、beside 和 toString、10.14 增高和增宽
 * @ModifiedBy: zhuxi
 */
abstract class Element {
  // 在这个类中，contents被声明为一个没有实现的方法。换句话说，这个方法是Element类的抽象（abstract）成员。
  // 一个包含抽象成员的类本身也要声明为抽象的，做法是在 class 关键字之前写上 abstract 修饰符
  // 注意，Element 类中的 content 方法并没有标上 abstract 修饰符。一个方法只要没有实现（即没有等号或方法体），那么它就是抽象的。
  // 跟 Java 不同，我们并不需要（也不能）对方法加上 abstract 修饰符。那些给出了实现的方法叫作具体（concrete）方法。
  // 另一组在叫法上的区分是声明（declaration）和定义（definition）。Element 类声明了 content 这个抽象方法，但目前并没有定义具体的方法。
  // 不过在下一节，我们将通过定义一些具体方法来增强 Element。
  def contents: Array[String]

  def height: Int = contents.length

  def width: Int = if (height == 0) 0 else contents(0).length
  // 注意，Element 的三个方法无一例外都没有参数列表，连空参数列表都没有。不带圆括号来定义这些方法。
  // 这样的无参方法（parameterless method）在 Scala 中很常见。
  // 与此对应，那些用空的圆括号定义的方法，比如 def height(): Int，被称作空圆括号方法（empty-paren method）。
  // 推荐的做法是对于没有参数且只通过读取所在对象字段的方式访问可变状态（确切地说不改变状态）的情况下尽量使用无参方法。
  // 这样的做法支持所谓的统一访问原则（uniform access principle）：使用方代码不应受到某个属性是用字段还是用方法实现的影响。

  // 总结下来就是，Scala鼓励我们将那些不接收参数也没有副作用的方法定义为无参方法（即省去空括号）。
  // 同时，对于有副作用的方法，不应该省去空括号，因为省掉括号以后这个方法调用看上去就像是字段选择，因此你的使用方可能会对其副作用感到意外。
  // 同理，每当你调用某个有副作用的函数，请确保在写下调用代码时加上空括号。
  // 换一个角度来思考这个问题，如果你调用的这个函数 执行了某个操作，就加上括号，而如果它仅仅是访问某个属性，则可以省去括号。

  // 个人注：IDEA 的对于方法调用的黄色 warning 底色也是根据方法定义时是无参方法还是空圆括号方法判断的

  /**
   * 其中 ++ 这个操作将两个数组拼接在一起。Scala 中的数组是用 Java 的数组表示的，不过支持更多的方法。
   * 具体来说，Scala 的数组可以被转换成 scala.Seq 类的实例，这个类代表了类似于序列的结构，包含了访问和转换序列的若干方法。
   *
   * 事实上，前面给出的代码并不是很够用，因为它并不允许你将宽度不同的元素叠在一起。
   * 不过为了让事情保持简单，我们将不理会这个问题，只是每次都记得传入相同长度的元素给 above。
   *
   * @param that
   * @return
   */
  def above(that: Element): Element = {
    val this1 = this widen that.width
    val that1 = that widen this.width
    // 在 Scala 中，断言的写法是对预定义方法 assert 的调用。
    // 如果 condition 不满足，表达式 assert(condition) 将抛出 AssertionError。
    // assert 还有另一个版本：assert(condition, explanation)，
    // 首先检查 condition 是否满足，如果不满足，那么就抛出包含给定 explanation 的 AssertionError。
    // explanation 的类型为 Any，因此可以传入任何对象。
    // assert 方法将调用 explanation 的 toString 方法来获取一个字符串的解释放入 AssertionError。
    assert(this1.width == that1.width)
    elem(this1.contents ++ that1.contents)
  }

  /**
   * 下一个要实现的方法是 beside。要把两个元素并排放在一起，我 们将创建一个新的元素。
   * 在这个新元素中，每一行都是由两个元素的对应行拼接起来的。跟之前一样，为了让事情保持简单，我们一开始假定两个元素有相同的高度
   *
   * @param that
   * @return
   */
  def beside(that: Element): Element = {
    val this1 = this heighten that.height
    val that1 = that heighten this.height
    /// 虽然这个 beside 的实现可以解决问题，它是用指令式风格编写的，明显的标志是我们用下标遍历数组时使用的循环。
    //    val contents = new Array[String](this.contents.length)
    //    /// 原文的写法可以简化
    ////    for (i <- 0 until this.contents.length)
    //    for (i <- this.contents.indices)
    //      contents(i) = this.contents(i) + that.contents(i)
    //    new ArrayElement(contents)
    elem(
      for (
        // 在这里，我们用 zip 操作符将 this.contents 和 that.contents 这两个数组转换成对偶（即Tuple2）的数组。
        // 这个 zip 操作符从它的两个操作元中选取对应的元素，组装成一个对偶（pair）的数组。
        (line1, line2) <- this1.contents zip that1.contents
      ) yield line1 + line2
    )
  }

  /**
   * 示例 10.13 展示了一个私有的助手方法 widen，接收一个宽度参数并返回这个宽度的元素。
   * 结果包含了这个 Element 元素的内容，两侧用空格填充，来达到要求的宽度。
   *
   * @param w
   * @return
   */
  def widen(w: Int): Element = {
    if (w <= width) this
    else {
      val left = elem(' ', (w - width) / 2, height)
      val right = elem(' ', w - width - left.width, height)
      left beside this beside right
    } ensuring (w <= _.width)
    // ensuring 这个方法可以被用于任何结果类型，这得益于一个隐式转换。
    // 虽然这段代码看上去调用的是 widen 结果的 ensuring 方法，实际上调用的是某个可以从 Element 隐式转换得到的类型的 ensuring 方法。
    // 该方法接收一个参数，这是一个接收结果类型参数并返回 Boolean 的前提条件函数。ensuring 所做的，就是把计算结果传递给这个前提条件函数。
    // 如果前提条件函数返回 true，那么 ensuring 就正常返回结果；如果前提条件返回 false，那么 ensuring 将抛出 AssertionError。
  }

  /**
   * 示例 10.13 还展示了另一个类似的方法 heighten，执行同样的功能，只不过方向变成了纵向的。
   *
   * @param h
   * @return
   */
  def heighten(h: Int): Element =
    if (h <= height) this
    else {
      val top = elem(' ', width, (h - height) / 2)
      val bot = elem(' ', width, h - height - top.height)
      top above this above bot
    }

  /**
   * toString 的实现用到了 mkString，这个方法对所有序列都适用，包括数组。
   * 如你在 7.8 节看到的，类似“arr mkString sep”这样的表达式将返回一个包含 arr 数组所有元素的字符串。
   * 每个元素都通过 toString 被映射成字符串。在连续的字符串元素中间，还会插入一个 sep 字符串用作分隔。
   * 因此，“contents mkString "\n"”这样的表达式将 contents 数组格式化成一个字符串，每个数组元素都独占一行。
   * 注意，toString 并没有带上一个空参数列表。这符合统一访问原则的建议，因为 toString 是一个不接收任何参数的纯方法。
   *
   * @return
   */
  override def toString: String = contents mkString "\n"
}

/**
 * 10.13 定义工厂对象
 */
object Element {
  // 除此之外，有了工厂方法后，ArrayElement、LineElement 和 UniformElement 这些子类可以变成私有的，因为它们不再需要被使用方直接访问了。
  // 在 Scala 中，可以在其他类或单例对象中定义类和单例对象。
  // 将 Element 的子类变成私有的方式之一是将它们放在 Element 单例对象当中，并声明为私有。
  // 这些类在需要时仍可以被那三个 elem 工厂方法访问到。
  // （这里我就不把之前类变成这里的私有类了，不然之前的代码都调不了了）
  def elem(contents: Array[String]): Element =
    new ArrayElement(contents)

  def elem(chr: Char, width: Int, height: Int): Element =
    new UniformElement(chr, width, height)

  def elem(line: String): Element =
    new LineElement(line)
}
