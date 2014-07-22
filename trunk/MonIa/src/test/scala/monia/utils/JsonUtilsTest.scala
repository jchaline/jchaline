package monia.utils

import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner
import org.junit.Test
import scala.util.parsing.json.JSON
import scala.util.parsing.json.JSONObject
import scala.util.parsing.json.JSONFormat
import scala.util.parsing.json.JSONObject
import monia.game.monster.Monster
import scala.collection.mutable.MutableList
import org.junit.Assert._
import monia.game.monster.TypeExperience
import play.api.libs.json.Json
import play.api.libs.json.JsObject
import play.api.libs.json.JsValue
import play.api.libs.json.JsUndefined
import monia.game.attaque.Attack

@RunWith(classOf[MockitoJUnitRunner])
class JsonUtilsTest {
  val NB_TYPES = 2
  val NB_MONSTERS = 2

  var strJsonMonsters = """{"monsters":[{"id":1,"name":"Salameche","race":"Salameche","attack":5,"defense":3,"life":10,"typeExperience":"starter","typeMonster":["feu"]},{"id":2,"name":"Carapuce","race":"Carapuce","attack":3,"defense":5,"life":10,"typeExperience":"starter","typeMonster":["eau"]}]}"""
  var strJsonTypes = """{"types":{"feu":{"eau":0.5,"feu":1},"eau":{"feu":2,"eau":1}}}"""
  var strJsonAttacks = """{"attacks":[{"name":"griffe","power":35,"points":25},{"name":"pic-pic","power":20,"points":20,"effect":{"name":"poison","probability":50}}]}"""

  @Test
  def loadJsonAttacksTest() {
    val json = Json.parse(strJsonAttacks)
    val jsonO = (json \ JsonConsts.MARK_ATTACKS).as[Array[JsObject]]
    val attacks = jsonO.map(convertToAttack(_))
    attacks.foreach(println(_))
  }

  def convertToAttack(json: JsObject) = {
    val name = (json \ "name").as[String]
    val power = (json \ "power").as[Int]
    val points = (json \ "points").as[Int]
    new Attack(name, power, points)
  }

  @Test
  def loadJsonTypesTest() {

    val res = JsonUtils.loadJsonTypeData(strJsonTypes)
    assertTrue(res.size == NB_TYPES)
    res foreach { case (k, v) => assertTrue(v.size == NB_TYPES) }
  }

  @Test
  def loadJsonMonsterTest() {
    val json = Json.parse(strJsonMonsters)
    val monsters = JsonUtils.loadJsonMonsterData(strJsonMonsters)
    assertTrue(monsters.size == NB_MONSTERS)
  }
}