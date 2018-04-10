package com.ruchij.models

import scala.util.Properties

case class ServiceInformation(
      serviceName: String,
      scalaVersion: String,
      javaVersion: String,
      sbtVersion: String
)

object ServiceInformation
{
  import com.eed3si9n.ruchij.BuildInfo._

  def apply(): ServiceInformation = ServiceInformation(name, scalaVersion, Properties.javaVersion, sbtVersion)
}
