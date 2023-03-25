package model.codec

import model.AppException
import model.card.{Deck, Recipe}
import model.enums.{ActionIdFields, DeckEffectsFields}
import model.field.{Alt, Linked, Mutation, NumberAndStringField, NumberField, Slot}
import org.bson.codecs.{Codec, DecoderContext, EncoderContext}
import org.bson.codecs.configuration.CodecRegistry
import org.bson.{BsonReader, BsonType, BsonWriter}

import scala.collection.mutable.ListBuffer

class RecipeCodec(val registry: CodecRegistry) extends Codec[Recipe] {

  val numberFieldCodec = registry.get(classOf[NumberField])
  val mutationCodec = registry.get(classOf[Mutation])
  val linkedCodec = registry.get(classOf[Linked])
  val numberAndStringFieldCodec = registry.get(classOf[NumberAndStringField])
  val deckCodec = registry.get(classOf[Deck])
  val altCodec = registry.get(classOf[Alt])
  val slotCodec = registry.get(classOf[Slot])

  override def decode(reader: BsonReader, decoderContext: DecoderContext): Recipe = {
    val recipe: Recipe = Recipe("")

    reader.readStartDocument()

    while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
      val fieldName: String = reader.readName

      if (fieldName == "_id")
        reader.skipValue()
      else if (fieldName == "id")
        recipe.setId(reader.readString())
      else if (fieldName == "label")
        recipe.setLabel(reader.readString())
      else if (fieldName == "description")
        recipe.setDescription(reader.readString())
      else if (fieldName == "comments")
        recipe.setComments(reader.readString())
      else if (fieldName == "signalEndingFlavour")
        recipe.setSignalEndingFlavour(reader.readString())
      else if (fieldName == "hintonly")
        recipe.setHintOnly(reader.readBoolean())
      else if (fieldName == "aspects")
        recipe.setAspects(numberFieldCodec.decode(reader,decoderContext))
      else if (fieldName == "purge")
        recipe.setPurge(numberFieldCodec.decode(reader,decoderContext))
      else if (fieldName == "maxexecutions")
        recipe.setMaxExecutions(reader.readInt32())
      else if (fieldName == "portaleffect")
        recipe.setPortalEffect(reader.readString())
      else if (fieldName == "startdescription")
        recipe.setStartDescription(reader.readString())
      else if (fieldName == "mutations") {
        val listMutation = ListBuffer[Mutation]()

        reader.readStartArray()
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
          listMutation += mutationCodec.decode(reader, decoderContext)
        }
        reader.readEndArray()

        recipe.setMutations(listMutation.toList)
      }
      else if (fieldName == "actionId")
        recipe.setActionId(reader.readString(),ActionIdFields.actionId)
      else if (fieldName == "actionid")
        recipe.setActionId(reader.readString(),ActionIdFields.actionid)
      else if (fieldName == "linked") {
        val listLinked = ListBuffer[Linked]()

        reader.readStartArray()
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
          listLinked += linkedCodec.decode(reader, decoderContext)
        }
        reader.readEndArray()

