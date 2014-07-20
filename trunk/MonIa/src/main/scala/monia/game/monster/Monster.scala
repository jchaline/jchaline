package monia.game.monster

import scala.beans.BeanProperty
import monia.game.attaque.Attack
import monia.game.ia.DefaultIA
import monia.game.ia.IA

class Monster(@BeanProperty id: Int, @BeanProperty name: String, @BeanProperty race: String, @BeanProperty attack: Int, @BeanProperty defense: Int, @BeanProperty life: Int, @BeanProperty typeExp: TypeExperience.Value, @BeanProperty typeMonster: List[String]) {
  val attacks: Map[String, Attack] = Map()

  var ia: IA = new DefaultIA()
  
  /**
   * must be call each turn
   */
  def processEffect={
    
  }

  /**
   * use ia and try to hit the target
   */
  def attack(target: Monster) = {

  }
}

object Monster {
  
  /**
   * Create default monster, for test or avoid fatal error
   */
  def default = {
    var mob = new Monster(0, "default", "default", 1, 1, 1, TypeExperience.Normal, List("normal"))
    mob
  }
}