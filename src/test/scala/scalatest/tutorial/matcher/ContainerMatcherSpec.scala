package scalatest.tutorial.matcher

import org.scalatest.{FlatSpec, Matchers}

class ContainerMatcherSpec extends FlatSpec with Matchers{

  "should be empty / shouldBe empty" should "空である" in {
    None should be (empty)
    "" should be (empty)
    Nil shouldBe empty
    List() shouldBe empty
  }

  // JUnit : hasItem
  "contain" should "指定された要素を持つ" in {
    "ScalaTest" should contain ('T')
    // "ScalaTest" should contain ("T")  // 失敗:文字列の要素は文字
    Some(1) should contain (1)
    List(0, 1, 2) should contain (1)
    Array(0, 1, 2) should contain (1)
    Map("one" -> 1, "two" -> 2, "three" -> 3) should contain ("one" -> 1)

    import java.util.Arrays.asList
    asList(0, 1, 2) should contain (1)
  }

  "contain oneOf/noneOf" should "指定された要素のうち1つだけ含む/1つも含まない" in {
    // Option
    Some(1) should contain oneOf (1, 2, 3)
    Some(1) should contain noneOf (4, 5, 6)

    List(0, 1, 2) should contain oneOf (2, 3, 4)
    List(0, 1, 2) should not contain oneOf (5, 6, 7)
    List(0, 1, 2) should not contain oneOf (1, 2, 3)
  }
}
