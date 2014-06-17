package battle.csn.lucette.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class IdentifiantServlet
 */
public class IdentifiantServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdentifiantServlet( )
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

        String strReponse = parameters[0].equals( "lucette" )
                && ( parameters.length == 1 || ( parameters.length == 2 && parameters[1].equals( "sopra123" ) ) ) ? ServletUtil.LUCETTE_ID
                : "Essaye lucette/sopra123";
        ServletUtil.writeText( response, strReponse );
        long tempsPasse = System.currentTimeMillis( ) - debut;
        System.out.println( "Time : " + tempsPasse );

    }

}
