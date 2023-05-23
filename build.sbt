import org.scalajs.linker.interface.ModuleSplitStyle

scalaVersion := "3.2.2"

enablePlugins(ScalaJSPlugin)

scalaJSUseMainModuleInitializer := true

libraryDependencies += "com.raquo" %%% "laminar" % "15.0.0"
libraryDependencies += "com.raquo" %%% "waypoint" % "6.0.0"
libraryDependencies += "com.lihaoyi" %%% "upickle" % "3.1.0"

name := "structure-catalogue"
version := "0.1.0"

scalaJSLinkerConfig ~= {
  _.withModuleKind(ModuleKind.ESModule)
    .withModuleSplitStyle(
      ModuleSplitStyle.SmallModulesFor(List("example")))
}
