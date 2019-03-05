package com.example.rest;

import java.io.IOException;
import java.net.InetAddress;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.net.ftp.FTPClient;

/**
 * Root ftp (exposed at "ftpRessource" path)
 */
@Path("ftp")
public class FtpRessource {
	private String address;
	private int port;
	private String username;
	private String password;
	private FTPClient client;

	public FtpRessource() throws Exception {
		this.address = InetAddress.getLocalHost().getHostName();
		this.port = 1025;
		this.client = new FTPClient();

	}

	@POST
	@Path("connect")
	@Consumes(MediaType.APPLICATION_JSON)
	public String connect(User user) throws IOException {
		try {
			this.client.connect(address, port);
			this.username = user.getUsername();
			this.password = user.getPassword();
			this.client.login(this.username, this.password);
		} catch (Exception e) {
			this.client.disconnect();
			e.printStackTrace();
		}
		return this.client.getReplyString();
	}

	@GET
	@Path("syst")
	public String doSyst() throws IOException {
		try {
			this.client.connect(address, port);
			this.client.login(this.username, this.password);
			this.client.syst();
		} catch (Exception e) {
			this.client.disconnect();
			e.printStackTrace();
		}
		return this.client.getReplyString();
	}

}
