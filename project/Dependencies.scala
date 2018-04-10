import sbt._

object Dependencies
{
  val LIFT_VERSION = "3.2.0"

  lazy val liftWebkit = "net.liftweb" %% "lift-webkit" % LIFT_VERSION

  lazy val liftMapper = "net.liftweb" %% "lift-mapper" % LIFT_VERSION

  lazy val postgresql = "org.postgresql" % "postgresql" % "42.2.2"

  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.4"

  lazy val pegdown = "org.pegdown" % "pegdown" % "1.6.0"
}