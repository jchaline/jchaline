package monia.game.monster

/**
 * Feu, Eau, Plante, ...
 */
class TypeMonster(name: String, ratio: Map[String, Double]) {
  override def toString = name + ":" + ratio
  
  def getRatio=ratio
}