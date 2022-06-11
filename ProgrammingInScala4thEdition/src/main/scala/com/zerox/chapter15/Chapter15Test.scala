package com.zerox.chapter15

import scala.math.{E, Pi, exp}

/**
 * @Author: zhuxi
 * @Time: 2022/6/11 15:30
 * @Description: 第 15 章 样例类和模式匹配
 * @ModifiedBy: zhuxi
 */
object Chapter15Test {

  /**
   * 15.1 一个简单的例子
   */
  def test15_1(): Unit = {
    // 首先，它会添加一个跟类同名的工厂方法。这意味着我们可以用 Var(“x”) 来构造一个 Var 对象，而不用稍长版本的 new Var(“x”)
    val v = Var("x")
    // 当你需要嵌套定义时，工厂方法尤为有用。由于代码中不再到处落满 new 关键字，可以一眼就看明白表达式的结构
    val op = BinOp("+", Number(1), v)
    // 其次，第二个语法上的便利是参数列表中的参数都隐式地获得了一个 val 前缀，因此它们会被当作字段处理
    println(v.name)
    println(op.left)
    // 再次，编译器会帮我们以“自然”的方式实现 toString、hashCode 和 equals 方法。这些方法分别会打印、哈希、比较包含类及所有入参的整棵树。
    // 由于 Scala 的 == 总是代理给 equals 方法，这意味着以样例类表示的元素总是以结构化的方式做比较
    println(op)
    println(op.right == Var("x"))
    // 最后，编译器还会添加一个 copy 方法用于制作修改过的拷贝。这 个方法可以用于制作除一两个属性不同之外其余完全相同的该类的新实例。
    // 这个方法用到了带名字的参数和默认参数（参考 8.8 节）。我们用带名字的参数给出想要做的修改。对于任何你没有给出的参数，都会用老对象中的原值。
    println(op.copy(operator = "-"))

    /**
     * 模式匹配包含一系列以 case 关键字打头的可选分支（alternative）。
     * 每一个可选分支都包括一个模式（pattern）以及一个或多个表达式，如果模式匹配了，这些表达式就会被求值。
     * 箭头符=>用于将模式和表达式分开。一个 match 表达式的求值过程是按照模式给出的顺序逐一尝试的。
     * 第一个匹配上的模式被选中，跟在这个模式后面的表达式被执行。
     * 类似 "+" 和 1 这样的常量模式（constant pattern）可以匹配那些按照 == 的要求跟它们相等的值。
     * 而像 e 这样的变量模式（variable pattern）可以匹配任何值。匹配后，在右侧的表达式中，这个变量将指向这个匹配的值。
     * 在本例中，注意前三个可选分支都求值为 e，一个在对应的模式中绑定的变量。
     * 通配模式（wildcard pattern），即 _ 也匹配任何值，不过它并不会引入一个变量名来指向这个值。
     * 构造方法模式（constructor pattern）看上去就像 UnOp("-", e)。
     * 这个模式匹配所有类型为 UnOp 且首个入参匹配 "-"，而第二个入参将被绑定为 e。注意构造方法的入参本身也是模式。
     * 这允许我们用精简的表示法来编写有深度的模式。
     *
     * match 表达式可以被看作 Java 风格的 switch 的广义化。
     * Java 风格的 switch 可以很自然地用 match 表达式表达，其中每个模式都是常量且最后一个模式可以是一个通配模式（代表 switch 中的默认 case）。
     * 不过，我们需要记住三个区别：
     * 首先，Scala 的 match 是一个表达式（也就是说它总是能得到一个值）。
     * 其次，Scala 的可选分支不会贯穿（fall through）到下一个 case。
     * 最后，如果没有一个模式匹配上，会抛出名为 MatchError 的异常。
     * 这意味着你需要确保所有的 case 被覆盖到，哪怕这意味着你需要添加一个什么都不做的默认 case。
     *
     * @param expr
     * @return
     */
    def simplifyTop(expr: Expr): Expr = expr match {
      case UnOp("-", UnOp("-", e)) => e
      case BinOp("+", e, Number(0)) => e
      case BinOp("*", e, Number(1)) => e
      case _ => expr
    }

    println(simplifyTop(UnOp("-", UnOp("-", Var("x")))))
  }

