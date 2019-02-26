package com.example.rest;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.net.ftp.FTPClient;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

	private String address;
	private int port;
	private FTPClient client;

	public MyResource() throws Exception {
		this.address = InetAddress.getLocalHost().getHostName();
		this.port = 1025;
		this.client = new FTPClient();
	}

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to the
	 * client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 * @throws IOException
	 * @throws Exception
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() throws Exception {
		this.client.connect(address, port);
		this.client.user("fr");
		String answer = client.getReplyString();
		this.client.disconnect();

		return answer;
	}
}
