package tp1.commands;

import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;
import tp1.utils.RequestMessage;

import java.io.DataOutputStream;
/**
 * Class to define the PWD command from the Client
 * 
 * @author irakoze and lepretre
 *
 */
public class CommandPwd extends Command {
	
	/**
	 * CommandPwd builder, it uses the Command builder. Initialize the attribute of
	 * the SuperClass.
	 * 
	 * @param dataOutputStream A DataOUTputStream object needed by the server to
	 *                         send message
	 */
    public CommandPwd(DataOutputStream dataOutputStream) {
        super(dataOutputStream);
    }

    /* (non-Javadoc)
     * @see tp1.commands.Command#execute(java.lang.String, tp1.utils.ConfigurationClient, tp1.utils.ConfigurationServer)
     */
    public void execute(String clientMessage, ConfigurationClient configClient, ConfigurationServer configServer) {
        if(!configClient.isLoggedIn()) {
            this.getRequestMessage().sendMessage(RequestMessage.CODE_530);
        }else {
            this.getRequestMessage().sendMessage((RequestMessage.CODE_257.replace("DIRECTORY", configServer.getCurrentDirectory())));
        }
    }
}
