package testCommand;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.InetAddress;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class TestCommandList {

	String address;
	int port;
	FTPClient client;
	String listexpected = "drwxr-xr-x 1 irakoze irakoze 4096 Feb 17 20:59 folder1\n"
			+ "-rw-r--r-- 1 irakoze irakoze 26 Feb 17 20:59 fileee1.txt";

	@Before
	public void Initialization() throws Exception {
		address = InetAddress.getLocalHost().getHostName();
		port = 1025;
		client = new FTPClient();
	}

	@Test
	public void test() throws IOException {
		client.connect(address, port);
		client.login("franco", "123");
		client.enterLocalPassiveMode();
		FTPFile[] reString = client.listFiles();
		for (FTPFile file : reString) {
			System.out.println(file.getName());
			assertTrue(listexpected.contains(file.getName()));
		}
	}

	@After
	public void end() throws IOException {
		client.disconnect();
	}

}
