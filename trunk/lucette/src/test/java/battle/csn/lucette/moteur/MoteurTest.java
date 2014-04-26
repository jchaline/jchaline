package battle.csn.lucette.moteur;

import java.util.List;

import org.junit.Test;

import battle.csn.lucette.LucetteTest;
import battle.csn.lucette.game.Moteur;
import battle.csn.lucette.game.board.AbstractBoard;
import battle.csn.lucette.game.board.Etat;
import battle.csn.lucette.game.structure.Move;


public class MoteurTest extends LucetteTest
{
    @Test
    public void testGameStatus( )
    {
        Moteur<Integer> moteur = new Moteur<Integer>( ID_PARTIE );
        String gameStatus = moteur.getGameStatus( ID_EQUIPE );
        assertEquals( gameStatus, AbstractBoard.ANNULE );

        moteur.addEquipe( ID_EQUIPE );
        gameStatus = moteur.getGameStatus( ID_EQUIPE );
        assertEquals( gameStatus, AbstractBoard.ANNULE );

        moteur.addEquipe( ID_EQUIPE_ADVERSE );
        gameStatus = moteur.getGameStatus( ID_EQUIPE_ADVERSE );
        assertEquals( gameStatus, AbstractBoard.NON );
        gameStatus = moteur.getGameStatus( ID_EQUIPE );
        assertEquals( gameStatus, AbstractBoard.OUI );

        int turn = Etat.WHITE;
        Integer[] coords;
        while ( gameStatus.equals( AbstractBoard.OUI ) || gameStatus.equals( AbstractBoard.NON ) )
        {
            Move botMove = moteur.getBotMove( turn == Etat.WHITE ? ID_EQUIPE : ID_EQUIPE_ADVERSE );
            List<Integer> positions = botMove.getPositions( );
            coords = new Integer[positions.size( )];
            for ( int j = 0; j < positions.size( ); j++ )
            {
                coords[j] = positions.get( j );
            }

            moteur.jouerCoup( turn == Etat.WHITE ? ID_EQUIPE : ID_EQUIPE_ADVERSE, coords );

            //change la main
            turn *= -1;
            gameStatus = moteur.getGameStatus( turn == Etat.WHITE ? ID_EQUIPE : ID_EQUIPE_ADVERSE );
        }
    }
}
