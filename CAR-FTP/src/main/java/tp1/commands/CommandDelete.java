package tp1.commands;

import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;
import tp1.utils.RequestMessage;

import java.io.DataOutputStream;
import java.io.File;

public class CommandDelete extends Command {

    public CommandDelete(DataOutputStream dataOutputStream) {
        super(dataOutputStream);
    }

    public void execute(String clientMessage, ConfigurationClient configClient, ConfigurationServer configServer) throws Exception {
        if (!configClient.isLoggedIn()) {
            this.getRequestMessage().sendMessage(RequestMessage.CODE_530);
            return;
        }

        String fileToDelete = configServer.getCurrentDirectory() + "/" + clientMessage.split(" ")[1];

        if (new File(fileToDelete).delete()) {
            this.getRequestMessage().sendMessage(RequestMessage.CODE_226);
        }
    }
}
