package tp1.commands;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.List;

import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;
import tp1.utils.ListDirectory;
import tp1.utils.RequestMessage;

public class CommandList extends Command {

	public CommandList(DataOutputStream dataOutputStream) {
	    super(dataOutputStream);
	}

	public void execute(String clientMessage, ConfigurationClient configClient, ConfigurationServer configServer) throws Exception {
        if(!configClient.isLoggedIn()) {
            this.getRequestMessage().sendMessage(RequestMessage.CODE_530);
        }else {
            String list = ListDirectory.generateList(configServer.getCurrentDirectory());
            this.getRequestMessage().sendMessage(RequestMessage.CODE_150);
            Socket transferSocket = configServer.getTransferServerSocket().accept();
            DataOutputStream transferDataOutputStream = new DataOutputStream(transferSocket.getOutputStream());

            transferDataOutputStream.writeBytes(list);
            transferDataOutputStream.flush();
            transferSocket.close();
            this.getRequestMessage().sendMessage(RequestMessage.CODE_226);
        }
	}

}
