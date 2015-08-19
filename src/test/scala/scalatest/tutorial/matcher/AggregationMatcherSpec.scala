package scalatest.tutorial.matcher

import org.scalatest.{Matchers, FlatSpec}

class AggregationMatcherSpec extends FlatSpec with Matchers{

  val jmap: java.util.Map[String, Int] = new java.util.HashMap()
  jmap.put("one", 1)
  jmap.put("two" ,2)
  jmap.put("three", 3)

  "have size" should "サイズが指定された値である" in {
    "ScalaTest" should have size 9
    Seq(1, 1, 2, 3, 5) should have size 5
    Set(1, 1, 2, 3, 5) should have size 4
    Map("one" -> 1, "two" -> 2, "three" -> 3) should have size 3

    // Java のコレクションも OK
    import java.util.Arrays.asList
    asList(1, 2, 3) should have size 3
    jmap should have size 3
  }

  "contain atLeastOneOf/atMostOneOf" should "指定された要素のうち少なくとも/多くとも1つ含む" in {
    List(0, 1, 2) should contain atLeastOneOf (1, 2, 3)  // 共通要素2個○
    List(0, 1, 2) should contain atLeastOneOf (2, 3, 4)  // 共通要素1個○
    List(0, 1, 2) should not contain atLeastOneOf (3, 4, 5)  // 共通要素なし×

    List(0, 1, 2) should contain atMostOneOf (3, 4, 5)  // 共通要素なし○
    List(0, 1, 2) should contain atMostOneOf (2, 3, 4)  // 共通要素1個○
    List(0, 1, 2) should not contain atMostOneOf (1, 2, 3)  // 共通要素2個×
  }

  "contain allOf" should "指定された要素をすべて含む" in {
    List(0, 1, 2) should contain allOf (1, 2)
    List(0, 1, 2) should not contain allOf (1, 2, 3)  // 含まない要素がある×
  }

  "contain only" should "指定された要素のみを含む" in {
    List(1, 1, 2, 3) should contain only (1, 2, 3)
    List(0, 1, 2, 3) should not contain only (1, 2, 3)  // 要素0は指定されてない×
    List(1, 1, 2, 2) should not contain only (1, 2, 3)  // 要素3が含まれてない×
  }

  "contain theSameElementsAs" should "指定されたコレクションと同じ要素を持つ" in {
    List(1, 2, 3) should contain theSameElementsAs Seq(3, 1, 2)
    List(1, 2, 3) should not contain theSameElementsAs (Seq(1, 1, 2, 3))

    // Java と Scala のコレクション
    import java.util.Arrays.asList
    List(1, 2, 3) should be (List(1, 2, 3))
    asList(1, 2, 3) should be (asList(1, 2, 3))

    asList(1, 2, 3) should not be List(1, 2, 3)  // Java と Scala のコレクション×

    import scala.collection.JavaConversions._
    asScalaBuffer(asList(1, 2, 3)) should be (Seq(1, 2, 3))
    asList(1, 2, 3) should be (seqAsJavaList(Seq(1, 2, 3)))
  }
}
