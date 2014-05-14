package battle.csn.lucette.game.structure;

public class Coord
{
    private int _x;
    private int _y;
    
    public Coord(int x, int y){
        setY( x );
        setY( y );
    }

    /**
     * @return the _x
     */
    public int getX( )
    {
        return _x;
    }

    /**
     * @param _x the _x to set
     */
    public void setX( int _x )
    {
        this._x = _x;
    }

    /**
     * @return the _y
     */
    public int getY( )
    {
        return _y;
    }

    /**
     * @param _y the _y to set
     */
    public void setY( int _y )
    {
        this._y = _y;
    }
}
