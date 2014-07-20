package monia.utils

import monia.game.monster.Monster
import scala.util.parsing.json.JSONObject
import scala.util.parsing.json.JSONType
import scala.util.parsing.json.JSONObject
import play.api.libs.json.JsValue
import play.api.libs.json.Json
import monia.game.monster.TypeMonster
import monia.game.monster.TypeMonster
import play.api.libs.json.JsObject
import scala.util.parsing.json.JSONObject
import play.api.libs.json.JsObject
import monia.game.monster.TypeExperience

object JsonUtils {
  def loadJsonMonsterData(strJson: String): Map[Int, Monster] = {

    val json = Json.parse(strJson)
    val arrayJson = (json \ "monsters").as[Array[JsObject]]

    val tuples = arrayJson map { x => ((x \ "id").as[Int], convertToMonster(x)) }
    val map = tuples.toMap

    map
  }

  def loadJsonTypeData(strJson: String): Map[String, TypeMonster] = {
    val json = Json.parse(strJson)

    val mapMap = (json \ "types").as[Map[String, Map[String, Double]]]
    val mapBean = mapMap map { case (k, v) => (k, new TypeMonster(k, v)) }
    mapBean
  }

  def convertToMonster(json: JsObject) = {
    val id = (json \ "id").as[Int]
    val name = (json \ "name").as[String]
    val race = (json \ "race").as[String]
    val attack = (json \ "attack").as[Int]
    val defense = (json \ "defense").as[Int]
    val life = (json \ "life").as[Int]
    val typeExperience = TypeExperience.get((json \ "typeExperience").as[String])
    val typeMonster = (json \ "typeMonster").as[List[String]]
    new Monster(id, name, race, attack, defense, life, typeExperience, typeMonster)
  }

}