import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "play-book-library"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    anorm,
  //  "com.mongodb.casbah" %% "casbah" % "2.1.5-1",
  //  "com.novus" %% "salat" % "1.9.4",
    "commons-codec" % "commons-codec" % "1.6",
     "com.fasterxml.jackson.core" % "jackson-databind" % "2.2.2",
    "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.2.2",
    "se.radley" %% "play-plugins-salat" % "1.3.0"


  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here
    routesImport += "se.radley.plugin.salat.Binders._",
    templatesImport += "org.bson.types.ObjectId",
    resolvers += Resolver.sonatypeRepo("snapshots")
  )

}
