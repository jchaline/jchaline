package tools.mapplugin.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import services.PropertiesService;
import services.SerializableService;
import tools.mapplugins.bean.Repository;
import tools.mapplugins.commons.MappluginConstants;
import tools.mapplugins.service.MavenService;
import tools.mapplugins.xml.Project;


@RunWith( MockitoJUnitRunner.class )
public class MavenServiceTest
{
    Repository repo;

    @Before
    public void init( )
    {
        PropertiesService.init( );
        String serializableFile = PropertiesService.getProperty( MappluginConstants.MARK_SERIALIZABLE_FILE );
        repo = SerializableService.deserialize( serializableFile );
    }

    @Test
    public void resolveVersionTest( ) 
    {
        Project dependencySimple = MavenService.findDependency( repo, "fr.paris.lutece.plugins", "plugin-html",
                "2.1.2-SNAPSHOT" );
        Project dependencyPlage = MavenService.findDependency( repo, "fr.paris.lutece.plugins", "plugin-html",
                "[2.0.2-SNAPSHOT,2.1.3]" );

        assertTrue( dependencySimple != null );
        assertTrue( dependencyPlage != null );
    }
}
