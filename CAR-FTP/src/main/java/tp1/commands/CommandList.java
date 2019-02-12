package tp1.commands;

import java.io.DataOutputStream;
import java.net.Socket;

import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;
import tp1.utils.RequestMessage;

public class CommandList extends Command {

	public CommandList(DataOutputStream dataOutputStream) {
		super(dataOutputStream);
	}

	public void execute(String clientMessage, ConfigurationClient configClient, ConfigurationServer configServer) {
		try {
			Socket socket = configServer.getServerSocket().accept();
			DataOutputStream data = new DataOutputStream(socket.getOutputStream());
			RequestMessage message = new RequestMessage(data);
			message.sendMessage("-rw-r--r--  1 lepretreg m1 1102 Feb  5 17:03 CAR-FTP.iml \r\n");
			this.getRequestMessage().sendMessage(RequestMessage.CODE_226);
			socket.close();
			configServer.getServerSocket().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
