package com.zerox.chapter28

/**
 * @author zhuxi
 * @since 2022/6/20 11:38
 * @note
 * 第 28 章 使用 XML
 */
object Chapter28Test {

  /**
   * 28.3 XML 字面量
   */
  def test28_3(): Unit = {
    val xml1 =
      <a>
        This is some XML.
        Here is a tag:<atag/>
      </a>
    println(xml1)
    println(xml1.getClass)
    // 你并不是只能逐字地写出确切的 XML，可以在 XML 字面量当中用花括号（{}）转义，对 Scala 代码求值。
    println(<a>{"hello" + ", world"}</a>)
    // 注意在例子中“什么都不加”用 XML 节点的方式表示就是 xml.NodeSeq.Empty。
    val yearMade = 1955
    println(<a>{if (yearMade < 2000) <old>{yearMade}</old> else xml.NodeSeq.Empty}</a>)
    // 花括号中的表达式并不一定要求值得到 XML 节点，它可以被求值成任何 Scala 的值。
    println(<a>{3 + 4}</a>)
    // 文本中任何 <、> 和 & 符号都会被转义，如果将它们打印出来:
    println(<a>{"</a>potential security hole<a>"}</a>)
  }

  /**
   * 28.4 序列化
   */
  def test28_4(): Unit = {
    val therm = new CCTherm {
      override val description: String = "hot dog #5"
      override val yearMade: Int = 1952
      override val dateObtained: String = "March 14, 2006"
      override val bookPrice: Int = 2199
      override val purchasePrice: Int = 500
      override val condition: Int = 9
    }
    println(therm)
    println(therm.toXML)

    // 顺便提一句，如果你想要在 XML 文本中包含花括号（{ 或 }）而不是用它来切换到 Scala 代码，可以连着写两个花括号
    println(<a>{{{{brace yourself!}}}}</a>)
  }

  /**
   * 28.5 拆解 XML
   */
  def test28_5(): Unit = {
    // 提取文本。对任何 XML 节点调用 text 方法，可以得到该节点去掉元素标签后的所有文本
    println(<a>Sounds<tag/>good</a>.text)
    // 所有的编码字符会被自动解码
    println(<a>input ---&gt; output</a>.text)
    // 提取子元素。如果想要通过标签名找到某个子元素，只需要简单地用这个标签名调用 \
    println(<a><b><c>hello</c></b></a> \ "b")
    // 还可以通过双反斜杠 \\ 而不是 \ 操作符来执行“深度搜索”
    println(<a><b><c>hello</c></b></a> \ "c")
    println(<a><b><c>hello</c></b></a> \\ "c")
    println(<a><b><c>hello</c></b></a> \ "a")
    println(<a><b><c>hello</c></b></a> \\ "a")
    // 提取属性。可以用同样的 \ 和 \\ 方法来提取标签属性，只要在标签名前面加上 @ 符号即可
    val joe = <employee name="Joe" rank="code monkey" serial="123"/>
    println(joe \ "@name")
    println(joe \ "@serial")
  }

  /**
   * 28.6 反序列化
   */
  def test28_6(): Unit = {
    val therm = new CCTherm {
      override val description: String = "hot dog #5"
      override val yearMade: Int = 1952
      override val dateObtained: String = "March 14, 2006"
      override val bookPrice: Int = 2199
      override val purchasePrice: Int = 500
      override val condition: Int = 9
    }

    def fromXML(node: scala.xml.Node): CCTherm =
      new CCTherm {
        override val description: String = (node \ "description").text
        override val yearMade: Int = (node \ "yearMade").text.toInt
        override val dateObtained: String = (node \ "dateObtained").text
        override val bookPrice: Int = (node \ "bookPrice").text.toInt
        override val purchasePrice: Int = (node \ "purchasePrice").text.toInt
        override val condition: Int = (node \ "condition").text.toInt
      }

    val node = therm.toXML
    println(node)
    println(fromXML(node))
  }

  /**
   * 28.8 对 XML 做模式匹配
   */
  def test28_8(): Unit = {
    def proc(node: scala.xml.Node): String =
      node match {
        case <a>{contents}</a> => "It's an a: " + contents
        case <b>{contents}</b> => "It's a b: " + contents
        case _ => "It's something else"
      }

    println(proc(<a>apple</a>))
    println(proc(<b>banana</b>))
    println(proc(<c>cherry</c>))
    // 很可能这个函数并不是你想要的，因为它只能精确地查找带有单个子节点的 <a> 或 <b>，因而无法匹配下面这样的 case
    println(proc(<a>a <em>red</em> apple</a>))
    println(proc(<a/>))

    def proc2(node: scala.xml.Node): String =
      node match {
        case <a>{contents @ _*}</a> => "It's an a: " + contents
        case <b>{contents @ _*}</b> => "It's a b: " + contents
        case _ => "It's something else"
      }

    println(proc2(<a>a <em>red</em> apple</a>))
    println(proc2(<a/>))

    val catalog =
      <catalog>
        <cctherm>
          <description>hot dog #5</description>
          <yearMade>1952</yearMade>
          <dateObtained>March 14, 2006</dateObtained>
          <bookPrice>2199</bookPrice>
          <purchasePrice>500</purchasePrice>
          <condition>9</condition>
        </cctherm>
        <cctherm>
          <description>Sprite Boy</description>
          <yearMade>1964</yearMade>
          <dateObtained>April 28, 2003</dateObtained>
          <bookPrice>1695</bookPrice>
          <purchasePrice>595</purchasePrice>
          <condition>5</condition>
        </cctherm>
      </catalog>

    catalog match {
      case <catalog>{therms@_*}</catalog> =>
        for (therm <- therms)
          println("processing: " + (therm \ "description").text)
    }
    catalog match {
      case <catalog>{therms @ _*}</catalog> =>
        for (therm @ <cctherm>{_*}</cctherm> <- therms)
          println("processing: " + (therm \ "description").text)
    }
  }

  def main(args: Array[String]): Unit = {
    test28_3()
    println("-------------")
    test28_4()
    println("-------------")
    test28_5()
    println("-------------")
    test28_6()
    println("-------------")
    test28_8()
  }
}
