package model.field

import model.card.Entity
import model.enums.ActionIdFields
import model.enums.ActionIdFields.ActionIdFields
import play.api.libs.functional.syntax.{toFunctionalBuilderOps, unlift}
import play.api.libs.json.JsonNaming.SnakeCase
import play.api.libs.json.{Format, JsObject, JsPath, JsResult, JsSuccess, JsValue, JsonConfiguration, Reads, Writes}

import scala.util.parsing.input.Reader

class Slot(pId:String) extends Entity(pId) {

  override val collectionName: String = ""

  /**
   * If the slot is part of a card and the card enters this verb tile, the slot will appear.
   */
  private var actionId: Option[String] = None
  private var actionid: Option[String] = None

  def setActionId(pActionId:String,field:ActionIdFields = ActionIdFields.actionId): Unit ={
    if(actionId.isDefined)
      actionId = Option(pActionId)
    else if(actionid.isDefined)
      actionid = Option(pActionId)
    else {
      field match {
        case ActionIdFields.actionId =>
          actionId = Option(pActionId)
        case ActionIdFields.actionid =>
          actionid = Option(pActionId)
      }
    }
  }

  def getActionId:String ={
    actionId.getOrElse(actionid.getOrElse(""))
  }

  def isActionIdDefined:Boolean = actionId.isDefined || actionid.isDefined

  def getActionIdFieldName:String = if(actionid.isDefined) "actionid" else "actionId"

  /**
   * The slot will accept cards with any of these elements.
   */
  private var required: Option[NumberField] = None

  def setRequired(pRequired: NumberField): Unit ={
    required = Option(pRequired)
  }

  def getRequired:NumberField ={
    required.getOrElse(NumberField())
  }

  def isRequiredDefined:Boolean = required.isDefined

  /**
   * The slot will not accept cards with any of these elements.
   */
  private var forbidden: Option[NumberField] = None

  def setForbidden(pForbidden:NumberField): Unit ={
    forbidden = Option(pForbidden)
  }

  def getForbidden:NumberField ={
    forbidden.getOrElse(NumberField())
  }

  def isForbiddenDefined:Boolean = forbidden.isDefined

  /**
   * Tells the slot to automatically grab a matching card.
   * Which card is prioritised?
   * Random or does it always grab the oldest card?
   */
  private var greedy: Option[Boolean] = None

  def setGreedy(pGreedy:Boolean): Unit ={
    greedy = Option(pGreedy)
  }

  def getGreedy:Boolean ={
    greedy.getOrElse(false)
  }

  def isGreedyDefined:Boolean = greedy.isDefined

  /**
   * ???
   */
  private var noanim: Option[Boolean] = None

  def setNoAnim(pNoAnim:Boolean): Unit ={
    noanim = Option(pNoAnim)
  }

  def getNoAnim:Boolean ={
    noanim.getOrElse(false)
  }

  def isNoAnimDefined:Boolean = noanim.isDefined

  /**
   * The card in this slot will disappear after the recipe finishes. Used in rites.
   */
  private var consumes: Option[Boolean] = None

  def setConsumes(pConsumes:Boolean): Unit ={
    consumes = Option(pConsumes)
  }

  def getConsumes:Boolean ={
    consumes.getOrElse(false)
  }

  def isConsumesDefined:Boolean = consumes.isDefined

  //This field is needed???
  private var unique: Option[Boolean] = None

  def setUnique(pUnique:Boolean): Unit ={
    unique = Option(pUnique)
  }

  def getUnique:Boolean ={
    unique.getOrElse(false)
  }

  def isUniqueDefined:Boolean = unique.isDefined

  def canEqual(a: Any):Boolean = a.isInstanceOf[Slot]

  override def equals(that: Any): Boolean =
    that match {
      case that: Slot => {
        that.canEqual(this) &&
          this.id == that.id &&
          this.label == that.label &&
          this.description == that.description &&
          this.comments == that.comments &&
          this.actionId == that.actionId &&
          this.actionid == that.actionid &&
          this.required == that.required &&
          this.forbidden == that.forbidden &&
          this.greedy == that.greedy &&
          this.noanim == that.noanim &&
          this.consumes == that.consumes &&
          this.unique == that.unique
      }
      case _ => false
    }

  // description
  // id
  // label
  // comments
  // actionId
  // actionid
  // required
  // forbidden
  // greedy
  // noanim
  // consumes
  // unique
}

object Slot {

  def apply(pId:String): Slot = new Slot(pId)

  private def applyRead(
                         pId: String,
                         pLabel: Option[String],
                         pDescription: Option[String],
                         pComments: Option[String],
                         pActionId: Option[String],
                         pActionid: Option[String],
                         pRequired: Option[NumberField],
                         pForbidden: Option[NumberField],
                         pGreedy: Option[Boolean],
                         pNoanim: Option[Boolean],
                         pConsumes: Option[Boolean],
                         pUnique:Option[Boolean]
                       ): Slot = {
    val s = new Slot(pId)
      s.label = pLabel
      s.description = pDescription
      s.comments = pComments
      s.actionId = pActionId
      s.actionid = pActionid
      s.required = pRequired
      s.forbidden = pForbidden
      s.greedy = pGreedy
      s.noanim = pNoanim
      s.consumes = pConsumes
      s.unique = pUnique
    s
  }

  def unapply(s: Slot): Option[(String,
    Option[String],
    Option[String],
    Option[String],
    Option[String],
    Option[String],
    Option[NumberField],
    Option[NumberField],
    Option[Boolean],
    Option[Boolean],
    Option[Boolean],
    Option[Boolean])] = {
    Option(s.id,
      s.label,
      s.description,
      s.comments,
      s.actionId,
      s.actionid,
      s.required,
      s.forbidden,
      s.greedy,
      s.noanim,
      s.consumes,
      s.unique)
  }

  implicit val slotReader: Reads[Slot] = (
    (JsPath \ "id").read[String] and
      (JsPath \ "label").readNullable[String] and
      (JsPath \ "description").readNullable[String] and
      (JsPath \ "comments").readNullable[String] and
      (JsPath \ "actionId").readNullable[String] and
      (JsPath \ "actionid").readNullable[String] and
      (JsPath \ "required").readNullable[NumberField] and
      (JsPath \ "forbidden").readNullable[NumberField] and
      (JsPath \ "greedy").readNullable[Boolean] and
      (JsPath \ "noanim").readNullable[Boolean] and
      (JsPath \ "consumes").readNullable[Boolean] and
      (JsPath \ "unique").readNullable[Boolean]
    ) (Slot.applyRead _)

  implicit val slotWriter: Writes[Slot] = (
    (JsPath \ "id").write[String] and
      (JsPath \ "label").writeNullable[String] and
      (JsPath \ "description").writeNullable[String] and
      (JsPath \ "comments").writeNullable[String] and
      (JsPath \ "actionId").writeNullable[String] and
      (JsPath \ "actionid").writeNullable[String] and
      (JsPath \ "required").writeNullable[NumberField] and
      (JsPath \ "forbidden").writeNullable[NumberField] and
      (JsPath \ "greedy").writeNullable[Boolean] and
      (JsPath \ "noanim").writeNullable[Boolean] and
      (JsPath \ "consumes").writeNullable[Boolean] and
      (JsPath \ "unique").writeNullable[Boolean]
    ) (unlift(Slot.unapply))
}
