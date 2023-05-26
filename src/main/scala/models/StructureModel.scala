package models

import io.circe._, io.circe.generic.semiauto._

final case class StructureModel(
  id: Int,
  title: String,
  description: String,
  schema: String
)

implicit val structureModelDecoder: Decoder[StructureModel] = deriveDecoder
implicit val structureModelEncoder: Encoder[StructureModel] = deriveEncoder

