package tp1.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private final int FTP_SERVER_COMMAND_PORT = 1025;
    private final int FTP_SERVER_TRANSFER_PORT = 1026;
    private ServerSocket commandServerSocket;
    private ServerSocket transferServerSocket;
    private boolean running;

    public Server() throws Exception {
        this.commandServerSocket = new ServerSocket(FTP_SERVER_COMMAND_PORT);
        this.transferServerSocket = new ServerSocket(FTP_SERVER_TRANSFER_PORT);
        this.running = true;
    }

    public void run() {
        System.out.println("FtpServer started on: " + this.commandServerSocket.getInetAddress().getHostAddress() + ":" + this.commandServerSocket.getLocalPort());
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
            try {
                this.commandServerSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
