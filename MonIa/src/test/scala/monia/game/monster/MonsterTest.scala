package monia.game.monster

import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner
import org.junit.Test

@RunWith(classOf[MockitoJUnitRunner])
class MonsterTest {

  @Test
  def attackTest() {
    val gala = Monster.default
    val jerry = Monster.default
    
    gala.attack(jerry)

  }

}