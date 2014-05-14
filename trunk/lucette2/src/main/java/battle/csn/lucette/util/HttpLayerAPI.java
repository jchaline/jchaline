package battle.csn.lucette.util;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


public class HttpLayerAPI
{

    private Client client;
    private String baseUrl;

    public HttpLayerAPI( String baseUrl )
    {
        client = Client.create( );
        this.baseUrl = baseUrl;
    }

    public WebResource call( String string )
    {
        WebResource webResource = client.resource( baseUrl + string );
        return webResource;
    }

    public String callString( String string )
    {
        WebResource webResource = call( string );
        return webResource.get( String.class );
    }

    public int getStatus( String pING_ERROR_4032 )
    {
        WebResource webResource = this.call( pING_ERROR_4032 );
        ClientResponse response = webResource
                // ajouter ce type de média accepté est necessaire pour avoir
                // une réponse
                // correct du serveur sinon on reçoit 406 "innaceptable"
                .accept( "text/html", "application/xhtml+xml", "application/xml;q=0.9", "image/webp", "*/*;q=0.8" )
                .get( ClientResponse.class );
        return response.getStatus( );
    }
}
