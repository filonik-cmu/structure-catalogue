package pages

import scala.scalajs.js.`import`

object API {
  val BASE = `import`.meta.env.BASE_URL.asInstanceOf[String] + "api/"

  def getAllStructures = BASE + "structures/index.json"
  def getStructure(id: String) = BASE + s"structures/${id}.json"
  def getSchema(id: String) = BASE + s"schemas/${id}.json"
}