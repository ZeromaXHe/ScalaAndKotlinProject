package com.zerox.chapter3

/**
 * @author zhuxi
 * @since 2022/7/13 14:26
 * @note
 * 第 3 章 替代面向对象模式
 */
object Chapter3Test {
  /**
   * 模式 1 替代函数式接口
   */
  private def pattern01(): Unit = {
    case class Person(firstName: String, lastName: String)

    case class PersonWithMN(firstName: String, middleName: String, lastName: String)

    val p1 = Person("Michael", "Bevilacqua")
    val p2 = Person("Pedro", "Vasquez")
    val p3 = Person("Robert", "Aarons")

    val people = Vector(p3, p2, p1)
    println(people.sortWith((p1, p2) => p1.firstName < p2.firstName))

    val pm1 = PersonWithMN("Aaron", "Jeffery", "Smith")
    val pm2 = PersonWithMN("Aaron", "Bailey", "Zanthar")
    val pm3 = PersonWithMN("Brian", "Adams", "Smith")
    val peopleWithMN = Vector(pm1, pm2, pm3)

    def complicatedSort(p1: PersonWithMN, p2: PersonWithMN) =
      if (p1.firstName != p2.firstName) p1.firstName < p2.firstName
      else if (p1.lastName != p2.lastName) p1.lastName < p2.lastName
      else p1.middleName < p2.middleName

    println(peopleWithMN.sortWith(complicatedSort))
  }

  /**
   * 模式 2 替代承载状态的函数式接口
   */
  def pattern02(): Unit = {
    case class Person(firstName: String, middleName: String, lastName: String)

    /**
     * 有一个地方可以进行优化，即创建一个短路版本的复合比较器，它可以在发现首个非零结果的时候停止运行比较器。
     * 为了做到这一点，我们可以使用模式 12 “尾递归模式” 中的递归函数，而不再使用此处为了方便大家理解所用的这种方式。
     *
     * @param comparisons
     * @return
     */
    def makeComposedComparison(comparisons: (Person, Person) => Int*) =
    // (p1: Person, p2: Person) => comparisons.map(cmp => cmp(p1, p2)).find(_ != 0).getOrElse(0)
      (p1: Person, p2: Person) => comparisons.find(cmp => cmp(p1, p2) != 0).getOrElse(0)

    def firstNameComparison(p1: Person, p2: Person) = p1.firstName.compareTo(p2.firstName)

    def lastNameComparison(p1: Person, p2: Person) = p1.lastName.compareTo(p2.lastName)

    val firstAndlastNameComparison = makeComposedComparison(firstNameComparison, lastNameComparison)

    val p1 = Person("John", "", "Adams")
    val p2 = Person("John", "Quincy", "Adams")

    println(firstAndlastNameComparison(p1, p2))
  }

  def pattern03(): Unit = {
    class CashRegister(var total: Int) {
      def addCash(toAdd: Int) = total += toAdd
    }

    def makePurchase(register: CashRegister, amount: Int) = () => {
      println("Purchase in amount: " + amount)
      register.addCash(amount)
    }

    // 用于保持对购买行为的跟踪
    var purchases: Vector[() => Unit] = Vector()

    def executePurchase(purchase: () => Unit): Unit = {
      purchases = purchases :+ purchase
      purchase()
    }

    // 下面是实际的解决方案
    val register = new CashRegister(0)
    val purchaseOne = makePurchase(register, 100)
    val purchaseTwo = makePurchase(register, 50)
    executePurchase(purchaseOne)
    executePurchase(purchaseTwo)
    println(register.total)
    println(purchases)
  }

  /**
   * 模式 4 替代生成器模式来获得不可变对象
   */
  def pattern04(): Unit = {
    // 不可变类
    class Person(val firstName: String, val middleName: String, val lastName: String)

    val p1 = new Person("John", "Quincy", "Adams")
    println(p1)
    val p2 = new Person(firstName = "John", middleName = "Quincy", lastName = "Adams")
    println(p2)

    class PersonWithDefault(val firstName: String, val middleName: String = "", val lastName: String)

    val p3 = new PersonWithDefault(firstName = "John", lastName = "Adams")
    println(p3)

    // 样本类
    case class PersonCaseClass(val firstName: String, val middleName: String = "", val lastName: String)
    val pc1 = PersonCaseClass(firstName = "John", lastName = "Adams")
    println(pc1)
    val pc2 = PersonCaseClass(firstName = "John", lastName = "Adams")
    println(pc2)
    println(pc1.equals(pc2))

    val pc3 = PersonCaseClass(firstName = "John", middleName = "Quincy", lastName = "Adams")
    println(pc3)
    println(pc2.equals(pc3))

    val pc1copy = pc1.copy(middleName = "Quincy")
    println(pc1copy)

    pc3 match {
      case PersonCaseClass(firstName, middleName, lastName) =>
        "First: %s - Middle: %s - Last: %s".format(firstName, middleName, lastName)
    }

    // 元组
    def p = ("John", "Adams")

    println(p._1)
    println(p._2)
    p match {
      case (firstName, lastName) =>
        println("First name is: " + firstName)
        println("Last name is: " + lastName)
    }
  }

