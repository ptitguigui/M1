package tp1.commands;

import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;
import tp1.utils.RequestMessage;

import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Abstract class to handle client command
 *
 * @author irakoze & lepretre
 */
public abstract class Command {

    private RequestMessage requestMessage;

    /**
     * Command builder. Initialize the attribute of the Class.
     *
     * @param dataOutputStream A DataOUTputStream object needed by the server to
     *                         send message
     */
    Command(DataOutputStream dataOutputStream) {
        this.requestMessage = new RequestMessage(dataOutputStream);
    }

    /**
     * Abstract method to define the action to be done by the server from the client
     * message
     *
     * @param clientMessage the message from the client
     * @param configClient  instance of ConfigurationClient containing informations
     *                      about the client
     * @param configServer  instance of ConfigurationServer containing informations
     *                      on the server
     * @throws Exception
     */
    public abstract void execute(String clientMessage, ConfigurationClient configClient,
                                 ConfigurationServer configServer) throws Exception;

    /**
     * Getter of the RequestMessage instance
     *
     * @return requestMessage an instance of RequestMessage class to handle the
     * message associate to request from the FTP server
     */
    RequestMessage getRequestMessage() {
        return requestMessage;
    }


    Socket getTransferSocket(ConfigurationClient configClient, ConfigurationServer configServer) throws Exception {
        Socket transferSocket;
        if (configClient.isPassiveMode()) {
            transferSocket = configServer.getTransferServerSocket().accept();
        } else {
            transferSocket = configServer.getSocketTransfer();
        }
        return transferSocket;
    }

}
