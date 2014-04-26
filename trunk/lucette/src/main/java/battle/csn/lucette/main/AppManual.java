package battle.csn.lucette.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import battle.csn.lucette.game.Moteur;
import battle.csn.lucette.game.structure.Move;
import battle.csn.lucette.service.IClient;
import battle.csn.lucette.service.RestClientAPI;


/**
 * Hello world!
 * Application d'execution, faisant le lien entre le client reseau et le moteur
 * de jeu
 * Application utilisable par un humain via System.in
 */
public class AppManual
{
    public static final int NBR_COORD = 4;
    public static final String STOP = "0";
    public static final Integer LEVEL = 1;
    public static final String ID_EQUIPE = "lucette";

    protected static Moteur<Integer> moteur;
    protected static IClient clientBattle = new RestClientAPI( );

    private static Scanner scan = new Scanner( System.in );
    private static List<String> menuChoices;

    private static void initialization( )
    {
        menuChoices = new ArrayList<String>( );

        //le joueur doit fournir les parametres permettant de jouer le coups
        menuChoices.add( "1) Jouer un coup " );

        //retourne les informations suivantes : 
        // - identifiant de la partie en cours si elle existe (NA sinon)
        // - indique "a l'équipe de jouer" (oui, non, gagne, perdu, annule)
        menuChoices.add( "2) Avoir les infos sur la partie en cours" );

        //permet d'obtenir le plateau de jeu, de le modeliser et d'avoir les informations pour jouer le coup suivant
        // A faire : parser la reponse pour modeliser le plateau
        menuChoices.add( "3) Avoir le plateau de jeu" );

        //permet d'avoir le dernier coup joué sur le plateau
        menuChoices.add( "4) Dernier coup joué" );

        menuChoices.add( "5) Créer une partie contre l'IA" );
    }

    public static void displayMenu( )
    {
        for ( String choice : menuChoices )
        {
            System.out.println( choice );
        }
    }

    public static void main( String[] args )
    {
        initialization( );
        String choice = "";
        while ( !choice.equalsIgnoreCase( STOP ) )
        {
            displayMenu( );
            choice = scan.nextLine( );
            try
            {
                checkChoice( choice );
            }
            catch ( Exception e )
            {
                System.out.println( "Attention à l'ordre de vos coups !" );
            }
        }
    }

    private static void checkChoice( String choice )
    {
        switch ( choice )
        {
        case "1":
            System.out.println( "Vous avez choisi de jouer un coup" );
            String coords = scan.nextLine( );
            Integer[] intCoords = parseCoords( coords );
            clientBattle.play( moteur.getIdPartie( ), ID_EQUIPE, Arrays.asList( intCoords ) );
            break;
        case "2":
            System.out.println( "Vous avez choisi d'avoir des informations" );
            String gameStatus = clientBattle.getGameStatus( moteur.getIdPartie( ), ID_EQUIPE );
            System.out.println( "Etat : " );
            System.out.println( gameStatus );
            break;
        case "3":
            System.out.println( "Vous avez choisi d'avoir le plateau" );
            String board = clientBattle.getBoard( moteur.getIdPartie( ) );
            System.out.println( "Plateau : " );
            System.out.println( board );
            break;
        case "4":
            System.out.println( "Vous avez choisi de connaitre le dernier coup joué" );
            break;
        case "5":
            System.out.println( "Vous avez choisi de créer une partie contre l'IA" );
            clientBattle.getNewPracticeGame( ID_EQUIPE, LEVEL );
            String idPartie = clientBattle.getGameId( ID_EQUIPE );
            System.out.println( "Identifiant de la partie : " + idPartie );
            moteur = new Moteur<Integer>( idPartie );
            break;
        case STOP:
            System.out.println( "Bye !" );
            break;
        default:
            System.out.println( "Choix invalide" );
            ;
        }
    }

    /**
     * Transformes les coordonnées entrées par l'utilisateur en tableau d'entier
     * @param coords coordonnées string
     * @return les coordonnées dans un tableau d'entier
     */
    private static Integer[] parseCoords( String coords )
    {
        List<Integer> positions = Move.parse( coords ).getPositions( );
        return (Integer[]) positions.toArray( );
    }
}
