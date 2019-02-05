package tp1.commands;

import java.io.DataOutputStream;

import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;
import tp1.utils.RequestMessage;

public class CommandUser extends Command {

	public CommandUser(DataOutputStream dataOutputStream) {
		super(dataOutputStream);
	}

	public void execute(String clientMessage, ConfigurationClient configClient, ConfigurationServer configServer) {
		configClient.setLogin(clientMessage.split(" ")[1]);
		this.getRequestMessage().sendMessage(RequestMessage.CODE_331);
	}

}
