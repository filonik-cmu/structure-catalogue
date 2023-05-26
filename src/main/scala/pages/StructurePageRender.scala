package pages

import concurrent.ExecutionContext.Implicits.global

import com.raquo.laminar.api.L.*
import io.laminext.fetch.circe.*

import example.Routes.*

import models.*

import scala.scalajs.js

object StructurePageRender {

  def render(structurePageSignal: Signal[StructurePage]): HtmlElement = {
    val content =
      structurePageSignal.map { structure =>
        Fetch
          .get(API.getStructure(structure.id))
          .decodeEither[Error, StructureModel]
          .data
          .map {
            case Right(structure) => renderStructure(structure)
            case Left(error) =>
              div(
                cls := "bg-red-100 text-red-700",
                span(error.getMessage())
              )
          }
      }

    div(child <-- content.map { c =>
      div(child.maybe <-- c.toWeakSignal)
    })
  }

  private def pprint(schema: String) = js.JSON.stringify(js.JSON.parse(schema), space=2)

  private def renderStructure(structure: StructureModel) = {
    val schema = 
      Fetch
        .get(API.getSchema(structure.schema))
        .text
    
    div(
      cls := "flex flex-col",
      h2(structure.title),
      p(structure.description),
      hr(cls := "my-2"),
      h3("Schema"),
      pre(
        cls := "p-2 text-sm bg-neutral-100 dark:bg-neutral-900",
        child.text <-- schema.data.map(pprint)
      )
    )
  }
}
