package com.zerox.basic

fun main() {
    standardOutput()
    println("--------------")
    functionTest()
    println("--------------")
    variableTest()
    println("--------------")
    classTest()
    println("--------------")
    stringTemplateTest()
    println("--------------")
    conditionalExpressionTest()
    println("--------------")
    forLoopTest()
    println("--------------")
    whileLoopTest()
    println("--------------")
    whenExpressionTest()
    println("--------------")
    rangeTest()
    println("--------------")
    collectionTest()
    println("--------------")
    nullTest()
    println("--------------")
    classCastTest()
}

fun standardOutput() {
    print("Hello ")
    print("world!")
}

fun sum(a: Int, b: Int) = a + b

fun printSum(a: Int, b: Int): Unit {
    println("sum of $a and $b is ${a + b}")
}

fun functionTest() {
    print("sum of 3 and 5 is")
    println(sum(3, 5))
    println("sum of 19 and 23 is ${sum(19, 23)}")
    printSum(-1, 8)
}

val PI = 3.14
var y = 0

fun incrementY() {
    y += 1
}

fun variableTest() {
    val a: Int = 1
    val b = 2
    // Scala 里面没办法这样先声明 val 再赋值
    val c: Int
    c = 3
    println("a = $a, b = $b, c = $c")

    var x = 5
    x += 1
    println("x = $x")

    println("y = $y; PI = $PI")
    incrementY()
    println("incrementY()")
    println("y = $y; PI = $PI")
}

// 类之间继承由冒号（:）声明。默认情况下类都是 final 的；如需让⼀个类可继承，请将其标记为 open
open class Shape

class Rectangle(var height: Double, var length: Double) : Shape() {
    var perimeter = (height + length) * 2
}

fun classTest() {
    val rectangle = Rectangle(5.0, 2.0)
    println("The perimeter is ${rectangle.perimeter}")
}

fun stringTemplateTest() {
    var a = 1
    val s1 = "a is $a"
    a = 2
    val s2 = "${s1.replace("is", "was")}, but now is $a"
    println(s2)
}

fun maxOf(a: Int, b: Int) = if (a > b) a else b

fun conditionalExpressionTest() {
    println("max of 0 and 42 is ${maxOf(0, 42)}")
}

fun forLoopTest() {
    val items = listOf("apple", "banana", "kiwifruit")
    for (item in items) {
        println(item)
    }
    for (index in items.indices) {
        println("item at $index is ${items[index]}")
    }
}

fun whileLoopTest() {
    val items = listOf("apple", "banana", "kiwifruit")
    var index = 0
    while (index < items.size) {
        println("item at $index is ${items[index]}")
        index++
    }
}

fun describe(obj: Any): String =
    when (obj) {
        1 -> "One"
        "Hello" -> "Greeting"
        is Long -> "Long"
        !is String -> "Not a String"
        else -> "Unknown"
    }

fun whenExpressionTest() {
    println(describe(1))
    println(describe("Hello"))
    println(describe(100L))
    println(describe(2))
    println(describe("other"))
}

fun rangeTest() {
    // 使⽤ in 操作符来检测某个数字是否在指定区间内
    val x = 10
    val y = 9
    if (x in 1..y + 1) {
        println("fits in range")
    }

    // 检测某个数字是否在指定区间外
    val list = listOf("a", "b", "c")
    if (-1 !in 0..list.lastIndex) {
        println("-1 is out of range")
    }
    if (list.size !in list.indices) {
        println("list size is out of valid list indices range, too")
    }

    // 区间迭代
    for (x in 1..5) {
        print(x)
    }

    // 数列迭代
    for (x in 1..10 step 2) {
        print(x)
    }
    println()
    for (x in 9 downTo 0 step 3) {
        print(x)
    }
}

fun collectionTest() {
    val items = listOf("apple", "banana", "kiwifruit")
    for (item in items) {
        println(item)
    }
    when {
        "orange" in items -> println("juicy")
        "apple" in items -> println("apple is fine too")
    }
    val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
    fruits
        .filter { it.startsWith("a") }
        .sortedBy { it }
        .map { it.uppercase() }
        .forEach { println(it) }
}

fun parseInt(str: String): Int? {
    return str.toIntOrNull()
}

fun printProduct(arg1: String, arg2: String) {
    val x = parseInt(arg1)
    val y = parseInt(arg2)
    // 直接使⽤ `x * y` 会导致编译错误，因为它们可能为 null
    if (x != null && y != null) {
        // 在空检测后，x 与 y 会⾃动转换为⾮空值（non-nullable）
        println(x * y)
    } else {
        println("'$arg1' or '$arg2' is not a number")
    }
}

fun nullTest() {
    printProduct("6", "7")
    printProduct("a", "7")
    printProduct("a", "b")
}

fun getStringLength(obj: Any): Int? {
    if (obj is String) {
        // `obj` 在该条件分⽀内⾃动转换成 `String`
        return obj.length
    }
    // 在离开类型检测分⽀后，`obj` 仍然是 `Any` 类型
    return null
}

fun classCastTest() {
    fun printLength(obj: Any) {
        println("Getting the length of '$obj'. Result: ${getStringLength(obj) ?: "E"}")
    }
    printLength("Incomprehensibilities")
    printLength(1000)
    printLength(listOf(Any()))
}