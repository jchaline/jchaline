package battle.csn.lucette.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import battle.csn.lucette.controller.Controller;


/**
 * Servlet implementation class NewPartieServlet
 */
public class NewPartieServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewPartieServlet( )
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
        //Get the extra url
        String pathInfo = request.getPathInfo( );
        String[] parameters = ServletUtil.splitPathInfo( pathInfo );
        Integer newPartie = Controller.newPartie( parameters[1], parameters[0] );
        ServletUtil.writeText( response, newPartie.toString( ) );
        System.out.println( this.getClass( ).getSimpleName( ) + " : get id new game" );
    }

}
