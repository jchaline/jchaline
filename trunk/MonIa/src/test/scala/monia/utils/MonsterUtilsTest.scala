package monia.utils

import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner
import org.junit.Test
import scala.util.parsing.json.JSON
import scala.util.parsing.json.JSONObject
import jdk.nashorn.internal.parser.JSONParser
import scala.util.parsing.json.JSONFormat

@RunWith(classOf[MockitoJUnitRunner])
class MonsterUtilsTest {
  var jsonMonsters = "{\"monsters\":{\"1\":{\"name\":\"Salameche\"},\"2\":{\"name\":\"Carapuce\"}}}";
  var jsonTypes = "{\"types\":{\"feu\":{\"eau\":0.5,\"feu\":1},\"eau\":{\"feu\":2,\"eau\":1}}}";

  @Test
  def loadJsonFileTest() {
    var result = JSON.parseRaw(jsonTypes)
    println(result)
    result = JSON.parseRaw(jsonMonsters)
    println(result)
    
    result match {
      // Matches if jsonStr is valid JSON and represents a Map of Strings to Any
      case Some(a) => println(a)
      case None => println("Parsing failed")
      case other => println("Unknown data structure: " + other)
    }

    //MonsterUtils.loadJsonData(json)
  }
}