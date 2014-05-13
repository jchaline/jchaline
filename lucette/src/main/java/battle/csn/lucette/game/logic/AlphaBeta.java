package battle.csn.lucette.game.logic;

import java.util.List;

import org.apache.log4j.Logger;

import battle.csn.lucette.game.board.IBoard;
import battle.csn.lucette.game.structure.Move;

import com.google.common.base.Function;


public class AlphaBeta implements ILogic
{
    private static final Logger logger = Logger.getLogger( AlphaBeta.class );
    private static int cpt=0;

    /**
     * Determine le score de chaque plateau avec une implémentation NegaMax -
     * AlphaBeta.
     * NB : alpha est toujours inférieur à beta
     * @param plateau le plateau à évaluer
     * @param alpha valeur minimum
     * @param beta valeur maximum
     * @param evaluate methode permettant d'évaluer un plateau
     * @param findMax boolean indiquant s'il faut chercher le meilleur ou le
     *            pire des plateau (pour l'un ou d'autre des joueurs)
     * @param deep profondeur maximum pour rechercher une valeur
     * @return la valeur d'un plateau
     */
    public int solve( IBoard<Integer> plateau, int alpha, int beta, Function<IBoard<Integer>, Integer> evaluate,
            boolean findMax, int deep )
    {
        cpt++;
        Integer value = 0;
        List<Move> moves = plateau.getMoveAvailables( );
        logger.debug( "alphaBeta mode " + findMax + " : " + moves.size( ) + " mouvements disponibles." );

        //si le plateau est une feuille
        if ( moves.size( ) == 0 || deep == 0 )
        {
            value = evaluate.apply( plateau );
        }
        else
        {
            int bestScore = findMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            //Pour toutes les coups jouables du plateau
            for ( Move move : moves )
            {
                IBoard<Integer> deepCopy = plateau.deepCopy( );
                deepCopy.play( move );
                int score = solve( deepCopy, -beta, -alpha, evaluate, !findMax, deep - 1 );
                if ( ( findMax && score > bestScore ) || ( !findMax && score < bestScore ) )
                {
                    bestScore = score;
                    if ( ( findMax && bestScore > alpha ) || ( !findMax && bestScore < alpha ) )
                    {
                        alpha = bestScore;
                        if ( ( findMax && alpha >= beta ) || ( findMax && alpha <= beta ) )
                        {
                            value = bestScore;
                            break;
                        }
                    }
                }
            }
        }
        
        logger.error( cpt );

        return value;
    }
}
