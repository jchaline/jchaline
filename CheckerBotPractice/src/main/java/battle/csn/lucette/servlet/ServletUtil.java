package battle.csn.lucette.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;


public class ServletUtil
{

    public static String LUCETTE_ID = "00001";

    /**
     * Split the path info string to get all the parameter.
     * @param pathInfo
     * @return
     */
    public static String[] splitPathInfo( String pathInfo )
    {
        System.out.println( "Split of : " + pathInfo );
        return pathInfo.substring( 1, pathInfo.length( ) ).split( "/" );
    }

    public static void writeText( HttpServletResponse response, String text ) throws IOException
    {
        response.getOutputStream( ).write( text.getBytes( ), 0, text.length( ) );
        response.getOutputStream( ).flush( );
    }

}
