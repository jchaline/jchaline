package battle.csn.lucette.board;

import java.util.List;

import battle.csn.lucette.LucetteTest;
import battle.csn.lucette.game.Moteur;
import battle.csn.lucette.game.structure.Move;


/**
 * Test des methodes supposé valide pour tout type de plateau
 * @author Jeremy
 * 
 */
public abstract class PlateauTest extends LucetteTest
{

    public abstract int getNbMoveBegin( );

    public void testMoveAvailable( )
    {
        Moteur moteur = initMoteur( );
        List<Move> moveAvailable = moteur.getPlateau( ).getMoveAvailables( );
        assertEquals( moveAvailable.size( ), getNbMoveBegin( ) );

        Move moveToTest = moveAvailable.get( 0 );
        moveToTest.setIdEquipe( ID_EQUIPE_ADVERSE );
    }

    private Moteur initMoteur( )
    {
        Moteur moteur = new Moteur( ID_PARTIE );
        moteur.addEquipe( ID_EQUIPE );
        moteur.addEquipe( ID_EQUIPE_ADVERSE );
        return moteur;
    }

}
