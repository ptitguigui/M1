package tp1;

import tp1.server.Server;

import java.io.IOException;

/**
 * Main class to run the FTP server
 * 
 * @author irakoze and lepretre
 *
 */
public class Main {
	
    /**
     * The main function used to run the FTP server
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        try {
            Server server = new Server();
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
