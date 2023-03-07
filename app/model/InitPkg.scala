package model


import model.CardCollection.getCollectionJson
import model.card._
import model.enums.CollectionEnum
import play.api.libs.json.{JsError, JsObject, JsSuccess}

import java.io.File
import scala.collection.mutable.ListBuffer


class InitPkg {

  val baseGamePath = "c:\\Cultist Simulator\\Game\\"


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
    val files = UtilPkg().getJsonFiles(baseGamePath)
    val verbCollections: ListBuffer[CardCollection[Verb]] = ListBuffer[CardCollection[Verb]]()
    val deskCollections: ListBuffer[CardCollection[Desk]] = ListBuffer[CardCollection[Desk]]()
    val elementCollections: ListBuffer[CardCollection[Element]] = ListBuffer[CardCollection[Element]]()
    val recipeCollections: ListBuffer[CardCollection[Recipe]] = ListBuffer[CardCollection[Recipe]]()
    val endingCollections: ListBuffer[CardCollection[Ending]] = ListBuffer[CardCollection[Ending]]()
    val portalCollections: ListBuffer[CardCollection[Portal]] = ListBuffer[CardCollection[Portal]]()
    val legacyCollections: ListBuffer[CardCollection[Legacy]] = ListBuffer[CardCollection[Legacy]]()

    files.foreach(file => {
      val json = UtilPkg().getJsonFromFile(file)

      val collectionFields = json.as[JsObject].fields

      if (collectionFields.length == 1)
        collectionFields.head._1 match {
          case field if field == CollectionEnum.Verbs =>
            verbCollections += CardCollection[Verb](CollectionEnum.Verbs, file)
          case field if field == CollectionEnum.Elements =>
            elementCollections += CardCollection[Element](CollectionEnum.Elements, file)
          case field if field == CollectionEnum.Recipes =>
            recipeCollections += CardCollection[Recipe](CollectionEnum.Recipes, file)
          case field if field == CollectionEnum.Decks =>
            deskCollections += CardCollection[Desk](CollectionEnum.Decks, file)
          case field if field == CollectionEnum.Legacies =>
            legacyCollections += CardCollection[Legacy](CollectionEnum.Legacies, file)
          case field if field == CollectionEnum.Endings =>
            endingCollections += CardCollection[Ending](CollectionEnum.Endings, file)
          case field if field == CollectionEnum.Portals =>
            portalCollections += CardCollection[Portal](CollectionEnum.Portals, file)
          case _ =>
            val test = 1
        }
    })

//    val database: MongoDatabase = getConnection("mydb")

    //val collection: MongoCollection[Ending] = database.getCollection[Ending]("test");

    //val ddoc = endingCollections.head.values.head

    //val result = collection.insertOne(ddoc)

    //val res = Await.result(result.toFuture(),Duration.Inf)

    //val id = res.getInsertedId

//    val test2 = collection.find().toFuture()
//
//    val maxWaitTime: FiniteDuration = Duration(5, TimeUnit.SECONDS)
//
//    val res = Await.result(test2,Duration.Inf)
//
//    endingCollections.foreach(col =>{
//      val collection: MongoCollection[Ending] = database.getCollection[Ending](col.collectionName)
//      col.values.foreach(doc => {
//        val result = collection.insertOne(doc).toFuture()
//        Await.result(result,Duration.Inf)
//      })
//    })

    var equal = true

//    endingCollections.foreach(col => {
//      var colLen = col.values.length
//      val collection: MongoCollection[Ending] = database.getCollection[Ending](col.collectionName)
//      val docsF = collection.find().toFuture()
//      val docs = Await.result(docsF,Duration.Inf)
//      docs.foreach(doc =>{
//        val colDoc = col.values.find(_.id == doc.id).get
//        if(colDoc.equals(doc))
//          true
//        else
//          equal = false
//        colLen -= 1
//      })
//
//      if(colLen == 0)
//        true
//      else
//        equal = false
//
//    })

  }

  def init(): List[String] = {
    //parseGameFiles()
    new CollectionTestClass[Desk]().checkCollectionParser(baseGamePath,CollectionEnum.Decks)
    new CollectionTestClass[Element]().checkCollectionParser(baseGamePath,CollectionEnum.Elements)
    new CollectionTestClass[Ending]().checkCollectionParser(baseGamePath,CollectionEnum.Endings)
    new CollectionTestClass[Legacy]().checkCollectionParser(baseGamePath,CollectionEnum.Legacies)
    new CollectionTestClass[Portal]().checkCollectionParser(baseGamePath,CollectionEnum.Portals)
    new CollectionTestClass[Recipe]().checkCollectionParser(baseGamePath,CollectionEnum.Recipes)
    new CollectionTestClass[Verb]().checkCollectionParser(baseGamePath,CollectionEnum.Verbs)
    List()
  }

}

object InitPkg {
  def apply(): InitPkg = new InitPkg()
}
