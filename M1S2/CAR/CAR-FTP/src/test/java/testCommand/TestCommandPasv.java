package testCommand;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.InetAddress;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Before;
import org.junit.Test;

public class TestCommandPasv {
	
	String address;
	int port;
	String message;

	FTPClient client;

	/**
	 * Initialization of the FTP client
	 * 
	 * @throws Exception
	 */
	@Before
	public void Initialization() throws Exception {
		String PORT_DIVIDEO = Integer.toString(1026/256);
		String PORT_MODULO =Integer.toString(1026%256);
		String end = ").\r\n";
		String dr = InetAddress.getLocalHost().getHostAddress().replace(".", ",");
		address = InetAddress.getLocalHost().getHostName();
		port = 1025;
		message = "227 Entering Passive Mode ("+dr+ ","+ PORT_DIVIDEO + ","+ PORT_MODULO+ end;
		client = new FTPClient();
	}
	
	/**
	 * Test on FTP server ability to handle passive mode connection
	 * 
	 * @throws IOException
	 */
	@Test
	public void test() throws IOException {
		client.connect(address, port);
		client.login("franco", "123");
		client.enterRemotePassiveMode();
		assertTrue(client.getReplyString().equals(message));
		client.disconnect();
		
	}

}
