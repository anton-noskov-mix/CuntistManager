package model.field

import model.enums.ActionIdFields
import model.enums.ActionIdFields.ActionIdFields
import play.api.libs.functional.syntax.{toFunctionalBuilderOps, unlift}
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json.{JsPath, Reads, Writes, __}

/**
 * The card's id.
 */
class Linked(private var id:String) {

  def setId(pId:String): Unit ={
    id = pId
  }

  def getId:String ={
    id
  }

  /**
   * The chance that the card will be induce.
   */
  private var chance: Option[Int] = None

  def setChance(pChance:Int): Unit ={
    chance = Option(pChance)
  }

  def getChance:Int ={
    chance.getOrElse(0)
  }

  def isChanceDefined:Boolean = chance.isDefined

  private var challenges:Option[StringField] = None

  def setChallenges(pChallenges:StringField):Unit ={
    challenges = Option(pChallenges)
  }

  def getChallenges:StringField ={
    challenges.getOrElse(StringField())
  }

  def isChallengesDefined:Boolean = challenges.isDefined

  private var effects:Option[NumberField] = None

  def setEffects(pEffects:NumberField): Unit ={
    effects = Option(pEffects)
  }

  def getEffects:NumberField ={
    effects.getOrElse(NumberField())
  }

  def isEffectsDefined:Boolean = effects.isDefined

  private var startdescription:Option[String] = None

  def setStartDescription(pStartDescription:String): Unit ={
    startdescription = Option(pStartDescription)
  }

  def getStartDescription:String ={
    startdescription.getOrElse("")
  }

  def isStartDescriptionDefined:Boolean = startdescription.isDefined

  private var actionid:Option[String] = None
  private var actionId:Option[String] = None

  def setActionId(pActionId: String, field: ActionIdFields = ActionIdFields.actionId): Unit = {
    if (actionId.isDefined)
      actionId = Option(pActionId)
    else if (actionid.isDefined)
      actionid = Option(pActionId)
    else
      field match {
        case ActionIdFields.actionId =>
          actionId = Option(pActionId)
        case ActionIdFields.actionid =>
          actionid = Option(pActionId)
      }
  }

  def getActionId: String = {
    actionid.getOrElse(actionId.getOrElse(""))
  }

  def isActionIdDefined: Boolean = actionid.isDefined || actionId.isDefined

  def getActionIdFieldName:String = if(actionid.isDefined) "actionid" else "actionId"


  private var label:Option[String] = None

  def setLabel(pLabel:String):Unit ={
    label = Option(pLabel)
  }

  def getLabel:String ={
    label.getOrElse("")
  }

  def isLabelDefined:Boolean = label.isDefined

  private var warmup:Option[Int] = None

  def setWarmUp(pWarmUp: Int): Unit = {
    warmup = Option(pWarmUp)
  }

  def getWarmUp:Int ={
    warmup.getOrElse(0)
  }

  def isWarmUpDefined:Boolean = warmup.isDefined

  private var requirements:Option[NumberAndStringField] = None

  def setRequirements(pRequirements: NumberAndStringField):Unit ={
    requirements = Option(pRequirements)
  }


  def getRequirements:NumberAndStringField ={
    requirements.getOrElse(NumberAndStringField())
  }

  def isRequirementsDefined:Boolean = requirements.isDefined


  private var mutations:Option[List[Mutation]] = None

  def setMutations(pMutations: List[Mutation]): Unit = {
    mutations = Option(pMutations)
  }

  def getMutations: List[Mutation] = {
    mutations.getOrElse(List[Mutation]())
  }

  def isMutationsDefined: Boolean = mutations.isDefined


  private var purge:Option[NumberField] = None

  def setPurge(pPurge:NumberField):Unit ={
    purge = Option(pPurge)
  }

  def getPurge:NumberField ={
    purge.getOrElse(NumberField())
  }

  def isPurgeDefined:Boolean = purge.isDefined

  private var extantreqs:Option[NumberField] = None

  def setExtantreqs(pExtantreqs:NumberField): Unit = {
    extantreqs = Option(pExtantreqs)
  }

  def getExtantreqs:NumberField ={
    extantreqs.getOrElse(NumberField())
  }

  def isExtantreqsDefined:Boolean = extantreqs.isDefined

  private var linked:Option[List[Linked]] = None

  def setLinked(pLinked:List[Linked]): Unit = {
    linked = Option(pLinked)
  }

  def getLinked:List[Linked] ={
    linked.getOrElse(List[Linked]())
  }

  def isLinkedDefined:Boolean = linked.isDefined

  def canEqual(a: Any):Boolean = a.isInstanceOf[Linked]

