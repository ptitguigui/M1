package tp1.commands;

import java.io.DataOutputStream;

import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;
import tp1.utils.RequestMessage;
/**
 * Class to define the PORT command from the Client
 * 
 * @author irakoze & lepretre
 *
 */
public class CommandPort extends Command {
	
	/**
	 * CommandPort builder, it uses the Command builder. Initialize the attribute of
	 * the SuperClass.
	 * 
	 * @param dataOutputStream A DataOUTputStream object needed by the server to
	 *                         send message
	 */
	public CommandPort(DataOutputStream dataOutputStream) {
		super(dataOutputStream);
	}

	/* (non-Javadoc)
	 * @see tp1.commands.Command#execute(java.lang.String, tp1.utils.ConfigurationClient, tp1.utils.ConfigurationServer)
	 */
	public void execute(String clientMessage, ConfigurationClient configClient, ConfigurationServer configServer) {
		// TODO

		String data[] = clientMessage.split(",");
			String address = data[0].split(" ")[1] + "."+ data[1] + "."+ data[2] + data[3];
			int port = Integer.parseInt(data[4]) * 256 + Integer.parseInt(data[5]);
			this.getRequestMessage()
			.sendMessage(RequestMessage.CODE_227
					.replace("PORT_DIVIDED",
							data[4])
					.replace("PORT_MODULO",
							data[5]));
	}

}
