package scalatest.tutorial.matcher.custom

import java.nio.file.{Files, Path}

import org.scalatest.enablers.{Length, Writability, Readability, Existence}
import org.scalatest.matchers.{MatchResult, Matcher}

trait PathMatchers {

  implicit object PathEnabler
      extends Existence[Path] with Readability[Path] with Writability[Path] with Length[Path] {

    override def exists(path: Path): Boolean = Files.exists(path)

    override def isReadable(path: Path): Boolean = Files.isReadable(path)

    override def isWritable(path: Path): Boolean = Files.isWritable(path)

    override def lengthOf(path: Path): Long = path.getNameCount
  }
}

object PathMatchers extends PathMatchers