        recipe.setLinked(listLinked.toList)
      }
      else if (fieldName == "deckeffects")
        recipe.setDeckEffects(numberFieldCodec.decode(reader, decoderContext),DeckEffectsFields.deckeffects)
      else if(fieldName == "deckeffect")
        recipe.setDeckEffects(numberFieldCodec.decode(reader, decoderContext),DeckEffectsFields.deckeffect)
      else if (fieldName == "requirements")
        recipe.setRequirements(numberAndStringFieldCodec.decode(reader, decoderContext))
      else if (fieldName == "tablereqs")
        recipe.setTableReqs(numberFieldCodec.decode(reader, decoderContext))
      else if (fieldName == "extantreqs")
        recipe.setExtantreqs(numberFieldCodec.decode(reader, decoderContext))
      else if (fieldName == "internaldeck")
        recipe.setInternalDeck(deckCodec.decode(reader, decoderContext))
      else if (fieldName == "alt") {
        val listAlt = ListBuffer[Alt]()

        reader.readStartArray()
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
          listAlt += altCodec.decode(reader, decoderContext)
        }
        reader.readEndArray()

        recipe.setAlt(listAlt.toList)
      }
      else if (fieldName == "craftable")
        recipe.setCraftable(reader.readBoolean())
      else if (fieldName == "ending")
        recipe.setEnding(reader.readString())
      else if (fieldName == "effects")
        recipe.setEffects(numberAndStringFieldCodec.decode(reader, decoderContext))
      else if (fieldName == "slots") {
        val listSlot = ListBuffer[Slot]()

        reader.readStartArray()
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
          listSlot += slotCodec.decode(reader, decoderContext)
        }
        reader.readEndArray()

        recipe.setSlots(listSlot.toList)
      }
      else if (fieldName == "deleteverb")
        recipe.setDeleteVerb(numberFieldCodec.decode(reader, decoderContext))
      else if (fieldName == "signalimportantloop")
        recipe.setSignalImportantLoop(reader.readBoolean())
      else if (fieldName == "alternativerecipes"){
        val listAlternativeRecipes = ListBuffer[Alt]()

        reader.readStartArray()
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
          listAlternativeRecipes += altCodec.decode(reader, decoderContext)
        }
        reader.readEndArray()

        recipe.setAlternativeRecipes(listAlternativeRecipes.toList)
      }
      else if (fieldName == "haltverb")
        recipe.setHaltVerb(numberFieldCodec.decode(reader, decoderContext))
      else if (fieldName == "warmup")
        recipe.setWarmUp(reader.readInt32())
      else if (fieldName == "burnimage")
        recipe.setBurnImage(reader.readString())
      else throw AppException(s"Ending json decode error:Unknown field:$fieldName")
    }

    reader.readEndDocument()

    recipe
  }

  override def encode(writer: BsonWriter, value: Recipe, encoderContext: EncoderContext): Unit = {
    writer.writeStartDocument()
    writer.writeName("id")
    writer.writeString(value.getId)
    if (value.isLabelDefined) {
      writer.writeName("label")
      writer.writeString(value.getLabel)
    }
    if (value.isDescriptionDefined) {
      writer.writeName("description")
      writer.writeString(value.getDescription)
    }
    if (value.isCommentsDefined) {
      writer.writeName("comments")
      writer.writeString(value.getComments)
    }
    if (value.isSignalEndingFlavourDefined) {
      writer.writeName("signalEndingFlavour")
      writer.writeString(value.getSignalEndingFlavour)
    }
    if (value.isHintOnlyDefined) {
      writer.writeName("hintonly")
      writer.writeBoolean(value.getHintOnly)
    }
    if (value.isAspectsDefined) {
      writer.writeName("aspects")
      numberFieldCodec.encode(writer, value.getAspects, encoderContext)
    }
    if (value.isPurgeDefined) {
      writer.writeName("purge")
      numberFieldCodec.encode(writer, value.getPurge, encoderContext)
    }
    if (value.isMaxExecutionsDefined) {
      writer.writeName("maxexecutions")
      writer.writeInt32(value.getMaxExecutions)
    }
    if (value.isPortalEffectDefined) {
      writer.writeName("portaleffect")
      writer.writeString(value.getPortalEffect)
    }
    if (value.isStartDescriptionDefined) {
      writer.writeName("startdescription")
      writer.writeString(value.getStartDescription)
    }
    if (value.isMutationsDefined) {
      writer.writeName("mutations")
      val listMutations = value.getMutations

      writer.writeStartArray()
      listMutations.foreach(v => {
        mutationCodec.encode(writer, v, encoderContext)
      })
      writer.writeEndArray()
    }
    if (value.isActionIdDefined) {
      writer.writeName(value.getActionIdFieldName)
      writer.writeString(value.getActionId)
    }
    if (value.isLinkedDefined) {
      writer.writeName("linked")
      val listLinked = value.getLinked

      writer.writeStartArray()
      listLinked.foreach(v => {
        linkedCodec.encode(writer, v, encoderContext)
      })
      writer.writeEndArray()
    }
    if (value.isDeckEffectsDefined) {
      writer.writeName(value.getDeckEffectsFieldName)
      numberFieldCodec.encode(writer, value.getDeckEffects, encoderContext)
    }
    if (value.isRequirementsDefined) {
      writer.writeName("requirements")
      numberAndStringFieldCodec.encode(writer, value.getRequirements, encoderContext)
    }
    if (value.isTableReqsDefined) {
      writer.writeName("tablereqs")
      numberFieldCodec.encode(writer, value.getTableReqs, encoderContext)
    }
    if (value.isExtantreqsDefined) {
      writer.writeName("extantreqs")
      numberFieldCodec.encode(writer, value.getExtantreqs, encoderContext)
    }
    if (value.isInternalDeckDefined) {
      writer.writeName("internaldeck")
      deckCodec.encode(writer, value.getInternalDeck, encoderContext)
    }
    if (value.isAltDefined) {
      writer.writeName("alt")
      val listAlt = value.getAlt

      writer.writeStartArray()
      listAlt.foreach(v => {
        altCodec.encode(writer, v, encoderContext)
      })
      writer.writeEndArray()
    }
    if (value.isCraftableDefined) {
      writer.writeName("craftable")
      writer.writeBoolean(value.getCraftable)
    }
    if (value.isEndingDefined) {
      writer.writeName("ending")
      writer.writeString(value.getEnding)
    }
    if (value.isEffectsDefined) {
      writer.writeName("effects")
      numberAndStringFieldCodec.encode(writer, value.getEffects, encoderContext)
    }
    if (value.isSlotsDefined) {
      writer.writeName("slots")
      val listSlots = value.getSlots

      writer.writeStartArray()
      listSlots.foreach(v => {
        slotCodec.encode(writer, v, encoderContext)
      })
      writer.writeEndArray()
    }
    if (value.isDeleteVerbDefined) {
      writer.writeName("deleteverb")
      numberFieldCodec.encode(writer, value.getDeleteVerb, encoderContext)
    }
    if (value.isSignalImportantLoopDefined) {
      writer.writeName("signalimportantloop")
      writer.writeBoolean(value.getSignalImportantLoop)
    }
    if (value.isAlternativeRecipesDefined) {
      writer.writeName("alternativerecipes")
      val listAlt = value.getAlternativeRecipes

      writer.writeStartArray()
      listAlt.foreach(v => {
        altCodec.encode(writer, v, encoderContext)
      })
      writer.writeEndArray()
    }
    if (value.isHaltVerbDefined) {
      writer.writeName("haltverb")
      numberFieldCodec.encode(writer, value.getHaltVerb, encoderContext)
    }
    if (value.isWarmUpDefined) {
      writer.writeName("warmup")
      writer.writeInt32(value.getWarmUp)
    }
    if (value.isBurnImageDefined) {
      writer.writeName("burnimage")
      writer.writeString(value.getBurnImage)
    }
    writer.writeEndDocument()
  }

  override def getEncoderClass: Class[Recipe] = classOf[Recipe]


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
