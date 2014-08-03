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
import tools.mapplugins.service.RepositoryService;
import tools.mapplugins.service.SqlService;
import tools.mapplugins.service.SvnService;
import tools.mapplugins.xml.Project;


@Service
public class MappluginTool implements Tool
{
	private static final Logger logger = Logger.getLogger( MappluginTool.class );
	private RepositoryService _repositoryService = new RepositoryService();

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

        Repository repo = null;
        //repo = SerializableService.deserialize( serializableFile );
        if ( repo == null )
        {
            //TODO : use url or local path
            String urlsConfig = PropertiesService.getProperty( MappluginConstants.MARK_SVN_URLS );
            repo = _repositoryService.parseUrl(urlsConfig);
        }

        //SerializableService.serialize( repo, serializableFile );
        
        MavenService.associateDependencies( repo );

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
