package battle.csn.lucette.main;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import battle.csn.lucette.machine.FightStateMachine;
import battle.csn.lucette.service.IClient;
import battle.csn.lucette.service.InitGameHelper;
import battle.csn.lucette.service.RestClientAPI;


/**
 * Hello world!
 * 
 */
public class AppAuto
{
    private static final String TEAM_PASSWORD = "sopra123";
    private static final String TEAM_NAME = "lucette";
    private static final String APP_NAME = "RobotKiller";
    
    
    private static final IClient restClient = new RestClientAPI( );

    public static void main( String[] args )
    {
        Options options = getOptions( );

        try
        {
            CommandLine cmd = new DefaultParser( ).parse( options, args );
            String teamId = getTeamName( cmd );
            // TODO : récupérer l'identidiant d'équipe
            if ( cmd.hasOption( "h" ) )
            {
                help( options );
            }
            else if ( cmd.hasOption( "p" ) )
            {
                pings( );
            }
            else if ( cmd.hasOption( "m" ) )
            {
                final String game = InitGameHelper.getGameId( teamId );
                new FightStateMachine( teamId, game ).start( );
            }
            else if ( cmd.hasOption( "e" ) )
            {
                int level = Integer.parseInt( cmd.getOptionValue( "e" ) );
                if ( level < 0 || level > 5 )
                {
                    System.out.println( "Level must be between 0 and 5." );
                    help( options );
                }
                else
                {
                    final String game = InitGameHelper.getTrainingGameId( teamId, level );
                    new FightStateMachine( teamId, game ).start( );
                }
            }
        }
        catch ( ParseException e )
        {
            System.out.println( e.getMessage( ) );
            help( options );
        }

    }

    private static String getTeamName( CommandLine cmd )
    {
        final String team;
        if ( cmd.hasOption( "t" ) )
        {
            team = cmd.getOptionValue( "t" );
        }
        else
        {
            team = TEAM_NAME;
        }

        final String mdp;
        if ( cmd.hasOption( "pwd" ) )
        {
            mdp = cmd.getOptionValue( "pwd" );
        }
        else
        {
            mdp = TEAM_PASSWORD;
        }

        return new RestClientAPI( ).getIdEquipe( team, mdp );
    }

    private static void help( Options options )
    {
        new HelpFormatter( ).printHelp( APP_NAME, options );
    }

    private static void pings( )
    {
        System.out.println( "ping : " + restClient.ping( ) );
        System.out.println( "ping : " + restClient.pingError403( ) );
        System.out.println( "ping : " + restClient.pingError500( ) );
    }

    private static Options getOptions( )
    {
        Options options = new Options( );
        Option optionE = Option.builder( "e" ).desc( "Entrainement" ).longOpt( "entrainement" ).numberOfArgs( 1 )
                .type( Integer.class ).build( );

        Option optionM = Option.builder( "m" ).desc( "Match" ).longOpt( "match" ).build( );
        Option optionP = Option.builder( "p" ).desc( "Ping - network test" ).longOpt( "ping" ).build( );
        Option optionH = Option.builder( "h" ).desc( "Help" ).longOpt( "help" ).build( );
        Option optionT = Option.builder( "t" ).desc( "team name (default value \"" + TEAM_NAME + "\")" )
                .longOpt( "team" ).numberOfArgs( 1 ).type( String.class ).build( );
        Option optionPwd = Option.builder( "pwd" ).desc( "team password (default value \"" + TEAM_PASSWORD + "\")" )
                .longOpt( "password" ).numberOfArgs( 1 ).type( String.class ).build( );
        OptionGroup og = new OptionGroup( );
        og.addOption( optionM );
        og.addOption( optionE );
        og.addOption( optionP );
        og.addOption( optionH );
        og.setRequired( true );
        options.addOptionGroup( og );
        options.addOption( optionT );
        options.addOption( optionPwd );
        return options;
    }
}
