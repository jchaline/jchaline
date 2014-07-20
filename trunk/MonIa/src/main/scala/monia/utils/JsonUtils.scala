package monia.utils

import monia.game.monster.Monster
import scala.util.parsing.json.JSONObject
import scala.util.parsing.json.JSONType
import scala.util.parsing.json.JSONObject
import play.api.libs.json.JsValue
import play.api.libs.json.Json
import monia.game.monster.TypeMonster
import monia.game.monster.TypeMonster

object MonsterUtils {
  def loadJsonMonsterData(strJson: String): Map[String, Monster] = {
    val map: Map[String, Monster] = Map()
    val json = Json.parse(strJson)

    val mapMap = (json \ "monsters")
    
    map
  }

  def loadJsonTypeData(strJson: String): Map[String, TypeMonster] = {
    val json = Json.parse(strJson)

    val mapMap = (json \ "types").as[Map[String, Map[String, Double]]]
    val mapBean = mapMap map { case (k, v) => (k, new TypeMonster(k, v)) }
    mapBean
  }

}