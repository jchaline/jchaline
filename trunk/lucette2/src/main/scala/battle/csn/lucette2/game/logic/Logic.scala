package battle.csn.lucette2.game.logic

import battle.csn.lucette.game.board.IBoard

trait Logic {
  
  def solve(plateau : IBoard[Int], alpha:Integer, beta:Integer, heuristique:(IBoard[Int]) => Int, findMax:Boolean, deep: Integer):Int
  
}