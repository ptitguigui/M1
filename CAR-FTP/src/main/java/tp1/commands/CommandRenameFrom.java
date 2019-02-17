package tp1.commands;

import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;
import tp1.utils.RequestMessage;

import java.io.DataOutputStream;

public class CommandRenameFrom extends Command {
    public CommandRenameFrom(DataOutputStream dataOutputStream) {
        super(dataOutputStream);
    }

    public void execute(String clientMessage, ConfigurationClient configClient, ConfigurationServer configServer) throws Exception {
        if (!configClient.isLoggedIn()) {
            this.getRequestMessage().sendMessage(RequestMessage.CODE_530);
        } else {

            String filename = clientMessage.split(" ")[1];
            configClient.setCurrentFile(filename);
            this.getRequestMessage().sendMessage(RequestMessage.CODE_350);
        }
    }
}
