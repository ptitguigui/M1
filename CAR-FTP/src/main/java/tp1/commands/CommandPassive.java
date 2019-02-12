package tp1.commands;

import java.io.DataOutputStream;
import java.net.ServerSocket;

import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;
import tp1.utils.RequestMessage;

public class CommandPassive extends Command {

	public CommandPassive(DataOutputStream dataOutputStream) {
		super(dataOutputStream);
	}

	public void execute(String clientMessage, ConfigurationClient configClient, ConfigurationServer configServer) {
		if (!configClient.isLoggedIn()) {
			this.getRequestMessage().sendMessage(RequestMessage.CODE_530);
		} else {
			configClient.setPassiveMode(true);
			int p1 = 118;
			int p2 = 218;

			this.getRequestMessage().sendMessage(
					(RequestMessage.CODE_227).replace("PORT_DIVIDED", p1+"").replace("PORT_MODULO", p2+""));
			try {
				ServerSocket serverData = new ServerSocket(p1*256+p2);
				configServer.setServerSocket(serverData);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
