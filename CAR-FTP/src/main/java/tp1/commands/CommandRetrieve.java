package tp1.commands;

import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;
import tp1.utils.RequestMessage;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

/**
 * Class to define the RETRIEVE command from the Client
 *
 * @author irakoze & lepretre
 */
public class CommandRetrieve extends Command {
    /**
     * CommandRetrieve builder, it uses the Command builder. Initialize the
     * attribute of the SuperClass.
     *
     * @param dataOutputStream A DataOUTputStream object needed by the server to
     *                         send message
     */
    public CommandRetrieve(DataOutputStream dataOutputStream) {
        super(dataOutputStream);
    }

    /*
     * (non-Javadoc)
     *
     * @see tp1.commands.Command#execute(java.lang.String,
     * tp1.utils.ConfigurationClient, tp1.utils.ConfigurationServer)
     */
    public void execute(String clientMessage, ConfigurationClient configClient, ConfigurationServer configServer)
            throws Exception {
        if (!configClient.isLoggedIn()) {
            this.getRequestMessage().sendMessage(RequestMessage.CODE_530);
        } else {
            this.getRequestMessage().sendMessage(RequestMessage.CODE_125);

            String filename = clientMessage.split(" ")[1];
            Socket transferSocket = getTransferSocket(configClient, configServer);
            DataOutputStream transferDataOutputStream = new DataOutputStream(transferSocket.getOutputStream());

            File file = new File(configServer.getCurrentDirectory() + "/" + filename);

            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[transferSocket.getSendBufferSize()];
            int bytesRead = 0;

            while ((bytesRead = fileInputStream.read(buffer)) > 0) {
                transferDataOutputStream.write(buffer, 0, bytesRead);
            }

            fileInputStream.close();
            transferDataOutputStream.flush();
            transferSocket.close();
            this.getRequestMessage().sendMessage(RequestMessage.CODE_226);
        }
    }
}
