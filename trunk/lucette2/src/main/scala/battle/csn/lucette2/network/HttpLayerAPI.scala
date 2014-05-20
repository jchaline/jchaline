package battle.csn.lucette2.network

import com.sun.jersey.api.client.Client

class HttpLayerAPI(val url:String) {
    var client:Client=_
    
    {
      client = Client.create()
    }
    
    def call(string:String )=
    {
        client.resource( url + string )
    }

    def callString( string :String)=
    {
        call( string ).get( classOf[String] )
    }
}