package model.codec

import model.AppException
import model.enums.{ActionIdFields, DeckEffectsFields}
import model.field.{Alt,Expulsion, Mutation, NumberField, StringField}
import org.bson.{BsonReader, BsonType, BsonWriter}
import org.bson.codecs.{Codec, DecoderContext, EncoderContext}
import org.bson.codecs.configuration.CodecRegistry

import scala.collection.mutable.ListBuffer

class AltCodec (val registry: CodecRegistry) extends Codec[Alt] {

  val expulsionCodec = registry.get(classOf[Expulsion])
  val numberFieldCodec = registry.get(classOf[NumberField])
  val mutationCodec = registry.get(classOf[Mutation])
  val stringFieldCodec = registry.get(classOf[StringField])

  override def decode(reader: BsonReader, decoderContext: DecoderContext): Alt = {
    val alt: Alt = Alt("")

    reader.readStartDocument()

    while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
      val fieldName: String = reader.readName

      if (fieldName == "_id")
        reader.skipValue()
      else if (fieldName == "id")
        alt.setId(reader.readString())
      else if (fieldName == "chance")
        alt.setChance(reader.readInt32())
      else if (fieldName == "additional")
        alt.setAdditional(reader.readBoolean())
      else if (fieldName == "expulsion")
        alt.setExpulsion(expulsionCodec.decode(reader, decoderContext))
      else if (fieldName == "effects")
        alt.setEffects(numberFieldCodec.decode(reader, decoderContext))
      else if (fieldName == "requirements")
        alt.setRequirements(numberFieldCodec.decode(reader, decoderContext))
      else if (fieldName == "startdescription")
        alt.setStartDescription(reader.readString())
      else if (fieldName == "mutations") {
        val listMutation = ListBuffer[Mutation]()

        reader.readStartArray()
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
          listMutation += mutationCodec.decode(reader, decoderContext)
        }
        reader.readEndArray()

        alt.setMutations(listMutation.toList)
      }
      else if (fieldName == "label")
        alt.setLabel(reader.readString())
      else if (fieldName == "challenges")
        alt.setChallenges(stringFieldCodec.decode(reader, decoderContext))
      else if (fieldName == "challengesOne")
        alt.setChallengesOne(reader.readString())
      else if (fieldName == "description")
        alt.setDescription(reader.readString())
      else if (fieldName == "deckeffects")
        alt.setDeckEffects(numberFieldCodec.decode(reader, decoderContext),DeckEffectsFields.deckeffects)
      else if(fieldName == "deckeffect")
        alt.setDeckEffects(numberFieldCodec.decode(reader, decoderContext),DeckEffectsFields.deckeffect)
      else if (fieldName == "actionId")
        alt.setActionId(reader.readString(),ActionIdFields.actionId)
      else if (fieldName == "actionid")
        alt.setActionId(reader.readString(),ActionIdFields.actionid)
      else if (fieldName == "ending")
        alt.setEnding(reader.readString())
      else if (fieldName == "signalEndingFlavour")
        alt.setSignalEndingFlavour(reader.readString())
      else if (fieldName == "extantreqs")
        alt.setExtantReqs(numberFieldCodec.decode(reader, decoderContext))
      else if (fieldName == "craftable")
        alt.setCraftable(reader.readBoolean())
      else throw AppException(s"Ending json decode error:Unknown field:$fieldName")
    }

    reader.readEndDocument()

    alt
  }

  override def encode(writer: BsonWriter, value: Alt, encoderContext: EncoderContext): Unit = {
    writer.writeStartDocument()
    writer.writeName("id")
    writer.writeString(value.getId)
    if (value.isChanceDefined) {
      writer.writeName("chance")
      writer.writeInt32(value.getChance)
    }
    if (value.isAdditionalDefined) {
      writer.writeName("additional")
      writer.writeBoolean(value.getAdditional)
    }
    if (value.isExpulsionDefined) {
      writer.writeName("expulsion")
      expulsionCodec.encode(writer, value.getExpulsion, encoderContext)
    }
    if (value.isEffectsDefined) {
      writer.writeName("effects")
      numberFieldCodec.encode(writer, value.getEffects, encoderContext)
    }
    if (value.isRequirementsDefined) {
      writer.writeName("requirements")
      numberFieldCodec.encode(writer, value.getRequirements, encoderContext)
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
    if (value.isLabelDefined) {
      writer.writeName("label")
      writer.writeString(value.getLabel)
    }
    if (value.isChallengesDefined) {
      writer.writeName("challenges")
      stringFieldCodec.encode(writer, value.getChallenges, encoderContext)
    }
    if (value.isChallengesOneDefined) {
      writer.writeName("challengesOne")
      writer.writeString(value.getChallengesOne)
    }
    if (value.isDescriptionDefined) {
      writer.writeName("description")
      writer.writeString(value.getDescription)
    }
    if (value.isDeckEffectsDefined) {
      writer.writeName(value.getDeckEffectsFieldName)
      numberFieldCodec.encode(writer, value.getDeckEffects, encoderContext)
    }
    if (value.isActionIdDefined) {
      writer.writeName(value.getActionIdFieldName)
      writer.writeString(value.getActionId)
    }
    if (value.isEndingDefined) {
      writer.writeName("ending")
      writer.writeString(value.getEnding)
    }
    if (value.isSignalEndingFlavourDefined) {
      writer.writeName("signalEndingFlavour")
      writer.writeString(value.getSignalEndingFlavour)
    }
    if (value.isExtantReqsDefined) {
      writer.writeName("extantreqs")
      numberFieldCodec.encode(writer, value.getExtantReqs, encoderContext)
    }
    if (value.isCraftableDefined) {
      writer.writeName("craftable")
      writer.writeBoolean(value.getCraftable)
    }
    writer.writeEndDocument()
  }

  override def getEncoderClass: Class[Alt] = classOf[Alt]

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
