package model.codec

import model.AppException
import model.card.Desk
import model.field.StringField
import org.bson.codecs.configuration.CodecRegistry
import org.bson.codecs.{Codec, DecoderContext, EncoderContext}
import org.bson.{BsonReader, BsonType, BsonWriter}

import scala.collection.mutable.ListBuffer

class DeskCodec(val registry: CodecRegistry) extends Codec[Desk] {

  val stringFieldCodec = registry.get(classOf[StringField])

  override def decode(reader: BsonReader, decoderContext: DecoderContext): Desk = {
    val desk: Desk = Desk("")

    reader.readStartDocument()

    while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
      val fieldName: String = reader.readName

      if (fieldName == "_id")
        reader.skipValue()
      else if (fieldName == "id")
        desk.setId(reader.readString())
      else if (fieldName == "label")
        desk.setLabel(reader.readString())
      else if (fieldName == "description")
        desk.setDescription(reader.readString())
      else if (fieldName == "comments")
        desk.setComments(reader.readString())
      else if (fieldName == "forlegacyfamily")
        desk.setForLegacyFamily(reader.readString())
      else if (fieldName == "drawmessages")
        desk.setDrawMessages(stringFieldCodec.decode(reader, decoderContext))
      else if (fieldName == "defaultcard")
        desk.setDefaultCard(reader.readString())
      else if (fieldName == "defaultdrawmessages")
        desk.setDefaultDrawMessages(stringFieldCodec.decode(reader, decoderContext))
      else if (fieldName == "resetonexhaustion")
        desk.setResetOnExhaustion(reader.readBoolean())
      else if (fieldName == "spec") {
        val listSpec = ListBuffer[String]()

        reader.readStartArray()
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
          listSpec += reader.readString()
        }
        reader.readEndArray()

        desk.setSpec(listSpec.toList)
      }
      else throw AppException(s"Ending json decode error:Unknown field:$fieldName")
    }

    reader.readEndDocument()

    desk
  }

  override def encode(writer: BsonWriter, value: Desk, encoderContext: EncoderContext): Unit = {
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
    if (value.isForLegacyFamilyDefined) {
      writer.writeName("forlegacyfamily")
      writer.writeString(value.getForLegacyFamily)
    }
    if (value.isDrawMessagesDefined) {
      writer.writeName("drawmessages")
      stringFieldCodec.encode(writer, value.getDrawMessages, encoderContext)
    }
    if (value.isDefaultCardDefined) {
      writer.writeName("defaultcard")
      writer.writeString(value.getDefaultCard)
    }
    if (value.isDefaultDrawMessagesDefined) {
      writer.writeName("defaultdrawmessages")
      stringFieldCodec.encode(writer, value.getDefaultDrawMessages, encoderContext)
    }
    if (value.isResetOnExhaustionDefined) {
      writer.writeName("resetonexhaustion")
      writer.writeBoolean(value.getResetOnExhaustion)
    }
    if (value.isSpecDefined) {
      writer.writeName("spec")
      val listSpec = value.getSpec

      writer.writeStartArray()
      listSpec.foreach(v => {
        writer.writeString(v)
      })
      writer.writeEndArray()
    }
    writer.writeEndDocument()
  }

  override def getEncoderClass: Class[Desk] = classOf[Desk]


  //  comments
  //  forlegacyfamily
  //  drawmessages
  //  description
  //  defaultcard
  //  id
  //  label
  //  defaultdrawmessages
  //  spec
  //  resetonexhaustion
}