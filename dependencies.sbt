libraryDependencies ++=
  "org.scalactic" %% "scalactic" % Versions.ScalaTest ::
    "org.scala-js" %%% "scalajs-dom" % "0.9.1" ::
    Nil

libraryDependencies ++=
  "org.scalatest" %% "scalatest" % Versions.ScalaTest % Test ::
    Nil

npmDevDependencies in Compile ++=
  "html-webpack-plugin" -> "2.28.0" ::
    Nil