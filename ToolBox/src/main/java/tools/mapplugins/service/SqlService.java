package tools.mapplugins.service;

import java.util.List;

import services.PropertiesService;
import services.ReadWrite;
import tools.mapplugins.bean.Repository;
import tools.mapplugins.commons.MappluginConstants;
import tools.mapplugins.xml.Project;


public class SqlService
{
    public static void generateSqlFile( Repository repo, String path )
    {
        String strFirstId = PropertiesService.getProperty( MappluginConstants.MARK_SQL_FIRST_ID );
        Integer firstId = Integer.valueOf( strFirstId );
        List<Project> list = repo.getProjectsList( );
        StringBuilder builder = new StringBuilder( );
        for ( Project project : list )
        {
            builder.append( getSqlForProject( firstId++, project ) );
        }

        ReadWrite.write( path, builder.toString( ) );
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
