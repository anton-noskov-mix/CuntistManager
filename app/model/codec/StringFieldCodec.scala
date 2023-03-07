package model.codec

import model.field.StringField
import org.bson.codecs.{Codec, DecoderContext, EncoderContext}
import org.bson.{BsonReader, BsonType, BsonWriter}

class StringFieldCodec extends Codec[StringField] {

  override def decode(reader: BsonReader, decoderContext: DecoderContext): StringField = {
    val values = collection.mutable.Map[String, String]()

    reader.readStartDocument()

    while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
      val fieldName = reader.readName
      val fieldValue = reader.readString()

      if (fieldName == "_id")
        reader.skipValue()
      else
        values += fieldName -> fieldValue
    }

    reader.readEndDocument()

    StringField(values.toMap)
  }

  override def encode(writer: BsonWriter, value: StringField, encoderContext: EncoderContext): Unit = {
    writer.writeStartDocument()
    value.values.foreach({
      case (fieldName, fieldValue) =>
        writer.writeName(fieldName)
        writer.writeString(fieldValue)
    })
    writer.writeEndDocument()
  }

  override def getEncoderClass: Class[StringField] = classOf[StringField]

}
