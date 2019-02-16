package tp1.commands;

import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;
import tp1.utils.RequestMessage;

import java.io.DataOutputStream;
import java.io.File;

public class CommandCwd extends Command {

    public CommandCwd(DataOutputStream dataOutputStream) {
        super(dataOutputStream);
    }

    public void execute(String clientMessage, ConfigurationClient configClient, ConfigurationServer configServer) {
        if (!configClient.isLoggedIn()) {
            this.getRequestMessage().sendMessage(RequestMessage.CODE_530);
        } else {

            String directory = clientMessage.split(" ")[1];
            String currentDirectory = configServer.getCurrentDirectory();

            System.out.println("client want to change to " + directory);

            if (directory.equals("..")) {
                String[] dir = currentDirectory.split("/");
                currentDirectory = "/";
                for (int i = 1; i < dir.length - 1; i++) {
                    currentDirectory += dir[i] + "/";
                }
            } else {
                if (directory.startsWith("/")) {
                    currentDirectory = directory + "/";
                } else {
                    currentDirectory += "/" + directory + "/";
                }
            }
            File tmpDir = new File(currentDirectory);
            if (tmpDir.exists()) {
                configServer.setCurrentDirectory(currentDirectory);
                this.getRequestMessage().sendMessage(RequestMessage.CODE_200.replace("DIRECTORY", currentDirectory));
            } else {
                this.getRequestMessage().sendMessage(RequestMessage.CODE_550);
            }
        }
    }
}

