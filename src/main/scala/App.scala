package example

import com.raquo.laminar.api.L.*
import com.raquo.waypoint._

import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

//import io.circe.syntax.*
//import io.circe.scalajs.*

import pages.*

import Routes.*

@js.native
@JSImport("stylesheets/app.css", JSImport.Namespace)
object Css extends js.Any

object App {
  val css: Css.type = Css

  val splitter = SplitRender[Page, HtmlElement](router.currentPageSignal)
    .collectStatic(HomePage) { HomePageRender.render }
    .collectSignal[ItemPage] { ItemPageRender.render }
    .collectSignal[StructurePage] { StructurePageRender.render }
 
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

    /*
    // ScalaJsSuite
    // https://github.com/circe/circe/blob/172c9a4d9e14270bab59700d9ffe439462c23db7/modules/tests/js/src/test/scala/io/circe/scalajs/ScalaJsSuite.scala
    val data: Page = HomePage //ItemPage(123)
    val jsonData: js.Any = data.asJsAny //convertJsonToJs(data.asJson)
    dom.console.log(jsonData)
    val dec = decodeJs[Page](jsonData)
    println(dec)
    */

    renderOnDomContentLoaded(container, myApp)
  }
}
