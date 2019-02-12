package tp1.commands;

import java.io.DataOutputStream;

import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;

public class CommandPort extends Command {

	public CommandPort(DataOutputStream dataOutputStream) {
		super(dataOutputStream);
	}

	public void execute(String clientMessage, ConfigurationClient configClient, ConfigurationServer configServer) {
		// TODO

		/*String data[] = clientMessage.split(",");
			String address = data[0].split(" ")[1] + "."+ data[1] + "."+ data[2] + data[3];
			int port = Integer.parseInt(data[4]) * 256 + Integer.parseInt(data[5]);*/
	}

}
