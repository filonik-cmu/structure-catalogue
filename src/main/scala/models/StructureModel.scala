package models

import io.circe._, io.circe.generic.semiauto._

final case class ReferenceModel(
  title: String,
  url: String,
)

implicit val referenceModelDecoder: Decoder[ReferenceModel] = deriveDecoder
implicit val referenceModelEncoder: Encoder[ReferenceModel] = deriveEncoder

final case class StructureModel(
  id: Int,
  title: String,
  aliases: List[String],
  description: String,
  references: List[ReferenceModel],
  schema: String
)

implicit val structureModelDecoder: Decoder[StructureModel] = deriveDecoder
implicit val structureModelEncoder: Encoder[StructureModel] = deriveEncoder

