package model.card

import model.field.NumberField
import play.api.libs.functional.syntax.{toFunctionalBuilderOps, unlift}
import play.api.libs.json.{JsPath, Reads, Writes}

class Legacy(pId:String) extends Entity(pId) {

  override val collectionName: String = "legacies"

  /**
   * Description text that is shown once the game has been started.
   */
  private var startdescription: Option[String] = None

  def setStartDescription(pStartDescription:String): Unit ={
    startdescription = Option(pStartDescription)
  }

  def getStartDescription:String ={
  startdescription.getOrElse("")
  }

  def isStartDescriptionDefined:Boolean = startdescription.isDefined

  /**
   * The cards the legacy starts the game with.
   */
  private var effects: Option[NumberField] = None

  def setEffects(pEffects:NumberField): Unit ={
    effects = Option(pEffects)
  }

  def getEffects:NumberField ={
    effects.getOrElse(NumberField())
  }

  def isEffectsDefined:Boolean = effects.isDefined

  /**
   * The image used in the main menu. This is required, otherwise the legacy
   * won't parse. Does not take the image IDs that elements use.
   */
  private var image: Option[String] = None

  def setImage(pImage:String): Unit ={
    image = Option(pImage)
  }

  def getImage:String ={
    image.getOrElse("")
  }

  def isImageDefined:Boolean = image.isDefined

  /**
   * The id of an ending that will force the legacy to become available for the next game.
   */
  private var fromending:Option[String] = None

  def setFromEnding(pFromEnding:String): Unit ={
    fromending = Option(pFromEnding)
  }

  def getFromEnding:String ={
    fromending.getOrElse("")
  }

  def isFromEndingDefined:Boolean = fromending.isDefined

  /**
   * Decides if it's possible for the legacy to show up without requiring the fromEnding
   * ending. Used for apostles, new game plus.
   */
  private var availableWithoutEndingMatch: Option[Boolean] = None

  def setAvailableWithoutEndingMatch(pAvailableWithoutEndingMatch:Boolean): Unit ={
    availableWithoutEndingMatch = Option(pAvailableWithoutEndingMatch)
  }

  def getAvailableWithoutEndingMatch:Boolean ={
    availableWithoutEndingMatch.getOrElse(false)
  }

  def isAvailableWithoutEndingMatchDefined:Boolean = availableWithoutEndingMatch.isDefined

  /**
   * The legacy will start with this verb tile on the board.
   */
  private var startingverbid:Option[String] = None

  def setStartingVerbId(pStartingVerbId:String): Unit ={
    startingverbid = Option(pStartingVerbId)
  }

  def getStartingVerbId:String ={
    startingverbid.getOrElse("")
  }

  def isStartingVerbIdDefined:Boolean = startingverbid.isDefined

  private var excludesOnEnding:Option[List[String]] = None

  def setExcludesOnEnding(pExcludesOnEnding:List[String]):Unit ={
    excludesOnEnding = Option(pExcludesOnEnding)
  }

  def getExcludesOnEnding:List[String] ={
    excludesOnEnding.getOrElse(List())
  }

  def isExcludesOnEndingDefined:Boolean = excludesOnEnding.isDefined

  private var statusbarelements:Option[List[String]] = None

  def setStatusBarElements(pStatusBarElements:List[String]): Unit ={
    statusbarelements = Option(pStatusBarElements)
  }

  def getStatusBarElements:List[String] ={
    statusbarelements.getOrElse(List())
  }

  def isStatusBarElementsDefined:Boolean = statusbarelements.isDefined

  private var newstart:Option[Boolean] = None

  def setNewStart(pNewStart:Boolean): Unit ={
    newstart = Option(pNewStart)
  }

  def getNewStart:Boolean ={
    newstart.getOrElse(false)
  }

  def isNewStartDefined:Boolean = newstart.isDefined

  private var tablecoverimage:Option[String] = None

  def setTableCoverImage(pTableCoverImage:String): Unit ={
    tablecoverimage = Option(pTableCoverImage)
  }

  def getTableCoverImage:String ={
    tablecoverimage.getOrElse("")
  }

  def isTableCoverImageDefined:Boolean = tablecoverimage.isDefined

  private var family:Option[String] = None

  def setFamily(pFamily:String): Unit ={
    family = Option(pFamily)
  }

  def getFamily:String ={
    family.getOrElse("")
  }

  def isFamilyDefined:Boolean = family.isDefined

  def canEqual(a: Any):Boolean = a.isInstanceOf[Legacy]

