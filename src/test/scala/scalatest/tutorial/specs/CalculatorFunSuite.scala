package scalatest.tutorial.specs

import org.scalatest.FunSuite

import scalatest.tutorial.Calculator

class CalculatorFunSuite extends FunSuite{

  test("Calculatorはmultiplyで3と4の乗算結果が取得できる"){
    val calc = new Calculator
    assert(calc.multiply(3, 4) == 12)
  }

  test("Calculatorはmultiplyで5と7の乗算結果が取得できる"){
    val calc = new Calculator
    assert(calc.multiply(5, 7) == 35)
  }

  test("Calculatorはdivideで3と2の除算結果が取得できる"){
    val calc = new Calculator
    assert(calc.divide(3, 2) == 1.5f)
  }

  test("Calculatorはdivideで5と0のときIllegalArgumentExceptionを送出する"){
    val calc = new Calculator
    intercept[IllegalArgumentException]{
      calc.divide(5, 0)
    }
  }
}
