package battle.csn.lucette.game.board;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import battle.csn.lucette.game.structure.Move;


public class DameBoard extends AbstractBoard<Integer> implements IBoard<Integer>
{
    private static final String LINE_SEPARATOR = "\n";
    private static final String COLUMN_SEPARATOR = ",";
    private static final String BLACK = "o";
    private static final String WHITE = "x";
    private static final int DIM_X = 10, DIM_Y = 10;

    public int getWidth( )
    {
        return _cases.getSizes( )[0];
    }

    public int getHeight( )
    {
        return _cases.getSizes( )[1];
    }

    public DameBoard( )
    {
        super( DIM_X, DIM_Y );
        reset( DIM_X, DIM_Y );
    }

    public DameBoard( int x, int y )
    {
        super( x, y );
        reset( x, y );
    }

    /**
     * Who's the turn white or black. White start.
     * turn is also used as a vector if white play they move by increasing a row
     * : row + turn as turn is +1,
     * if black play they move by decreasing a row thus also row + turn as turn
     * is -1 in this case.
     */
    public int turn = Etat.WHITE;

    /**
     * Initialize a game
     */
    public void reset( int x, int y )
    {
        //init the empty 
        for ( int i = 0; i < DIM_X; i++ )
        {
            for ( int j = 0; j < DIM_Y; j++ )
            {
                _cases.set( Etat.EMPTY, i, j );
            }
        }

        //set the black
        for ( int row = 0; row < 2; row++ )
        {
            for ( int column = 0; column < x; column++ )
            {
                if ( whiteCase( row, column ) )
                {
                    _cases.set( Etat.WHITE, row, column );
                }
            }
        }

        //set the white
        for ( int row = x - 1; row > x - 3; row-- )
        {
            for ( int column = 0; column < x; column++ )
            {
                if ( whiteCase( row, column ) )
                {
                    _cases.set( Etat.BLACK, row, column );
                }
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
        for ( int i = 0; i < DIM_X; i++ )
        {
            for ( int j = 0; j < DIM_Y; j++ )
            {
                switch ( _cases.get( i, j ) )
                {
                case Etat.EMPTY:
                    gameBoard += " " + COLUMN_SEPARATOR;
                    break;
                case Etat.WHITE:
                    gameBoard += WHITE + COLUMN_SEPARATOR;
                    break;
                case Etat.BLACK:
                    gameBoard += BLACK + COLUMN_SEPARATOR;
                    break;
                }
            }
            gameBoard += LINE_SEPARATOR;
        }
        return gameBoard;
    }

    /**
     * move a position from a position to another.
     * Raise a checkerException if the move is forbbiden.
     */
    @Override
    public void play( Move move )
    {
        int x1 = move.getPositions( ).get( 0 ), y1 = move.getPositions( ).get( 1 );
        int x2 = move.getPositions( ).get( 2 ), y2 = move.getPositions( ).get( 3 );
        List<Move> moveAvailables = getMoveAvailables( );
        if ( !moveAvailables.contains( move ) )
        {
            throw new RuntimeException( "Impossible move" );
        }
        _cases.set( Etat.EMPTY, x1, y1 ); //empty the initial position
        _cases.set( turn, x2, y2 ); //fill the new one.
        //if we take an adverse pawn
        if ( ( Math.abs( x2 - x1 ) == 2 ) )
        {
            int adverseX = ( x2 + x1 ) / 2;
            int adverseY = ( y2 + y1 ) / 2;
            _cases.set( Etat.EMPTY, adverseX, adverseY );
        }
        //switch the turn 
        turn = turn * ( -1 );
    }

    /**
     * get the list of all possible move on the board.
     * This situation depend of the turn color, it's thus
     * all the possible move of the turn color.
     * @return
     */
    @Override
    public List<Move> getMoveAvailables( )
    {
        //possible moves
        List<Move> moves = new ArrayList<Move>( );

        for ( int row = 0; row < DIM_X; row++ )
        {
            for ( int column = 0; column < DIM_Y; column++ )
            {
                //only the turn color can make a move 
                if ( readCase( row, column ) == turn )
                {
                    //check what's around the pawn.
                    //1) check onward left
                    if ( inBoudary( row + turn, column - 1 ) )
                    {
                        if ( readCase( row + turn, column - 1 ) == Etat.EMPTY )
                        {
                            //that's a possible move
                            moves.add( new Move( row, column, row + turn, column - 1 ) );
                        }
                        else //if the color is different we may take the pawn
                        if ( readCase( row + turn, column - 1 ) != turn )
                        {
                            if ( inBoudary( row + ( 2 * turn ), column - 2 ) )
                            {
                                if ( readCase( row + ( 2 * turn ), column - 2 ) == Etat.EMPTY )
                                {
                                    moves.add( new Move( row, column, row + ( 2 * turn ), column - 2 ) );
                                }
                            }
                        }
                    }

                    //2) check onward right
                    if ( inBoudary( row + turn, column + 1 ) )
                    {
                        if ( readCase( row + turn, column + 1 ) == Etat.EMPTY )
                        {
                            //that's a possible move
                            moves.add( new Move( row, column, row + turn, column + 1 ) );
                        }
                        else //if the color is different we may take the pawn
                        if ( readCase( row + turn, column + 1 ) != turn )
                        {
                            if ( inBoudary( row + ( 2 * turn ), column + 2 ) )
                            {
                                if ( readCase( row + ( 2 * turn ), column + 2 ) == Etat.EMPTY )
                                {
                                    moves.add( new Move( row, column, row + ( 2 * turn ), column + 2 ) );
                                }
                            }
                        }
                    }

                    //3) check backward left only if we take the pawn
                    if ( inBoudary( row - turn, column - 1 ) )
                    {
                        if ( readCase( row - turn, column - 1 ) != turn
                                && readCase( row - turn, column - 1 ) != Etat.EMPTY )
                        {
                            if ( inBoudary( row - ( 2 * turn ), column - 2 ) )
                            {
                                if ( readCase( row - ( 2 * turn ), column - 2 ) == Etat.EMPTY )
                                {
                                    moves.add( new Move( row, column, row - ( 2 * turn ), column - 2 ) );
                                }
                            }
                        }
                    }

                    //4) check backward right only if we take the pawn 
                    if ( inBoudary( row - turn, column + 1 ) )
                    {
                        if ( readCase( row - turn, column + 1 ) != turn
                                && readCase( row - turn, column + 1 ) != Etat.EMPTY )
                        {
                            if ( inBoudary( row - ( 2 * turn ), column + 2 ) )
                            {
                                if ( readCase( row - ( 2 * turn ), column + 2 ) == Etat.EMPTY )
                                {
                                    moves.add( new Move( row, column, row - ( 2 * turn ), column + 2 ) );
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
        return x < DIM_X && x >= 0 && y < DIM_Y && y >= 0;
    }

    @Override
    protected boolean validateMove( Move move )
    {
        return false;
    }

    @Override
    public IBoard<Integer> deepCopy( )
    {
        DameBoard b = new DameBoard( DIM_X, DIM_Y );
        for ( int row = 0; row < DIM_X; row++ )
        {
            for ( int column = 0; column < DIM_Y; column++ )
            {
                b._cases.set( _cases.get( row, column ), row, column );
            }
        }
        b.turn = turn;
        return b;
    }

    @Override
    public String getGameStatus( String idEquipe )
    {
        String status = null;
        if ( getMoveAvailables( ).isEmpty( ) )
        {
            status = AbstractBoard.PERDU;
        }
        else
        {
            int etat = getIdEquipes( ).get( 0 ).equals( idEquipe ) ? Etat.WHITE : Etat.BLACK;
            status = etat == turn ? AbstractBoard.OUI : AbstractBoard.NON;
        }
        return status;
    }

    @Override
    public void writeCase( Integer value, int... dimensions )
    {
        _cases.set( value, dimensions );
    }

    @Override
    public void updateBoard( String strBoard )
    {
        String[] lines = strBoard.split( LINE_SEPARATOR );
        int i = 0;
        for ( String line : lines )
        {
            int j = 0;
            String[] columns = line.split( COLUMN_SEPARATOR );
            for ( String elem : columns )
            {
                elem = elem.trim( );
                if ( StringUtils.isNotBlank( elem ) )
                {
                    writeCase( elem.equals( WHITE ) ? Etat.WHITE : Etat.BLACK, i, j );
                }
                else
                {
                    writeCase( Etat.EMPTY, i, j );
                }
                j++;
            }
            i++;
        }
    }
}
