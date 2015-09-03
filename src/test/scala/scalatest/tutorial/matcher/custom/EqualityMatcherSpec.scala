package scalatest.tutorial.matcher.custom

import org.scalactic.{Equivalence, Equality}
import org.scalatest.{FlatSpec, Matchers}
import org.scalactic.StringNormalizations._

class EqualityMatcherSpec extends FlatSpec with Matchers{

  "after" should "指定した等価関係で等価性を検証する" in {
    "ScalaTest" should equal ("SCALATEST") (after being upperCased)
    "ScalaTest" should equal ("scalatest") (after being lowerCased)
    "ScalaTest" should equal ("\tScalaTest\t") (after being trimmed)
  }

  "determined" should "指定した等価関係で等価性を検証する" in {
    val kana = new Equivalence[String]{
      override def areEquivalent(a: String, b: String): Boolean = ???
    }

//    "ScalaTest" should equal ("SCALATEST") (determined by kana)
  }

  "decided" should "指定した等価関係で等価性を検証する" in {
    val kana = new Equality[String]{
      override def areEqual(s: String, b: Any): Boolean = false
    }

    "ScalaTest" should equal ("SCALATEST") (decided by kana)
  }

//  "kana" should "カタカナをひらがなに変換する" in {
//    (12354 to 12454).map(_.).foreach(println)
//  }
}
