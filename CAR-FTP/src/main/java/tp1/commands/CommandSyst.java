package tp1.commands;

import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;
import tp1.utils.RequestMessage;

import java.io.DataOutputStream;
/**
 * Class to define the SYST command from the Client
 * 
 * @author irakoze & lepretre
 *
 */
public class CommandSyst extends Command {
	/**
	 * CommandSyst builder, it uses the Command builder. Initialize the attribute of
	 * the SuperClass.
	 * 
	 * @param dataOutputStream A DataOUTputStream object needed by the server to
	 *                         send message
	 */
	public CommandSyst(DataOutputStream dataOutputStream) {
		super(dataOutputStream);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tp1.commands.Command#execute(java.lang.String,
	 * tp1.utils.ConfigurationClient, tp1.utils.ConfigurationServer)
	 */
	public void execute(String clientMessage, ConfigurationClient configClient, ConfigurationServer configServer) {
		if (!configClient.isLoggedIn()) {
			this.getRequestMessage().sendMessage(RequestMessage.CODE_530);
		} else {
			this.getRequestMessage().sendMessage(RequestMessage.CODE_215);
		}
	}
}
