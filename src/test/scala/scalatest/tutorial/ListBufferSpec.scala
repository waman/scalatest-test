package scalatest.tutorial

import scala.collection.mutable
import org.scalatest.{GivenWhenThen, FlatSpec}

class ListBufferSpec extends FlatSpec with GivenWhenThen{

  "ListBuffer" should "+=で要素を追加するとサイズが1となりgetで取得できる" in{
    // SetUp
    Given("空のListBuffer")
    val sut = mutable.ListBuffer.empty[String]
    // Exercise
    When("要素を1つ追加")
    sut += "Hello"
    // Verify
    Then("サイズが1")
    assert(sut.size == 1)
    And("最初の要素が加えた要素")
    assert(sut.head == "Hello")
  }

  it should "要素が2つ追加された状態で要素をremoveするとsizeが1となる" in {
    // SetUp
    Given("要素が2つのListBuffer")
//    val sut = mutable.ListBuffer.empty[String]
//    sut += "Hello"
//    sut += "World"
    val sut = mutable.ListBuffer("Hello", "World")
    // Exercise
    When("要素を削除")
    sut.remove(0)
    // Verify
    Then("サイズが1")
    assert(sut.size == 1)
    And("最初の要素が2番目に加えられた要素")
    assert(sut.head == "World")
  }
}