  /**
   * 模式 5 替代迭代器模式
   */
  def pattern05(): Unit = {
    val isVowel = Set('a', 'e', 'i', 'o', 'u')
    println(isVowel('a'))
    println(isVowel('z'))

    def vowelsInWord(word: String) = word.filter(isVowel).toSet

    println(vowelsInWord("onomotopeia"))
    println(vowelsInWord("yak"))

    def prependHello(names: Seq[String]) = names.map(name => "Hello, " + name)

    println(prependHello(Vector("Mike", "John", "Joe")))

    def sumSequence(sequence: Seq[Int]) =
      if (sequence.isEmpty) 0 else sequence.reduce((acc, curr) => acc + curr)

    println(sumSequence(Vector(1, 2, 3, 4, 5)))

    // 范例代码：序列推导
    case class Person(name: String, address: Address)
    case class Address(zip: Int)

    def generateGreetings(people: Seq[Person]) =
      for (Person(name, address) <- people if isCloseZip(address.zip))
        yield "Hello, %s, and welcome to the Lambda Bar And Grille!".format(name)

    def isCloseZip(zipCode: Int) = zipCode == 19123 || zipCode == 19103
  }

  /**
   * 模式 6 替代模板方法模式
   */
  def pattern06(): Unit = {
    def makeGradeReporter(numToLetter: Double => String, printGradeReport: Seq[String] => Unit) =
      (grades: Seq[Double]) => printGradeReport(grades.map(numToLetter))

    def fullGradeConverter(grade: Double) = {
      if (grade <= 5.0 && grade > 4.0) "A"
      else if (grade <= 4.0 && grade > 3.0) "B"
      else if (grade <= 3.0 && grade > 2.0) "C"
      else if (grade <= 2.0 && grade > 0.0) "D"
      else if (grade == 0.0) "F"
      else "N/A"
    }

    def printHistogram(grades: Seq[String]) = {
      val grouped = grades.groupBy(identity)
      val counts = grouped.map(kv => (kv._1, kv._2.size)).toSeq.sorted
      for (count <- counts) {
        val stars = "*" * count._2
        println("%s: %s".format(count._1, stars))
      }
    }

    val fullGradeReporter = makeGradeReporter(fullGradeConverter, printHistogram)
    val sampleGrades = Vector(5.0, 4.0, 4.4, 2.2, 3.3, 3.5)
    fullGradeReporter(sampleGrades)

    def plusMinusGradeConverter(grade: Double) = {
      if (grade <= 5.0 && grade > 4.7) "A"
      else if (grade <= 4.7 && grade > 4.3) "A-"
      else if (grade <= 4.3 && grade > 4.0) "B+"
      else if (grade <= 4.0 && grade > 3.7) "B"
      else if (grade <= 3.7 && grade > 3.3) "B-"
      else if (grade <= 3.3 && grade > 3.0) "C+"
      else if (grade <= 3.0 && grade > 2.7) "C"
      else if (grade <= 2.7 && grade > 2.3) "C-"
      else if (grade <= 2.3 && grade > 0.0) "D"
      else if (grade == 0.0) "F"
      else "N/A"
    }

    def printAllGrades(grades: Seq[String]) = for (grade <- grades) println("Grade is: " + grade)

    val plusMinusGradeReporter = makeGradeReporter(plusMinusGradeConverter, printAllGrades)
    plusMinusGradeReporter(sampleGrades)
  }

  /**
   * 模式 7 替代策略模式
   */
  def pattern07(): Unit = {
    case class Person(firstName: Option[String], middleName: Option[String], lastName: Option[String])

    def isFirstNameValid(person: Person) = person.firstName.isDefined

    def isFullNameValid(person: Person) = person match {
      case Person(firstName, middleName, lastName) => firstName.isDefined && middleName.isDefined && lastName.isDefined
    }

    def personCollector(isValid: Person => Boolean) = {
      var validPeople = Vector[Person]()
      (person: Person) => {
        if (isValid(person)) validPeople = validPeople :+ person
        validPeople
      }
    }

    val singleNameValidCollector = personCollector(isFirstNameValid)
    val fullNameValidCollector = personCollector(isFullNameValid)

    val p1 = Person(Some("John"), Some("Quincy"), Some("Adams"))
    val p2 = Person(Some("Mike"), None, Some("Linn"))
    val p3 = Person(None, None, None)

    println(singleNameValidCollector(p1))
    println(singleNameValidCollector(p2))
    println(singleNameValidCollector(p3))
    println(fullNameValidCollector(p1))
    println(fullNameValidCollector(p2))
    println(fullNameValidCollector(p3))
  }

