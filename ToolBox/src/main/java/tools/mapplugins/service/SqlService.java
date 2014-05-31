package tools.mapplugins.service;

import java.util.List;

import org.apache.log4j.Logger;

import services.PropertiesService;
import services.ReadWrite;
import tools.mapplugins.bean.Repository;
import tools.mapplugins.commons.MappluginConstants;
import tools.mapplugins.xml.Project;


public class SqlService
{
    private static final Logger LOGGER = Logger.getLogger( SqlService.class );

    public static void generateSqlFile( Repository repo, String path )
    {
        String strArtifactFirstId = PropertiesService.getProperty( MappluginConstants.MARK_SQL_ARTIFACT_FIRST_ID );
        String strDependenciesFirstId = PropertiesService
                .getProperty( MappluginConstants.MARK_SQL_DEPENDENCIES_FIRST_ID );
        Integer artifactFirstId = Integer.valueOf( strArtifactFirstId );
        Integer dependenciesFirstId = Integer.valueOf( strDependenciesFirstId );

        List<Project> list = repo.getProjectsList( );
        StringBuilder builder = new StringBuilder( );
        for ( Project project : list )
        {
            builder.append( getSqlForProject( artifactFirstId, project ) );
            for ( String dependency : project.getRealDependencies( ) )
            {
                builder.append( getSqlForStrDependency( artifactFirstId, dependenciesFirstId, dependency ) );
                dependenciesFirstId++;
            }
            artifactFirstId++;
        }

        ReadWrite.write( path, builder.toString( ) );
    }

    /**
     * Get the line for the dependency
     * @param dependency the str dependency
     * @return the SQL line
     */
    private static Object getSqlForStrDependency( Integer artifactId, Integer dependencyId, String dependency )
    {
        StringBuilder builder = new StringBuilder( );
        String[] coords = dependency.split( MappluginConstants.ARTIFACT_COORD_SEPARATOR );
        if ( coords.length == 3 )
        {
            builder.append( "INSERT INTO " );
            builder.append( PropertiesService.getProperty( MappluginConstants.MARK_SQL_DEPENDENCIES_TABLE ) );
            builder.append( " VALUES ('" + artifactId + "', '" + dependencyId + "', '" + coords[0] + "', '" + coords[1]
                    + "', '" + coords[2] + "');\n" );
        }
        else
        {
            LOGGER.error( "Wrong number of coords into dependency : " + dependency );
        }
        return builder.toString( );
    }

    /**
     * Get the line for the artifact
     * @param project the artifact
     * @return the SQL line
     */
    private static String getSqlForProject( int currentId, Project project )
    {
        StringBuilder builder = new StringBuilder( );
        builder.append( "INSERT INTO " );
        builder.append( PropertiesService.getProperty( MappluginConstants.MARK_SQL_ARTIFACT_TABLE ) );
        builder.append( " VALUES ('" + currentId + "', '" + project.getArtifactId( ) + "', '" + project.getGroupId( )
                + "', '" + project.getVersion( ) + "');\n" );
        return builder.toString( );
    }
}
