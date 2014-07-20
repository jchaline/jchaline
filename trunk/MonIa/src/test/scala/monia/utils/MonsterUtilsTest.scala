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

@RunWith(classOf[MockitoJUnitRunner])
class MonsterUtilsTest {
  val NB_TYPES = 2

  var strJsonMonsters = """{"monsters":[{"id":"1","name":"Salameche"},{"id":"2","name":"Carapuce"}]}"""
  var strJsonTypes = """{"types":{"feu":{"eau":0.5,"feu":1},"eau":{"feu":2,"eau":1}}}"""

  @Test
  def loadJsonFileTest() {

    val res = MonsterUtils.loadJsonTypeData(strJsonTypes)
    assertTrue(res.size == NB_TYPES)
    res foreach { case (k, v) => assertTrue(v.size == NB_TYPES) }
  }

  @Test
  def loadJsonMonsterTest() {
    val json = Json.parse(strJsonMonsters)

    val mapMap = (json \ "monsters").as[Array[JsObject]]
    println(mapMap)
    println(mapMap.size)
    
    var i=0
    val map = mapMap map { _ => (""+(Math.random()), "de") }
    println(map)

  }
}