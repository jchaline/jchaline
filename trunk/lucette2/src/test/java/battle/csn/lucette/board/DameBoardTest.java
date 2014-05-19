package battle.csn.lucette.board;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import battle.csn.lucette.game.board.DameBoard;
import battle.csn.lucette.game.structure.Move;


@RunWith( JUnit4.class )
public class DameBoardTest
{
    DameBoard board;

    @Before
    public void initBoard( )
    {
        board = new DameBoard( );
    }

    @Test
    public void compareWithScala( )
    {
        List<Move> moves = board.getMoveAvailables( );
        System.out.println( board );
        System.out.println( moves.size( ) );
        for ( Move m : moves )
        {
            System.out.println( m );
        }
    }

    @Test
    public void getBoardTest( )
    {
        String strBoard1 = board.getBoard( );
        assertTrue( strBoard1.charAt( 23 ) == 'x' );
        board.play( new Move( 1, 1, 2, 0 ) );
        String strBoard2 = board.getBoard( );
        assertTrue( strBoard2.charAt( 23 ) == ' ' );
    }

    @Test
    public void updateBoardTest( )
    {
        for ( Move move : board.getMoveAvailables( ) )
        {
            DameBoard b1 = new DameBoard( );
            DameBoard b2 = new DameBoard( );
            b1.play( move );
            String strBoard1 = b1.getBoard( );
            b2.updateBoard( strBoard1 );
            String strBoard2 = b2.getBoard( );
            assertTrue( strBoard1.equals( strBoard2 ) );
        }
    }
}
