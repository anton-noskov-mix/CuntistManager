package model.field

import play.api.libs.json.{JsError, JsObject, JsResult, JsSuccess, JsValue, Json, Reads, Writes}

class NumberAndStringField(var numberValues: Map[String, Int],var stringValues: Map[String, String]) {

  def canEqual(a: Any):Boolean = a.isInstanceOf[NumberAndStringField]

  override def equals(that: Any): Boolean =
    that match {
      case that: NumberAndStringField => {
        that.canEqual(this) &&
          this.numberValues == that.numberValues &&
          this.stringValues == that.stringValues
      }
      case _ => false
    }

}

object NumberAndStringField {

  def apply() = new NumberAndStringField(Map(),Map())

  def apply(numberValues: Map[String, Int], stringValues: Map[String, String]): NumberAndStringField = {
    new NumberAndStringField(numberValues,stringValues)
  }

  implicit val numberAndStringFieldReaders: Reads[NumberAndStringField] = new Reads[NumberAndStringField] {
    def reads(jv: JsValue): JsResult[NumberAndStringField] = {
      try {
        val nvalues = collection.mutable.Map[String, Int]()
        val svalues = collection.mutable.Map[String, String]()
        jv.as[JsObject].fields.foreach({ case (path, value) => {
          value.validate[String] match {
            case JsSuccess(p, v) =>
              svalues += (path -> value.as[String])
            case e: JsError =>
              value.validate[Int] match {
                case JsSuccess(p, v) =>
                  nvalues += (path -> value.as[Int])
                case e: JsError =>
              }
          }
        }
        })
        JsSuccess(NumberAndStringField(nvalues.toMap, svalues.toMap))
      }
      catch {
        case e: Exception =>
          throw e
      }
    }
  }

  implicit val numberAndStringFieldWrither: Writes[NumberAndStringField] = new Writes[NumberAndStringField] {
    def writes(o: NumberAndStringField): JsValue = {
      var obj = Json.obj()
      o.numberValues.foreach(tuple => obj = obj + (tuple._1 -> Json.toJson(tuple._2)))
      o.stringValues.foreach(tuple => obj = obj + (tuple._1 -> Json.toJson(tuple._2)))
      obj
    }
  }
}
