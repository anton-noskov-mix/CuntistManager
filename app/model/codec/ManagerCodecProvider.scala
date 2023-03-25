package model.codec

import model.AppException
import model.card.{Deck, Element, Ending, Legacy, Portal, Recipe, Verb}
import model.field.{Alt, ChanceField, Consequence, Expulsion, Linked, Mutation, NumberAndStringField, NumberField, Slot, StringField, XTriggers, XTriggersField}
import org.bson.codecs.Codec
import org.bson.codecs.configuration.{CodecProvider, CodecRegistry}

class ManagerCodecProvider extends CodecProvider {

  override def get[T](clazz: Class[T], registry: CodecRegistry): Codec[T] = {
    clazz match {
      //Cards
      case c if c == classOf[Ending] =>
        new EndingCodec().asInstanceOf[Codec[T]]
      case c if c == classOf[Deck] =>
        new DeckCodec(registry).asInstanceOf[Codec[T]]
      case c if c == classOf[Legacy] =>
        new LegacyCodec(registry).asInstanceOf[Codec[T]]
      case c if c == classOf[Portal] =>
        new PortalCodec(registry).asInstanceOf[Codec[T]]
      case c if c == classOf[Verb] =>
        new VerbCodec(registry).asInstanceOf[Codec[T]]
      case c if c == classOf[Element] =>
        new ElementCodec(registry).asInstanceOf[Codec[T]]
      case c if c == classOf[Recipe] =>
        new RecipeCodec(registry).asInstanceOf[Codec[T]]

      //Fields
      case c if c == classOf[StringField] =>
        new StringFieldCodec().asInstanceOf[Codec[T]]
      case c if c == classOf[NumberField] =>
        new NumberFieldCodec().asInstanceOf[Codec[T]]
      case c if c == classOf[Consequence] =>
        new ConsequenceCodec(registry).asInstanceOf[Codec[T]]
      case c if c == classOf[Slot] =>
        new SlotCodec(registry).asInstanceOf[Codec[T]]
      case c if c == classOf[ChanceField] =>
        new ChanceFieldCodec().asInstanceOf[Codec[T]]
      case c if c == classOf[Mutation] =>
        new MutationCodec().asInstanceOf[Codec[T]]
      case c if c == classOf[Expulsion] =>
        new ExpulsionCodec(registry).asInstanceOf[Codec[T]]
      case c if c == classOf[Alt] =>
        new AltCodec(registry).asInstanceOf[Codec[T]]
      case c if c == classOf[Linked] =>
        new LinkedCodec(registry).asInstanceOf[Codec[T]]
      case c if c == classOf[NumberAndStringField] =>
        new NumberAndStringFieldCodec().asInstanceOf[Codec[T]]
      //XTriggers
      case c if c == classOf[XTriggers] =>
        new XTriggersCodec(registry).asInstanceOf[Codec[T]]
      case c if c == classOf[XTriggersField] =>
        new XTriggersFieldCodec().asInstanceOf[Codec[T]]
      case _ =>
        throw AppException(s"Codec for class ${clazz.getName} not found!")
    }
  }
}
