package model.codec

import model.AppException
import model.field.XTriggersField
import org.bson.{BsonReader, BsonType, BsonWriter}
import org.bson.codecs.{Codec, DecoderContext, EncoderContext}

class XTriggersFieldCodec extends Codec[XTriggersField] {


  override def decode(reader: BsonReader, decoderContext: DecoderContext): XTriggersField = {
    val desk: XTriggersField = XTriggersField("")

    reader.readStartDocument()

    while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
      val fieldName: String = reader.readName

      if (fieldName == "_id")
        reader.skipValue()
      else if (fieldName == "id")
        desk.setId(reader.readString())
      else if (fieldName == "morpheffect")
        desk.setMorphEffect(reader.readString())
      else if (fieldName == "level")
        desk.setLevel(reader.readInt32())
      else if (fieldName == "chance")
        desk.setChance(reader.readInt32())
      else throw AppException(s"Ending json decode error:Unknown field:$fieldName")
    }

    reader.readEndDocument()

    desk
  }

  override def encode(writer: BsonWriter, value: XTriggersField, encoderContext: EncoderContext): Unit = {
    writer.writeStartDocument()

    writer.writeName("id")
    writer.writeString(value.getId)
    if (value.isMorphEffectDefined) {
      writer.writeName("morpheffect")
      writer.writeString(value.getMorphEffect)
    }
    if (value.isLevelDefined) {
      writer.writeName("level")
      writer.writeInt32(value.getLevel)
    }
    if (value.isChanceDefined) {
      writer.writeName("chance")
      writer.writeInt32(value.getChance)
    }

    writer.writeEndDocument()
  }

  override def getEncoderClass: Class[XTriggersField] = classOf[XTriggersField]

  //id
  //morpheffect
  //level
  //chance
}
