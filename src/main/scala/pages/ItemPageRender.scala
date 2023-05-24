package pages

import com.raquo.laminar.api.L.*

import example.Routes.*

object ItemPageRender {

  def render(itemPageSignal: Signal[ItemPage]): HtmlElement = 
    div(
      h2(
        "Item Page",
        child.text <-- itemPageSignal.map(itemPage => s" #${itemPage.id}")
      )
    )

}
