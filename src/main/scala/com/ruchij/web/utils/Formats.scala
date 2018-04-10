package com.ruchij.web.utils

import com.ruchij.models.ServiceInformation
import net.liftweb.json.{DefaultFormats, Extraction, JValue}

object Formats
{
  implicit def defaultFormats: DefaultFormats = DefaultFormats

  implicit def serviceInfoJValue(serviceInformation: ServiceInformation): JValue =
    Extraction.decompose(serviceInformation)
}
