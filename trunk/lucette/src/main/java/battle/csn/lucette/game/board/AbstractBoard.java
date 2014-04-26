package battle.csn.lucette.game.board;

import java.util.ArrayList;
import java.util.List;

import battle.csn.lucette.game.structure.Move;


/**
 * Classe representant le plateau de jeu
 * @author Jeremy
 * 
 */
public abstract class AbstractBoard<T> implements IBoard<T>
{

    public static final String OUI = "OUI";
    public static final String NON = "NON";
    public static final String GAGNE = "GAGNE";
    public static final String PERDU = "PERDU";
    public static final String ANNULE = "ANNULE";

    protected ICases<T> _cases = new MultiTab<T>();
    private List<String> _idEquipes = new ArrayList<String>( );

    /**
     * Methode par defaut retourne le pseudo
     */
    @Override
    public String getIdEquipe( String pseudo )
    {
        return pseudo;
    }

    @Override
    public String getBoard( )
    {
        return "";
    }

    /**
     * Creer un plateau par defaut, avec une ligne et une colonne
     */
    public AbstractBoard( int x, int y )
    {
        updateSize( x, y );
    }

    @Override
    public void updateSize( int... dimensions )
    {
        _cases.updateSizes( dimensions );
    }

    @Override
    public T readCase( int... dimensions )
    {
        return _cases.get( dimensions );
    }

    /**
     * Condition necessaire pour jouer un mouvement
     * @param move coup à jouer
     * @return true si le coup peut etre joué, false sinon
     */
    protected abstract boolean validateMove( Move move );

    @Override
    public List<String> getIdEquipes( )
    {
        return _idEquipes;
    }

    @Override
    public void setIdEquipes( List<String> idEquipes )
    {
        this._idEquipes = idEquipes;
    }

    /**
     * @return the _cases
     */
    public ICases<T> getCases( )
    {
        return _cases;
    }

    /**
     * @param cases the _cases to set
     */
    public void setCases( ICases<T> cases )
    {
        this._cases = cases;
    }

    @Override
    public void writeCase( T value, int ...dimensions)
    {
        _cases.set( value, dimensions );
    }
}
