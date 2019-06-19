package tp1.commands;

import java.io.DataOutputStream;

import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;
import tp1.utils.RequestMessage;

/**
 * Class to define the PASSIVE command from the Client. It changes the
 * connection type from active to passive.
 * 
 * @author irakoze and lepretre
 *
 */
public class CommandPassive extends Command {

	/**
	 * CommandPassive builder, it uses the Command builder. Initialize the attribute
	 * of the SuperClass.
	 * 
	 * @param dataOutputStream A DataOUTputStream object needed by the server to
	 *                         send message
	 */
	public CommandPassive(DataOutputStream dataOutputStream) {
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
			configClient.setPassiveMode(true);
			this.getRequestMessage()
					.sendMessage(RequestMessage.CODE_227
							.replace("PORT_DIVIDED",
									String.valueOf(configServer.getTransferServerSocket().getLocalPort() / 256))
							.replace("PORT_MODULO",
									String.valueOf(configServer.getTransferServerSocket().getLocalPort() % 256)));
		}
	}

}
