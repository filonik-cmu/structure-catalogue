package models

import io.circe._, io.circe.generic.semiauto._

final case class Error(message: String) extends Exception(message)

implicit val errorDecoder: Decoder[Error] = deriveDecoder
implicit val errorEncoder: Encoder[Error] = deriveEncoder
