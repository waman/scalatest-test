package scalatest.tutorial.matcher

import org.scalatest.{FlatSpec, Inside, Matchers}

class PatternMatcherSpec extends FlatSpec with Matchers with Inside{

  case class Person(name:String, age:Int, sex:String)
  val me = Person("waman", 100, "male")

  "matchPattern" should "パターンにマッチしていることを検証する" in {
    me should matchPattern { case Person(_, 100, _) => }
  }

  "inside" should "パターンマッチで抽出したものを検証する" in {
    inside(me){ case Person(_, age, _) =>
      age should be >= 20
    }
  }
}
