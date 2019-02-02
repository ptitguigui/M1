package tp1.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    private ServerSocket serverSocket;
    private String directory;
    private boolean running;

    public Server(int port, String directory) throws Exception {
        this.serverSocket = new ServerSocket(port);
        this.directory = directory;
        this.running = true;
    }

    public void run() {
        System.out.println("FtpServer started on: " + this.serverSocket.getInetAddress().getHostAddress() + ":" + this.serverSocket.getLocalPort());
        System.out.println("Waiting client... ");
        try {
            while (this.running) {
                Socket client = this.serverSocket.accept();
                new Thread(new RequestHandler(client)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.running = false;
        } finally {
            try {
                this.serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
