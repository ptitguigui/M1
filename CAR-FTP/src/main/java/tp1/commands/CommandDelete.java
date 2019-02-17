package tp1.commands;

import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;
import tp1.utils.RequestMessage;

import java.io.DataOutputStream;
import java.io.File;

/**
 * Class to define the DELETE command from the Client
 * 
 * @author irakoze & lepretre
 *
 */
public class CommandDelete extends Command {

	/**
	 * CommandDelete builder, it uses the Command builder. Initialize the attribute of
	 * the SuperClass.
	 * 
	 * @param dataOutputStream A DataOUTputStream object needed by the server to
	 *                         send message
	 */
    public CommandDelete(DataOutputStream dataOutputStream) {
        super(dataOutputStream);
    }


    /* (non-Javadoc)
     * @see tp1.commands.Command#execute(java.lang.String, tp1.utils.ConfigurationClient, tp1.utils.ConfigurationServer)
     */
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
