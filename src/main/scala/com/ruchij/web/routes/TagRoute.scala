package com.ruchij.web.routes

import com.ruchij.daos.product.lift.TagMapper
import com.ruchij.web.requests.TagRequest
import com.ruchij.web.utils.Formats._
import net.liftweb.http.{JsonResponse, LiftRules}
import net.liftweb.http.rest.RestHelper

class TagRoute extends RestHelper
{
  serve {
    case JsonGet("tag" :: TagMapper(tag) :: Nil, _) => JsonResponse(tag)

    case JsonPost("tag" :: Nil, (TagRequest(tagRequest), _)) => {
      val tag = TagRequest.toTag(tagRequest)
      TagMapper.createFromTag(tag).save()
      JsonResponse(tag)
    }
  }
}

object TagRoute
{
  def init() =
  {
    LiftRules.statelessDispatch.append(new TagRoute())
  }
}