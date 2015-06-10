package scalatest.tutorial

import scala.collection.mutable
import org.scalatest.{GivenWhenThen, FlatSpec}

class ListBufferSpec extends FlatSpec with GivenWhenThen{

  "ListBuffer" should "要素が2つ追加された状態で要素をremoveするとsizeが1となる" in {
    // SetUp
    Given("事前準備")
//    val sut = mutable.ListBuffer.empty[String]
//    sut += "Hello"
//    sut += "World"
    val sut = mutable.ListBuffer("Hello", "World")
    note("ミュータブルだよ")

    // Exercise
    When("実行")
    sut.remove(0)

    // Verify
    Then("検証")
    assert(sut.size == 1)
    assert(sut.head == "World")

    // tearDown
    info("後処理")
    alert("警告があったらalert")
  }
}
