package com.zerox

/**
 * @author zhuxi
 * @since 2022/6/29 9:55
 * @note
 * 535. TinyURL 的加密与解密 | 难度：中等 | 标签：设计、哈希表、字符串、哈希函数
 * TinyURL 是一种 URL 简化服务， 比如：当你输入一个 URL https://leetcode.com/problems/design-tinyurl 时，
 * 它将返回一个简化的URL http://tinyurl.com/4e9iAk 。请你设计一个类来加密与解密 TinyURL 。
 *
 * 加密和解密算法如何设计和运作是没有限制的，你只需要保证一个 URL 可以被加密成一个 TinyURL ，并且这个 TinyURL 可以用解密方法恢复成原本的 URL 。
 *
 * 实现 Solution 类：
 *
 * Solution() 初始化 TinyURL 系统对象。
 * String encode(String longUrl) 返回 longUrl 对应的 TinyURL 。
 * String decode(String shortUrl) 返回 shortUrl 原本的 URL 。题目数据保证给定的 shortUrl 是由同一个系统对象加密的。
 *
 * 示例：
 * 输入：url = "https://leetcode.com/problems/design-tinyurl"
 * 输出："https://leetcode.com/problems/design-tinyurl"
 *
 * 解释：
 * Solution obj = new Solution();
 * string tiny = obj.encode(url); // 返回加密后得到的 TinyURL 。
 * string ans = obj.decode(tiny); // 返回解密后得到的原本的 URL 。
 *
 * 提示：
 * 1 <= url.length <= 104
 * 题目数据保证 url 是一个有效的 URL
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/encode-and-decode-tinyurl
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution535 {

  /**
   * 执行用时：484 ms, 在所有 Scala 提交中击败了 50.00% 的用户
   * 内存消耗：52.5 MB, 在所有 Scala 提交中击败了 50.00% 的用户
   * 通过测试用例：739 / 739
   *
   * 没有要求的话，完全没有写的愿望。直接无招胜有招，直接返回就完事了。
   */
  class Codec {
    // Encodes a URL to a shortened URL.
    def encode(longURL: String): String = {
      longURL
    }

    // Decodes a shortened URL to its original URL.
    def decode(shortURL: String): String = {
      shortURL
    }
  }

  /**
   * Your Codec object will be instantiated and called as such:
   * var obj = new Codec()
   * val s = obj.encode(longURL)
   * val ans = obj.decode(s)
   */
}
