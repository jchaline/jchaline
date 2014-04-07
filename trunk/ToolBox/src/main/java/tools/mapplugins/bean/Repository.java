package tools.mapplugins.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import tools.mapplugins.xml.Project;


/**
 * Handling artifacts
 * @author jchaline
 */
public class Repository implements Serializable
{
    private static final long serialVersionUID = 8115463804253023495L;
    /**
     * Reference all the artifact, getting by group id, id, and version
     */
    private Map<String, Map<String, Map<String, Project>>> artifacts = new HashMap<String, Map<String, Map<String, Project>>>( );

    /**
     * Get all the artifacts
     * @return the artifacts list
     */
    public List<Project> getProjectsList( )
    {
        List<Project> list = new ArrayList<Project>( );
        for ( Map<String, Map<String, Project>> groups : artifacts.values( ) )
        {
            for ( Map<String, Project> versions : groups.values( ) )
            {
                list.addAll( versions.values( ) );
            }
        }

        return list;
    }

    /**
     * The way to add artifact to repository
     * @param artifact the artifact to add
     */
    public void add( Project artifact )
    {
        if ( !artifacts.containsKey( artifact.getGroupId( ) ) )
        {
            artifacts.put( artifact.getGroupId( ), new HashMap<String, Map<String, Project>>( ) );
        }
        if ( !artifacts.get( artifact.getGroupId( ) ).containsKey( artifact.getArtifactId( ) ) )
        {
            artifacts.get( artifact.getGroupId( ) ).put( artifact.getArtifactId( ), new HashMap<String, Project>( ) );
        }
        artifacts.get( artifact.getGroupId( ) ).get( artifact.getArtifactId( ) ).put( artifact.getVersion( ), artifact );
    }

    /**
     * Get exactly artifact with coordonates
     * @param groupId
     * @param artifactId
     * @param version
     * @return
     */
    public Project get( String groupId, String artifactId, String version )
    {
        Project project = null;
        if ( artifacts.containsKey( groupId ) && artifacts.get( groupId ).containsKey( artifactId ) )
        {
            project = artifacts.get( groupId ).get( artifactId ).get( version );
        }
        return project;
    }

    /**
     * Get the dependency corresponding to the coordonate
     * @param groupId
     * @param artifactId
     * @param version
     * @return
     */
    public Project findDependency( String groupId, String artifactId, String version )
    {
        Project dependency = null;
        List<String> listVersion = getListVersionsAvailable( groupId, artifactId );

        if ( !listVersion.isEmpty( ) )
        {
            dependency = get( groupId, artifactId, listVersion.get( 0 ) );
        }

        return dependency;
    }

    /**
     * Get the list ordered of the available versions for any artifact
     * @param groupId
     * @param artifactId
     * @return the ordered list of the artifacts
     */
    private List<String> getListVersionsAvailable( String groupId, String artifactId )
    {
        List<String> listVersion = new ArrayList<String>( );
        if ( this.artifacts.containsKey( groupId ) )
        {
            Map<String, Map<String, Project>> group = this.artifacts.get( groupId );
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
}
