package battle.csn.lucette.checker;

import java.util.ArrayList;
import java.util.List;


/**
 * Stupid IA, given a board and a color to play try
 * to evaluate the best move.
 * 
 * @author michael
 * 
 */
public class StupidIA
{

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
    public static Move findBestMove( int myColor, Board board, int futureLevel )
    {
        Move bestMove = null;
        double bestEval = Integer.MIN_VALUE;
        for ( Move m : board.getPossibleMoves( ) )
        {
            Board clone = board.clone( );
            clone.move( m.x1, m.y1, m.x2, m.y2 );
            List<Board> futureBoards = new ArrayList<Board>( );
            evaluateFutureBoard( clone, futureBoards, futureLevel );
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
    private static double eval( List<Board> futureBoards, int myColor )
    {
        double sum = 0;
        int nbNonZeroBoard = 0;
        for ( Board board : futureBoards )
        {
            int boardValue = board.evaluateBoard( );
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
    public static void evaluateFutureBoard( Board initialBoard, List<Board> possibleBoards, int futureLevel )
    {
        List<Move> moves = initialBoard.getPossibleMoves( );
        if ( moves.size( ) == 0 )
        {
            //job is finish the game is not evolving.
            possibleBoards.add( initialBoard );
        }
        else
        {
            for ( Move m : moves )
            {
                Board futureBoard = initialBoard.clone( );
                futureBoard.move( m.x1, m.y1, m.x2, m.y2 );
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

}
