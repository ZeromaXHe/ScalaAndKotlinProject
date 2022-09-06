package com.zerox.scala.aider

/**
 * @author zhuxi
 * @since 2022/9/6 14:54
 * @note
 * 双向 HashMap
 */
class BidirectionalHashMap[K, V] {

  import scala.collection.mutable

  private val kv = new mutable.HashMap[K, V]
  private val vk = new mutable.HashMap[V, mutable.HashSet[K]]

  def containsKey(k: K): Boolean = kv.contains(k)

  def containsValue(v: V): Boolean = vk.contains(v)

  def valueSet: collection.Set[V] = vk.keySet

  def getValue(k: K): V = kv(k)

  def getValueOrElse(k: K, default: V): V = kv.getOrElse(k, default)

  def keySet: collection.Set[K] = kv.keySet

  def getKeys(v: V): mutable.HashSet[K] = vk(v)

  def put(k: K, v: V): Unit = {
    if (kv.contains(k)) {
      val oldV = kv(k)
      vk(oldV).remove(k)
      if (vk(oldV).isEmpty) vk.remove(oldV)
    }
    kv(k) = v
    if (!vk.contains(v)) vk(v) = new mutable.HashSet[K]
    vk(v) += k
  }

  def remove(k: K): Option[V] = {
    val optV = kv.remove(k)
    if (optV.nonEmpty) {
      val v = optV.get
      vk(v).remove(k)
      if (vk(v).isEmpty) vk.remove(v)
    }
    optV
  }
}
