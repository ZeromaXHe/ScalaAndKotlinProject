package chapter8

/**
 * @Author: zhuxi
 * @Time: 2022/6/9 18:03
 * @Description:
 * @ModifiedBy: zhuxi
 */
object Chapter8Test {
  val someNumbers = List(-11, -10, -5, 0, 5, 10)

  /**
   * 8.3 一等函数
   */
  def test8_3(): Unit = {
    // Scala 支持一等函数。不仅可以定义函数并调用它们，还可以用匿名的字面量来编写函数并将它们作为值（value）进行传递。
    var increase = (x: Int) => x + 1
    println(increase(10))
    // 由于本例中的 increase 是 var，可以稍后将它赋值成其他函数值
    increase = (x: Int) => x + 9999
    println(increase(10))
    // 如果你想要在函数字面量中包含多于 1 条语句，可以将函数体用花括号括起来，每条语句占一行，组成一个代码块（block）。
    // 跟方法一样，当函数值被调用时，所有的语句都会被执行，并且该函数的返回值就是对最后一个表达式求值的结果。
    increase = (x: Int) => {
      println("We")
      println("are")
      println("here!")
      x + 1
    }
    println(increase(10))

    someNumbers.foreach((x: Int) => println(x))
    println(someNumbers.filter((x: Int) => x > 0))
  }

  /**
   * 8.4 函数字面量的简写形式
   */
  def test8_4(): Unit = {
    // 一种让代码变得更简要的方式是略去参数类型声明。
    // 另一个去除源码中无用字符的方式是省去某个靠类型推断（而不是显式给出）的参数两侧的圆括号。
    println(someNumbers.filter(x => x > 0))
  }

  /**
   * 8.5 占位符语法
   */
  def test8_5(): Unit = {
    // 为了让函数字面量更加精简，还可以使用下画线作为占位符，用来表示一个或多个参数，只要满足每个参数只在函数字面量中出现一次即可。
    println(someNumbers.filter(_ > 0))

    // 有时候当你用下画线为参数占位时，编译器可能并没有足够多的信息来推断缺失的参数类型。在这类情况下，可以用冒号来给出类型
    val f = (_: Int) + (_: Int)
    // 注意，_ + _将会展开成一个接收两个参数的函数字面量。
    // 这就是为什么只有当每个参数在函数字面量中出现不多不少正好一次的时候才能使用这样的精简写法。
    // 多个下画线意味着多个参数，而不是对单个参数的重复使用。
    // 第一个下画线代表第一个参数，第二个下画线代 表第二个参数，第三个下画线代表第三个参数，以此类推。
    println(f(3, 4))
  }

  /**
   * 8.6 部分应用的函数
   */
  def test8_6(): Unit = {
    // 虽然前面的例子用下画线替换掉单独的参数，也可以用下画线替换整个参数列表。例如，对于 println(_)，也可以写成 println _。
    // 如果你要的部分应用函数表达式并不给出任何参数，比如 println _ 或 sum _，可以在需要这样一个函数的地方更加精简地表示，连下画线也不用写。
    someNumbers.foreach(println)

    def sum(a: Int, b: Int, c: Int) = a + b + c

    // 当你这样使用下画线时，实际上是在编写一个部分应用的函数 （partially applied function）。
    // 在 Scala 中，当你调用某个函数，传入任何需要的参数时，实际上是应用那个函数到这些参数上
    val a = sum _
    // 省略掉 _ 的这种形式只在明确需要函数的地方被允许，比如上面例中的 foreach 调用 println。
    // 编译器知道这里需要的是一个函数，因为 foreach 要求一个函数作为入参。
    // 在那些并不需要函数的场合（比如这里的 var a），尝试使用这样的形式会引发编译错误。
    println(a)
    println(a(1, 2, 3))

    // 至此，我们已经知道 sum _ 是一个不折不扣的部分应用函数，可能你仍然感到困惑，为什么我们会这样称呼它。
    // 部分应用函数之所以叫作部分应用函数，是因为你并没有把那个函数应用到所有入参。拿 sum _ 来说，你没有应用任何入参。
    // 不过，完全可以通过给出一些必填的参数来表达一个部分应用的函数。
    // 在本例中，提供了第一个和最后一个参数给sum，但没有给出第二个参数。
    // 由于只有一个参数缺失，Scala 编译器将生成一个新的函数类，这个类的 apply 方法接收一个参数。
    // 当我们用那个参数来调用这个新的函数时，这个生成的函数的 apply 方法将调用 sum，依次传入 1、传给当前函数的入参和 3。
    val b = sum(1, _: Int, 3)
    println(b(2))
    println(b(5))
  }

