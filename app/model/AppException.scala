package model

class AppException(message:String) extends Exception(message){

}

object AppException {
  def apply(message:String): AppException = new AppException(message)
}
