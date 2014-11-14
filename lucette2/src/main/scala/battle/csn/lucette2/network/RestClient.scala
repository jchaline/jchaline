package battle.csn.lucette2.network

class RestClient extends WsClient {

  var BASE_URL = "http://localhost:8080/test"
  //    private static String BASE_URL = "http://ec2-54-200-12-98.us-west-2.compute.amazonaws.com/csnbattlearena/webservices/test";

  val URI_SEPARATOR = "/";
  val PING_URI = "/ping"
  val PING_ERROR_500 = "/pingError500"
  val PING_ERROR_403 = "/pingError403"
  val GET_ID_EQUIPE_URI = "/player/getIdEquipe/"
  val STATUS_URI = "/game/status/"
  val BOARD_URI = "/game/board/"
  val LAST_MOVE_URI = "/game/getlastmove/"
  val PLAY_URI = "/game/play/"

  val CREATE_GAME_URI = "/practice/new/"
  val GET_ID_GAME_URI = "/versus/next/"

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
      val getIdEquipe = GET_ID_EQUIPE_URI + nomEquipe + URI_SEPARATOR + motDePasse
      client.callString(getIdEquipe);
    }

  @Override
  def getGameStatus(idPartie: String, idEquipe: String) =
    {
      val getStatusUri = STATUS_URI + idPartie + URI_SEPARATOR + idEquipe;
      client.callString(getStatusUri)
    }

  @Override
  def getBoard(idPartie: String) =
    {
      val getBoard = BOARD_URI + idPartie;
      client.callString(getBoard)
    }

  @Override
  def getNewPracticeGame(idEquipe: String, level: Int) =
    {
      val getNewPractiveGame = CREATE_GAME_URI + level + URI_SEPARATOR + idEquipe
      client.callString(getNewPractiveGame)
    }

  @Override
  def getGameId(idEquipe: String) =
    {
      client.callString(GET_ID_GAME_URI)
    }

  @Override
  def getLastMove(idPartie: String) =
    {
      val getLastMove = LAST_MOVE_URI + idPartie
      client.callString(getLastMove)
    }

  @Override
  def play(idPartie: String, idEquipe: String, coords: Seq[Int]) =
    {
      var playMove = PLAY_URI + idPartie + URI_SEPARATOR + idEquipe
      for (coord <- coords) {
        playMove += URI_SEPARATOR + String.valueOf(coord)
      }
      client.callString(playMove)
    }
}