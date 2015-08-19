package scalatest.tutorial.matcher

import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, FlatSpec}

class CodeSnippetMatcherSpec extends FlatSpec with Matchers with TypeCheckedTripleEquals{

  "compile" should "コード断片がコンパイルできる" in {
    "var i = 1 ; i = 2" should compile
    "val i = 1 ; i = 2" shouldNot compile
  }

  "shouldNot typeCheck" should "コード断片がコンパイル時に型チェックを通らない" in {
    "val s : String = 10" shouldNot typeCheck
    "10 === 10.0" shouldNot typeCheck
  }
}
