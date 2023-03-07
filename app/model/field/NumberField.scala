package model.field

import play.api.libs.json.{JsObject, JsResult, JsSuccess, JsValue, Json, Reads, Writes}

/**
 * The card's field with id card and number of it.
 */
class NumberField(var values: Map[String, Int],
                  var comments:Option[String]) {

  def canEqual(a: Any): Boolean = a.isInstanceOf[NumberField]

  override def equals(that: Any): Boolean =
    that match {
      case that: NumberField =>
        that.canEqual(this) &&
          this.values == that.values &&
          this.comments == that.comments
      case _ => false
    }


}

object NumberField {
  def apply(): NumberField = new NumberField(Map(), None)

  def apply(values: Map[String, Int],comments:Option[String]): NumberField = {
    new NumberField(values,comments)
  }

  implicit val fieldReaders: Reads[NumberField] = new Reads[NumberField] {
    def reads(jv: JsValue): JsResult[NumberField] = {
      try {
        val values = collection.mutable.Map[String,Int]()
        var comments:Option[String] = None

        jv.as[JsObject].fields.foreach({case (path,value) => {
          if(path == "comments")
            comments = Option(value.as[String])
          else
            values += (path -> value.as[Int])
        }})
        JsSuccess(NumberField(values.toMap,comments))
      }
      catch {
        case e: Exception =>
          throw e
      }
    }
  }

  implicit val fieldWrither: Writes[NumberField] = new Writes[NumberField] {
    def writes(o: NumberField): JsValue = {
      var obj = Json.obj()
      o.values.foreach(tuple => obj = obj + (tuple._1 -> Json.toJson(tuple._2)))
      if(o.comments.isDefined)
        obj = obj + ("comments" -> Json.toJson(o.comments.get))
      obj
    }
  }

}