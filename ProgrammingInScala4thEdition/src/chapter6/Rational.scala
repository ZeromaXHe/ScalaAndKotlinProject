package chapter6

/**
 * @Author: zhuxi
 * @Time: 2022/6/9 15:48
 * @Description:
 * @ModifiedBy: zhuxi
 */
class Rational(n: Int, d: Int) extends Ordered[Rational] {
  /// Scala 编译器会将你在类定义体中给出的非字段或方法定义的代码编译进类的主构造方法中
  //  println("Created " + n + "/" + d)
  require(d != 0)

  // Scala 编译器会把 Rational 的三个字段的初始化器代码按照它们在代码中出现的先后次序编译进主构造方法中。
  private val g = gcd(n.abs, d.abs)
  val numer: Int = n / g
  val denom: Int = d / g

  /**
   * 在 Scala 中，主构造方法之外的构造方法称为辅助构造方法（auxiliary constructor）。
   * Scala 的辅助构造方法以 def this(...) 开始。
   * 在 Scala 中，每个辅助构造方法都必须首先调用同一个类的另一个构造方法。
   * 换句话说，Scala 每个辅助构造方法的第一条语句都必须是这样的形式：“this(...)”。
   * 被调用的这个构造方法要么是主构造方法（就像 Rational 示例那样），要么是另一个出现在发起调用的构造方法之前的另一个辅助构造方法。
   * 这个规则的净效应是 Scala 的每个构造方法最终都会调用到该类的主构造方法。这样一来，主构造方法就是类的单一入口。
   *
   * 如果你熟悉 Java，可能会好奇为什么 Scala 的构造方法规则比 Java 更严格。
   * 在 Java 中，构造方法要么调用同一个类的另一个构造方法，要么直接调用超类的构造方法。
   * 而在 Scala 类中，只有主构造方法可以调用超类的构造方法。
   * Scala 这个增强的限制实际上是一个设计的取舍，用来换取更精简的代码和跟 Java 相比更为简单的构造方法。
   * 我们将会在第 10 章详细介绍超类，以及构造方法和继承的相互作用。
   *
   * @param n
   */
  def this(n: Int) = this(n, 1)

  def +(that: Rational): Rational =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )

  def +(i: Int): Rational =
    new Rational(numer + i * denom, denom)

  def -(that: Rational): Rational =
    new Rational(
      numer * that.denom - that.numer * denom,
      denom * that.denom
    )

  def -(i: Int): Rational =
    new Rational(numer - i * denom, denom)

  def *(that: Rational): Rational =
    new Rational(numer * that.numer, denom * that.denom)

  def *(i: Int): Rational =
    new Rational(numer * i, denom)

  def /(that: Rational): Rational =
    new Rational(numer * that.denom, denom * that.numer)

  def /(i: Int): Rational =
    new Rational(numer, denom * i)

  override def toString: String = s"$numer/$denom"

  /**
   * 关键字 this 指向当前执行方法的调用对象，当被用在构造方法里的时候，指向被构造的对象实例。
   * 在这里，this.numer 指向执行 lessThan 调用的对象的分子。也可以省去 this 前缀，只写 numer。这两种表示法是等效的。
   *
   * @param that
   * @return
   */
  def lessThan(that: Rational) =
    this.numer * that.denom < that.numer * this.denom

  /**
   * 再举个不能省去this的例子，假定我们要给 Rational 添加一个 max 方法，返回给定的有理数和参数之间较大的那个
   * 在这里，第一个 this 是冗余的。完全可以不写 this，直接写 lessThan(that)。
   * 但第二个 this 代表了当测试返回 false 时该方法的结果；如果不写 this，就没有可返回的结果了！
   *
   * @param that
   * @return
   */
  def max(that: Rational) =
    if (this.lessThan(that)) that else this

  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)

  /**
   * 12.4 Ordered 特质
   * 你只需要做两件事。首先，这个版本的 Rational 混入了 Ordered 特质。
   * 与你看到过的其他特质不同，Ordered 要求你在混入时传入一个类型参数（type paramter）。
   * 你需要做的第二件事是定义一个用来比较两个对象的 compare 方法，该方法应该比较接收者，即 this，和作为参数传入该方法的对象。
   * 如果两个对象相同，它应该返回 0；如果接收者比入参小，应该返回负值；如果接收者比入参大，则返回正值。
   *
   * 要小心 Ordered 特质并不会帮你定义 equals 方法，因为它做不到。
   * 这当中的问题在于用 compare 来实现 equals 需要检查传入对象的类型，而由于（Java 的）类型擦除机制，Ordered 特质自己无法完成这个检查。
   * 因此，需要自己定义 equals 方法，哪怕你已经继承了 Ordered。
   *
   * 个人注：特质的方法 override 关键字不是强制的，重写具体实现才必须
   *
   * @param that
   * @return
   */
  def compare(that: Rational): Int =
    (this.numer * that.denom) - (that.numer * this.denom)
}
