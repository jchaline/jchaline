package monia.game.monster

import scala.beans.BeanProperty

/**
 * Feu, Eau, Plante, ...
 */
class TypeMonster(@BeanProperty name: String, @BeanProperty ratio: Map[String, Double]) {
  override def toString = name + ":" + ratio

  def getRatio(other: String) = {
    if (ratio.contains(other)) {
      ratio(other)
    } else {
      1
    }
  }

  def size = ratio.size
}