package testCommand;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCommandMKd {

	String address;
	int port;
	FTPClient client;
	File folder;

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
	 * Test on the FTP server ability to create folder after Client commands MKD
	 * 
	 * @throws IOException
	 */
	@Test
	public void test() throws IOException {
		client.connect(address, port);
		client.login("franco", "123");
		client.mkd("test");
		folder = new File(System.getProperty("user.dir") + "/myFTPDirectory/test");
		assertTrue(folder.exists() && folder.isDirectory());

	}

	/**
	 * End the connection of the FTP Client
	 * 
	 * @throws IOException
	 */
	@After
	public void end() throws IOException {
		folder.delete();
		client.disconnect();
	}

}
