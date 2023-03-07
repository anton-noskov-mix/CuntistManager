package model.field

import play.api.libs.functional.syntax.{toFunctionalBuilderOps, unlift}
import play.api.libs.json.{JsPath, Reads, Writes}

class Consequence( var id:String,
                   var topath:Option[String],
                   var deckeffects:Option[NumberField]) {

  def canEqual(a: Any): Boolean = a.isInstanceOf[Consequence]

  override def equals(that: Any): Boolean =
    that match {
      case that: Consequence =>
        that.canEqual(this) &&
          this.id == that.id &&
          this.topath == that.topath &&
          this.deckeffects == that.deckeffects
      case _ => false
    }

}

object Consequence {
  def apply(): Consequence = new Consequence("",None,None)

  def apply(pId:String,pToPath:Option[String],pDeckEffects:Option[NumberField]): Consequence = new Consequence(pId,pToPath,pDeckEffects)


  def unapply(c: Consequence): Option[
    (String,
      Option[String],
      Option[NumberField]
      )] = {
    Option(
      c.id,
      c.topath,
      c.deckeffects)
  }

  private def applyRead(
                         pId: String,
                         pToPath: Option[String],
                         pDeckEffects:Option[NumberField]
                       ): Consequence = {
    new Consequence(pId,pToPath,pDeckEffects)
  }


  lazy implicit val consequenceReader: Reads[Consequence] = (
    (JsPath \ "id").read[String] and
      (JsPath \ "topath").readNullable[String] and
      (JsPath \ "deckeffects").readNullable[NumberField]
    ) (Consequence.applyRead _)

  lazy implicit val consequenceWriter: Writes[Consequence] = (
    (JsPath \ "id").write[String] and
      (JsPath \ "topath").writeNullable[String] and
      (JsPath \ "deckeffects").writeNullable[NumberField]
    ) (unlift(Consequence.unapply))

}
