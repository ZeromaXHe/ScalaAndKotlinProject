package com.zerox.from1001to1200

/**
 * @author zhuxi
 * @since 2022/6/21 11:58
 * @note
 * 1108. IP 地址无效化 | 难度：简单 | 标签：字符串
 * 给你一个有效的 IPv4 地址 address，返回这个 IP 地址的无效化版本。
 *
 * 所谓无效化 IP 地址，其实就是用 "[.]" 代替了每个 "."。
 *
 * 示例 1：
 * 输入：address = "1.1.1.1"
 * 输出："1[.]1[.]1[.]1"
 *
 * 示例 2：
 * 输入：address = "255.100.50.0"
 * 输出："255[.]100[.]50[.]0"
 *
 * 提示：
 * 给出的 address 是一个有效的 IPv4 地址
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/defanging-an-ip-address
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution1108 {
  /**
   * 执行用时：440 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：52.3 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：62 / 62
   *
   * @param address
   * @return
   */
  def defangIPaddr(address: String): String = {
    address.replace(".", "[.]")
  }
}
