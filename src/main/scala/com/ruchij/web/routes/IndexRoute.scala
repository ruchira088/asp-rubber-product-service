package com.ruchij.web.routes

import com.ruchij.models.ServiceInformation
import net.liftweb.http.{JsonResponse, LiftRules}
import net.liftweb.http.LiftRulesMocker.toLiftRules
import net.liftweb.http.rest.RestHelper

class IndexRoute(serviceInformation: ServiceInformation) extends RestHelper
{
  import com.ruchij.web.utils.Formats._

  serve {
    case JsonGet("info" :: Nil, _) => JsonResponse(serviceInformation)
  }
}

object IndexRoute
{
  def init(serviceInformation: ServiceInformation) =
  {
    LiftRules.statelessDispatch.append(new IndexRoute(serviceInformation))
  }
}
