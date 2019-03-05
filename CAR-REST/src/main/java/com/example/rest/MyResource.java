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

	

	public MyResource() {
		
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
		return "Got it!";
	}
}
