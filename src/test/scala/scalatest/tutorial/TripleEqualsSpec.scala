package scalatest.tutorial

import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{DoNotDiscover, FlatSpec}

@DoNotDiscover
class TripleEqualsSpec extends FlatSpec with TypeCheckedTripleEquals{

  "==" should "Double値とFloat値を比較できる" in {
    assertCompiles("1.5d == 1.5f")
    assert(1.5d == 1.5f)

    assertCompiles("1.5d == 2.5f")
    // assert(1.5d == 2.5f)  // アサーション・エラー
  }

  "!=" should "Double値とFloat値を比較できる" in {
    assertCompiles("1.5d != 1.5f")
    // assert(1.5d != 1.5f)  // アサーション・エラー

    assertCompiles("1.5d != 2.5f")
    assert(1.5d != 2.5f)
  }

  "===" should "Double値とFloat値を比較しようとするとコンパイル・エラー" in {
    assertDoesNotCompile("1.5d === 1.5f")
    assertTypeError("1.5d === 1.5f")
    // assert(1.5d === 1.5f)  // コンパイル・エラー
  }

  "!==" should "Double値とFloat値を!==で比較しようとするとコンパイル・エラー" in {
    assertDoesNotCompile("1.5d !== 2.5f")
    assertTypeError("1.5d !== 2.5f")
    //assert(1.5d !== 2.5f)  // コンパイル・エラー

    assertCompiles("1.5d !== 1.5")
    // assert(1.5d !== 1.5)  // アサーション・エラー
  }

  "Calculator" should "divideで3と2の除算結果が取得できる" in {
    val calc = new Calculator
    val expected = 1.5
    val actual = calc.divide(3, 2)
    assert(actual == expected)
    //    assert(actual === expected)  // コンパイル・エラー
  }
}