package com.zerox.basic

import java.io.File
import java.math.BigDecimal
import java.nio.file.Files
import java.nio.file.Paths

// 【创建 DTO】
// 会为 Customer 类提供以下功能：
// - 所有属性的 getter （对于 var 定义的还有 setter）
// - equals()
// - hashCode()
// - toString()
// - copy()
// - 所有属性的 component1() 、 component2() ……等等（参⻅数据类）
data class Customer(val name: String, val email: String)

// 函数的默认参数
fun foo(a: Int = 0, b: String = "") {}

// 过滤 list
val list = listOf(1, 2, 3, -1, -2, -3)
val positives = list.filter { x -> x > 0 }
val positives2 = list.filter { it > 0 }

// 检测元素是否存在于集合中
fun checkElement() {
    val emailsList = listOf("zerox@163.com")
    if ("john@example.com" in emailsList) {
        // ...
    }
    if ("jane@example.com" !in emailsList) {
        // ...
    }
}

// 只读 map
val map = mapOf("a" to 1, "b" to 2, "c" to 3)

// 访问 map 条目
fun visitMap() {
    val mMap = mutableMapOf("key" to 1)
    println(mMap["key"])
    mMap["key"] = 4
}

// 遍历 map 或者 pair 型 list
fun traverseMapOrListPair() {
    for ((k, v) in map) {
        println("$k -> $v")
    }
}

// 区间迭代
fun rangeIteration() {
    for (i in 1..100) {
    } // 闭区间：包含 100
    for (i in 1 until 100) {
    } // 半开区间：不包含 100
    for (x in 2..10 step 2) {
    }
    for (x in 10 downTo 1) {
    }
    (1..10).forEach { }
}

// 延迟属性
val p: String by lazy { // the value is computed only on first access
    // 计算该字符串
    ""
}

// 扩展函数
fun extendsFunction() {
    fun String.spaceToCamelCase() {}
    "Convert this to camelcase".spaceToCamelCase()
}

// 创建单例
object Resource {
    val name = "Name"
}

// 实例化一个抽象类
abstract class MyAbstractClass {
    abstract fun doSomething()
    abstract fun sleep()
}

// 实例化一个抽象类
fun instantiateAbstractClass() {
    val myObject = object : MyAbstractClass() {
        override fun doSomething() {
            // ...
        }

        override fun sleep() {
            // ...
        }
    }
    myObject.doSomething()
}

// if-not-null 缩写
fun ifNotNull() {
    val files = File("Test").listFiles()
    println(files?.size) // 如果 files 不是 null，那么输出其⼤⼩（size）
}

// if-not-null-else 缩写
fun ifNotNullElse() {
    val files = File("Test").listFiles()
    println(files?.size ?: "empty") // 如果 files 为 null，那么输出“empty”
    // 要在代码块里计算 fallback 值的话，使用 `run`
    val filesSize = files?.size ?: run {
        "42"
    }
    println(filesSize)
}

// if null 执行一个语句
fun ifNullExecute() {
    val values = mapOf("e" to 1)
    val email = values["email"] ?: throw IllegalStateException("Email is missing!")
}

// 在可能会空的集合中取第⼀元素
val emails = listOf<String>()
val mainEmail = emails.firstOrNull() ?: ""

// if not null 执⾏代码
fun ifNotNullExecute() {
    val value: String? = "test"
    value?.let {
        // 代码会执⾏到此处, 假如 data 不为 null
        println("test")
    }
    // 映射可空值（如果⾮空的话）
    val mapped = value?.let { transform(it) } ?: 12
}

// 返回 when 表达式
fun transform(color: String): Int? {
    return when (color) {
        "Red" -> 0
        "Green" -> 1
        "Blue" -> null
        else -> throw IllegalArgumentException("Invalid color param value")
    }
}

// 返回类型为 Unit 的⽅法的构建器⻛格⽤法
fun arrayOfMinusOnes(size: Int): IntArray {
    return IntArray(size).apply { fill(-1) }
}

// 对⼀个对象实例调⽤多个⽅法 （with）
fun callMultipleFunctionsOfObjectByWith() {
    class Turtle {
        fun penDown() {}
        fun penUp() {}
        fun turn(degrees: Double) {}
        fun forward(pixels: Double) {}
    }

    val myTurtle = Turtle()

    with(myTurtle) { // 画⼀个 100 像素的正⽅形
        penDown()
        for (i in 1..4) {
            forward(100.0)
            turn(90.0)
        }
        penUp()
    }
}

// 配置对象的属性（apply）
fun setObjectProperties() {
    class Rectangle {
        var length: Int = 0
        var breadth: Int = 0
        var color: Int = 0
    }

    val myRectangle = Rectangle().apply {
        length = 4
        breadth = 5
        color = 0xFAFAFA
    }
}

// Java 7 的 try-with-resources
fun tryWithResources() {
    val stream = Files.newInputStream(Paths.get("/some/file.txt"))
    stream.buffered().reader().use { reader ->
        println(reader.readText())
    }
}

// 对于需要泛型信息的泛型函数的适宜形式
// inline fun <reified T: Any> Gson.fromJson(json: JsonElement): T = this.fromJson(json, T::class.java)

// 交换两个变量
fun swap() {
    var a = 1
    var b = 2
    a = b.also { b = a }
}

// 将代码标记为不完整(TODO)
fun calcTaxes(): BigDecimal = TODO("Waiting for feedback from accounting")