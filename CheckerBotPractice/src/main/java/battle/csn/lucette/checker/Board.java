package battle.csn.lucette.checker;

import java.util.ArrayList;
import java.util.List;


/**
 * Get the state of the board.
 * Send a move, the board reevevaluate its state.
 * @author michael
 * 
 */
public class Board
{

    /**
     * Empty position
     */
    public static final int EMPTY = 0;

    /**
     * Identify a black pawn.
     */
    public static final int BLACK = -1;

    /**
     * Identify a white pawn.
     */
    public static final int WHITE = 1;

    /**
     * Size of the board
     */
    public final static int SIZE = 10;

    /**
     * The game board
     */
    public int[][] cases = new int[SIZE][SIZE];

    /**
     * Who's the turn white or black. White start.
     * turn is also used as a vector if white play they move by increasing a row
     * : row + turn as turn is +1,
     * if black play they move by decreasing a row thus also row + turn as turn
     * is -1 in this case.
     */
    private int turn = WHITE;

    /**
     * Initialize a game
     */
    public void reset( )
    {
        //set the black
        for ( int row = 0; row < 4; row++ )
        {
            for ( int column = 0; column < SIZE; column++ )
            {
                if ( whiteCase( row, column ) )
                    cases[row][column] = WHITE;
            }
        }

        //set the white
        for ( int row = SIZE - 1; row > SIZE - 5; row-- )
        {
            for ( int column = 0; column < SIZE; column++ )
            {
                if ( whiteCase( row, column ) )
                    cases[row][column] = BLACK;
            }
        }

    }

    /*
     * Is it a white case ?
     */
    public boolean whiteCase( int x, int y )
    {
        return ( x + y ) % 2 == 0;
    }

    @Override
    public String toString( )
    {
        String gameBoard = "";
        for ( int i = 0; i < SIZE; i++ )
        {
            for ( int j = 0; j < SIZE; j++ )
            {
                switch ( cases[i][j] )
                {
                case 0:
                    gameBoard += " ,";
                    break;
                case 1:
                    gameBoard += "x,";
                    break;
                case -1:
                    gameBoard += "o,";
                    break;
                }
            }
            gameBoard += "\n";
        }
        return gameBoard;
    }

    /**
     * move a position from a position to another.
     * Raise a checkerException if the move is forbbiden.
     */
    public void move( int x1, int y1, int x2, int y2 )
    {
        if ( !getPossibleMoves( ).contains( new Move( x1, y1, x2, y2 ) ) )
        {
            throw new RuntimeException( "Impossible move" );
        }
        cases[x1][y1] = EMPTY; //empty the initial position
        cases[x2][y2] = getTurn(); //fill the new one.
        //if we take an adverse pawn
        if ( ( Math.abs( x2 - x1 ) == 2 ) )
        {
            int adverseX = ( x2 + x1 ) / 2;
            int adverseY = ( y2 + y1 ) / 2;
            cases[adverseX][adverseY] = 0;
        }
        //switch the turn 
        setTurn( getTurn() * ( -1 ) );
    }

    public void move( Move move )
    {
        move( move.x1, move.y1, move.x2, move.y2 );
    }

    /**
     * Get the cases of the board. Any algorithm can then evaluate the
     * situation.
     * @return
     */
    public int[][] getCases( )
    {
        return cases;
    }