  /**
   * 15.2 模式的种类
   */
  def test15_2(): Unit = {
    /**
     * 通配模式(_)会匹配任何对象。前面已经看到过通配模式用于默认、捕获所有的可选路径。通配模式还可以用来忽略某个对象中你并不关心的局部。
     * 例如，前面这个例子实际上并不需要关心二元操作的操作元是什么，它只是检查这个表达式是否是二元操作，仅此而已。
     * 因此，这段代码也完全可以用通配模式来表示 BinOp 的操作元
     *
     * @param expr
     */
    def wildcardPatternTest(expr: Expr): Unit = expr match {
      case BinOp(_, _, _) => println(expr + " is a binary operation")
      case _ => println("It's something else")
    }

    wildcardPatternTest(BinOp("+", Var("x"), Var("y")))

    /**
     * 常量模式仅匹配自己。任何字面量都可以作为常量（模式）使用。例如，5、true 和 "hello" 都是常量模式。
     * 同时，任何 val 或单例对象也可以被当作常量（模式）使用。例如，Nil 这个单例对象能且仅能匹配空列表。
     *
     * @param x
     * @return
     */
    def describe(x: Any) = x match {
      case 5 => "five"
      case true => "truth"
      case "hello" => "hi!"
      case Nil => "the empty list"
      case _ => "something else"
    }

    println(describe(5))
    println(describe(true))
    println(describe("hello"))
    println(describe(Nil))
    println(describe(List(1, 2, 3)))

    /**
     * 变量模式匹配任何对象，这一点跟通配模式相同。不过不同于通配模式的是，Scala将对应的变量绑定成匹配上的对象。
     * 在绑定之后，你就可以用这个变量来对对象做进一步的处理。
     *
     * @param expr
     * @return
     */
    def variablePatternTest(expr: Int) = expr match {
      case 0 => "zero"
      case somethingElse => "not zero:" + somethingElse
    }

    println(variablePatternTest(0))
    println(variablePatternTest(1))

    // 常量模式也可以有符号形式的名称。当我们把 Nil 当作一个模式的时候，实际上就是在用一个符号名称来引用常量。
    println(E match {
      case Pi => "strange math? Pi = " + Pi
      case _ => "OK"
    })

    // Scala 编译器是如何知道 Pi 是从 scala.math 包引入的常量，而不是一个代表选择器值本身的变量呢？
    // Scala 采用了一个简单的词法规则来区分：一个以小写字母打头的简单名称会被当作模式变量处理；所有其他引用都是常量。
    // 想看到具体的区别？可以给 Pi 创建一个小写的别名
    val pi = math.Pi
    // 在这里编译器甚至不允许我们添加一个默认的 case。由于 pi 是变量模式，它将会匹配所有输入，因此不可能走到后面的 case
    // (个人注：为啥我这样就可以正常编译？是 2.11.12 之后版本才不允许？ 2.11.12 只有两个相关的 warning：
    // patterns after a variable pattern cannot match (SLS 8.1.1) 和 unreachable code due to variable pattern 'pi')
    println(E match {
      case pi => "strange math? Pi = " + pi
      case _ => "OK"
    })

    // 如果需要，仍然可以用小写的名称来作为模式常量，有两个小技巧。首先，如果常量是某个对象的字段，可以在字段名前面加上限定词。
    // 例如，虽然 pi 是个变量模式，但 this.pi 或 obj.pi 是常量（模式），尽管它们以小写字母打头。
    // 如果这样不行（比如说 pi 可能是个局部变量），也可以用反引号将这个名称包起来。例如 `pi` 就能再次被编译器解读为一个常量，而不是变量了。
    println(E match {
      // 你应该看到了，给标识符加上反引号在 Scala 中有两种用途，来帮助你从不寻常的代码场景中走出来。
      // 这里你看到的是如何将小写字母打头的标识符用作模式匹配中的常量。
      // 更早的时候，在 6.10 节，你还看到过反引号可以用来将关键字当作普通的标识符，比如 Thread.`yield`() 这段代码将 yield 当作标识符而不是关键字。
      case `pi` => "strange math? Pi = " + pi
      case _ => "OK"
    })

    /**
     * 构造方法模式是真正体现出模式匹配威力的地方。一个构造方法模式看上去像这样：“BinOp("+", e, Number(0))”。
     * 它由一个名称（BinOp）和一组圆括号中的模式："+"、e 和 Number(0) 组成。假定这里的名称指定的是一个样例类，
     * 这样的一个模式将首先检查被匹配的对象是否是以这个名称命名的样例类的实例，然后再检查这个对象的构造方法参数是否匹配这些额外给出的模式。
     * 这些额外的模式意味着 Scala 的模式支持深度匹配（deep match）。
     * 这样的模式不仅检查给出的对象的顶层，还会进一步检查对象的内容是否匹配额外的模式要求。
     * 由于额外的模式也可能是构造方法模式，用它们来检查对象内部时可以到任意的深度。
     *
     * @param expr
     */
    def constructorPatternTest(expr: Expr) = expr match {
      case BinOp("+", e, Number(0)) => println("a deep match")
      case _ =>
    }

    constructorPatternTest(BinOp("+", Number(1), Number(0)))

    /**
     * 就跟与样例类匹配一样，也可以跟序列类型做匹配，比如 List 或 Array。使用的语法是相同的，不过现在可以在模式中给出任意数量的元素。
     * 如果你想匹配一个序列，但又不想给出多长，你可以用_*作为模式的最后一个元素。
     * 这个看上去有些奇怪的模式能够匹配序列中任意数量的元素，包括0个元素。
     *
     * @param expr
     */
    def sequencePatternTest(expr: List[Int]) = expr match {
      case List(0, _, _) => println("found it")
      case List(0, _*) => println("found other")
      case _ =>
    }

    sequencePatternTest(List(0))
    sequencePatternTest(List(0, 1, 2))
    sequencePatternTest(List(0, 1, 2, 3))

    /**
     * 我们还可以匹配元组（tuple）。形如 (a, b, c) 这样的模式能匹配任意的三元组。
     *
     * @param expr
     */
    def tupleDemo(expr: Any) = expr match {
      case (a, b, c) => println("matched " + a + b + c)
      case _ =>
    }

    tupleDemo(("a ", 3, "-tuple"))

    /**
     * 可以用带类型的模式（typed pattern）来替代类型测试和类型转换
     * 需要注意的是，尽管 s 和 x 指向同一个值，x 的类型是 Any，而 s 的类型是 String。
     * 因此可以在与模式相对应的可选分支中使用 s.length，但不能写成 x.length，因为类型 Any 并没有一个叫作 length 的成员。
     *
     * 示例 15.11 中的 match 表达式的第二个 case 包含了带类型的模式 “m: Map[_, _]”。
     * 这个模式匹配的是任何 Map 值，不管它的键和值的类型是什么，然后让 m 指向这个值。
     * 因此，m.size 的类型是完备的，返回的是这个映射（map）的大小。类型模式（type pattern）中的下画线就像是其他模式中的通配符。
     * 除了用下画线，也可以用（小写的）类型变量。
     *
     * @param x
     * @return
     */
    def generalSize(x: Any) = x match {
      case s: String => s.length
      case m: Map[_, _] => m.size
      case _ => -1
    }

    println(generalSize("abc"))
    println(generalSize(Map(1 -> 'a', 2 -> 'b')))
    println(generalSize(math.Pi))

    // 另一个跟用带类型的模式匹配等效但是更冗长的方式是做类型测试然后（强制）类型转换。
    // 对于类型测试和转换，Scala 跟 Java 的语法不太一样。
    def generalSizeNotRecommendEdition(x: Any) = {
      // 你现在应该已经注意到了，在 Scala 中编写类型测试和类型检查会比较啰唆。我们是有意为之，因为这并不是一个值得鼓励的做法。
      // 通常，使用带类型的模式会更好，尤其是当你需要同时做类型测试和类型转换的时候，因为这两个操作所做的事情会被并在单个模式匹配中完成。
      // （IDEA 都会提醒你换掉这种写法……）
      if (x.isInstanceOf[String]) {
        val s = x.asInstanceOf[String]
        s.length
      } else -1
    }

    /**
     * Scala 采用了擦除式的泛型，就跟 Java 一样。这意味着在运行时并不会保留类型参数的信息。
     * 这么一来，我们在运行时就无法判断某个给定的 Map 对象是用两个 Int 的类型参数创建的，还是其他什么类型参数创建的。
     * 系统能做的只是判断某个值是某种不确定类型参数的 Map。
     *
     * @param x
     * @return
     */
    def isIntIntMap(x: Any) = x match {
      case m: Map[Int, Int] => true
      case _ => false
    }

    // 第一次应用返回 true，看上去是正确的，不过第二次应用同样返回 true，这可能会让你感到意外。
    // 为了警示这种可能违反直觉的运行时行为，编译器会给出前面我们看到的那种非受检的警告：
    // non-variable type argument Int in type pattern scala.collection.immutable.Map[Int,Int]
    // (the underlying of Map[Int,Int]) is unchecked since it is eliminated by erasure
    println(isIntIntMap(Map(1 -> 1)))
    println(isIntIntMap(Map("abc" -> "abc")))

    def isStringArray(x: Any) = x match {
      case a: Array[String] => "yes"
      case _ => "no"
    }

    val as = Array("abc")
    println(isStringArray(as))
    val ai = Array(1, 2, 3)
    println(isStringArray(ai))

    /**
     * 除了独自存在的变量模式，我们还可以对任何其他模式添加变量。只需要写下变量名、一个 @ 符和模式本身，就得到一个变量绑定模式。
     * 意味着这个模式将跟平常一样执行模式匹配，如果匹配成功，就将匹配的对象赋值给这个变量，就像简单的变量模式一样。
     *
     * @param expr
     * @return
     */
    def variableBindingTest(expr: Expr) = expr match {
      case UnOp("abs", e@UnOp("abs", _)) => e
      case _ =>
    }

    println(variableBindingTest(UnOp("abs", UnOp("abs", Number(-1)))))
  }

