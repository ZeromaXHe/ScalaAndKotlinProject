package chapter10

/**
 * @Author: zhuxi
 * @Time: 2022/6/10 11:18
 * @Description: 10.5 重写方法和字段
 * @ModifiedBy: zhuxi
 */
class ArrayElementVal(conts:Array[String]) extends Element {
  // 统一访问原则只是 Scala 比 Java 在处理字段和方法上更加统一的一个方面。
  // 另一个区别是 Scala 中字段和方法属于同一个命名空间。这使得用字段重写无参方法变为可能。
  // Scala 也禁止在同一个类中使用相同的名称命名字段和方法。
  // 一般来说，Scala 只有两个命名空间用于定义，不同于 Java 的四个。Java 的四个命名空间分别是：字段、方法、类型和包，
  // 而Scala的两个命名空间分别是：值（字段、方法、包和单例对象）、类型（类和特质名）
  // Scala 将字段和方法放在同一个命名空间的原因正是为了让你可以用 val 来重写一个无参方法，这在 Java 中是不允许的。
  // Scala 中包也跟字段和方法共用一个命名空间的原因是让你能引入包（而不仅仅是类型的名称）及单例对象的字段和方法。这同样是 Java 不允许的。
  override val contents: Array[String] = conts
}
