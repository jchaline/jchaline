package monia.utils

import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner
import org.junit.Test
import org.apache.commons.lang3.StringUtils
import org.junit.Assert._
import scala.io.Source
import play.api.libs.json.Json

@RunWith(classOf[MockitoJUnitRunner])
class FileUtilsTest {

  val PATH_MONSTER_FILE = "/monsters.json"
  val PATH_TYPES_FILE = "/types.json"

  @Test
  def loadJsonFileTest() {
    val fileMonsterContent = FileUtils.readAll(PATH_MONSTER_FILE)
    assertTrue(StringUtils.isNotBlank(fileMonsterContent))
    
    val fileTypesContent = FileUtils.readAll(PATH_TYPES_FILE)
    assertTrue(StringUtils.isNotBlank(fileTypesContent))
  }
}