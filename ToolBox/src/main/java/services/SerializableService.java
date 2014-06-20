package services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;


public class SerializableService
{

    private static final Logger logger = Logger.getLogger( SerializableService.class );

    public static <T> void serialize( T object, String path )
    {
        try
        {
            FileOutputStream outputFile = new FileOutputStream( path );
            ObjectOutputStream oos = new ObjectOutputStream( outputFile );
            oos.writeObject( object );
            oos.flush( );
            oos.close( );
        }
        catch ( IOException e )
        {
            logger.error( "Error while create serializable file in " + path );
        }
    }

    public static <T> T deserialize( String path )
    {
        T result = null;
        FileInputStream inputFile = null;
        try
        {
            inputFile = new FileInputStream( path );
            ObjectInputStream ois = new ObjectInputStream( inputFile );
            result = (T) ois.readObject( );
            ois.close( );
            inputFile.close( );
        }
        catch ( FileNotFoundException e1 )
        {
            logger.info( "File " + path + " not exist, ask SVN repos" );
        }
        catch ( IOException e )
        {
            logger.info( "File " + path + " throw error." );
        }
        catch ( ClassNotFoundException e )
        {
            logger.info( "File " + path + " throw error." );
        }
        return result;
    }
}