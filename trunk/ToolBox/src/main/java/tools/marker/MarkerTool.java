package tools.marker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import services.FileService;
import services.PropertiesService;
import tools.Tool;
import utils.ToolsConstants;

@Service
public final class MarkerTool implements Tool
{
    private static final Logger logger = Logger.getLogger( MarkerTool.class );
    private static final String TOOL_ID = "marker";
    private static final String TOOL_NAME = "Injecteur de marque";

    /**
     * For each filepath given, add a mark to recognize it
     * @param filePath the path to the file
     */
    public static void appendMark( String filePath )
    {
        FileWriter writer = null;
        String texteBegin = "<!-- " + PropertiesService.getProperty( "marker.mark" ) + " BEGIN " + filePath + " -->";
        String texteEND = "<!-- " + PropertiesService.getProperty( "marker.mark" ) + " END " + filePath + " -->";
        RandomAccessFile randomAccesFile = null;
        try
        {
        	FileService.insert( filePath, 0, texteBegin.getBytes( ) );
            //            randomAccesFile = new RandomAccessFile( new File( filePath ), "rw" );
            //            randomAccesFile.seek( 0 ); // to the beginning
            //            randomAccesFile.write( texteBegin.getBytes( ) );

            writer = new FileWriter( filePath, true );
            writer.write( texteEND, 0, texteEND.length( ) );
        }
        catch ( IOException e )
        {
            logger.error(e);
        }
        finally
        {
            if ( writer != null )
            {
                try
                {
                    writer.close( );
                }
                catch ( IOException e )
                {
                	logger.error(e);
                }
            }
            if ( randomAccesFile != null )
            {
                try
                {
                    randomAccesFile.close( );
                }
                catch ( IOException e )
                {
                	logger.error(e);
                }
            }
        }
    }

    @Override
    public int run( )
    {
        logger.info( "Run tool : " + getName( ) );
        PropertiesService.init( );
        String filePatternFilter = PropertiesService.getProperty( "marker.templates.pattern" );
        logger.debug( "Use pattern : " + filePatternFilter );
        String pathFiles = PropertiesService.getProperty( "marker.templates.path" );
        logger.debug( "File path : " + pathFiles );

        //configuration test
        if ( StringUtils.isBlank( filePatternFilter ) || StringUtils.isBlank( pathFiles ) )
        {
            logger.error( "You must configure the pattern and the path for the file, into the marker.properties file" );
            return ToolsConstants.STATUS_ERROR;
        }

        //environnement test
        if ( !(new File( pathFiles ).exists( )) )
        {
            logger.error( pathFiles
                    + " isn't correct, you must set a valide path for the files, into marker.properties : marker.templates.path" );
            return ToolsConstants.STATUS_ERROR;
        }

        List<String> listFiles = FileService.findFiles( -1, pathFiles, filePatternFilter );

        logger.debug( "# files found : " + listFiles.size( ) );
        for ( String file : listFiles )
        {
            appendMark( file );
        }
        logger.debug( " Files appends. " );

        return ToolsConstants.STATUS_OK;
    }

    @Override
    public String getConf( )
    {
        return "no conf set";
    }

    @Override
    public String getId( )
    {
        return TOOL_ID;
    }

    @Override
    public String getName( )
    {
        return TOOL_NAME;
    }
}
