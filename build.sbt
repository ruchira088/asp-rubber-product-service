import Dependencies._

lazy val root = (project in file("."))
  .settings(
    inThisBuild(List(
      organization := "com.ruchij",
      scalaVersion := "2.12.5"
    )),
    name := "asp-rubber-product-service",
    libraryDependencies ++= Seq(
      liftWebkit,
      liftMapper, postgresql,

      scalaTest % Test,
      pegdown % Test
    ),
    buildInfoKeys := BuildInfoKey.ofN(name, scalaVersion, sbtVersion),
    buildInfoPackage := "com.eed3si9n.ruchij",
    assemblyJarName in assembly := "asp-rubber-product-service-assembly.jar"
  )

enablePlugins(BuildInfoPlugin, JettyPlugin)

coverageEnabled := true

testOptions in Test +=
  Tests.Argument(TestFrameworks.ScalaTest, "-h", "target/test-results")

addCommandAlias("testWithCoverage", "; clean; test; coverageReport")