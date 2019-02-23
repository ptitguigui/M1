package testCommand;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.junit.Before;
import org.junit.Test;

public class TestCommandCWD {

	String address;
	int port;
	FTPClient client, client1;

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
		client1 = new FTPClient();
	}

	/**
	 * Test on FTP server ability to handle command CWD, case the directory are
	 * allowed.
	 * 
	 * @throws SocketException
	 * @throws IOException
	 */
	@Test
	public void testCWDWork() throws SocketException, IOException {
		String directory = System.getProperty("user.dir") + "/myFTPDirectory";
		client.connect(address, port);
		client.login("franco", "123");
		client.enterLocalPassiveMode();
		client.listFiles();
		client.cwd(
				"/home/irakoze/Desktop/M1/semestre2/car-lepretre-irakoze/CAR-FTP/myFTPDirectory/folder1/dossierPhoto");
		assertTrue(client.getReplyString()
				.equals("200 OK \r\n"));
		client.cwd("/home/irakoze/Desktop/M1/semestre2/car-lepretre-irakoze/CAR-FTP/myFTPDirectory/folder1");
		assertTrue(client.getReplyString().equals("200 OK \r\n"));
		client.cwd("/home/irakoze/Desktop/M1/semestre2/car-lepretre-irakoze/CAR-FTP/myFTPDirectory");
		assertTrue(client.getReplyString().equals("200 OK \r\n"));
		client.cwd("/home/irakoze/Desktop/M1/semestre2/car-lepretre-irakoze/CAR-FTP/myFTPDirectory/folder1");
		client.cwd("/home/irakoze/Desktop/M1/semestre2/car-lepretre-irakoze/CAR-FTP/myFTPDirectory");
		assertTrue(client.getReplyString().equals("200 OK \r\n"));
		client.disconnect();
	}

	/**
	 * Test on FTP server ability to handle command CWD, case it is not allowed to
	 * access to the directory.
	 * 
	 * @throws SocketException
	 * @throws IOException
	 */
	@Test (expected = FTPConnectionClosedException.class)
	public void testCWDNotWorking() throws SocketException, IOException {
		client1.connect(address, port);
		client1.login("guillaume", "mdp123");
		client1.enterLocalPassiveMode();
		client1.listFiles();
		client1.cwd("/home/irakoze/Desktop/M1/semestre2/car-lepretre-irakoze/CAR-FTP");
		assertTrue(client1.getReplyString().equals("421 Home directory not available - aborting \r\n"));
		client1.cwd("/home/irakoze/Desktop/M1/semestre2/car-lepretre-irakoze/CAR-FTP/myFTPDirectory/test");
		assertTrue(client1.getReplyString().equals("421 Home directory not available - aborting \r\n"));
		client1.disconnect();
	}

}
