package tp1.commands;

import java.io.DataOutputStream;

import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;
import tp1.utils.RequestMessage;

public abstract class Command {

	private RequestMessage requestMessage;
	
	public Command(DataOutputStream dataOutputStream) {
		this.requestMessage = new RequestMessage(dataOutputStream);
	}
	
	public abstract void execute(String clientMessage, ConfigurationClient configClient, ConfigurationServer configServer);

	public RequestMessage getRequestMessage() {
		return requestMessage;
	}

	public void setRequestMessage(RequestMessage requestMessage) {
		this.requestMessage = requestMessage;
	}
}
