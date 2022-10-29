package com.zerox.scala.from1to200

/**
 * @author zhuxi
 * @since 2022/10/29 9:54
 * @note
 * 161. 相隔为 1 的编辑距离 | 难度：中等 | 标签：双指针、字符串
 * 给定两个字符串 s 和 t ，如果它们的编辑距离为 1 ，则返回 true ，否则返回 false 。
 *
 * 字符串 s 和字符串 t 之间满足编辑距离等于 1 有三种可能的情形：
 *
 * 往 s 中插入 恰好一个 字符得到 t
 * 从 s 中删除 恰好一个 字符得到 t
 * 在 s 中用 一个不同的字符 替换 恰好一个 字符得到 t
 *
 * 示例 1：
 *
 * 输入: s = "ab", t = "acb"
 * 输出: true
 * 解释: 可以将 'c' 插入字符串 s 来得到 t。
 * 示例 2:
 *
 * 输入: s = "cab", t = "ad"
 * 输出: false
 * 解释: 无法通过 1 步操作使 s 变为 t。
 *
 * 提示:
 * 0 <= s.length, t.length <= 104
 * s 和 t 由小写字母，大写字母和数字组成
 */
object Solution161 {
  /**
   * 时间 460 ms 击败 100%
   * 内存 52.5 MB 击败 100%
   *
   * 细节疯狂出错，提交错了 3 次
   *
   * @param s
   * @param t
   * @return
   */
  def isOneEditDistance(s: String, t: String): Boolean = {
    if (s.length == t.length) {
      var diff = false
      for (i <- s.indices) {
        if (s(i) != t(i)) {
          if (diff) return false
          diff = true
        }
      }
      diff
    } else if (s.length == t.length - 1) {
      var diff = 0
      for (i <- s.indices) {
        while (s(i) != t(i + diff)) {
          if (diff == 1) return false
          diff += 1
        }
      }
      true
    } else if (s.length - 1 == t.length) {
      var diff = 0
      for (i <- t.indices) {
        while (s(i + diff) != t(i)) {
          if (diff == 1) return false
          diff += 1
        }
      }
      true
    } else false
  }
}
