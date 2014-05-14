package battle.csn.lucette.game.bot;

import battle.csn.lucette.game.structure.Move;


/**
 * Bot par defaut
 * @author Jeremy
 * 
 */
public class DonaldDuck extends AbstractBot<Integer>
{

    @Override
    public Move chooseMove( String idEquipe )
    {
        double randX = Math.random( );
        double randY = Math.random( );
        randX *= 10;
        randY *= 10;
        Move move = new Move( (int) randX, (int) randY );
        return move;
    }

}
