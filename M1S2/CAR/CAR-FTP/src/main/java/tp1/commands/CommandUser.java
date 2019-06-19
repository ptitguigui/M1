package tp1.commands;

import java.io.DataOutputStream;

import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;
import tp1.utils.RequestMessage;

/**
 * Class to define the USER command from the Client
 * 
 * @author irakoze and lepretre
 *
 */
public class CommandUser extends Command {
	/**
	 * CommandUser builder, it uses the Command builder. Initialize the attribute of
	 * the SuperClass.
	 * 
	 * @param dataOutputStream A DataOUTputStream object needed by the server to
	 *                         send message
	 */
    public CommandUser(DataOutputStream dataOutputStream) {
        super(dataOutputStream);
    }

    /* (non-Javadoc)
     * @see tp1.commands.Command#execute(java.lang.String, tp1.utils.ConfigurationClient, tp1.utils.ConfigurationServer)
     */
    public void execute(String clientMessage, ConfigurationClient configClient, ConfigurationServer configServer) {
        String login = clientMessage.split(" ")[1];

        if (configServer.getUsers().containsKey(login)) {
            this.getRequestMessage().sendMessage(RequestMessage.CODE_331);
            configClient.setLogin(login);
        } else {
            this.getRequestMessage().sendMessage(RequestMessage.CODE_332);
        }
    }

}
