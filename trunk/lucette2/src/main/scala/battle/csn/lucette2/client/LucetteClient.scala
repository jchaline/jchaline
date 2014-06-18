package battle.csn.lucette2.client

import battle.csn.lucette2.network.RestClient
import org.apache.commons.cli.Options
import org.apache.commons.cli.{Option=>OptionApache}


class LucetteClient {
    val TEAM_PASSWORD = "sopra123"
    val TEAM_NAME = "lucette"
    val APP_NAME = "RobotKiller"
    
    
    val restClient = new RestClient( );

    def run( args :String*)
    {
        Options options = getOptions ( );

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

    def pings( )
    {
        println( "ping : " + restClient.ping( ) );
    }

    def getOptions( )=
    {
        var options = new Options( )
        var optionE = OptionApache.builder( "e" ).desc( "Entrainement" ).longOpt( "entrainement" ).numberOfArgs( 1 )
                .type( classOf[Int]).build( );

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