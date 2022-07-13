package com.zerox.chapter2.steptwo

import com.zerox.chapter2.stepone.ScalaView
import com.zerox.chapter2.{ControllerException, HttpRequest, HttpResponse, RenderingException}

/**
 * @author zhuxi
 * @since 2022/7/13 11:34
 * @note
 */
trait ScalaTestController {
  def handleRequest(httpRequest: HttpRequest): HttpResponse
}

class FunctionTestController(view: ScalaView, doRequest: HttpRequest => Map[String, List[String]]) extends ScalaTestController {
  override def handleRequest(request: HttpRequest): HttpResponse = {
    var responseCode = 200
    var responseBody = ""

    try {
      val model = doRequest(request)
      responseBody = view.render(model)
    } catch {
      case e: ControllerException => responseCode = e.getStatusCode
      case _: RenderingException =>
        responseCode = 500
        responseBody = "Exception while rendering"
      case _: Exception => responseCode = 500
    }
    HttpResponse.Builder.newBuilder().body(responseBody).responseCode(responseCode).build()
  }
}
