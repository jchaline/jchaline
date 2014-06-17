package battle.csn.lucette.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import battle.csn.lucette.controller.Controller;


/**
 * Servlet implementation class PlayServlet
 */
public class PlayServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlayServlet( )
    {
        super( );
    }

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void service( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException
    {
        System.out.println( this.getClass( ).getSimpleName( )+" BoardServlet Service" );
        long debut = System.currentTimeMillis( );
        //Get the extra url
        String pathInfo = request.getPathInfo( );
        String[] parameters = ServletUtil.splitPathInfo( pathInfo );
        String result = Controller.move( parameters[0], parameters[1], parameters[2], parameters[3], parameters[4],
                parameters[5] );
        ServletUtil.writeText( response, result );
        long tempsPasse = System.currentTimeMillis( ) - debut;
        System.out.println( "Time : " + tempsPasse );
    }

}
