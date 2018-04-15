package com.ruchij.daos.product.lift

import com.ruchij.constants.config.LiftMapperConfigs.DEFAULT_STRING_LENGTH
import net.liftweb.mapper._

class ProductMapper extends LongKeyedMapper[ProductMapper] with IdPK
{
  self =>

  object productId extends MappedString(self, DEFAULT_STRING_LENGTH)
  object createdAt extends MappedDateTime(self)
  object name extends MappedString(self, DEFAULT_STRING_LENGTH)
  object description extends MappedText(self)

  override def getSingleton: KeyedMetaMapper[Long, ProductMapper] = ProductMapper
}

object ProductMapper extends ProductMapper with LongKeyedMetaMapper[ProductMapper]
{
  def init(): List[String] = Schemifier.schemify(true, Schemifier.infoF _, ProductMapper)
}