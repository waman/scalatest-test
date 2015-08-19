package scalatest.tutorial.matcher

import java.nio.file.Files

import org.scalatest.{FlatSpec, Inside, Matchers}

class JavaBeansMatcherSpec extends FlatSpec with Matchers with Inside{

  "a/an '《symbol》" should "Boolean値のプロパティがtrueである" in {
    val f = Files.createTempFile(null, null).toFile
    f should be a 'file
    f shouldNot be a 'directory
    f should exist

    val dir = Files.createTempDirectory(null).toFile
    dir should be a 'directory
    dir shouldNot be a 'hidden
  }

  case class Person(name:String, age:Int, sex:String)

  "have" should "プロパティが指定された値を持つ" in {
    val me = Person("waman", 100, "male")

    me should have (
      'name ("waman"),
      'age  (100),
      'sex  ("male")
    )

    convertToAnyShouldWrapper(me).should(have.apply(
      'name.apply("waman"),
      'age.apply(100),
      'sex.apply("male")
    ))
  }

  "inside" should "パターンマッチで抽出したものを検証する" in {
    val me = Person("waman", 100, "male")

    inside(me){ case Person(_, age, _) =>
      age should be >= 20
    }
  }
}
