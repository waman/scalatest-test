package scalatest.tutorial.specs

import org.scalatest.Spec

import scalatest.tutorial.Calculator

class CalculatorObjectSpec extends Spec{

  object `Calculator ` {
    object `multiplyメソッド ` {
      def `3と4の乗算結果が取得できる ` {
        val calc = new Calculator
        assert(calc.multiply(3, 4) == 12)
      }

      def `5と7の乗算結果が取得できる ` {
        val calc = new Calculator
        assert(calc.multiply(5, 7) == 35)
      }
    }

    object `divideメソッド ` {
      def `3と2の除算結果が取得できる ` {
        val calc = new Calculator
        assert(calc.divide(3, 2) == 1.5f)
      }

      def `5と0のときIllegalArgumentExceptionを送出する ` {
        val calc = new Calculator
        intercept[IllegalArgumentException] {
          calc.divide(5, 0)
        }
      }
    }
  }
}
