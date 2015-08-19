package scalatest.tutorial.matcher

import org.scalatest.{FlatSpec, Matchers}
import java.util.Arrays.asList

class SequenceMatcherSpec extends FlatSpec with Matchers{

  "have length" should "長さが指定された値である" in {
    Seq(1, 1, 2, 3, 5) should have length 5
    "ScalaTest" should have length 9  // 文字列は文字のシーケンス

    // Set には length は使えないヨ
    "Set(1, 1, 2, 3, 5) should have length 4" shouldNot compile
  }

  "contain inOrder/inOrderOnly" should "指定された要素を/要素だけを順に含む" in {
    // inOrder
    List(1, 2, 3, 3) should contain inOrder (1, 2, 3)  // 回数は気にしなくてOK
    List(1, 2, 4, 3) should contain inOrder (1, 2, 3)  // 他の要素(4)は気にしなくてOK
    List(1, 2, 3, 1) should not contain inOrder (1, 2, 3)
    // List(1, 2, 3) should contain inOrder (1, 2, 3, 3)  引数にダブった値は不可

    // inOrderOnly
    List(1, 2, 3, 3) should contain inOrderOnly (1, 2, 3)
    List(1, 2, 3, 4) should not contain inOrderOnly (1, 2, 3)
  }

  "contain theSameElementsInOrderAs" should "指定されたを要素を同じ順番で持つ" in {
    List(1, 2, 3, 3) should contain theSameElementsInOrderAs Seq(1, 2, 3, 3)
    List(1, 2, 3, 4) should not contain theSameElementsInOrderAs (Seq(1, 2, 3))
    List(1, 2, 3, 3) shouldNot contain theSameElementsInOrderAs Seq(1, 2, 3)

    // java.util.List と Scala の List も比較可
    asList(1, 2, 3, 3) should contain theSameElementsInOrderAs List(1, 2, 3, 3)
    // List(1, 2, 3) should contain theSameElementsInOrderAs asList(1, 2, 3)  逆はダメ
  }

  "be sorted" should "要素がソートされている" in {
    List(1, 2, 3, 4) should be (sorted)
    List(1, 2, 3, 3) shouldBe sorted
    List(1, 2, 3, 1) should not be sorted

    // 配列や java.util.List も OK
    Array(1, 2, 3, 3) shouldBe sorted
    asList(1, 2, 3, 3) shouldBe sorted
  }
}
