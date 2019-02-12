package tp1.commands;

import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;
import tp1.utils.RequestMessage;

import java.io.DataOutputStream;

public class CommandType extends Command {
    public CommandType(DataOutputStream dataOutputStream) {
        super(dataOutputStream);
    }

    public void execute(String clientMessage, ConfigurationClient configClient, ConfigurationServer configServer) {
        if (!configClient.isLoggedIn()) {
            this.getRequestMessage().sendMessage(RequestMessage.CODE_530);
        } else {
            this.getRequestMessage().sendMessage((RequestMessage.CODE_200.replace("DIRECTORY", configServer.getCurrentDirectory())));
        }
    }
}
