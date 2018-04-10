package bootstrap.liftweb

import com.ruchij.EnvironmentVariables
import com.ruchij.daos.{DatabaseConnectionManager, PostgresDatabase}
import com.ruchij.models.ServiceInformation
import com.ruchij.web.routes.IndexRoute
import net.liftweb.db.DB
import net.liftweb.util.DefaultConnectionIdentifier

import scala.util.Try

class Boot
{
  def boot(): Unit =
    Try {

      implicit val environmentVariables: Map[String, String] = sys.env

      IndexRoute.init(ServiceInformation())

      init().failed.foreach {
        throw _
      }

    }
      .fold[Unit](
        throwable => {
          System.err.println(throwable.getMessage)
          System.exit(1)
        },
        _ => {
          println("LiftWeb has been successfully initialized.")
        }
      )

  def init()(implicit environmentVariables: EnvironmentVariables): Try[Unit] =
    for {
      connectionManager <- DatabaseConnectionManager.connectionManager(PostgresDatabase)

      _ = DB.defineConnectionManager(DefaultConnectionIdentifier, connectionManager)
    }
    yield (): Unit
}
