package scalatest.tutorial.specs

import org.scalatest.FunSpec

import scalatest.tutorial.Calculator

class CalculatorFunSpec extends FunSpec{

  describe("Calculator") {
    describe("multiplyメソッド") {
      it("3と4の乗算結果が取得できる") {
        val calc = new Calculator
        assert(calc.multiply(3, 4) == 12)
      }

      it("5と7の乗算結果が取得できる") {
        val calc = new Calculator
        assert(calc.multiply(5, 7) == 35)
      }
    }

    describe("divideメソッド") {
      it("3と2の除算結果が取得できる") {
        val calc = new Calculator
        assert(calc.divide(3, 2) == 1.5f)
      }

      it("5と0のときIllegalArgumentExceptionを送出する") {
        val calc = new Calculator
        intercept[IllegalArgumentException] {
          calc.divide(5, 0)
        }
      }
    }
  }
}
