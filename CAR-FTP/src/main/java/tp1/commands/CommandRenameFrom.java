package tp1.commands;

import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;
import tp1.utils.RequestMessage;

import java.io.DataOutputStream;
/**
 * Class to define the RENAMEFROM command from the Client
 * 
 * @author irakoze & lepretre
 *
 */
public class CommandRenameFrom extends Command {
	/**
	 * CommandRenameFrom builder, it uses the Command builder. Initialize the attribute of
	 * the SuperClass.
	 * 
	 * @param dataOutputStream A DataOUTputStream object needed by the server to
	 *                         send message
	 */
    public CommandRenameFrom(DataOutputStream dataOutputStream) {
        super(dataOutputStream);
    }
    
    /* (non-Javadoc)
     * @see tp1.commands.Command#execute(java.lang.String, tp1.utils.ConfigurationClient, tp1.utils.ConfigurationServer)
     */
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
