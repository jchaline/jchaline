package monia.utils

import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner
import org.junit.Test
import scala.util.parsing.json.JSON
import scala.util.parsing.json.JSONObject
import jdk.nashorn.internal.parser.JSONParser
import scala.util.parsing.json.JSONFormat
import scala.util.parsing.json.JSONObject
import monia.game.monster.Monster
import scala.collection.mutable.MutableList
import org.junit.Assert._
import monia.game.monster.TypeExperience
import monia.game.monster.TypeMonster

@RunWith(classOf[MockitoJUnitRunner])
class MonsterUtilsTest {
  var jsonMonsters = "{\"monsters\":[{\"id\":\"1\",\"name\":\"Salameche\"},{\"id\":\"2\",\"name\":\"Carapuce\"}]}";
  var jsonTypes = "{\"types\":{\"feu\":{\"eau\":0.5,\"feu\":1},\"eau\":{\"feu\":2,\"eau\":1}}}";
  var json = "{\"monsters\":{}}";

  @Test
  def loadJsonFileTest() {

    import argonaut._, Argonaut._

    case class Mob(id: Int, name: String, race: String, attack: Int, defense: Int, life: Int, speed: Int, typeExp: TypeExperience.Value, typeMonster: Seq[TypeMonster])
    case class Person(name: String, age: Int, things: List[String])
    implicit def PersonCodecJson = jdecode3L(Person.apply)("name", "age", "things")
    implicit def MobCodecJson = jdecode9L(Mob.apply)("id", "name", "race", "attack", "defense", "life", "speed", "typeExp", "typeMonster")

    val person = Person("Bam Bam", 2, List("club"))
    println(person)

    val json: Json = person.asJson
    println(json)

    val prettyprinted: String = json.spaces2
    println(prettyprinted)

    val parsed: Option[Person] = prettyprinted.decodeOption[Person]
    println(parsed)

    //    val json: JsValue = Json.parse(jsonString)
    //    val someJson = JSON.parseRaw(jsonMonsters)
    //
    //    var list: List[Monster] = List()
    //
    //    someJson match {
    //      // Matches if jsonStr is valid JSON and represents a Map of Strings to Any
    //      case Some(a) => list :::= MonsterUtils.loadJsonData(a)
    //      case None => println("Parsing failed")
    //      case other => println("Unknown data structure: " + other)
    //    }

    //    assertTrue(list.size == 2)

  }
}