package scalatest.tutorial.matcher

import org.scalatest.{Matchers, FlatSpec}

class StringRegexMatcherSpec extends FlatSpec with Matchers{

  "startWith" should "指定した文字列で始まる" in {
    val string = "Hello, ScalaTest world!"

    string should startWith ("Hello")
    string should startWith regex "Hel*o"
    string should startWith regex ("(.*)," withGroup "Hello")
    string should startWith regex ("(.*), (.*) " withGroups ("Hello", "ScalaTest"))
  }

  "include" should "指定した文字列を含む" in {
    val string = "Hello, ScalaTest world!"

    string should include ("ScalaTest")
    string should include regex "[Tt]est"
    string should include regex ("\\s(.*)\\s" withGroup "ScalaTest")
  }

  "endWith" should "指定した文字列で終わる" in {
    val string = "Hello, ScalaTest world!"

    string should endWith ("world!")
    string should endWith regex "wo.ld."
    string should endWith regex ("(\\w*)!" withGroup "world")
  }

  "fullyMatch" should "指定した正規表現にマッチする" in {
    val string = "Hello, ScalaTest world!"

    string should fullyMatch regex "Hello, .* world!"
    string should fullyMatch regex ("(.*), (.*) (.*)!" withGroups("Hello", "ScalaTest", "world"))
  }

}
