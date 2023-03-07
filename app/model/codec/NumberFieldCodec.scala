package model.codec

import model.field.NumberField
import org.bson.codecs.{Codec, DecoderContext, EncoderContext}
import org.bson.{BsonReader, BsonType, BsonWriter}

class NumberFieldCodec extends Codec[NumberField] {

  override def decode(reader: BsonReader, decoderContext: DecoderContext): NumberField = {
    val values = collection.mutable.Map[String, Int]()
    var comments: Option[String] = None

    reader.readStartDocument()

    while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
      val fieldName = reader.readName

      if (fieldName == "_id")
        reader.skipValue()
      else if (fieldName == "comments")
        comments = Option(reader.readString())
      else {
        val fieldValue = reader.readInt32()

        values += fieldName -> fieldValue
      }
    }

    reader.readEndDocument()

    NumberField(values.toMap, comments)
  }

  override def encode(writer: BsonWriter, value: NumberField, encoderContext: EncoderContext): Unit = {
    writer.writeStartDocument()

    value.values.foreach({
      case (fieldName, fieldValue) =>
        writer.writeName(fieldName)
        writer.writeInt32(fieldValue)
    })

    if (value.comments.isDefined) {
      writer.writeName("comments")
      writer.writeString(value.comments.get)
    }

    writer.writeEndDocument()
  }

  override def getEncoderClass: Class[NumberField] = classOf[NumberField]

}