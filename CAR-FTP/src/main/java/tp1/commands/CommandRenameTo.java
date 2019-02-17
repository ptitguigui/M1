package tp1.commands;

import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;
import tp1.utils.RequestMessage;

import java.io.DataOutputStream;
import java.io.File;
/**
 * Class to define the RENAMETO command from the Client
 * 
 * @author irakoze & lepretre
 *
 */
public class CommandRenameTo extends Command {
	/**
	 * CommandRenameTo builder, it uses the Command builder. Initialize the
	 * attribute of the SuperClass.
	 * 
	 * @param dataOutputStream A DataOUTputStream object needed by the server to
	 *                         send message
	 */
	public CommandRenameTo(DataOutputStream dataOutputStream) {
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
