package scalatest.tutorial

import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{FlatSpec, Matchers}

class TripleEqualsSpec extends FlatSpec with Matchers with TypeCheckedTripleEquals{

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
}
