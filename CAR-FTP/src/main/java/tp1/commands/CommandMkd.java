package tp1.commands;

import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;
import tp1.utils.RequestMessage;

import java.io.DataOutputStream;
import java.io.File;

public class CommandMkd extends Command {
    public CommandMkd(DataOutputStream dataOutputStream) {
        super(dataOutputStream);
    }

    public void execute(String clientMessage, ConfigurationClient configClient, ConfigurationServer configServer) throws Exception {
        if (!configClient.isLoggedIn()) {
            this.getRequestMessage().sendMessage(RequestMessage.CODE_530);
        } else {
            String newDirectory = configServer.getCurrentDirectory() + "/"+ clientMessage.split(" ")[1];

            if (new File(newDirectory).mkdirs()) {
                this.getRequestMessage().sendMessage(RequestMessage.CODE_226);
            }
        }
    }
}
