name := "sandbox-scala-js"

version := "1.0"

lazy val client =
  (project in file("modules") / "client")

lazy val server =
  (project in file("modules") / "server")
    .dependsOn(client)

lazy val sandbox =
  (project in file("."))
    .aggregate(client, server)