  /**
   * 15.3 模式守卫
   */
  def test15_3(): Unit = {
    /**
     * 因为 Scala 要求模式都是线性（linear）的：同一个模式变量在模式中只能出现一次。
     * 不过，我们可以用一个模式守卫 （pattern guard）来重新定义这个匹配逻辑
     *
     * 模式守卫出现在模式之后，并以 if 打头。模式守卫可以是任意的布尔表达式，通常会引用到模式中的变量。
     * 如果存在模式守卫，这个匹配仅在模式守卫求值得到 true 时才会成功。因此，上面提到的首个 case 只能匹配那些两个操作元相等的二元操作。
     * 以下是其他一些带有守卫的模式示例：
     * // 只匹配正整数
     * case n: Int if 0 < n => ...
     * // 只匹配以字母 'a' 打头的字符串
     * case s: String if s(0) == 'a' => ...
     *
     * @param e
     * @return
     */
    def simplifyAdd(e: Expr) = e match {
      case BinOp("+", x, y) if x == y => BinOp("*", x, Number(2))
      case _ => e
    }

    println(simplifyAdd(BinOp("+", Number(1), Number(1))))
  }

  /**
   * 15.4 模式重叠
   */
  def test15_4(): Unit = {
    /**
     * 模式会按照代码中的顺序逐个被尝试。
     *
     * 在本例中，捕获所有的 case 出现在更具体的简化规则之后，这是很重要的。
     * 如果我们将顺序颠倒过来，那么捕获所有的 case 就会优先于更具体的规则执行。在许多场景下，编译器甚至会拒绝编译。
     *
     * @param expr
     * @return
     */
    def simplifyAll(expr: Expr): Expr = expr match {
      case UnOp("-", UnOp("-", e)) => simplifyAll(e)
      case BinOp("+", e, Number(0)) => simplifyAll(e)
      case BinOp("*", e, Number(1)) => simplifyAll(e)
      case UnOp(op, e) => UnOp(op, simplifyAll(e))
      case BinOp(op, l, r) => BinOp(op, simplifyAll(l), simplifyAll(r))
      case _ => expr
    }

    println(simplifyAll(BinOp("*", UnOp("-", UnOp("-", Number(1))), BinOp("+", Number(1), Number(0)))))
  }

