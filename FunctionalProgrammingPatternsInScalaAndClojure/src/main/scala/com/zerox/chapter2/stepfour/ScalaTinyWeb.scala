package com.zerox.chapter2.stepfour

import com.zerox.chapter2.stepthree.{ScalaController, ScalaHttpRequest, ScalaHttpResponse}

/**
 * @author zhuxi
 * @since 2022/7/13 11:45
 * @note
 */
class ScalaTinyWeb(controllers: Map[String, ScalaController], filters: List[ScalaHttpRequest => ScalaHttpRequest]) {
  def handleRequest(httpRequest: ScalaHttpRequest): Option[ScalaHttpResponse] = {
    val composedFilter = filters.reverse.reduceLeft((composed, next) => composed compose next)
    val filteredRequest = composedFilter(httpRequest)
    val controllerOption = controllers.get(filteredRequest.path)
    controllerOption map {controller => controller.handleRequest(filteredRequest)}
  }
}
