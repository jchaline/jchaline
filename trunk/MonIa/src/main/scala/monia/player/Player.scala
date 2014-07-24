package monia.player

import monia.game.monster.Monster

class Player(name: String, level: Int, experience: Int) {
  val monsters: Map[String, Monster] = Map()
}