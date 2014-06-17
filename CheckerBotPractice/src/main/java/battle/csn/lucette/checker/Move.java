package battle.csn.lucette.checker;

/**
 * Describe a move on the board.
 * Equals and HashCode are overridden to allow the
 * use of contains methos in a list of Move.
 * @author michael
 * 
 */
public class Move
{
    public int x1;
    public int y1;
    public int x2;
    public int y2;

    public Move( int x1, int y1, int x2, int y2 )
    {
        super( );
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public int hashCode( )
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + x1;
        result = prime * result + x2;
        result = prime * result + y1;
        result = prime * result + y2;
        return result;
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass( ) != obj.getClass( ) )
            return false;
        Move other = (Move) obj;
        if ( x1 != other.x1 )
            return false;
        if ( x2 != other.x2 )
            return false;
        if ( y1 != other.y1 )
            return false;
        if ( y2 != other.y2 )
            return false;
        return true;
    }

    @Override
    public String toString( )
    {
        return "[" + x1 + "," + y1 + " -> " + x2 + "," + y2 + "]";
    }

}