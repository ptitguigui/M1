package tp1.commands;

import java.io.DataOutputStream;

import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;
import tp1.utils.RequestMessage;
/**
 * Class to handle all commands from the Client unknown by the server
 * 
 * @author irakoze and lepretre
 *
 */
public class CommandUnknown extends Command{
	/**
	 * CommandUnknown builder, it uses the Command builder. Initialize the attribute of
	 * the SuperClass.
	 * 
	 * @param dataOutputStream A DataOUTputStream object needed by the server to
	 *                         send message
	 */
	public CommandUnknown(DataOutputStream dataOutputStream) {
		super(dataOutputStream);
	}
	
	/* (non-Javadoc)
	 * @see tp1.commands.Command#execute(java.lang.String, tp1.utils.ConfigurationClient, tp1.utils.ConfigurationServer)
	 */
	public void execute(String clientMessage, ConfigurationClient configClient, ConfigurationServer configServer) {
		this.getRequestMessage().sendMessage(RequestMessage.CODE_502);
	}

}
