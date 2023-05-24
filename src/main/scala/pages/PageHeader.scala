package pages

import com.raquo.laminar.api.L.*

object PageHeader {

  def apply(): HtmlElement =
    headerTag(
      cls := "flex flex-row items-center justify-center",
      img(
        cls :="w-10 h-10 mx-2 dark:invert",
        src := "/favicon.svg",
      ),
      h1(
        a(href := "/", "The Data Structure Catalogue")
      ),
    )

}
