package battle.csn.lucette.board;

import battle.csn.lucette.game.board.DameBoard;


public class DamePlateauTest extends PlateauTest
{

    /** Nombre de mouvement displonible pour le tictactoe en début de partie */
    private static final int MOVE_BEGIN = 9;

    public void testInit( )
    {
        DameBoard p = new DameBoard( );
        assertNotNull( p );
    }

    @Override
    public int getNbMoveBegin( )
    {
        return MOVE_BEGIN;
    }

}
