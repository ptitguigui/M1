package tp1.commands;

import java.io.DataOutputStream;

import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;
import tp1.utils.RequestMessage;
/**
 * Class to define the QUIT command from the Client
 * 
 * @author irakoze & lepretre
 *
 */
public class CommandQuit extends Command {

	/**
	 * CommandQuit builder, it uses the Command builder. Initialize the attribute of
	 * the SuperClass.
	 * 
	 * @param dataOutputStream A DataOUTputStream object needed by the server to
	 *                         send message
	 */
	public CommandQuit(DataOutputStream dataOutputStream) {
		super(dataOutputStream);
	}

	/* (non-Javadoc)
	 * @see tp1.commands.Command#execute(java.lang.String, tp1.utils.ConfigurationClient, tp1.utils.ConfigurationServer)
	 */
	public void execute(String clientMessage, ConfigurationClient configClient, ConfigurationServer configServer) {
		System.out.println("Client Disconnected");
		this.getRequestMessage().sendMessage(RequestMessage.CODE_221);
	}

}
