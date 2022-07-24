package com.zerox.contest

/**
 * @author ZeromaXHe
 * @since 2022/7/24 10:18
 * @note
 * 第 303 场周赛
 */
object WeeklyContest303 {
  /**
   * 6124. 第一个出现两次的字母 | 难度：简单
   * 92 / 92 个通过测试用例
   * 状态：通过
   * 执行用时: 436 ms
   * 内存消耗: 52.3 MB
   *
   * @param s
   * @return
   */
  def repeatedCharacter(s: String): Char = {
    val set = new scala.collection.mutable.HashSet[Char]
    for (i <- s.indices) {
      if (set.contains(s(i))) return s(i)
      set.add(s(i))
    }
    '#'
  }

  /**
   * 6125. 相等行列对 | 难度：中等
   * 69 / 69 个通过测试用例
   * 状态：通过
   * 执行用时: 952 ms
   * 内存消耗: 62.9 MB
   *
   * @param grid
   * @return
   */
  def equalPairs(grid: Array[Array[Int]]): Int = {
    val trans = grid.transpose
    var count = 0
    for (i <- grid; j <- trans) {
      if (i sameElements j) count += 1
    }
    count
  }

  /**
   * 6126. 设计食物评分系统 | 难度：中等
   * 通过
   *
   * @param _foods
   * @param _cuisines
   * @param _ratings
   */
  class FoodRatings(_foods: Array[String], _cuisines: Array[String], _ratings: Array[Int]) {
    case class Food(name: String, cuisine: String, var rate: Int)

    import scala.collection.mutable

    val heaps = new mutable.HashMap[String, java.util.PriorityQueue[Food]]
    val map = new mutable.HashMap[String, Food]

    for (i <- _foods.indices) {
      val food = Food(_foods(i), _cuisines(i), _ratings(i))
      if (!heaps.contains(food.cuisine)) {
        heaps(food.cuisine) = new java.util.PriorityQueue[Food](
          Ordering.by[Food, Int](f => f.rate).reverse.thenComparing(Ordering.by[Food, String](_.name)))
      }
      heaps(food.cuisine).add(food)
      map(food.name) = food
    }

    def changeRating(food: String, newRating: Int) {
      if (map.contains(food)) {
        val changing = map(food)
        val cuisineHeap = heaps(changing.cuisine)
        cuisineHeap.remove(changing)
        changing.rate = newRating
        cuisineHeap.add(changing)
      }
    }

    def highestRated(cuisine: String): String = {
      if (heaps.contains(cuisine)) {
        heaps(cuisine).peek().name
      } else ""
    }

  }

  /**
   * Your FoodRatings object will be instantiated and called as such:
   * var obj = new FoodRatings(foods, cuisines, ratings)
   * obj.changeRating(food,newRating)
   * var param_2 = obj.highestRated(cuisine)
   */

  /**
   * 6127. 优质数对的数目 | 难度：困难
   * 56 / 56 个通过测试用例
   * 状态：通过
   * 执行用时: 1192 ms
   * 内存消耗: 81.6 MB
   *
   * @param nums
   * @param k
   * @return
   */
  def countExcellentPairs(nums: Array[Int], k: Int): Long = {
    var res = 0L
    val bit = nums.toSet.toArray.map(Integer.bitCount).sorted
    val bitToCount = bit.groupBy(identity).mapValues(_.length).toArray.sortBy(t => t._1).map(t => Array(t._1, t._2))
    for (i <- (0 until (bitToCount.length - 1)).reverse) {
      bitToCount(i)(1) += bitToCount(i + 1)(1)
    }
    val map = bitToCount.map(arr => (arr(0), arr(1))).toMap
    val dp = new Array[Int](k + 1)
    for (i <- 0 to k) {
      if (map.contains(k - i)) dp(i) = map(k - i)
      else if (i == 0) dp(i) = map.find(t => t._1 > k).getOrElse((0, 0))._2
      else dp(i) = dp(i - 1)
    }
    for (c <- bit) {
      if (c > k) res += bit.length
      else res += dp(c)
    }
    res
  }

  def main(args: Array[String]): Unit = {
    // heapTest()
    println(countExcellentPairs(Array(1, 2, 3, 1), 3)) // 5
    println(countExcellentPairs(Array(5, 1, 1), 10)) // 0
    println(countExcellentPairs(Array(1, 2, 3, 1, 536870911), 3)) // 12
  }

  def heapTest() = {
    case class Food(name: String, cuisine: String, var rate: Int)
    val foods = new java.util.PriorityQueue[Food](
      Ordering.by[Food, Int](f => f.rate).reverse.thenComparing(Ordering.by[Food, String](_.name)))
    foods.add(Food("aaa", "jjj", 50))
    foods.add(Food("abb", "jjj", 50))
    foods.add(Food("cbbc", "jjj", 100))
    println(foods.poll())
    println(foods.poll())
    println(foods.poll())
  }
}