  /**
   * 模式 8 替代空对象
   */
  def pattern08(): Unit = {
    def aSome = Some("foo")

    def aNone = None

    println(aSome.getOrElse("default value"))
    println(aNone.getOrElse("default value"))
    println(aSome.map(s => s.toUpperCase))

    // 范例代码：默认值
    def aMap = Map(1 -> "Hello", 2 -> "Aloha")

    println(aMap.get(1))
    println(aMap.get(3))
    println(aMap.getOrElse(3, "Default Greeting"))

    case class Person(firstName: String = "John", lastName: String = "Doe")
    val nullPerson = Person()

    def fetchPerson(people: Map[Int, Person], id: Int) = people.getOrElse(id, nullPerson)

    val joe = Person("Joe", "Smith")
    val jack = Person("Jack", "Brown")
    val somePeople = Map(1 -> joe, 2 -> jack)
    println(fetchPerson(somePeople, 1))
    println(fetchPerson(somePeople, 3))

    // 范例代码：从无到有
    def vecFoo = Vector("foo")

    def someFoo = Some("foo")

    def someBar = Some("bar")

    def aNone2 = None

    println(for (theFoo <- vecFoo) yield theFoo)
    println(for (theFoo <- someFoo) yield theFoo)
    println(for (theFoo <- someFoo; theBar <- someBar) yield (theFoo, theBar))
    println(for (theFoo <- someFoo; theNone <- aNone2) yield (theFoo, theNone))

    def buildPerson(firstNameOption: Option[String], lastNameOption: Option[String]) =
      (for (firstName <- firstNameOption;
            lastName <- lastNameOption)
      yield Person(firstName, lastName)).getOrElse(Person("John", "Doe"))

    println(buildPerson(Some("Mike"), Some("Linn")))
    println(buildPerson(Some("Mike"), None))
  }

  /**
   * 模式 9 替代装饰器模式
   */
  def pattern09(): Unit = {
    def add(a: Int, b: Int) = a + b

    def substract(a: Int, b: Int) = a - b

    def multiply(a: Int, b: Int) = a * b

    def divide(a: Int, b: Int) = a / b

    def makeLogger(calcFn: (Int, Int) => Int) =
      (a: Int, b: Int) => {
        val result = calcFn(a, b)
        println("Result is: " + result)
        result
      }

    val loggingAdd = makeLogger(add)
    val loggingSubstract = makeLogger(substract)
    val loggingMultiply = makeLogger(multiply)
    val loggingDivide = makeLogger(divide)

    loggingAdd(2, 3)
    loggingSubstract(2, 3)
  }

  /**
   * 模式 10 替代访问者模式
   */
  def pattern10(): Unit = {
    // 范例代码：可扩展的 Person
    trait Person {
      def fullName: String

      def firstName: String

      def lastName: String

      def houseNum: Int

      def street: String
    }

    class SimplePerson(val firstName: String, val lastName: String, val houseNum: Int, val street: String) extends Person {
      override def fullName: String = firstName + " " + lastName
    }

    val simplePerson = new SimplePerson("Mike", "Linn", 123, "Fake St.")
    println(simplePerson.fullName)

    implicit class ExtendedPerson(person: Person) {
      def fullAddress: String = person.houseNum + " " + person.street
    }

    println(simplePerson.fullAddress)

    class ComplexPerson(name: Name, address: Address) extends Person {
      override def fullName: String = name.firstName + " " + name.lastName

      override def firstName: String = name.firstName

      override def lastName: String = name.lastName

      override def houseNum: Int = address.houseNum

      override def street: String = address.street
    }
    class Address(val houseNum: Int, val street: String)
    class Name(val firstName: String, val lastName: String)

    val name = new Name("Mike", "Linn")
    val address = new Address(123, "Fake St.")
    val complexPerson = new ComplexPerson(name, address)
    println(complexPerson.fullName)
    println(complexPerson.fullAddress)

    // 范例代码：可扩展的几何形状
    trait PerimeterShapes {
      trait Shape {
        def perimeter: Double
      }

      class Circle(radius: Double) extends Shape {
        override def perimeter: Double = 2 * Math.PI * radius
      }

      class Rectangle(width: Double, height: Double) extends Shape {
        override def perimeter: Double = 2 * width + 2 * height
      }
    }

    object FirstShapeExample extends PerimeterShapes {
      val aCircle = new Circle(4)
      val aRectangle = new Rectangle(2, 2)
    }

    println(FirstShapeExample.aCircle.perimeter)
    println(FirstShapeExample.aRectangle.perimeter)

    trait AreaShapes extends PerimeterShapes {
      trait Shape extends super.Shape {
        def area: Double
      }

      class Circle(radius: Double) extends super.Circle(radius) with Shape {
        override def area: Double = Math.PI * radius * radius
      }

      class Rectangle(width: Double, height: Double) extends super.Rectangle(width, height) with Shape {
        override def area: Double = width * height
      }
    }

    object SecondShapeExample extends AreaShapes {
      val someShapes = Vector(new Circle(4), new Rectangle(2, 2))
    }

    println(for (shape <- SecondShapeExample.someShapes) yield shape.perimeter)
    println(for (shape <- SecondShapeExample.someShapes) yield shape.area)

    trait MorePerimeterShapes extends PerimeterShapes {
      class Square(side: Double) extends Shape {
        override def perimeter: Double = 4 * side
      }
    }

    trait MoreAreaShapes extends AreaShapes with MorePerimeterShapes {
      class Square(side: Double) extends super.Square(side) with Shape {
        def area = side * side
      }
    }

    object ThirdShapeExample extends MoreAreaShapes {
      val someMoreShapes = Vector(new Circle(4), new Rectangle(2, 2), new Square(4))
    }

    println(for (shape <- ThirdShapeExample.someMoreShapes) yield shape.perimeter)
    println(for (shape <- ThirdShapeExample.someMoreShapes) yield shape.area)
  }

