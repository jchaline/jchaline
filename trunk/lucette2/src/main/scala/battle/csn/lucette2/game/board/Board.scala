package battle.csn.lucette2.game.board

import battle.csn.lucette2.game.structure.Move
import battle.csn.lucette2.game.structure.ICases
import battle.csn.lucette2.game.structure.MultiTab

/**
 * Interface d'un plateau standard
 */
trait Board[T] {
  val cases: ICases[T] = new MultiTab[T]()

  /**
   * Joue un coup si possible sur le plateau courant pour l'equipe donné
   * @param move coup joué, pas de position car abstraction du type de coup
   * @param move coup joué, pas de position car abstraction du type de coup
   */
  def play(player: Int, move: Move)

  /**
   * Retourne le plateau courant sous forme textuel pour être parsé
   * @return le plateau sous forme texte
   */
  def toString(): String

  /**
   * Copie compléte d'un plateau
   * @return plateau copié
   */
  def deepCopy(): Board[T]

  /**
   * Permet d'obtenir l'ensemble des coups jouables à un instant t.
   * @param player couleur du joueur pour lequel on cherche les coups
   * @return la liste des déplacements disponible pour le joueur donné
   */
  def moveAvailables(player: Int): Seq[Move]

  /**
   * Créer les cases nécessaires pour obtenir un plateau de la taille XxYxZ...
   * Attention, cette methode reinitilise complétement le plateau
   * @param sizes tailles
   */
  protected def updateSize(sizes: Seq[Int]) = this.cases.updateSizes(sizes)

  /**
   * Lire une case du plateau en fonction des coordonnées (x,y)
   * @param x abscisse de la case
   * @param y ordonnée de la case
   * @return la case
   */
  def readCase(coords: Seq[Int]) = this.cases.get(coords)

  /**
   * set case value
   * @param value the value to set on the board
   * @param coords the positions
   */
  def writeCase(value: T, coords: Int*) = this.cases.set(value, coords)

  /**
   * Met à jour le plateau via une chaine de caractère
   * @param strBoard le plateau sous forme de string
   */
  def updateBoard(strBoard: String)

  /**
   * Reset le plateau avec la disposition de départ classic
   */
  def reset()
  
  /**
   * Get the game status for any team
   * @param playerName the team to get the status
   */
  def gameStatus(player: Int):String
}
object Board{
  val OUI = "OUI"
  val NON = "NON"
  val GAGNE = "GAGNE"
  val PERDU = "PERDU"
  val ANNULE = "ANNULE"
  val ERROR = "ERROR"
}