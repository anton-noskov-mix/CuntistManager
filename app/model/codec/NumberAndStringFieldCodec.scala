package model.codec

import model.field.NumberAndStringField
import org.bson.{BsonReader, BsonType, BsonWriter}
import org.bson.codecs.{Codec, DecoderContext, EncoderContext}


class NumberAndStringFieldCodec extends Codec[NumberAndStringField] {

  override def decode(reader: BsonReader, decoderContext: DecoderContext):  NumberAndStringField = {
    val numberAndStringField:NumberAndStringField = NumberAndStringField()
    val pNumberValues = collection.mutable.Map[String, Int]()
    val pStringValues = collection.mutable.Map[String, String]()
    reader.readStartDocument()

    while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
      val fieldName: String = reader.readName

      if (fieldName == "_id")
        reader.skipValue()
      else {
        try {
          val value = reader.readString()
          pStringValues += (fieldName -> value)
        } catch {
          case e:Exception =>
            val test = e
            val value = reader.readInt32()
            pNumberValues += (fieldName -> value)
        }

      }
    }

    reader.readEndDocument()

    numberAndStringField.stringValues = pStringValues.toMap
    numberAndStringField.numberValues = pNumberValues.toMap

    numberAndStringField
  }

  override def encode(writer: BsonWriter, value: NumberAndStringField, encoderContext: EncoderContext): Unit = {
    writer.writeStartDocument()

    if (value.numberValues.nonEmpty) {
      value.numberValues.foreach(v => {
        writer.writeName(v._1)
        writer.writeInt32(v._2)
      })
    }
    if (value.stringValues.nonEmpty) {
      value.stringValues.foreach(v => {
        writer.writeName(v._1)
        writer.writeString(v._2)
      })
    }
    writer.writeEndDocument()
  }

  override def getEncoderClass: Class[NumberAndStringField] = classOf[NumberAndStringField]


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
