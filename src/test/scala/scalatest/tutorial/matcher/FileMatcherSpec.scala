package scalatest.tutorial.matcher

import java.io.File

import org.scalatest.{FlatSpec, Matchers}

class FileMatcherSpec extends FlatSpec with Matchers{

  "exist" should "存在する" in {
    val file = File.createTempFile("exist-", ".txt")
    file should exist

    file.delete()  // ファイルを削除
    file shouldNot exist
    file should not (exist)
  }

  "be readable/writable" should "読み書きができる" in {
    val file = File.createTempFile("rw-", ".txt")

    file should be (readable)
    file should be (writable)

    file shouldBe readable
    file shouldBe writable

    file.delete()  // ファイルを削除
    file should not be readable
    file should not be writable
  }
}
