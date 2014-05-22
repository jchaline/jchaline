package battle.csn.lucette2.game.bot

import battle.csn.lucette2.game.board.Board

class DameBot extends Bot[Int] {

  def chooseMove(player: String, board: Board[Int]) = {
    var moves = board.moveAvailables(player)

    if (moves.size == 0) {
      None
    } else {
      Some(moves(0))
    }
  }
}