package battle.csn.lucette2.game.board

import battle.csn.lucette2.game.structure.Move
import battle.csn.lucette.game.board.Etat
import org.apache.commons.lang3.StringUtils
import scala.collection.mutable.MutableList

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

  def play(move: Move) {

  }

  override def toString() = {
    var gameBoard = ""
    for (i <- 0 to DIM_X - 1) {
      for (j <- 0 to DIM_Y - 1) {
        var value = readCase(Seq(i, j))
        var append = value match {
          case None | Some(Etat.EMPTY) => " "
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
    var copy = new DameBoard()
    copy.updateBoard(this.toString)
    copy
  }

  def caseMatchValue(x:Int, y:Int, value: Int) = {
    var c = readCase(Seq(x,y))
    c match {
      case Some(a) => if (a == value) true else false
      case _ => false
    }
  }

  def moveAvailables(color: Int) = {
    var moves = MutableList[Move]()

    for (row <- 0 to DIM_X - 1) {
      for (column <- 0 to DIM_Y - 1) {
        
        //only the turn color can make a move 
        if (caseMatchValue( row, column ,color)) {
          //check what's around the pawn.
          //1) check onward left
          if (inBoundary(row + color, column - 1)) {
            if (caseMatchValue(row + color, column - 1, Etat.EMPTY)) {
              //that's a possible move
              moves += Move(row, column, row + color, column - 1)
            } else //if the color is different we may take the pawn
            if (!caseMatchValue(row + color, column - 1, color)) {
              if (inBoundary(row + (2 * color), column - 2)) {
                if (caseMatchValue(row + (2 * color), column - 2, Etat.EMPTY)) {
                  moves += Move(row, column, row + (2 * color), column - 2)
                }
              }
            }
          }

          //2) check onward right
          if (inBoundary(row + color, column + 1)) {
            if (caseMatchValue(row + color, column + 1,Etat.EMPTY)) {
              //that's a possible move
              moves += Move(row, column, row + color, column + 1)
            }
            //if the color is different we may take the pawn
            else{
                if (!caseMatchValue(row + color, column + 1,color)) {
                  if (inBoundary(row + (2 * color), column + 2)) {
                    if (caseMatchValue(row + (2 * color), column + 2,Etat.EMPTY)) {
                      moves+= Move(row, column, row + (2 * color), column + 2)
                    }
                  }
                }
            }
          }

          //3) check backward left only if we take the pawn
          if (inBoundary(row - color, column - 1)) {
            if (!caseMatchValue(row - color, column - 1,color)
              && !caseMatchValue(row - color, column - 1,Etat.EMPTY)) {
              if (inBoundary(row - (2 * color), column - 2)) {
                if (caseMatchValue(row - (2 * color), column - 2,Etat.EMPTY)) {
                  moves +=  Move(row, column, row - (2 * color), column - 2)
                }
              }
            }
          }

          //4) check backward right only if we take the pawn 
          if (inBoundary(row - color, column + 1)) {
            if (!caseMatchValue(row - color, column + 1,color)
              && !caseMatchValue(row - color, column + 1,Etat.EMPTY)) {
              if (inBoundary(row - (2 * color), column + 2)) {
                if (caseMatchValue(row - (2 * color), column + 2,Etat.EMPTY)) {
                  moves += Move(row, column, row - (2 * color), column + 2)
                }
              }
            }
          }
        }
      }
    }
    moves;
  }

  def whiteCase(x: Int, y: Int) = (x + y) % 2 == 0

  def reset() {
    for (i <- 0 to DIM_X - 1) for (j <- 0 to DIM_Y - 1) writeCase(Etat.EMPTY, Seq(i, j))

    //set the black
    for (i <- 0 to 3) for (j <- 0 to DIM_Y - 1) if (whiteCase(i, j)) writeCase(Etat.WHITE, Seq(i, j))

    for (i <- DIM_X - 4 to DIM_X - 1) for (j <- 0 to DIM_Y - 1) if (whiteCase(i, j)) writeCase(Etat.BLACK, Seq(i, j))
  }

  def inBoundary(x: Int, y: Int) = (x < DIM_X && x >= 0 && y < DIM_Y && y >= 0)

  def updateBoard(strBoard: String) {
    var lines = strBoard.split(LINE_SEPARATOR)
    var i = 0
    for (line <- lines) {
      var j = 0;
      var columns = line.split(COLUMN_SEPARATOR)
      for (col <- columns) {
        var elem = col.trim();
        if (StringUtils.isNotBlank(elem)) {
          writeCase(if (elem.equals(WHITE)) Etat.WHITE else Etat.BLACK, Seq(i, j))
        } else {
          writeCase(Etat.EMPTY, Seq(i, j))
        }
        j += 1
      }
      i += 1
    }
  }
}