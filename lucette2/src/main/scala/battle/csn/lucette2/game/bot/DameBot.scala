package battle.csn.lucette2.game.bot

import battle.csn.lucette2.game.board.Board
import battle.csn.lucette2.game.structure.Move

class DameBot extends Bot[Int] {
  
  val ALPHA = Int.MaxValue
  val BETA = Int.MinValue
  val FIND_MAX = true
  val DEEP = 0

  def chooseMove(player: Int, board: Board[Int]) = {
    var moves = board.moveAvailables(player)
    
    var bestMove : Option[Move]=None
    
    logic match{
      case Some(l) => 
          var maxFind = BETA
          
          for( move <- moves){
              var copy = board.deepCopy()
              copy.play(player, move)
              var evaluation = l.solve(board, evaluateBoard, DEEP, BETA, ALPHA, player)
              
              if (evaluation>maxFind) {
                maxFind = evaluation
                bestMove=Some(move)
              }
          }
        
      case None => 
    }
    
    bestMove
  }
  
    def evaluateBoard( player:Int, board:Board[Int]  )=
    {
        var sum = 0
        for ( row <- 0 to board.getWidth()-1 )
        {
            for ( column <- 0 to board.getHeight()-1 )
            {
                var value = board.readCase( Seq(row, column) )
                value match{
                  case Some(v)=> sum+=v
                  case None => 
                } 
            }
        }
        //first player collect the +1, second collect the -1,
        //both try to find the best score
        sum*player
    }
}