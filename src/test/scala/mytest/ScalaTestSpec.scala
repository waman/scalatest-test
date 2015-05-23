package mytest

import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.exceptions.{TestCanceledException, TestFailedException}
import org.scalatest.{FlatSpec, Matchers}

class ScalaTestSpec extends FlatSpec with Matchers with TypeCheckedTripleEquals{

  "Calculator" should "multiplyで3と4の乗算結果が取得できる" in {
    val calc = new Calculator
    assertResult(12){
      calc.multiply(3, 4)
    }
  }

  "==" should "Double値とFloat値を比較できる" in {
    "1.5d == 1.5f" should compile
    assert(1.5d == 1.5f)
  }

  "===" should "TypeCheckedTripleEqualsならDouble値とFloat値を比較できない（コンパイル・エラー）" in {
    "1.5d === 1.5f" shouldNot compile
  }

  "Calculator" should "divideで3と2の除算結果が取得できる" in {
    val calc = new Calculator
    val expected = 1.5d
    val actual = calc.divide(3, 2)
    assert(actual == expected)
    //assert(actual === expected)  // コンパイル・エラー
  }

  "assert, assume" should "Boolean値を引数にとってfalseなら例外を投げる" in {
    intercept[TestFailedException]{ assert(false) }
    intercept[TestCanceledException]{ assume(false) }
  }

  "fail, cancel" should "引数をとらずに例外を投げる" in {
    intercept[TestFailedException]{ fail() }
    intercept[TestCanceledException]{ cancel() }
  }
}