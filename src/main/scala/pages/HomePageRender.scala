package pages

import com.raquo.laminar.api.L.*

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

import example.Routes.*

object HomePageRender {

  @js.native
  @JSImport("stylesheets/home.css", JSImport.Namespace)
  object Css extends js.Any

  def render: HtmlElement = {
    val css: Css.type = Css

    div(
      "Home Page",
      ul(
        Range(1,10).map { id =>
          li(a(href(router.absoluteUrlForPage(ItemPage(id))), s"Item #$id"))
        }
      )
    )
  }

}
