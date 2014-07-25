package monia.game.map

/**
 * case d'une map
 * peut etre un terrain simple, ou un terrain contenant des mobs, ou un terrain infranchissable
 * ou encore un type spécial (eau, lave ?) traversable sous certaines conditions
 */
class Square {
  var monsters: List[MonsterLocation] = List()

}