package model.field

import play.api.libs.functional.syntax.{toFunctionalBuilderOps, unlift}
import play.api.libs.json._

class Mutation {


  private var filterOnAspectId: Option[String] = None

  def setFilterOnAspectId(pFilterOnAspectId: String): Unit = {
    filterOnAspectId = Option(pFilterOnAspectId)
  }

  def getFilterOnAspectId: String = {
    filterOnAspectId.getOrElse("")
  }

  def isFilterOnAspectIdDefined: Boolean = filterOnAspectId.isDefined

  private var mutateAspectId: Option[String] = None

  def setMutateAspectId(pMutateAspectId: String): Unit = {
    mutateAspectId = Option(pMutateAspectId)
  }

  def getMutateAspectId: String = {
    mutateAspectId.getOrElse("")
  }

  def isMutateAspectIdDefined: Boolean = mutateAspectId.isDefined

  private var mutationLevel: Option[Int] = None

  def setMutationLevel(pMutationLevel: Int): Unit = {
    mutationLevel = Option(pMutationLevel)
  }

  def getMutationLevel: Int = {
    mutationLevel.getOrElse(0)
  }

  def isMutationLevelDefined: Boolean = mutationLevel.isDefined

  private var additive: Option[Boolean] = None

  def setAdditive(pAdditive: Boolean): Unit = {
    additive = Option(pAdditive)
  }

  def getAdditive: Boolean = {
    additive.getOrElse(false)
  }

  def isAdditiveDefined: Boolean = additive.isDefined

  private var filter: Option[String] = None

  def setFilter(pFilter: String): Unit = {
    filter = Option(pFilter)
  }

  def getFilter: String = {
    filter.getOrElse("")
  }

  def isFilterDefined: Boolean = filter.isDefined

  private var mutate: Option[String] = None

  def setMutate(pMutate: String): Unit = {
    mutate = Option(pMutate)
  }

  def getMutate: String = {
    mutate.getOrElse("")
  }

  def isMutateDefined: Boolean = mutate.isDefined

  private var level: Option[Int] = None

  def setLevel(pLevel: Int): Unit = {
    level = Option(pLevel)
  }

  def getLevel: Int = {
    level.getOrElse(0)
  }

  def isLevelDefined: Boolean = level.isDefined

  private var additional: Option[Boolean] = None

  def setAdditional(pAdditional: Boolean): Unit = {
    additional = Option(pAdditional)
  }

  def getAdditional: Boolean = {
    additional.getOrElse(false)
  }

  def isAdditionalDefined: Boolean = additional.isDefined

  def canEqual(a: Any):Boolean = a.isInstanceOf[Mutation]

  override def equals(that: Any): Boolean =
    that match {
      case that: Mutation => {
        that.canEqual(this) &&
          this.filterOnAspectId == that.filterOnAspectId &&
          this.mutateAspectId == that.mutateAspectId &&
          this.mutationLevel == that.mutationLevel &&
          this.additive == that.additive &&
          this.filter == that.filter &&
          this.mutate == that.mutate &&
          this.level == that.level &&
          this.additional == that.additional
      }
      case _ => false
    }

  //filterOnAspectId
  //mutateAspectId
  //mutationLevel
  //additive
  //filter
  //mutate
  //level
  //additional


}

object Mutation {
  def apply(): Mutation = new Mutation()

  private def applyRead(
                         pFilterOnAspectId: Option[String],
                         pMutateAspectId: Option[String],
                         pMutationLevel: Option[Int],
                         pAdditive: Option[Boolean],
                         pFilter: Option[String],
                         pMutate: Option[String],
                         pLevel: Option[Int],
                         pAdditional: Option[Boolean]
                       ): Mutation = {
    val m = new Mutation()
    m.filterOnAspectId = pFilterOnAspectId
    m.mutateAspectId = pMutateAspectId
    m.mutationLevel = pMutationLevel
    m.additive = pAdditive
    m.filter = pFilter
    m.mutate = pMutate
    m.level = pLevel
    m.additional = pAdditional
    m
  }

