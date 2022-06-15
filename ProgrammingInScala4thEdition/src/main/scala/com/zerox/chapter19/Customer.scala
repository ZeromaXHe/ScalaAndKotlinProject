package com.zerox.chapter19

/**
 * @author zhuxi
 * @since 2022/6/15 18:23
 * @note
 * 19.6 逆变
 * 现在我们来看一下 Customer 的最后一行。这一行调用了 Library 的 printBookList，并将 getTitle 打包在一个函数值传入。
 * 这一行能够通过类型检查，尽管函数的结果类型 String 是 printBookList 的 info 参数的结果类型 AnyRef 的子类型。
 * 这段代码能通过编译是因为函数的结果类型被声明为是协变的。
 */
object Customer extends App {
  def getTitle(p: Publication): String = p.title
  Library.printBookList(getTitle)
}

class Publication(val title: String)

class Book(title: String) extends Publication(title)

object Library {
  val books: Set[Book] = Set(new Book("Programming in Scala"), new Book("Walden"))

  def printBookList(info: Book => AnyRef) = {
    for (book <- books) println(info(book))
  }
}