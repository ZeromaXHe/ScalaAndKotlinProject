package com.zerox.chapter4

import com.zerox.chapter4.ChecksumAccumulator.calculate

/**
 * @Author: zhuxi
 * @Time: 2022/6/9 10:58
 * @Description: “4.4 Scala应用程序”中的代码示例
 * @ModifiedBy: zhuxi
 */
object Summer {
  def main(args: Array[String]) = {
    for(arg <- args)
      println(arg + ": " + calculate(arg))
  }
}
