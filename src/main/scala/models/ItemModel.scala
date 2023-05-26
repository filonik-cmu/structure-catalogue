package models

import io.circe._, io.circe.generic.semiauto._

final case class ItemModel(
  id: Int,
  title: String,
  description: String,
  path: String
)

implicit val itemModelDecoder: Decoder[ItemModel] = deriveDecoder
implicit val itemModelEncoder: Encoder[ItemModel] = deriveEncoder

