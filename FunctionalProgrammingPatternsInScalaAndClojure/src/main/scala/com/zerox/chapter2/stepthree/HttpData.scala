package com.zerox.chapter2.stepthree

/**
 * @author zhuxi
 * @since 2022/7/13 11:40
 * @note
 */
case class ScalaHttpRequest(headers: Map[String, String], body: String, path: String)
case class ScalaHttpResponse(body: String, responseCode: Integer)
