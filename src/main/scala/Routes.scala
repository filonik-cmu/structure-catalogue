package example

import scala.scalajs.js.`import`

import com.raquo.laminar.api.L.{*, given}
import com.raquo.waypoint.*
import upickle.default.*

import pages.* 

object Routes {
  given HomePageRW: ReadWriter[HomePage.type] = macroRW
  given ItemPageRW: ReadWriter[ItemPage] = macroRW
  given rw: ReadWriter[Page] = macroRW

  //val basePath: String =  Router.localFragmentBasePath
  val basePath: String = `import`.meta.env.BASE_URL.asInstanceOf[String].slice(1, -1)

  private val routes = List(
    Route.static(HomePage, root / endOfSegments, basePath),
    Route[ItemPage, Int](
      encode = itemPage => itemPage.id,
      decode = id => ItemPage(id=id),
      pattern = root / "items" / segment[Int] / endOfSegments,
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
