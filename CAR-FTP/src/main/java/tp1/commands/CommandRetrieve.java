package tp1.commands;

import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;
import tp1.utils.RequestMessage;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

public class CommandRetrieve extends Command {
    public CommandRetrieve(DataOutputStream dataOutputStream) {
        super(dataOutputStream);
    }

    public void execute(String clientMessage, ConfigurationClient configClient, ConfigurationServer configServer) throws Exception {
        if (!configClient.isLoggedIn()) {
            this.getRequestMessage().sendMessage(RequestMessage.CODE_530);
        } else {
            this.getRequestMessage().sendMessage(RequestMessage.CODE_125);

            String filename = clientMessage.split(" ")[1];
            Socket transfertSocket = configServer.getTransferServerSocket().accept();
            DataOutputStream dataOutputStream = new DataOutputStream(transfertSocket.getOutputStream());

            File file = new File(configServer.getCurrentDirectory() + "/" + filename);

            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[transfertSocket.getSendBufferSize()];
            int bytesRead = 0;

            while ((bytesRead = fileInputStream.read(buffer)) > 0) {
                dataOutputStream.write(buffer, 0, bytesRead);
            }

            fileInputStream.close();
            dataOutputStream.flush();
            transfertSocket.close();
            this.getRequestMessage().sendMessage(RequestMessage.CODE_226);
        }
    }
}
