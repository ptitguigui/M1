package testCommand;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCommandPwd {
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
	 * Test on the FTP server ability to respond to the command pwd
	 * @throws SocketException
	 * @throws IOException
	 */
	@Test
	public void test() throws SocketException, IOException {
		FTPClient client = new FTPClient();
		client.connect(address, port);
		client.login("franco", "123");
		client.pwd();
		assertTrue(("257 \""+System.getProperty("user.dir") + "/myFTPDirectory\"\r\n").equals(client.getReplyString()));
		
	}
	
	/**
	 * End the client connection
	 * 
	 * @throws IOException
	 */
	@After
	public void end() throws IOException {
		client.disconnect();
	}
}
