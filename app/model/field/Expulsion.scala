package model.field

import play.api.libs.functional.syntax.{toFunctionalBuilderOps, unlift}
import play.api.libs.json.{JsPath, JsResult, JsSuccess, JsValue, Json, Reads, Writes}

class Expulsion {

  var limit: Option[Int] = None

  def setLimit(pLimit:Int): Unit ={
    limit = Option(pLimit)
  }

  def getLimit:Int ={
    limit.getOrElse(0)
  }

  def isLimitDefined:Boolean = limit.isDefined

  var filter: Option[NumberField] = None

  def setFilter(pFilter: NumberField): Unit = {
    filter = Option(pFilter)
  }

  def getFilter:NumberField ={
    filter.getOrElse(NumberField())
  }

  def isFilterDefined:Boolean = filter.isDefined

  def canEqual(a: Any):Boolean = a.isInstanceOf[Expulsion]

  override def equals(that: Any): Boolean =
    that match {
      case that: Expulsion => {
        that.canEqual(this) &&
          this.limit == that.limit &&
          this.filter == that.filter
      }
      case _ => false
    }

}

object Expulsion {
  def apply(): Expulsion = new Expulsion()

  private def applyRead(
                         pLimit: Option[Int],
                         pFilter: Option[NumberField]
                       ): Expulsion = {
    val e = new Expulsion()
    e.limit = pLimit
    e.filter = pFilter
    e
  }

  def unapply(e: Expulsion): Option[(Option[Int], Option[NumberField])] = {
    Option(e.limit, e.filter)
  }


  implicit val verbReader: Reads[Expulsion] = (
    (JsPath \ "limit").readNullable[Int] and
      (JsPath \ "filter").readNullable[NumberField]
    ) (Expulsion.applyRead _)

  implicit val verbWriter: Writes[Expulsion] = (
    (JsPath \ "limit").writeNullable[Int] and
      (JsPath \ "filter").writeNullable[NumberField]
    ) (unlift(Expulsion.unapply))

//  implicit val expulsionReader: Reads[Expulsion] = new Reads[Expulsion] {
//    def reads(jv: JsValue): JsResult[Expulsion] = {
//      val e = Expulsion()
//      val limit = (jv \ "limit").asOpt[Int]
//      if (limit.isDefined)
//        e.limit = limit.get
//      val filter = (jv \ "filter").asOpt[NumberField]
//      if (filter.isDefined)
//        e.filter = filter.get
//
//      JsSuccess(e)
//    }
//  }
//
//  implicit val expulsionWrither: Writes[Expulsion] = new Writes[Expulsion] {
//    def writes(o: Expulsion): JsValue = {
//      var obj = Json.obj()
//      if (o.limit != null)
//        obj = obj + ("limit" -> Json.toJson(o.limit.asInstanceOf[Int]))
//      if (o.filter != null)
//        obj = obj + ("filter" -> Json.toJson(o.filter))
//      obj
//    }
//  }
}
