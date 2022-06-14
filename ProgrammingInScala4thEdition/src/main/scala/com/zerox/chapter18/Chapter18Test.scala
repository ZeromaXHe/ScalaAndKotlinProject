package com.zerox.chapter18

/**
 * @author zhuxi
 * @since 2022/6/14 16:38
 * @note
 * 第 18 章 可变对象
 */
object Chapter18Test {

  /**
   * 18.1 什么样的对象是可变的
   */
  def test18_1(): Unit = {
    class BankAccount {
      private var bal: Int = 0

      def balance: Int = bal

      def deposit(amount: Int) = {
        require(amount > 0)
        bal += amount
      }

      def withdraw(amount: Int): Boolean =
        if (amount > bal) false
        else {
          bal -= amount
          true
        }
    }

    val account = new BankAccount
    account deposit 100
    println(account withdraw 80)
    println(account withdraw 80)

    class Keyed {
      def computeKey: Int = {
        // 这需要些时间
        Thread.sleep(1000)
        1
      }
    }

    /**
     * 假设 computeKey 既不读也不写任何 var，可以通过添加缓存来让 Keyed 变得更高效
     * 使用 MemoKeyed 而不是 Keyed 可以提速，因为 computeKey 操作第二次被请求时，可以直接返回保存在 keyCache 字段中的值，
     * 而不是再次运行 computeKey。不过除了速度上的提升，Keyed类和MemoKeyed类的行为完全一致。
     * 因此，如果说 Keyed 是纯函数式的，那么 MemoKeyed 同样也是，尽管它有一个可被重新赋值的变量。
     */
    class MemoKeyed extends Keyed {
      private var keyCache: Option[Int] = None

      override def computeKey: Int = {
        if (keyCache.isEmpty) keyCache = Some(super.computeKey)
        keyCache.get
      }
    }
  }

  /**
   * 18.2 可被重新赋值的变量和属性
   */
  def test18_2(): Unit = {
    /**
     * 在 Scala 中，每一个非私有的 var 成员都隐式地定义了对应的 getter 和 setter 方法。
     * 不过，这些 getter 方法和 setter 方法的命名跟 Java 的习惯不一样。
     * var x 的 getter 方法只是命名为 “x”，而它的 setter 方法命名为 “x_=”。
     * 如果原先的 var 定义是公有的，那么它的 getter 和 setter 也是公有的；
     * 如果原先的 var 定义是 protected，那么它的 getter 和 setter 也是 protected；以此类推。
     */
    class Time {
      var hour = 12
      var minute = 0
    }

    /**
     * 和 Time 完全等效。局部字段 h 和 m 的名称是随意选的，只要不跟已经用到的名称冲突即可。
     */
    class Time2 {
      private[this] var h = 12
      private[this] var m = 0

      def hour: Int = h

      def hour_=(x: Int): Unit = {
        h = x
      }

      def minute: Int = m

      def minute_=(x: Int): Unit = {
        m = x
      }
    }

    /**
     * 这个将 var 展开成 getter 和 setter 的机制有趣的一点在于我们仍然可以直接定义 getter 和 setter，而不是定义一个 var。
     * 通过直接定义这些访问方法，可以按照自己的意愿来解释变量访问和赋值的操作。
     */
    class Time3 {
      private[this] var h = 12
      private[this] var m = 0

      def hour: Int = h

      def hour_=(x: Int): Unit = {
        require(0 <= x && x < 24)
        h = x
      }

      def minute: Int = m

      def minute_=(x: Int): Unit = {
        require(0 <= x && x < 60)
        m = x
      }
    }

    // 某些语言对于这些类似变量的值有特殊的语法表示，它们不同于普通变量的地方在于 getter 和 setter 可以被重新定义。
    // 例如 C# 有属性来承担这个角色。
    // 从效果上讲，Scala 总是将变量解读为 setter 和 getter方法的这个做法，让我们在不需要特殊语法的情况下获得了跟 C# 属性一样的能力。

    /**
     * 这个类定义体的第一行定义了一个 var 变量 celsius，用来包含摄氏度的温度。
     * celsius 变量一开始被设成默认值，因为我们给出了 “_” 作为它的“初始值”。
     * 更确切地说，某个字段的 “= _” 初始化代码会给这个字段赋一个零值（zero value）。具体零值是什么取决于字段的类型。
     * 数值类型的零值是 0，布尔值的零值是 false，引用类型的零值是 null。这跟 Java 中没有初始化代码的变量效果一样。
     * 注意在 Scala 中并不能简单地去掉 “= _”。如果是这样写的：var celsius: Float
     * 这将会定义一个抽象变量，而不是一个没有被初始化的变量。
     */
    class Thermometer {
      var celsius: Float = _

      def fahrenheit: Float = celsius * 9 / 5 + 32

      def fahrenheit_=(f: Float): Unit = {
        celsius = (f - 32) * 5 / 9
      }

      override def toString: String = fahrenheit + "F/" + celsius + "C"
    }

    val t = new Thermometer
    t.celsius = 100
    println(t)
    t.fahrenheit = -40
    println(t)
  }

  /**
   * 18.6 电路模拟
   */
  def test18_6(): Unit = {
    import MySimulation._
    val input1, input2, sum, carry = new Wire
    probe("sum", sum)
    probe("carry", carry)
    halfAdder(input1, input2, sum, carry)
    input1 setSignal true
    run()
    input2 setSignal true
    run()
  }

  def main(args: Array[String]): Unit = {
    test18_1()
    println("---------------")
    test18_2()
    println("---------------")
    test18_6()
  }
}
