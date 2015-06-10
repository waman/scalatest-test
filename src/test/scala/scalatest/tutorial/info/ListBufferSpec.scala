package scalatest.tutorial.info

import org.scalatest.{FlatSpec, GivenWhenThen}

import scala.collection.mutable

class ListBufferSpec extends FlatSpec with JapaneseInformer{

  "ListBuffer" should "+=で要素を追加するとサイズが1となりgetで取得できる" in{
    事前準備()
    val sut = mutable.ListBuffer.empty[String]

    実行()
    sut += "Hello"

    検証()
    assert(sut.size == 1)
    assert(sut.head == "Hello")
  }

  it should "要素が2つ追加された状態で要素をremoveするとsizeが1となる" in {
    事前準備("要素が2つのListBuffer")
    val sut = mutable.ListBuffer("Hello", "World")

    実行("要素を削除")
    sut.remove(0)

    検証("サイズが1")
    assert(sut.size == 1)

    また("最初の要素が2番目に加えられた要素")
    assert(sut.head == "World")
  }
}
