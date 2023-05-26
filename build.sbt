import org.scalajs.linker.interface.ModuleSplitStyle

scalaVersion := "3.2.2"

enablePlugins(ScalaJSPlugin)

scalaJSUseMainModuleInitializer := true

libraryDependencies += "com.raquo" %%% "laminar" % "15.0.0"
libraryDependencies += "com.raquo" %%% "waypoint" % "6.0.0"
libraryDependencies += "com.lihaoyi" %%% "upickle" % "3.1.0"

libraryDependencies ++= Seq(
  "io.circe" %%% "circe-core",
  "io.circe" %%% "circe-generic",
  "io.circe" %%% "circe-parser",
).map(_ % "0.14.5")

/*
libraryDependencies ++= Seq(
      "io.circe" %% "circe-derivation",
      "io.circe" %% "circe-derivation-annotations",
).map(_ % "0.13.0-M5")
*/

libraryDependencies ++= Seq(
  "io.laminext" %%% "core",
  "io.laminext" %%% "fetch",
  "io.laminext" %%% "fetch-circe",
  "io.laminext" %%% "util",
).map(_ % "0.15.0")

name := "structure-catalogue"
version := "0.1.0"

scalaJSLinkerConfig ~= {
  _.withModuleKind(ModuleKind.ESModule)
    .withModuleSplitStyle(
      ModuleSplitStyle.SmallModulesFor(List("example")))
}
