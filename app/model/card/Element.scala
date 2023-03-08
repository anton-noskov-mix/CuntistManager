package model.card

import model.field.{ChanceField, NumberField, Slot, XTriggers}
import play.api.libs.json.{JsResult, JsSuccess, JsValue, Json, Reads, Writes}


class Element(pId:String) extends Entity(pId) {
  /**
   * The card's aspects and their level.
   */
  private var aspects: Option[NumberField] = None

  def setAspects(pAspects:NumberField): Unit ={
    aspects = Option(pAspects)
  }

  def getAspects:NumberField ={
    aspects.getOrElse(NumberField())
  }

  def isAspectsDefined:Boolean = aspects.isDefined

  /**
   * List of recipes and their chances to trigger after any recipe finishes where this element was in some way involved.
   * The element might be an aspect of a recipe or of a card or a card itself. This will spawn multiple recipes as if
   * it had "additional:true". "induces" effects are checked after "xtriggers" effects have already happened.
   * "induces" effects of cards created using a recipe's "effects" are not checked for that recipe.
   */
  private var induces: Option[List[ChanceField]] = None

  def setInduces(pInduces:List[ChanceField]): Unit ={
    induces = Option(pInduces)
  }

  def getInduces:List[ChanceField] ={
  induces.getOrElse(List[ChanceField]())
  }

  def isInducesDefined:Boolean = induces.isDefined

  /**
   * The card's slots.
   */
  private var slots: Option[List[Slot]] = None

  def setSlots(pSlots: List[Slot]):Unit = {
    slots = Option(pSlots)
  }

  def getSlots:List[Slot] ={
  slots.getOrElse(List[Slot]())
  }

  def isSlotsDefined:Boolean = slots.isDefined

  /**
   * List of elements that, when they are in a recipe together with the card, they will transform the card into another
   * one after the recipe finishes. The element might be an aspect of a recipe or of a card or a card itself.
   * When a card would transform to become the same card, it will cause its lifetime to refresh.
   */
  private var xtriggers: Option[XTriggers] = None

  def setXTriggers(pXTriggers:XTriggers): Unit ={
    xtriggers = Option(pXTriggers)
  }

  def getXTriggers:XTriggers ={
    xtriggers.getOrElse(XTriggers())
  }

  def isXTriggersDefined:Boolean = xtriggers.isDefined

  /**
   * Amount of seconds the card lasts for until it disappears.
   */
  private var lifetime: Option[Int] = None

  def setLifeTime(pLifeTime:Int): Unit ={
    lifetime = Option(pLifeTime)
  }

  def getLifeTime:Int ={
    lifetime.getOrElse(0)
  }

  def isLifeTimeDefined:Boolean = lifetime.isDefined

  /**
   * If the card lifetime ran out, it will transform into this card.
   */
  private var decayTo: Option[String] = None

  def setDecayTo(pDecayTo:String): Unit ={
    decayTo = Option(pDecayTo)
  }

  def getDecayTo:String ={
    decayTo.getOrElse("")
  }

  def isDecayTo:Boolean = decayTo.isDefined

  /**
   * Decides if this element is an aspect.
   */
  private var isAspect: Option[Boolean] = None

  def setIsAspect(pIsAspect:Boolean): Unit ={
    isAspect = Option(pIsAspect)
  }

  def getIsAspect:Boolean ={
    isAspect.getOrElse(false)
  }

  def isIsAspectDefine:Boolean = isAspect.isDefined

  /**
   * If true, then there can at most be 1 copy of this card on the board.
   */
  private var unique: Option[Boolean] = None

  def setUnique(pUnique:Boolean): Unit ={
    unique = Option(pUnique)
  }

  def getUnique:Boolean ={
    unique.getOrElse(false)
  }

  def isUniqueDefine:Boolean = unique.isDefined

  /**
   * There can at most be 1 card with the same uniquenessgroup on the board.
   * All uniquenessgroups must be declared as aspects to work properly.
   */
  private var uniquenessgroup: Option[String] = None

  def setUniquenessGroup(pUniquenessGroup:String): Unit ={
    uniquenessgroup = Option(pUniquenessGroup)
  }

  def getUniquenessGroup:String ={
    uniquenessgroup.getOrElse("")
  }

  def isUniquenessGroupDefined:Boolean = uniquenessgroup.isDefined

  /**
   * Causes an aspect to be invisible to the player.
   */
  private var isHidden: Option[Boolean] = None

  def setIsHidden(pIsHidden:Boolean): Unit ={
    isHidden = Option(pIsHidden)
  }

  def getIsHidden:Boolean ={
    isHidden.getOrElse(false)
  }

  def isIsHiddenDefined:Boolean = isHidden.isDefined

  /**
   * The element's picture, as a card. It does not work for aspects.
   */
  private var icon: Option[String] = None

