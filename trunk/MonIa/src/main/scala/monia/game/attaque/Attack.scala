package monia.game.attaque

import monia.game.effect.Effect

class Attack(name:String, power:Int,points:Int,effect:Effect=null) {
	override def toString()={
	  name+":"+power+", "+points
	}
}