package tp1.commands;

import java.io.DataOutputStream;

import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;
import tp1.utils.RequestMessage;

public class CommandUnknown extends Command{

	public CommandUnknown(DataOutputStream dataOutputStream) {
		super(dataOutputStream);
	}

	public void execute(String clientMessage, ConfigurationClient configClient, ConfigurationServer configServer) {
		this.getRequestMessage().sendMessage(RequestMessage.CODE_502);
	}

}
