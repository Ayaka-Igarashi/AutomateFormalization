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


enablePlugins(Antlr4Plugin)

Antlr4 / antlr4Version := "4.9"
Antlr4 / antlr4GenVisitor := false
Antlr4 / antlr4GenListener := false
Antlr4 / sourceDirectory := baseDirectory.value / "antlr4"