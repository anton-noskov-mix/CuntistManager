package model.card

import model.field.{Consequence, NumberField}
import play.api.libs.functional.syntax.{toFunctionalBuilderOps, unlift}
import play.api.libs.json.{JsPath, Reads, Writes}

class Portal(pId:String) extends Entity(pId) {

  override val collectionName: String = "portals"

  private var icon:Option[String] = None

  def setIcon(pIcon:String): Unit ={
    icon = Option(pIcon)
  }

  def getIcon:String = {
    icon.getOrElse("")
  }

  def isIconDefined:Boolean = icon.isDefined

  private var otherworldid:Option[String] = None

  def setOtherWorldId(pOtherWorldId:String): Unit ={
    otherworldid = Option(pOtherWorldId)
  }

  def getOtherWorldId:String ={
    otherworldid.getOrElse("")
  }

  def isOtherWorldIdDefined:Boolean = otherworldid.isDefined

  private var egressid:Option[String] = None

  def setEgressId(pEgressId:String): Unit ={
    egressid = Option(pEgressId)
  }

  def getEgressId:String = {
    egressid.getOrElse("")
  }

  def isEgressIdDefined:Boolean = egressid.isDefined

  private var consequences:Option[List[Consequence]] = None

  def setConsequences(pConsequences:List[Consequence]): Unit ={
    consequences = Option(pConsequences)
  }

  def getConsequences:List[Consequence] ={
    consequences.getOrElse(List[Consequence]())
  }

  def isConsequencesDefined:Boolean = consequences.isDefined

  def canEqual(a: Any):Boolean = a.isInstanceOf[Portal]

  override def equals(that: Any): Boolean =
    that match {
      case that: Portal => {
        that.canEqual(this) &&
          this.id == that.id &&
          this.label == that.label &&
          this.description == that.description &&
          this.comments == that.comments &&
          this.egressid == that.egressid &&
          this.otherworldid == that.otherworldid &&
          this.icon == that.icon &&
          this.consequences == that.consequences
      }
      case _ => false
    }

//  egressid
//  otherworldid
//  icon
//  description
//  consequences
//  id
//  label

}

object Portal {
  def apply(pId:String): Portal = new Portal(pId)


  def unapply(p: Portal): Option[
    (String,
      Option[String],
      Option[String],
      Option[String],
      Option[String],
      Option[String],
      Option[String],
      Option[List[Consequence]]
      )] = {
    Option(
      p.id,
      p.label,
      p.description,
      p.comments,
      p.icon,
      p.otherworldid,
      p.egressid,
      p.consequences)
  }

  private def applyRead(
                         pId: String,
                         pLabel: Option[String],
                         pDescription: Option[String],
                         pComments: Option[String],
                         pIcon: Option[String],
                         pOtherWorldId: Option[String],
                         pEgressId: Option[String],
                         pConsequences:Option[List[Consequence]]
                       ): Portal = {
    val p = new Portal(pId)
    p.label = pLabel
    p.description = pDescription
    p.comments = pComments
    p.icon = pIcon
    p.otherworldid = pOtherWorldId
    p.egressid = pEgressId
    p.consequences = pConsequences
    p
  }


  lazy implicit val portalReader: Reads[Portal] = (
    (JsPath \ "id").read[String] and
      (JsPath \ "label").readNullable[String] and
      (JsPath \ "description").readNullable[String] and
      (JsPath \ "comments").readNullable[String] and
      (JsPath \ "icon").readNullable[String] and
      (JsPath \ "otherworldid").readNullable[String] and
      (JsPath \ "egressid").readNullable[String] and
      (JsPath \ "consequences").readNullable[List[Consequence]]
    ) (Portal.applyRead _)

  lazy implicit val portalWriter: Writes[Portal] = (
    (JsPath \ "id").write[String] and
      (JsPath \ "label").writeNullable[String] and
      (JsPath \ "description").writeNullable[String] and
      (JsPath \ "comments").writeNullable[String] and
      (JsPath \ "icon").writeNullable[String] and
      (JsPath \ "otherworldid").writeNullable[String] and
      (JsPath \ "egressid").writeNullable[String] and
      (JsPath \ "consequences").writeNullable[List[Consequence]]
    ) (unlift(Portal.unapply))
}
