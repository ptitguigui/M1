package tp1.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Create a FTP server
 * 
 * @author irakoze & lepretre
 *
 */
public class Server implements Runnable {
	private final int FTP_SERVER_COMMAND_PORT = 1025;
	private final int FTP_SERVER_TRANSFER_PORT = 1026;
	private ServerSocket commandServerSocket;
	private ServerSocket transferServerSocket;
	private boolean running;

	/**
	 * FTP server Builder using two ServerSockets. One to handle the command
	 * from the client and the other to manage all transfer between the server and
	 * the client.
	 * 
	 * @throws Exception
	 */
	public Server() throws Exception {
		this.commandServerSocket = new ServerSocket(FTP_SERVER_COMMAND_PORT, 1, InetAddress.getLocalHost());
		this.transferServerSocket = new ServerSocket(FTP_SERVER_TRANSFER_PORT, 1, InetAddress.getLocalHost());
		this.running = true;
	}

	public ServerSocket getCommandServerSocket() {
		return commandServerSocket;
	}

	public void setCommandServerSocket(ServerSocket commandServerSocket) {
		this.commandServerSocket = commandServerSocket;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		System.out.println("FtpServer started on: " + this.commandServerSocket.getInetAddress().getHostAddress() + ":"
				+ this.commandServerSocket.getLocalPort());
		System.out.println("Waiting client... ");
		try {
			while (this.running) {
				Socket client = this.commandServerSocket.accept();
				new Thread(new RequestHandler(client, this.transferServerSocket)).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.running = false;
		} finally {
				stop();
			
		}
	}
	/**
	 * Stop the server
	 */
	public void stop() {
		try {
			this.commandServerSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
