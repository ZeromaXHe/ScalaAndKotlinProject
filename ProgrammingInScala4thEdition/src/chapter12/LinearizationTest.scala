package chapter12

/**
 * @Author: zhuxi
 * @Time: 2022/6/10 16:50
 * @Description: 12.6 为什么不用多重继承
 *               特质是一种从多个像类一样的结构继承的方式，不过它们跟许多其他语言中的多重继承有着重大的区别。
 *               其中一个区别尤为重要：对 super 的解读。在多重继承中，super 调用的方法在调用发生的地方就已经确定了。
 *               而特质中的 super 调用的方法取决于类和混入该类的特质的线性化（linearization）。
 *               正是这个差别让前一节介绍的可叠加修改变为可能。
 *
 *               线性化的确切顺序在语言规格说明书里有描述。
 *               这个描述有点复杂，不过你需要知道的要点是，在任何线性化中，类总是位于所有它的超类和混入的特质之前。
 *               因此，当你写下调用 super 的方法时，那个方法绝对是在修改超类和混入特质的行为，而不是反过来。
 * @ModifiedBy: zhuxi
 */
class Animal2

trait Furry extends Animal2

trait HasLegs2 extends Animal2

trait FourLegged extends HasLegs2

/**
 * Animal2 类的线性化关系为：Animal2 -> AnyRef -> Any
 * Furry 类的线性化关系为：Furry -> Animal2 -> AnyRef -> Any
 * Fourlegged 类的线性化关系为：FourLegged -> HasLegs2 -> Furry -> Animal2 -> AnyRef -> Any
 * Cat 类的线性化关系为：Cat -> FourLegged -> HasLegs2 -> Furry -> Animal2 -> AnyRef -> Any
 * 任何已经在超类或首个混入中拷贝过的类都不再重复出现
 */
class Cat extends Animal2 with Furry with FourLegged
