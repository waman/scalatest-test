name := "scalatest-test"

version := "4.4"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.11" % "2.2.4",
  "junit" % "junit" % "4.12",
  "org.hamcrest" % "hamcrest-library" % "1.3"
)


//***** Compile Options *****
javacOptions ++= Seq(
  "-source", "1.8",
  "-target", "1.8",
  "-encoding", "UTF-8"
)

scalacOptions ++= Seq(
  "-Xlint",
  "-deprecation",
  "-unchecked",
  "-feature",
  "-encoding", "UTF-8"
)

fork := true
