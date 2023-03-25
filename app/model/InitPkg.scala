package model


import model.CardCollection.getCollectionJson
import model.MongoDBPkg.getConnection
import model.card._
import model.enums.CollectionEnum
import org.mongodb.scala.model.{IndexOptions, Indexes}
import org.mongodb.scala.model.Indexes.ascending
import org.mongodb.scala.{MongoCollection, MongoDatabase}
import play.api.libs.json.{JsError, JsObject, JsSuccess}

import java.io.File
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.{Duration, FiniteDuration}
import scala.reflect.ClassTag


class InitPkg {

  val baseGamePath = "c:\\Cultist Simulator\\Game\\"

  def getCollection[A:ClassTag](name: String)(implicit database: MongoDatabase): MongoCollection[A] = {
    val observ = database.listCollectionNames().collect()
    observ.toFuture().foreach(list =>
      if (!list.contains(name)) {
        database.getCollection(name).createIndex(
          ascending("id"),
          IndexOptions().background(false).unique(true)
        ).toFuture()
      })

    Await.result(observ.toFuture(), Duration.Inf)
    database.getCollection[A](name)
  }


  def parseCardsFields(files: List[File], element: String): List[String] = {
    val jsonFields = collection.mutable.Set[String]()
    files.foreach(file => {
      val json = UtilPkg().getJsonFromFile(file)
      json.validate(getCollectionJson(element)) match {
        case collection: JsSuccess[List[JsObject]] =>
          collection.value.foreach(element => {
            jsonFields ++= element.fields.map(_._1)
          })
        case e: JsError =>
      }
    })
    jsonFields.toList
  }

  def parseGameFiles(): Unit = {
    val parseError = mutable.Map[String,String]()

    val verbCollections: ListBuffer[CardCollection[Verb]] = ListBuffer[CardCollection[Verb]]()
    val deckCollections: ListBuffer[CardCollection[Deck]] = ListBuffer[CardCollection[Deck]]()
    val elementCollections: ListBuffer[CardCollection[Element]] = ListBuffer[CardCollection[Element]]()
    val recipeCollections: ListBuffer[CardCollection[Recipe]] = ListBuffer[CardCollection[Recipe]]()
    val endingCollections: ListBuffer[CardCollection[Ending]] = ListBuffer[CardCollection[Ending]]()
    val portalCollections: ListBuffer[CardCollection[Portal]] = ListBuffer[CardCollection[Portal]]()
    val legacyCollections: ListBuffer[CardCollection[Legacy]] = ListBuffer[CardCollection[Legacy]]()

    UtilPkg().getJsonFiles(baseGamePath).foreach(file => {
      val json = UtilPkg().getJsonFromFile(file)

      val collectionFields = json.as[JsObject].fields

      if (collectionFields.length == 1) {
        collectionFields.head._1 match {
          case field if field == CollectionEnum.Verbs =>
            verbCollections += CardCollection[Verb](CollectionEnum.Verbs, file)
          case field if field == CollectionEnum.Elements =>
            elementCollections += CardCollection[Element](CollectionEnum.Elements, file)
          case field if field == CollectionEnum.Recipes =>
            recipeCollections += CardCollection[Recipe](CollectionEnum.Recipes, file)
          case field if field == CollectionEnum.Decks =>
            deckCollections += CardCollection[Deck](CollectionEnum.Decks, file)
          case field if field == CollectionEnum.Legacies =>
            legacyCollections += CardCollection[Legacy](CollectionEnum.Legacies, file)
          case field if field == CollectionEnum.Endings =>
            endingCollections += CardCollection[Ending](CollectionEnum.Endings, file)
          case field if field == CollectionEnum.Portals =>
            portalCollections += CardCollection[Portal](CollectionEnum.Portals, file)
          case _ =>
            parseError += (file.getName -> s"Коллекция не поддерживается:${collectionFields.head._1}")
        }
      } else {
        parseError += (file.getName -> s"Файл содержит более одной коллекции:${collectionFields.map(_._1).mkString(",")}")
      }
    })

    implicit val database: MongoDatabase = getConnection("mydb")

    endingCollections.foreach(col =>{
      val collection: MongoCollection[Ending] = getCollection[Ending](col.collectionName)
      col.values.foreach(doc => {
        val result = collection.insertOne(doc).toFuture()
        Await.result(result,Duration.Inf)
      })
    })

    var equal = true

    endingCollections.foreach(col => {
      var colLen = col.values.length
      val collection: MongoCollection[Ending] = database.getCollection[Ending](col.collectionName)
      val docsF = collection.find().toFuture()
      val docs = Await.result(docsF,Duration.Inf)
      docs.foreach(doc =>{
        val colDoc = col.values.find(_.getId == doc.getId).get
        if(colDoc.equals(doc))
          true
        else
          equal = false
        colLen -= 1
      })

      if(colLen == 0)
        true
      else
        equal = false

    })

  }

  def init(): List[String] = {
    parseGameFiles()
    List()
  }

}

object InitPkg {
  def apply(): InitPkg = new InitPkg()
}
