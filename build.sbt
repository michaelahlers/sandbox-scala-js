import sbt.Project.projectToRef

name := "sandbox-scala-js"

version := "1.0"

lazy val desktop =
  (project in file("clients") / "desktop")
    .enablePlugins(ScalaJSPlugin, ScalaJSWeb)
    .settings(
      scalaVersion := Versions.Scala,
      scalacOptions ++= Compiler.scalacOptions,
      scalaJSUseMainModuleInitializer := true,
      mainClass in Compile := Some("ahlers.michael.sandbox.scalajs.DesktopApplication")
    )

// Client projects (could be many).
lazy val clients = Seq(desktop)

lazy val server =
  (project in file("."))
    .enablePlugins(PlayScala, SbtTwirl)
    .disablePlugins(PlayLayoutPlugin)
    .aggregate(clients.map(projectToRef): _*)
    .settings(
      scalaVersion := Versions.Scala,
      scalacOptions ++= Compiler.scalacOptions,
      scalaJSProjects := clients,
      compile in Compile <<= (compile in Compile) dependsOn scalaJSPipeline,
      pipelineStages in Assets := scalaJSPipeline :: Nil,
      pipelineStages := digest :: simpleUrlUpdate :: digest :: gzip :: Nil,
      includeFilter in(Assets, LessKeys.less) := "index.less"
    )
