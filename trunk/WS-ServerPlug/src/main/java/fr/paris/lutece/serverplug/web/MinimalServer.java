package fr.paris.lutece.serverplug.web;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

import fr.paris.lutece.serverplug.servlet.ReceiveSignalement;

public class MinimalServer
{
    private static final int PORT = 8080;

    public static void main( String[] args ) throws Exception
    {
        Server server = new Server( PORT );
        ServletHandler handler = new ServletHandler( );
        handler.addServletWithMapping( ReceiveSignalement.class, ReceiveSignalement.PATH );//Set the servlet to run.
        server.setHandler( handler );
        server.start( );
        server.join( );
    }
}
