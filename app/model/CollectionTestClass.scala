package model

import model.CardCollection.getCollectionJson
import model.card.Entity
import play.api.libs.json._

import scala.collection.mutable.ListBuffer

class CollectionTestClass[A <: Entity] {

  def checkCollectionParser(baseGamePath: String, collectionName: String)(implicit reader: Reads[A], writer: Writes[A]): Unit = {
    val filesToLoad = UtilPkg().getJsonFiles(baseGamePath)

    //Для подщёта всех файлов коллекций
    val jsonCounter = collection.mutable.Map[String,Int]()

    //Для подщёта файлов нужной коллекции
    var collectionJsonCounter = 0

    val someParsedCollections: ListBuffer[CardCollection[A]] = ListBuffer[CardCollection[A]]()
    val someLoadedJsons = ListBuffer[List[JsObject]]()

    filesToLoad.foreach(file => {
      val json = UtilPkg().getJsonFromFile(file)

      json.as[JsObject].fields.foreach(t2 => {
        val count = jsonCounter.getOrElseUpdate(t2._1,0)
        jsonCounter(t2._1) = count + 1
      })


      json.validate(getCollectionJson(collectionName)) match {
        case collection: JsSuccess[List[JsObject]] =>
          collectionJsonCounter += 1

          val someParsedCollection = CardCollection[A](collectionName, file)
          if (someParsedCollection.parseError.nonEmpty)
            throw AppException(s"${collectionName} collections load error")

          someParsedCollections += someParsedCollection

          someLoadedJsons += collection.value
        case e: JsError =>
      }

      val someWrittenJsons = someParsedCollections.map(col => {
        col.toJson().map(_.as[JsObject])
      })

      if (someLoadedJsons.flatten.length != someWrittenJsons.flatten.length)
        throw AppException(
          s"""Collection ${collectionName} has errors: Loaded count and written count don't match!
             |Loaded count: ${someLoadedJsons.flatten.length}
             |Written count: ${someWrittenJsons.flatten.length}
             |""".stripMargin)

      compareResults(someLoadedJsons.toList.flatten, someWrittenJsons.toList.flatten)
    })

    if(jsonCounter(collectionName) != collectionJsonCounter)
      throw AppException(
        s"Count from all loaded files(${jsonCounter(collectionName)}) don't match with parsed count ($collectionJsonCounter)"
      )

  }

  private def compareResults(load: List[JsObject], write: List[JsObject]): Unit = {
    for (i <- load.indices) {
      if (load(i) == write(i)) {
      } else {
        throw AppException(
          s"""Loaded json and Written json № $i don't match!
            |Loaded json: ${load(i).toString()}
            |
            |Written json:  ${write(i).toString()}
            |
            |""".stripMargin)
      }
    }
  }

}
