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

public class TestFtpUploadFileGet {

	private HttpServer server;
    private WebTarget target, target1, target2, target3, target4;

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
        target2 = c.target(Main.BASE_URI+"ftp/upload/file2.txt");
        target3 = c.target(Main.BASE_URI+"ftp/upload/newFile1.txt");
        target = c.target(Main.BASE_URI+"ftp/removeFile");
        target4 = c.target(Main.BASE_URI+"ftp/disconnect");
    }


	@Test
	public void test_upload_fail() {
		target1.request().post(Entity.json("{\n" + 
				"	\"username\":\"anonymous\",\n" + 
				"	\"password\":\"\"\n" + 
				"}"));
		Response response = target2.request().get();
		String output = response.readEntity(String.class);
		assertEquals("should return status 200", 200, response.getStatus());
		assertTrue(output.equals("Erreur lors de l'upload du fichier. "));
		target4.request().get();
		
	}
	
	@Test
	public void test_upload() {
		target1.request().post(Entity.json("{\n" + 
				"	\"username\":\"anonymous\",\n" + 
				"	\"password\":\"\"\n" + 
				"}"));
		Response response = target3.request().get();
		String output = response.readEntity(String.class);
		assertEquals("should return status 200", 200, response.getStatus());
		assertTrue(output.equals("Fichier upload avec succès. "));
		target.request().post(Entity.json("{\n" + 
				"	\"filename\":\"newFile1.txt\"\n" + 
				"}"));
		target4.request().get();
		
	}
		
	@After
	public void shutdown() {
		server.shutdownNow();
	}


}
