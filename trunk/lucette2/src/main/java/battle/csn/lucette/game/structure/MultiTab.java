package battle.csn.lucette.game.structure;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;


/**
 * Structure de donnée multidimensionelle pour enregistrer la valeurs des cases
 * d'un jeu à n dimension
 * @author Jeremy
 * 
 * @param <T> type d'éléments enregistrés
 */
public class MultiTab<T> implements ICases<T>
{
    private int _deep;
    private T _value;
    private List<MultiTab<T>> _subTab;
    private int[] _dimensions;

    /**
     * Get the sub tab
     * @param index the index of the sub tab
     * @return the tab
     */
    private MultiTab<T> getSubTab( int index )
    {
        return _subTab.get( index );
    }

    public MultiTab( )
    {
        this._deep = 1;
    }

    @Override
    public int[] getSizes( )
    {
        return _dimensions;
    }

    @Override
    public T get( int... dimensions )
    {
        if ( dimensions.length == 0 )
        {
            return _value;
        }
        else
        {
            return this.getSubTab( dimensions[0] ).get( ArrayUtils.remove( dimensions, 0 ) );
        }
    }

    @Override
    public void set( T value, int... dimensions )
    {
        if ( dimensions.length == 0 )
        {
            _value = value;
        }
        else
        {
            this.getSubTab( dimensions[0] ).set( value, ArrayUtils.remove( dimensions, 0 ) );
        }
    }

    @Override
    public void updateSizes( int... dimensions )
    {
        _dimensions = dimensions;
        _deep = dimensions.length;
        if ( dimensions.length > 0 )
        {
            int[] dimensionsUpdate = ArrayUtils.remove( dimensions, 0 );
            _subTab = new ArrayList<MultiTab<T>>( );
            for ( int i = 0; i < dimensions[0]; i++ )
            {
                _subTab.add( new MultiTab<T>( ) );
                _subTab.get( i ).updateSizes( dimensionsUpdate );
            }
        }
        else
        {
            this._value = null;
        }
    }

    /**
     * @return the _deep
     */
    public int getDeep( )
    {
        return _deep;
    }

    @Override
    public String toString( )
    {
        String result = "";
        if ( _deep == 1 )
        {
            for ( int i = 0; i < this._dimensions[0]; i++ )
            {
                result += this.getSubTab( i ).get( );
            }
        }
        return result;
    }
}
