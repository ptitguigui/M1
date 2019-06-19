package testCommand;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Before;
import org.junit.Test;

public class TestCommandReady {
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
	 * Test on FTP server ability to connect to client
	 * 
	 * @throws SocketException
	 * @throws IOException
	 */
	@Test
	public void test() throws SocketException, IOException {
		client.connect(address, port);
		assertTrue(client.getReplyString().equals("220 Service ready for new user.\r\n"));
		client.disconnect();
	}

}
