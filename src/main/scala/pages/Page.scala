package pages

sealed abstract class Page(val title: String)
case object HomePage extends Page("Home")
case class ItemPage(val id: Int) extends Page("Item")
case class StructurePage(val id: String) extends Page("Structure")
