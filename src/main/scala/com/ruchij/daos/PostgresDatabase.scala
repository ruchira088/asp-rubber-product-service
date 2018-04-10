package com.ruchij.daos

import org.postgresql.Driver

object PostgresDatabase extends Database[Driver]
{
  override def urlPrefix: String = "postgresql"
}