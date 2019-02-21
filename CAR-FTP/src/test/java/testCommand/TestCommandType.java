package testCommand;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Before;
import org.junit.Test;

public class TestCommandType {

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
	 * Test on the FTP server ability to handle the command TYPE which set the
	 * Transfert type to binary file
	 * 
	 * @throws SocketException
	 * @throws IOException
	 */
	@Test
	public void test() throws SocketException, IOException {
		String directory = System.getProperty("user.dir") + "/myFTPDirectory";
		client.connect(address, port);
		client.login("franco", "123");
		client.type(FTP.BINARY_FILE_TYPE);
		assertTrue(client.getReplyString().equals("200 Directory changed to \"" + directory + "\"\r\n"));
		client.disconnect();
	}

}
