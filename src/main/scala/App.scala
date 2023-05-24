package example

import com.raquo.laminar.api.L.*
import com.raquo.waypoint._

import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

import Routes.{*, given}
import pages.*

@js.native
@JSImport("stylesheets/app.css", JSImport.Namespace)
object Css extends js.Any

object App {
  val css: Css.type = Css

  val splitter = SplitRender[Page, HtmlElement](router.currentPageSignal)
    .collectSignal[ItemPage] { ItemPageRender.render }
    .collectStatic(HomePage) { HomePageRender.render }
 
  def main(args: Array[String]): Unit = {
    lazy val container = dom.document.getElementById("app")

    lazy val myApp = div(
      cls := "h-full flex flex-col",
      PageHeader(),
      mainTag(
        cls := "flex-grow w-full",
        child <-- splitter.signal
      ),
      PageFooter(
        span(cls := "opacity-50", "© 2023")
      ),
    )

    renderOnDomContentLoaded(container, myApp)
  }
}
