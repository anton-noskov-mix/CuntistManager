package model

import com.fasterxml.jackson.core.JsonParseException
import play.api.libs.json.{JsValue, Json}

import java.io.{File, FileInputStream}
import scala.collection.mutable.ListBuffer

class UtilPkg {

  def getJsonFiles(dirName: String): List[File] = {
    val jsonList = ListBuffer[File]()
    val baseDir = new File(dirName)
    if (baseDir.exists() && baseDir.isDirectory) {
      getListOfFiles(baseDir).foreach(file => {
        if (file.isDirectory)
          jsonList ++= getJsonFiles(file.getCanonicalPath)
        else {
          if (file.isFile && getExtention(file.getCanonicalPath).toUpperCase() == "JSON") {
            jsonList += file
          }
        }
      })
    } else
      throw AppException("Не найдена базовая директория игры!")
    jsonList.toList
  }

  def getListOfFiles(dir: File): List[File] = {
    if (dir.exists && dir.isDirectory) {
      dir.listFiles.toList
    } else {
      List[File]()
    }
  }

  def getExtention(fileName: String): String = {
    fileName.split("\\.").last
  }

  def getJsonFromFile(file: File): JsValue = {
    val stream = new FileInputStream(file)
    try {
      Json.parse(stream)
    }
    catch {
      case e:JsonParseException =>
        Json.parse("{}")
    }
    finally {
      stream.close()
    }
  }

}

object UtilPkg {
  def apply(): UtilPkg = new UtilPkg()
}
