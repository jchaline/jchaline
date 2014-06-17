package battle.csn.lucette.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import battle.csn.lucette.controller.Controller;


/**
 * Servlet implementation class BoardServlet
 */
public class BoardServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardServlet( )
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
        System.out.println( "Begin BoardServlet Service" );
        long debut = System.currentTimeMillis( );
        //Get the extra url
        String pathInfo = request.getPathInfo( );
        String[] parameters = ServletUtil.splitPathInfo( pathInfo );
        String board = Controller.board( parameters[0] );
        ServletUtil.writeText( response, board );
        long tempsPasse = System.currentTimeMillis( ) - debut;
        System.out.println( "Time : " + tempsPasse );
    }

}
