package scalatest.tutorial.matcher

import org.scalatest.exceptions.TestFailedException
import org.scalatest.{FlatSpec, Matchers}

class ContainerSyntaxMatcherSpec extends FlatSpec with Matchers{

  "Option.value" should "Optionの値を取り出して検証を行う" in {
    import org.scalatest.OptionValues._

    Some(1).value should be > 0
    a [TestFailedException] should be thrownBy{ None.value }
  }

  "loneElement" should "コレクションの唯一の値を取り出して検証を行う" in {
    import org.scalatest.LoneElement._

    Set(1).loneElement should be > 0
    a [TestFailedException] should be thrownBy{ Set.empty[Int].loneElement }
    a [TestFailedException] should be thrownBy{ Set(1, 2).loneElement }
  }

  "toStream" should "IteratorをStreamに変換して検証を行う" in {
    val ite = List(1, 2, 3).iterator

    ite.toStream should contain (2)
  }
}