  override def equals(that: Any): Boolean =
    that match {
      case that: Linked => {
        that.canEqual(this) &&
          this.id == that.id &&
          this.chance == that.chance &&
          this.challenges == that.challenges &&
          this.effects == that.effects &&
          this.startdescription == that.startdescription &&
          this.actionid == that.actionid &&
          this.actionId == that.actionId &&
          this.label == that.label &&
          this.warmup == that.warmup &&
          this.requirements == that.requirements &&
          this.mutations == that.mutations &&
          this.purge == that.purge &&
          this.extantreqs == that.extantreqs &&
          this.linked == that.linked
      }
      case _ => false
    }

  //id
  //chance
  //challenges
  //effects
  //startdescription
  //actionid
  //actionId
  //label
  //warmup
  //requirements
  //mutations
  //purge
  //extantreqs
  //linked

}

object Linked {

  def apply(pId:String): Linked = new Linked(pId)

  def unapply(l: Linked): Option[
    (String,
      Option[Int],
      Option[StringField],
      Option[NumberField],
      Option[String],
      Option[String],
      Option[String],
      Option[Int],
      Option[NumberAndStringField],
      Option[List[Mutation]],
      Option[NumberField],
      Option[NumberField],
      Option[String],
      Option[List[Linked]])] = {
    Option(
      l.id,
      l.chance,
      l.challenges,
      l.effects,
      l.startdescription,
      l.actionid,
      l.label,
      l.warmup,
      l.requirements,
      l.mutations,
      l.purge,
      l.extantreqs,
      l.actionId,
      l.linked)
  }

  private def applyRead(
                         pId: String,
                         pChance: Option[Int],
                         pChallenges:Option[StringField],
                         pEffects:Option[NumberField],
                         pStartDescription:Option[String],
                         pActionid:Option[String],
                         pLabel:Option[String],
                         pWarmUp:Option[Int],
                         pRequirements:Option[NumberAndStringField],
                         pMutations:Option[List[Mutation]],
                         pPurge:Option[NumberField],
                         pExtantreqs:Option[NumberField],
                         pActionId:Option[String],
                         pLinked:Option[List[Linked]]
                       ): Linked = {
    val i = new Linked(pId)
      i.chance = pChance
      i.challenges = pChallenges
      i.effects = pEffects
      i.startdescription = pStartDescription
      i.actionid = pActionid
      i.label = pLabel
      i.warmup = pWarmUp
      i.requirements = pRequirements
      i.mutations = pMutations
      i.purge = pPurge
      i.extantreqs = pExtantreqs
      i.actionId = pActionId
      i.linked = pLinked
    i
  }


  lazy implicit val linkedReader: Reads[Linked] = (
    (JsPath \ "id").read[String] and
      (JsPath \ "chance").readNullable[Int] and
      (JsPath \ "challenges").readNullable[StringField] and
      (JsPath \ "effects").readNullable[NumberField] and
      (JsPath \ "startdescription").readNullable[String] and
      (JsPath \ "actionid").readNullable[String] and
      (JsPath \ "label").readNullable[String] and
      (JsPath \ "warmup").readNullable[Int] and
      (JsPath \ "requirements").readNullable[NumberAndStringField] and
      (JsPath \ "mutations").readNullable[List[Mutation]] and
      (JsPath \ "purge").readNullable[NumberField] and
      (JsPath \ "extantreqs").readNullable[NumberField] and
      (JsPath \ "actionId").readNullable[String] and
      ( __ \ 'linked).lazyReadNullable(Reads.list(linkedReader))
    ) (Linked.applyRead _)

  lazy implicit val linkedWriter: Writes[Linked] = (
    (JsPath \ "id").write[String] and
      (JsPath \ "chance").writeNullable[Int] and
      (JsPath \ "challenges").writeNullable[StringField] and
      (JsPath \ "effects").writeNullable[NumberField] and
      (JsPath \ "startdescription").writeNullable[String] and
      (JsPath \ "actionid").writeNullable[String] and
      (JsPath \ "label").writeNullable[String] and
      (JsPath \ "warmup").writeNullable[Int] and
      (JsPath \ "requirements").writeNullable[NumberAndStringField] and
      (JsPath \ "mutations").writeNullable[List[Mutation]] and
      (JsPath \ "purge").writeNullable[NumberField] and
      (JsPath \ "extantreqs").writeNullable[NumberField] and
      (JsPath \ "actionId").writeNullable[String] and
      ( __ \ 'linked).lazyWriteNullable(Writes.list(linkedWriter))
    ) (unlift(Linked.unapply))



}