  def setIcon(pIcon:String): Unit ={
    icon = Option(pIcon)
  }

  def getIcon:String ={
    icon.getOrElse("")
  }

  def isIconDefined:Boolean = icon.isDefined

  /**
   * ???
   */
  private var noartneeded: Option[Boolean] = None

  def setNoArtNeeded(pNoArtNeeded:Boolean): Unit ={
    noartneeded = Option(pNoArtNeeded)
  }

  def getNoArtNeeded:Boolean ={
    noartneeded.getOrElse(false)
  }

  def isNoArtNeededDefined:Boolean = noartneeded.isDefined

  /**
   * If a card has a limited lifetime, it normally gradually loses its colors as time goes on,
   * but if this is set to true, then it will slowly gain its colors instead.
   */
  private var resaturate: Option[Boolean] = None

  def setResaturate(pResaturate:Boolean): Unit ={
    resaturate = Option(pResaturate)
  }

  def getResaturate:Boolean ={
    resaturate.getOrElse(false)
  }

  def isResaturateDefine:Boolean = resaturate.isDefined

  /**
   * The card's aspects and their level.
   */
  private var animFrames: Option[Int] = None

  def setAnimFrames(pAnimFrames:Int): Unit ={
    animFrames = Option(pAnimFrames)
  }

  def getAnimFrames:Int ={
    animFrames.getOrElse(0)
  }

  def isAnimFramesDefined:Boolean = animFrames.isDefined

  private var burnTo: Option[String] = None

  def setBurnTo(pBurnTo:String): Unit ={
    burnTo = Option(pBurnTo)
  }

  def getBurnTo:String ={
    burnTo.getOrElse("")
  }

  def isBurnToDefined:Boolean = burnTo.isDefined

  private var manifestationtype: Option[String] = None

  def setManifestationType(pManifestationType:String): Unit ={
    manifestationtype = Option(pManifestationType)
  }

  def getManifestationType:String ={
    manifestationtype.getOrElse("")
  }

  def isManifestationTypeDefine:Boolean = manifestationtype.isDefined

  private var inherits: Option[String] = None

  def setInherits(pInherits:String): Unit ={
    inherits = Option(pInherits)
  }

  def getInherits:String ={
    inherits.getOrElse("")
  }

  def isInheritsDefined:Boolean = inherits.isDefined

  private var verbicon: Option[String] = None

  def setVerbIcon(pVerbIcon:String): Unit ={
    verbicon = Option(pVerbIcon)
  }

  def getVerbIcon:String ={
    verbicon.getOrElse("")
  }

  def isVerbIconDefined:Boolean = verbicon.isDefined

  private var lever: Option[String] = None

  def setLever(pLever:String): Unit ={
    lever = Option(pLever)
  }

  def getLever:String ={
    lever.getOrElse("")
  }

  def isLeverDefined:Boolean = lever.isDefined

  private var metafictional: Option[Boolean] = None

  def setMetaFictional(pMetaFictional:Boolean): Unit ={
    metafictional = Option(pMetaFictional)
  }

  def getMetaFictional:Boolean ={
    metafictional.getOrElse(false)
  }

  def isMetaFictionalDefined:Boolean = metafictional.isDefined

  def canEqual(a: Any):Boolean = a.isInstanceOf[Element]

  override def equals(that: Any): Boolean =
    that match {
      case that: Element => {
        that.canEqual(this) &&
          this.id == that.id &&
          this.label == that.label &&
          this.description == that.description &&
          this.comments == that.comments &&
          this.induces == that.induces &&
          this.aspects == that.aspects &&
          this.icon == that.icon &&
          this.lifetime == that.lifetime &&
          this.burnTo == that.burnTo &&
          this.resaturate == that.resaturate &&
          this.animFrames == that.animFrames &&
          this.uniquenessgroup == that.uniquenessgroup &&
          this.xtriggers == that.xtriggers &&
          this.manifestationtype == that.manifestationtype &&
          this.inherits == that.inherits &&
          this.isAspect == that.isAspect &&
          this.noartneeded == that.noartneeded &&
          this.verbicon == that.verbicon &&
          this.lever == that.lever &&
          this.metafictional == that.metafictional &&
          this.slots == that.slots &&
          this.unique == that.unique &&
          this.decayTo == that.decayTo &&
          this.isHidden == that.isHidden
      }
      case _ => false
    }

  //  induces
  //  aspects
  //  icon
  //  lifetime
  //  description
  //  burnTo
  //  resaturate
  //  animFrames
  //  uniquenessgroup
  //  xtriggers
  //  manifestationtype
  //  id
  //  inherits
  //  isAspect
  //  noartneeded
  //  comments
  //  verbicon
  //  label
  //  lever
  //  isHidden
  //  metafictional
  //  slots
  //  unique
  //  decayTo
}

object Element {
  val collectionType: String = "elements"

