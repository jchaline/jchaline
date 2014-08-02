package tools.mapplugins;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.JAXBException;

import org.apache.commons.lang.StringUtils;
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
    private static final String PREFIX_HTTP = "http";
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
            //TODO : use url or local path
            String urlsConfig = PropertiesService.getProperty( MappluginConstants.MARK_SVN_URLS );
            updateRepository(repo, urlsConfig);
        }

        SerializableService.serialize( repo, serializableFile );
        
        MavenService.associateDependencies( repo );

        //derniere etape, generer le fichier SQL permettant de créer la bdd
        String sqlPath = PropertiesService.getProperty( MappluginConstants.MARK_SQL_FILE );
        SqlService.generateSqlFile( repo, sqlPath );
        return 0;
    }

    /**
     * For all path configure, update repo with url or local folder/file
     * @param repo the repository to update
     * @param pathConfig the path configured
     */
	private void updateRepository(Repository repo, String pathConfig) {
		String[] urls = pathConfig.split(";");
		for (String url : urls) {
			if (isUrl(url)) {
				updateRepoWithLocalPath(repo, url);
			} else {
				updateRepoWithSVNUrl(repo, url);
			}
		}
	}

    private boolean isUrl(String url) {
		return StringUtils.isNotBlank(url) && url.startsWith(PREFIX_HTTP);
	}

    /**
     * Check path (distant url or local path)
     * @param repo the repo to update
     * @param path the path
     */
    private void updateRepoWithLocalPath(Repository repo, String path) {
    	Svn svn = new Svn( );
    	svn.setUrl( path );
    	if ( svn.connect( ) )
    	{
    		try
    		{
    			SvnFilter filter = SvnService.getSvnFilter( );
    			
    			//get all the pom.xml
    			List<SvnEntry> content = svn.list( "", -1, filter );
    			logger.info( content.size( ) + " elements founds at " + path );
    			
    			for ( SvnEntry element : content )
    			{
    				//pour chaque element, obtention et parsing du pom
    				Project unmarshal = null;
    				try
    				{
    					unmarshal = SvnService.getProject( element.getUrl( ) );
    					MavenService.correctProject( unmarshal );
    					
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
	/**
     * Update repository with the given svn url
     * @param repo the repo to update
     * @param url the svn url
     */
	private void updateRepoWithSVNUrl(Repository repo, String url) {
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
		                MavenService.correctProject( unmarshal );

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
