package com.zerox.chapter18

/**
 * @author zhuxi
 * @since 2022/6/14 17:15
 * @note
 * 18.5 Simulation API
 */
abstract class Simulation {
  /**
   * 离散事件模拟在指定的时间（time）执行用户定义的动作（action）。所有有具体模拟子类定义的动作都是如下类型 Action 的
   * 这条语句将 Action 定义为接收空参数列表并返回 Unit 的过程类型的别名。Action 是 Simulation 类的类型成员（type member）。
   * 可以将它想象成 () => Unit 这个类型更可读的名字。关于类型成员的更详细内容请参考 20.6 节。
   */
  type Action = () => Unit

  /**
   * 一个需要在指定时间执行的动作被称为工作项（work item）。工作项由如下这个类实现：
   *
   * @param time
   * @param action
   */
  case class WorkItem(time: Int, action: Action)

  /**
   * 动作被执行的时间是模拟时间，跟实际的“挂钟”（wall clock）时间无关。模拟时间简单地以整数表示，当前的模拟时间保存在私有变量里：
   * 我们将 WorkItem 处理成样例类，这是由于样例类的便捷性：
   * 可以用 WorkItem 工厂方法创建该类的示例，还可以免费获得对构造方法参数 time 和 action 的 getter 方法。
   * 还要注意一点，WorkItem 类是内嵌在 Simulation 类里的。Scala 对嵌套类的处理跟 Java 类似。更多细节请参考 20.7 节。
   */
  private var curtime = 0

  def currentTime: Int = curtime

  /**
   * Simulation 类有一个日程（agenda），记录了所有还未执行的工作项。工作项的排序依据是它们需要执行的模拟时间
   */
  private var agenda: List[WorkItem] = List()

  /**
   * 被创建出来的工作项还需要被插入到日程中。可以通过 insert 方法来完成，这个方法保证了日程是按时间排序的
   *
   * @param ag
   * @param item
   * @return
   */
  private def insert(ag: List[WorkItem], item: WorkItem): List[WorkItem] = {
    if (ag.isEmpty || item.time < ag.head.time) item :: ag
    else ag.head :: insert(ag.tail, item)
  }

  /**
   * 列表 agenda 的排序由更新它的 insert 方法保证。我们可以看到 insert 方法的调用来自 afterDelay，这也是向日程添加工作项的唯一方式。
   * 正如这个名称表达的，这个方法向日程中插入一个动作（由 block 给出），计划在当前模拟时间之后的若干（由 delay 给出）时间单元执行。
   * 要执行的代码包含在方法的第二个入参。这个入参的形参类型为 “=> Unit”（即按名传递的类型为 Unit 的计算）。
   * 我们可以回忆一下，传名参数在传入方法时并不会被求值。
   *
   * @param delay
   * @param block
   */
  def afterDelay(delay: Int)(block: => Unit) = {
    val item = WorkItem(currentTime + delay, () => block)
    agenda = insert(agenda, item)
  }

  /**
   * next 方法用模式匹配将当前的日程拆成一个最开始的工作项 item 和剩下的工作项 rest 两部分。
   * 然后将最开始的工作项 item 从当前日程移除，将模拟时间 curtime 设置为工作项的时间，并执行该工作项的动作。
   * 注意 next 只能在日程不为空时调用。我们并没有给出空列表的 case，因此当我们尝试对空日程运行 next 时，将得到一个 MatchError 异常。
   *
   * 事实上，Scala编译器通常会警告我们漏掉了列表的某个可能的模式：warning: match is not exhaustive! missing combination Nil
   * 在本例中，缺失的这个 case 并不是问题，因为我们知道 next 只在非空的日程才会被调用。因此，我们可能会想要禁用这个警告。
   * 在 15.5 节提到过，可以通过对模式匹配的选择器表达式添加 @unchecked 注解来禁用警告。
   * 这也是为什么 Simulation 代码使用 “(agenda: @unchecked) match” 而不是 “agenda match”。
   */
  private def next() = {
    (agenda: @unchecked) match {
      case item :: rest =>
        agenda = rest
        curtime = item.time
        item.action()
    }
  }

  /**
   * Simulation 类的核心是下面这个 run 方法。
   * 这个方法不断重复地从日程中获取第一个工作项，从日程移除并执行，直到日程中没有更多要执行的工作项为止。每一步都会调用 next 方法。
   */
  def run() = {
    afterDelay(0) {
      println("*** simulation started, time = " + currentTime + " ***")
    }
    while (agenda.nonEmpty) next()
  }
}
