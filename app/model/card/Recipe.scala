package model.card

import model.enums.ActionIdFields.ActionIdFields
import model.enums.DeckEffectsFields.DeckEffectsFields
import model.enums.{ActionIdFields, DeckEffectsFields}
import model.field._
import play.api.libs.json._

class Recipe(pId: String) extends Entity(pId) {
  /**
   * Text label that is displayed in-game.
   */
  private var startdescription: Option[String] = None

  def setStartDescription(pStartDescription: String): Unit = {
    startdescription = Option(pStartDescription)
  }

  def getStartDescription: String = {
    startdescription.getOrElse("")
  }

  def isStartDescriptionDefined: Boolean = startdescription.isDefined

  /**
   * The Id of the verb tile used for this recipe.
   */
  private var actionId: Option[String] = None
  /**
   * Repeated field json model error
   */
  private var actionid: Option[String] = None

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

  def getActionIdFieldName: String = if (actionid.isDefined) "actionid" else "actionId"

  /**
   * Positive numbers mean "at least" and negative numbers mean "less than".
   * Note that unlike slots recipes require all requirements to be fulfilled in order to successfully start.
   */
  private var requirements: Option[NumberAndStringField] = None

  def setRequirements(pRequirements: NumberAndStringField): Unit = {
    requirements = Option(pRequirements)
  }

  def getRequirements: NumberAndStringField = {
    requirements.getOrElse(NumberAndStringField())
  }

  def isRequirementsDefined: Boolean = requirements.isDefined

  /**
   * Checks for the existence of elements (aspects/ cards) in the game (even in verb tiles).
   * Positive numbers mean "at least" and negative numbers mean "less than". Stackable elements and elements
   * currently held by the player might cause buggy behavior.
   */
  private var extantreqs: Option[NumberField] = None

  def setExtantreqs(pExtantreqs: NumberField): Unit = {
    extantreqs = Option(pExtantreqs)
  }

  def getExtantreqs: NumberField = {
    extantreqs.getOrElse(NumberField())
  }

  def isExtantreqsDefined: Boolean = extantreqs.isDefined

  /**
   * Checks for the existence of elements (aspects/ cards) on the table. Positive numbers mean "at least" and negative
   * numbers mean "less than". Stackable elements and elements currently held by the player might cause buggy behavior.
   */
  private var tablereqs: Option[NumberField] = None

  def setTableReqs(pTableReqs: NumberField): Unit = {
    tablereqs = Option(pTableReqs)
  }

  def getTableReqs: NumberField = {
    tablereqs.getOrElse(NumberField())
  }

  def isTableReqsDefined: Boolean = tablereqs.isDefined

  /**
   * Cards that are created (positive number) and destroyed (negative number).
   */
  private var effects: Option[NumberAndStringField] = None

  def setEffects(pEffects: NumberAndStringField): Unit = {
    effects = Option(pEffects)
  }

  def getEffects: NumberAndStringField = {
    effects.getOrElse(NumberAndStringField())
  }

  def isEffectsDefined: Boolean = effects.isDefined

  /**
   * Aspects of the recipe. These are not displayed in-game, but they are used for "induces" and "xtriggers" effects.
   * See element properties above.
   */
  private var aspects: Option[NumberField] = None

  def setAspects(pAspects: NumberField): Unit = {
    aspects = Option(pAspects)
  }

  def getAspects: NumberField = {
    aspects.getOrElse(NumberField())
  }

  def isAspectsDefined: Boolean = aspects.isDefined

  /**
   * May use "filter", "mutate", and "level" as an alias for "filterOnAspectId", "mutateAspectId", and "mutationLevel"
   * respectively. Filters for cards with the specified ID or that have the specified ID as an aspect and then
   * increases (positive number) or reduces (negative number) an aspect on them. When "additive" is false, it will be
   * set to that number instead. The mutations stay on the card even after it is transformed into a different one via
   * "xtriggers" or "decayTo". Mutated cards will become unstackable. Mutations are applied before cards
   * are transformed with "xtriggers".
   */
  private var mutations: Option[List[Mutation]] = None

  def setMutations(pMutations: List[Mutation]): Unit = {
    mutations = Option(pMutations)
  }

  def getMutations: List[Mutation] = {
    mutations.getOrElse(List[Mutation]())
  }

  def isMutationsDefined: Boolean = mutations.isDefined

  /**
   * Recipes that are started instead of this one. Test it???
   */
  private var alternativerecipes: Option[List[Alt]] = None

