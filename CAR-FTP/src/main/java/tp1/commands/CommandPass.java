package tp1.commands;

import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;
import tp1.utils.RequestMessage;

import java.io.DataOutputStream;
import java.util.HashMap;

public class CommandPass extends Command {

    public CommandPass(DataOutputStream dataOutputStream) {
        super(dataOutputStream);
    }

    public void execute(String clientMessage, ConfigurationClient configClient, ConfigurationServer configServer) {
        HashMap<String, String> users = configServer.getUsers();

        String mdpClient = clientMessage.split(" ")[1];
        String goodMdp = users.get(configClient.getLogin());

        System.out.println(goodMdp);

        if (mdpClient.equals(goodMdp)) {
            configClient.setLoggedIn(true);
            this.getRequestMessage().sendMessage(RequestMessage.CODE_230);
        } else {
            this.getRequestMessage().sendMessage(RequestMessage.CODE_530);
        }
    }

}
