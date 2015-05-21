package mytest.specs

import mytest.Calculator
import org.scalatest.FreeSpec

class CalculatorFreeSpec extends FreeSpec{

  "Calculator" - {
    "multiply" - {
      "3と4の乗算結果が取得できる" in {
        val calc = new Calculator
        val expected = 12
        val actual = calc.multiply(3, 4)
        assert(actual == expected)
      }

      "5と7の乗算結果が取得できる" in {
        val calc = new Calculator
//        val expected = 12
        val expected = 35
        val actual = calc.multiply(5, 7)
        assert(actual == expected)
      }
    }

    "divide" - {
      "3と2の除算結果が取得できる" in {
        val calc = new Calculator
        val expected = 1.5f
        val actual = calc.divide(3, 2)
        assert(actual == expected)
      }

      "5と0のときIllegalArgumentExceptionを送出する" in {
        val calc = new Calculator
        intercept[IllegalArgumentException] {
          calc.divide(5, 0)
        }
      }
    }
  }
}
