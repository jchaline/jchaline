package monia.game.attaque

import monia.game.effect.Effect

class Attack(name: String, typeName: String, power: Int, points: Int, effect: Effect = null) {
  override def toString() = {
    name + "(" + typeName + "):" + power + ", " + points
  }
}