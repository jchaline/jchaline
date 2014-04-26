package battle.csn.lucette.game.bot;

import battle.csn.lucette.game.board.IBoard;


/**
 * Type abstrait de bot, fournissant une implémentation partielle
 * @author Jeremy
 * 
 */
public abstract class AbstractBot<T> implements IBot<T>
{
    private IBoard<T> _plateau;

    @Override
    public void setPlateau( IBoard<T> plateau )
    {
        this._plateau = plateau;
    }

    /**
     * Fournit le plateau utilisable par le bot
     * @return le plateau
     */
    protected IBoard<T> getPlateau( )
    {
        return _plateau;
    }
}
