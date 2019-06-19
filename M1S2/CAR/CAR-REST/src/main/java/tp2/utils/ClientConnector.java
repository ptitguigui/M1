package tp2.utils;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;

public class ClientConnector {

    private static String address;
    private static int port;
    private static FTPClient FTP_CLIENT;
    private static ClientConnector client = new ClientConnector();

    private ClientConnector() {
    }

    public void connect(String username, String password) throws IOException {
        FTP_CLIENT.connect(address, port);
        int reply = FTP_CLIENT.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            this.close();
            throw new IOException("Exception in connecting to FTP Server");
        }
        FTP_CLIENT.login(username, password);
    }

    public void close() throws IOException {
        FTP_CLIENT.disconnect();
    }

    public static ClientConnector getClientConnector() {
        return client;
    }

    public FTPClient getFTPClient() {
        ConfigClient configClient = new ConfigClient();
        address = configClient.getAddress();
        port = configClient.getPort();
        if (FTP_CLIENT == null) {
            FTP_CLIENT = new FTPClient();
        }
        return FTP_CLIENT;
    }
}
