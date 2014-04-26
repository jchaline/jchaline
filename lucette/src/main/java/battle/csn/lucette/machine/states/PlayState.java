package battle.csn.lucette.machine.states;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import battle.csn.lucette.game.GameEngine;
import battle.csn.lucette.game.Moteur;
import battle.csn.lucette.game.board.IBoard;
import battle.csn.lucette.game.structure.Move;
import battle.csn.lucette.machine.Chain;
import battle.csn.lucette.machine.FightStateMachine;
import battle.csn.lucette.machine.states.framework.AbstractIntermediateState;
import battle.csn.lucette.service.RestClientAPI;


public class PlayState extends AbstractIntermediateState
{
    private static final String OUI = "OK";
    private static final String KO = "KO";
    private static final String PTT = "PTT";
    private static final String GAGNE = "GAGNE";

    @Override
    public void pull( Chain chain )
    {
        FightStateMachine stateMachine = chain.getStateMachine( );

        // TODO : here the magic happend.

        String strBoard = new RestClientAPI( ).getBoard( chain.getStateMachine( ).getGame( ) );

        Moteur moteur = new Moteur( "" );
        IBoard plateau = moteur.getPlateau( );
        plateau.updateCases( strBoard );

        List<String> idEquipes = new ArrayList<>( );
        idEquipes.add( chain.getStateMachine( ).getIdEquipe( ) );
        idEquipes.add( "TODO adv" );
        plateau.setIdEquipes( idEquipes );

        Move move = new GameEngine( ).getMove( plateau, chain.getStateMachine( ).getIdEquipe( ) );
        List<Integer> positions = move.getPositions( );

        String result = new RestClientAPI( ).play( stateMachine.getGame( ), stateMachine.getIdEquipe( ), positions );

        switch ( result )
        {
        case OUI:
            chain.setState( new InitialState( ) );
            break;
        case KO:
            chain.setState( new ErrorState( "We did a forbidden move here :/" ) );
            break;
        case PTT:
            System.out.println( "Why the f**k did we played now ???" );
            chain.setState( new InitialState( ) );
            break;
        case GAGNE:
            chain.setState( new GagneState( ) );
            break;
        default:
            chain.setState( new ErrorState( "I don't know what to do with this" ) );
            break;
        }

    }
}
