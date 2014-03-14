package tools.mapplugins;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;

import services.PropertiesService;
import services.svn.Svn;
import services.svn.SvnEntry;
import services.svn.SvnFilter;
import tools.Tool;
import tools.mapplugins.bean.Repository;
import tools.mapplugins.commons.MappluginConstants;


@Service
public class MappluginTool implements Tool
{
    private static final String TAG_DEPENDENCY = "dependency";
    private static final Logger logger = Logger.getLogger( MappluginTool.class );

    @Override
    public String getConf( )
    {
        return null;
    }

    @Override
    public int run( )
    {
        Repository repo = new Repository();
        
        String url = PropertiesService.getProperty( MappluginConstants.MARK_SVN_URL );
        Svn svn = new Svn( );
        svn.setUrl( url );

        if ( svn.connect( ) )
        {
            try
            {
                SvnFilter filter = new SvnFilter( );
                filter.getBlackList( ).add( "src" );
                filter.getBlackList( ).add( "webapp" );
                filter.setKind( SVNNodeKind.FILE );

                //get all the pom.xml
                List<SvnEntry> content = svn.list( "", -1, filter );
                logger.info( content.size( ) + " elements founds at " + url );

                for ( SvnEntry element : content )
                {
                    //pour chaque element, obtention et parsing du pom
                    Document pom = Jsoup.connect( url + element.getPath( ) ).get( );

                    //1ere etape, referencer l'artifact courant

                    //2eme etape, obtenir les dependances
                    Elements dependencies = pom.getElementsByTag( TAG_DEPENDENCY );
                    for ( Element dependency : dependencies )
                    {
                        
                    }
                }
            }
            catch ( SVNException e )
            {
                logger.error( "Error : ", e );
            }
            catch ( IOException e )
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
