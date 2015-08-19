package scalatest.tutorial.matcher

import org.scalatest.{Entry, Matchers, FlatSpec}

class MapMatcherSpec extends FlatSpec with Matchers{

  "contain key/value/( -> )" should "指定されたキー/値/エントリーを含む" in {
    val map = Map("one" -> 1, "two" -> 2, "three" -> 3)

    map should contain key "one"
    map should contain value 2
    map should contain ("three" -> 3)
    map should not contain ("four" -> 4)
    map shouldNot contain ("five" -> 5)
  }

  "contain key/value/Entry (for Java map)" should "指定されたキー/値/エントリーを含む" in {
    val map = new java.util.HashMap[String, Int]
    map.put("one", 1)
    map.put("two", 2)
    map.put("three", 3)

    map should contain key "one"
    map should contain value 2
    map should contain (Entry("three", 3))
    map should not contain Entry("four", 4)
  }
}
