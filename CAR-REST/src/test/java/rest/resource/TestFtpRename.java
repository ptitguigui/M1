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

public class TestFtpRename {

	private HttpServer server;
	private WebTarget target1, target2, target3, target4;

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
		target2 = c.target(Main.BASE_URI + "ftp/rename");
		target3 = c.target(Main.BASE_URI + "ftp/rename");
		target4 = c.target(Main.BASE_URI + "ftp/disconnect");
	}

	/**
	 * Test qui valide le renommage d'un fichier ou un répertoire à travers la
	 * passerelle
	 */
	@Test
	public void test_rename() {
		target1.request()
				.post(Entity.json("{\n" + "	\"username\":\"anonymous\",\n" + "	\"password\":\"\"\n" + "}"));
		Response response = target2.request().put(
				Entity.json("{\n" + "	\"filename\":\"file.txt\",\n" + "	\"newFilename\": \"newFile.txt\"\n" + "}"));
		String output = response.readEntity(String.class);
		assertEquals("should return status 200", 200, response.getStatus());
		assertTrue(output.equals("Modification effectué avec succés"));
		target2.request().put(Entity
				.json("{\n" + "	\"filename\":\"newFile.txt\",\n" + "	\"newFilename\": \"file.txt\"\n" + "}"));
		target4.request().get();

	}

	/**
	 * Arrête la passerelle
	 */
	@After
	public void shutdown() {
		server.shutdownNow();
	}

}
