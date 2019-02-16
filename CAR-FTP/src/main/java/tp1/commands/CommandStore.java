package tp1.commands;

import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;
import tp1.utils.RequestMessage;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;

public class CommandStore extends Command {
    public CommandStore(DataOutputStream dataOutputStream) {
        super(dataOutputStream);
    }

    public void execute(String clientMessage, ConfigurationClient configClient, ConfigurationServer configServer) throws Exception {
        if (!configClient.isLoggedIn()) {
            this.getRequestMessage().sendMessage(RequestMessage.CODE_530);
            return;
        }

        this.getRequestMessage().sendMessage(RequestMessage.CODE_125);

        String filename = clientMessage.split(" ")[1];
        Socket transferSocket = configServer.getTransferServerSocket().accept();
        InputStream in = transferSocket.getInputStream();

        File file = new File(configServer.getCurrentDirectory() + "/" + filename);

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byte[] buffer = new byte[transferSocket.getReceiveBufferSize()];
        int bytesRead = 0;

        while ((bytesRead = in.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, bytesRead);
        }

        fileOutputStream.close();
        fileOutputStream.flush();
        transferSocket.close();
        this.getRequestMessage().sendMessage(RequestMessage.CODE_226);
    }
}