  /**
   * 15.5 密封类
   * 现在我们可以试着定义一个漏掉了某些可能 case 的模式匹配
   * 将得到类似下面这样的编译器警告: match may not be exhaustive.
   * 这样的警告告诉我们这段代码存在产生 MatchError 异常的风险，因为某些可能出现的模式（UnOp、BinOp）并没有被处理。
   * 这个警告指出了潜在的运行时错误源，因此这通常有助于我们编写正确的程序。
   *
   * 我们会在第27章介绍注解。一般来说，可以像添加类型声明那样对表达式添加注解：在表达式后加一个冒号和注解的名称（以 @ 打头）。
   * 例如，在本例中我们给变量 e 添加了 @unchecked 注解，即“e: @unchecked”。@unchecked 注解对模式匹配而言有特殊的含义。
   * 如果 match 表达式的选择器带上了这个注解，那么编译器对后续模式分支的覆盖完整性检查就会被压制。
   *
   * @param e
   * @return
   */
  def describe(e: Expr): String = (e: @unchecked) match {
    case Number(_) => "a number"
    case Var(_) => "a variable"
    // 不过，有时候你也会遇到编译器过于挑剔的情况。
    // 举例来说，你可能从上下文知道永远只会将 describe 应用到 Number 或 Var，因此很清楚不会有 MatchError 发生。
    // 这时你可以对 describe 添加一个捕获所有的 case，这样就不会有编译器告警了
    // case _ => throw new RuntimeException // 不应该发生
    // 这样可行，但并不理想。你可能并不会很乐意，因为被迫添加了 永远不会被执行的代码（也可能是你认为不会），而所有这些只是为了让编译器闭嘴。
    // 一个更轻量的做法是给 match 表达式的选择器部分添加一个 @unchecked 注解。
  }

