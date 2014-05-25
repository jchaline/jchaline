package battle.csn.lucette2.game.logic

import battle.csn.lucette2.game.board.Board

trait Logic {
  
  def solve(player:Int, plateau : Board[Int], alpha:Integer, beta:Integer, heuristique:(Int, Board[Int]) => Int, findMax:Boolean, deep: Integer):Int
  
}