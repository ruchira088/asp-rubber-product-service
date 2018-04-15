package com.ruchij.daos

import java.sql.{Connection, DriverManager}

import com.ruchij.EnvironmentVariables
import com.ruchij.constants.EnvValues._
import com.ruchij.utils.ConfigUtils.getEnvValue
import net.liftweb.common.{Box, Empty, Full}
import net.liftweb.db.ConnectionManager
import net.liftweb.util.ConnectionIdentifier

import scala.util.Try

object DatabaseConnectionManager
{
  def connectionManager(database: Database[_], hostname: String, port: Int, databaseName: String)
                       (username: String, password: String): Try[ConnectionManager] =
  Try {
    Class.forName(database.className)

    new ConnectionManager
    {
      override def newConnection(name: ConnectionIdentifier): Box[Connection] =
        Try(DriverManager.getConnection(database.dbUrl(hostname, port, databaseName), username, password))
          .fold[Box[Connection]](_ => Empty, Full(_))

      override def releaseConnection(connection: Connection): Unit =
        connection.close()
    }
  }

  def connectionManager(database: Database[_])
                       (implicit environmentVariables: EnvironmentVariables): Try[ConnectionManager] =
    for {
      dbHostname <- getEnvValue(DB_HOSTNAME)
      dbPort <- getEnvValue(DB_PORT).flatMap(portString => Try(portString.toInt))
      dbName <- getEnvValue(DB_NAME)
      dbUsername <- getEnvValue(DB_USERNAME)
      dbPassword <- getEnvValue(DB_PASSWORD)

      manager <- connectionManager(database, dbHostname, dbPort, dbName)(dbUsername, dbPassword)
    }
    yield manager
}