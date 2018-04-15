package bootstrap.liftweb

import com.ruchij.EnvironmentVariables
import com.ruchij.daos.product.lift.{ProductMapper, TagMapper}
import com.ruchij.daos.{DatabaseConnectionManager, PostgresDatabase}
import com.ruchij.models.ServiceInformation
import com.ruchij.web.routes.{IndexRoute, TagRoute}
import net.liftweb.db.DB
import net.liftweb.util.DefaultConnectionIdentifier

import scala.util.Try

class Boot
{
  def boot(): Unit =
    Try {

      implicit val environmentVariables: Map[String, String] = sys.env

      init().failed.foreach {
        throw _
      }

      IndexRoute.init(ServiceInformation())
      TagRoute.init()
      ProductMapper.init()
      TagMapper.init()

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
