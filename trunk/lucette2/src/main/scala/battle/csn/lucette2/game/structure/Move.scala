package battle.csn.lucette2.game.structure

import org.apache.commons.lang3.StringUtils

class Move(var positions: Seq[Int]) {
  var idEquipe: String = _;

  /**
   * @return the _idEquipe
   */
  def getIdEquipe() = this.idEquipe

  /**
   * @param _idEquipe the _idEquipe to set
   */
  def setIdEquipe(idEquipe: String) = this.idEquipe = idEquipe

  def equals(other: Move) =
    {
      var equals = false;
      if (other.isInstanceOf[Move]) {
        equals = positions.equals(other.positions)
      }
      equals
    }

  override def toString() =
    {
      positions.mkString(", ")
    }
}

object Move {
  var COORD_SEPARATOR = ",";
  def apply(pos: Int*) = new Move(pos)

  def parse(strMove: String) {
    var move = new Move(Seq(1))
    if (StringUtils.isNotBlank(strMove)) {
      var strCoords = strMove.split(COORD_SEPARATOR);
      var coords = Seq[Int]();
      strCoords.foreach(coords :+ Integer.valueOf(_))
      move = new Move(coords)
    }
    move
  }
}
