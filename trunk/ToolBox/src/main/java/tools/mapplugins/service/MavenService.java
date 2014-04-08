package tools.mapplugins.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import tools.mapplugins.bean.ArtifactComparator;
import tools.mapplugins.bean.Repository;
import tools.mapplugins.xml.Project;


public class MavenService
{
    private static final Logger logger = Logger.getLogger( MavenService.class );

    private static final char MARK_PARENTHESE_OPEN = '(';
    private static final char MARK_PARENTHESE_CLOSE = ')';
    private static final char MARK_HOOK_OPEN = '[';
    private static final char MARK_HOOK_CLOSE = ']';
    private static final String MARK_COMMA = ",";

    /**
     * Get the list ordered of the available versions for any artifact
     * @param groupId
     * @param artifactId
     * @return the ordered list of the artifacts
     */
    public static List<String> getListVersionsAvailable( Repository repo, String groupId, String artifactId )
    {
        Map<String, Map<String, Map<String, Project>>> artifacts = repo.getArtifacts( );
        List<String> listVersion = new ArrayList<String>( );
        if ( artifacts.containsKey( groupId ) )
        {
            Map<String, Map<String, Project>> group = artifacts.get( groupId );
            if ( group.containsKey( artifactId ) )
            {
                Map<String, Project> versions = group.get( artifactId );
                Set<String> keySet = versions.keySet( );
                listVersion.addAll( keySet );
                Collections.sort( listVersion, new ArtifactComparator( ) );
            }
        }
        return listVersion;
    }

    /**
     * Get the dependency corresponding to the coordonate
     * @param groupId
     * @param artifactId
     * @param version
     * @return
     */
    public static Project findDependency( Repository repo, String groupId, String artifactId, String version )
    {
        Project dependency = null;

        //traitement d'une plage de version
        if ( MARK_PARENTHESE_OPEN==( version.charAt( 0 ) ) || MARK_HOOK_OPEN==( version.charAt( 0 ) ) )
        {
            List<String> listVersion = getListVersionsAvailable( repo, groupId, artifactId );
            String[] plages = version.split( MARK_COMMA );

            char firstChar = plages[0].charAt( 0 );
            char lastChar = plages[1].charAt( plages[1].length( ) - 1 );

            String minVersion = StringUtils.EMPTY;
            String maxVersion = StringUtils.EMPTY;
            if ( plages[0].length( ) > 1 )
            {
                minVersion = plages[0].substring( 1 );
            }
            else
            {
                minVersion = listVersion.get( 0 );
            }
            if ( plages[1].length( ) > 1 )
            {
                maxVersion = plages[1].substring( 0, plages[1].length( ) - 2 );
            }
            else
            {
                maxVersion = listVersion.get( listVersion.size( ) - 1 );
            }
            logger.debug( "Use minVersion:" + minVersion + ", maxVersion:" + maxVersion );
        }
        else
        {
            dependency = repo.get( groupId, artifactId, version );
        }

        return dependency;
    }
}
