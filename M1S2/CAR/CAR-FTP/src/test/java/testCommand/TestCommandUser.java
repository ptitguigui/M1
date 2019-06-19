package testCommand;

import static org.junit.Assert.*;

import java.net.InetAddress;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Before;
import org.junit.Test;

public class TestCommandUser {

	String address;
	int port;

	FTPClient client;

	/**
	 * Initialization of the FTP client
	 * 
	 * @throws Exception
	 */
	@Before
	public void Initialization() throws Exception {
		address = InetAddress.getLocalHost().getHostName();
		port = 1025;
		client = new FTPClient();
	}

	/**
	 * Test on the server FTP ability to verify if the user is known
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUserUnKnown() throws Exception {
		client.connect(address, port);
		client.user("fr");
		String answer = client.getReplyString();
		assertTrue(answer.equals("332 Need account for login.\r\n"));
		client.disconnect();
	}

	/**
	 * Test on the server FTP ability to verify if the user is known
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUserknown() throws Exception {
		client.connect(address, port);
		client.user("franco");
		assertTrue(client.getReplyString().equals("331 User name okay, need password.\r\n"));
		client.disconnect();
	}
}
