package tp1.commands;

import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;
import tp1.utils.RequestMessage;

import java.io.DataOutputStream;

public class CommandReady extends Command {

    public CommandReady(DataOutputStream dataOutputStream) {
        super(dataOutputStream);
    }

    public void execute(String clientMessage, ConfigurationClient configClient, ConfigurationServer configServer) {
        this.getRequestMessage().sendMessage(RequestMessage.CODE_220);
    }
}
