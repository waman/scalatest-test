name := "scalatest-test"

version := "1.ex"

scalaVersion := "2.11.6"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"

//***** Compile Options *****
javacOptions ++= Seq(
  "-source", "1.8",
  "-target", "1.8",
  "-encoding", "UTF-8"
)

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8"
)

fork := true
