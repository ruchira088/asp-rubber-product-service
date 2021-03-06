package com.ruchij.models

import org.joda.time.DateTime

case class Product(
      id: String,
      createdAt: DateTime,
      name: String,
      description: String
)