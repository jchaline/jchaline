package battle.csn.lucette;

import java.util.ArrayList;

import junit.framework.TestCase;
import battle.csn.lucette.service.RestClientAPI;


public class ClientAPITest extends TestCase
{

    public void testPing( )
    {
        RestClientAPI RestClientAPI = new RestClientAPI( );
        String reponse = RestClientAPI.ping( );
        assertEquals( "pong", reponse );
    }

    public void testPingErreur500( )
    {
        RestClientAPI RestClientAPI = new RestClientAPI( );
        int reponse = RestClientAPI.pingError500( );
        assertEquals( 500, reponse );
    }

    public void testPingErreur403( )
    {
        RestClientAPI RestClientAPI = new RestClientAPI( );
        int reponse = RestClientAPI.pingError403( );
        assertEquals( 403, reponse );
    }

    public void testIdEquipe( )
    {
        RestClientAPI restClientAPI = new RestClientAPI( );
        String reponse = restClientAPI.getIdEquipe( "lucette", "sopra123" );
        //TODO A améliorer quand on aura plus d'info
        assertNotNull( reponse );
    }

    public void testIdPartie( )
    {
        RestClientAPI restClientAPI = new RestClientAPI( );
        String idEquipe = restClientAPI.getIdEquipe( "lucette", "sopra123" );
        String idPartie = restClientAPI.getGameId( idEquipe );
        //TODO A améliorer quand on aura plus d'info
        assertNotNull( idPartie );
    }

    public void testGameStatus( )
    {
        RestClientAPI restClientAPI = new RestClientAPI( );
        String idEquipe = restClientAPI.getIdEquipe( "lucette", "sopra123" );
        String idPartie = restClientAPI.getGameId( idEquipe );
        String statut = restClientAPI.getGameStatus( idEquipe, idPartie );
        //TODO A améliorer quand on aura plus d'info
        assertNotNull( statut );
    }

    public void testGetBoard( )
    {
        RestClientAPI restClientAPI = new RestClientAPI( );
        String idEquipe = restClientAPI.getIdEquipe( "lucette", "sopra123" );
        String idPartie = restClientAPI.getGameId( idEquipe );
        String board = restClientAPI.getBoard( idPartie );
        //TODO A améliorer quand on aura plus d'info
        assertNotNull( board );
    }

    public void testGetLastMove( )
    {
        RestClientAPI RestClientAPI = new RestClientAPI( );
        String idEquipe = RestClientAPI.getIdEquipe( "lucette", "sopra123" );
        String idPartie = RestClientAPI.getGameId( idEquipe );
        String lastMove = RestClientAPI.getLastMove( idPartie );
        //TODO A améliorer quand on aura plus d'info
        //bizarre durant les tests je récupere annule.
        assertNotNull( lastMove );
    }

    public void testPlay( )
    {
        RestClientAPI RestClientAPI = new RestClientAPI( );
        String idEquipe = RestClientAPI.getIdEquipe( "lucette", "sopra123" );
        String idPartie = RestClientAPI.getGameId( idEquipe );
        ArrayList<Integer> coords = new ArrayList<Integer>( );
        coords.add( 0 );
        coords.add( 0 );
        coords.add( 1 );
        coords.add( 1 );
        String play = RestClientAPI.play( idPartie, idEquipe, coords );
        //TODO A améliorer quand on aura plus d'info
        //bizarre durant les tests je récupere annule.
        assertNotNull( play );
    }

}
