package example

import com.raquo.laminar.api.L.{*, given}
import com.raquo.waypoint.*
import upickle.default.*

import pages.* 

object Routes {
  given HomePageRW: ReadWriter[HomePage.type] = macroRW
  given ItemPageRW: ReadWriter[ItemPage] = macroRW
  given rw: ReadWriter[Page] = macroRW

  private val routes = List(
    Route.static(HomePage, root / endOfSegments, Router.localFragmentBasePath),
    Route[ItemPage, Int](
      encode = itemPage => itemPage.id,
      decode = id => ItemPage(id=id),
      pattern = root / "items" / segment[Int] / endOfSegments,
      basePath = Router.localFragmentBasePath,
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
