package battle.csn.lucette.game.bot;

import battle.csn.lucette.game.board.IBoard;
import battle.csn.lucette.game.logic.ILogic;


/**
 * Type abstrait de bot, fournissant une implémentation partielle
 * @author Jeremy
 * 
 */
public abstract class AbstractBot<T> implements IBot<T>
{
    private IBoard<T> _plateau;
    private ILogic _logic;

    @Override
    public void setPlateau( IBoard<T> plateau )
    {
        this._plateau = plateau;
    }

    /**
     * Fournit le plateau utilisable par le bot
     * @return le plateau
     */
    @Override
    public IBoard<T> getPlateau( )
    {
        return _plateau;
    }

    /**
     * @return the logic
     */
    public ILogic getLogic( )
    {
        return _logic;
    }

    /**
     * @param logic the logic to set
     */
    public void setLogic( ILogic logic )
    {
        this._logic = logic;
    }
}
