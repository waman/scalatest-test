package scalatest.tutorial.matcher.custom

import java.nio.file.{Files, Paths}

import org.scalatest.exceptions.TestFailedException
import org.scalatest.{FlatSpec, Matchers}

class PathEnablerSpec extends FlatSpec with Matchers with CustomMatchers{

  "Matcher" should "カスタムマッチャーを作る" in {
    val path = Paths.get("/path/to/some/zip/file.zip")
    path should endWithExtension("zip")

    val ex1 = the [TestFailedException] thrownBy { path should endWithExtension("txt") }
    ex1.getMessage should include ("ファイル file.zip の拡張子が txt ではありません。")

    val ex2 = the [TestFailedException] thrownBy { path shouldNot endWithExtension("zip") }
    ex2.getMessage should include ("ファイル file.zip の拡張子が zip です。")
  }

  "Existence" should "existで検証を行う" in {
    val path = Files.createTempFile(null, null)
    path should exist

    Files.delete(path)
    path should not (exist)
  }

  "Readability" should "readableで検証を行う" in {
    val path = Files.createTempFile(null, null)
    path should be (readable)

    Files.delete(path)
    path should not be readable
  }

  "Writability" should "writableで検証を行う" in {
    val path = Files.createTempFile(null, null)
    path should be (writable)

    Files.delete(path)
    path should not be writable
  }

  "Length" should "have lengthで検証を行う" in {
    val path = Paths.get("/path/to/some/file")
    path should have length 4
  }

//  "Sequencing" should "containsInOrderなどで検証を行う" in {
//    implicit object PathSequencing extends Sequencing[Path] {
//
//      override def containsInOrder(path: Path, seq: Seq[Any]): Boolean = ???
//
//      override def containsInOrderOnly(sequence: Path, eles: Seq[Any]): Boolean = ???
//
//      override def containsTheSameElementsInOrderAs(path: Path, seq: GenTraversable[Any]): Boolean =
//        path zip seq.seq
//
//      implicit def path2Seq(path:Path):Seq[Path] = ???
//    }
//
//    val path = Paths.get("/user/local/bin")
//    path should have length 3
//  }
}
