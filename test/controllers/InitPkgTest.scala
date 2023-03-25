package controllers

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.Injecting


class InitPkgTest extends PlaySpec with GuiceOneAppPerTest with Injecting {

  val baseGamePath = "c:\\Cultist Simulator\\Game\\"

//  def parseGameFiles(): Unit = {
//    val files = UtilPkg().getJsonFiles(baseGamePath)
//    val verbCollections: ListBuffer[CardCollection[Verb]] = ListBuffer[CardCollection[Verb]]()
//    val deckCollections: ListBuffer[CardCollection[Deck]] = ListBuffer[CardCollection[Deck]]()
//    val elementCollections: ListBuffer[CardCollection[Element]] = ListBuffer[CardCollection[Element]]()
//    val recipeCollections: ListBuffer[CardCollection[Recipe]] = ListBuffer[CardCollection[Recipe]]()
//    val endingCollections: ListBuffer[CardCollection[Ending]] = ListBuffer[CardCollection[Ending]]()
//    val portalCollections: ListBuffer[CardCollection[Portal]] = ListBuffer[CardCollection[Portal]]()
//    val legacyCollections: ListBuffer[CardCollection[Legacy]] = ListBuffer[CardCollection[Legacy]]()
//
//    val verbLoadedJson = ListBuffer[List[JsObject]]()
//    val deckLoadedJson = ListBuffer[List[JsObject]]()
//    val elementLoadedJson = ListBuffer[List[JsObject]]()
//    val recipeLoadedJson = ListBuffer[List[JsObject]]()
//    val endingLoadedJson = ListBuffer[List[JsObject]]()
//    val portalLoadedJson = ListBuffer[List[JsObject]]()
//    val legacyLoadedJson = ListBuffer[List[JsObject]]()
//
//    files.foreach(file => {
//      val json = UtilPkg().getJsonFromFile(file)
//
//
//            //Verbs collections
//            json.validate(getCollectionReader(CollectionEnum.Verbs)) match {
//              case collection: JsSuccess[List[JsObject]] =>
//                val verbCollection = CardCollection[Verb](CollectionEnum.Verbs, file.getName)
//                if(verbCollection.parseError.nonEmpty)
//                  throw AppException("Verb collections load error")
//
//                verbCollection.addFromFile(file)
//                verbCollections += verbCollection
//                verbLoadedJson += collection.value
//              case e: JsError =>
//            }
//
//            val verbWrittenJson = verbCollections.map(col => {
//              col.toJson().map(_.as[JsObject])
//            })
//
//            if(!InitPkg().compareResults(verbLoadedJson.toList.flatten,verbWrittenJson.toList.flatten))
//              throw AppException("Verb collections load error")
//
//            //Deck collections
//            json.validate(getCollectionReader(CollectionEnum.Decks)) match {
//              case collection: JsSuccess[List[JsObject]] =>
//                val deckCollection = CardCollection[Deck](CollectionEnum.Decks, file.getName)
//                if(deckCollection.parseError.nonEmpty)
//                  throw AppException("Deck collections load error")
//                deckCollection.addFromFile(file)
//                deckCollections += deckCollection
//                deckLoadedJson += collection.value
//              case e: JsError =>
//            }
//
//            val deckWrittenJson = deckCollections.map(col => {
//              col.toJson().map(_.as[JsObject])
//            })
//
//            if(!InitPkg().compareResults(deckLoadedJson.toList.flatten,deckWrittenJson.toList.flatten))
//              throw AppException("Deck collections load error")
//
//              //Element collections
//            json.validate(getCollectionReader(CollectionEnum.Elements)) match {
//              case collection: JsSuccess[List[JsObject]] =>
//                val elementCollection = CardCollection[Element](CollectionEnum.Elements, file.getName)
//                if(elementCollection.parseError.nonEmpty)
//                  throw AppException("Element collections load error")
//                elementCollection.addFromFile(file)
//                elementCollections += elementCollection
//                elementLoadedJson += collection.value
//              case e: JsError =>
//            }
//
//            val elementWrittenJson = elementCollections.map(col => {
//              col.toJson().map(_.as[JsObject])
//            })
//
//            if(!InitPkg().compareResults(elementLoadedJson.toList.flatten,elementWrittenJson.toList.flatten))
//              throw AppException("Element collections load error")
//
//            //Recipes collections
//            json.validate(getCollectionReader(CollectionEnum.Recipes)) match {
//              case collection: JsSuccess[List[JsObject]] =>
//                val recipeCollection = CardCollection[Recipe](CollectionEnum.Recipes, file.getName)
//                if(recipeCollection.parseError.nonEmpty)
//                  throw AppException("Recipe collections load error")
//                recipeCollection.addFromFile(file)
//                recipeCollections += recipeCollection
//                recipeLoadedJson += collection.value
//              case e: JsError =>
//            }
//
//            val recipeWrittenJson = recipeCollections.map(col => {
//              col.toJson().map(_.as[JsObject])
//            })
//
//            if(!InitPkg().compareResults(recipeLoadedJson.toList.flatten,recipeWrittenJson.toList.flatten))
//              throw AppException("Recipe collections load error")
//
//            //Endings collections
//            json.validate(getCollectionReader(CollectionEnum.Endings)) match {
//              case collection: JsSuccess[List[JsObject]] =>
//                val endingCollection = CardCollection[Ending](CollectionEnum.Endings, file.getName)
//                if(endingCollection.parseError.nonEmpty)
//                  throw AppException("Endings collections load error")
//                endingCollection.addFromFile(file)
//                endingCollections += endingCollection
//                endingLoadedJson += collection.value
//              case e: JsError =>
//            }
//
//            val endingWrittenJson = endingCollections.map(col => {
//              col.toJson().map(_.as[JsObject])
//            })
//
//            if(!InitPkg().compareResults(endingLoadedJson.toList.flatten,endingWrittenJson.toList.flatten))
//              throw AppException("Endings collections load error")
//
//            //Portal collections
//            json.validate(getCollectionReader(CollectionEnum.Portals)) match {
//              case collection: JsSuccess[List[JsObject]] =>
//                val portalCollection = CardCollection[Portal](CollectionEnum.Portals, file.getName)
//                if(portalCollection.parseError.nonEmpty)
//                  throw AppException("Portals collections load error")
//                portalCollection.addFromFile(file)
//                portalCollections += portalCollection
//                portalLoadedJson += collection.value
//              case e: JsError =>
//            }
//
//            val portalWrittenJson = portalCollections.map(col => {
//              col.toJson().map(_.as[JsObject])
//            })
//
//            if(!InitPkg().compareResults(portalLoadedJson.toList.flatten,portalWrittenJson.toList.flatten))
//              throw AppException("Portals collections load error")
//
//            //Legacy collections
//            json.validate(getCollectionReader(CollectionEnum.Legacies)) match {
//              case collection: JsSuccess[List[JsObject]] =>
//                val legacyCollection = CardCollection[Legacy](CollectionEnum.Legacies, file.getName)
//                if(legacyCollection.parseError.nonEmpty)
//                  throw AppException("Legacies collections load error")
//                legacyCollection.addFromFile(file)
//                legacyCollections += legacyCollection
//                legacyLoadedJson += collection.value
//              case e: JsError =>
//            }
//
//            val legacyWrittenJson = legacyCollections.map(col => {
//              col.toJson().map(_.as[JsObject])
//            })
//
//            if(!InitPkg().compareResults(legacyLoadedJson.toList.flatten,legacyWrittenJson.toList.flatten))
//              throw AppException("Legacies collections load error")
//
//    })
//    val test = 1
//  }

}
