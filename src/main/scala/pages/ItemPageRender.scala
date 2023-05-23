package pages

import com.raquo.laminar.api.L.*

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

import example.Routes.*

object ItemPageRender {
  
  @js.native
  @JSImport("stylesheets/item.css", JSImport.Namespace)
  object Css extends js.Any

  def render(itemPageSignal: Signal[ItemPage]): HtmlElement = {
    val css: Css.type = Css

    div(
      "Item Page",
      child.text <-- itemPageSignal.map(itemPage => s" #${itemPage.id}")
    )
  }

}
