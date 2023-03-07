package model.enums

object CollectionEnum {
  val Elements = "elements"
  val Verbs = "verbs"
  val Recipes = "recipes"
  val Portals = "portals"
  val Decks = "decks"
  val Endings = "endings"
  val Legacies = "legacies"
}

object DeckEffectsFields extends Enumeration {
  type DeckEffectsFields = Value

  val deckeffect, deckeffects = Value
}

object ActionIdFields extends Enumeration {
  type ActionIdFields = Value

  val actionId, actionid = Value
}



