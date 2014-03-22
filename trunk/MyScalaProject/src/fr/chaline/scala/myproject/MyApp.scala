package fr.chaline.scala.myproject

import org.apache.commons.lang.{ StringUtils => ApacheStringUtils }
import org.apache.commons.lang.{ StringUtils => ApacheStringUtils }

class Person(val firstName: String, val lastName: String = "Valjean") {}

object MyApp {
  def main(args: Array[String]) {
    println("Hello Scala !")
    val strTest = "notEmpty"
    val strTestEmpty = ""
    println("test not empty : " + ApacheStringUtils.isNotBlank(strTest))
    println("test empty : " + ApacheStringUtils.isNotBlank(strTestEmpty))

    val jean1 = new Person("jean")
    val jean2 = new Person("bob", "morane")
    println(jean1.firstName + " " + jean1.lastName)
    println(jean2.firstName + " " + jean2.lastName)

    val score = 42
    val max = 100
    val eval = if (score > max) true else false
    print("result : " + eval)

    val b = for (i <- 1 to 30) yield i + "bottle"
    println("b : " + b)
    val c = for (i <- 1 to 30 if i % 2 == 0) yield i + "bottle"
    println("c : " + c)

    println(function1(function2()))
  }
  def function1(f: =>String): String = {
    println("inside function1()")
    "function1" + f
  }

  def function2(): String = {
    println("inside function2()")
    "function2"
  }
}