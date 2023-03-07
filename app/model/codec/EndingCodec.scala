package model.codec

import model.AppException
import model.card.Ending
import org.bson.codecs.{Codec, DecoderContext, EncoderContext}
import org.bson.{BsonReader, BsonType, BsonWriter}

class EndingCodec extends Codec[Ending] {

  override def decode(reader: BsonReader, decoderContext: DecoderContext): Ending = {
    val ending: Ending = Ending("")

    reader.readStartDocument()

    while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
      val fieldName: String = reader.readName

      if (fieldName == "_id")
        reader.skipValue()
      else if (fieldName == "id")
        ending.setId(reader.readString())
      else if (fieldName == "label")
        ending.setLabel(reader.readString())
      else if (fieldName == "description")
        ending.setDescription(reader.readString())
      else if (fieldName == "comments")
        ending.setComments(reader.readString())
      else if (fieldName == "image")
        ending.setImage(reader.readString())
      else if (fieldName == "flavour")
        ending.setFlavour(reader.readString())
      else if (fieldName == "achievement")
        ending.setAchievement(reader.readString())
      else if (fieldName == "comment")
        ending.setComment(reader.readString())
      else if (fieldName == "anim")
        ending.setAnim(reader.readString())
      else throw AppException(s"Ending json decode error:Unknown field:$fieldName")
    }

    reader.readEndDocument()

    ending
  }

  override def encode(writer: BsonWriter, value: Ending, encoderContext: EncoderContext): Unit = {
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
    if (value.isImageDefined) {
      writer.writeName("image")
      writer.writeString(value.getImage)
    }
    if (value.isFlavourDefined) {
      writer.writeName("flavour")
      writer.writeString(value.getFlavour)
    }
    if (value.isAchievementDefined) {
      writer.writeName("achievement")
      writer.writeString(value.getAchievement)
    }
    if (value.isCommentDefined) {
      writer.writeName("comment")
      writer.writeString(value.getComment)
    }
    if (value.isAnimDefined) {
      writer.writeName("anim")
      writer.writeString(value.getAnim)
    }
    writer.writeEndDocument()
  }

  override def getEncoderClass: Class[Ending] = classOf[Ending]

  //  image
  //  comments
  //  flavour
  //  achievement
  //  description
  //  comment
  //  id
  //  label
  //  anim
}
