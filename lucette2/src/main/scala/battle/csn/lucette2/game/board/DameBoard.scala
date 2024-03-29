package battle.csn.lucette2.game.board


import battle.csn.lucette2.game.structure.Move
import org.apache.commons.lang3.StringUtils
import scala.collection.mutable.MutableList
import battle.csn.lucette2.game.structure.State

class DameBoard extends Board[Int] {

  val DIM_X, DIM_Y = 10
  val LINE_SEPARATOR = "\n"
  val COLUMN_SEPARATOR = ","
  val BLACK = "o"
  val WHITE = "x"
  var turn = 1
  
  def getWidth = DIM_X

  def getHeight = DIM_X

  {
    updateSize(Seq(DIM_X, DIM_Y))
    reset()
  }

  def play(player: Int, move: Move) {
    var x1 = move.positions(0)
    var y1 = move.positions(1)
    var x2 = move.positions(2)
    var y2 = move.positions(3);
    writeCase(State.EMPTY, x1, y1) //empty the initial position
    writeCase(player, x2, y2) //fill the new one.
    //if we take an adverse pawn
    if ((Math.abs(x2 - x1) == 2)) {
      var adverseX = (x2 + x1) / 2
      var adverseY = (y2 + y1) / 2
      writeCase(State.EMPTY, adverseX, adverseY)
    }
    turn *= -1
  }

  override def toString() = {
    var gameBoard = ""
    for (i <- 0 to DIM_X - 1) {
      for (j <- 0 to DIM_Y - 1) {
        var value = readCase(Seq(i, j))
        var append = value match {
          case None | Some(State.EMPTY) => " "
          case Some(State.WHITE) => WHITE
          case Some(State.BLACK) => BLACK
          case _ => ""
        }
        gameBoard += (append + COLUMN_SEPARATOR)
      }
      gameBoard += LINE_SEPARATOR
    }
    gameBoard
  }

  def deepCopy() = {
    var copy = new DameBoard()
    copy.updateBoard(this.toString)
    copy
  }

  def caseMatchValue(x: Int, y: Int, value: Int) = {
    var c = readCase(Seq(x, y))
    c match {
      case Some(a) => if (a == value) true else false
      case _ => false
    }
  }

  def moveAvailables(player: Int) = {
    var moves = MutableList[Move]()

    for (row <- 0 to DIM_X - 1) {
      for (column <- 0 to DIM_Y - 1) {

        //only the turn color can make a move 
        if (caseMatchValue(row, column, player)) {
          //check what's around the pawn.
          //1) check onward left
          if (inBoundary(row + player, column - 1)) {
            if (caseMatchValue(row + player, column - 1, State.EMPTY)) {
              //that's a possible move
              moves += Move(row, column, row + player, column - 1)
            } else //if the color is different we may take the pawn
            if (!caseMatchValue(row + player, column - 1, player)) {
              if (inBoundary(row + (2 * player), column - 2)) {
                if (caseMatchValue(row + (2 * player), column - 2, State.EMPTY)) {
                  moves += Move(row, column, row + (2 * player), column - 2)
                }
              }
            }
          }

          //2) check onward right
          if (inBoundary(row + player, column + 1)) {
            if (caseMatchValue(row + player, column + 1, State.EMPTY)) {
              //that's a possible move
              moves += Move(row, column, row + player, column + 1)
            } //if the color is different we may take the pawn
            else {
              if (!caseMatchValue(row + player, column + 1, player)) {
                if (inBoundary(row + (2 * player), column + 2)) {
                  if (caseMatchValue(row + (2 * player), column + 2, State.EMPTY)) {
                    moves += Move(row, column, row + (2 * player), column + 2)
                  }
                }
              }
            }
          }

          //3) check backward left only if we take the pawn
          if (inBoundary(row - player, column - 1)) {
            if (!caseMatchValue(row - player, column - 1, player)
              && !caseMatchValue(row - player, column - 1, State.EMPTY)) {
              if (inBoundary(row - (2 * player), column - 2)) {
                if (caseMatchValue(row - (2 * player), column - 2, State.EMPTY)) {
                  moves += Move(row, column, row - (2 * player), column - 2)
                }
              }
            }
          }

          //4) check backward right only if we take the pawn 
          if (inBoundary(row - player, column + 1)) {
            if (!caseMatchValue(row - player, column + 1, player)
              && !caseMatchValue(row - player, column + 1, State.EMPTY)) {
              if (inBoundary(row - (2 * player), column + 2)) {
                if (caseMatchValue(row - (2 * player), column + 2, State.EMPTY)) {
                  moves += Move(row, column, row - (2 * player), column + 2)
                }
              }
            }
          }
        }
      }
    }
    moves.toList
  }

  def whiteCase(x: Int, y: Int) = (x + y) % 2 == 0

  def reset() {
    for (i <- 0 to DIM_X - 1) for (j <- 0 to DIM_Y - 1) writeCase(State.EMPTY, i, j)

    //set the black
    for (i <- 0 to 3) for (j <- 0 to DIM_Y - 1) if (whiteCase(i, j)) writeCase(State.WHITE, i, j)

    for (i <- DIM_X - 4 to DIM_X - 1) for (j <- 0 to DIM_Y - 1) if (whiteCase(i, j)) writeCase(State.BLACK, i, j)
  }

  def inBoundary(x: Int, y: Int) = (x < DIM_X && x >= 0 && y < DIM_Y && y >= 0)

  def updateBoard(strBoard: String) {
    val lines = strBoard.split(LINE_SEPARATOR)
    var i = 0
    for (line <- lines) {
      var j = 0;
      val columns = line.split(COLUMN_SEPARATOR)
      for (col <- columns) {
        val elem = col.trim();
        if (StringUtils.isNotBlank(elem)) {
          writeCase(if (elem.equals(WHITE)) State.WHITE else State.BLACK, i, j)
        } else {
          writeCase(State.EMPTY, i, j)
        }
        j += 1
      }
      i += 1
    }
  }

  def gameStatus(player: Int) =
    {
      var status = "";
      if (moveAvailables(player).isEmpty) {
        status = Board.PERDU;
      } else {
        status = if (player == turn) Board.OUI else Board.NON
      }
      status
    }
}