package model.codec

import model.AppException
import model.enums.ActionIdFields
import model.field.{NumberField, Slot}
import org.bson.codecs.configuration.CodecRegistry
import org.bson.codecs.{Codec, DecoderContext, EncoderContext}
import org.bson.{BsonReader, BsonType, BsonWriter}

class SlotCodec(val registry: CodecRegistry) extends Codec[Slot] {

  val numberFieldCodec = registry.get(classOf[NumberField])

  override def decode(reader: BsonReader, decoderContext: DecoderContext): Slot = {
    val slot: Slot = Slot("")

    reader.readStartDocument()

    while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
      val fieldName: String = reader.readName

      if (fieldName == "_id")
        reader.skipValue()
      else if (fieldName == "id")
        slot.setId(reader.readString())
      else if (fieldName == "label")
        slot.setLabel(reader.readString())
      else if (fieldName == "description")
        slot.setDescription(reader.readString())
      else if (fieldName == "comments")
        slot.setComments(reader.readString())
      else if (fieldName == "actionId")
        slot.setActionId(reader.readString(),ActionIdFields.actionId)
      else if(fieldName == "actionid")
        slot.setActionId(reader.readString(),ActionIdFields.actionid)
      else if (fieldName == "required")
        slot.setRequired(numberFieldCodec.decode(reader, decoderContext))
      else if (fieldName == "forbidden") {
        slot.setForbidden(numberFieldCodec.decode(reader, decoderContext))
      } else if (fieldName == "greedy")
        slot.setGreedy(reader.readBoolean())
      else if (fieldName == "noanim")
        slot.setNoAnim(reader.readBoolean())
      else if (fieldName == "consumes")
        slot.setConsumes(reader.readBoolean())
      else if (fieldName == "unique")
        slot.setUnique(reader.readBoolean())
      else throw AppException(s"Ending json decode error:Unknown field:$fieldName")
    }

    reader.readEndDocument()

    slot
  }

  override def encode(writer: BsonWriter, value: Slot, encoderContext: EncoderContext): Unit = {
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
    if (value.isActionIdDefined) {
      writer.writeName(value.getActionIdFieldName)
      writer.writeString(value.getActionId)
    }
    if (value.isRequiredDefined) {
      writer.writeName("required")
      numberFieldCodec.encode(writer, value.getRequired, encoderContext)
    }
    if (value.isForbiddenDefined) {
      writer.writeName("forbidden")
      numberFieldCodec.encode(writer, value.getForbidden, encoderContext)
    }
    if (value.isGreedyDefined) {
      writer.writeName("greedy")
      writer.writeBoolean(value.getGreedy)
    }
    if (value.isNoAnimDefined) {
      writer.writeName("noanim")
      writer.writeBoolean(value.getNoAnim)
    }
    if (value.isConsumesDefined) {
      writer.writeName("consumes")
      writer.writeBoolean(value.getConsumes)
    }
    if (value.isUniqueDefined) {
      writer.writeName("unique")
      writer.writeBoolean(value.getUnique)
    }
    writer.writeEndDocument()
  }

  override def getEncoderClass: Class[Slot] = classOf[Slot]

  // description
  // id
  // label
  // comments
  // actionId
  // actionId
  // required
  // forbidden
  // greedy
  // noanim
  // consumes
  // unique
}