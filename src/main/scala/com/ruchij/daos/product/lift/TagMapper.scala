package com.ruchij.daos.product.lift

import java.util.Date

import com.ruchij.constants.config.LiftMapperConfigs.DEFAULT_STRING_LENGTH
import com.ruchij.models.Tag
import net.liftweb.mapper._
import org.joda.time.DateTime

class TagMapper extends LongKeyedMapper[TagMapper] with IdPK
{
  self =>

  object tagId extends MappedString(self, DEFAULT_STRING_LENGTH)
  object createdAt extends MappedDateTime(self)
  object name extends MappedString(self, DEFAULT_STRING_LENGTH)
  object label extends MappedString(self, DEFAULT_STRING_LENGTH)
  object description extends MappedText(self)

  override def getSingleton: KeyedMetaMapper[Long, TagMapper] = TagMapper
}

object TagMapper extends TagMapper with LongKeyedMetaMapper[TagMapper]
{
  def init(): List[String] = Schemifier.schemify(true, Schemifier.infoF _, TagMapper)

  def transform(tagMapper: TagMapper) =
    Tag(
      tagMapper.tagId.get,
      new DateTime(tagMapper.createdAt.get.getTime),
      tagMapper.name.get,
      Option(tagMapper.label.get),
      Option(tagMapper.description.get)
    )

  def unapply(id: String): Option[Tag] =
    find(By(TagMapper.tagId, id))
      .map(transform)
      .toOption

  def createFromTag(tag: Tag): TagMapper =
    TagMapper.create
      .tagId(tag.id)
      .createdAt(new Date(tag.createdAt.getMillis))
      .name(tag.name)
      .label(tag.label.orNull)
      .description(tag.description.orNull)

}
