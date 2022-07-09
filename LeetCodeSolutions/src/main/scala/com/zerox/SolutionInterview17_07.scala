package com.zerox

/**
 * @author zhuxi
 * @since 2022/7/9 11:46
 * @note
 * 面试题 17.07. 婴儿名字 | 难度：中等 | 标签：深度优先搜索、广度优先搜索、并查集、数组、哈希表、字符串、计数
 * 每年，政府都会公布一万个最常见的婴儿名字和它们出现的频率，也就是同名婴儿的数量。有些名字有多种拼法，例如，John 和 Jon 本质上是相同的名字，但被当成了两个名字公布出来。给定两个列表，一个是名字及对应的频率，另一个是本质相同的名字对。设计一个算法打印出每个真实名字的实际频率。注意，如果 John 和 Jon 是相同的，并且 Jon 和 Johnny 相同，则 John 与 Johnny 也相同，即它们有传递和对称性。
 *
 * 在结果列表中，选择 字典序最小 的名字作为真实名字。
 *
 * 示例：
 * 输入：names = ["John(15)","Jon(12)","Chris(13)","Kris(4)","Christopher(19)"], synonyms = ["(Jon,John)","(John,Johnny)","(Chris,Kris)","(Chris,Christopher)"]
 * 输出：["John(27)","Chris(36)"]
 *
 * 提示：
 * names.length <= 100000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/baby-names-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
object SolutionInterview17_07 {
  def main(args: Array[String]): Unit = {
    println(trulyMostPopular(Array("John(15)", "Jon(12)", "Chris(13)", "Kris(4)", "Christopher(19)"),
      Array("(Jon,John)", "(John,Johnny)", "(Chris,Kris)", "(Chris,Christopher)")).mkString("Array(", ", ", ")"))
    println(trulyMostPopular(Array("Fcclu(70)", "Ommjh(63)", "Dnsay(60)", "Qbmk(45)", "Unsb(26)", "Gauuk(75)",
      "Wzyyim(34)", "Bnea(55)", "Kri(71)", "Qnaakk(76)", "Gnplfi(68)", "Hfp(97)", "Qoi(70)", "Ijveol(46)", "Iidh(64)",
      "Qiy(26)", "Mcnef(59)", "Hvueqc(91)", "Obcbxb(54)", "Dhe(79)", "Jfq(26)", "Uwjsu(41)", "Wfmspz(39)", "Ebov(96)",
      "Ofl(72)", "Uvkdpn(71)", "Avcp(41)", "Msyr(9)", "Pgfpma(95)", "Vbp(89)", "Koaak(53)", "Qyqifg(85)", "Dwayf(97)",
      "Oltadg(95)", "Mwwvj(70)", "Uxf(74)", "Qvjp(6)", "Grqrg(81)", "Naf(3)", "Xjjol(62)", "Ibink(32)", "Qxabri(41)",
      "Ucqh(51)", "Mtz(72)", "Aeax(82)", "Kxutz(5)", "Qweye(15)", "Ard(82)", "Chycnm(4)", "Hcvcgc(97)", "Knpuq(61)",
      "Yeekgc(11)", "Ntfr(70)", "Lucf(62)", "Uhsg(23)", "Csh(39)", "Txixz(87)", "Kgabb(80)", "Weusps(79)", "Nuq(61)",
      "Drzsnw(87)", "Xxmsn(98)", "Onnev(77)", "Owh(64)", "Fpaf(46)", "Hvia(6)", "Kufa(95)", "Chhmx(66)", "Avmzs(39)",
      "Okwuq(96)", "Hrschk(30)", "Ffwni(67)", "Wpagta(25)", "Npilye(14)", "Axwtno(57)", "Qxkjt(31)", "Dwifi(51)",
      "Kasgmw(95)", "Vgxj(11)", "Nsgbth(26)", "Nzaz(51)", "Owk(87)", "Yjc(94)", "Hljt(21)", "Jvqg(47)", "Alrksy(69)",
      "Tlv(95)", "Acohsf(86)", "Qejo(60)", "Gbclj(20)", "Nekuam(17)", "Meutux(64)", "Tuvzkd(85)", "Fvkhz(98)",
      "Rngl(12)", "Gbkq(77)", "Uzgx(65)", "Ghc(15)", "Qsc(48)", "Siv(47)"),
      Array("(Gnplfi,Qxabri)", "(Uzgx,Siv)", "(Bnea,Lucf)", "(Qnaakk,Msyr)", "(Grqrg,Gbclj)", "(Uhsg,Qejo)",
        "(Csh,Wpagta)", "(Xjjol,Lucf)", "(Qoi,Obcbxb)", "(Npilye,Vgxj)", "(Aeax,Ghc)", "(Txixz,Ffwni)", "(Qweye,Qsc)",
        "(Kri,Tuvzkd)", "(Ommjh,Vbp)", "(Pgfpma,Xxmsn)", "(Uhsg,Csh)", "(Qvjp,Kxutz)", "(Qxkjt,Tlv)", "(Wfmspz,Owk)",
        "(Dwayf,Chycnm)", "(Iidh,Qvjp)", "(Dnsay,Rngl)", "(Qweye,Tlv)", "(Wzyyim,Kxutz)", "(Hvueqc,Qejo)", "(Tlv,Ghc)",
        "(Hvia,Fvkhz)", "(Msyr,Owk)", "(Hrschk,Hljt)", "(Owh,Gbclj)", "(Dwifi,Uzgx)", "(Iidh,Fpaf)", "(Iidh,Meutux)",
        "(Txixz,Ghc)", "(Gbclj,Qsc)", "(Kgabb,Tuvzkd)", "(Uwjsu,Grqrg)", "(Vbp,Dwayf)", "(Xxmsn,Chhmx)",
        "(Uxf,Uzgx)")).mkString("Array(", ", ", ")"))
  }

  /**
   * 执行用时：1184 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：63.8 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：36 / 36
   *
   * @param names
   * @param synonyms
   * @return
   */
  def trulyMostPopular_regex(names: Array[String], synonyms: Array[String]): Array[String] = {
    val regex = """([a-zA-Z]+)\((\d+)\)""".r
    val map = names.map(str => {
      val regex(name, freq) = str
      (name, freq.toInt)
    })

    val unionSet = new UnionSet(map)
    val regexBind = """\(([a-zA-Z]+),([a-zA-Z]+)\)""".r
    synonyms.foreach(str => {
      val regexBind(name1, name2) = str
      unionSet.union(name1, name2)
    })

    unionSet.getAllFreq
  }

  /**
   * 执行用时：1000 ms, 在所有 Scala 提交中击败了 100.00% 的用户
   * 内存消耗：63.1 MB, 在所有 Scala 提交中击败了 100.00% 的用户
   * 通过测试用例：36 / 36
   *
   * @param names
   * @param synonyms
   * @return
   */
  def trulyMostPopular(names: Array[String], synonyms: Array[String]): Array[String] = {
    val map = names.map(str => {
      val nameFreq = str.substring(0, str.length - 1).split("\\(")
      (nameFreq(0), nameFreq(1).toInt)
    })

    val unionSet = new UnionSet(map)
    synonyms.foreach(str => {
      val names = str.substring(1, str.length - 1).split(",")
      unionSet.union(names(0), names(1))
    })

    unionSet.getAllFreq
  }

  class UnionSet(map: Array[(String, Int)]) {
    private val parent = new scala.collection.mutable.HashMap[String, String]
    private val freq = new scala.collection.mutable.HashMap[String, Int] ++= map

    def find(x: String): String = {
      if (x != parent.getOrElse(x, x)) parent(x) = find(parent(x))
      parent.getOrElse(x, x)
    }

    def union(x: String, y: String): Unit = {
      val rootX = find(x)
      val rootY = find(y)
      if (rootX == rootY) return
      val small = if (rootX > rootY) rootY else rootX
      val big = if (rootX > rootY) rootX else rootY
      parent(big) = small
      freq.put(small, freq.getOrElse(small, 0) + freq.getOrElse(big, 0))
      freq remove big
    }

    def getAllFreq: Array[String] = {
      freq.map(e => s"${e._1}(${e._2})").toArray
    }
  }
}
