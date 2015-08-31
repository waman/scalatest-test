package scalatest.tutorial.matcher

import java.nio.file.Files

import org.scalatest.{FlatSpec, Matchers}

class JavaBeansMatcherSpec extends FlatSpec with Matchers{

  "be a/an '《symbol》" should "Boolean値のプロパティがtrueである" in {
    val f = Files.createTempFile(null, null).toFile
    f should be a 'file
    f shouldNot be a 'directory

    val dir = Files.createTempDirectory(null).toFile
    dir should be a 'directory
    dir shouldNot be a 'hidden
  }

  "have" should "プロパティが指定された値を持つ" in {
    case class Person(name:String, age:Int, sex:String)
    val me = Person("waman", 100, "male")

    me should have (
      'name ("waman"),
      'age  (100),
      'sex  ("male")
    )
  }
}
