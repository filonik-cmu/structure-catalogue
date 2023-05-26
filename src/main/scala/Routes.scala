package example

import scala.scalajs.js.`import`

import com.raquo.laminar.api.L.{*, given}
import com.raquo.waypoint.*
import upickle.default.*

//import org.scalajs.dom

import pages.* 

object Routes {
  given HomePageRW: ReadWriter[HomePage.type] = macroRW
  given ItemPageRW: ReadWriter[ItemPage] = macroRW
  given StructurePageRw: ReadWriter[StructurePage] = macroRW
  given rw: ReadWriter[Page] = macroRW

  //val basePath: String =  Router.localFragmentBasePath
  def basePath: String = {
    `import`.meta.env.BASE_URL.asInstanceOf[String] + "#"
  }

  private val routes = List(
    Route.static(HomePage, root / endOfSegments, basePath),
    Route[ItemPage, Int](
      encode = itemPage => itemPage.id,
      decode = id => ItemPage(id = id),
      pattern = root / "items" / segment[Int] / endOfSegments,
      basePath = basePath,
    ),
    Route[StructurePage, String](
      encode = structurePage => structurePage.id,
      decode = id => StructurePage(id = id),
      pattern = root / "structures" / segment[String] / endOfSegments,
      basePath = basePath,
    )
  )

  val router = new Router[Page](
    routes = routes,
    getPageTitle = _.title,
    serializePage = page => write(page)(rw),
    deserializePage = pageStr => read(pageStr)(rw)
  )(
    popStateEvents = windowEvents(_.onPopState),
    owner = unsafeWindowOwner
  )
}
