package mytest.basics

import org.scalatest.exceptions.{TestCanceledException, TestFailedException}
import org.scalatest.{FlatSpec, Matchers}

class BasicAssertionSpec extends FlatSpec with Matchers{

  "assert, assume" should "Boolean値を引数にとってfalseなら例外を投げる" in {
    intercept[TestFailedException]{ assert(false) }
    intercept[TestCanceledException]{ assume(false) }
  }

  "fail, cancel" should "引数をとらずに例外を投げる" in {
    intercept[TestFailedException]{ fail() }
    intercept[TestCanceledException]{ cancel() }
  }
}