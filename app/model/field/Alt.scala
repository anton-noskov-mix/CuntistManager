package model.field

import model.enums.ActionIdFields.ActionIdFields
import model.enums.{ActionIdFields, DeckEffectsFields}
import model.enums.DeckEffectsFields.DeckEffectsFields
import play.api.libs.json._

class Alt(private var id: String) {

  def setId(pId: String): Unit = {
    id = pId
  }

  def getId: String = {
    id
  }

  private var chance: Option[Int] = None

  def setChance(pChance: Int): Unit = {
    chance = Option(pChance)
  }

  def getChance: Int = {
    chance.getOrElse(0)
  }

  def isChanceDefined: Boolean = chance.isDefined

  private var additional: Option[Boolean] = None

  def setAdditional(pAdditional: Boolean): Unit = {
    additional = Option(pAdditional)
  }

  def getAdditional: Boolean = {
    additional.getOrElse(false)
  }

  def isAdditionalDefined: Boolean = additional.isDefined

  private var expulsion: Option[Expulsion] = None

  def setExpulsion(pExpulsion: Expulsion): Unit = {
    expulsion = Option(pExpulsion)
  }

  def getExpulsion: Expulsion = {
    expulsion.getOrElse(Expulsion())
  }

  def isExpulsionDefined: Boolean = expulsion.isDefined

  private var effects: Option[NumberField] = None

  def setEffects(pEffects: NumberField): Unit = {
    effects = Option(pEffects)
  }

  def getEffects: NumberField = {
    effects.getOrElse(NumberField())
  }

  def isEffectsDefined: Boolean = effects.isDefined

  private var requirements: Option[NumberField] = None

  def setRequirements(pRequirements: NumberField): Unit = {
    requirements = Option(pRequirements)
  }

  def getRequirements: NumberField = {
    requirements.getOrElse(NumberField())
  }

  def isRequirementsDefined: Boolean = requirements.isDefined

  private var startdescription: Option[String] = None

  def setStartDescription(pStartDescription: String): Unit = {
    startdescription = Option(pStartDescription)
  }

  def getStartDescription: String = {
    startdescription.getOrElse("")
  }

  def isStartDescriptionDefined: Boolean = startdescription.isDefined

  private var mutations: Option[List[Mutation]] = None

  def setMutations(pMutations: List[Mutation]): Unit = {
    mutations = Option(pMutations)
  }

  def getMutations: List[Mutation] = {
    mutations.getOrElse(List[Mutation]())
  }

  def isMutationsDefined: Boolean = mutations.isDefined

  private var label: Option[String] = None

  def setLabel(pLabel: String): Unit = {
    label = Option(pLabel)
  }

  def getLabel: String = {
    label.getOrElse("")
  }

  def isLabelDefined: Boolean = label.isDefined

  private var challenges: Option[StringField] = None

  def setChallenges(pChallenges: StringField): Unit = {
    challenges = Option(pChallenges)
  }

  def getChallenges: StringField = {
    challenges.getOrElse(StringField())
  }

  def isChallengesDefined: Boolean = challenges.isDefined

  //todo Don't use this field
  private var challengesOne: Option[String] = None

  def setChallengesOne(pChallengesOne: String): Unit = {
    challengesOne = Option(pChallengesOne)
  }

  def getChallengesOne: String = {
    challengesOne.getOrElse("")
  }

  def isChallengesOneDefined: Boolean = challengesOne.isDefined

  private var description: Option[String] = None

  def setDescription(pDescription: String): Unit = {
    description = Option(pDescription)
  }

  def getDescription: String = {
    description.getOrElse("")
  }

  def isDescriptionDefined: Boolean = description.isDefined

  private var deckeffects: Option[NumberField] = None

  private var deckeffect: Option[NumberField] = None

  def setDeckEffects(pDeckEffects: NumberField, field: DeckEffectsFields = DeckEffectsFields.deckeffect): Unit = {
    if (deckeffect.isDefined)
      deckeffect = Option(pDeckEffects)
    else if (deckeffects.isDefined)
      deckeffects = Option(pDeckEffects)
    else
      field match {
        case DeckEffectsFields.deckeffect =>
          deckeffect = Option(pDeckEffects)
        case DeckEffectsFields.deckeffects =>
          deckeffects = Option(pDeckEffects)
      }
  }

