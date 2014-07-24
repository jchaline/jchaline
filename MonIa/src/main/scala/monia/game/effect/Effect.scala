package monia.game.effect

import monia.game.monster.Monster

trait Effect {
  def getName() = {
    this.getClass().getName().toLowerCase()
  }

  def process(target: Monster)

}

