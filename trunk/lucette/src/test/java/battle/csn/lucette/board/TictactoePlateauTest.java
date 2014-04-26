package battle.csn.lucette.board;

import java.util.List;

import battle.csn.lucette.game.board.IBoard;
import battle.csn.lucette.game.board.TictactoeBoard;
import battle.csn.lucette.game.structure.Move;

/**
 * Test spécifique au plateau de Tic Tac Toe
 * @author Jeremy
 *
 */
public class TictactoePlateauTest extends PlateauTest
{
    
    /** Nombre de mouvement displonible pour le tictactoe en début de partie */
    private static final int MOVE_BEGIN = 9;

    /**
     * test partant du principe que c'est a l'équipe lucette de commencer
     */
    public void testPlay( )
    {
        IBoard<Integer> plateauTest = new TictactoeBoard();
        List<Move> moveAvailable = plateauTest.getMoveAvailables( );

        assertTrue( moveAvailable.size( ) == 9 );
    }



    @Override
    /**
     * Configurer avec Spring
     */
    public int getNbMoveBegin( )
    {
        return MOVE_BEGIN;
    }
}
