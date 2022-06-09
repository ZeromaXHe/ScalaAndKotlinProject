package chapter4

import chapter4.ChecksumAccumulator.calculate

/**
 * @Author: zhuxi
 * @Time: 2022/6/9 10:59
 * @Description: “4.5 App 特质”中的代码示例
 * @ModifiedBy: zhuxi
 */
object FallWinterSpringSummer extends App {
  // 不需要直接编写 main 方法，而是将你打算放在 main 方法里的代码直接写在 extends App 的单例对象的花括号中
  for (season <- List("fall", "winter", "spring"))
    println(season + ": " + calculate(season))
}
