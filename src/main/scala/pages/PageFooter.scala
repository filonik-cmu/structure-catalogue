package pages

import com.raquo.laminar.api.L.*

object PageFooter {

  def apply(mods: Modifier[HtmlElement]*): HtmlElement =
    footerTag(
      cls := "w-full text-center",
      mods
    )

}
