package chapter8

import scala.io.Source

/**
 * @Author: zhuxi
 * @Time: 2022/6/9 17:49
 * @Description:
 * @ModifiedBy: zhuxi
 */
object LongLines {
  def processFile(filename: String, width: Int) = {
    // 将私有方法 processLine 转换成了一个局部函数。
    // 为此我们移除了 private 修饰符（这个修饰符只能也只需要加在成员上），并将 processLine 的定义放在了 processFile 的定义中。
    // 注意这里不需要传递 filename 和 width 给助手函数，因为局部函数可以访问包含它们的函数的参数。
    // 既然现在 processLine 定义在 processFile 内部，可以直接使用外部的processFile函数的参数
    def processLine(line: String) = {
      if (line.length > width)
        println(filename + ": " + line.trim)
    }

    val source = Source.fromFile(filename)
    for (line <- source.getLines())
      processLine(line)
  }
}
