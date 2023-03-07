package model.codec

import model.AppException
import model.card.Verb
import model.field.Slot
import org.bson.{BsonReader, BsonType, BsonWriter}
import org.bson.codecs.{Codec, DecoderContext, EncoderContext}
import org.bson.codecs.configuration.CodecRegistry

class VerbCodec (val registry: CodecRegistry) extends Codec[Verb] {

  val slotCodec = registry.get(classOf[Slot])

  override def decode(reader: BsonReader, decoderContext: DecoderContext): Verb = {
    val verb: Verb = Verb("")

    reader.readStartDocument()

    while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
      val fieldName: String = reader.readName

      if (fieldName == "_id")
        reader.skipValue()
      else if (fieldName == "id")
        verb.setId(reader.readString())
      else if (fieldName == "label")
        verb.setLabel(reader.readString())
      else if (fieldName == "description")
        verb.setDescription(reader.readString())
      else if (fieldName == "comments")
        verb.setComments(reader.readString())
      else if (fieldName == "icon")
        verb.setIcon(reader.readString())
      else if (fieldName == "atStart")
        verb.setAtStart(reader.readBoolean())
      else if (fieldName == "slot")
        verb.setSlot(slotCodec.decode(reader,decoderContext))
      else throw AppException(s"Ending json decode error:Unknown field:$fieldName")
    }

    reader.readEndDocument()

    verb
  }

  override def encode(writer: BsonWriter, value: Verb, encoderContext: EncoderContext): Unit = {
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
    if (value.isIconDefined) {
      writer.writeName("icon")
      writer.writeString(value.getIcon)
    }
    if (value.isAtStartDefined) {
      writer.writeName("atStart")
      writer.writeBoolean(value.getAtStart)
    }
    if (value.isSlotDefined) {
      writer.writeName("slot")
      slotCodec.encode(writer,value.getSlot,encoderContext)
    }
    writer.writeEndDocument()
  }

  override def getEncoderClass: Class[Verb] = classOf[Verb]

  //  icon
  //  description
  //  atStart
  //  id
  //  slot
  //  label
}