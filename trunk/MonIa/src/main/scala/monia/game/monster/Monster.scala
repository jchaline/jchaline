package monia.game.monster

import scala.beans.BeanProperty

class Monster(id:Int, name:String, race:String, attack:Int,defense:Int, life:Int, typeExp:TypeExperience.Value, typeMonster:List[String]) {
}

object Monster {
  def default = {
    var mob = new Monster(0,"default","default",1,1,1,TypeExperience.Normal,List("normal"))
    mob
  }
}