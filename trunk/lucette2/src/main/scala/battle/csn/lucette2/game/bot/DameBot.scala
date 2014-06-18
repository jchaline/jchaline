package battle.csn.lucette2.game.bot

import battle.csn.lucette2.game.board.Board

class DameBot extends Bot[Int] {
  
  val ALPHA = Int.MaxValue
  val BETA = Int.MinValue
  val FIND_MAX = true
  val DEEP = 3

  def chooseMove(player: Int, board: Board[Int]) = {
    var moves = board.moveAvailables(player)
    
    logic match{
      case Some(l) => l.solve( player, board, ALPHA*player, BETA*player, evaluateBoard, player==1, DEEP )
      case None => 
    }
    
    if (moves.size == 0) {
      None
    } else {
      Some(moves(0))
    }
  }
  
    def evaluateBoard( player:Int, board:Board[Int]  )=
    {
        var sum = 0
        for ( row <- 0 to board.getWidth() )
        {
            for ( column <- 0 to board.getHeight() )
            {
                var value = board.readCase( Seq(row, column) )
                value match{
                  case Some(v)=> sum+=v
                  case None => 
                } 
            }
        }
        sum
    }
}