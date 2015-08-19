package scalatest.tutorial.matcher

import org.scalatest.{Matchers, FlatSpec}

class NumberMatcherSpec extends FlatSpec with Matchers{

  "<, <=, >, >=" should "大小関係を比較する" in {
    // lessThan
    -1 should be < 0
    0 should not be < (0)
    1 should not be < (0)

    // lessThanOrEqualTo
    -1 should be <= 0
    0 should be <= 0
    1 should not be <= (0)

    // greaterThan
    -1 should not be > (0)
    0 should not be > (0)
    1 should be < 0

    // greaterThanOrEqualTo
    -1 should not be >= (0)
    0 should be >= 0
    1 should be < 0
  }

  // JUnit : closeTo
  "( +- )" should "指定した範囲内にある" in {
    -10 should not be (0 +- 5)
    -5 should be (0 +- 5)
    0 should be (0 +- 5)
    5 should be (0 +- 5)
    10 should not be (0 +- 5)

    10 should equal (0 +- 10)
    10 should === (0 +- 10)
    10 shouldBe 0 +- 10
    10 shouldEqual 0 +- 10
  }
}
