package battle.csn.lucette.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import battle.csn.lucette.controller.Controller;


/**
 * Servlet implementation class StatusServlet
 */
public class StatusServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatusServlet( )
    {
        super( );
    }

    protected void service( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException
    {
        System.out.println( this.getClass( ).getSimpleName( ) + " BoardServlet Service" );
        long debut = System.currentTimeMillis( );
        //Get the extra url
        String pathInfo = request.getPathInfo( );
        String[] parameters = ServletUtil.splitPathInfo( pathInfo );
        String status = Controller.status( parameters[0], parameters[1] );
        ServletUtil.writeText( response, status );
        long tempsPasse = System.currentTimeMillis( ) - debut;
        System.out.println( "Time : " + tempsPasse + "(status : " + status + ")" );
    }

}
