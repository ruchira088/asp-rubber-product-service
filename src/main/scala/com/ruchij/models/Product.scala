package com.ruchij.models

case class Product(
      id: String,
      name: String,
      description: String,
      label: Option[String]
)