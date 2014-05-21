package battle.csn.lucette2.game.logic

import org.springframework.stereotype.Service
import battle.csn.lucette.game.board.IBoard
import battle.csn.lucette.game.structure.Move
import scala.collection.JavaConverters._

class NegaMax extends Logic {

  private var cpt = 0
  
    def getCpt():Int={cpt}
    
    def solve(plateau : IBoard[Int], alpha:Integer, beta:Integer, heuristique:(IBoard[Int]) => Int, findMax:Boolean, deep: Integer):Int={
        cpt+=1
        var value = 0;
        var moves = plateau.getMoveAvailables( )

        //si le plateau est une feuille
        if ( moves.size( ) == 0 || deep == 0 ) 
        {
            value = heuristique(plateau)
        }
        else
        {
            var bestScore = if (findMax) Integer.MIN_VALUE else Integer.MAX_VALUE
            var move : Move=null
            //Pour toutes les coups jouables du plateau
            for ( move <- moves.asScala )
            {
                var deepCopy = plateau.deepCopy( );
                deepCopy.play( move );
                var score = solve( deepCopy, -beta, -alpha, heuristique, !findMax, deep - 1 );
                if ( ( findMax && score > bestScore ) || ( !findMax && score < bestScore ) )
                {
                    bestScore = score;
                    if ( ( findMax && bestScore > alpha ) || ( !findMax && bestScore < alpha ) )
                    {
                        if ( ( findMax && bestScore >= beta ) || ( findMax && bestScore <= beta ) )
                        {
                            value = bestScore;
                            //break;
                        }
                    }
                }
            }
        }
        value
    }
}