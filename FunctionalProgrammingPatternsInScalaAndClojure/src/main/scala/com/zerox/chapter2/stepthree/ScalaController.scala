package com.zerox.chapter2.stepthree

import com.zerox.chapter2.{ControllerException, RenderingException}
import com.zerox.chapter2.stepone.ScalaView

/**
 * @author zhuxi
 * @since 2022/7/13 11:42
 * @note
 */
trait ScalaController {
  def handleRequest(httpRequest: ScalaHttpRequest): ScalaHttpResponse
}

class FunctionController(view: ScalaView, doRequest: ScalaHttpRequest => Map[String, List[String]]) extends ScalaController {
  override def handleRequest(request: ScalaHttpRequest): ScalaHttpResponse = {
    try {
      val model = doRequest(request)
      val responseBody = view.render(model)
      ScalaHttpResponse(responseBody, 200)
    } catch {
      case e: ControllerException =>
        ScalaHttpResponse("", e.getStatusCode)
      case _: RenderingException =>
        ScalaHttpResponse("Exception while rendering", 500)
      case _: Exception =>
        ScalaHttpResponse("", 500)
    }
  }
}
