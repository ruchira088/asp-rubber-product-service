package com.ruchij.daos

import java.sql.Driver

import scala.reflect.ClassTag

trait Database[A <: Driver]
{
  def className(implicit classTag: ClassTag[A]): String = classTag.runtimeClass.getName

  def urlPrefix: String

  def dbUrl(hostname: String, port: Int, databaseName: String): String =
    s"jdbc:$urlPrefix://$hostname:$port/$databaseName"
}