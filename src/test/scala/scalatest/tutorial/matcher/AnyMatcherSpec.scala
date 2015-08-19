package scalatest.tutorial.matcher

import java.io.Serializable

import org.scalatest.{Matchers, FlatSpec}

class AnyMatcherSpec extends FlatSpec with Matchers{

  // Java : equals
  // JUnit : is
  // Scala : Any.==
  "equal" should "等価なオブジェクトまたは値である" in {
    "ScalaTest" should be ("ScalaTest")
    "ScalaTest" should equal ("ScalaTest")
    "ScalaTest" should === ("ScalaTest")

    "ScalaTest" shouldBe "ScalaTest"
    "ScalaTest" shouldEqual "ScalaTest"

    convertToAnyShouldWrapper("ScalaTest").should(be.apply("ScalaTest"))
    convertToAnyShouldWrapper("ScalaTest").should(equal("ScalaTest"))
  }

  // Java : ==
  // JUnit : sameInstance
  // Scala : AnyRef.eq
  "theSameInstanceAs" should "同一のインスタンスである" in {
    val s = "ScalaTest"
    val t = s
    s should be theSameInstanceAs t
    s shouldBe theSameInstanceAs (t)

    "ScalaTest" should not be theSameInstanceAs (new String("ScalaTest"))
  }

  // null チェック
  "be (null) / not be (null)" should "null値である" in {
    val actual:String = null
    actual should be (null)
    actual shouldBe null

    "null" should not be null

    convertToAnyShouldWrapper(actual).should(be.apply(null))
    convertToAnyShouldWrapper("null").should(not).be(null)
  }

  // Java : instanceof
  // JUnit : instanceOf
  // Scala : isInstanceOf
  "be a" should "指定したクラスのインスタンスである" in {
    "ScalaTest" should be (a [Serializable])
    "ScalaTest" shouldBe a [Serializable]

    "ScalaTest" should not be a [List[_]]
  }
}
