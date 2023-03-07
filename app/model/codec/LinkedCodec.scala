package model.codec

import model.AppException
import model.enums.ActionIdFields
import model.field.{Linked, Mutation, NumberAndStringField, NumberField, StringField}
import org.bson.{BsonReader, BsonType, BsonWriter}
import org.bson.codecs.{Codec, DecoderContext, EncoderContext}
import org.bson.codecs.configuration.CodecRegistry

import scala.collection.mutable.ListBuffer

class LinkedCodec(val registry: CodecRegistry) extends Codec[Linked] {

  val stringFieldCodec = registry.get(classOf[StringField])
  val numberFieldCodec = registry.get(classOf[NumberField])
  val numberAndStringFieldCodec = registry.get(classOf[NumberAndStringField])
  val mutationCodec = registry.get(classOf[Mutation])

  override def decode(reader: BsonReader, decoderContext: DecoderContext): Linked = {
    val linked: Linked = Linked("")

    reader.readStartDocument()

    while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
      val fieldName: String = reader.readName

      if (fieldName == "_id")
        reader.skipValue()
      else if (fieldName == "id")
        linked.setId(reader.readString())
      else if (fieldName == "chance")
        linked.setChance(reader.readInt32())
      else if (fieldName == "challenges")
        linked.setChallenges(stringFieldCodec.decode(reader, decoderContext))
      else if (fieldName == "effects")
        linked.setEffects(numberFieldCodec.decode(reader, decoderContext))
      else if (fieldName == "startdescription")
        linked.setStartDescription(reader.readString())
      else if (fieldName == "actionId")
        linked.setActionId(reader.readString(),ActionIdFields.actionId)
      else if (fieldName == "actionid")
        linked.setActionId(reader.readString(),ActionIdFields.actionid)
      else if (fieldName == "label")
        linked.setLabel(reader.readString())
      else if (fieldName == "warmup")
        linked.setWarmUp(reader.readInt32())
      else if (fieldName == "requirements")
        linked.setRequirements(numberAndStringFieldCodec.decode(reader, decoderContext))
      else if (fieldName == "mutations") {
        val listMutation = ListBuffer[Mutation]()

        reader.readStartArray()
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
          listMutation += mutationCodec.decode(reader, decoderContext)
        }
        reader.readEndArray()

        linked.setMutations(listMutation.toList)
      }
      else if (fieldName == "purge")
        linked.setPurge(numberFieldCodec.decode(reader, decoderContext))
      else if (fieldName == "extantreqs")
        linked.setExtantreqs(numberFieldCodec.decode(reader, decoderContext))
      else if (fieldName == "linked") {
        val listLinked = ListBuffer[Linked]()

        reader.readStartArray()
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
          listLinked += decode(reader, decoderContext)
        }
        reader.readEndArray()

        linked.setLinked(listLinked.toList)
      }
      else throw AppException(s"Ending json decode error:Unknown field:$fieldName")
    }

    reader.readEndDocument()

    linked
  }

  override def encode(writer: BsonWriter, value: Linked, encoderContext: EncoderContext): Unit = {
    writer.writeStartDocument()
    writer.writeName("id")
    writer.writeString(value.getId)
    if (value.isChanceDefined) {
      writer.writeName("chance")
      writer.writeInt32(value.getChance)
    }
    if (value.isChallengesDefined) {
      writer.writeName("challenges")
      stringFieldCodec.encode(writer, value.getChallenges, encoderContext)
    }
    if (value.isEffectsDefined) {
      writer.writeName("effects")
      numberFieldCodec.encode(writer, value.getEffects, encoderContext)
    }
    if (value.isStartDescriptionDefined) {
      writer.writeName("startdescription")
      writer.writeString(value.getStartDescription)
    }
    if (value.isActionIdDefined) {
      writer.writeName(value.getActionIdFieldName)
      writer.writeString(value.getActionId)
    }
    if (value.isLabelDefined) {
      writer.writeName("label")
      writer.writeString(value.getLabel)
    }
    if (value.isWarmUpDefined) {
      writer.writeName("warmup")
      writer.writeInt32(value.getWarmUp)
    }
    if (value.isRequirementsDefined) {
      writer.writeName("requirements")
      numberAndStringFieldCodec.encode(writer, value.getRequirements, encoderContext)
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
    if (value.isPurgeDefined) {
      writer.writeName("purge")
      numberFieldCodec.encode(writer, value.getPurge, encoderContext)
    }
    if (value.isExtantreqsDefined) {
      writer.writeName("extantreqs")
      numberFieldCodec.encode(writer, value.getExtantreqs, encoderContext)
    }
    if (value.isLinkedDefined) {
      writer.writeName("linked")
      val listLinked = value.getLinked

      writer.writeStartArray()
      listLinked.foreach(v => {
        encode(writer, v, encoderContext)
      })
      writer.writeEndArray()
    }
    writer.writeEndDocument()
  }

  override def getEncoderClass: Class[Linked] = classOf[Linked]

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
