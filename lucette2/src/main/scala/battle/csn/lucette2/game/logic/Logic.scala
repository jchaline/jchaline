package battle.csn.lucette2.game.logic

import battle.csn.lucette2.game.board.Board

trait Logic {
  
  def solve(node:Board[Int], heuristic:(Int,Board[Int]) => Int, depth:Int, α:Int, β:Int, color:Int):Int
  
}