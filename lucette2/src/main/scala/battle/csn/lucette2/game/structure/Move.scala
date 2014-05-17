package battle.csn.lucette2.game.structure

import org.apache.commons.lang3.StringUtils

class Move {
    var COORD_SEPARATOR = ",";

    var positions: Seq[Int]=_ ;
    var idEquipe:String=_;

    def Move( positions:Seq[Int] )=
    {
        this.positions=positions
    }

    /**
     * @return the _positions
     */
    def getPositions( )=this.positions

    /**
     * @param _positions the _positions to set
     */
    def setPositions( positions:Seq[Int] )=this.positions = positions;

    /**
     * @return the _idEquipe
     */
    def getIdEquipe( ) = this.idEquipe

    /**
     * @param _idEquipe the _idEquipe to set
     */
    def setIdEquipe( idEquipe:String )=this.idEquipe = idEquipe;

    def equals( other:Move )=
    {
        var equals = false;
        if ( other.isInstanceOf[Move])
        {
            equals = this.getPositions( ).equals( other.getPositions( ) );
        }
        equals
    }

    override def toString( )=
    {
        var toString = "(";
        positions.foreach(toString += " " + _ + " ,")
        toString += ")"
        toString
    }

    def parse( strMove:String )
    {
        var move = new Move( );

        if ( StringUtils.isNotBlank( strMove ) )
        {
            var strCoords = strMove.split( COORD_SEPARATOR );
            var coords = List[Integer]( );
            strCoords.foreach(coords :+ Integer.valueOf( _ ) )
        }

        move
    }
}