package scalatest.tutorial.matcher

import java.io.Serializable

import org.scalatest.{Matchers, FlatSpec}

class AnyMatcherSpec extends FlatSpec with Matchers{

  // Java の equals, JUnit の is, Scala の Any.==
  "equal" should "等価なオブジェクトまたは値であることを検証する" in {
    Array(1, 2) should equal (Array(1, 2))
    Array(1, 2) should === (Array(1, 2))
    Array(1, 2) should be (Array(1, 2))

    Array(1, 2) shouldEqual Array(1, 2)
    Array(1, 2) shouldBe Array(1, 2)
  }

  // Java の ==, JUnit の sameInstance, Scala の AnyRef.eq
  "theSameInstanceAs" should "同一のインスタンスであることを検証する" in {
    val array = Array(1, 2)
    array should be theSameInstanceAs (array)  // ()なしでもOK

    Array(1, 2) should not be theSameInstanceAs (Array(1, 2))
  }

  // null チェック
  "be (null) / not be (null)" should "null値であることを検証する" in {
    val actual:String = null
    actual should be (null)

    "null" should not be (null)  // ()なしでもOK
  }

  // Java の instanceof, JUnit の instanceOf, Scala の isInstanceOf
  "be a" should "あるクラスのインスタンスであることを検証する" in {
    Array(1, 2) should be (a [Serializable])
    Array(1, 2) shouldBe a [Serializable]

    Array(1, 2) should not be a [List[_]]
  }
}
