package model.card


abstract class Entity(protected var id:String) {

  val collectionName:String

  def setId(pId:String): Unit ={
    this.id = pId
  }

  def getId: String ={
    this.id
  }

  /**
   * 	Name in-game.
   */
  protected var label:Option[String] = None

  def setLabel(pLabel:String): Unit ={
    label = Option(pLabel)
  }

  def getLabel: String ={
    label.getOrElse("")
  }

  def isLabelDefined:Boolean = label.isDefined


  /**
   * Description that is displayed when the element is clicked on.
   */
  protected var description:Option[String] = None

  def setDescription(pDescription:String): Unit ={
    description = Option(pDescription)
  }

  def getDescription: String ={
    description.getOrElse("")
  }

  def isDescriptionDefined:Boolean = description.isDefined


  /**
   * Comment
   */
  protected var comments:Option[String] = None

  def setComments(pComments:String): Unit ={
    comments = Option(pComments)
  }

  def getComments: String ={
    comments.getOrElse("")
  }

  def isCommentsDefined:Boolean = comments.isDefined
}
