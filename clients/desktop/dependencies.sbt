libraryDependencies ++=
  "org.scala-js" %%% "scalajs-dom" % "0.9.1" ::
    "com.github.japgolly.scalacss" %%% "ext-react" % "0.5.1" ::
    "com.github.japgolly.scalajs-react" %%% "core" % Versions.`scalajs-react` ::
    "com.github.japgolly.scalajs-react" %%% "extra" % Versions.`scalajs-react` ::
    Nil

jsDependencies ++=
  ("org.webjars.bower" % "react" % Versions.React / "react-with-addons.js" minified "react-with-addons.min.js" commonJSName "React") ::
    ("org.webjars.bower" % "react" % Versions.React / "react-dom.js" minified "react-dom.min.js" dependsOn "react-with-addons.js" commonJSName "ReactDOM") ::
    Nil
