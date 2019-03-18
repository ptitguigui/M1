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

public class TestFtpDownloadFolder {

	private HttpServer server;
	private WebTarget target1, target2;

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
		target2 = c.target(Main.BASE_URI + "ftp/downloadDirectory/folderA");
	}

	/**
	 * Test qui valide le téléchargement d'un dossier à travers la passerelle
	 */
	@Test
	public void test_download_folder() {
		target1.request()
				.post(Entity.json("{\n" + "	\"username\":\"anonymous\",\n" + "	\"password\":\"\"\n" + "}"));
		Response response = target2.request().get();
		String output = response.readEntity(String.class);

		assertEquals("should return status 200", 200, response.getStatus());
		assertTrue(output.equals("Le dossier a été télécharger avec succès"));

	}

	/**
	 * Arrête la passerelle
	 */
	@After
	public void shutdown() {
		server.shutdownNow();
	}

}
