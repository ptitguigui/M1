package rest.resource;

import static org.junit.Assert.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tp2.Main;

public class TestFtpDisconnect {

	private HttpServer server;
    private WebTarget target1, target2;


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

        target1 = c.target(Main.BASE_URI+"ftp/connect");
        target2 = c.target(Main.BASE_URI+"ftp/disconnect");
    }


	@Test
	public void test_disconnect() {
		target1.request().post(Entity.json("{\n" + 
				"	\"username\":\"guillaume\",\n" + 
				"	\"password\":\"mdp123\"\n" + 
				"}"));
		Response response = target2.request().get();
		String output = response.readEntity(String.class);
		
		assertEquals("should return status 200", 200, response.getStatus());
		assertTrue(output.equals("Client déconnecté"));
		
	}
	@Test
	public void test_disconnect_when_not_connected() {
		Response response = target2.request().get();
		String output = response.readEntity(String.class);
		
		assertEquals("should return status 200", 200, response.getStatus());
		assertTrue(output.equals("Le client n'est pas connecté"));
		
	}
	
	@After
	public void shutdown() {
		server.shutdownNow();
	}


}