  def getDeckEffects: NumberField = deckeffects.getOrElse(deckeffect.getOrElse(NumberField()))

  def isDeckEffectsDefined: Boolean = deckeffects.isDefined || deckeffect.isDefined

  def getDeckEffectsFieldName:String = if(deckeffect.isDefined) "deckeffect" else "deckeffects"


  private var actionid: Option[String] = None

  private var actionId: Option[String] = None

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

  private var ending: Option[String] = None

  def setEnding(pEnding: String): Unit = {
    ending = Option(pEnding)
  }

  def getEnding: String = {
    ending.getOrElse("")
  }

  def isEndingDefined: Boolean = ending.isDefined

  private var signalEndingFlavour: Option[String] = None

  def setSignalEndingFlavour(pSignalEndingFlavour: String): Unit = {
    signalEndingFlavour = Option(pSignalEndingFlavour)
  }

  def getSignalEndingFlavour: String = {
    signalEndingFlavour.getOrElse("")
  }

  def isSignalEndingFlavourDefined: Boolean = signalEndingFlavour.isDefined

  private var extantreqs: Option[NumberField] = None

  def setExtantReqs(pExtantReqs: NumberField): Unit = {
    extantreqs = Option(pExtantReqs)
  }

  def getExtantReqs: NumberField = {
    extantreqs.getOrElse(NumberField())
  }

  def isExtantReqsDefined: Boolean = extantreqs.isDefined

  private var craftable: Option[Boolean] = None

  def setCraftable(pCraftable: Boolean): Unit = {
    craftable = Option(pCraftable)
  }

  def getCraftable: Boolean = {
    craftable.getOrElse(false)
  }

  def isCraftableDefined: Boolean = craftable.isDefined

  def canEqual(a: Any):Boolean = a.isInstanceOf[Alt]

  override def equals(that: Any): Boolean =
    that match {
      case that: Alt => {
        that.canEqual(this) &&
          this.id == that.id &&
          this.chance == that.chance &&
          this.additional == that.additional &&
          this.expulsion == that.expulsion &&
          this.effects == that.effects &&
          this.requirements == that.requirements &&
          this.startdescription == that.startdescription &&
          this.mutations == that.mutations &&
          this.label == that.label &&
          this.challenges == that.challenges &&
          this.challengesOne == that.challengesOne &&
          this.deckeffects == that.deckeffects &&
          this.deckeffect == that.deckeffect &&
          this.actionid == that.actionid &&
          this.actionId == that.actionId &&
          this.ending == that.ending &&
          this.signalEndingFlavour == that.signalEndingFlavour &&
          this.extantreqs == that.extantreqs &&
          this.craftable == that.craftable
      }
      case _ => false
    }


  //id
  //chance
  //additional
  //expulsion
  //effects
  //requirements
  //startdescription
  //mutations
  //label
  //challenges
  //challengesOne
  //description
  //deckeffects
  //deckeffect
  //actionid
  //actionId
  //ending
  //signalEndingFlavour
  //extantreqs
  //craftable

}


object Alt {
  def apply(pId: String): Alt = new Alt(pId)

