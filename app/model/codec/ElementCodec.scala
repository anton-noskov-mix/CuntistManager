package model.codec

import model.AppException
import model.card.Element
import model.field.{ChanceField, NumberField, Slot, XTriggers}
import org.bson.{BsonReader, BsonType, BsonWriter}
import org.bson.codecs.{Codec, DecoderContext, EncoderContext}
import org.bson.codecs.configuration.CodecRegistry

import scala.collection.mutable.ListBuffer

class ElementCodec (val registry: CodecRegistry) extends Codec[Element] {

  val numberFieldCodec = registry.get(classOf[NumberField])
  val chanceFieldCodec = registry.get(classOf[ChanceField])
  val slotCodec = registry.get(classOf[Slot])
  val xTriggersCodec = registry.get(classOf[XTriggers])


  override def decode(reader: BsonReader, decoderContext: DecoderContext): Element = {
    val element: Element = Element("")

    reader.readStartDocument()

    while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
      val fieldName: String = reader.readName

      if (fieldName == "_id")
        reader.skipValue()
      else if (fieldName == "id")
        element.setId(reader.readString())
      else if (fieldName == "label")
        element.setLabel(reader.readString())
      else if (fieldName == "description")
        element.setDescription(reader.readString())
      else if (fieldName == "comments")
        element.setComments(reader.readString())
      else if (fieldName == "induces") {
        val listInduces = ListBuffer[ChanceField]()

        reader.readStartArray()
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
          listInduces += chanceFieldCodec.decode(reader,decoderContext)
        }
        reader.readEndArray()

        element.setInduces(listInduces.toList)
      } else if (fieldName == "aspects")
        element.setAspects(numberFieldCodec.decode(reader,decoderContext))
      else if (fieldName == "icon")
        element.setIcon(reader.readString())
      else if (fieldName == "lifetime")
        element.setLifeTime(reader.readInt32())
      else if (fieldName == "burnTo")
        element.setBurnTo(reader.readString())
      else if (fieldName == "resaturate")
        element.setResaturate(reader.readBoolean())
      else if (fieldName == "animFrames")
        element.setAnimFrames(reader.readInt32())
      else if (fieldName == "uniquenessgroup")
        element.setUniquenessGroup(reader.readString())
      else if (fieldName == "xtriggers")
        element.setXTriggers(xTriggersCodec.decode(reader,decoderContext))
      else if (fieldName == "manifestationtype")
        element.setManifestationType(reader.readString())
      else if (fieldName == "inherits")
        element.setInherits(reader.readString())
      else if (fieldName == "isAspect")
        element.setIsAspect(reader.readBoolean())
      else if (fieldName == "noartneeded")
        element.setNoArtNeeded(reader.readBoolean())
      else if (fieldName == "verbicon")
        element.setVerbIcon(reader.readString())
      else if (fieldName == "lever")
        element.setLever(reader.readString())
      else if (fieldName == "isHidden")
        element.setIsHidden(reader.readBoolean())
      else if (fieldName == "metafictional")
        element.setMetaFictional(reader.readBoolean())
      else if (fieldName == "slots") {
        val listSlots = ListBuffer[Slot]()

        reader.readStartArray()
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
          listSlots += slotCodec.decode(reader,decoderContext)
        }
        reader.readEndArray()

        element.setSlots(listSlots.toList)
      }
      else if (fieldName == "unique")
        element.setUnique(reader.readBoolean())
      else if (fieldName == "decayTo")
        element.setDecayTo(reader.readString())
      else throw AppException(s"Ending json decode error:Unknown field:$fieldName")
    }

    reader.readEndDocument()

    element
  }

  override def encode(writer: BsonWriter, value: Element, encoderContext: EncoderContext): Unit = {
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
    if (value.isInducesDefined) {
      writer.writeName("induces")
      val listInduces = value.getInduces

      writer.writeStartArray()
      listInduces.foreach(v => {
        chanceFieldCodec.encode(writer,v,encoderContext)
      })
      writer.writeEndArray()
    }
    if (value.isAspectsDefined) {
      writer.writeName("aspects")
      numberFieldCodec.encode(writer,value.getAspects,encoderContext)
    }
    if (value.isIconDefined) {
      writer.writeName("icon")
      writer.writeString(value.getIcon)
    }
    if (value.isLifeTimeDefined) {
      writer.writeName("lifetime")
      writer.writeInt32(value.getLifeTime)
    }
    if (value.isBurnToDefined) {
      writer.writeName("burnTo")
      writer.writeString(value.getBurnTo)
    }
    if (value.isResaturateDefine) {
      writer.writeName("resaturate")
      writer.writeBoolean(value.getResaturate)
    }
    if (value.isAnimFramesDefined) {
      writer.writeName("animFrames")
      writer.writeInt32(value.getAnimFrames)
    }
    if (value.isUniquenessGroupDefined) {
      writer.writeName("uniquenessgroup")
      writer.writeString(value.getUniquenessGroup)
    }
    if (value.isXTriggersDefined) {
      writer.writeName("xtriggers")
      xTriggersCodec.encode(writer,value.getXTriggers,encoderContext)
    }
    if (value.isManifestationTypeDefine) {
      writer.writeName("manifestationtype")
      writer.writeString(value.getManifestationType)
    }
    if (value.isInheritsDefined) {
      writer.writeName("inherits")
      writer.writeString(value.getInherits)
    }
    if (value.isIsAspectDefine) {
      writer.writeName("isAspect")
      writer.writeBoolean(value.getIsAspect)
    }
    if (value.isNoArtNeededDefined) {
      writer.writeName("noartneeded")
      writer.writeBoolean(value.getNoArtNeeded)
    }
    if (value.isVerbIconDefined) {
      writer.writeName("verbicon")
      writer.writeString(value.getVerbIcon)
    }
    if (value.isLeverDefined) {
      writer.writeName("lever")
      writer.writeString(value.getLever)
    }
    if (value.isIsHiddenDefined) {
      writer.writeName("isHidden")
      writer.writeBoolean(value.getIsHidden)
    }
    if (value.isMetaFictionalDefined) {
      writer.writeName("metafictional")
      writer.writeBoolean(value.getMetaFictional)
    }
    if (value.isSlotsDefined) {
      writer.writeName("slots")
      val listSlots = value.getSlots

      writer.writeStartArray()
      listSlots.foreach(v => {
        slotCodec.encode(writer,v,encoderContext)
      })
      writer.writeEndArray()
    }
    if (value.isUniqueDefine) {
      writer.writeName("unique")
      writer.writeBoolean(value.getUnique)
    }
    if (value.isDecayTo) {
      writer.writeName("decayTo")
      writer.writeString(value.getDecayTo)
    }
    writer.writeEndDocument()
  }

  override def getEncoderClass: Class[Element] = classOf[Element]

  //  induces
  //  aspects
  //  icon
  //  lifetime
  //  description
  //  burnTo
  //  resaturate
  //  animFrames
  //  uniquenessgroup
  //  xtriggers
  //  manifestationtype
  //  id
  //  inherits
  //  isAspect
  //  noartneeded
  //  comments
  //  verbicon
  //  label
  //  lever
  //  isHidden
  //  metafictional
  //  slots
  //  unique
  //  decayTo
}
