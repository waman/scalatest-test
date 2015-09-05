package scalatest.tutorial.matcher

import org.scalatest.{Matchers, FlatSpec}

class ExceptionMatcherSpec extends FlatSpec with Matchers{

  "an [] should be thrownBy" should "例外が投げられる" in {
    an[ArithmeticException] should be thrownBy 1 / 0
  }

  "have ('message (\"\"))" should "指定されたメッセージを持つことを検証する" in {
    val ex = new RuntimeException("実行時例外")
    ex should have ('message ("実行時例外"))
  }

  "the [] thrownBy" should "投げられた例外を取得できる" in {
    val ex = the [ArithmeticException] thrownBy 1 / 0
    ex should have ('message ("/ by zero"))
  }

  "noException should be thrownBy" should "例外がなげられない" in {
    noException should be thrownBy 0 / 1
  }
}