  def apply(pId:String): Element = new Element(pId)

  implicit val elemmentReader: Reads[Element] = new Reads[Element] {
    def reads(jv: JsValue): JsResult[Element] = {
      val e = Element((jv \ "id").as[String])
      e.label = (jv \ "label").asOpt[String]
      e.description = (jv \ "description").asOpt[String]
      e.comments = (jv \ "comments").asOpt[String]
      e.aspects = (jv \ "aspects").asOpt[NumberField]
      e.induces = (jv \ "induces").asOpt[List[ChanceField]]
      e.slots = (jv \ "slots").asOpt[List[Slot]]
      e.xtriggers = (jv \ "xtriggers").asOpt[XTriggers]
      e.lifetime = (jv \ "lifetime").asOpt[Int]
      e.decayTo = (jv \ "decayTo").asOpt[String]
      e.isAspect = (jv \ "isAspect").asOpt[Boolean]
      e.unique = (jv \ "unique").asOpt[Boolean]
      e.uniquenessgroup = (jv \ "uniquenessgroup").asOpt[String]
      e.isHidden = (jv \ "isHidden").asOpt[Boolean]
      e.icon = (jv \ "icon").asOpt[String]
      e.noartneeded = (jv \ "noartneeded").asOpt[Boolean]
      e.resaturate = (jv \ "resaturate").asOpt[Boolean]
      e.animFrames = (jv \ "animFrames").asOpt[Int]
      e.burnTo = (jv \ "burnTo").asOpt[String]
      e.manifestationtype = (jv \ "manifestationtype").asOpt[String]
      e.inherits = (jv \ "inherits").asOpt[String]
      e.verbicon = (jv \ "verbicon").asOpt[String]
      e.lever = (jv \ "lever").asOpt[String]
      e.metafictional = (jv \ "metafictional").asOpt[Boolean]
      JsSuccess(e)
    }
  }

  implicit val elemmentWrither: Writes[Element] = new Writes[Element] {
    def writes(o: Element): JsValue = {
      var obj = Json.obj()
      obj = obj + ("id" -> Json.toJson(o.id))
      if (o.label.isDefined)
        obj = obj + ("label" -> Json.toJson(o.label.get))
      if (o.description.isDefined)
        obj = obj + ("description" -> Json.toJson(o.description.get))
      if (o.comments.isDefined)
        obj = obj + ("comments" -> Json.toJson(o.comments.get))
      if (o.aspects.isDefined)
        obj = obj + ("aspects" -> Json.toJson(o.aspects.get))
      if (o.induces.isDefined)
        obj = obj + ("induces" -> Json.toJson(o.induces.get))
      if (o.slots.isDefined)
        obj = obj + ("slots" -> Json.toJson(o.slots.get))
      if (o.xtriggers.isDefined)
        obj = obj + ("xtriggers" -> Json.toJson(o.xtriggers.get))
      if (o.lifetime.isDefined)
        obj = obj + ("lifetime" -> Json.toJson(o.lifetime.get))
      if (o.decayTo.isDefined)
        obj = obj + ("decayTo" -> Json.toJson(o.decayTo.get))
      if (o.isAspect.isDefined)
        obj = obj + ("isAspect" -> Json.toJson(o.isAspect.get))
      if (o.unique.isDefined)
        obj = obj + ("unique" -> Json.toJson(o.unique.get))
      if (o.uniquenessgroup.isDefined)
        obj = obj + ("uniquenessgroup" -> Json.toJson(o.uniquenessgroup.get))
      if (o.isHidden.isDefined)
        obj = obj + ("isHidden" -> Json.toJson(o.isHidden.get))
      if (o.icon.isDefined)
        obj = obj + ("icon" -> Json.toJson(o.icon.get))
      if (o.noartneeded.isDefined)
        obj = obj + ("noartneeded" -> Json.toJson(o.noartneeded.get))
      if (o.resaturate.isDefined)
        obj = obj + ("resaturate" -> Json.toJson(o.resaturate.get))
      if (o.animFrames.isDefined)
        obj = obj + ("animFrames" -> Json.toJson(o.animFrames.get))
      if (o.burnTo.isDefined)
        obj = obj + ("burnTo" -> Json.toJson(o.burnTo.get))
      if (o.manifestationtype.isDefined)
        obj = obj + ("manifestationtype" -> Json.toJson(o.manifestationtype.get))
      if (o.inherits.isDefined)
        obj = obj + ("inherits" -> Json.toJson(o.inherits.get))
      if (o.verbicon.isDefined)
        obj = obj + ("verbicon" -> Json.toJson(o.verbicon.get))
      if (o.lever.isDefined)
        obj = obj + ("lever" -> Json.toJson(o.lever.get))
      if (o.metafictional.isDefined)
        obj = obj + ("metafictional" -> Json.toJson(o.metafictional.get))
      obj
    }
  }


}
