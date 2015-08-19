package scalatest.tutorial.matcher

import org.scalatest.{Matchers, FlatSpec}

class LogicalMatcherSpec extends FlatSpec with Matchers{

  "not" should "否定する" in {
    "ScalaTest" should startWith ("Scala")
    "ScalaTest" should not startWith "Java"
    convertToAnyShouldWrapper("ScalaTest").should(startWith.apply("Scala"))
    convertToAnyShouldWrapper("ScalaTest").should(not).startWith("Java")

    // You cannot use "should not" instead of "shouldNot"
    List(1, 2, 3) shouldNot have size 4
    "val s: String = 1" shouldNot compile
    "val s: String = 1" shouldNot typeCheck
    new java.io.File("pom.xml") shouldNot exist
    0 shouldNot be > 1
  }

  "and" should "かつ（論理積）" in {
    val map = Map("one" -> 1, "two" -> 2, "three" -> 3)

    map should (have size 3 and contain key "one")

    convertToAnyShouldWrapper(map).should(
      have.size(3).and(contain).key("one")
    )
  }

  "or" should "または（論理和）" in {
    val map = Map("one" -> 1, "two" -> 2, "three" -> 3)

    map should (have size 3 or contain ("four" -> 4))

    convertToAnyShouldWrapper(map).should(
      have.size(3).or(contain.apply("four" -> 4))
    )
  }
}
