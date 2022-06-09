package chapter4

import scala.collection.mutable

/**
 * @Author: zhuxi
 * @Time: 2022/6/9 10:44
 * @Description: “4.1 类、字段和方法”中的代码示例
 * @ModifiedBy: zhuxi
 */
class ChecksumAccumulator {
  private var sum = 0

  /// 可以省略结果类型，Scala 会帮你推断出来
//  def add(b: Byte) = sum += b
//  def checkSum() = ~(sum & 0xFF) + 1

  // 但通常更好的做法是对类中声明为公有的方法显式地给出结果类型，哪怕编译器可以帮你推断出来
  // 对于结果类型为 Unit 的方法，如 ChecksumAccumulator 的 add 方法，执行的目的是为了得到其副作用。
  // 副作用通常来说指的是改变方法外部的某种状态或者执行 I/O 的动作。那些仅仅因为副作用而被执行的方法被称作过程（procedure）。
  def add(b: Byte): Unit = {
    sum += b
  }

  def checkSum(): Int = ~(sum & 0xFF) + 1
}

/**
 * 4.3 单例对象
 * Scala 比 Java 更面向对象的一点，是 Scala 的类不允许有静态（static）成员。
 * 对此类使用场景，Scala 提供了单例对象（singleton object）。
 *
 * 下面的单例对象名叫 ChecksumAccumulator，和上面例子中的类名一样。
 * 当单例对象跟某个类共用同一个名字时，它被称作这个类的伴生对象（companion object）。必须在同一个源码文件中定义类和类的伴生对象。
 * 同时，类又叫作这个单例对象的伴生类（companion class）。类和它的伴生对象可以互相访问对方的私有成员。
 */
object ChecksumAccumulator {
  private val cache = mutable.Map.empty[String, Int]

  def calculate(s: String): Int =
    if (cache.contains(s))
      cache(s)
    else {
      val acc = new ChecksumAccumulator
      for (c <- s)
        acc.add(c.toByte)
      val cs = acc.checkSum()
      cache += (s -> cs)
      cs
    }
}
