package com.zerox.chapter2.stepone

import com.zerox.chapter2.RenderingException

/**
 * @author zhuxi
 * @since 2022/7/13 11:29
 * @note
 */
trait ScalaView {
  def render(model: Map[String, List[String]]): String
}

class FunctionView(viewRender: Map[String, List[String]] => String) extends ScalaView {
  override def render(model: Map[String, List[String]]): String =
    try {
      viewRender(model)
    } catch {
      case e: Exception => throw new RenderingException(e)
    }
}