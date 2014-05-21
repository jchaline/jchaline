package battle.csn.lucette2.engine

import org.scalatest.junit.AssertionsForJUnit
import org.scalatest.mock.MockitoSugar
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner
import org.junit.Test

@RunWith(classOf[MockitoJUnitRunner])
class EngineTest  extends AssertionsForJUnit with MockitoSugar {
  
  @Test
  def initEngineTest(){
    var e = new Engine[Int]("game1");
  }
  

}