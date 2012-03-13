import sbt._
import Keys._

object FooscoreBuild extends Build {
  lazy val root = Project(id = "fooscore-parent",
    base = file(".")) aggregate(core)

  lazy val core = Project(id = "fooscore-core",
    base = file("fooscore-core"))
}