  def setAlternativeRecipes(pAlternativeRecipes: List[Alt]): Unit = {
    alternativerecipes = Option(pAlternativeRecipes)
  }

  def getAlternativeRecipes: List[Alt] = {
    alternativerecipes.getOrElse(List[Alt]())
  }

  def isAlternativeRecipesDefined: Boolean = alternativerecipes.isDefined

  /**
   * Recipes that are started instead of this one. Test it???
   */
  private var alt: Option[List[Alt]] = None

  def setAlt(pAlt: List[Alt]): Unit = {
    alt = Option(pAlt)
  }

  def getAlt: List[Alt] = {
    alt.getOrElse(List[Alt]())
  }

  def isAltDefined: Boolean = alt.isDefined

  /**
   * Recipes that will be started after this one is finished. Linked recipes may use "challenges"
   * in the same way as alternativerecipes.
   */
  private var linked: Option[List[Linked]] = None

  def setLinked(pLinked: List[Linked]): Unit = {
    linked = Option(pLinked)
  }

  def getLinked: List[Linked] = {
    linked.getOrElse(List[Linked]())
  }

  def isLinkedDefined: Boolean = linked.isDefined

  /**
   * Time in seconds it takes for this recipe to finish.
   * A warmup of 0 might cause weird behavior when the recipe is "additional".
   */
  private var slots: Option[List[Slot]] = None

  def setSlots(pSlots: List[Slot]): Unit = {
    slots = Option(pSlots)
  }

  def getSlots: List[Slot] = {
    slots.getOrElse(List[Slot]())
  }

  def isSlotsDefined: Boolean = slots.isDefined


  /**
   * Time in seconds it takes for this recipe to finish.
   * A warmup of 0 might cause weird behavior when the recipe is "additional".
   */
  private var warmup: Option[Int] = None

  def setWarmUp(pWarmUp: Int): Unit = {
    warmup = Option(pWarmUp)
  }

  def getWarmUp: Int = {
    warmup.getOrElse(0)
  }

  def isWarmUpDefined: Boolean = warmup.isDefined

  /**
   * Max amount of times this recipe can be done. 0 is unlimited.
   */
  private var maxexecutions: Option[Int] = None

  def setMaxExecutions(pMaxExecutions: Int): Unit = {
    maxexecutions = Option(pMaxExecutions)
  }

  def getMaxExecutions: Int = {
    maxexecutions.getOrElse(0)
  }

  def isMaxExecutionsDefined: Boolean = maxexecutions.isDefined


  /**
   * Pulls a certain amount of random cards from a deck. Multiple decks?
   */
  private var deckeffect: Option[NumberField] = None

  private var deckeffects: Option[NumberField] = None

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

  def getDeckEffectsFieldName: String = if (deckeffect.isDefined) "deckeffect" else "deckeffects"


  /**
   * Lets you define a deck right inside the recipe.
   * The "draws" keyword will determine how many cards are drawn from it.
   */
  private var internaldeck: Option[Desk] = None

  def setInternalDeck(pInternalDeck: Desk): Unit = {
    internaldeck = Option(pInternalDeck)
  }

  def getInternalDeck: Desk = {
    internaldeck.getOrElse(Desk(""))
  }

  def isInternalDeckDefined: Boolean = internaldeck.isDefined

  /**
   * An image that is displayed when the recipe is started.
   */
  private var burnimage: Option[String] = None

  def setBurnImage(pBurnImage: String): Unit = {
    burnimage = Option(pBurnImage)
  }

  def getBurnImage: String = {
    burnimage.getOrElse("")
  }

  def isBurnImageDefined: Boolean = burnimage.isDefined

  /**
   * Causes this recipe to end the game with a specific end screen
   */
  private var ending: Option[String] = None

  def setEnding(pEnding: String): Unit = {
    ending = Option(pEnding)
  }

  def getEnding: String = {
    ending.getOrElse("")
  }

  def isEndingDefined: Boolean = ending.isDefined

  /**
   * Changes the colour of the colour of the recipe's timer. Grand means yellow and Melancholy red.
   */
  private var signalEndingFlavour: Option[String] = None

  def setSignalEndingFlavour(pSignalEndingFlavour: String): Unit = {
    signalEndingFlavour = Option(pSignalEndingFlavour)
  }

  def getSignalEndingFlavour: String = {
    signalEndingFlavour.getOrElse("")
  }

