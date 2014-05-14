package battle.csn.lucette.game.structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


/**
 * Contient la liste des positions correspondant à un coup joué
 * @author Jeremy
 * 
 */
public class Move
{
    private static String COORD_SEPARATOR = ",";

    private List<Integer> _positions;
    private String _idEquipe;

    public Move( Integer... positions )
    {
        setPositions( new ArrayList<Integer>( Arrays.asList( positions ) ) );
    }

    /**
     * @return the _positions
     */
    public List<Integer> getPositions( )
    {
        return _positions;
    }

    /**
     * @param _positions the _positions to set
     */
    public void setPositions( List<Integer> _positions )
    {
        this._positions = _positions;
    }

    /**
     * @return the _idEquipe
     */
    public String getIdEquipe( )
    {
        return _idEquipe;
    }

    /**
     * @param _idEquipe the _idEquipe to set
     */
    public void setIdEquipe( String _idEquipe )
    {
        this._idEquipe = _idEquipe;
    }

    @Override
    public boolean equals( Object other )
    {
        boolean equals = false;
        if ( other instanceof Move && other != null )
        {
            equals = this.getPositions( ).equals( ( (Move) other ).getPositions( ) );
        }
        return equals;
    }

    @Override
    public String toString( )
    {
        String toString = "(";
        for ( Integer pos : _positions )
        {
            toString += " " + pos + " ,";
        }
        toString += ")";
        return toString;
    }

    public static Move parse( String strMove )
    {
        Move move = new Move( );

        if ( StringUtils.isNotBlank( strMove ) )
        {
            String[] strCoords = strMove.split( COORD_SEPARATOR );
            List<Integer> coords = new ArrayList<Integer>( );
            for ( String strCoord : strCoords )
            {
                coords.add( Integer.valueOf( strCoord ) );
            }
        }

        return move;
    }
}
