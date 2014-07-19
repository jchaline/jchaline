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
  def loadJsonMonsterData(jsonMonsters: JsValue): List[Monster] = {
    var list: List[Monster] = List()
    list
  }

  def loadJsonTypeData(strJson: String): Map[String, TypeMonster] = {
    val json = Json.parse(strJson)

    val mapMap = (json \ "types").as[Map[String, Map[String, Double]]]
    val mapBean = mapMap map { case (k, v) => (k, new TypeMonster(k, v) )}
    mapBean
  }

}