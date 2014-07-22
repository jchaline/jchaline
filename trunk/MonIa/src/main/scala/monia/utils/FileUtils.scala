package monia.utils

import scala.io.Source

object FileUtils {

  def readAll(path: String) = {
    val is = Source.fromURL(getClass.getResource(path))
    is.mkString
  }
}