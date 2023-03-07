package model.codec

import model.AppException
import model.card.Legacy
import model.field.NumberField
import org.bson.codecs.configuration.CodecRegistry
import org.bson.codecs.{Codec, DecoderContext, EncoderContext}
import org.bson.{BsonReader, BsonType, BsonWriter}

import scala.collection.mutable.ListBuffer

class LegacyCodec(val registry: CodecRegistry) extends Codec[Legacy] {

  val numberFieldCodec = registry.get(classOf[NumberField])

  override def decode(reader: BsonReader, decoderContext: DecoderContext): Legacy = {
    val legacy: Legacy = Legacy("")

    reader.readStartDocument()

    while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
      val fieldName: String = reader.readName

      if (fieldName == "_id")
        reader.skipValue()
      else if (fieldName == "id")
        legacy.setId(reader.readString())
      else if (fieldName == "label")
        legacy.setLabel(reader.readString())
      else if (fieldName == "description")
        legacy.setDescription(reader.readString())
      else if (fieldName == "comments")
        legacy.setComments(reader.readString())
      else if (fieldName == "image")
        legacy.setImage(reader.readString())
      else if (fieldName == "excludesOnEnding") {
        val listExcludesOnEnding = ListBuffer[String]()

        reader.readStartArray()
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
          listExcludesOnEnding += reader.readString()
        }
        reader.readEndArray()

        legacy.setExcludesOnEnding(listExcludesOnEnding.toList)
      }
      else if (fieldName == "statusbarelements") {
        val listStatusbarelements = ListBuffer[String]()

        reader.readStartArray()
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
          listStatusbarelements += reader.readString()
        }
        reader.readEndArray()

        legacy.setStatusBarElements(listStatusbarelements.toList)
      } else if (fieldName == "fromending")
        legacy.setFromEnding(reader.readString())
      else if (fieldName == "newstart")
        legacy.setNewStart(reader.readBoolean())
      else if (fieldName == "effects")
        legacy.setEffects(numberFieldCodec.decode(reader, decoderContext))
      else if (fieldName == "tablecoverimage")
        legacy.setTableCoverImage(reader.readString())
      else if (fieldName == "startdescription")
        legacy.setStartDescription(reader.readString())
      else if (fieldName == "availableWithoutEndingMatch")
        legacy.setAvailableWithoutEndingMatch(reader.readBoolean())
      else if (fieldName == "family")
        legacy.setFamily(reader.readString())
      else if (fieldName == "startingverbid")
        legacy.setStartingVerbId(reader.readString())
      else throw AppException(s"Ending json decode error:Unknown field:$fieldName")
    }

    reader.readEndDocument()

    legacy
  }

  override def encode(writer: BsonWriter, value: Legacy, encoderContext: EncoderContext): Unit = {
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
    if (value.isImageDefined) {
      writer.writeName("image")
      writer.writeString(value.getImage)
    }
    if (value.isExcludesOnEndingDefined) {
      writer.writeName("excludesOnEnding")
      val listExcludesOnEnding = value.getExcludesOnEnding
      writer.writeStartArray()
      listExcludesOnEnding.foreach(v => {
        writer.writeString(v)
      })
      writer.writeEndArray()
    }
    if (value.isStatusBarElementsDefined) {
      writer.writeName("statusbarelements")
      val listStatusBarElements = value.getStatusBarElements
      writer.writeStartArray()
      listStatusBarElements.foreach(v => {
        writer.writeString(v)
      })
      writer.writeEndArray()
    }
    if (value.isFromEndingDefined) {
      writer.writeName("fromending")
      writer.writeString(value.getFromEnding)
    }
    if (value.isNewStartDefined) {
      writer.writeName("newstart")
      writer.writeBoolean(value.getNewStart)
    }
    if (value.isEffectsDefined) {
      writer.writeName("effects")
      numberFieldCodec.encode(writer, value.getEffects, encoderContext)
    }
    if (value.isTableCoverImageDefined) {
      writer.writeName("tablecoverimage")
      writer.writeString(value.getTableCoverImage)
    }
    if (value.isStartDescriptionDefined) {
      writer.writeName("startdescription")
      writer.writeString(value.getStartDescription)
    }
    if (value.isAvailableWithoutEndingMatchDefined) {
      writer.writeName("availableWithoutEndingMatch")
      writer.writeBoolean(value.getAvailableWithoutEndingMatch)
    }
    if (value.isFamilyDefined) {
      writer.writeName("family")
      writer.writeString(value.getFamily)
    }
    if (value.isStartingVerbIdDefined) {
      writer.writeName("startingverbid")
      writer.writeString(value.getStartingVerbId)
    }
    writer.writeEndDocument()
  }

  override def getEncoderClass: Class[Legacy] = classOf[Legacy]

  //  image
  //  description
  //  excludesOnEnding
  //  statusbarelements
  //  label
  //  fromending
  //  newstart
  //  effects
  //  tablecoverimage
  //  startdescription
  //  availableWithoutEndingMatch
  //  id
  //  family
  //  startingverbid
}
