package scalatest.tutorial.specs

import scalatest.tutorial.Calculator
import org.scalatest.{DoNotDiscover, Spec}

@DoNotDiscover
class CalculatorObjectSpec extends Spec{

  object `A Calculator` {
    object `multiply method` {
      def `3と4の乗算結果が取得できる ` {
        val calc = new Calculator
        val expected = 12
        val actual = calc.multiply(3, 4)
        assert(actual == expected)
      }

      def `5と7の乗算結果が取得できる ` {
        val calc = new Calculator
//        val expected = 12
        val expected = 35
        val actual = calc.multiply(5, 7)
        assert(actual == expected)
      }
    }

    object `divide method` {
      def `3と2の除算結果が取得できる ` {
        val calc = new Calculator
        val expected = 1.5f
        val actual = calc.divide(3, 2)
        assert(actual == expected)
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
