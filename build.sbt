name := "sandbox-scala-js"

version := "1.0"

enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)

webpackConfigFile := Some((resourceDirectory in Compile).value / "webpack.config.js")