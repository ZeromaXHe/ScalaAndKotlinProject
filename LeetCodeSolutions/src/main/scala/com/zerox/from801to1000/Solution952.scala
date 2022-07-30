package com.zerox.from801to1000

/**
 * @author ZeromaXHe
 * @since 2022/7/30 11:18
 * @note
 * 952. 按公因数计算最大组件大小 | 难度：困难 | 标签：并查集、数组、数学
 * 给定一个由不同正整数的组成的非空数组 nums ，考虑下面的图：
 *
 * 有 nums.length 个节点，按从 nums[0] 到 nums[nums.length - 1] 标记；
 * 只有当 nums[i] 和 nums[j] 共用一个大于 1 的公因数时，nums[i] 和 nums[j]之间才有一条边。
 * 返回 图中最大连通组件的大小 。
 *
 * 示例 1：
 * 输入：nums = [4,6,15,35]
 * 输出：4
 *
 * 示例 2：
 * 输入：nums = [20,50,9,63]
 * 输出：2
 *
 * 示例 3：
 * 输入：nums = [2,3,6,7,4,12,21,39]
 * 输出：8
 *
 * 提示：
 * 1 <= nums.length <= 2 * 104
 * 1 <= nums[i] <= 105
 * nums 中所有值都 不同
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/largest-component-size-by-common-factor
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object Solution952 {
  def main(args: Array[String]): Unit = {
    println(largestComponentSize(Array(4, 6, 15, 35)))
  }

  /**
   * 执行用时：884 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：60.6 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：108 / 108
   *
   * 参考题解做的。空间换时间，先用质因数预处理好并查集
   *
   * @param nums
   * @return
   */
  def largestComponentSize(nums: Array[Int]): Int = {
    val m = nums.max
    val unionSet = new UnionSet(m + 1)
    for (num <- nums) {
      var i = 2
      while (i * i <= num) {
        if (num % i == 0) {
          unionSet.union(num, i)
          unionSet.union(num, num / i)
        }
        i += 1
      }
    }
    val counts = new Array[Int](m + 1)
    var max = 0
    for (num <- nums) {
      val parent = unionSet.find(num)
      counts(parent) += 1
      max = math.max(max, counts(parent))
    }
    max
  }

  class UnionSet(n: Int) {
    private val parent = Array.tabulate(n)(identity)
    private val size = Array.fill(n)(1)

    def find(x: Int): Int = {
      if (x != parent(x)) parent(x) = find(parent(x))
      parent(x)
    }

    def union(x: Int, y: Int): Unit = {
      val rootX = find(x)
      val rootY = find(y)
      if (rootX == rootY) return
      val big = if (size(rootX) < size(rootY)) rootY else rootX
      val small = if (size(rootX) < size(rootY)) rootX else rootY
      parent(small) = big
      size(big) += size(small)
    }

    def getSize(x: Int): Int = {
      size(x)
    }
  }
}
