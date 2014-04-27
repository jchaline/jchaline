package battle.csn.lucette.game.bot;

import battle.csn.lucette.game.board.IBoard;
import battle.csn.lucette.game.structure.Move;

/**
 * The interface for bot
 * @author Jeremy
 *
 * @param <T> the game board object content
 */
public interface IBot<T>
{

    /**
     * Un bot doit être capable de choisir l'action a effectuer sur un plateau à
     * un instant t;
     * Nous considerons ici que le bot peut jouer pour n'importe quelle équipe :
     * Il sait qui doit jouer, il trouve le meilleur coup.
     * @param idEquipe equipe pour qui le bot trouve le coup à jouer
     * @return
     */
    Move chooseMove( String idEquipe );

    /**
     * @param _plateau the _plateau to set
     */
    void setPlateau( IBoard<T> plateau );
    
    /**
     * Fournit le plateau utilisable par le bot
     * @return le plateau
     */
    IBoard<T> getPlateau( );
}
