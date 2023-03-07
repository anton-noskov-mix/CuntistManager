package model.codec

import model.AppException
import model.field.Mutation
import org.bson.{BsonReader, BsonType, BsonWriter}
import org.bson.codecs.{Codec, DecoderContext, EncoderContext}

class MutationCodec extends Codec[Mutation] {

  override def decode(reader: BsonReader, decoderContext: DecoderContext): Mutation = {
    val mutation: Mutation = Mutation()

    reader.readStartDocument()

    while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
      val fieldName: String = reader.readName

      if (fieldName == "_id")
        reader.skipValue()
      else if (fieldName == "filterOnAspectId")
        mutation.setFilterOnAspectId(reader.readString())
      else if (fieldName == "mutateAspectId")
        mutation.setMutateAspectId(reader.readString())
      else if (fieldName == "mutationLevel")
        mutation.setMutationLevel(reader.readInt32())
      else if (fieldName == "additive")
        mutation.setAdditive(reader.readBoolean())
      else if (fieldName == "filter")
        mutation.setFilter(reader.readString())
      else if (fieldName == "mutate")
        mutation.setMutate(reader.readString())
      else if (fieldName == "level")
        mutation.setLevel(reader.readInt32())
      else if (fieldName == "additional")
        mutation.setAdditional(reader.readBoolean())
      else throw AppException(s"Ending json decode error:Unknown field:$fieldName")
    }

    reader.readEndDocument()

    mutation
  }

  override def encode(writer: BsonWriter, value: Mutation, encoderContext: EncoderContext): Unit = {
    writer.writeStartDocument()
    if (value.isFilterOnAspectIdDefined) {
      writer.writeName("filterOnAspectId")
      writer.writeString(value.getFilterOnAspectId)
    }
    if (value.isMutateAspectIdDefined) {
      writer.writeName("mutateAspectId")
      writer.writeString(value.getMutateAspectId)
    }
    if (value.isMutationLevelDefined) {
      writer.writeName("mutationLevel")
      writer.writeInt32(value.getMutationLevel)
    }
    if (value.isAdditiveDefined) {
      writer.writeName("additive")
      writer.writeBoolean(value.getAdditive)
    }
    if (value.isFilterDefined) {
      writer.writeName("filter")
      writer.writeString(value.getFilter)
    }
    if (value.isMutateDefined) {
      writer.writeName("mutate")
      writer.writeString(value.getMutate)
    }
    if (value.isLevelDefined) {
      writer.writeName("level")
      writer.writeInt32(value.getLevel)
    }
    if (value.isAdditionalDefined) {
      writer.writeName("additional")
      writer.writeBoolean(value.getAdditional)
    }
    writer.writeEndDocument()
  }

  override def getEncoderClass: Class[Mutation] = classOf[Mutation]

  //filterOnAspectId
  //mutateAspectId
  //mutationLevel
  //additive
  //filter
  //mutate
  //level
  //additional
}
