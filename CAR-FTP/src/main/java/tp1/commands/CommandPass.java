package tp1.commands;

import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;
import tp1.utils.RequestMessage;

import java.io.DataOutputStream;
import java.util.HashMap;

/**
 * Class to define the PASS command from the Client
 * 
 * @author irakoze and lepretre
 *
 */
public class CommandPass extends Command {
	/**
	 * CommandPass builder, it uses the Command builder. Initialize the attribute of
	 * the SuperClass.
	 * 
	 * @param dataOutputStream A DataOUTputStream object needed by the server to
	 *                         send message
	 */
    public CommandPass(DataOutputStream dataOutputStream) {
        super(dataOutputStream);
    }

    /* (non-Javadoc)
     * @see tp1.commands.Command#execute(java.lang.String, tp1.utils.ConfigurationClient, tp1.utils.ConfigurationServer)
     */
    public void execute(String clientMessage, ConfigurationClient configClient, ConfigurationServer configServer) {
        HashMap<String, String> users = configServer.getUsers();

        String mdpClient = clientMessage.split(" ")[1];
        String goodMdp = users.get(configClient.getLogin());

        System.out.println(goodMdp);

        if (mdpClient.equals(goodMdp)) {
            configClient.setLoggedIn(true);
            this.getRequestMessage().sendMessage(RequestMessage.CODE_230);
        } else {
            this.getRequestMessage().sendMessage(RequestMessage.CODE_530);
        }
    }

}