  implicit val altReader: Reads[Alt] = new Reads[Alt] {
    def reads(jv: JsValue): JsResult[Alt] = {
      val a = Alt((jv \ "id").as[String])
      val chance = (jv \ "chance").asOpt[Int]
      if (chance.isDefined)
        a.chance = chance
      val additional = (jv \ "additional").asOpt[Boolean]
      if (additional.isDefined)
        a.additional = additional
      val expulsion = (jv \ "expulsion").asOpt[Expulsion]
      if (expulsion.isDefined)
        a.expulsion = expulsion
      val effects = (jv \ "effects").asOpt[NumberField]
      if (effects.isDefined)
        a.effects = effects
      val requirements = (jv \ "requirements").asOpt[NumberField]
      if (requirements.isDefined)
        a.requirements = requirements
      val startdescription = (jv \ "startdescription").asOpt[String]
      if (startdescription.isDefined)
        a.startdescription = startdescription
      val mutations = (jv \ "mutations").asOpt[List[Mutation]]
      if (mutations.isDefined)
        a.mutations = mutations
      val label = (jv \ "label").asOpt[String]
      if (label.isDefined)
        a.label = label
      (jv \ "challenges").validate[StringField] match {
        case JsSuccess(v, p) =>
          a.challenges = Option(v)
        case e: JsError => {
          val challengesOne = (jv \ "challenges").asOpt[String]
          if (challengesOne.isDefined)
            a.challengesOne = challengesOne
        }
      }
      val description = (jv \ "description").asOpt[String]
      if (description.isDefined)
        a.description = description
      val deckeffects = (jv \ "deckeffects").asOpt[NumberField]
      if (deckeffects.isDefined)
        a.deckeffects = deckeffects
      val deckeffect = (jv \ "deckeffect").asOpt[NumberField]
      if (deckeffect.isDefined)
        a.deckeffect = deckeffect

      val actionid = (jv \ "actionid").asOpt[String]
      if (actionid.isDefined)
        a.actionid = actionid
      val actionId = (jv \ "actionId").asOpt[String]
      if (actionId.isDefined)
        a.actionId = actionId
      val ending = (jv \ "ending").asOpt[String]
      if (ending.isDefined)
        a.ending = ending
      val signalEndingFlavour = (jv \ "signalEndingFlavour").asOpt[String]
      if (signalEndingFlavour.isDefined)
        a.signalEndingFlavour = signalEndingFlavour
      val extantreqs = (jv \ "extantreqs").asOpt[NumberField]
      if (extantreqs.isDefined)
        a.extantreqs = extantreqs
      val craftable = (jv \ "craftable").asOpt[Boolean]
      if (craftable.isDefined)
        a.craftable = craftable

      JsSuccess(a)
    }
  }

  implicit val altWrither: Writes[Alt] = new Writes[Alt] {
    def writes(o: Alt): JsValue = {
      var obj = Json.obj()
      obj = obj + ("id" -> Json.toJson(o.id))
      if (o.chance.isDefined)
        obj = obj + ("chance" -> Json.toJson(o.chance))
      if (o.additional.isDefined)
        obj = obj + ("additional" -> Json.toJson(o.additional))
      if (o.expulsion.isDefined)
        obj = obj + ("expulsion" -> Json.toJson(o.expulsion))

      if (o.effects.isDefined)
        obj = obj + ("effects" -> Json.toJson(o.effects))
      if (o.requirements.isDefined)
        obj = obj + ("requirements" -> Json.toJson(o.requirements))
      if (o.startdescription.isDefined)
        obj = obj + ("startdescription" -> Json.toJson(o.startdescription))
      if (o.mutations.isDefined)
        obj = obj + ("mutations" -> Json.toJson(o.mutations))
      if (o.label.isDefined)
        obj = obj + ("label" -> Json.toJson(o.label))

      if (o.challenges.isDefined)
        obj = obj + ("challenges" -> Json.toJson(o.challenges))
      if (o.challengesOne.isDefined)
        obj = obj + ("challenges" -> Json.toJson(o.challengesOne))

      if (o.description.isDefined)
        obj = obj + ("description" -> Json.toJson(o.description))
      if (o.deckeffects.isDefined)
        obj = obj + ("deckeffects" -> Json.toJson(o.deckeffects))
      if (o.deckeffect.isDefined)
        obj = obj + ("deckeffect" -> Json.toJson(o.deckeffect))

      if (o.actionid.isDefined)
        obj = obj + ("actionid" -> Json.toJson(o.actionid))
      if (o.actionId.isDefined)
        obj = obj + ("actionId" -> Json.toJson(o.actionId))

      if (o.ending.isDefined)
        obj = obj + ("ending" -> Json.toJson(o.ending))
      if (o.signalEndingFlavour.isDefined)
        obj = obj + ("signalEndingFlavour" -> Json.toJson(o.signalEndingFlavour))
      if (o.extantreqs.isDefined)
        obj = obj + ("extantreqs" -> Json.toJson(o.extantreqs))
      if (o.craftable.isDefined)
        obj = obj + ("craftable" -> Json.toJson(o.craftable))
      obj
    }
  }
}