  /**
   * 8.7 闭包
   */
  def test8_7(): Unit = {
    // 运行时从这个函数字面量创建出来的函数值（对象）被称作闭包（closure）。该名称源于“捕获”其自由变量从而“闭合”该函数字面量的动作。
    // 没有自由变量的函数字面量，比如 (x: Int) => x + 1，称为闭合语（closed term），这里的语（term）指的是一段源代码。
    // 因此，运行时从这个函数字面量创建出来的函数值严格来说并不是一个闭包，因为 (x: Int) => x + 1 按照目前这个写法已经是闭合的了。
    // 而运行时从任何带有自由变量的函数字面量，比如(x: Int) => x + more，创建的函数值，按照定义，要求捕获到它的自由变量 more 的绑定。
    // 相应的函数值结果（包含指向被捕获的 more 变量的引用）就被称作闭包，因为函数值是通过闭合这个开放语（open term）的动作产生的。
    var more = 1
    val addMore = (x: Int) => x + more
    println(addMore(10))

    // 如果 more 在闭包创建以后被改变会发生什么？在 Scala 中，答案是闭包能够看到这个改变。
    more = 9999
    println(addMore(10))

    // 很符合直觉的是，Scala的闭包捕获的是变量本身，而不是变量引用的值。
    // 正如前面示例所展示的，为 (x: Int) => x + more 创建的闭包能够看到闭包外对 more 的修改。
    // 反过来也是成立的：闭包对捕获到的变量的修改也能在闭包外被看到。
    var sum = 0
    someNumbers.foreach(sum += _)
    println(sum)

    // 那么如果一个闭包访问了某个随着程序运行会产生多个副本的变量会如何呢？
    // 例如，如果一个闭包使用了某个函数的局部变量，而这个函数又被调用了多次，会怎么样？闭包每次访问到的是这个变量的哪一个实例呢？
    // 只有一个答案是跟 Scala 其他组成部分是一致的：闭包引用的实例是在闭包被创建时活跃的那一个。
    def makeIncreaser(more: Int) = (x: Int) => x + more

    val inc1 = makeIncreaser(1)
    val inc9999 = makeIncreaser(9999)
    println(inc1(10))
    println(inc9999(10))
  }

  /**
   * 8.8 特殊的函数调用形式
   */
  def test8_8(): Unit = {
    // Scala 允许你标识出函数的最后一个参数可以被重复。
    // 这让我们可以对函数传入一个可变长度的参数列表。要表示这样一个重复参数，需要在参数的类型之后加上一个星号（*）。
    def echo(args: String*) = for (arg <- args) println(arg)

    echo()
    echo("one")
    echo("hello", "world!")

    // 在函数内部，这个重复参数的类型是一个所声明的参数类型的 Seqs。因此，在echo函数内部，args的类型其实是Seq[String]。
    // 尽管如此，如果你有一个合适类型的数组，并尝试将它作为重复参数传入时，将得到一个编译错误
    // 要完成这样的操作，你需要在数组实参的后面加上冒号和一个 _* 符号
    val arr = Array("What's", "up", "doc?")
    // 这种表示法告诉编译器将 arr 的每个元素作为参数传给 echo，而不是将所有元素放在一起作为单个实参传入。
    echo(arr: _*)

    def speed(distance: Float, time: Float) = distance / time

    println(speed(100, 10))
    // 带名字的参数让你可以用不同的顺序将参数传给函数。其语法是简单地在每个实参前加上参数名和等号。
    println(speed(distance = 100, time = 10))
    // 用带名字的参数发起调用，实参可以在不改变含义的前提下交换位置
    println(speed(time = 10, distance = 100))
    // 我们还可以混用按位置和带名字的参数。在这种情况下，按位置的参数需要放在前面。带名字的参数最常见的场合是跟默认参数值一起使用。

    println("函数缺省参数值测试：")

    // Scala允许你给函数参数指定默认值。这些有默认值的参数可以不出现在在函数调用中，对应的参数将会被填充为默认值。
    def printTime(out: java.io.PrintStream = Console.out, divisor: Int = 1) =
      out.println("time = " + System.currentTimeMillis() / divisor)

    // 不知道这个过程哪里打印了一个括号"()"？
    println(printTime(out = Console.err))
    println(printTime(divisor = 1000))
  }

  /**
   * 8.9 尾递归
   */
  def test8_9(): Unit = {
    // 在最后一步调用自己的函数，被称为尾递归（tail recursive）函数。
    // Scala 编译器能够检测到尾递归并将它替换成跳转到函数的最开始，并在跳转之前将参数更新为新的值。
    // 这背后的意思是我们不应该回避使用递归算法来解决问题。通常，递归算法比基于循环的算法更加优雅、精简。
    // 如果解决方案是尾递归的，那么我们并不需要支付任何（额外的）运行时开销。

    // 该函数并不是尾递归的，因为它在递归调用之后还执行了一个递增操作。
    def boom(x: Int): Int =
      if (x == 0) throw new Exception("boom")
      else boom(x - 1) + 1

    // 改成尾递归
    def bang(x: Int): Int =
      if (x == 0) throw new Exception("bang")
      else bang(x - 1)

    println("非尾递归 boom 堆栈：")
    try {
      boom(3)
    } catch {
      case exception: Exception => exception.getStackTrace.foreach(println)
    }
    println("尾递归 bang 堆栈：")
    try {
      bang(3)
    } catch {
      case exception: Exception => exception.getStackTrace.foreach(println)
    }

    // 在 Scala 中使用尾递归是比较受限的，因为用JVM指令集实现更高级形式的尾递归非常困难。Scala 只能对那些直接尾递归调用自己的函数做优化。
    // 如果递归调用是间接的，比如如下示例中的两个相互递归的函数，Scala 就没法优化它们
    def isEven(x: Int): Boolean = if (x == 0) true else isOdd(x - 1)

    def isOdd(x: Int): Boolean = if (x == 0) false else isEven(x - 1)

    // 同样地，如果最后一步调用的是一个函数值（而不是发起调用的那个函数自己），也无法享受到尾递归优化。
    // 这里好像不能用 val funValue = nestedFun _，会报错：forward reference extends over definition of value funValue
    def funValue = nestedFun _

    def nestedFun(x: Int): Unit = {
      if (x != 0) {
        println(x)
        funValue(x - 1)
      }
    }
  }

  def main(args: Array[String]): Unit = {
    test8_3()
    println("-----------")
    test8_4()
    println("-----------")
    test8_5()
    println("-----------")
    test8_6()
    println("-----------")
    test8_7()
    println("-----------")
    test8_8()
    println("-----------")
    test8_9()
  }
}
