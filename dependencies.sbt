libraryDependencies ++=
  "org.scalactic" %% "scalactic" % Versions.ScalaTest ::
    "org.scala-js" %%% "scalajs-dom" % "0.9.1" ::
    "com.github.japgolly.scalajs-react" %%% "core" % Versions.`scalajs-react` ::
    // "com.github.japgolly.scalajs-react" %%% "extras" % Versions.`scalajs-react` ::
    Nil

libraryDependencies ++=
  "org.scalatest" %% "scalatest" % Versions.ScalaTest % Test ::
    Nil

jsDependencies ++=
  ("org.webjars.bower" % "react" % Versions.React / "react-with-addons.js" minified "react-with-addons.min.js" commonJSName "React") ::
    ("org.webjars.bower" % "react" % Versions.React / "react-dom.js" minified "react-dom.min.js" dependsOn "react-with-addons.js" commonJSName "ReactDOM") ::
    // ("org.webjars.bower" % "react" % Versions.React / "react-dom-server.js" minified "react-dom-server.min.js" dependsOn "react-with-addons.js" commonJSName "ReactDOMServer") ::
    Nil

npmDevDependencies in Compile ++=
  "html-webpack-plugin" -> "2.28.0" ::
    Nil