package monia.game.effect

trait Effect {
  def getName()={
    this.getClass().getName().toLowerCase()
  }

}

