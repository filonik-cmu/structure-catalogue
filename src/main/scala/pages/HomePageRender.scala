package pages

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
