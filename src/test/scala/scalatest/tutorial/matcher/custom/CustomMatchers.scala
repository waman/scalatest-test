package scalatest.tutorial.matcher.custom

import java.nio.file.{Files, Path}

import org.scalatest.enablers.{Length, Writability, Readability, Existence}
import org.scalatest.matchers.{MatchResult, Matcher}

trait CustomMatchers {
  
  class PathEndsWithExtensionMatcher(expectedExtension:String) extends Matcher[Path]{
    override def apply(left: Path): MatchResult = {
      val filename = left.getFileName.toString
      MatchResult(
        filename.endsWith(expectedExtension),
        s"""ファイル $filename の拡張子が $expectedExtension ではありません。""",
        s"""ファイル $filename の拡張子が $expectedExtension です。"""
      )
    }
  }
  
  def endWithExtension(expectedExtension:String) = new PathEndsWithExtensionMatcher(expectedExtension)

  implicit object PathMatcher
      extends Existence[Path] with Readability[Path] with Writability[Path] with Length[Path] {

    override def exists(path: Path): Boolean = Files.exists(path)

    override def isReadable(path: Path): Boolean = Files.isReadable(path)

    override def isWritable(path: Path): Boolean = Files.isWritable(path)

    override def lengthOf(path: Path): Long = path.getNameCount
  }
}

object CustomMatchers extends CustomMatchers