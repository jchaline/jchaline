package tools.mapplugins;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.tmatesoft.svn.core.SVNException;

import services.PropertiesService;
import services.SerializableService;
import services.svn.Svn;
import services.svn.SvnEntry;
import services.svn.SvnFilter;
import tools.Tool;
import tools.mapplugins.bean.Repository;
import tools.mapplugins.commons.MappluginConstants;
import tools.mapplugins.service.MavenService;
import tools.mapplugins.service.SqlService;
import tools.mapplugins.service.SvnService;
import tools.mapplugins.xml.Project;


@Service
public class MappluginTool implements Tool
{
    private static final Logger logger = Logger.getLogger( MappluginTool.class );

    @Override
    public String getConf( )
    {
        StringBuilder builder = new StringBuilder( );
        builder.append( "SVN urls : " + PropertiesService.getProperty( MappluginConstants.MARK_SVN_URLS ) );
        builder.append( "SQL file : " + PropertiesService.getProperty( MappluginConstants.MARK_SQL_FILE ) );
        return builder.toString( );
    }

    @Override
    public int run( )
    {
        String serializableFile = PropertiesService.getProperty( MappluginConstants.MARK_SERIALIZABLE_FILE );
        Repository repo = SerializableService.deserialize( serializableFile );

        if ( repo == null )
        {
            repo = new Repository( );
            String urlsConfig = PropertiesService.getProperty( MappluginConstants.MARK_SVN_URLS );
            String[] urls = urlsConfig.split( ";" );
            for ( String url : urls )
            {
                Svn svn = new Svn( );
                svn.setUrl( url );

                if ( svn.connect( ) )
                {
                    try
                    {
                        SvnFilter filter = SvnService.getSvnFilter( );

                        //get all the pom.xml
                        List<SvnEntry> content = svn.list( "", -1, filter );
                        logger.info( content.size( ) + " elements founds at " + url );

                        for ( SvnEntry element : content )
                        {
                            //pour chaque element, obtention et parsing du pom
                            Project unmarshal = null;
                            try
                            {
                                unmarshal = SvnService.getProject( element.getUrl( ) );

                                //referencer l'artifact courant
                                repo.add( unmarshal );
                            }
                            catch ( JAXBException e )
                            {
                                logger.error( "Error : ", e );
                            }
                            catch ( IOException e )
                            {
                                logger.error( "Error : ", e );
                            }
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
            }
        }

        SerializableService.serialize( repo, serializableFile );
        
        //derniere etape, generer le fichier SQL permettant de créer la bdd
        String sqlPath = PropertiesService.getProperty( MappluginConstants.MARK_SQL_FILE );
        SqlService.generateSqlFile( repo, sqlPath );
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
