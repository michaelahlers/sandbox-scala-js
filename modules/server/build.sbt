lazy val client = (project in file(".") / ".." / "client")

enablePlugins(PlayScala, SbtTwirl)
disablePlugins(PlayLayoutPlugin)

scalaJSProjects := client :: Nil
compile in Compile <<= (compile in Compile) dependsOn scalaJSPipeline
pipelineStages in Assets := scalaJSPipeline :: Nil
pipelineStages := digest :: gzip :: Nil
