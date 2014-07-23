package monia.game.effect.service

import monia.game.effect.Poison
import monia.game.effect.Sleep
import monia.game.effect.DefaultEffect

object EffectFactory {
  def getOne(name: String, probability: Int) = {
    name match {
      case "poison" => new Poison(probability)
      case "sleep" => new Sleep(probability)
      case _ => new DefaultEffect
    }
  }
}