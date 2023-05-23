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
    )
  )

  val router = new Router[Page](
    routes = routes,
    getPageTitle = _.title, // displayed in the browser tab next to favicon
    serializePage = page => write(page)(rw), // serialize page data for storage in History API log
    deserializePage = pageStr => read(pageStr)(rw) // deserialize the above
  )(
    popStateEvents = windowEvents(_.onPopState), // this is how Waypoint avoids an explicit dependency on Laminar
    owner = unsafeWindowOwner // this router will live as long as the window
  )
}