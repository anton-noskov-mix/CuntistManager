package model.card

import model.field.Slot
import play.api.libs.functional.syntax.{toFunctionalBuilderOps, unlift}
import play.api.libs.json.{JsPath, Reads, Writes}

class Verb(pId: String) extends Entity(pId) {

  override val collectionName: String = "verbs"

  /**
   * Does not seem to have any effect.
   */
  private var atStart: Option[Boolean] = None

  def setAtStart(pAtStart: Boolean): Unit = {
    atStart = Option(pAtStart)
  }

  def getAtStart: Boolean = {
    atStart.getOrElse(false)
  }

  def isAtStartDefined: Boolean = atStart.isDefined

  /**
   * erb tiles can only have 1 slot on their own, but you can create additional slots by assigning them to a card
   * element instead.
   */
  private var slot: Option[Slot] = None

  def setSlot(pSlot: Slot): Unit = {
    slot = Option(pSlot)
  }

  def getSlot: Slot = {
    slot.getOrElse(Slot(""))
  }

  def isSlotDefined: Boolean = slot.isDefined

  /**
   * Icon for verb
   */
  private var icon: Option[String] = None

  def setIcon(pIcon: String): Unit = {
    icon = Option(pIcon)
  }

  def getIcon: String = {
    icon.getOrElse("")
  }

  def isIconDefined: Boolean = icon.isDefined

  def canEqual(a: Any):Boolean = a.isInstanceOf[Verb]

  override def equals(that: Any): Boolean =
    that match {
      case that: Verb => {
        that.canEqual(this) &&
          this.id == that.id &&
          this.label == that.label &&
          this.description == that.description &&
          this.comments == that.comments &&
          this.icon == that.icon &&
          this.atStart == that.atStart &&
          this.slot == that.slot
      }
      case _ => false
    }

  // Fields
  //  icon
  //  description
  //  atStart
  //  id
  //  slot
  //  label
}

object Verb {
  def apply(pId:String): Verb = new Verb(pId)

  private def applyRead(
                         pId: String,
                         pLabel: Option[String],
                         pDescription: Option[String],
                         pComments: Option[String],
                         pAtStart: Option[Boolean],
                         pSlot: Option[Slot],
                         pIcon: Option[String]
                       ): Verb = {
    val v = new Verb(pId)
    v.label = pLabel
    v.description = pDescription
    v.comments = pComments
    v.atStart = pAtStart
    v.slot = pSlot
    v.icon = pIcon
    v
  }

  def unapply(v: Verb): Option[
    (String,
      Option[String],
      Option[String],
      Option[String],
      Option[Boolean],
      Option[Slot],
      Option[String])] = {
    Option(v.id,
      v.label,
      v.description,
      v.comments,
      v.atStart,
      v.slot,
      v.icon)
  }


  implicit val verbReader: Reads[Verb] = (
    (JsPath \ "id").read[String] and
      (JsPath \ "label").readNullable[String] and
      (JsPath \ "description").readNullable[String] and
      (JsPath \ "comments").readNullable[String] and
      (JsPath \ "atStart").readNullable[Boolean] and
      (JsPath \ "slot").readNullable[Slot](Slot.slotReader) and
      (JsPath \ "icon").readNullable[String]
    ) (Verb.applyRead _)

  implicit val verbWriter: Writes[Verb] = (
    (JsPath \ "id").write[String] and
      (JsPath \ "label").writeNullable[String] and
      (JsPath \ "description").writeNullable[String] and
      (JsPath \ "comments").writeNullable[String] and
      (JsPath \ "atStart").writeNullable[Boolean] and
      (JsPath \ "slot").writeNullable[Slot] and
      (JsPath \ "icon").writeNullable[String]
    ) (unlift(Verb.unapply))

}
