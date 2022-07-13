package com.zerox.chapter2.example

import com.zerox.chapter2.stepfour.ScalaTinyWeb
import com.zerox.chapter2.stepone.FunctionView
import com.zerox.chapter2.stepthree.{FunctionController, ScalaHttpRequest}

import scala.util.Random

/**
 * @author zhuxi
 * @since 2022/7/13 11:51
 * @note
 */
object ScalaExample {
  def greetingViewRenderer(model: Map[String, List[String]]) =
    "<h1>Friendly Greetings:%s".format(
      model
        getOrElse("greetings", List[String]())
        map (renderGreeting)
        mkString ", "
    )

  private def renderGreeting(greeting: String) = "<h2>%s</h2>".format(greeting)

  def greetingView = new FunctionView(greetingViewRenderer)

  def handleGreetingRequest(request: ScalaHttpRequest) =
    Map("greetings" -> request.body.split(",").toList.map(makeGreeting))

  private def random = new Random()

  private def greetings = Vector("Hello", "Greetings", "Salutations", "Hola")

  private def makeGreeting(name: String) = "%s, %s".format(greetings(random.nextInt(greetings.size)), name)

  def greetingController = new FunctionController(greetingView, handleGreetingRequest)

  private def loggingFilter(request: ScalaHttpRequest) = {
    println("In Logging Filter - request for path: %s".format(request.path))
    request
  }

  def tinyWeb = new ScalaTinyWeb(Map("/greeting" -> greetingController), List(loggingFilter))

  def testHttpRequest = ScalaHttpRequest(body = "Mike,Joe,John,Steve", path = "/greeting", headers = Map.empty)

  def main(args: Array[String]): Unit = {
    val optionResp = tinyWeb.handleRequest(testHttpRequest)
    optionResp.foreach(resp => println("body: " + resp.body + "\ncode:" + resp.responseCode))
  }
}
