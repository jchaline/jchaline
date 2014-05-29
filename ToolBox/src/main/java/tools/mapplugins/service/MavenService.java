package tools.mapplugins.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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
     * @return the dependency
     */
    public static Project findDependency( Repository repo, String groupId, String artifactId, String version )
    {
        Project dependency = null;

        //traitement d'une plage de version
        if ( MARK_PARENTHESE_OPEN == ( version.charAt( 0 ) ) || MARK_HOOK_OPEN == ( version.charAt( 0 ) ) )
        {
            dependency = findDependencyWithRange( repo, groupId, artifactId, version );
        }
        else
        {
            dependency = repo.get( groupId, artifactId, version );
        }

        return dependency;
    }

    /**
     * Get the dependency corresponding to the coordonate
     * @param groupId the group id
     * @param artifactId the artifact id
     * @param version the range version
     * @return the dependency
     */
    private static Project findDependencyWithRange( Repository repo, String groupId, String artifactId, String version )
    {
        Project p = null;
        List<String> listVersions = getListVersionsAvailable( repo, groupId, artifactId );
        String[] plages = version.split( MARK_COMMA );

        char firstChar = plages[0].charAt( 0 );
        char lastChar = plages[1].charAt( plages[1].length( ) - 1 );

        //get the using min and max version
        String minVersion = plages[0].length( ) > 1 ? plages[0].substring( 1 ) : listVersions.get( 0 );
        String maxVersion = plages[1].length( ) > 1 ? plages[1].substring( 0, plages[1].length( ) - 1 ) : listVersions
                .get( listVersions.size( ) - 1 );

        String v = searchVersion( listVersions, minVersion, maxVersion, firstChar, lastChar );
        logger.debug( "Use minVersion:" + minVersion + ", maxVersion:" + maxVersion );
        if ( StringUtils.isNotBlank( v ) )
        {
            p = repo.get( groupId, artifactId, v );
        }

        return p;
    }

    /**
     * Search the greatest version
     * @param listVersions the list to search
     * @param min the mininum version accepted
     * @param max the maximum version accepted
     * @param bornMin the min born
     * @param bornMax the max born
     * @return the version
     */
    private static String searchVersion( List<String> listVersions, String minVersion, String maxVersion,
            char firstChar, char lastChar )
    {
        Collections.sort( listVersions, new ArtifactComparator( ) );
        Collections.reverse( listVersions);
        boolean find = false;
        String version = null;
        Iterator<String> itr = listVersions.iterator( );
        while ( itr.hasNext( ) && !find )
        {
            String v = itr.next( );
            int compare = ArtifactComparator.compareStatic( v, maxVersion);
            if(compare<=0){
                if(compare<0 || lastChar==MARK_HOOK_CLOSE ){
                    version = v;
                    find = true;
                }
            }
        }

        return version;
    }
}
