package battle.csn.lucette2.game.bot

import battle.csn.lucette2.game.board.Board
import battle.csn.lucette2.game.structure.Move
import battle.csn.lucette2.game.logic.Logic

trait Bot[T] {
  var logic: Option[Logic] = _
  /**
   * Un bot doit être capable de choisir l'action a effectuer sur un plateau pour une équipe donné
   * @param board le plateau sur lequel jouer
   * @param player l'equipe
   * @return le coup
   */
  def chooseMove(player: Int, board: Board[T]): Option[Move]
}