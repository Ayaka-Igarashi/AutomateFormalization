val scala3Version = "3.1.0"

libraryDependencies += "org.jsoup" % "jsoup" % "1.9.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "scala3_sbt",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"
  )
