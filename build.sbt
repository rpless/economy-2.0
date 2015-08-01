
lazy val economytwo = project.in(file("."))
  .settings(moduleName := "economytwo")
  .settings(baseSettings)

lazy val server = project
  .settings(moduleName := "server")
  .settings(baseSettings)

lazy val baseSettings = Seq(
  organization := "io.github.rpless",
  version := "0.1.0",
  scalaVersion := "2.11.7",
  libraryDependencies ++= baseDependencies,
  scalacOptions ++= compilerOptions,
  resolvers ++= Seq(
    "Ghost4j releases" at "http://repo.ghost4j.org/maven2/releases",
    "image-io-bintray" at "http://www.mygrid.org.uk/maven/repository"
  )
)

lazy val baseDependencies = Seq(
  "com.github.finagle" %% "finch-core" % "0.7.1",
  "com.github.finagle" %% "finch-argonaut" % "0.7.1",
  "net.sourceforge.tess4j" % "tess4j" % "2.0.0",

  "org.scalatest" %% "scalatest" % "2.2.5" % "test"
)

lazy val compilerOptions = Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-unchecked",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Ywarn-unused-import",
  "-Xfuture",
  "-Xlint"
)
