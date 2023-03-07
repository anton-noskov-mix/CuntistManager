package controllers

import model.Artist
import play.api.libs.json.Json
import play.api.mvc.{BaseController, ControllerComponents}

import javax.inject.Inject
import scala.xml.Node

class ArtistController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def listArtist = Action {
      Ok(views.html.home(Artist.fetch))
  }

  def fetchArtistByName(name:String) = Action {
  Ok(views.html.home(Artist.fetchByName(name)))
  }

  def search(name: String, country: String) = Action {
    val result = Artist.fetchByNameOrCountry(name, country)
    if(result.isEmpty)
      NoContent
    else
      Ok(views.html.home(result))
  }

  def search2(name: Option[String], country: String) = Action {
    val result = name match {
      case Some(name) =>
        Artist.fetchByNameOrCountry(name, country)
      case None =>
        Artist.fetchByCountry(country)
    }
    if(result.isEmpty)
      NoContent
    else
      Ok(views.html.home(result))
  }

  def getConfig = Action {
    implicit request =>
      val xmlResponse: Node = <metadata>
        <company>TinySensors</company>
        <batch>md2907</batch>
      </metadata>
      val jsonResponse = Json.obj("metadata" -> Json.arr(
        Json.obj("company" -> "TinySensors"),
        Json.obj("batch" -> "md2907"))
      )
      render {
        case Accepts.Xml() =>
          Ok(xmlResponse)
        case Accepts.Json() =>
          Ok(jsonResponse)
      }
  }

}
