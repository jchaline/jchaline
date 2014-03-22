package fr.chaline.scala.myproject

object MyFunctionalApp {
  def main(args: Array[String]) {
    printCalc(2, double)
    printCalc(2, carre)
  }

  def double = (value: Int) => value * 2
  def carre = (value: Int) => value ^ 2

  def printCalc(value: Int, f: (Int) => Int):Int = {
    println("Calculting...")
    f(value)
  }

}