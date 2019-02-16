package tp1.commands;

import java.io.DataOutputStream;

import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;
import tp1.utils.RequestMessage;

public class CommandPassive extends Command {

    public CommandPassive(DataOutputStream dataOutputStream) {
        super(dataOutputStream);
    }

    public void execute(String clientMessage, ConfigurationClient configClient, ConfigurationServer configServer) {
        if (!configClient.isLoggedIn()) {
            this.getRequestMessage().sendMessage(RequestMessage.CODE_530);
        } else {

            this.getRequestMessage().sendMessage(RequestMessage.CODE_227
                    .replace("PORT_DIVIDED", String.valueOf(configServer.getTransferServerSocket().getLocalPort() / 256))
                    .replace("PORT_MODULO", String.valueOf(configServer.getTransferServerSocket().getLocalPort() % 256)));
        }
    }

}
