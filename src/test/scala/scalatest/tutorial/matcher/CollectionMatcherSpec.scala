package scalatest.tutorial.matcher

import org.scalatest.{Matchers, FlatSpec}

class CollectionMatcherSpec extends FlatSpec with Matchers{

  // Java の contains, JUnit の hasItem
  "have" should "指定された要素を持つことを検証する" in {
    val actual = List("Hello", "ScalaTest", "World", "!")
    actual should contain ("World")
    actual should not contain ("JUnit")  // ()なしでOK

    actual should contain allOf("Hello", "World")
  }
}
