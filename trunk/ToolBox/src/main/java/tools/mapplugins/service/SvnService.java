package tools.mapplugins.service;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.tmatesoft.svn.core.SVNNodeKind;

import services.PropertiesService;
import services.svn.SvnFilter;
import tools.mapplugins.commons.MappluginConstants;
import tools.mapplugins.xml.Project;


public class SvnService
{
    /**
     * Get the project object from tje
     * @param url the project pom.xml url
     * @return the project
     * @throws IOException exception while getting the pom from url
     * @throws JAXBException exception while parsing the pom
     */
    public static Project getProject( String url ) throws IOException, JAXBException
    {
        Document pom = Jsoup.connect( url ).get( );
        String pomContent = pom.body( ).children( ).removeAttr( "xmlns" ).removeAttr( "xmlns:xsi" )
                .removeAttr( "xsi:schemalocation" ).toString( );

        JAXBContext jaxbContext = JAXBContext.newInstance( Project.class );
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller( );

        Project unmarshal = (Project) jaxbUnmarshaller
                .unmarshal( new StringReader( pomContent.replaceAll( "[\n ]", "" ) ) );
        return unmarshal;
    }

    /**
     * Init the svn filter
     * @return the filter
     */
    public static SvnFilter getSvnFilter( )
    {
        SvnFilter filter = new SvnFilter( );
        String blackFolders = PropertiesService.getProperty( MappluginConstants.MARK_FOLDER_BLACKLISTED );
        if ( StringUtils.isNotBlank( blackFolders ) )
        {
            for ( String folderName : blackFolders.split( MappluginConstants.PROPERTIES_SEPARATOR ) )
            {
                filter.getBlackList( ).add( folderName );
            }
        }
        filter.setKind( SVNNodeKind.FILE );
        return filter;
    }
}
