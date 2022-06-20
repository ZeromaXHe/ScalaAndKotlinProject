package com.zerox.chapter28

/**
 * @author zhuxi
 * @since 2022/6/20 11:48
 * @note
 * 28.4 序列化
 */
abstract class CCTherm {
  val description: String
  val yearMade: Int
  val dateObtained: String
  val bookPrice: Int // 美分
  val purchasePrice: Int // 美分
  val condition: Int // 1 到 10

  override def toString = description

  // 要将这个类的实例转换成 XML，只需要简单地添加一个 toXML 方法，用 XML 字面量和花括号转义来实现即可
  def toXML =
    <cctherm>
      <description>{description}</description>
      <yearMade>{yearMade}</yearMade>
      <dateObtained>{dateObtained}</dateObtained>
      <bookPrice>{bookPrice}</bookPrice>
      <purchasePrice>{purchasePrice}</purchasePrice>
      <condition>{condition}</condition>
    </cctherm>
}
