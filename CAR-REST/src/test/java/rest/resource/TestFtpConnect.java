package rest.resource;



import org.junit.After;
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
import static org.junit.Assert.assertTrue;

import javax.ws.rs.client.Entity;
import org.junit.Test;

public class TestFtpConnect{
	

	private HttpServer server;
    private WebTarget target, target1;

    @Before
    public void setUp() throws Exception {
    	// start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(Main.BASE_URI+"ftp/connect");
        target1 = c.target(Main.BASE_URI+"ftp/connect");
    }


	@Test
	public void test_connect() {
		Response response = target.request().post(Entity.json("{\n" + 
				"	\"username\":\"anonymous\",\n" + 
				"	\"password\":\"\"\n" + 
				"}"));
		assertEquals("should return status 200", 200, response.getStatus());
		
	}
	@Test
	public void test_connect_user_unknown() {
		Response response = target1.request().post(Entity.json("{\n" + 
				"	\"username\":\"toto\",\n" + 
				"	\"password\":\"m\"\n" + 
				"}"));
		assertEquals("should return status 200", 200, response.getStatus());
		assertTrue(response.readEntity(String.class).equals("Mauvais login/mdp"));
	}
	
	@After
	public void shutdown() {
		server.shutdownNow();
	}

}
