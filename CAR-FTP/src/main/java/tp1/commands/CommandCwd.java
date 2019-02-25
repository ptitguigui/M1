package tp1.commands;

import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;
import tp1.utils.RequestMessage;

import java.io.DataOutputStream;
import java.io.File;

/**
 * Class to define the CWD command from the Client
 * 
 * @author irakoze & lepretre
 *
 */
public class CommandCwd extends Command {

	/**
	 * CommandCwd builder, it uses the Command builder. Initialize the attribute of
	 * the SuperClass.
	 * 
	 * @param dataOutputStream A DataOUTputStream object needed by the server to
	 *                         send message
	 */
	public CommandCwd(DataOutputStream dataOutputStream) {
		super(dataOutputStream);
	}

	/* (non-Javadoc)
	 * @see tp1.commands.Command#execute(java.lang.String, tp1.utils.ConfigurationClient, tp1.utils.ConfigurationServer)
	 */
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

			String rootDirectory = configServer.getRootDirectory();
			if (currentDirectory.contains(rootDirectory)) {
				File tmpDir = new File(currentDirectory);
				if (tmpDir.exists()) {
					configServer.setCurrentDirectory(currentDirectory);
					this.getRequestMessage()
							.sendMessage(RequestMessage.CODE_200);
				} else {
					this.getRequestMessage().sendMessage(RequestMessage.CODE_550);
				}
			} else {
				this.getRequestMessage().sendMessage(RequestMessage.CODE_421);
			}
		}
	}
}
