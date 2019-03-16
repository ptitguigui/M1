package tp1.commands;

import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;
import tp1.utils.RequestMessage;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;

/**
 * Class to define the STORE command from the Client
 *
 * @author irakoze and lepretre
 */
public class CommandStore extends Command {
    /**
     * CommandStore builder, it uses the Command builder. Initialize the attribute
     * of the SuperClass.
     *
     * @param dataOutputStream A DataOUTputStream object needed by the server to
     *                         send message
     */
    public CommandStore(DataOutputStream dataOutputStream) {
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
            return;
        }

        this.getRequestMessage().sendMessage(RequestMessage.CODE_125);

        String filename = clientMessage.split(" ")[1];
        Socket transferSocket = getTransferSocket(configClient, configServer);
        InputStream in = transferSocket.getInputStream();

        String path[] = filename.split("/");
        String currentDirectory = configServer.getCurrentDirectory();
        if (path.length > 1) {
            for (int i = 0; i < path.length - 1; i++) {
                currentDirectory += "/" + path[i];
                File folder = new File(currentDirectory);
                if (!folder.exists()) {
                    folder.mkdirs();
                }
            }
        }

        File file = new File(currentDirectory + "/" + path[path.length - 1]);

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byte[] buffer = new byte[transferSocket.getReceiveBufferSize()];
        int bytesRead = 0;

        while ((bytesRead = in.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, bytesRead);
        }

        fileOutputStream.close();
        fileOutputStream.flush();
        transferSocket.close();
        this.getRequestMessage().sendMessage(RequestMessage.CODE_226);
    }
}
