package testCommand;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import org.apache.commons.net.ftp.FTPClient;

public class TestCommandPass {
	String address;
	int port;

	@Before
	public void Initialization() throws Exception {
		address = "0.0.0.0";
		port = 1025;
	}

	/**
	 * Test on the server FTP ability to handle user connection if the login is
	 * correct
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLoginTrue() throws Exception {
		FTPClient client = new FTPClient();
		client.connect(address, port);
		boolean isLoggedIn = client.login("franco", "123");
		assertTrue(client.getReplyString().equals("230 User logged in, proceed.\r\n"));
		assertTrue(isLoggedIn);
		client.disconnect();
	}

	/**
	 * Test on the server FTP ability to handle user connection if the login is not
	 * correct
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLoginFalse() throws Exception {
		FTPClient client = new FTPClient();
		client.connect(address, port);
		boolean isLoggedIn = client.login("toto", "125");
		assertTrue(client.getReplyString().equals("530 Not logged in.\r\n"));
		assertFalse(isLoggedIn);
		client.disconnect();
	}

}
