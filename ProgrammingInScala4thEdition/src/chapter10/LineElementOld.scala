package chapter10

/**
 * @Author: zhuxi
 * @Time: 2022/6/10 11:28
 * @Description: 10.7 调用超类构造方法 ~ 10.8 使用 override 修饰符
 * @ModifiedBy: zhuxi
 */
class LineElementOld(s: String) extends ArrayElement(Array(s)) {
  // 由于 LineElement 的 height 和 width 的确是重写了 Element 类中的具体定义，override 这个修饰符是必需的。

  // Scala 并不能完全解决脆弱基类的问题，但它比 Java 对此情况有所改善。
  // 如果代码是用Scala编写的，那么使用方代码中原先的实现并不会带上 override 修饰符，因为在当时并没有其他方法使用了这个名称。
  // 一旦你在第二版的基类添加了同名的方法，重新编译使用方代码将会给出类似如下的报错：method *** needs `override` modifier
  // 也就是说，使用你类库的代码并不会表现出错误的行为，而是得到一个编译期错误，这通常是更优的选择。
  override def width: Int = s.length

  override def height: Int = 1
}
