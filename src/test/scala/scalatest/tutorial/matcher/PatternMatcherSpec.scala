package scalatest.tutorial.matcher

import org.scalatest.{FlatSpec, Inside, Matchers}

class PatternMatcherSpec extends FlatSpec with Matchers with Inside{

  "inside" should "パターンマッチで抽出したものを検証する" in {
    case class Person(name:String, age:Int, sex:String)
    val me = Person("waman", 100, "male")

    inside(me){ case Person(_, age, _) =>
      age should be >= 20
    }
  }
}
