package battle.csn.lucette.game.bot;

import java.util.ArrayList;
import java.util.List;

import battle.csn.lucette.game.board.Etat;
import battle.csn.lucette.game.structure.Move;


public class TictactoeBot extends AbstractBot<Integer>
{
    @Override
    public Move chooseMove( String idEquipe )
    {
        int myPion = getPlateau( ).getIdEquipes( ).get( 0 ).equals( idEquipe ) ? Etat.WHITE : Etat.BLACK;
        int otherPion = getPlateau( ).getIdEquipes( ).get( 1 ).equals( idEquipe ) ? Etat.BLACK : Etat.WHITE;
        Move move = coupEvidents( myPion );
        if ( move == null )
        {
            List<Move> winnings = findWinMove( myPion );
            if ( winnings.isEmpty( ) )
            {
                List<Move> loosing = findWinMove( otherPion );
                if ( loosing.isEmpty( ) )
                {
                    move = getPlateau( ).getMoveAvailables( ).get( 0 );
                }
                else
                {
                    move = loosing.get( 0 );
                }
            }
            else
            {
                move = winnings.get( 0 );
            }
        }

        return move;
    }

    /**
     * Determine si un coup evident existe pour une situation donnée
     * @param myPion etat recherche par le bot pour jouer son coup
     * @return le coup s'il existe, null sinon
     */
    private Move coupEvidents( int myPion )
    {
        Move move = null;
        int movesAvailables = this.getPlateau( ).getMoveAvailables( ).size( );

        //meilleur coup à jouer en début de partie
        if ( movesAvailables == 9 )
        {
            move = new Move( 1, 1 );
        }
        else if ( movesAvailables == 8 )
        {
            if ( this.getPlateau( ).readCase( 1, 1 ) == Etat.EMPTY )
            {
                move = new Move( 1, 1 );
            }
            else
            {
                //second meilleur coup, prendre une diagonale
                move = new Move( 0, 0 );
            }
        }
        else if ( movesAvailables == 7 )
        {
            //la case 1,1 est forcement prise à ce moment là
            if ( this.getPlateau( ).readCase( 1, 1 ) == Etat.EMPTY )
            {
                move = new Move( 1, 1 );
            }
        }

        return move;
    }

    /**
     * Trouve les coups gagnant pour une equipe donné
     * @param etat correspond a une des equipe du jeu
     * @return les coups gagnant
     */
    private List<Move> findWinMove( int etat )
    {
        List<Move> moves = new ArrayList<Move>( );

        return moves;
    }
}
