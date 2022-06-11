package com.zerox
/**
 * @Author: zhuxi
 * @Time: 2022/6/10 18:09
 * @Description: 13.6 包对象
 *               如果你有某个希望在整个包都能用的助手方法，大可将它放在包的顶层。
 *               具体的做法是把定义放在包对象（package object）当中。
 *               每个包都允许有一个包对象，任何被放在包对象里的定义都会被当作这个包本身的成员。
 *               从语法上讲，包对象跟本章前面展示的花括号“打包”很像。唯一的区别是包对象包含了一个object关键字。
 *               这是一个包对象，而不是一个包。花括号括起来的部分可以包含任何你想添加的定义。
 *
 *               继续往前看，包对象还有不少等着你去发现的用途。包对象经常用于包级别的类型别名（第20章）和隐式转换（第21章）。
 *               顶层的 scala 包也有一个包对象，其中的定义对所有 Scala 代码都可用。
 *
 *               包对象会被编译成名为 package.class 的类文件，该文件位于它增强的包的对应目录下。源文件最好能保持相同的习惯
 *               (个人注：使用 IDEA new -> Scala class 的话，没办法命名为 package.scala，必须 new -> File。
 *               而且需要注意创建之后第一行 package com.zerox 不要加 chapter13, 否则也报错…… 不过文件本身不报，比较神奇。
 *               如果加了 package com.zerox.chapter13 的话，这样编译后的类文件在包目录下创建了一个和类名相同的目录，下面才是 package.class。
 *               所以在包外使用 import com.zerox.chapter13.showFruit 是无效的，找不到。Chapter13Test 里面相当于是普通的包内调用，所以正常)
 * @ModifiedBy: zhuxi
 */
package object chapter13 {
  def showFruit(fruit: Fruit) = {
    import fruit._
    println(name + "s are " + color)
  }
}
