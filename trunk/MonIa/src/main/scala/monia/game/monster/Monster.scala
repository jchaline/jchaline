package monia.game.monster

import scala.beans.BeanProperty

class Monster {
  var id:Int=_
  var name: String = _
  var race: String = _
  var attack: Int = _
  var defense: Int = _
  var life: Int = _
  var speed: Int = _
  var typeExp = TypeExperience.Normal
  var typeMonster: List[TypeMonster] = List()
}

object Monster {
  def default = {
    var mob = new Monster
    mob.name = "default"
    mob.race = "default"
    mob
  }
}