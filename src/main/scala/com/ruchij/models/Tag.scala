package com.ruchij.models

import org.joda.time.DateTime

case class Tag(
      id: String,
      createdAt: DateTime,
      name: String,
      label: Option[String],
      description: Option[String]
)