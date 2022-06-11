package com.zerox.chapter10

/**
 * @Author: zhuxi
 * @Time: 2022/6/10 11:20
 * @Description: 10.6 定义参数化字段
 *               注意，现在 contents 参数前面放了一个 val。这是同时定义参数和同名字段的简写方式。
 *               具体来说，ArrayElement 类现在具备一个（不能被重新赋值的）contents 字段。
 * @ModifiedBy: zhuxi
 */
class ArrayElementWithParametricField(val contents: Array[String]) extends Element

// 你也可以在类参数的前面加上 var，这样的话对应的字段就可以被重新赋值。
// 最后，你还可以给这些参数化字段添加修饰符，比如 private、protected 或者 override，就像你能够对其他类成员做的那样。
class Cat {
  val dangerous = false
}

class Tiger(override val dangerous: Boolean,
            private var age: Int) extends Cat