  /**
   * 15.6 Option 类型
   * Scala 由一个名为 Option 的标准类型来表示可选值。
   * 这样的值可以有两种形式：Some(x)，其中 x 是那个实际的值；或者 None 对象，代表没有值。
   */
  def test15_6(): Unit = {
    // Scala 集合类的某些标准操作会返回可选值。
    // 比如，Scala 的 Map 有一个 get 方法，当传入的键有对应的值时，返回 Some(value)；而当传入的键在 Map 中没有定义时，返回 None。
    val capitals = Map("France" -> "Paris", "Japan" -> "Tokyo")
    println(capitals get "France")
    println(capitals get "NorthPole")

    /**
     * 将可选值解开最常见的方式是通过模式匹配。
     *
     * @param x
     * @return
     */
    def show(x: Option[String]) = x match {
      case Some(s) => s
      case None => "?"
    }

    println(show(capitals get "Japan"))
    println(show(capitals get "France"))
    println(show(capitals get "North Pole"))
    // 如果某个变量允许为 null，那么必须记住在每次用到它的时候都要判空（null）。如果忘记了，那么运行时就有可能出现 NullPointerException。
    // 由于这样的类异常可能并不经常发生，在测试过程中也就很难发现。
    // 对 Scala 而言，这种方式完全不能工作，因为 Scala 允许在哈希映射中存放值类型的数据，而 null 并不是值类型的合法元素。
    // 例如，一个 HashMap[Int, Int] 不可能用返回 null 来表示“无值”。

    // Scala 鼓励我们使用 Option 来表示可选值。这种处理可选值的方式跟 Java 相比有若干优势。
    // 首先，对于代码的读者而言，某个类型为 Option[String] 的变量对应一个可选的 String，
    // 跟某个类型为 String 的变量是一个可选的String（可能为null）相比，要直观得多。
    // 不过最重要的是，我们之前描述的那种在不检查某个变量是否为 null 就开始用它的编程错误在 Scala 中直接变成了类型错误。
    // 如果某个变量的类型为 Option[String]，而我们把它当作 String 来用，这样的 Scala 程序是无法编译通过的。
  }

