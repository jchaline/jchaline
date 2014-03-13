package services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.InvocationTargetException;
import java.nio.channels.FileChannel;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;


public class ReadWrite
{

    private ReadWrite( )
    {
    }

    /**
     * Read all the line of the file and return the list
     * @param path the path to the file
     * @return the list of the correct line
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static List<String> read( String path )
    {
        List<String> ret = new LinkedList<String>( );
        Scanner scanner = new Scanner( System.in );
        try
        {
            scanner = new Scanner( new File( path ) );
        }
        catch ( FileNotFoundException e )
        {
            e.printStackTrace( );
        }
        while ( scanner.hasNextLine( ) )
        {
            String line = new String( scanner.nextLine( ) );
            ret.add( line );
        }

        scanner.close( );

        return ret;
    }

    /**
     * Create a file in the folder given, with the content given
     * @param filePath the path to the folder
     * @param text the text content
     */
    public static void write( String filePath, String text )
    {
        //on met try si jamais il y a une exception
        BufferedWriter output = null;
        try
        {
            FileWriter fw = new FileWriter( filePath, false );
            // le BufferedWriter output auquel on donne comme argument le FileWriter fw cree juste au dessus
            output = new BufferedWriter( fw );

            //on marque dans le fichier ou plutot dans le BufferedWriter qui sert comme un tampon(stream)
            output.write( text );
            //on peut utiliser plusieurs fois methode write

            output.flush( );
            //ensuite flush envoie dans le fichier, ne pas oublier cette methode pour le BufferedWriter

            output.close( );
        }
        catch ( IOException ioe )
        {
            ioe.printStackTrace( );
        }
    }

    /**
     * Take an url and create all folders needed
     * @param path the path to the last folder to create
     */
    public static void makeFolder( String path )
    {
        File file = new File( path );
        file.mkdirs( );

    }

    /**
     * Delete a recursively a folder and all the content
     */
    public void deleteForlder( )
    {

    }

    /**
     * Find all the file in the directory given
     * @param deep the deep for recursively search. Stop at 0, boundless for
     *            negative parameter (like -1)
     * @param fileList the list of the files
     * @param directoryPath the directory to search recursively
     * @param pattern the pattern the file must match, can be null
     */
    public static void findFiles( int deep, List<String> fileList, String directoryPath, String pattern )
    {
        if ( fileList != null )
        {
            File directory = new File( directoryPath );

            //si le fichier courant n'existe pas, on arrête cette passe
            if ( !directory.exists( ) )
            {
            }
            //si le fichier courant n'est pas un repertoire, on l'ajoute ou non en fonction du respect du pattern
            else if ( !directory.isDirectory( ) )
            {
                boolean matches = Pattern.compile( pattern ).matcher( directoryPath ).matches( );
                if ( pattern == null || matches )
                {
                    fileList.add( directoryPath );
                }
            }
            //sinon, en fonction de la profondeur paramétrée, on descend dans les sous répertoires
            else if ( deep != 0 )
            {
                File[] subfiles = directory.listFiles( );
                for ( int i = 0; i < subfiles.length; i++ )
                {
                    String name = subfiles[i].getName( );
                    String path = directoryPath + "/" + name;
                    findFiles( deep - 1, fileList, path, pattern );
                }
            }
        }
    }

    /**
     * Make a copy of the original file, rename it and insert in the new file the content given at the bigining
     * @param filename the path to the file
     * @param offset the position to insert content
     * @param content the content to insert
     * @throws IOException the exception
     */
    public static void insert( String filename, long offset, byte[] content ) throws IOException
    {
        RandomAccessFile r = new RandomAccessFile( new File( filename ), "rw" );
        RandomAccessFile rtemp = new RandomAccessFile( new File( filename + "~" ), "rw" );
        long fileSize = r.length( );
        FileChannel sourceChannel = r.getChannel( );
        FileChannel targetChannel = rtemp.getChannel( );
        sourceChannel.transferTo( offset, ( fileSize - offset ), targetChannel );
        sourceChannel.truncate( offset );
        r.seek( offset );
        r.write( content );
        long newOffset = r.getFilePointer( );
        targetChannel.position( 0L );
        sourceChannel.transferFrom( targetChannel, newOffset, ( fileSize - offset ) );
        sourceChannel.close( );
        targetChannel.close( );
        r.close( );
        rtemp.close( );
    }
}