  def isSignalEndingFlavourDefined: Boolean = signalEndingFlavour.isDefined

  /**
   * For entering the mansus.
   */
  private var portaleffect: Option[String] = None

  def setPortalEffect(pPortalEffect: String): Unit = {
    portaleffect = Option(pPortalEffect)
  }

  def getPortalEffect: String = {
    portaleffect.getOrElse("")
  }

  def isPortalEffectDefined: Boolean = portaleffect.isDefined

  /**
   * Decides if the recipe can be triggered manually by the player.
   */
  private var craftable: Option[Boolean] = None

  def setCraftable(pCraftable: Boolean): Unit = {
    craftable = Option(pCraftable)
  }

  def getCraftable: Boolean = {
    craftable.getOrElse(false)
  }

  def isCraftableDefined: Boolean = craftable.isDefined

  /**
   * Makes it impossible to execute the recipe. For when you just want to display a description.
   */
  private var hintonly: Option[Boolean] = None

  def setHintOnly(pHintOnly: Boolean): Unit = {
    hintonly = Option(pHintOnly)
  }

  def getHintOnly: Boolean = {
    hintonly.getOrElse(false)
  }

  def isHintOnlyDefined: Boolean = hintonly.isDefined

  /**
   * Plays a sound effect when the recipe starts?
   */
  private var signalimportantloop: Option[Boolean] = None

  def setSignalImportantLoop(pSignalImportantLoop: Boolean): Unit = {
    signalimportantloop = Option(pSignalImportantLoop)
  }

  def getSignalImportantLoop: Boolean = {
    signalimportantloop.getOrElse(false)
  }

  def isSignalImportantLoopDefined: Boolean = signalimportantloop.isDefined

  private var purge: Option[NumberField] = None

  def setPurge(pPurge:NumberField): Unit ={
    purge = Option(pPurge)
  }

  def getPurge:NumberField ={
    purge.getOrElse(NumberField())
  }

  def isPurgeDefined:Boolean = purge.isDefined

  private var deleteverb: Option[NumberField] = None

  def setDeleteVerb(pDeleteVerb:NumberField): Unit ={
    deleteverb = Option(pDeleteVerb)
  }

  def getDeleteVerb:NumberField ={
    deleteverb.getOrElse(NumberField())
  }

  def isDeleteVerbDefined:Boolean = deleteverb.isDefined

  private var haltverb: Option[NumberField] = None

  def setHaltVerb(pHaltVerb:NumberField): Unit ={
    haltverb = Option(pHaltVerb)
  }

  def getHaltVerb:NumberField ={
    haltverb.getOrElse(NumberField())
  }

  def isHaltVerbDefined:Boolean = haltverb.isDefined

  def canEqual(a: Any):Boolean = a.isInstanceOf[Recipe]

  override def equals(that: Any): Boolean =
    that match {
      case that: Recipe => {
        that.canEqual(this) &&
          this.id == that.id &&
          this.label == that.label &&
          this.description == that.description &&
          this.comments == that.comments &&
          this.signalEndingFlavour == that.signalEndingFlavour &&
          this.hintonly == that.hintonly &&
          this.aspects == that.aspects &&
          this.purge == that.purge &&
          this.maxexecutions == that.maxexecutions &&
          this.portaleffect == that.portaleffect &&
          this.startdescription == that.startdescription &&
          this.mutations == that.mutations &&
          this.actionid == that.actionid &&
          this.linked == that.linked &&
          this.deckeffect == that.deckeffect &&
          this.requirements == that.requirements &&
          this.tablereqs == that.tablereqs &&
          this.extantreqs == that.extantreqs &&
          this.internaldeck == that.internaldeck &&
          this.alt == that.alt &&
          this.deckeffects == that.deckeffects &&
          this.craftable == that.craftable &&
          this.ending == that.ending &&
          this.effects == that.effects &&
          this.slots == that.slots &&
          this.deleteverb == that.deleteverb &&
          this.signalimportantloop == that.signalimportantloop &&
          this.alternativerecipes == that.alternativerecipes &&
          this.actionId == that.actionId &&
          this.haltverb == that.haltverb &&
          this.warmup == that.warmup &&
          this.burnimage == that.burnimage
      }
      case _ => false
    }


