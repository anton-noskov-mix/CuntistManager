package model.card

import model.field.StringField
import play.api.libs.functional.syntax.{toFunctionalBuilderOps, unlift}
import play.api.libs.json.{JsPath, Reads, Writes}


class  Desk(pId:String) extends Entity(pId) {

  override val collectionName: String = "decks"

  /**
   * List of the cards contained in the deck.
   */
  private var spec: Option[List[String]] = None

  def setSpec(pSpec: List[String]): Unit ={
    spec = Option(pSpec)
  }

  def getSpec: List[String] ={
    spec.getOrElse(List())
  }

  def isSpecDefined:Boolean = spec.isDefined

  /**
   * Causes the deck to be reset after the last card is drawn from it.
   * If true, the deck will never be empty.
   */
  private var resetonexhaustion: Option[Boolean] = None

  def setResetOnExhaustion(pResetOnExhaustion:Boolean): Unit ={
    resetonexhaustion = Option(pResetOnExhaustion)
  }

  def getResetOnExhaustion: Boolean = {
    resetonexhaustion.getOrElse(false)
  }

  def isResetOnExhaustionDefined:Boolean = resetonexhaustion.isDefined

  /**
   * The card that is drawn in case the deck is empty.
   */
  private var defaultcard: Option[String] = None

  def setDefaultCard(pDefaultCard:String): Unit ={
    defaultcard = Option(pDefaultCard)
  }

  def getDefaultCard: String ={
    defaultcard.getOrElse("")
  }

  def isDefaultCardDefined:Boolean = defaultcard.isDefined

  /**
   * Displays a description text based on the card that was drawn. Used in the mansus.
   */
  private var drawmessages: Option[StringField] = None

  def setDrawMessages(pDrawMessages:StringField): Unit ={
    drawmessages = Option(pDrawMessages)
  }

  def getDrawMessages: StringField ={
    drawmessages.getOrElse(StringField())
  }

  def isDrawMessagesDefined:Boolean = drawmessages.isDefined

  /**
   * Only used for internaldecks inside recipes?
   */
  private var draws: Option[Int] = None

  def setDraws(pDraws:Int): Unit ={
    draws = Option(pDraws)
  }

  //todo Is getter needed???
  def getDraws: Int = {
    draws.getOrElse(0)
  }

  def isDrawsDefined:Boolean = draws.isDefined

  /**
   * ???
   */
  private var forlegacyfamily:Option[String] = None

  def setForLegacyFamily(pForLegacyFamily:String): Unit ={
    forlegacyfamily = Option(pForLegacyFamily)
  }

  def getForLegacyFamily: String ={
    forlegacyfamily.getOrElse("")
  }

  def isForLegacyFamilyDefined:Boolean = forlegacyfamily.isDefined

  /**
   * ???
   */
  private var defaultdrawmessages: Option[StringField] = None

  def setDefaultDrawMessages(pDefaultDrawMessages:StringField): Unit ={
    defaultdrawmessages = Option(pDefaultDrawMessages)
  }

  def getDefaultDrawMessages: StringField ={
    defaultdrawmessages.getOrElse(StringField())
  }

  def isDefaultDrawMessagesDefined:Boolean = defaultdrawmessages.isDefined

  def canEqual(a: Any):Boolean = a.isInstanceOf[Desk]

  override def equals(that: Any): Boolean =
    that match {
      case that: Desk => {
        that.canEqual(this) &&
          this.id == that.id &&
          this.label == that.label &&
          this.description == that.description &&
          this.comments == that.comments &&
          this.forlegacyfamily == that.forlegacyfamily &&
          this.drawmessages == that.drawmessages &&
          this.defaultcard == that.defaultcard &&
          this.defaultdrawmessages == that.defaultdrawmessages &&
          this.spec == that.spec &&
          this.resetonexhaustion == that.resetonexhaustion
      }
      case _ => false
    }

//  Fields
//  comments
//  forlegacyfamily
//  drawmessages
//  description
//  defaultcard
//  id
//  label
//  defaultdrawmessages
//  spec
//  resetonexhaustion
}

object Desk {
  def apply(pId:String): Desk = new Desk(pId)

  private def applyRead(
                         pId: String,
                         pLabel: Option[String],
                         pDescription: Option[String],
                         pComments: Option[String],
                         pSpec: Option[List[String]],
                         pResetOnExhaustion: Option[Boolean],
                         pDefaultCard: Option[String],
                         pDrawMessages: Option[StringField],
                         pDefaultDrawMessages: Option[StringField],
                         pDraws: Option[Int],
                         pForLegacyFamily: Option[String]
                       ): Desk = {
    val d = new Desk(pId)
      d.label = pLabel
      d.description = pDescription
      d.comments = pComments
      d.spec = pSpec
      d.resetonexhaustion = pResetOnExhaustion
      d.defaultcard = pDefaultCard
      d.drawmessages = pDrawMessages
      d.defaultdrawmessages = pDefaultDrawMessages
      d.draws = pDraws
      d.forlegacyfamily = pForLegacyFamily
    d
  }