  def unapply(m: Mutation): Option[
    (Option[String],
      Option[String],
      Option[Int],
      Option[Boolean],
      Option[String],
      Option[String],
      Option[Int],
      Option[Boolean])] = {
    Option(m.filterOnAspectId,
      m.mutateAspectId,
      m.mutationLevel,
      m.additive,
      m.filter,
      m.mutate,
      m.level,
      m.additional)
  }


  implicit val verbReader: Reads[Mutation] = (
    (JsPath \ "filterOnAspectId").readNullable[String] and
      (JsPath \ "mutateAspectId").readNullable[String] and
      (JsPath \ "mutationLevel").readNullable[Int] and
      (JsPath \ "additive").readNullable[Boolean] and
      (JsPath \ "filter").readNullable[String] and
    (JsPath \ "mutate").readNullable[String] and
    (JsPath \ "level").readNullable[Int] and
    (JsPath \ "additional").readNullable[Boolean]
  ) (Mutation.applyRead _)

  implicit val verbWriter: Writes[Mutation] = (
    (JsPath \ "filterOnAspectId").writeNullable[String] and
      (JsPath \ "mutateAspectId").writeNullable[String] and
      (JsPath \ "mutationLevel").writeNullable[Int] and
      (JsPath \ "additive").writeNullable[Boolean] and
      (JsPath \ "filter").writeNullable[String] and
      (JsPath \ "mutate").writeNullable[String] and
      (JsPath \ "level").writeNullable[Int] and
      (JsPath \ "additional").writeNullable[Boolean]
    ) (unlift(Mutation.unapply))

//
//  implicit val mutationReader: Reads[Mutation] = new Reads[Mutation] {
//    def reads(jv: JsValue): JsResult[Mutation] = {
//      val m = Mutation()
//      val filterOnAspectId = (jv \ "filterOnAspectId").asOpt[String]
//      if (filterOnAspectId.isDefined)
//        m.filterOnAspectId = filterOnAspectId.get
//      val mutateAspectId = (jv \ "mutateAspectId").asOpt[String]
//      if (mutateAspectId.isDefined)
//        m.mutateAspectId = mutateAspectId.get
//      val mutationLevel = (jv \ "mutationLevel").asOpt[Int]
//      if (mutationLevel.isDefined)
//        m.mutationLevel = mutationLevel.get
//      val additive = (jv \ "additive").asOpt[Boolean]
//      if (additive.isDefined)
//        m.additive = additive.get
//      val filter = (jv \ "filter").asOpt[String]
//      if (filter.isDefined)
//        m.filter = filter.get
//      val mutate = (jv \ "mutate").asOpt[String]
//      if (mutate.isDefined)
//        m.mutate = mutate.get
//      val level = (jv \ "level").asOpt[Int]
//      if (level.isDefined)
//        m.level = level.get
//      val additional = (jv \ "additional").asOpt[Boolean]
//      if (additional.isDefined)
//        m.additional = additional.get
//      JsSuccess(m)
//    }
//  }
//
//  implicit val mutationWrither: Writes[Mutation] = new Writes[Mutation] {
//    def writes(o: Mutation): JsValue = {
//      var obj = Json.obj()
//      if (o.filterOnAspectId != null)
//        obj = obj + ("filterOnAspectId" -> Json.toJson(o.filterOnAspectId))
//      if (o.mutateAspectId != null)
//        obj = obj + ("mutateAspectId" -> Json.toJson(o.mutateAspectId))
//      if (o.mutationLevel != null)
//        obj = obj + ("mutationLevel" -> Json.toJson(o.mutationLevel.asInstanceOf[Int]))
//      if (o.additive != null)
//        obj = obj + ("additive" -> Json.toJson(o.additive.asInstanceOf[Boolean]))
//      if (o.filter != null)
//        obj = obj + ("filter" -> Json.toJson(o.filter))
//      if (o.mutate != null)
//        obj = obj + ("mutate" -> Json.toJson(o.mutate))
//      if (o.level != null)
//        obj = obj + ("level" -> Json.toJson(o.level.asInstanceOf[Int]))
//      if (o.additional != null)
//        obj = obj + ("additional" -> Json.toJson(o.additional.asInstanceOf[Boolean]))
//      obj
//    }
//  }

}
