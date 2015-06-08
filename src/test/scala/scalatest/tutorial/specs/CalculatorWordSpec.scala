package scalatest.tutorial.specs

import org.scalatest.WordSpec

import scalatest.tutorial.Calculator

class CalculatorWordSpec extends WordSpec{

  trait Fixture{
    val calc = new Calculator
  }

  def provide = afterWord("規定する")

  "Calculator" should provide{
    "multiplyメソッド" which {
      "3と4の乗算結果が取得できる" in new Fixture {
        assert(calc.multiply(3, 4) == 12)
      }

      "5と7の乗算結果が取得できる" in new Fixture {
        assert(calc.multiply(5, 7) == 35)
      }
    }

    "divideメソッド" which {
      "3と2の除算結果が取得できる" in new Fixture {
        assert(calc.divide(3, 2) == 1.5f)
      }

      "5と0のときIllegalArgumentExceptionを送出する" in new Fixture {
        intercept[IllegalArgumentException] {
          calc.divide(5, 0)
        }
      }
    }
  }
}
