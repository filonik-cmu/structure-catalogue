package pages

import concurrent.ExecutionContext.Implicits.global

import com.raquo.laminar.api.L.*
import io.laminext.fetch.circe.*

import example.Routes.*

import models.*

import scala.scalajs.js
import scala.annotation.targetName

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
    
    def titleAndDescription = div(
      h2(structure.title),
      if structure.aliases.length > 0 then
        p(
          cls := "-mt-3 mb-4",
          "Also: ",
          i(structure.aliases.mkString(", ")),
        )
      else
        emptyNode
      ,
      p(structure.description)
    )

    def schemaAndInstance = div(
        cls := "flex flex-row gap-2",
        div(
          cls := "w-1/2",
          h3("Schema"),
          pre(
            cls := "p-2",
            child.text <-- schema.data.map(pprint)
          )
        ),
        div(
          cls := "w-1/2",
          h3("Instance"),
          pre(
            cls := "p-2",
            "TODO"
          )
        )
      )
    
    def references = div(
      h3("References"),
      ul(
        cls := "list-disc",
        structure.references.map(reference =>  
          li(
            a(
              href := reference.url,
              target := "_blank",
              reference.title
            )
          )
        )
      )
    )

    div(
      cls := "mb-4 flex flex-col",
      titleAndDescription,
      hr(cls := "my-4"),
      schemaAndInstance,
      hr(cls := "my-4"),
      references,
    )
  }
}
