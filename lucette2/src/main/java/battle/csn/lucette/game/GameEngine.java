package battle.csn.lucette.game;

import battle.csn.lucette.game.board.IBoard;
import battle.csn.lucette.game.bot.IBot;
import battle.csn.lucette.game.structure.Move;


public class GameEngine implements IGameEngine
{

    public Move getMove( IBoard plateau, String idEquipe )
    {
        Moteur moteur = new Moteur("");
        IBot ibb = moteur.getBot( );
        ibb.setPlateau( plateau );
        return ibb.chooseMove( idEquipe );
    }
}
