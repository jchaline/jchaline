package battle.csn.lucette.game.bot;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.log4j.Logger;

import battle.csn.lucette.game.board.DameBoard;
import battle.csn.lucette.game.board.IBoard;
import battle.csn.lucette.game.logic.AlphaBeta;
import battle.csn.lucette.game.structure.Move;
import battle.csn.lucette.java8.MethodMagic;


public class DameBotNegaMax extends AbstractBot<Integer>
{
    private static final String METHOD_EVALUATE = "evaluateBoard";
    private static final Logger LOGGER = Logger.getLogger( DameBotNegaMax.class );

    private Integer _maxDeep = 2;

    @Override
    public Move chooseMove( String idEquipe )
    {
        Move move = null;
        Integer alpha = null;
        Integer beta = null;
        Boolean findMax = null;
        Integer bestValueFind = null;
        if ( getPlateau( ).getIdEquipes( ).get( 0 ).equals( idEquipe ) )
        {
            alpha = Integer.MIN_VALUE;
            beta = Integer.MAX_VALUE;
            findMax = true;
            bestValueFind = Integer.MIN_VALUE;
        }
        else
        {
            alpha = Integer.MAX_VALUE;
            beta = Integer.MIN_VALUE;
            findMax = false;
            bestValueFind = Integer.MAX_VALUE;
        }

        List<Move> moves = getPlateau( ).getMoveAvailables( );

        for ( Move tryMove : moves )
        {
            IBoard<Integer> deepCopy = getPlateau( ).deepCopy( );
            deepCopy.play( tryMove );
            MethodMagic heuristique = new MethodMagic( getEvaluateMethod( ), this, deepCopy );
            try
            {
                int actualValue = AlphaBeta.alphaBeta( deepCopy, alpha, beta, heuristique, findMax, _maxDeep );
                if ( ( findMax && actualValue > bestValueFind ) || ( !findMax && actualValue < bestValueFind ) )
                {
                    bestValueFind = actualValue;
                    move = tryMove;
                }
            }
            catch ( Exception e )
            {
                LOGGER.error( "Erreur lors de l'utilisation d'alpha beta par le bot Dame " );
                LOGGER.error( "Plateau : " + deepCopy );
                LOGGER.error( "Mouvement : " + tryMove );
                LOGGER.error( "Erreur rencontrée : ", e );
            }
        }

        return move;
    }

    /**
     * Give the value of the board if it's a positive value white as an
     * advantage
     * if it's negative black has the advantage.
     * @return
     */
    public static int evaluateBoard( IBoard<Integer> plateau )
    {
        DameBoard plateauCast = (DameBoard) plateau;
        int sum = 0;
        for ( int row = 0; row < plateauCast.getWidth( ); row++ )
        {
            for ( int column = 0; column < plateauCast.getHeight( ); column++ )
            {
                sum += plateauCast.readCase( row, column );
            }
        }
        return sum;
    }

    private static Method getEvaluateMethod( )
    {
        Method m = null;
        try
        {
            m = DameBotNegaMax.class.getMethod( METHOD_EVALUATE, IBoard.class );
        }
        catch ( NoSuchMethodException e )
        {
            LOGGER.error( e );
        }
        catch ( SecurityException e )
        {
            LOGGER.error( e );
        }
        return m;
    }

    public Integer getMaxDeep( )
    {
        return _maxDeep;
    }

    public void setMaxDeep( Integer _maxDeep )
    {
        this._maxDeep = _maxDeep;
    }
}
