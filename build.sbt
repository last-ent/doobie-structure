val CirceVersion = "0.11.1"
val sqliteJdbcVersion = "3.7.2"
lazy val doobieVersion = "0.6.0"

lazy val root = (project in file("."))
  .settings(
    organization := "com.example",
    name := "quickstart",
    version := "0.0.1-SNAPSHOT",
    scalaVersion in ThisBuild := "2.12.8",
    scalacOptions ++= Seq("-Ypartial-unification"),
    libraryDependencies ++= Seq(
      "io.circe"        %% "circe-generic"       % CirceVersion,
      "org.tpolecat" %% "doobie-core"     % doobieVersion,
      "org.tpolecat" %% "doobie-postgres" % doobieVersion,
      "org.xerial" % "sqlite-jdbc" % sqliteJdbcVersion,
      "org.typelevel" %% "cats-core" % "1.6.0",
      "org.typelevel" %% "cats-effect" % "1.3.0"
    ),
    addCompilerPlugin("org.spire-math" %% "kind-projector"     % "0.9.6"),
    addCompilerPlugin("com.olegpy"     %% "better-monadic-for" % "0.2.4")
  )

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-language:higherKinds",
  "-language:postfixOps",
  "-feature",
  "-Ypartial-unification",
  "-Xfatal-warnings",
)
