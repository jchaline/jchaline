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
import play.api.libs.json.Json

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
}