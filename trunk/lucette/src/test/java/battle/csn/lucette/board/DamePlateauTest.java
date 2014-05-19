package battle.csn.lucette.board;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import battle.csn.lucette.game.board.DameBoard;
import battle.csn.lucette.game.structure.Move;


@RunWith( JUnit4.class )
public class DamePlateauTest extends PlateauTest
{

    /** Nombre de mouvement displonible pour le tictactoe en début de partie */
    private static final int MOVE_BEGIN = 9;

    public void testInit( )
    {
        DameBoard p = new DameBoard( );
        assertNotNull( p );
    }

    @Test
    public void moveAvailables( )
    {
        DameBoard p = new DameBoard( );
        List<Move> moveAvailables = p.getMoveAvailables( );
        assertTrue( moveAvailables.size( ) > 0 );
        Move move = moveAvailables.get( 0 );
        p.play( move );
        moveAvailables = p.getMoveAvailables( );
        assertTrue( moveAvailables.size( ) > 0 );
    }

    @Override
    public int getNbMoveBegin( )
    {
        return MOVE_BEGIN;
    }

}