  private def applyInternalRead(
                         pSpec: List[String],
                         pLabel: Option[String],
                         pDescription: Option[String],
                         pComments: Option[String],
                         pResetOnExhaustion: Option[Boolean],
                         pDefaultCard: Option[String],
                         pDrawMessages: Option[StringField],
                         pDefaultDrawMessages: Option[StringField],
                         pDraws: Option[Int],
                         pForLegacyFamily: Option[String]
                       ): Desk = {
    val d = new Desk(null)
    d.spec = Option(pSpec)
    d.label = pLabel
    d.description = pDescription
    d.comments = pComments
    d.resetonexhaustion = pResetOnExhaustion
    d.defaultcard = pDefaultCard
    d.drawmessages = pDrawMessages
    d.defaultdrawmessages = pDefaultDrawMessages
    d.draws = pDraws
    d.forlegacyfamily = pForLegacyFamily
    d
  }

  def unapply(d: Desk): Option[
    (String,
      Option[String],
      Option[String],
      Option[String],
      Option[List[String]],
      Option[Boolean],
      Option[String],
      Option[StringField],
      Option[StringField],
      Option[Int],
      Option[String])] = {
    Option(d.id,
      d.label,
      d.description,
      d.comments,
      d.spec,
      d.resetonexhaustion,
      d.defaultcard,
      d.drawmessages,
      d.defaultdrawmessages,
      d.draws,
      d.forlegacyfamily)
  }

  def unapplyInternalDesk(d: Desk): Option[
    (Option[String],
      Option[String],
      Option[String],
      Option[String],
      Option[List[String]],
      Option[Boolean],
      Option[String],
      Option[StringField],
      Option[StringField],
      Option[Int],
      Option[String])] = {
    Option(
      None,
      d.label,
      d.description,
      d.comments,
      d.spec,
      d.resetonexhaustion,
      d.defaultcard,
      d.drawmessages,
      d.defaultdrawmessages,
      d.draws,
      d.forlegacyfamily)
  }


  lazy implicit val deskReader: Reads[Desk] = (
    (JsPath \ "id").read[String] and
      (JsPath \ "label").readNullable[String] and
      (JsPath \ "description").readNullable[String] and
      (JsPath \ "comments").readNullable[String] and
      (JsPath \ "spec").readNullable[List[String]] and
      (JsPath \ "resetonexhaustion").readNullable[Boolean] and
      (JsPath \ "defaultcard").readNullable[String] and
      (JsPath \ "drawmessages").readNullable[StringField] and
      (JsPath \ "defaultdrawmessages").readNullable[StringField] and
      (JsPath \ "draws").readNullable[Int] and
      (JsPath \ "forlegacyfamily").readNullable[String]
    )(Desk.applyRead _)

  val internalDeskReader: Reads[Desk] = (
    (JsPath \ "spec").read[List[String]] and
      (JsPath \ "label").readNullable[String] and
      (JsPath \ "description").readNullable[String] and
      (JsPath \ "comments").readNullable[String] and
      (JsPath \ "resetonexhaustion").readNullable[Boolean] and
      (JsPath \ "defaultcard").readNullable[String] and
      (JsPath \ "drawmessages").readNullable[StringField] and
      (JsPath \ "defaultdrawmessages").readNullable[StringField] and
      (JsPath \ "draws").readNullable[Int] and
      (JsPath \ "forlegacyfamily").readNullable[String]
    )(Desk.applyInternalRead _)

  lazy implicit val deskWriter: Writes[Desk] = (
    (JsPath \ "id").write[String] and
      (JsPath \ "label").writeNullable[String] and
      (JsPath \ "description").writeNullable[String] and
      (JsPath \ "comments").writeNullable[String] and
      (JsPath \ "spec").writeNullable[List[String]] and
      (JsPath \ "resetonexhaustion").writeNullable[Boolean] and
      (JsPath \ "defaultcard").writeNullable[String] and
      (JsPath \ "drawmessages").writeNullable[StringField] and
      (JsPath \ "defaultdrawmessages").writeNullable[StringField] and
      (JsPath \ "draws").writeNullable[Int] and
      (JsPath \ "forlegacyfamily").writeNullable[String]
    ) (unlift(Desk.unapply))

  val internalDeskWriter: Writes[Desk] = (
    (JsPath \ "id").writeNullable[String] and
      (JsPath \ "label").writeNullable[String] and
      (JsPath \ "description").writeNullable[String] and
      (JsPath \ "comments").writeNullable[String] and
      (JsPath \ "spec").writeNullable[List[String]] and
      (JsPath \ "resetonexhaustion").writeNullable[Boolean] and
      (JsPath \ "defaultcard").writeNullable[String] and
      (JsPath \ "drawmessages").writeNullable[StringField] and
      (JsPath \ "defaultdrawmessages").writeNullable[StringField] and
      (JsPath \ "draws").writeNullable[Int] and
      (JsPath \ "forlegacyfamily").writeNullable[String]
    ) (unlift(Desk.unapplyInternalDesk))

}
