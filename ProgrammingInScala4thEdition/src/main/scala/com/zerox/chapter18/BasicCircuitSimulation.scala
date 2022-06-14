package com.zerox.chapter18

/**
 * @author zhuxi
 * @since 2022/6/14 17:41
 * @note
 * 18.6 电路模拟
 */
abstract class BasicCircuitSimulation extends Simulation {
  /**
   * BasicCircuitSiumulation 类声明了三个抽象方法来表示基本门的延迟：InverterDelay、AndGateDelay 和 OrGateDelay。
   * 实际的延迟在这个类的层次是未知的，因为它们取决于被模拟电路采用的技术。
   * 这就是为什么在 BasicCircuitSimulation 类中这些延迟是抽象的，这样它们的具体定义就代理给了子类来完成。
   *
   * @return
   */
  def InverterDelay: Int

  def AndGateDelay: Int

  def OrGateDelay: Int

  class Wire {
    /**
     * sigVal 变量表示当前的信号
     */
    private var sigVal = false
    /**
     * actions 表示当前附加到线上的动作过程
     */
    private var actions: List[Action] = List()

    /**
     * 返回线的当前信号。
     *
     * @return
     */
    def getSignal = sigVal

    /**
     * 将线的信号设置为 s。
     * 唯一有趣的方法实现是 setSignal：当线的信号发生变化时，新的值被存在变量 sigVal 中。不止如此，所有附加到线上的动作都会被执行。
     * 注意执行这个操作的简写语法：“actions foreach(_ ())”，这段代码会对 actions 列表中的每个元素应用函数“_ ()”。
     * 正如我们在 8.5 节提到的，函数 “_ ()” 是 “f => f()” 的简写，它接收一个函数（被称为 f）并将它应用到空的参数列表。
     *
     * @param s
     */
    def setSignal(s: Boolean) =
      if (s != sigVal) {
        sigVal = s
        actions foreach (_ ())
      }

    /**
     * 将给定的过程 p 附加在线的 actions 中。所有附加在线上的动作过程会在每次线的信号发生变化时被执行。
     * 通常线上的动作都是由连接到线上的组件添加的。附加的动作会在添加到线上的时候执行一次，然后当线的信号发生变化时，都会再次执行。
     *
     * @param a
     */
    def addAction(a: Action) = {
      actions = a :: actions
      a()
    }
  }

  /**
   * 创建反转器的唯一作用是将一个动作安装到输入线上。这个动作在安装时执行一次，然后每次输入变化时都会再次执行。
   * 这个动作的效果是设置反转器的输出值（通过 setSignal）为与输入相反的值。
   * 由于反转器有延迟，这个变化只有在输入值变更后的 InverterDelay 的模拟时间过后才会被执行。
   *
   * inverter 方法的作用是将 invertAction 添加到输入线。这个动作在执行时会读取输入信号并安装另一个将输出信号反转的动作到模拟日程中。
   * 后一个动作将在 InverterDelay 的模拟时间后执行。注意这个方法是如何利用模拟框架的 afterDelay 方法来创建一个新的在未来执行的工作项的。
   *
   * @param input
   * @param output
   */
  def inverter(input: Wire, output: Wire) = {
    def invertAction() = {
      val inputSig = input.getSignal
      afterDelay(InverterDelay) {
        output setSignal !inputSig
      }
    }

    input addAction invertAction
  }

  /**
   * 与门的实现跟反转器的实现类似。与门的目的是输出其输入信号的逻辑与结果。
   * 这应该在两个输入中任何一个发生变化后的 AndGateDelay 模拟时间后发生。
   *
   * andGate 方法的作用是添加一个 andAction 到两个输入线 a1 和 a2。
   * 当这个动作被调用时，同时获取两个输入信号并安装另一个动作，将 output 信号设置为输入信号的逻辑与。
   * 后一个动作将在 AndGateDelay 所指定的模拟时间后执行。注意当任意一根输入线的信号变化时，输出都需要被重新计算。
   * 这就是为什么同一个 andAction 会被同时安装到输入线 a1 和 a2。
   * @param a1
   * @param a2
   * @param output
   */
  def andGate(a1: Wire, a2: Wire, output: Wire) = {
    def andAction() = {
      val a1Sig = a1.getSignal
      val a2Sig = a2.getSignal
      afterDelay(AndGateDelay) {
        output setSignal (a1Sig & a2Sig)
      }
    }

    a1 addAction andAction
    a2 addAction andAction
  }

  def orGate(o1: Wire, o2: Wire, output: Wire) = {
    def orAction() = {
      val o1Sig = o1.getSignal
      val o2Sig = o2.getSignal
      afterDelay(OrGateDelay) {
        output setSignal (o1Sig | o2Sig)
      }
    }

    o1 addAction orAction
    o2 addAction orAction
  }

  /**
   * 为了运行这个模拟器，我们需要一种方式来观察线上信号的变化。
   * probe 过程的作用是安装一个 probeAction 到给定的线上。跟平常一样，这个安装的动作在每次线的信号发生变化时被执行。
   * 在本例中它仅仅是打印出线的名称（作为 probe 的首个参数传入），以及当前的模拟时间和线的新值。
   * @param name
   * @param wire
   */
  def probe(name: String, wire: Wire) = {
    def probeAction() = {
      println(name + " " + currentTime + " new-value = " + wire.getSignal)
    }

    wire addAction probeAction
  }
}
