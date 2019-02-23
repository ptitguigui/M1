package testCommand;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Before;
import org.junit.Test;

public class TestCommandDelete {
	
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
	 * Test FTP server on deleting a file which not exist
	 * 
	 * @throws SocketException
	 * @throws IOException
	 */
	@Test
	public void test() throws SocketException, IOException {
		client.connect(address, port);
		client.login("franco", "123");
		client.enterLocalPassiveMode();
		client.dele("/home/irakoze/Desktop/M1/semestre2/car-lepretre-irakoze/CAR-FTP/myFTPDirectory/folder1/fileee1.txt");
		assertTrue(client.getReplyString().equals("550 No such file or directory. \r\n"));
		client.disconnect();
	}

}
