package battle.csn.lucette.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import battle.csn.lucette.controller.Controller;


/**
 * Servlet implementation class NextPartieServlet
 */
public class NextPartieServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NextPartieServlet( )
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
        String partie = Controller.next( parameters[0] );
        ServletUtil.writeText( response, partie );
        long tempsPasse = System.currentTimeMillis( ) - debut;
        System.out.println( "Time : " + tempsPasse );
    }

}
