package tp1.commands;

import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;
import tp1.utils.RequestMessage;

import java.io.DataOutputStream;

public abstract class Command {

    private RequestMessage requestMessage;

    Command(DataOutputStream dataOutputStream) {
        this.requestMessage = new RequestMessage(dataOutputStream);
    }

    public abstract void execute(String clientMessage, ConfigurationClient configClient, ConfigurationServer configServer) throws Exception;

    RequestMessage getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(RequestMessage requestMessage) {
        this.requestMessage = requestMessage;
    }

}
