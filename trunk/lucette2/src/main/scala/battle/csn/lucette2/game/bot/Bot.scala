package battle.csn.lucette2.game.bot

import battle.csn.lucette2.game.board.Board
import battle.csn.lucette2.game.structure.Move


trait Bot[T] {
/**
     * Un bot doit être capable de choisir l'action a effectuer sur un plateau pour une équipe donné
     * @param board le plateau sur lequel jouer
     * @param player l'equipe
     * @return le coup
     */
    def chooseMove( board:Board[T], player:Int ):Option[Move]
}