package battle.csn.lucette.game.bot;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import org.apache.log4j.Logger;

import battle.csn.lucette.game.board.DameBoard;
import battle.csn.lucette.game.board.IBoard;
import battle.csn.lucette.game.logic.AlphaBeta;
import battle.csn.lucette.game.logic.AlphaBetaThread;
import battle.csn.lucette.game.structure.Move;
import battle.csn.lucette.java8.MethodMagic;


public class DameBotNegaMaxThread extends AbstractBot<Integer>
{
    private static final String METHOD_EVALUATE = "evaluateBoard";
    private static final Logger LOGGER = Logger.getLogger( DameBotNegaMaxThread.class );

    private Integer _maxDeep = 2;

    @Override
    public Move chooseMove( String idEquipe )
    {
        Move move = null;

        AlphaBetaThread thread = new AlphaBetaThread( );

        //Nous récupérons le nombre de processeurs disponibles
        int processeurs = Runtime.getRuntime( ).availableProcessors( );
        
        //Nous créons notre pool de thread pour nos tâches de fond
        ForkJoinPool pool = new ForkJoinPool( processeurs );
        Long start = System.currentTimeMillis( );

        //Nous lançons le traitement de notre tâche principale via le pool
        pool.invoke( thread );

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
            m = DameBotNegaMaxThread.class.getMethod( METHOD_EVALUATE, IBoard.class );
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
