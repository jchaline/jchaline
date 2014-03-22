package fr.chaline.scala.myproject

object Currying {

  def concat(value: String)(append: String): String = value + append
  
  def encaps1(arround:String)(l:List[String]):List[String]=l.map(arround+_+arround)
  
  def encaps2(arround: String, l: List[String]): List[String] = {
    l.map(arround + _ + arround)
  }

  def printCalc(value: Int, f: (Int) => Int): Int = {
    println("Calculting...")
    f(value)
  }

  def main(args: Array[String]): Unit = {
    val l = List("Jean", "Michel", "Jar")
    println(l.map(concat("Canard")))
  }
}