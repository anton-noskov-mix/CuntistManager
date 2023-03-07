package model.field

import play.api.libs.json._

//Displays a description text based on the card that was drawn.
class StringField(var values: Map[String, String]) {

  def canEqual(a: Any): Boolean = a.isInstanceOf[StringField]

  override def equals(that: Any): Boolean =
    that match {
      case that: StringField =>
        that.canEqual(this) &&
        this.values == that.values
      case _ => false
    }

}

object StringField {
  def apply(): StringField = new StringField(Map())

  def apply(values: Map[String, String]): StringField = {
    new StringField(values)
  }

  implicit val drawMessagesReader: Reads[StringField] = new Reads[StringField] {
    def reads(jv: JsValue): JsResult[StringField] = {
      try {
        JsSuccess(StringField(jv.as[Map[String, String]]))
      }
      catch {
        case e: Exception =>
          JsError()
      }
    }
  }

  implicit val drawMessagesWrither: Writes[StringField] = new Writes[StringField] {
    def writes(o: StringField): JsValue = {
      var obj = Json.obj()
      o.values.foreach(tuple => obj = obj + (tuple._1 -> Json.toJson(tuple._2)))
      obj
    }
  }
}
