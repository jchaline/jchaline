package monia.game.ia

import monia.game.monster.Monster

trait IA {
	def attack(owner:Monster,target:Monster)
}