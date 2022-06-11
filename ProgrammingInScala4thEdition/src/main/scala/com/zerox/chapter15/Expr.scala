package com.zerox.chapter15

/**
 * @Author: zhuxi
 * @Time: 2022/6/11 15:19
 * @Description: 15.1 一个简单的例子
 *               示例 15.1 中另一个值得注意的点是每个子类都有一个 case 修饰符。带有这种修饰符的类称作样例类（case class）。
 *               用上这个修饰符会让 Scala 编译器对我们的类添加一些语法上的便利。
 *
 *               首先，它会添加一个跟类同名的工厂方法。这意味着我们可以用 Var(“x”) 来构造一个 Var 对象，而不用稍长版本的 new Var(“x”)
 *               其次，第二个语法上的便利是参数列表中的参数都隐式地获得了一个 val 前缀，因此它们会被当作字段处理
 *               再次，编译器会帮我们以“自然”的方式实现 toString、hashCode 和 equals 方法。这些方法分别会打印、哈希、比较包含类及所有入参的整棵树。
 *               由于 Scala 的 == 总是代理给 equals 方法，这意味着以样例类表示的元素总是以结构化的方式做比较
 *               最后，编译器还会添加一个 copy 方法用于制作修改过的拷贝。这个方法可以用于制作除一两个属性不同之外其余完全相同的该类的新实例。
 *               这个方法用到了带名字的参数和默认参数（参考 8.8 节）。我们用带名字的参数给出想要做的修改。
 *               对于任何你没有给出的参数，都会用老对象中的原值。
 *
 *               所有这些带来的是大量的便利（代价却很小）。你需要多写一个 case 修饰符，并且你的类和对象会变得大那么一点。
 *               之所以更大，是因为生成了额外的方法，并且对于构造方法的每个参数都隐式地添加了字段。
 *               不过，样例类最大的好处是它们支持模式匹配。
 *
 *               15.5 密封类
 *               将这些样例类的超类标记为密封（sealed）的。密封类除在同一个文件中定义的子类之外，不能添加新的子类。
 *               这一点对于模式匹配而言十分有用，因为这样一来我们就只需要关心那些已知的样例类。
 *               不仅如此，我们还因此获得了更好的编译器支持。如果我们对继承自密封类的样例类做匹配，编译器会用警告消息标示出缺失的模式组合。
 * @ModifiedBy: zhuxi
 */
sealed abstract class Expr

case class Var(name: String) extends Expr

case class Number(num: Double) extends Expr

case class UnOp(operator: String, arg: Expr) extends Expr

case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

