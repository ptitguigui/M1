package testCommand;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestCommandPass {
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
	 * Test on the server FTP ability to handle user connection if the login is
	 * correct
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLoginTrue() throws Exception {
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
		client.connect(address, port);
		boolean isLoggedIn = client.login("toto", "125");
		assertTrue(client.getReplyString().equals("530 Not logged in.\r\n"));
		assertFalse(isLoggedIn);
		client.disconnect();
	}

}
