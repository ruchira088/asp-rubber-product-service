package com.ruchij.web.utils

import com.ruchij.models.{ServiceInformation, Tag}
import net.liftweb.json._
import org.joda.time.DateTime

import scala.util.Try

object Formats
{
  implicit def defaultFormats: Formats = DefaultFormats + JodaTimeSerializer

  private object JodaDateTime
  {
    def unapply(jValue: JValue): Option[DateTime] =
      jValue match {
        case JString(dateTimeString) => Try(DateTime.parse(dateTimeString)).toOption
        case _ => None
      }
  }

  implicit object JodaTimeSerializer extends Serializer[DateTime]
  {
    private val typeClass: Class[DateTime] = classOf[DateTime]

    override def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), DateTime] =
    {
      case (TypeInfo(`typeClass`, _), JodaDateTime(dateTime)) => dateTime
    }

    override def serialize(implicit format: Formats): PartialFunction[Any, JValue] =
    {
      case dateTime: DateTime => JString(dateTime.toString)
    }
  }

  implicit def serviceInfoJValue(serviceInformation: ServiceInformation): JValue =
    Extraction.decompose(serviceInformation)

  implicit def tagJValue(tag: Tag): JValue =
    Extraction.decompose(tag)
}
