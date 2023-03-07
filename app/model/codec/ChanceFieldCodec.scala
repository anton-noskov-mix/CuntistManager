package model.codec

import model.AppException
import model.field.ChanceField
import org.bson.{BsonReader, BsonType, BsonWriter}
import org.bson.codecs.{Codec, DecoderContext, EncoderContext}


class ChanceFieldCodec extends Codec[ChanceField] {

  override def decode(reader: BsonReader, decoderContext: DecoderContext): ChanceField = {
    val desk: ChanceField = ChanceField()

    reader.readStartDocument()

    while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
      val fieldName: String = reader.readName

      if (fieldName == "_id")
        reader.skipValue()
      else if (fieldName == "id")
        desk.id = reader.readString()
      else if (fieldName == "chance")
        desk.chance = reader.readInt32()
      else throw AppException(s"Ending json decode error:Unknown field:$fieldName")
    }

    reader.readEndDocument()

    desk
  }

  override def encode(writer: BsonWriter, value: ChanceField, encoderContext: EncoderContext): Unit = {
    writer.writeStartDocument()

    writer.writeName("id")
    writer.writeString(value.id)
    writer.writeName("chance")
    writer.writeInt32(value.chance)

    writer.writeEndDocument()
  }

  override def getEncoderClass: Class[ChanceField] = classOf[ChanceField]
}
