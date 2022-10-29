package com.zerox.scala.from1601to1800

/**
 * @author zhuxi
 * @since 2022/10/29 9:48
 * @note
 * 1773. 统计匹配检索规则的物品数量 | 难度：简单
 */
object Solution1773 {
  /**
   * 时间 564 ms 击败 100%
   * 内存 60.7 MB 击败 100%
   *
   * @param items
   * @param ruleKey
   * @param ruleValue
   * @return
   */
  def countMatches(items: List[List[String]], ruleKey: String, ruleValue: String): Int = {
    val idx = ruleKey match {
      case "type" => 0
      case "color" => 1
      case "name" => 2
    }
    items.count(_ (idx) == ruleValue)
  }
}
