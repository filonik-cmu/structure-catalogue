package pages

import com.raquo.laminar.api.L.*

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

import org.scalajs.dom

object PageHeader {
  @js.native
  @JSImport("/favicon.svg", JSImport.Default)
  private object _logo extends js.Any
  def logo: String = _logo.asInstanceOf[String]

  def apply(): HtmlElement = {
    dom.console.log(logo)
    headerTag(
      cls := "flex flex-row items-center justify-center",
      img(
        cls :="w-10 h-10 mx-2 dark:invert",
        src := logo,
      ),
      h1(
        a(href := "/", "The Data Structure Catalogue")
      ),
    )
  }

}
