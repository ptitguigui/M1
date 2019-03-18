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

public class TestFtpConnect {

	private HttpServer server;
	private WebTarget target, target1;

	/**
	 * Initialise les Webtarget et lance la passerelle
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		// start the server
		server = Main.startServer();
		// create the client
		Client c = ClientBuilder.newClient();

		target = c.target(Main.BASE_URI + "ftp/connect");
		target1 = c.target(Main.BASE_URI + "ftp/connect");
	}

	/**
	 * Test qui valide la connection à travers la passerelle
	 */
	@Test
	public void test_connect() {
		Response response = target.request()
				.post(Entity.json("{\n" + "	\"username\":\"anonymous\",\n" + "	\"password\":\"\"\n" + "}"));
		assertEquals("should return status 200", 200, response.getStatus());

	}

	/**
	 * Test qui valide la gestion d'erreur de login lors de la connection
	 */
	@Test
	public void test_connect_user_unknown() {
		Response response = target1.request()
				.post(Entity.json("{\n" + "	\"username\":\"toto\",\n" + "	\"password\":\"m\"\n" + "}"));
		assertEquals("should return status 200", 200, response.getStatus());
		assertTrue(response.readEntity(String.class).equals("Mauvais login/mdp"));
	}

	/**
	 * Arrête la passerelle
	 */
	@After
	public void shutdown() {
		server.shutdownNow();
	}

}
