package rest.resource;

import static org.junit.Assert.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tp2.Main;

public class TestFtpCreateDirectory {

	private HttpServer server;
	private WebTarget target, target1, target2, target3;

	/**
	 * initialise les WebTarget et lance la passerelle
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
		target1 = c.target(Main.BASE_URI + "ftp/create");
		target2 = c.target(Main.BASE_URI + "ftp/remove/folderC");
		target3 = c.target(Main.BASE_URI + "ftp/disconnect");
	}

	/**
	 * Test qui valide la creation d'un dossier à travers la passerelle
	 */
	@Test
	public void test_create() {
		target.request().post(Entity.json("{\n" + "	\"username\":\"anonymous\",\n" + "	\"password\":\"\"\n" + "}"));
		Response response = target1.request().post(Entity.json("{\n" + "	\"filename\":\"folderC\"\n" + "}"));
		String output = response.readEntity(String.class);
		assertEquals("should return status 200", 200, response.getStatus());
		assertTrue(output.equals("Creation du dossier folderC effectué avec succés"));
		target2.request().delete();
		target3.request().get();
	}

	/**
	 * Arrête la passerelle
	 */
	@After
	public void shutdown() {
		server.shutdownNow();
	}

}
