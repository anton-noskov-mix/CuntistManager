package model.field

import play.api.libs.functional.syntax.{toFunctionalBuilderOps, unlift}
import play.api.libs.json._

class XTriggersField(private var id: String) {

  def setId(pId: String): Unit = {
    id = pId
  }

  def getId: String = {
    id
  }

  private var morpheffect: Option[String] = None

  def setMorphEffect(pMorphEffect: String): Unit = {
    morpheffect = Option(pMorphEffect)
  }

  def getMorphEffect: String = {
    morpheffect.getOrElse("")
  }

  def isMorphEffectDefined: Boolean = morpheffect.isDefined

  private var level: Option[Int] = None

  def setLevel(pLevel:Int): Unit ={
    level = Option(pLevel)
  }

  def getLevel:Int ={
    level.getOrElse(0)
  }

  def isLevelDefined:Boolean = level.isDefined

  private var chance: Option[Int] = None

  def setChance(pChance:Int): Unit ={
    chance = Option(pChance)
  }

  def getChance:Int ={
    chance.getOrElse(0)
  }

  def isChanceDefined:Boolean = chance.isDefined

  def canEqual(a: Any):Boolean = a.isInstanceOf[XTriggersField]

  override def equals(that: Any): Boolean =
    that match {
      case that: XTriggersField => {
        that.canEqual(this) &&
          this.id == that.id &&
          this.morpheffect == that.morpheffect &&
          this.level == that.level &&
          this.chance == that.chance
      }
      case _ => false
    }


  //id
  //morpheffect
  //level
  //chance


}

object XTriggersField {
  def apply(pId:String): XTriggersField = new XTriggersField(pId)

  private def applyRead(
                         pId: String,
                         pMorphEffect: Option[String],
                         pLevel: Option[Int],
                         pChance: Option[Int]
                       ): XTriggersField = {
    val x = new XTriggersField(pId)
    x.morpheffect = pMorphEffect
    x.level = pLevel
    x.chance = pChance
    x
  }

  def unapply(x: XTriggersField): Option[(String,
    Option[String],
    Option[Int],
    Option[Int])] = {
    Option(x.id,
      x.morpheffect,
      x.level,
      x.chance)
  }


  implicit val xTriggersFieldReader: Reads[XTriggersField] = (
    (JsPath \ "id").read[String] and
      (JsPath \ "morpheffect").readNullable[String] and
      (JsPath \ "level").readNullable[Int] and
      (JsPath \ "chance").readNullable[Int]
    ) (XTriggersField.applyRead _)

  implicit val xTriggersFieldWrither: Writes[XTriggersField] = (
    (JsPath \ "id").write[String] and
      (JsPath \ "morpheffect").writeNullable[String] and
      (JsPath \ "level").writeNullable[Int] and
      (JsPath \ "chance").writeNullable[Int]
    ) (unlift(XTriggersField.unapply))

//  implicit val xTriggersFieldWrither: Writes[XTriggersField] = new Writes[XTriggersField] {
//    def writes(o: XTriggersField): JsValue = {
//      var obj = Json.obj()
//      obj = obj + ("id" -> Json.toJson(o.id))
//      if (o.morpheffect != null)
//        obj = obj + ("morpheffect" -> Json.toJson(o.morpheffect))
//      if (o.level != null)
//        obj = obj + ("level" -> Json.toJson(o.level.asInstanceOf[Int]))
//      if (o.chance != null)
//        obj = obj + ("chance" -> Json.toJson(o.chance.asInstanceOf[Int]))
//      obj
//    }
//  }

}
