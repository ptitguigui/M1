package tp1.commands;

import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;
import tp1.utils.RequestMessage;

import java.io.DataOutputStream;
import java.io.File;

public class CommandRenameTo extends Command {
    public CommandRenameTo(DataOutputStream dataOutputStream) {
        super(dataOutputStream);
    }

    public void execute(String clientMessage, ConfigurationClient configClient, ConfigurationServer configServer) throws Exception {
        if (!configClient.isLoggedIn()) {
            this.getRequestMessage().sendMessage(RequestMessage.CODE_530);
        } else {

            String filename = configClient.getCurrentFile();
            String fileRename = clientMessage.split(" ")[1];

            File file = new File(configServer.getCurrentDirectory() + "/" + filename);
            File newFile = new File(configServer.getCurrentDirectory() + "/" + fileRename);
            if (file.renameTo(newFile)) {
                System.out.println("File rename success");
                this.getRequestMessage().sendMessage(RequestMessage.CODE_250);
            } else {
                System.out.println("File rename failed");
                this.getRequestMessage().sendMessage(RequestMessage.CODE_450);
            }
        }
    }
}
