package pages

/*
import com.raquo.laminar.api.L.*

import example.Routes.*

object HomePageRender {

  def render: HtmlElement = 
    div(
      h2("Home Page"),
      ul(
        Range(1,10).map { id =>
          li(a(href(router.absoluteUrlForPage(ItemPage(id))), s"Item #$id"))
        }
      )
    )

}
*/

import concurrent.ExecutionContext.Implicits.global

import com.raquo.laminar.api.L.*
import io.laminext.fetch.circe.*

import models.*

object HomePageRender {

  def render: HtmlElement = {
    val content = {
      Fetch
        .get("/api/structures/index.json")
        .decodeEither[Error, List[ItemModel]]
        .data
        .map {
          case Right(items) => renderItems(items)
          case Left(error) =>
            div(
              cls := "bg-red-100 text-red-700 p-4",
              span(error.message)
            )
        }
    }
    div(child <-- content)
  }

  private def renderItems(items: List[ItemModel]) = {
    div(
      cls := "grid grid-cols-3 gap-4",
      items.map(item =>        
        a(
          href := "#" + item.path,
          div(
            cls := "max-w-sm rounded overflow-hidden shadow-lg",
            div(
              cls := "px-6 py-4",
              div(
                cls := "font-bold text-lg mb-2",
                item.title
              ),
              p(
                cls := "text-gray-700 text-base",
                item.description,
              )
            )
          )
        )
      )
    )
  }

}
