package model.codec

import model.AppException
import model.field.{Consequence, NumberField}
import org.bson.codecs.configuration.CodecRegistry
import org.bson.codecs.{Codec, DecoderContext, EncoderContext}
import org.bson.{BsonReader, BsonType, BsonWriter}

class ConsequenceCodec(val registry: CodecRegistry) extends Codec[Consequence] {

  val numberFieldCodec = registry.get(classOf[NumberField])

  override def decode(reader: BsonReader, decoderContext: DecoderContext): Consequence = {
    val consequence: Consequence = Consequence()

    reader.readStartDocument()

    while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
      val fieldName: String = reader.readName

      if (fieldName == "_id")
        reader.skipValue()
      else if (fieldName == "id")
        consequence.id = reader.readString()
      else if (fieldName == "topath")
        consequence.topath = Option(reader.readString())
      else if (fieldName == "deckeffects")
        consequence.deckeffects = Option(numberFieldCodec.decode(reader, decoderContext))
      else throw AppException(s"Ending json decode error:Unknown field:$fieldName")
    }

    reader.readEndDocument()

    consequence
  }

  override def encode(writer: BsonWriter, value: Consequence, encoderContext: EncoderContext): Unit = {
    writer.writeStartDocument()
    writer.writeName("id")
    writer.writeString(value.id)
    if (value.topath.isDefined) {
      writer.writeName("topath")
      writer.writeString(value.topath.get)
    }
    if (value.deckeffects.isDefined) {
      writer.writeName("deckeffects")
      numberFieldCodec.encode(writer, value.deckeffects.get, encoderContext)
    }
    writer.writeEndDocument()
  }

  override def getEncoderClass: Class[Consequence] = classOf[Consequence]
}