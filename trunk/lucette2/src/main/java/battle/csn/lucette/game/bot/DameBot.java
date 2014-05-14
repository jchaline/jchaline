package battle.csn.lucette.game.bot;

import java.util.ArrayList;
import java.util.List;

import battle.csn.lucette.game.board.DameBoard;
import battle.csn.lucette.game.board.Etat;
import battle.csn.lucette.game.board.IBoard;
import battle.csn.lucette.game.structure.Move;


public class DameBot extends AbstractBot<Integer>
{

    private int _deepSearch;

    /*
     * find the best move to the board.
     * But in a very simplistic way. It try combination of board in
     * a recursive way to a certain level (futureLevel parameter) and evaluate
     * the
     * best move depending of the best evolution.
     * We must make sure that myColor is equal to the turn color of the board.
     * 
     * For each possibles move we evaluate a list of possible future. We need to
     * choose the list that is the less advantageous for the opponent.
     */
    @Override
    public Move chooseMove( String idEquipe )
    {
        int myColor;
        myColor = getPlateau( ).getIdEquipes( ).get( 0 ).equals( idEquipe ) ? Etat.WHITE : Etat.BLACK;

        Move bestMove = null;
        double bestEval = Integer.MIN_VALUE;
        for ( Move m : getPlateau( ).getMoveAvailables( ) )
        {
            IBoard<Integer> clone = this.getPlateau( ).deepCopy( );
            clone.play( m );
            List<IBoard<Integer>> futureBoards = new ArrayList<IBoard<Integer>>( );
            evaluateFutureBoard( clone, futureBoards, _deepSearch );
            double eval = eval( futureBoards, myColor );
            if ( eval > bestEval )
            {
                bestMove = m;
                bestEval = eval;
            }
        }
        return bestMove;
    }

    /**
     * return an average evaluation on non 0 board.
     * That's a very simplistic heuristic but it's ok for a 1 to 2 move in
     * advance
     * 
     * @param futureBoards
     * @param myColor
     * @return
     */
    private static double eval( List<IBoard<Integer>> futureBoards, int myColor )
    {
        double sum = 0;
        int nbNonZeroBoard = 0;
        for ( IBoard<Integer> board : futureBoards )
        {
            int boardValue = DameBot.evaluateBoard( board );
            if ( boardValue != 0 )
            {
                nbNonZeroBoard++;
                sum += boardValue;
            }
        }
        if ( nbNonZeroBoard == 0 )
        {
            return 0;
        }
        return ( sum / nbNonZeroBoard ) * myColor;
    }

    /**
     * Search all the possibles board from a given one to a limited future
     * level.
     * All the future board will be added to the possibleBoards list in order
     * to evaluate the best choose.
     * @param initialBoard
     * @param possibleBoards
     * @param futureLevel
     */
    public static void evaluateFutureBoard( IBoard<Integer> initialBoard, List<IBoard<Integer>> possibleBoards, int futureLevel )
    {
        List<Move> moves = initialBoard.getMoveAvailables( );
        if ( moves.size( ) == 0 )
        {
            //job is finish the game is not evolving.
            possibleBoards.add( initialBoard );
        }
        else
        {
            for ( Move m : moves )
            {
                IBoard<Integer> futureBoard = initialBoard.deepCopy( );
                futureBoard.play( m );
                if ( futureLevel > 0 )
                {
                    evaluateFutureBoard( futureBoard, possibleBoards, futureLevel - 1 );
                }
                else
                {
                    possibleBoards.add( futureBoard );
                }
            }
        }
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
}
