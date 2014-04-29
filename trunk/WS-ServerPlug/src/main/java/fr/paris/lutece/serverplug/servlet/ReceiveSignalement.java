package fr.paris.lutece.serverplug.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReceiveSignalement extends HttpServlet
{
    private static final long serialVersionUID = -1950970494228821415L;
    public static final String PATH = "/receiveSignalement";
    
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException
    {
        response.setContentType( "text/html" );
        response.setStatus( HttpServletResponse.SC_OK );
        response.getWriter( ).println( "<h1>Reception du signalement</h1>" );
    }
}