  //  signalEndingFlavour
  //  hintonly
  //  aspects
  //  description
  //  purge
  //  maxexecutions
  //  portaleffect
  //  startdescription
  //  mutations
  //  actionid
  //  id
  //  linked
  //  deckeffect
  //  requirements
  //  comments
  //  tablereqs
  //  extantreqs
  //  internaldeck
  //  alt
  //  label
  //  deckeffects
  //  craftable
  //  ending
  //  effects
  //  slots
  //  deleteverb
  //  signalimportantloop
  //  alternativerecipes
  //  actionId
  //  haltverb
  //  warmup
  //  burnimage

}

object Recipe {
  val collectionType: String = "recipes"

  def apply(pId:String): Recipe = new Recipe(pId)

  implicit val recipeReader: Reads[Recipe] = new Reads[Recipe] {
    def reads(jv: JsValue): JsResult[Recipe] = {
      val r = Recipe("")
      val id = (jv \ "id").as[String]
      r.id = id
      val label = (jv \ "label").asOpt[String]
      if (label.isDefined)
        r.label = label
      val description = (jv \ "description").asOpt[String]
      if (description.isDefined)
        r.description = description
      val comments = (jv \ "comments").asOpt[String]
      if (comments.isDefined)
        r.comments = comments
      val startdescription = (jv \ "startdescription").asOpt[String]
      if (startdescription.isDefined)
        r.startdescription = startdescription
      val actionId = (jv \ "actionId").asOpt[String]
      if (actionId.isDefined)
        r.actionId = actionId
      val actionid = (jv \ "actionid").asOpt[String]
      if (actionid.isDefined)
        r.actionid = actionid
      val requirements = (jv \ "requirements").asOpt[NumberAndStringField]
      if (requirements.isDefined)
        r.requirements = requirements
      val extantreqs = (jv \ "extantreqs").asOpt[NumberField]
      if (extantreqs.isDefined)
        r.extantreqs = extantreqs
      val tablereqs = (jv \ "tablereqs").asOpt[NumberField]
      if (tablereqs.isDefined)
        r.tablereqs = tablereqs
      val effects = (jv \ "effects").asOpt[NumberAndStringField]
      if (effects.isDefined)
        r.effects = effects
      val aspects = (jv \ "aspects").asOpt[NumberField]
      if (aspects.isDefined)
        r.aspects = aspects
      val mutations = (jv \ "mutations").asOpt[List[Mutation]]
      if (mutations.isDefined)
        r.mutations = mutations
      val alternativerecipes = (jv \ "alternativerecipes").asOpt[List[Alt]]
      if (alternativerecipes.isDefined)
        r.alternativerecipes = alternativerecipes
      val alt = (jv \ "alt").asOpt[List[Alt]]
      if (alt.isDefined)
        r.alt = alt
      val linked = (jv \ "linked").asOpt[List[Linked]]
      if (linked.isDefined)
        r.linked = linked
      val slots = (jv \ "slots").asOpt[List[Slot]]
      if (slots.isDefined)
        r.slots = slots
      val warmup = (jv \ "warmup").asOpt[Int]
      if (warmup.isDefined)
        r.warmup = warmup
      val maxexecutions = (jv \ "maxexecutions").asOpt[Int]
      if (maxexecutions.isDefined)
        r.maxexecutions = maxexecutions
      val deckeffect = (jv \ "deckeffect").asOpt[NumberField]
      if (deckeffect.isDefined)
        r.deckeffect = deckeffect
      val internaldeck = (jv \ "internaldeck").asOpt[Desk](Desk.internalDeskReader)
      if (internaldeck.isDefined)
        r.internaldeck = internaldeck
      val burnimage = (jv \ "burnimage").asOpt[String]
      if (burnimage.isDefined)
        r.burnimage = burnimage
      val ending = (jv \ "ending").asOpt[String]
      if (ending.isDefined)
        r.ending = ending
      val signalEndingFlavour = (jv \ "signalEndingFlavour").asOpt[String]
      if (signalEndingFlavour.isDefined)
        r.signalEndingFlavour = signalEndingFlavour
      val portaleffect = (jv \ "portaleffect").asOpt[String]
      if (portaleffect.isDefined)
        r.portaleffect = portaleffect
      val craftable = (jv \ "craftable").asOpt[Boolean]
      if (craftable.isDefined)
        r.craftable = craftable
      val hintonly = (jv \ "hintonly").asOpt[Boolean]
      if (hintonly.isDefined)
        r.hintonly = hintonly
      val signalimportantloop = (jv \ "signalimportantloop").asOpt[Boolean]
      if (signalimportantloop.isDefined)
        r.signalimportantloop = signalimportantloop
      val purge = (jv \ "purge").asOpt[NumberField]
      if (purge.isDefined)
        r.purge = purge
      val deckeffects = (jv \ "deckeffects").asOpt[NumberField]
      if (deckeffects.isDefined)
        r.deckeffects = deckeffects
      val deleteverb = (jv \ "deleteverb").asOpt[NumberField]
      if (deleteverb.isDefined)
        r.deleteverb = deleteverb
      val haltverb = (jv \ "haltverb").asOpt[NumberField]
      if (haltverb.isDefined)
        r.haltverb = haltverb
      JsSuccess(r)
    }
  }

