name := """StudentInfoManagement"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)
lazy val myProject = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.7"
libraryDependencies += filters
libraryDependencies ++= Seq(
  javaJdbc,
  "org.mindrot" % "jbcrypt" % "0.3m",
  cache,
  javaWs
)
libraryDependencies += evolutions
libraryDependencies += filters
routesGenerator := InjectedRoutesGenerator

libraryDependencies += "org.postgresql" % "postgresql" % "9.3-1100-jdbc41"
