package battle.csn.lucette.game.bot;

import java.util.concurrent.ForkJoinPool;

import com.google.common.base.Function;

import battle.csn.lucette.game.board.DameBoard;
import battle.csn.lucette.game.board.IBoard;
import battle.csn.lucette.game.logic.AlphaBetaThread;
import battle.csn.lucette.game.structure.Move;


public class DameBotNegaMaxThread extends AbstractBot<Integer>
{
    private Integer _maxDeep = 2;

    @Override
    public Move chooseMove( String idEquipe )
    {
        Integer alpha = null;
        Integer beta = null;
        Boolean findMax = null;
        if ( getPlateau( ).getIdEquipes( ).get( 0 ).equals( idEquipe ) )
        {
            alpha = Integer.MIN_VALUE;
            beta = Integer.MAX_VALUE;
            findMax = true;
        }
        else
        {
            alpha = Integer.MAX_VALUE;
            beta = Integer.MIN_VALUE;
            findMax = false;
        }
        
        Function<IBoard<Integer>, Integer> evaluate = getEvaluation( );
        
        AlphaBetaThread thread = new AlphaBetaThread( getPlateau( ), alpha, beta, evaluate, findMax, _maxDeep );

        //Nous récupérons le nombre de processeurs disponibles
        int processeurs = Runtime.getRuntime( ).availableProcessors( );

        //Nous créons notre pool de thread pour nos tâches de fond
        ForkJoinPool pool = new ForkJoinPool( processeurs );

        //Nous lançons le traitement de notre tâche principale via le pool
        pool.invoke( thread );

        return thread.getMove( );
    }
    
    /**
     * Get the evaluation function
     * @return the evaluation function
     */
    private Function<IBoard<Integer>, Integer> getEvaluation( )
    {
        Function<IBoard<Integer>, Integer> evaluate = new Function<IBoard<Integer>, Integer>( )
        {
            @Override
            public Integer apply( IBoard<Integer> input )
            {
                return input == null ? 0 : evaluateBoard( input );
            }
        };
        return evaluate;
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

    public Integer getMaxDeep( )
    {
        return _maxDeep;
    }

    public void setMaxDeep( Integer _maxDeep )
    {
        this._maxDeep = _maxDeep;
    }
}
