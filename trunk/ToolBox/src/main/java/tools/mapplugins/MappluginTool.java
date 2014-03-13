package tools.mapplugins;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.tmatesoft.svn.core.SVNException;

import services.svn.Svn;
import services.svn.SvnEntry;
import tools.Tool;

@Service
public class MappluginTool implements Tool
{
    private static final String URL_SVN = "http://dev.lutece.paris.fr/svn/lutece/portal/";
    private static final Logger logger = Logger.getLogger( MappluginTool.class );

    @Override
    public String getConf( )
    {
        return null;
    }

    @Override
    public int run( )
    {
        Svn svn = new Svn( );
        svn.setUrl( URL_SVN );

        if ( svn.connect( ) )
        {
            try
            {
                List<SvnEntry> content = svn.list( "" ,0);
                logger.info( content.size( )+" elements founds at "+URL_SVN );
                for ( SvnEntry element : content )
                {
                    System.out.println( "Element : " + element.toString( ) );
                }
            }
            catch ( SVNException e )
            {
                logger.error( "Error : ", e );
            }
        }
        else
        {
            logger.error( "Error with connection" );
        }

        return 0;
    }

    @Override
    public String getId( )
    {
        return "mapplugin";
    }

    @Override
    public String getName( )
    {
        return "mapplugin";
    }
}
