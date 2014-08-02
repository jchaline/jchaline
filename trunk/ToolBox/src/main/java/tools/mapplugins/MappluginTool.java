package tools.mapplugins;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;
import org.tmatesoft.svn.core.SVNException;

import services.FileService;
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
        String serializableFile = PropertiesService.getProperty( MappluginConstants.MARK_SERIALIZABLE_FILE +"e");
        Repository repo = SerializableService.deserialize( serializableFile );

        if ( repo == null )
        {
            repo = new Repository( );
            //TODO : use url or local path
            String urlsConfig = PropertiesService.getProperty( MappluginConstants.MARK_SVN_URLS );
            repo.addAll(updateRepository(urlsConfig));
        }

        //SerializableService.serialize( repo, serializableFile );
        
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
	private List<Project> updateRepository(String pathConfig) {
		List<Project> projects = new ArrayList<Project>();
		String[] urls = pathConfig.split(";");
		for (String url : urls) {
			if (isUrl(url)) {
				projects.addAll(updateRepoWithSVNUrl(url));
			} else {
				projects.addAll(updateRepoWithLocalPath(url));
			}
		}
		return projects;
	}

    private boolean isUrl(String url) {
		return StringUtils.isNotBlank(url) && url.startsWith(PREFIX_HTTP);
	}

    /**
     * Check path (distant url or local path)
     * @param repo the repo to update
     * @param repoPath the path
     */
    private List<Project> updateRepoWithLocalPath(String repoPath) {
    	List<Project> projects = new ArrayList<Project>();
    	List<String> listPaths = FileService.findFiles(-1, repoPath, ".*/pom\\.xml$");
    	logger.debug("Find "+listPaths.size()+" poms");
    	for(String pomPath : listPaths){
    		List<String> lines = FileService.read(pomPath);
    		String pomContent = StringUtils.join(lines, "\n");
    		pomContent = Jsoup.parse(pomContent).body( ).children( ).removeAttr( "xmlns" ).removeAttr( "xmlns:xsi" )
            .removeAttr( "xsi:schemalocation" ).toString( );
    		try {
    			projects.add(MavenService.getProject(pomContent));
			} catch (JAXBException e) {
				logger.error(e);
			}
    		logger.debug(pomContent.length());
    	}
    	return projects;
    }
	/**
     * Update repository with the given svn url
     * @param repo the repo to update
     * @param url the svn url
     */
	private List<Project> updateRepoWithSVNUrl(String url) {
		List<Project> projects = new ArrayList<Project>();
		
		Svn svn = new Svn( );
		svn.setUrl( url );
		if ( svn.connect( ) )
		{
		    try
		    {
		        SvnFilter filter = SvnService.getSvnFilter( );

		        //get all the pom.xml
		        List<SvnEntry> svnEntries = svn.list( "", -1, filter );
		        logger.info( svnEntries.size( ) + " elements founds at " + url );

		        for ( SvnEntry entry : svnEntries )
		        {
		            //pour chaque element, obtention et parsing du pom
		            Project unmarshal = null;
		            try
		            {
		            	String content = SvnService.getProjectContent( entry.getUrl( ) );
		                unmarshal = MavenService.getProject(content);
		                MavenService.correctProject( unmarshal );

		                //referencer l'artifact courant
		                projects.add( unmarshal );
		            }
		            catch ( IOException | JAXBException e )
		            {
		                logger.error( e );
		            }
		        }
		    }
		    catch ( SVNException e )
		    {
		        logger.error( e );
		    }
		}
		else
		{
		    logger.error( "Error with connection, can't connect to "+url );
		}
		return projects;
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
