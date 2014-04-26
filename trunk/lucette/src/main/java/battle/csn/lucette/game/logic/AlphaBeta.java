package battle.csn.lucette.game.logic;

import java.util.List;

import org.apache.log4j.Logger;

import battle.csn.lucette.game.board.IBoard;
import battle.csn.lucette.game.structure.Move;
import battle.csn.lucette.java8.MethodMagic;


public class AlphaBeta implements ILogic
{
    private static final Logger logger = Logger.getLogger( AlphaBeta.class );

    /**
     * Determine le score de chaque plateau avec une implémentation NegaMax -
     * AlphaBeta.
     * NB : alpha est toujours inférieur à beta
     * @param plateau le plateau à évaluer
     * @param alpha valeur minimum
     * @param beta valeur maximum
     * @param heuristique methode permettant d'évaluer un plateau
     * @param findMax boolean indiquant s'il faut chercher le meilleur ou le
     *            pire des plateau (pour l'un ou d'autre des joueurs)
     * @param deep profondeur maximum pour rechercher une valeur
     * @return la valeur d'un plateau
     */
    public static int alphaBeta( IBoard<Integer> plateau, int alpha, int beta, MethodMagic heuristique,
            boolean findMax, int deep )
    {
        Integer value = 0;
        List<Move> moves = plateau.getMoveAvailables( );
        logger.debug( "alphaBeta mode " + findMax + " : " + moves.size( ) + " mouvements disponibles." );

        //si le plateau est une feuille
        if ( moves.size( ) == 0 || deep == 0 )
        {
            Object[] params = new Object[1];
            params[0] = plateau;
            heuristique.setParams( params );
            value = (Integer) heuristique.invoke( );
        }
        else
        {
            int bestScore = findMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            //Pour toutes les coups jouables du plateau
            for ( Move move : moves )
            {
                IBoard<Integer> deepCopy = plateau.deepCopy( );
                deepCopy.play( move );
                int score = alphaBeta( deepCopy, -beta, -alpha, heuristique, !findMax, deep - 1 );
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

        return value;
    }
}
