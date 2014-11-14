package battle.csn.lucette2.game.logic

import org.springframework.stereotype.Service
import scala.collection.JavaConverters._
import battle.csn.lucette2.game.board.Board
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.util.{ Failure, Success }
import scala.util.Try
import scala.util.control.Breaks._

class NegaMax extends Logic {

  private var cpt = 0

  def getCpt(): Int = { cpt }

  def solveR(player: Int, plateau: Board[Int], alpha: Integer, beta: Integer, heuristique: (Int, Board[Int]) => Int, findMax: Boolean, deep: Integer): Int = {
    val s = future {
      solveR(player, plateau, alpha, beta, heuristique, findMax, deep)
    }
    var res = 0
    s onComplete {
      case Success(value) => res = value
      case _ =>
    }
    res
  }

  /**
   * this algorithm can only use with black & white players games
   * TODO : verifier avec une heuristique qui donne toujours le score positif d'un joueur
   * si enlever le "findmax" (toujours avoir true) suffit, comparer avec des exemples d'algo sur le sujet
   */
  def solveOld(player: Int, plateau: Board[Int], alpha: Integer, beta: Integer, heuristique: (Int, Board[Int]) => Int, findMax: Boolean, deep: Integer): Int = {
    cpt += 1
    var value = 0;
    val moves = plateau.moveAvailables(player)

    //si le plateau est une feuille
    if (moves.size == 0 || deep == 0) {
      value = heuristique(player, plateau)
    } else {
      var bestScore = if (findMax) Integer.MIN_VALUE else Integer.MAX_VALUE
      //Pour toutes les coups jouables du plateau
      for (move <- moves) {
        val deepCopy = plateau.deepCopy()
        deepCopy.play(player, move)
        val score = solveOld(player * -1, deepCopy, -beta, -alpha, heuristique, !findMax, deep - 1)
        if ((findMax && score > bestScore) || (!findMax && score < bestScore)) {
          bestScore = score
          if ((findMax && bestScore > alpha) || (!findMax && bestScore < alpha)) {
            if ((findMax && bestScore >= beta) || (findMax && bestScore <= beta)) {
              value = bestScore
              break
            }
          }
        }
      }
    }
    //println("cpt : "+cpt)
    value
  }
  
  
  def solve(node:Board[Int], heuristic:(Int,Board[Int]) => Int, depth:Int, α:Int, β:Int, color:Int):Int={
    
    val moves = node.moveAvailables(color)
    if (depth == 0 || moves.isEmpty ) {
        color * heuristic(color,node)
    }
    else{
      var αLooped = α
        var bestValue = Int.MinValue
        for(move <- moves){
          val copy = node.deepCopy
          copy.play(color * -1, move)
          
          val value = -solve(copy, heuristic, depth - 1, -β, -αLooped, -color)
          bestValue = math.max( bestValue, value )
          αLooped = math.max( αLooped, value );
          if (αLooped >= β){
              break
          }
        }
      bestValue
    }
  }

}