package model.field

import model.AppException
import play.api.libs.json._

class XTriggers {

  var transformObj: Option[Map[String, String]] = None

  def setTransformObj(pTransformObj: Map[String, String]): Unit = {
    transformObj = Option(pTransformObj)
  }

  def getTransformObj: Map[String, String] = {
    transformObj.getOrElse(Map[String, String]())
  }

  def isTransformObjDefined: Boolean = transformObj.isDefined

  var xTriggerObj: Option[Map[String, List[XTriggersField]]] = None

  def setXTriggerObj(pXTriggerObj: Map[String, List[XTriggersField]]): Unit = {
    xTriggerObj = Option(pXTriggerObj)
  }

  def getXTriggerObj: Map[String, List[XTriggersField]] = {
    xTriggerObj.getOrElse(Map[String, List[XTriggersField]]())
  }

  def isXTriggerObjDefined: Boolean = xTriggerObj.isDefined

  def canEqual(a: Any):Boolean = a.isInstanceOf[XTriggers]

  override def equals(that: Any): Boolean =
    that match {
      case that: XTriggers => {
        that.canEqual(this) &&
          this.transformObj == that.transformObj &&
          this.xTriggerObj == that.xTriggerObj
      }
      case _ => false
    }

}

object XTriggers {
  def apply(): XTriggers = new XTriggers()

  def apply(pTransformObj: Map[String, String], pXTriggerObj: Map[String, List[XTriggersField]]): XTriggers = {
    val t = new XTriggers()
    t.transformObj = Option(pTransformObj)
    t.xTriggerObj = Option(pXTriggerObj)
    t
  }

  implicit val xTriggersReader: Reads[XTriggers] = new Reads[XTriggers] {
    def reads(jv: JsValue): JsResult[XTriggers] = {
      try {
        val transformObj = collection.mutable.Map[String, String]()
        val xTriggerObj = collection.mutable.Map[String, List[XTriggersField]]()

        jv.as[JsObject].fields.foreach({ case (path, value) =>
          value.validate[String] match {
            case JsSuccess(v, p) =>
              transformObj += (path -> v)
            case e: JsError =>
              value.validate[List[XTriggersField]] match {
                case JsSuccess(v, p) =>
                  xTriggerObj += (path -> v)
                case e: JsError =>
                  throw AppException("xTrigger unknown field format")
              }
          }
        })

        JsSuccess(XTriggers(transformObj.toMap, xTriggerObj.toMap))
      }
      catch {
        case e: Exception =>
          throw e
      }
    }
  }

  implicit val xTriggersWrither: Writes[XTriggers] = new Writes[XTriggers] {
    def writes(o: XTriggers): JsValue = {
      var obj = Json.obj()
      if (o.transformObj.isDefined)
        o.transformObj.get.foreach(tuple => obj = obj + (tuple._1 -> Json.toJson(tuple._2)))

      if (o.xTriggerObj.isDefined)
        o.xTriggerObj.get.foreach(tuple => obj = obj + (tuple._1 -> Json.toJson(tuple._2)))
      obj
    }
  }
}
