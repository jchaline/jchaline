package battle.csn.lucette2.network

import java.net.URL
import java.net.Proxy
import java.net.InetSocketAddress

class ConnectionFactory {
    var proxy:Proxy =_

    def initializeProxy( )
    {
        proxy = new Proxy( Proxy.Type.HTTP, new InetSocketAddress( "ptx.proxy.corp.sopra", 8080 ) )
    }

    def getHttpURLConnection(  url :URL)=
    {
        initializeProxy( )
        url.openConnection( proxy )
    }
}