package testCommand;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Before;
import org.junit.Test;

import tp1.server.Server;

public class TestCommandDelete {

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
	 * Test FTP server on deleting a file which does not exist
	 * 
	 * @throws SocketException
	 * @throws IOException
	 */
	@Test
	public void test() throws SocketException, IOException {

		client.connect(address, port);
		client.login("franco", "123");
		client.enterRemotePassiveMode();
		client.dele(
				"/home/irakoze/Desktop/M1/semestre2/car-lepretre-irakoze/CAR-FTP/myFTPDirectory/folder1/fileee1.txt");
		String response = client.getReplyString();
		assertTrue(response.equals("550 No such file or directory.\r\n"));
		client.disconnect();
	}

	/**
	 * Test FTP server on deleting an existing file
	 * 
	 * @throws SocketException
	 * @throws IOException
	 */
	@Test
	public void testDeleteFileExist() throws SocketException, IOException {
		client1.connect(address, port);
		client1.login("guillaume", "mdp123");
		client1.setFileType(FTP.BINARY_FILE_TYPE);
		client1.setFileType(FTP.BINARY_FILE_TYPE);
		client1.enterLocalPassiveMode();
		FileInputStream file = new FileInputStream(new File(System.getProperty("user.dir") + "/fileConf.txt"));
		client1.storeFile("file.txt", file);
		client1.enterLocalPassiveMode();
		client1.dele(System.getProperty("user.dir")+"/myFTPDirectory/file.txt");
		assertTrue(client1.getReplyString().equals("250 Requested file action okay, completed. \r\n"));
		client1.disconnect();
	}

}
