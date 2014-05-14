package battle.csn.lucette.bot;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import battle.csn.lucette.LucetteTest;
import battle.csn.lucette.game.Moteur;
import battle.csn.lucette.game.board.AbstractBoard;
import battle.csn.lucette.game.board.Etat;
import battle.csn.lucette.game.structure.Move;


@RunWith( JUnit4.class )
public class BotTest extends LucetteTest
{

    @Test
    public void testChooseMove( )
    {
        Moteur<Integer> moteur = new Moteur<Integer>( ID_PARTIE );

        moteur.addEquipe( ID_EQUIPE );
        moteur.addEquipe( ID_EQUIPE_ADVERSE );
        int turn = Etat.WHITE;

        String gameStatus = moteur.getGameStatus( ID_EQUIPE );
        assertEquals( gameStatus, AbstractBoard.OUI );
        Move botMove = moteur.getBotMove( turn == Etat.WHITE ? ID_EQUIPE : ID_EQUIPE_ADVERSE );
        assertFalse( botMove.getPositions( ).isEmpty( ) );
    }
}
