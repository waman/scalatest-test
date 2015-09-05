package scalatest.tutorial.matcher

import org.scalactic.StringNormalizations
import org.scalatest.{Matchers, FlatSpec}

class StringEqualityMatcherSpec extends FlatSpec with Matchers with StringNormalizations{

  "after being lowerCased" should "全ての文字を小文字に変換して比較すると等しい" in {
    "ScalaTest" should equal ("scalatest") (after being lowerCased)
  }

  "after being upperCased" should "全ての文字を大文字に変換して比較すると等しい" in {
    "Html" should equal ("HTML") (after being upperCased)
  }

  "after being trimmed" should "トリム（前後の空白文字を削除）して比較すると等しい" in {
    " Scala\t\r\n" should equal ("Scala") (after being trimmed)
  }

  "after being lowerCased and trimmed" should "lowerCasedとtrimmedを併せて比較する" in {
    " ScalaTest\t\r\n" should equal ("scalatest") (after being lowerCased and trimmed)
  }
}