  override def equals(that: Any): Boolean =
    that match {
      case that: Legacy => {
        that.canEqual(this) &&
          this.id == that.id &&
          this.label == that.label &&
          this.description == that.description &&
          this.comments == that.comments &&
          this.image == that.image &&
          this.excludesOnEnding == that.excludesOnEnding &&
          this.statusbarelements == that.statusbarelements &&
          this.fromending == that.fromending &&
          this.newstart == that.newstart &&
          this.effects == that.effects &&
          this.tablecoverimage == that.tablecoverimage &&
          this.startdescription == that.startdescription &&
          this.availableWithoutEndingMatch == that.availableWithoutEndingMatch &&
          this.family == that.family &&
          this.startingverbid == that.startingverbid
      }
      case _ => false
    }


//  image
//  description
//  excludesOnEnding
//  statusbarelements
//  label
//  fromending
//  newstart
//  effects
//  tablecoverimage
//  startdescription
//  availableWithoutEndingMatch
//  id
//  family
//  startingverbid

}

object Legacy {
  def apply(pId:String): Legacy = new Legacy(pId)

  def unapply(l: Legacy): Option[
    (String,
      Option[String],
      Option[String],
      Option[String],
      Option[String],
      Option[List[String]],
      Option[List[String]],
      Option[String],
      Option[Boolean],
      Option[NumberField],
      Option[String],
      Option[String],
      Option[Boolean],
      Option[String],
      Option[String]
      )] = {
    Option(
      l.id,
      l.label,
      l.description,
      l.comments,
      l.image,
      l.excludesOnEnding,
      l.statusbarelements,
      l.fromending,
      l.newstart,
      l.effects,
      l.tablecoverimage,
      l.startdescription,
      l.availableWithoutEndingMatch,
      l.family,
      l.startingverbid
    )
  }

  private def applyLegacy(
                           pId: String,
                           pLabel: Option[String],
                           pDescription: Option[String],
                           pComments: Option[String],
                           pImage: Option[String],
                           pExcludesOnEnding: Option[List[String]],
                           pStatusBarElements: Option[List[String]],
                           pFromEnding: Option[String],
                           pNewStart: Option[Boolean],
                           pEffects: Option[NumberField],
                           pTableCoverImage: Option[String],
                           pStartDescription: Option[String],
                           pAvailableWithoutEndingMatch: Option[Boolean],
                           pFamily: Option[String],
                           pStartingVerbId: Option[String]
                         ): Legacy = {
    val l = new Legacy(pId)
      l.label = pLabel
      l.description = pDescription
      l.comments = pComments
      l.image = pImage
      l.excludesOnEnding = pExcludesOnEnding
      l.statusbarelements = pStatusBarElements
      l.fromending = pFromEnding
      l.newstart = pNewStart
      l.effects = pEffects
      l.tablecoverimage = pTableCoverImage
      l.startdescription = pStartDescription
      l.availableWithoutEndingMatch = pAvailableWithoutEndingMatch
      l.family = pFamily
      l.startingverbid = pStartingVerbId
    l
  }


  implicit val legacyReader: Reads[Legacy] = (
    (JsPath \ "id").read[String] and
      (JsPath \ "label").readNullable[String] and
      (JsPath \ "description").readNullable[String] and
      (JsPath \ "comments").readNullable[String] and
      (JsPath \ "image").readNullable[String] and
      (JsPath \ "excludesOnEnding").readNullable[List[String]] and
      (JsPath \ "statusbarelements").readNullable[List[String]] and
      (JsPath \ "fromending").readNullable[String] and
      (JsPath \ "newstart").readNullable[Boolean] and
      (JsPath \ "effects").readNullable[NumberField] and
      (JsPath \ "tablecoverimage").readNullable[String] and
      (JsPath \ "startdescription").readNullable[String] and
      (JsPath \ "availableWithoutEndingMatch").readNullable[Boolean] and
      (JsPath \ "family").readNullable[String] and
      (JsPath \ "startingverbid").readNullable[String]
    ) (Legacy.applyLegacy _)

  implicit val legacyWriter: Writes[Legacy] = (
    (JsPath \ "id").write[String] and
      (JsPath \ "label").writeNullable[String] and
      (JsPath \ "description").writeNullable[String] and
      (JsPath \ "comments").writeNullable[String] and
      (JsPath \ "image").writeNullable[String] and
      (JsPath \ "excludesOnEnding").writeNullable[List[String]] and
      (JsPath \ "statusbarelements").writeNullable[List[String]] and
      (JsPath \ "fromending").writeNullable[String] and
      (JsPath \ "newstart").writeNullable[Boolean] and
      (JsPath \ "effects").writeNullable[NumberField] and
      (JsPath \ "tablecoverimage").writeNullable[String] and
      (JsPath \ "startdescription").writeNullable[String] and
      (JsPath \ "availableWithoutEndingMatch").writeNullable[Boolean] and
      (JsPath \ "family").writeNullable[String] and
      (JsPath \ "startingverbid").writeNullable[String]
    ) (unlift(Legacy.unapply))

}

