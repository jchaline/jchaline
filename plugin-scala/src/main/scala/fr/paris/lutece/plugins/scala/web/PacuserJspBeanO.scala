package fr.paris.lutece.plugins.scala.web

object PacuserJspBeanO {
  def concat(value: String)(append: String): String = value + append

  def encaps1(arround: String)(l: List[String]): List[String] = l.map(arround + _ + arround)

  def encaps2(arround: String, l: List[String]): List[String] = {
    l.map(arround + _ + arround)
  }

  def printCalc(value: Int, f: (Int) => Int): Int = {
    println("Calculting...")
    f(value)
  }
}