  /**
   * 15.7 到处都是模式
   */
  def test15_7(): Unit = {
    // 每当我们定义一个 val 或 var，都可以用模式而不是简单的标识符。例如，可以将一个元组解开并将其中的每个元素分别赋值给不同的变量
    val myTuple = (123, "abc")
    val (number, string) = myTuple
    println(number)
    println(string)
    val exp = BinOp("*", Number(5), Number(1))
    val BinOp(op, left, right) = exp
    println(op)
    println(left)
    println(right)
    // 用花括号包起来的一系列 case（即可选分支）可以用在任何允许出现函数字面量的地方。
    // 从本质上讲，case 序列就是一个函数字面量，只是更加通用。
    // 不像普通函数那样只有一个入口和参数列表，case 序列可以有多个入口，每个入口都有自己的参数列表。
    // 每个 case 对应该函数的一个入口，而该入口的参数列表用模式来指定。每个入口的逻辑主体是 case 右边的部分。
    val withDefault: Option[Int] => Int = {
      case Some(x) => x
      case None => 0
    }

    println(withDefault(Some(10)))
    println(withDefault(None))

    // 还有另一点值得我们注意：通过 case 序列得到的是一个偏函数（partial function）。
    // 如果我们将这样一个函数应用到它不支持的值上，它会产生一个运行时异常。例如，这里有一个返回整数列表中第二个元素的偏函数：
    val second: List[Int] => Int = {
      case x :: y :: _ => y
    }
    // 如果传入一个三元素列表，这个函数会成功执行，不过传入空列表就没那么幸运了
    println(second(List(5, 6, 7)))
    try {
      println(second(List()))
    } catch {
      case ex: MatchError => println(ex.getMessage() + "\n" + ex.getStackTrace.mkString("StackTrace(", "\n", ")"))
    }

    // 如果想检查某个偏函数是否对某个入参有定义，必须首先告诉编译器你知道你要处理的是偏函数。
    // List[Int] => Int 这个类型涵盖了所有从整数列表到整数的函数，不论这个函数是偏函数还是全函数。
    // 仅涵盖从整数列表到整数的偏函数的类型写作 PartialFunction[List[Int], Int]。
    // 我们重新写一遍 second 函数，这次用偏函数的类型声明：
    val second2: PartialFunction[List[Int], Int] = {
      case x :: y :: _ => y
    }

    // 偏函数定义了一个 isDefinedAt 方法，可以用来检查该函数是否对某个特定的值有定义。
    // 在本例中，这个函数对于任何至少有两个元素的列表都有定义
    println(second2.isDefinedAt(List(5, 6, 7)))
    println(second2.isDefinedAt(List()))

    // 偏函数的典型用例是模式匹配函数字面量，就像前面这个例子。事实上，这样的表达式会被 Scala 编译器翻译成偏函数，这样的翻译发生了两次：
    // 一次是实现真正的函数，另一次是测试这个函数是否对指定值有定义。
    // 举例来说，函数字面量 { case x :: y :: _ => y } 将被翻译成如下的偏函数值：
    val second3 = new PartialFunction[List[Int], Int] {
      def apply(xs: List[Int]): Int = xs match {
        case x :: y :: _ => y
      }

      def isDefinedAt(xs: List[Int]): Boolean = xs match {
        case x :: y :: _ => true
        case _ => false
      }
    }
    // 只要函数字面量声明的类型是 PartialFunction，这样的翻译就会生效。
    // 如果声明的类型只是 Function1，或没有声明，那么函数字面量对应的就是一个全函数（complete function）。
    // 一般来说，我们应该尽量用全函数，因为偏函数允许运行时出现错误，而编译器帮不了我们。不过有时候偏函数也特别有用。
    // 你也许确信不会有不能处理的值传入，也可能会用到那种预期偏函数的框架，在调用函数之前，总是会先用 isDefinedAt 做一次检查。

    // 我们还可以在 for 表达式中使用模式，如示例 15.18 所示。
    // 这里的 for 表达式从 capitals 映射中接收键/值对，每个键/值对都跟模式 (country, city) 匹配，这个模式定义了两个变量，country 和 city。
    val capitals = Map("France" -> "Paris", "Japan" -> "Tokyo")
    for ((country, city) <- capitals)
      println("The capital of " + country + " is " + city)

    // 不过某个模式不能匹配某个生成的值的情况也同样存在。示例 15.19 的代码就是这样一个例子。
    // 我们从这个例子当中可以看到，生成的值当中那些不能匹配给定模式的值会被直接丢弃。
    // 例如，results 列表中的第二个元素 None 就不能匹配上模式 Some(fruit)，因此它也就不会出现在输出当中了。
    val results = List(Some("apple"), None, Some("orange"))
    for (Some(fruit) <- results) println(fruit)
  }

  def main(args: Array[String]): Unit = {
    test15_1()
    println("------------")
    test15_2()
    println("------------")
    test15_3()
    println("------------")
    test15_4()
    println("------------")
    test15_6()
    println("------------")
    test15_7()
  }
}
