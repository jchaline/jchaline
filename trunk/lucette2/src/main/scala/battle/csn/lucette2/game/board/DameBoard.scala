package battle.csn.lucette2.game.board

import battle.csn.lucette2.game.structure.Move
import battle.csn.lucette.game.board.Etat

class DameBoard extends Board[Int] {

  val DIM_X, DIM_Y = 10
  val LINE_SEPARATOR = "\n"
  val COLUMN_SEPARATOR = ","
  val BLACK = "o"
  val WHITE = "x"

  {
    updateSize(Seq(DIM_X, DIM_Y))
    reset()
  }
  
  def getCases()=this.cases

  def play(move: Move) {

  }

  override def toString() = {
    var gameBoard = ""
    for (i <- 0 to DIM_X - 1) {
      for (j <- 0 to DIM_Y - 1) {
        var value = readCase(Seq(i, j))
        var append = value match {
              case None | Some(Etat.EMPTY) =>  " "
              case Some(Etat.WHITE) => WHITE
              case Some(Etat.BLACK) => BLACK 
              case _ => ""
            }
        gameBoard += (append + COLUMN_SEPARATOR)
        
      }
      gameBoard += LINE_SEPARATOR
    }
    gameBoard
  }

  def deepCopy() = {
    this
  }

  def moveAvailables(player: Int) = {
    Seq[Move]()
  }

  def whiteCase(x: Int, y: Int) = (x + y) % 2 == 0

  def reset() {
    for (i <- 0 to DIM_X - 1) for (j <- 0 to DIM_Y - 1) writeCase(Etat.EMPTY, Seq(i, j))

    //set the black
    for (i <- 0 to 3) for (j <- 0 to DIM_Y - 1) if (whiteCase(i,j)) writeCase( Etat.BLACK , Seq(i, j))

    for (i <- DIM_X - 4 to DIM_X - 1) for (j <- 0 to DIM_Y - 1) if (whiteCase(i,j)) writeCase( Etat.WHITE , Seq(i, j))
  }

  def updateBoard(strBoard: String) {

  }
}