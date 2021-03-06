package tp1.commands;

import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;
import tp1.utils.RequestMessage;

import java.io.DataOutputStream;

public class CommandExtendPassive extends Command {
    public CommandExtendPassive(DataOutputStream dataOutputStream) {
        super(dataOutputStream);
    }

    /* (non-Javadoc)
     * @see tp1.commands.Command#execute(java.lang.String, tp1.utils.ConfigurationClient, tp1.utils.ConfigurationServer)
     */
    public void execute(String clientMessage, ConfigurationClient configClient, ConfigurationServer configServer) throws Exception {
        if (!configClient.isLoggedIn()) {
            this.getRequestMessage().sendMessage(RequestMessage.CODE_530);
        } else {
            this.getRequestMessage().sendMessage(RequestMessage.CODE_229.replace("PORT", String.valueOf(configServer.getTransferServerSocket().getLocalPort())));
        }
    }
}
