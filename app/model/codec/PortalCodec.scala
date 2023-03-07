package model.codec

import model.AppException
import model.card.Portal
import model.field.Consequence
import org.bson.{BsonReader, BsonType, BsonWriter}
import org.bson.codecs.{Codec, DecoderContext, EncoderContext}
import org.bson.codecs.configuration.CodecRegistry

import scala.collection.mutable.ListBuffer

class PortalCodec (val registry: CodecRegistry) extends Codec[Portal] {

  val consequenceCodec = registry.get(classOf[Consequence])

  override def decode(reader: BsonReader, decoderContext: DecoderContext): Portal = {
    val portal: Portal = Portal("")

    reader.readStartDocument()

    while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
      val fieldName: String = reader.readName

      if (fieldName == "_id")
        reader.skipValue()
      else if (fieldName == "id")
        portal.setId(reader.readString())
      else if (fieldName == "label")
        portal.setLabel(reader.readString())
      else if (fieldName == "description")
        portal.setDescription(reader.readString())
      else if (fieldName == "comments")
        portal.setComments(reader.readString())
      else if (fieldName == "egressid")
        portal.setEgressId(reader.readString())
      else if (fieldName == "otherworldid")
        portal.setOtherWorldId(reader.readString())
      else if (fieldName == "icon")
        portal.setIcon(reader.readString())
      else if (fieldName == "consequences") {
        val listConsequences = ListBuffer[Consequence]()

        reader.readStartArray()
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
          listConsequences += consequenceCodec.decode(reader,decoderContext)
        }
        reader.readEndArray()

        portal.setConsequences(listConsequences.toList)
      }
      else throw AppException(s"Ending json decode error:Unknown field:$fieldName")
    }

    reader.readEndDocument()

    portal
  }

  override def encode(writer: BsonWriter, value: Portal, encoderContext: EncoderContext): Unit = {
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
    if (value.isEgressIdDefined) {
      writer.writeName("egressid")
      writer.writeString(value.getEgressId)
    }
    if (value.isOtherWorldIdDefined) {
      writer.writeName("otherworldid")
      writer.writeString(value.getOtherWorldId)
    }
    if (value.isIconDefined) {
      writer.writeName("icon")
      writer.writeString(value.getIcon)
    }
    if (value.isConsequencesDefined) {
      writer.writeName("consequences")
      val listConsequences = value.getConsequences
      writer.writeStartArray()
      listConsequences.foreach(v => {
        consequenceCodec.encode(writer,v,encoderContext)
      })
      writer.writeEndArray()
    }
    writer.writeEndDocument()
  }

  override def getEncoderClass: Class[Portal] = classOf[Portal]

  //  egressid
  //  otherworldid
  //  icon
  //  description
  //  consequences
  //  id
  //  label
}
