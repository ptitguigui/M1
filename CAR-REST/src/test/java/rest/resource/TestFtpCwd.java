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

public class TestFtpCwd {
	private HttpServer server;
	private WebTarget target1, target2, target3;

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
		target1 = c.target(Main.BASE_URI + "ftp/connect");
		target2 = c.target(Main.BASE_URI + "ftp/cwd/folder1");
		target3 = c.target(Main.BASE_URI + "ftp/cwd/folder2");
	}

	/**
	 * Test qui valide la commande cwd à travers la passerelle
	 */
	@Test
	public void test_cwd() {
		target1.request()
				.post(Entity.json("{\n" + "	\"username\":\"anonymous\",\n" + "	\"password\":\"\"\n" + "}"));
		Response response = target2.request().get();
		String output = response.readEntity(String.class);
		assertEquals("should return status 200", 200, response.getStatus());
		assertTrue(output.equals("Déplacer vers folder1"));

	}

	/**
	 * Test qui valide la gestion d'erreur lors d'une commande cwd à travers la
	 * passerelle
	 */
	@Test
	public void test_cwd_error() {
		target1.request()
				.post(Entity.json("{\n" + "	\"username\":\"anonymous\",\n" + "	\"password\":\"\"\n" + "}"));
		Response response = target3.request().get();
		String output = response.readEntity(String.class);
		assertEquals("should return status 200", 200, response.getStatus());
		assertTrue(output.equals("Erreur lors du déplacement du répertoire folder2"));

	}

	/**
	 * Arrête la passerelle
	 */
	@After
	public void shutdown() {
		server.shutdownNow();
	}

}
