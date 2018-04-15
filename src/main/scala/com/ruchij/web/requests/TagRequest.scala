package com.ruchij.web.requests

import com.ruchij.models.Tag
import com.ruchij.utils.GeneralUtils.uuid
import net.liftweb.json.JsonAST.JValue
import com.ruchij.web.utils.Formats._
import org.joda.time.DateTime

case class TagRequest(
      name: String,
      label: Option[String],
      description: Option[String]
)

object TagRequest
{
  def unapply(json: JValue): Option[TagRequest] = json.extractOpt[TagRequest]

  def toTag(tagRequest: TagRequest): Tag =
    Tag(
      uuid(),
      DateTime.now(),
      tagRequest.name,
      tagRequest.label,
      tagRequest.description
    )
}
