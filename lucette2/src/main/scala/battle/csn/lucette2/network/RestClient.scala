package battle.csn.lucette2.network

class RestClient extends WsClient {

  var BASE_URL = "http://localhost:8080/test"
  //    private static String BASE_URL = "http://ec2-54-200-12-98.us-west-2.compute.amazonaws.com/csnbattlearena/webservices/test";

  var URI_SEPARATOR = "/";
  var PING_URI = "/ping"
  var PING_ERROR_500 = "/pingError500"
  var PING_ERROR_403 = "/pingError403"
  var GET_ID_EQUIPE_URI = "/player/getIdEquipe/"
  var STATUS_URI = "/game/status/"
  var BOARD_URI = "/game/board/"
  var LAST_MOVE_URI = "/game/getlastmove/"
  var PLAY_URI = "/game/play/"

  var CREATE_GAME_URI = "/practice/new/"
  var GET_ID_GAME_URI = "/versus/next/"

  var client: HttpLayerAPI = _

  {
    client = new HttpLayerAPI(BASE_URL)
  }

  @Override
  def ping() =
    {
      client.callString(PING_URI);
    }

  @Override
  def getIdEquipe(nomEquipe: String, motDePasse: String) =
    {
      var getIdEquipe = GET_ID_EQUIPE_URI + nomEquipe + URI_SEPARATOR + motDePasse
      client.callString(getIdEquipe);
    }

  @Override
  def getGameStatus(idPartie: String, idEquipe: String) =
    {
      var getStatusUri = STATUS_URI + idPartie + URI_SEPARATOR + idEquipe;
      client.callString(getStatusUri);
    }

  @Override
  def getBoard(idPartie: String) =
    {
      var getBoard = BOARD_URI + idPartie;
      client.callString(getBoard);
    }

  @Override
  def getNewPracticeGame(idEquipe: String, level: Int) =
    {
      var getNewPractiveGame = CREATE_GAME_URI + level + URI_SEPARATOR + idEquipe;
      client.callString(getNewPractiveGame);
    }

  @Override
  def getGameId(idEquipe: String) =
    {
      client.callString(GET_ID_GAME_URI);
    }

  @Override
  def getLastMove(idPartie: String) =
    {
      var getLastMove = LAST_MOVE_URI + idPartie;
      client.callString(getLastMove);
    }

  @Override
  def play(idPartie: String, idEquipe: String, coords: Seq[Int]) =
    {
      var playMove = PLAY_URI + idPartie + URI_SEPARATOR + idEquipe;
      for (coord <- coords) {
        playMove += URI_SEPARATOR + String.valueOf(coord);
      }
      client.callString(playMove);
    }
}