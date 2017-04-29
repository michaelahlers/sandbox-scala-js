import sbt.Project.projectToRef

name := "sandbox-scala-js"

version := "1.0"

lazy val client =
  (project in file("modules") / "client")
    .enablePlugins(ScalaJSPlugin, ScalaJSWeb)
    .settings(
      scalaVersion := Versions.Scala,
      scalacOptions ++= Compiler.scalacOptions,
      scalaJSUseMainModuleInitializer := true
    )

// Client projects (could be many).
lazy val clients = Seq(client)

lazy val server =
  (project in file("modules") / "server")
    .enablePlugins(PlayScala, SbtTwirl)
    .disablePlugins(PlayLayoutPlugin)
    .aggregate(clients.map(projectToRef): _*)
    .settings(
      scalaVersion := Versions.Scala,
      scalacOptions ++= Compiler.scalacOptions,
      scalaJSProjects := clients,
      compile in Compile <<= (compile in Compile)  dependsOn scalaJSPipeline ,
      pipelineStages in Assets := scalaJSPipeline :: Nil,
      pipelineStages := digest :: gzip :: Nil
    )

lazy val sandbox =
  (project in file("."))
    .aggregate(client, server)
