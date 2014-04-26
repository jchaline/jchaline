package battle.csn.lucette.service;

import java.util.List;

import battle.csn.lucette.ClientAPITest;
import battle.csn.lucette.util.HttpLayerAPI;


/**
 * Classe d'appel aux API rest.
 * 
 * @see ClientAPITest pour des exemples d'utilisation.
 * 
 * @author michael
 * 
 */
public class RestClientAPI implements IClient
{
    private static final String URI_SEPARATOR = "/";

    private static String BASE_URL = "http://localhost:8080/test";
    //    private static String BASE_URL = "http://ec2-54-200-12-98.us-west-2.compute.amazonaws.com/csnbattlearena/webservices/test";

    private static String PING_URI = "/ping";
    private static String PING_ERROR_500 = "/pingError500";
    private static String PING_ERROR_403 = "/pingError403";
    private static String GET_ID_EQUIPE_URI = "/player/getIdEquipe/";
    private static String STATUS_URI = "/game/status/";
    private static String BOARD_URI = "/game/board/";
    private static String LAST_MOVE_URI = "/game/getlastmove/";
    private static String PLAY_URI = "/game/play/";

    private static String CREATE_GAME_URI = "/practice/new/";
    private static String GET_ID_GAME_URI = "/versus/next/";

    private final HttpLayerAPI client;

    public RestClientAPI( )
    {
        this.client = new HttpLayerAPI( BASE_URL );
    }

    @Override
    public String ping( )
    {
        return client.callString( PING_URI );
    }

    @Override
    public int pingError500( )
    {
        return client.getStatus( PING_ERROR_500 );
    }

    @Override
    public int pingError403( )
    {
        return client.getStatus( PING_ERROR_403 );
    }

    @Override
    public String getIdEquipe( String nomEquipe, String motDePasse )
    {
        String getIdEquipe = GET_ID_EQUIPE_URI + nomEquipe + URI_SEPARATOR + motDePasse;
        return client.callString( getIdEquipe );
    }

    @Override
    public String getGameStatus( String idPartie, String idEquipe )
    {
        String getStatusUri = STATUS_URI + idPartie + URI_SEPARATOR + idEquipe;
        return client.callString( getStatusUri );
    }

    @Override
    public String getBoard( String idPartie )
    {
        String getBoard = BOARD_URI + idPartie;
        return client.callString( getBoard );
    }

    @Override
    public String getNewPracticeGame( String idEquipe, Integer level )
    {
        String getNewPractiveGame = CREATE_GAME_URI + level + URI_SEPARATOR + idEquipe;
        return client.callString( getNewPractiveGame );
    }

    @Override
    public String getGameId( String idEquipe )
    {
        return client.callString( GET_ID_GAME_URI );
    }

    @Override
    public String getLastMove( String idPartie )
    {
        String getLastMove = LAST_MOVE_URI + idPartie;
        return client.callString( getLastMove );
    }

    @Override
    public String play( String idPartie, String idEquipe, List<Integer> coords )
    {
        String playMove = PLAY_URI + idPartie + URI_SEPARATOR + idEquipe;
        for ( Integer coord : coords )
        {
            playMove += URI_SEPARATOR + String.valueOf( coord );
        }
        return client.callString( playMove );
    }

}
