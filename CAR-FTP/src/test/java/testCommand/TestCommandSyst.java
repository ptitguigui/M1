package testCommand;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Before;
import org.junit.Test;

public class TestCommandSyst {
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
	 * Test on FTP server ability to respond to the command SYST from FTP Client
	 * 
	 * @throws SocketException
	 * @throws IOException
	 */
	@Test
	public void test() throws SocketException, IOException {
		client.connect(address, port);
		client.login("franco", "123");
		client.syst();
		assertTrue(client.getReplyString().equals("215 Unix system type.\r\n"));
		client.disconnect();
	}

}
