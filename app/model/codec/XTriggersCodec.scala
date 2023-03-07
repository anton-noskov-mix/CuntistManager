package model.codec

import model.AppException
import model.field.{XTriggers, XTriggersField}
import org.bson.codecs.configuration.CodecRegistry
import org.bson.codecs.{Codec, DecoderContext, EncoderContext}
import org.bson.{BsonReader, BsonType, BsonWriter}

import scala.collection.mutable.ListBuffer

class XTriggersCodec(val registry: CodecRegistry) extends Codec[XTriggers] {

  val xTriggersFieldCodec = registry.get(classOf[XTriggersField])

  override def decode(reader: BsonReader, decoderContext: DecoderContext): XTriggers = {
    val transformObj = collection.mutable.Map[String, String]()
    val xTriggerObj = collection.mutable.Map[String, List[XTriggersField]]()

    reader.readStartDocument()

    while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
      val fieldName: String = reader.readName


      if (fieldName == "_id")
        reader.skipValue()
      else {
        if (reader.getCurrentBsonType == BsonType.STRING) {
          val fieldValue = reader.readString()
          transformObj += (fieldName -> fieldValue)
        } else if (reader.getCurrentBsonType == BsonType.ARRAY) {
          val listXTriggersField = ListBuffer[XTriggersField]()

          reader.readStartArray()
          while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            listXTriggersField += xTriggersFieldCodec.decode(reader, decoderContext)
          }
          reader.readEndArray()

          xTriggerObj += (fieldName -> listXTriggersField.toList)
        }
        else throw AppException(s"Ending json decode error:Unknown field:$fieldName")

      }
    }

    reader.readEndDocument()

    XTriggers(transformObj.toMap, xTriggerObj.toMap)
  }

  override def encode(writer: BsonWriter, value: XTriggers, encoderContext: EncoderContext): Unit = {
    writer.writeStartDocument()
    if (value.isTransformObjDefined) {
      value.transformObj.get.foreach({
        case (fieldName, fieldValue) =>
          writer.writeName(fieldName)
          writer.writeString(fieldValue)
      })
    }
    if (value.isXTriggerObjDefined) {
      value.xTriggerObj.get.foreach({
        case (fieldName, fieldValue) =>
          writer.writeName(fieldName)
          writer.writeStartArray()
          fieldValue.foreach(xField => xTriggersFieldCodec.encode(writer, xField, encoderContext))
          writer.writeEndArray()
      })
    }
    writer.writeEndDocument()
  }

  override def getEncoderClass: Class[XTriggers] = classOf[XTriggers]

}

