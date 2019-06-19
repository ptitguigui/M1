package tp1.commands;

import java.io.DataOutputStream;
import java.net.Socket;

import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;
import tp1.utils.ListDirectory;
import tp1.utils.RequestMessage;

/**
 * Class to define the LIST command from the Client
 *
 * @author irakoze and lepretre
 */
public class CommandList extends Command {

    /**
     * CommandList builder, it uses the Command builder. Initialize the attribute of
     * the SuperClass.
     *
     * @param dataOutputStream A DataOUTputStream object needed by the server to
     *                         send message
     */
    public CommandList(DataOutputStream dataOutputStream) {
        super(dataOutputStream);
    }


    /* (non-Javadoc)
     * @see tp1.commands.Command#execute(java.lang.String, tp1.utils.ConfigurationClient, tp1.utils.ConfigurationServer)
     */
    public void execute(String clientMessage, ConfigurationClient configClient, ConfigurationServer configServer) throws Exception {
        String list;
        if (!configClient.isLoggedIn()) {
            this.getRequestMessage().sendMessage(RequestMessage.CODE_530);
        } else {
            if (clientMessage.split(" ").length == 1) {
                list = ListDirectory.generateList(configServer.getCurrentDirectory());
            } else {
                String directory = clientMessage.split(" ")[1];
                if (directory.startsWith("/")) {
                    list = ListDirectory.generateList(directory);
                } else {
                    list = ListDirectory.generateList(configServer.getCurrentDirectory() + "/" + directory);
                }
            }
            this.getRequestMessage().sendMessage(RequestMessage.CODE_150);

            Socket transferSocket = getTransferSocket(configClient, configServer);
            DataOutputStream transferDataOutputStream = new DataOutputStream(transferSocket.getOutputStream());

            this.getRequestMessage().sendMessage(RequestMessage.CODE_226);
            transferDataOutputStream.writeBytes(list);
            transferDataOutputStream.flush();
            transferSocket.close();
        }
    }

}
