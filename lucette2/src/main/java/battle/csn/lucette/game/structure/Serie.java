package battle.csn.lucette.game.structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Serie
{

    private List<Coord> _coords;

    public Serie( Coord... coords )
    {
        _coords = new ArrayList<Coord>( Arrays.asList( coords ) );
    }

    /**
     * @return the _coords
     */
    public List<Coord> getCoords( )
    {
        return _coords;
    }

    /**
     * @param _coords the _coords to set
     */
    public void setCoords( List<Coord> _coords )
    {
        this._coords = _coords;
    }
}
