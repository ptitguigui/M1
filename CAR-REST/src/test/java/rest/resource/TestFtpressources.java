package rest.resource;



import org.junit.Before;
import org.junit.Test;

import tp2.Main;
import tp2.resource.FtpResource;
import javax.ws.rs.core.Response;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;


import org.glassfish.grizzly.http.server.HttpServer;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import org.junit.Test;

public class TestFtpressources{
	

	private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(Main.BASE_URI+"connect");
    }


	@Test
	public void test() {
		Response response = target.request().post(Entity.json("{\n" + 
				"	\"username\":\"guillaume\",\n" + 
				"	\"password\":\"mdp123\"\n" + 
				"}"));
		assertEquals("should return status 200", 200, response.getStatus());
//		Response res = given().contentType(ContentType.JSON).body("{\n" + 
//				"	\"username\":\"guillaume\",\n" + 
//				"	\"password\":\"mdp123\"\n" + 
//				"}").
//	    when().
//	        get("http://127.0.1.1:8080/myapp/ftp/connect");
//		String body = res.getBody().asString();
//		assertTrue(body.equals("Client connecté"));
	}

}
