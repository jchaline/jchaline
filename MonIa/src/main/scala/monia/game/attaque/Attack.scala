package monia.game.attaque

import monia.game.effect.Effect
import monia.game.monster.Monster

class Attack(name: String, typeName: String, power: Int, points: Int, effect: Effect = null) {
  override def toString() = {
    name + "(" + typeName + "):" + power + ", " + points
  }
  
  def process(target:Monster)={
    
  }
}


object AttackResult extends Enumeration{
    type AttackResult = Value
    val Success, Fail, Critial, Ineffective = Value
}