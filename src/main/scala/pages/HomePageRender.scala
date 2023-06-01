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
        .get(API.getAllStructures)
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
    div(
      cls := "my-4 max-w-3xl mx-auto",
      child <-- content
    )
  }

  private def renderItems(items: List[ItemModel]) = {
    div(
      cls := "grid grid-cols-3 gap-4",
      items.map(item =>        
        a(
          href := "#" + item.path,
          div(
            cls := "max-w-sm rounded overflow-hidden shadow-lg dark:shadow-black/50",
            div(
              cls := "px-6 py-4",
              div(
                cls := "font-bold text-base mb-2",
                item.title
              ),
              p(
                cls := "text-sm opacity-70 line-clamp-3",
                item.description,
              )
            )
          )
        )
      )
    )
  }

}
