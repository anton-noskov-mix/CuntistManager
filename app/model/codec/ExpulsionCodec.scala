package model.codec

import model.AppException
import model.field.{Expulsion, NumberField}
import org.bson.{BsonReader, BsonType, BsonWriter}
import org.bson.codecs.{Codec, DecoderContext, EncoderContext}
import org.bson.codecs.configuration.CodecRegistry


class ExpulsionCodec(val registry: CodecRegistry) extends Codec[Expulsion] {

  val numberFieldCodec: Codec[NumberField] = registry.get(classOf[NumberField])

  override def decode(reader: BsonReader, decoderContext: DecoderContext): Expulsion = {
    val expulsion: Expulsion = Expulsion()

    reader.readStartDocument()

    while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
      val fieldName: String = reader.readName

      if (fieldName == "_id")
        reader.skipValue()
      else if (fieldName == "limit")
        expulsion.setLimit(reader.readInt32())
      else if (fieldName == "filter")
        expulsion.setFilter(numberFieldCodec.decode(reader, decoderContext))
      else throw AppException(s"Ending json decode error:Unknown field:$fieldName")
    }

    reader.readEndDocument()

    expulsion
  }

  override def encode(writer: BsonWriter, value: Expulsion, encoderContext: EncoderContext): Unit = {
    writer.writeStartDocument()
    if (value.isLimitDefined) {
      writer.writeName("limit")
      writer.writeInt32(value.getLimit)
    }
    if (value.isFilterDefined) {
      writer.writeName("filter")
      numberFieldCodec.encode(writer, value.getFilter, encoderContext)
    }
    writer.writeEndDocument()
  }

  override def getEncoderClass: Class[Expulsion] = classOf[Expulsion]
}