    /**
     * get the list of all possible move on the board.
     * This situation depend of the turn color, it's thus
     * all the possible move of the turn color.
     * @return
     */
    public List<Move> getPossibleMoves( )
    {
        //possible moves
        List<Move> moves = new ArrayList<Move>( );

        for ( int row = 0; row < SIZE; row++ )
        {
            for ( int column = 0; column < SIZE; column++ )
            {
                //only the turn color can make a move 
                if ( cases[row][column] == getTurn() )
                {
                    //check what's around the pawn.
                    //1) check onward left
                    if ( inBoudary( row + getTurn(), column - 1 ) )
                    {
                        if ( cases[row + getTurn()][column - 1] == EMPTY )
                        {
                            //that's a possible move
                            moves.add( new Move( row, column, row + getTurn(), column - 1 ) );
                        }
                        else //if the color is different we may take the pawn
                        if ( cases[row + getTurn()][column - 1] != getTurn() )
                        {
                            if ( inBoudary( row + ( 2 * getTurn() ), column - 2 ) )
                            {
                                if ( cases[row + ( 2 * getTurn() )][column - 2] == EMPTY )
                                {
                                    moves.add( new Move( row, column, row + ( 2 * getTurn() ), column - 2 ) );
                                }
                            }
                        }
                    }

                    //2) check onward right
                    if ( inBoudary( row + getTurn(), column + 1 ) )
                    {
                        if ( cases[row + getTurn()][column + 1] == EMPTY )
                        {
                            //that's a possible move
                            moves.add( new Move( row, column, row + getTurn(), column + 1 ) );
                        }
                        else //if the color is different we may take the pawn
                        if ( cases[row + getTurn()][column + 1] != getTurn() )
                        {
                            if ( inBoudary( row + ( 2 * getTurn() ), column + 2 ) )
                            {
                                if ( cases[row + ( 2 * getTurn() )][column + 2] == EMPTY )
                                {
                                    moves.add( new Move( row, column, row + ( 2 * getTurn() ), column + 2 ) );
                                }
                            }
                        }
                    }

                    //3) check backward left only if we take the pawn
                    if ( inBoudary( row - getTurn(), column - 1 ) )
                    {
                        if ( cases[row - getTurn()][column - 1] != getTurn() && cases[row - getTurn()][column - 1] != EMPTY )
                        {
                            if ( inBoudary( row - ( 2 * getTurn() ), column - 2 ) )
                            {
                                if ( cases[row - ( 2 * getTurn() )][column - 2] == EMPTY )
                                {
                                    moves.add( new Move( row, column, row - ( 2 * getTurn() ), column - 2 ) );
                                }
                            }
                        }
                    }

                    //4) check backward right only if we take the pawn 
                    if ( inBoudary( row - getTurn(), column + 1 ) )
                    {
                        if ( cases[row - getTurn()][column + 1] != getTurn() && cases[row - getTurn()][column + 1] != EMPTY )
                        {
                            if ( inBoudary( row - ( 2 * getTurn() ), column + 2 ) )
                            {
                                if ( cases[row - ( 2 * getTurn() )][column + 2] == EMPTY )
                                {
                                    moves.add( new Move( row, column, row - ( 2 * getTurn() ), column + 2 ) );
                                }
                            }
                        }
                    }
                }
            }
        }
        return moves;
    }

    /**
     * Tel if the coordonate is inside the board.
     * @param x
     * @param y
     * @return
     */
    private boolean inBoudary( int x, int y )
    {
        return x < SIZE && x >= 0 && y < SIZE && y >= 0;
    }

    /**
     * Give the value of the board if it's a positive value white as an
     * advantage
     * if it's negative black has the advantage.
     * @return
     */
    public int evaluateBoard( )
    {
        int sum = 0;
        for ( int row = 0; row < SIZE; row++ )
        {
            for ( int column = 0; column < SIZE; column++ )
            {
                sum += cases[row][column];
            }
        }
        return sum;
    }

    @Override
    protected Board clone( )
    {
        Board b = new Board( );
        for ( int row = 0; row < SIZE; row++ )
        {
            for ( int column = 0; column < SIZE; column++ )
            {
                b.cases[row][column] = cases[row][column];
            }
        }
        b.setTurn( turn );
        return b;
    }

    /**
     * @return the turn
     */
    public int getTurn( )
    {
        return turn;
    }

    /**
     * @param turn the turn to set
     */
    public void setTurn( int turn )
    {
        this.turn = turn;
    }

}
