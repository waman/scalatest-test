package scalatest.tutorial

import org.scalatest.FlatSpec


class CalculatorSpec extends FlatSpec{

  "Calculator" should "multiplyで3と4の乗算結果が取得できる" in {
    val calc = new Calculator
    val expected = 12
    val actual = calc.multiply(3, 4)
    assert(actual == expected)
  }

  it should "multiplyで5と7の乗算結果が取得できる" in {
    val calc = new Calculator
    assertResult(35) {
      calc.multiply(5, 7)
    }
  }

  it should "divideで3と2の除算結果が取得できる" in {
    val calc = new Calculator
    val expected = 1.5f
    val actual = calc.divide(3, 2)
    assert(actual == expected)
  }

  it should "divideで5と0のときIllegalArgumentExceptionを送出する" in {
    val calc = new Calculator
    intercept[IllegalArgumentException] {
      calc.divide(5, 0)
    }
  }
}
