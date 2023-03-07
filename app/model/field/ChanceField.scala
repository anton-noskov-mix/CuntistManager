package model.field

import play.api.libs.functional.syntax.{toFunctionalBuilderOps, unlift}
import play.api.libs.json.{JsPath, Reads, Writes}


/**
 * The card's id and the chance that the card will be induce.
 */
class ChanceField(var id: String,
                  var chance: Int) {

  def canEqual(a: Any):Boolean = a.isInstanceOf[ChanceField]

  override def equals(that: Any): Boolean =
    that match {
      case that: ChanceField => {
        that.canEqual(this) &&
          this.id == that.id &&
          this.chance == that.chance
      }
      case _ => false
    }

}

object ChanceField {
  def apply(): ChanceField = new ChanceField("",0)

  def apply(pId:String,pChance:Int): ChanceField = new ChanceField(pId,pChance)

  private def applyRead(
                         pId: String,
                         pChance: Int
                       ): ChanceField = {
    new ChanceField(pId,pChance)
  }

  def unapply(c: ChanceField): Option[(String, Int)] = {
    Option(c.id, c.chance)
  }


  implicit val chanceFieldReader: Reads[ChanceField] = (
    (JsPath \ "id").read[String] and
      (JsPath \ "chance").read[Int]
    ) (ChanceField.applyRead _)

  implicit val chanceFieldWriter: Writes[ChanceField] = (
    (JsPath \ "id").write[String] and
      (JsPath \ "chance").write[Int]
    ) (unlift(ChanceField.unapply))


}
