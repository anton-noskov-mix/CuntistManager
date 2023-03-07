package model

import model.CardCollection.getCollectionJson
import model.card.Entity
import play.api.libs.json.{JsError, JsObject, JsPath, JsSuccess, JsValue, Json, Reads, Writes}

import java.io.File
import scala.collection.mutable.ListBuffer

class CardCollection[A <: Entity] private (val colType:String, val locale:String,collectionName:String) {

  val values:ListBuffer[A] = ListBuffer[A]()
  val parseError: ListBuffer[JsObject] = ListBuffer[JsObject]()


  def addFromFile(file: File)(implicit reader: Reads[A]): Unit = {
    val json = UtilPkg().getJsonFromFile(file)
    json.validate(getCollectionJson(colType)) match {
      case collection: JsSuccess[List[JsObject]] =>
        collection.value.foreach(jobj => {
          jobj.validate[A] match {
            case JsSuccess(card, path) => {
              values += card
            }
            case e: JsError => {
              parseError += jobj
            }
          }
        })
      case e: JsError =>
    }
  }

  def toJson()(implicit writer:Writes[A]): ListBuffer[JsValue] ={
    values.map(v => Json.toJson(v))
  }

}

object CardCollection {
  def apply[A <: Entity](colType: String,locale:String, collectionName: String): CardCollection[A] = {
    new CardCollection(colType, locale, collectionName)
  }


  def apply[A <: Entity](colType: String, file: File)(implicit reader: Reads[A]): CardCollection[A] = {
    val filePath = file.getPath
    val regEx = """loc_(..)""".r
    val locale = if(filePath.contains("loc_")) {
      regEx.findFirstMatchIn(filePath) match {
        case Some(r) => r.group(1)
        case None => throw AppException(s"Locale not found in file:$filePath")
      }
    }
    else "en"

    val collectionName = s"basic.$locale.$colType.${file.getName.split("\\.").head}"
    val col = new CardCollection[A](colType,locale, collectionName)
    col.addFromFile(file)
    col
  }


  def getCollectionJson(collection:String): Reads[List[JsObject]] = (JsPath \ collection).read[List[JsObject]]
}