  implicit val recipeWrither: Writes[Recipe] = new Writes[Recipe] {
    def writes(o: Recipe): JsValue = {
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
      if (o.startdescription.isDefined)
        obj = obj + ("startdescription" -> Json.toJson(o.startdescription.get))
      if (o.actionId.isDefined)
        obj = obj + ("actionId" -> Json.toJson(o.actionId.get))
      if (o.actionid.isDefined)
        obj = obj + ("actionid" -> Json.toJson(o.actionid.get))
      if (o.requirements.isDefined)
        obj = obj + ("requirements" -> Json.toJson(o.requirements.get))
      if (o.extantreqs.isDefined)
        obj = obj + ("extantreqs" -> Json.toJson(o.extantreqs.get))
      if (o.tablereqs.isDefined)
        obj = obj + ("tablereqs" -> Json.toJson(o.tablereqs.get))
      if (o.effects.isDefined)
        obj = obj + ("effects" -> Json.toJson(o.effects.get))
      if (o.aspects.isDefined)
        obj = obj + ("aspects" -> Json.toJson(o.aspects.get))
      if (o.mutations.isDefined)
        obj = obj + ("mutations" -> Json.toJson(o.mutations.get))
      if (o.alternativerecipes.isDefined)
        obj = obj + ("alternativerecipes" -> Json.toJson(o.alternativerecipes.get))
      if (o.alternativerecipes.isDefined)
        obj = obj + ("alternativerecipes" -> Json.toJson(o.alternativerecipes.get))
      if (o.alt.isDefined)
        obj = obj + ("alt" -> Json.toJson(o.alt.get))
      if (o.linked.isDefined)
        obj = obj + ("linked" -> Json.toJson(o.linked.get))
      if (o.slots.isDefined)
        obj = obj + ("slots" -> Json.toJson(o.slots.get))
      if (o.warmup.isDefined)
        obj = obj + ("warmup" -> Json.toJson(o.warmup.get))
      if (o.maxexecutions.isDefined)
        obj = obj + ("maxexecutions" -> Json.toJson(o.maxexecutions.get))
      if (o.deckeffect.isDefined)
        obj = obj + ("deckeffect" -> Json.toJson(o.deckeffect.get))
      if (o.internaldeck.isDefined)
        obj = obj + ("internaldeck" -> Json.toJson(o.internaldeck.get)(Desk.internalDeskWriter))
      if (o.burnimage.isDefined)
        obj = obj + ("burnimage" -> Json.toJson(o.burnimage.get))
      if (o.ending.isDefined)
        obj = obj + ("ending" -> Json.toJson(o.ending.get))
      if (o.signalEndingFlavour.isDefined)
        obj = obj + ("signalEndingFlavour" -> Json.toJson(o.signalEndingFlavour.get))
      if (o.portaleffect.isDefined)
        obj = obj + ("portaleffect" -> Json.toJson(o.portaleffect.get))
      if (o.craftable.isDefined)
        obj = obj + ("craftable" -> Json.toJson(o.craftable.get))
      if (o.hintonly.isDefined)
        obj = obj + ("hintonly" -> Json.toJson(o.hintonly.get))
      if (o.signalimportantloop.isDefined)
        obj = obj + ("signalimportantloop" -> Json.toJson(o.signalimportantloop.get))
      if (o.purge.isDefined)
        obj = obj + ("purge" -> Json.toJson(o.purge.get))
      if (o.deckeffects.isDefined)
        obj = obj + ("deckeffects" -> Json.toJson(o.deckeffects.get))
      if (o.deleteverb.isDefined)
        obj = obj + ("deleteverb" -> Json.toJson(o.deleteverb.get))
      if (o.haltverb.isDefined)
        obj = obj + ("haltverb" -> Json.toJson(o.haltverb.get))
      obj
    }
  }
}
