package battle.csn.lucette2.network

trait WsClient {
  /**
   * Indique si c’est au tour du joueur concerné dans la partie concernée
   * Retourne « OUI », « NON », « GAGNE », « PERDU » ou « ANNULE »
   *
   * @param idEquipe
   * @param idPartie
   * @return
   */
  def getGameStatus(idPartie: String, idEquipe: String): String

  /**
   * Retourne le plateau de jeu de la partie concernée La chaine de caractère
   * retournée sera précisée lors de la battle "NA" si le plateau n'est pas
   * disponible
   *
   * @param idPartie
   * @return
   */
  def getBoard(idPartie: String): String

  /**
   * Créer une partie contre l'IA au level specifié
   *
   * @param idEquipe identifiant de l'équipe
   * @param level niveau de difficulté
   * @return une erreur le cas echéant, null si tout s'est bien passé
   */
  def getNewPracticeGame(idEquipe: String, level: Int): String

  /**
   * Demande au jeu quel est l'identifiant de la partie a laquelle l'équipe
   * doit participer
   *
   * @param idEquipe identifiant de l'équipe
   * @param botGame null ou false pour jouer contre des joueurs, vrai pour
   *            jouer
   *            contre les bots
   * @return l'identifiant de la partie
   */
  def getGameId(idEquipe: String): String

  /**
   * Methode de test de l'API pour vérifier son bon fonctionnement.
   *
   * @return devrait retourner pong.
   */
  def ping(): String

  /**
   * Retourne le dernier coup joué sur ce plateau "coordX,coordY" (détail
   * pendant la battle)
   *
   * @param idPartie
   * @return
   */
  def getLastMove(idPartie: String): String

  /**
   * Joue un coup dans la partie concernée, pour l’équipe concernée aux
   * coordonnées X et Y données Retourne "OK" si le coup est accepté "KO" si
   * le coup est refusé/interdit "GAGNE" si c'est le dernier coup gagnant
   * "PTT" (Pas Ton Tour) si ce n'est pas au tour du joueur
   *
   * @param idPartie
   * @param idEquipe
   * @param coordX
   * @param coordY
   * @return
   */
  def play(idPartie: String, idEquipe: String, coords: Seq[Int]): String

  /**
   * Permet d'obtenir l'id de l'équipe.
   *
   * @param nomEquipe
   * @param motDePasse
   * @return
   */
  def getIdEquipe(nomEquipe: String, motDePasse: String):String;
}