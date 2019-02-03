package tp1;

import tp1.server.Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            Server server = new Server(1025);
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