  // 模式 11 替代依赖注入
  // 如果在 pattern11 内声明的话，执行时会报错：Illegal field modifiers in class com/zerox/chapter3/Chapter3Test$MovieServiceComponentImpl$1
  case class Movie(movieId: String, title: String)

  case class Video(movieId: String)

  case class DecoratedMovie(movie: Movie, video: Video)

  trait MovieDaoComponent {
    trait MovieDao {
      def getMovie(id: String): Movie
    }
  }

  trait FavoritesServiceComponent {
    trait FavoritesService {
      def getFavoriteVideos(id: String): Vector[Video]
    }
  }

  /**
   * 模式 11 替代依赖注入
   */
  def pattern11(): Unit = {
    // 范例代码：收藏的视频
    trait MovieDaoComponentImpl extends MovieDaoComponent {
      class MovieDaoImpl extends MovieDao {
        override def getMovie(id: String): Movie = Movie("42", "A Movie")
      }
    }

    trait FavoritesServiceComponentImpl extends FavoritesServiceComponent {
      class FavoritesServiceImpl extends FavoritesService {
        override def getFavoriteVideos(id: String): Vector[Video] = Vector(Video("1"))
      }
    }

    trait MovieServiceComponentImpl {
      this: MovieDaoComponent with FavoritesServiceComponent =>

      val favoritesService: FavoritesService
      val movieDao: MovieDao

      class MovieServiceImpl {
        def getFavoriteDecoratedMovies(userId: String): Vector[DecoratedMovie] =
          for (
            favoriteVideo <- favoritesService.getFavoriteVideos(userId);
            movie = movieDao.getMovie(favoriteVideo.movieId)
          ) yield DecoratedMovie(movie, favoriteVideo)
      }
    }

    object ComponentRegistry extends MovieServiceComponentImpl with FavoritesServiceComponentImpl with MovieDaoComponentImpl {
      val favoritesService = new FavoritesServiceImpl
      val movieDao = new MovieDaoImpl

      val movieService = new MovieServiceImpl
    }

    val movieService = ComponentRegistry.movieService
    println(movieService.getFavoriteDecoratedMovies(""))

    // 范例代码：测试桩
    trait MovieDaoComponentTestImpl extends MovieDaoComponent {
      class MovieDaoTestImpl extends MovieDao {
        override def getMovie(id: String): Movie = Movie("43", "A Test Movie")
      }
    }

    trait FavoriteServiceComponentTestImpl extends FavoritesServiceComponent {
      class FavoriteServiceTestImpl extends FavoritesService {
        override def getFavoriteVideos(id: String): Vector[Video] = Vector(Video("2"))
      }
    }

    object TestComponentRegistry extends MovieServiceComponentImpl with FavoriteServiceComponentTestImpl with MovieDaoComponentTestImpl {
      val favoritesService = new FavoriteServiceTestImpl
      val movieDao = new MovieDaoTestImpl

      val movieSerive = new MovieServiceImpl
    }

    println(TestComponentRegistry.movieSerive.getFavoriteDecoratedMovies(""))
  }

  def main(args: Array[String]): Unit = {
    pattern01()
    println("-------------")
    pattern02()
    println("-------------")
    pattern03()
    println("-------------")
    pattern04()
    println("-------------")
    pattern05()
    println("-------------")
    pattern06()
    println("-------------")
    pattern07()
    println("-------------")
    pattern08()
    println("-------------")
    pattern09()
    println("-------------")
    pattern10()
    println("-------------")
    pattern11()
  }
}