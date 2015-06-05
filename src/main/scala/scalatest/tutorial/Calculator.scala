package scalatest.tutorial

class Calculator {

  def multiply(x:Int, y:Int):Int = x * y

  def divide(x:Int, y:Int):Float = y match {
    case 0 => throw new IllegalArgumentException("divide by zero.")
    case _ => x.toFloat / y.toFloat
  }
}
