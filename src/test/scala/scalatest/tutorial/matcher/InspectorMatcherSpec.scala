package scalatest.tutorial.matcher

import org.scalatest.{Matchers, FlatSpec}

class InspectorMatcherSpec extends FlatSpec with Matchers{

  "all" should "すべての要素が条件を満たす" in {
    val list = List(1, 3, 5, 7, 9)
    all(list) should be > 0
    all(list) shouldNot be > 10
//    all(list) should be <= 10 と同じ
//    all(list) should be > 6  // 失敗する
  }

  "every" should "allと同じ（ただし失敗時にすべての要素を列挙する）" in {
    val list = List(1, 3, 5, 7, 9)
    every(list) should be > 0
    every(list) shouldNot be > 10
    //    every(list) should be <= 10 と同じ
    //    every(list) should be > 6  // 失敗する
  }

  "no" should "すべての要素が条件を満たさない" in {
    val list = List(1, 3, 5, 7, 9)
    no(list) should be < 0
    no(list) shouldNot be > 0
//    no(list) should be <= 0  と同じ
  }

  "exactly" should "指定された個数の要素だけ条件を満たす" in {
    val list = List(1, 3, 5, 7, 9)
    exactly(3, list) should be > 4
    exactly(2, list) shouldNot be > 4
    //    exactly(2, list) should be <= 4 と同じ
  }

  "atLeast" should "少なくともいくつかの要素が条件を満たす" in {
    val list = List(1, 3, 5, 7, 9)
    atLeast(2, list) should be > 6
    atLeast(2, list) shouldNot be > 6
//    atLeast(2, list) should be <= 6と同じ
  }

  "atMost" should "多くともいくつかの要素しか条件を満たさない" in {
    val list = List(1, 3, 5, 7, 9)
    atMost(3, list) should be > 4
    atMost(3, list) shouldNot be > 4
    //    atMost(3, list) should be <= 4 と同じ
  }

  "between" should "条件に合う要素の個数が指定された範囲内である" in {
    val list = List(1, 3, 5, 7, 9)
    between(2, 3, list) should be > 6
    between(2, 3, list) shouldNot be > 6
//    between(2, 3, list) should be <= 6 と同じ
  }
}
