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

  val PATH_FILE = "/monsters.json"

  @Test
  def loadJsonFileTest() {
    val fileContent = FileUtils.readAll(PATH_FILE)
    assertTrue(StringUtils.isNotBlank(fileContent))
  }
}