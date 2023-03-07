package model.card

import org.mongodb.scala.bson.annotations.BsonProperty
import play.api.libs.functional.syntax.{toFunctionalBuilderOps, unlift}
import play.api.libs.json.{JsPath, Reads, Writes}

class Ending(pId:String) extends Entity(pId) {

  override val collectionName: String = "endings"

  /**
   * The image used for the card on the ending screen.
   */
  private var image: Option[String] = None

  def setImage(pImage:String): Unit ={
    image = Option(pImage)
  }

  def getImage:String ={
    image.getOrElse("")
  }

  def isImageDefined:Boolean = image.isDefined

  /**
   * Appears to accept "Grand", "Melancholy", and "Vile".
   */
  private var flavour: Option[String] = None

  def setFlavour(pFlavour:String): Unit ={
    flavour = Option(pFlavour)
  }

  def getFlavour:String ={
    flavour.getOrElse("")
  }

  def isFlavourDefined:Boolean = flavour.isDefined

  /**
   * Potentially determines what animation is played when the victory is triggered.
   * Accepts "DramaticLight", "DramaticLightCool", and "DramaticLightEvil".
   */
  private var anim: Option[String] = None

  def setAnim(pAnim:String): Unit ={
    anim = Option(pAnim)
  }

  def getAnim:String ={
    anim.getOrElse("")
  }

  def isAnimDefined:Boolean = anim.isDefined

  /**
   * Possibly determines what achievements are gained by getting this victory.
   * Must be present for a victory to trigger successfully. Appears to accept "XXX" or "" for endings
   * that don't trigger achievements.
   */
  private var achievement: Option[String] = None

  def setAchievement(pAchievement:String): Unit ={
    achievement = Option(pAchievement)
  }

  def getAchievement:String ={
    achievement.getOrElse("")
  }

  def isAchievementDefined:Boolean = achievement.isDefined

  /**
   * Comments
   */
  private var comment: Option[String] = None

  def setComment(pComment:String): Unit ={
    comment = Option(pComment)
  }

  def getComment:String ={
    comment.getOrElse("")
  }

  def isCommentDefined:Boolean = comment.isDefined


  def canEqual(a: Any):Boolean = a.isInstanceOf[Ending]

  override def equals(that: Any): Boolean =
    that match {
      case that: Ending => {
        that.canEqual(this) &&
          this.id == that.id &&
          this.label == that.label &&
          this.description == that.description &&
          this.comments == that.comments &&
          this.image == that.image &&
          this.flavour == that.flavour &&
          this.anim == that.anim &&
          this.achievement == that.achievement &&
          this.comment == that.comment
      }
      case _ => false
    }


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

object Ending {
  def apply(pId:String): Ending = new Ending(pId)

  def unapply(e: Ending): Option[
    (String,
      Option[String],
      Option[String],
      Option[String],
      Option[String],
      Option[String],
      Option[String],
      Option[String],
      Option[String]
      )] = {
    Option(
      e.id,
      e.label,
      e.description,
      e.comments,
      e.image,
      e.flavour,
      e.anim,
      e.achievement,
      e.comment)
  }

  private def applyRead(
                         pId: String,
                         pLabel: Option[String],
                         pDescription: Option[String],
                         pComments: Option[String],
                         pImage: Option[String],
                         pFlavour: Option[String],
                         pAnim: Option[String],
                         pAchievement: Option[String],
                         pComment: Option[String]
                       ): Ending = {
    val e = new Ending(pId)
    e.label = pLabel
    e.description = pDescription
    e.comments = pComments
    e.image = pImage
    e.flavour = pFlavour
    e.anim = pAnim
    e.achievement = pAchievement
    e.comment = pComment
    e
  }


  lazy implicit val endingsReader: Reads[Ending] = (
    (JsPath \ "id").read[String] and
      (JsPath \ "label").readNullable[String] and
      (JsPath \ "description").readNullable[String] and
      (JsPath \ "comments").readNullable[String] and
      (JsPath \ "image").readNullable[String] and
      (JsPath \ "flavour").readNullable[String] and
      (JsPath \ "anim").readNullable[String] and
      (JsPath \ "achievement").readNullable[String] and
      (JsPath \ "comment").readNullable[String]
    ) (Ending.applyRead _)

  lazy implicit val endingsWriter: Writes[Ending] = (
    (JsPath \ "id").write[String] and
      (JsPath \ "label").writeNullable[String] and
      (JsPath \ "description").writeNullable[String] and
      (JsPath \ "comments").writeNullable[String] and
      (JsPath \ "image").writeNullable[String] and
      (JsPath \ "flavour").writeNullable[String] and
      (JsPath \ "anim").writeNullable[String] and
      (JsPath \ "achievement").writeNullable[String] and
      (JsPath \ "comment").writeNullable[String]
    ) (unlift(Ending.unapply))

}
